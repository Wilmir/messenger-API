package com.wilmir.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wilmir.javabrains.messenger.database.DatabaseClass;
import com.wilmir.javabrains.messenger.model.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	
	public ProfileService() {
		profiles.put("wilmir", new Profile(1L, "wilmir", "Wilmir", "Nicanor"));
		profiles.put("ping", new Profile(2L, "ping", "Ping", "Tienwutinum"));

	}
	
	
	
	
	public List<Profile> getProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		
		profiles.put(profile.getProfileName(),profile);
		
		return profile;
	}
	
	
	public Profile updateProfile(Profile profile) {
		if(profile.getId() <= 0) {
			return null;
		}
		
		profiles.put(profile.getProfileName(), profile);
		
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
	
	

}
