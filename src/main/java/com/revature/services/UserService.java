package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	RSSService rssService;
	

	public List<User> getAllUser(){
		List<User> users = userRepository.findAll();

		if(users.size() != 0) {
			return users;		
		}

		return null;
	}
	
	/**
	 * @author Ryan Clayton
	 * @param id this is the user's Id
	 * @return User this returns the user entity with updated RSS points
	 */

	public User getUserById(int id) {
			try {
				Optional<User> optUser = userRepository.findById(id);
				if(optUser.isPresent()) {
					return optUser.get();
				
					//user.setPoints(rssService.getPoints(id));
					//return userRepository.save(user);
				}
			}catch(Exception e) {

				//Logger logger = Logger.getLogger(UserService.class);
				//logger.error("error in UserService getUserById", e);
				return null;
			}
			return null;
	}

}
