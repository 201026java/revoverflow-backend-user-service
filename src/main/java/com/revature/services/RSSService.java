package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.revature.DTOs.RSSAccountDTO;
import com.revature.DTOs.RSSUserDTO;
import com.revature.clients.RSSClient;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class RSSService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CircuitBreakerFactory<?, ?> cbFactory;
	
	@Autowired
	private RSSClient rssClient;
	
	public ResponseEntity<User> login(RSSUserDTO user) { 

		return this.cbFactory.create("login").run( 
			() -> {
					RSSUserDTO u = rssClient.loginUser(user); 
					if(u == null) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
					}
					User auth = new User(u.getUserId(), 0, 0, u.isAdmin(), null, u.getEmail(), u.getFirstName(), u.getLastName());
					
					if (!userRepository.existsById(auth.getUserID())) {
						//Remember to use this line
						List<RSSAccountDTO> tempAccts = rssClient.findAccountByUserId(u);
						RSSAccountDTO acc = new RSSAccountDTO(tempAccts.get(0));
						auth.setRSSAccountId(acc.getAccId());
						auth.setPoints(acc.getPoints());
					}else {
						
					}
					return ResponseEntity.ok(userRepository.save(auth));
				},
				throwable -> loginFallback());
}
	  
	 public ResponseEntity<User> loginFallback() { 
		 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null); 
	 }
	 

}
