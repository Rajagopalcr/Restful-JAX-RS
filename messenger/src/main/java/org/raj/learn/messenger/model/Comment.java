package org.raj.learn.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {
	
	private long id;
	private String comment;
	private String author;
	private Date created;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Comment(long id,String comment,String author,Date created) {
		this.id = id;
		this.comment = comment;
		this.author = author;
		this.created = created;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
