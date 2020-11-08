package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.revature.repositories.UserRepository;

@Service
public class RSSService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired private CircuitBreakerFactory<?, ?> cbFactory;
	 
	/*
	 * public ResponseEntity<User> login(RSSUserDTO user) { User auth = new User();
	 * 
	 * return this.cbFactory.create("login").run( // Positive Case () -> {
	 * RSSUserDTO u = rssClient.getLogin(user); auth.setUserID(u.getUserId());
	 * return ResponseEntity.ok(auth); },
	 * 
	 * // Negative Case throwable -> loginFallback()); }
	 * 
	 * public ResponseEntity<User> loginFallback() { return
	 * ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null); }
	 */
	 
	
}

