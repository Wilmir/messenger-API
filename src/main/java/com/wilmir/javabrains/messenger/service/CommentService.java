package com.wilmir.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.wilmir.javabrains.messenger.database.DatabaseClass;
import com.wilmir.javabrains.messenger.model.Comment;
import com.wilmir.javabrains.messenger.model.ErrorMessage;
import com.wilmir.javabrains.messenger.model.Message;

public class CommentService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();

	
	public List<Comment> getComments(long messageId){
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
		
		ErrorMessage errorMessage = new ErrorMessage("Not Found",404,"https://wilmir.com");
		Response response = Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(errorMessage)
						.build();
		
		
		Message message = messages.get(messageId);
		if(message == null)
			throw new WebApplicationException(response);
		
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		
		Comment comment  = comments.get(commentId);
		if(comment == null)
			throw new WebApplicationException(response);
		
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		
		comments.put(comment.getId(),comment);
		
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0) {
			return null;
		}
		
		comments.put(comment.getId(),comment);
		
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long,Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
	
	
	
}
