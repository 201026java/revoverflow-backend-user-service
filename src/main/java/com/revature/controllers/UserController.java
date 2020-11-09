package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.DTOs.RSSUserDTO;
import com.revature.models.User;
import com.revature.services.RSSService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(
		origins = { "http://localhost:8500" }, 
		methods = { RequestMethod.GET, RequestMethod.PUT, 
					RequestMethod.PATCH, RequestMethod.POST },
		allowedHeaders = { "content-type" }
	)
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	RSSService rssService;
	

	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		
		return userService.getUserById(id);
	}
	
	@PostMapping("/login") 
	public ResponseEntity<User> loginUser(@RequestBody RSSUserDTO user) { 
		return rssService.login(user); 
	}
	

}