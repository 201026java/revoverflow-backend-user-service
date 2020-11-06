package com.revature.messaging;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.revature.repositories.UserRepository;


public class MessageService {
	
	private static Set<Integer> eventCache = new HashSet<>();
	
	@Autowired
	private UserRepository userDao;

	@Autowired
	private KafkaTemplate<String, MessageEvent> kt;
	
	public void triggerUserEvent(MessageEvent event) {
		eventCache.add(event.hashCode());
		
		if(event.getOperation() == Operation.DELETE) {
			kt.send("user", event);
		}
		
		kt.send("user", event);
	}
	
	@KafkaListener(topics = "user")
	public void processMessageEvent(MessageEvent event) {
		if(eventCache.contains(event.hashCode())) {
			eventCache.remove(event.hashCode());
			return;
		}
		
		//Flashcard card = event.getFlashcard();
		
		switch(event.getOperation()) {
		case CREATE:
			//this.userDao.save(card);
			break;
		case UPDATE:
			//this.userDao.save(card);
			break;
		case DELETE:
			//this.userDao.deleteById(card.getId());
			break;
		}
	}
}
