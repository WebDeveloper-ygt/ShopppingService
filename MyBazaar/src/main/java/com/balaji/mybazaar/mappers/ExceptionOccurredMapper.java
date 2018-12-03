package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.balaji.mybazaar.exception.ExceptionMessage;
import com.balaji.mybazaar.exception.ExceptionOccurred;

@Provider
public class ExceptionOccurredMapper implements ExceptionMapper<ExceptionOccurred> {

	@Override
	public Response toResponse(ExceptionOccurred exception) {
		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("INTERNAL SERVER ERROR");
		exMessage.setStatusCode(500);
		exMessage.setDescription("Sorry! We are facing some technical issues");
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exMessage).build();
	}

}
