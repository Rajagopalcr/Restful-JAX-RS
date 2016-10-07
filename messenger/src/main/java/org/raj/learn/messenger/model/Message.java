package org.raj.learn.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

	
	private long id;
	private String message;
	private String author;
	private Date date;
	private static Map<Long,Comment> comments = new HashMap<>();	
	private List<Link> links = new ArrayList<>();

	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(long id,String message,String author){
		this.id=id;
		this.message=message;
		this.author=author;
		this.date = new Date();				
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLink() {
		return links;
	}

	public void setLink(List<Link> links) {
		this.links = links;
	}	
	
	public void addLink(String uri,String rel){	
		Link list = new Link();
		list.setLink(uri);
		list.setRel(rel);
		links.add(list);
	}
}
