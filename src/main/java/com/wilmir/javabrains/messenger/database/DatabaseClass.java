package com.wilmir.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.wilmir.javabrains.messenger.model.*;

public class DatabaseClass {

	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	

	public static Map<Long, Message> getMessages(){
		System.out.println("Databse Service has been called");
		System.out.println("The content of the database are:");
		for(Long key:messages.keySet()) {
			System.out.println(key + " : " + messages.get(key).getMessage());
		}	
		return messages;
	}
	
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
	
	
	
}
