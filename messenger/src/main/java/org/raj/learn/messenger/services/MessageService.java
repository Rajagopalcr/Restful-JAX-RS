package org.raj.learn.messenger.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.persistence.exceptions.i18n.DatabaseExceptionResource;
import org.raj.learn.messenger.db.DatabaseClass;
import org.raj.learn.messenger.exception.DataNotFoundException;
import org.raj.learn.messenger.model.Comment;
import org.raj.learn.messenger.model.Message;

public class MessageService {	
	
	private static Map<Long,Message> messages = DatabaseClass.getAllMessages();
	
	public MessageService() {
		Message m1 = new Message(1L,"Hello World!","Rakshith");
		Message m2 = new Message(2L,"Hello Jersey!","Rajagopal");
		messages.put(m1.getId(), m1);
		messages.put(m2.getId(), m2);
		
		Map<Long,Comment> map1 = new HashMap<>();
		Map<Long,Comment> map2 = new HashMap<>();
		map1.put(1L, new Comment(1L,"Hi Rakshi, Super pic!","Sudhi",new Date()));
		map1.put(2L, new Comment(1L,"Hi Rakshi, En samachara?","Murali",new Date()));
		map2.put(3L, new Comment(1L,"Hello Ranju, How are you?","Sowmya",new Date()));
		map2.put(4L, new Comment(1L,"Hello Ranju anna, Profile pic super?","Saagu",new Date()));		
		
		m1.setComments(map1);
		m2.setComments(map2);
		
	}

	public List<Message> getAllMessages(){
		return new ArrayList(messages.values());
	}
	
	public List<Message> getAllMessageForYear(int year){		
		Calendar cal = Calendar.getInstance();
		List<Message> messagesForYear = new ArrayList<>();	
		
		for(Message message : messages.values()){
			cal.setTime(message.getDate());
			if(cal.get(Calendar.YEAR) == year){
				messagesForYear.add(message);
			}
			
		}		
		return messagesForYear;
	}
	
	public List<Message> getAllMessageBySize(int start,int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()) return new ArrayList<Message>(messages.values());
		return list.subList(start, start+size);
	}
	
	public Message getMessage(Long id){
		Message message = messages.get(id);
		if(message == null){
			throw new DataNotFoundException("Message with id: "+id+" is not found");
		}		
		return message;
	}
	
	public Message addMessage(Message message){
		message.setId((long) (messages.size()+1));
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if(message.getId() < 0){
			return message;
		}else{
			messages.put(message.getId(), message);
			return message;
		}		
	}
	
	public Message removeMessage(Long id){
		return messages.remove(id);
	}
	
}
