package com.wilmir.javabrains.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.wilmir.javabrains.messenger.model.Message;
import com.wilmir.javabrains.messenger.resources.beans.MessageFilterBean;
import com.wilmir.javabrains.messenger.service.MessageService;

@Path("/messages")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class MessageResource {

	
	MessageService messageService = new MessageService();
	

	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesPerYear(filterBean.getYear());
		}
		
		if(filterBean.getStart()>=0 && filterBean.getSize()>0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		
		return messageService.getAllMessages();

	}
	

	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message) throws URISyntaxException {
		messageService.addMessage(message);
		
		String id = String.valueOf(message.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();

		return Response.created(uri)
						.entity(message)
						.build();
	}	

	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {		
		Message message = messageService.getMessage(id);
		message.addLink(getUriforSelf(uriInfo, message), "self");
		message.addLink(getUriforProfile(uriInfo, message), "profile");
		message.addLink(getUriforComment(uriInfo, message), "comments");

		return message;
	
	}


	private String getUriforComment(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId",message.getId())
				.build()
				.toString();
		return uri;
	}


	private String getUriforProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				   .path(ProfileResource.class)
				   .path(message.getAuthor())
				   .build()
				   .toString();
			
			return uri;
		}
	


	private String getUriforSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
			   .path(MessageResource.class)
			   .path(Long.toString(message.getId()))
			   .build()
			   .toString();
		
		return uri;
	}
	

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
		
	}
	
	

	
	
	
}
