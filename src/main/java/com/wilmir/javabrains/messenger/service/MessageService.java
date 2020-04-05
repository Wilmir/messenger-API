package com.wilmir.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.wilmir.javabrains.messenger.database.DatabaseClass;
import com.wilmir.javabrains.messenger.exception.DataNotFoundException;
import com.wilmir.javabrains.messenger.model.Comment;
import com.wilmir.javabrains.messenger.model.Message;

public class MessageService {

	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	
	public MessageService() {
	    Comment c1 = new Comment (1L,"One comment","wilmir");  
	    Comment c2 = new Comment (2L,"Another comment","wilmir");
	    Comment c3 = new Comment (3L,"And another comment more","wilmir");
		
		messages.put(1L, new Message(1, "Hello World", "wilmir"));
		messages.put(2L, new Message(2, "Hello Jersey", "wilmir"));
		System.out.println("Message Service isntance has been created");

		Map<Long,Comment> comments1 = messages.get(1L).getComments();
		comments1.put(c1.getId(),c1);
		comments1.put(c2.getId(),c2);
		comments1.put(c3.getId(),c3);
		
		Map<Long,Comment> comments2 = messages.get(2L).getComments();
		comments2.put(c1.getId(),c1);
		comments2.put(c2.getId(),c2);
		comments2.put(c3.getId(),c3);
		
	}
	
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	
	
	public List<Message> getAllMessagesPerYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
			
		}
		
		
		return messagesForYear;
		
	}
	
	
	
	public List<Message> getAllMessagesPaginated(int start, int size){
		List<Message> list = new ArrayList<>(messages.values());
		
		if(start + size > list.size()) return new ArrayList<Message>();
		
		return list.subList(start, start+size);
		
	}
	
		
	
	public Message getMessage(long id) {
		Message message =  messages.get(id);
		
		if(message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		
		return message;
		
	}
	
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(),message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		
		messages.put(message.getId(), message);
		return message;		
	}
	
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
}
