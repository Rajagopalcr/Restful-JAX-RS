package org.raj.learn.messenger.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.raj.learn.messenger.model.Message;
import org.raj.learn.messenger.services.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.TEXT_XML}) // In request header Accept = application/json or text/xml
@Consumes(MediaType.APPLICATION_JSON) // Content-Type = application/json or text/xml
// Produces ---- Accept (Request header)
// Consumes ----- ContentType (Request header)
public class MessageResource {
	MessageService messageService = new MessageService();
	
	@GET	
	public List<Message> getMessages(@QueryParam("year") int year, // Filtering: filter for year
									 @QueryParam("start") int start,// Pagination start: from where to start
									 @QueryParam("size") int size,// Pagination size: how many record to get
									 @Context UriInfo urInfo){ 	
		if(year > 0){
			return getSelfUriForAllMessage(urInfo,messageService.getAllMessageForYear(year));
		}
		if(start >= 0 && size >0){
			return getSelfUriForAllMessage(urInfo,messageService.getAllMessageBySize(start, size));
		}
		return getSelfUriForAllMessage(urInfo,messageService.getAllMessages());
	}
	
	@GET	
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo urInfo){
		Message message = messageService.getMessage(messageId);		
		message.addLink(getUriForSelf(urInfo,message),"self");
		message.addLink(getUriForComment(urInfo,message),"self");
		return messageService.getMessage(messageId);		
	}
	
	@POST	
	public Response addMessage(Message message, @Context UriInfo uriInfo){		
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build();
		return Response.created(uri).entity(newMessage).build();
	}
	
	@PUT	
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		return messageService.updateMessage(message);
	}
	
	@DELETE	
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
	private String getUriForSelf(UriInfo urInfo,Message message){
		String url = urInfo.getBaseUriBuilder()
				  .path(MessageResource.class)
				  .path(Long.toString(message.getId()))
				  .build()
				  .toString();
		
		//String newUrl = urInfo.getAbsolutePath().toString(); 
		//Can be use like this, but nor apply for subresources like commentResources
		return url;
	}
	
	private String getUriForComment(UriInfo urInfo,Message message){
		String uri = urInfo.getBaseUriBuilder()
				  .path(MessageResource.class)
				  .path(MessageResource.class,"getCommentResource")
				  .path(CommentResource.class)
				  .resolveTemplate("messageId", message.getId())
				  .build()
				  .toString();			
		return uri;
	}
	
	private List<Message> getSelfUriForAllMessage(UriInfo urInfo,List<Message> list){
		List<Message> retVal = new ArrayList<>();		
		for(int i=0;i<list.size();i++){
			Message message = (Message)list.get(i);
			message.addLink(getUriForSelf(urInfo,message),"self");
			message.addLink(getUriForComment(urInfo,message),"self");
			retVal.add(message);
		}
		return retVal;
	}
	
	
	
}
