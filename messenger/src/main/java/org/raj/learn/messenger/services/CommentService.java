package org.raj.learn.messenger.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.raj.learn.messenger.db.DatabaseClass;
import org.raj.learn.messenger.model.Comment;
import org.raj.learn.messenger.model.ErrorMessage;
import org.raj.learn.messenger.model.Message;

public class CommentService {
	
	private static Map<Long,Message> messages = DatabaseClass.getAllMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return new ArrayList(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId){
		Message message = messages.get(messageId);
		ErrorMessage error = new ErrorMessage("Not found",404,"www.google.com");
		Response response = Response.status(Status.NOT_FOUND).entity(error).build();
		if(message == null){
			throw new WebApplicationException(response);			
		}
		Map<Long,Comment> comments = message.getComments();
		if(comments == null){
			throw new WebApplicationException(response);
		}
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0)
			return null;
		else{
			comments.put(comment.getId(), comment);
			return comment;
		}
		
	}
	
	public void removeComment(long messageId, long commentId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comments.remove(commentId);
	}
	
	

}
