package org.raj.learn.messenger.db;

import java.util.HashMap;
import java.util.Map;

import org.raj.learn.messenger.model.Message;

public class DatabaseClass {
	
	public static Map<Long,Message> messages = new HashMap<>();
	public static Map<Long,Message> profiles = new HashMap<>();
	
	public static Map<Long,Message> getAllMessages(){
		return messages;
	}
	
	public static Map<Long,Message> getAllProfiles(){
		return profiles;
	}
}
