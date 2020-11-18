package com.revature.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.revature.DTOs.RSSAccountDTO;
import com.revature.DTOs.RSSUserDTO;
import com.revature.clients.RSSClient;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.security.JwtUtil;

@Service
public class RSSService {

	@Autowired
	private UserRepository userRepository;

	//@Autowired
	private CircuitBreakerFactory<?, ?> cbFactory;
	
	@Autowired
	private RSSClient rssClient;
	
	@Autowired
	JwtUtil jwtUtil;
	
	public ResponseEntity<User> login(RSSUserDTO user) { 

		return this.cbFactory.create("login").run( 
			() -> {
					RSSUserDTO u = rssClient.loginUser(user); 
					User auth; 
					if(u == null) {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
					}
					List<RSSAccountDTO> tempAccts = rssClient.findAccountByUserId(u);
					RSSAccountDTO acc = new RSSAccountDTO(tempAccts.get(0));
					
					if (!userRepository.existsById(u.getUserId())) {
						auth = new User(u.getUserId(), u.isAdmin(), u.getEmail(), u.getFirstName(), u.getLastName());
						auth.setRSSAccountId(acc.getAccId());
						auth.setPoints(acc.getPoints());
						userRepository.save(auth);
						auth.setJwt(jwtUtil.generateToken(auth));
						return ResponseEntity.ok(auth);
					}else {
						Optional<User> optAuth = userRepository.findById(u.getUserId());
						if(optAuth.isPresent()) {
							auth = optAuth.get();
							auth.setRSSAccountId(acc.getAccId());
							auth.setPoints(acc.getPoints());
							userRepository.save(auth);
							auth.setJwt(jwtUtil.generateToken(auth));
							return ResponseEntity.ok(auth);
						}
					}
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
				},
				throwable -> loginFallback());
}
	  
	 public ResponseEntity<User> loginFallback() { 
		 return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null); 
	 }
	 
	 public Collection<GrantedAuthority> getAuthority(User u){
			Collection<GrantedAuthority>auths = new ArrayList<>();
			Optional<User> optUser = userRepository.findById(u.getUserID());
			SimpleGrantedAuthority a= null; 
			
			if (optUser.isPresent()) {
				User user = optUser.get();
				if(user.isAdmin()) {
					a = new SimpleGrantedAuthority("ADMIN");
					auths.add(a);
					a = new SimpleGrantedAuthority("USER");
					auths.add(a);
				}else {
					a = new SimpleGrantedAuthority("USER");
					auths.add(a);
					
				}
			}
			return auths;
	 }

	public ResponseEntity<User> getUserByEmail(String email) {
		User auth = userRepository.findByEmail(email);
		if(auth != null) {
			return ResponseEntity.ok(auth);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

}
