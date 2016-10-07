package org.raj.learn.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.raj.learn.messenger.model.Message;
import org.raj.learn.messenger.resources.bean.MessageFilterBean;
import org.raj.learn.messenger.services.MessageService;

@Path("/bean")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeanParamDemo {
	MessageService messageService = new MessageService();
	@GET
	public List<Message> getParametersUsingBean(@BeanParam MessageFilterBean filterbean){
		if(filterbean.getYear() > 0){
			return messageService.getAllMessageForYear(filterbean.getYear());
		}
		if(filterbean.getStart() >= 0 && filterbean.getSize() >0){
			return messageService.getAllMessageBySize(filterbean.getStart(), filterbean.getSize());
		}
		return messageService.getAllMessages();
		
	}
}
