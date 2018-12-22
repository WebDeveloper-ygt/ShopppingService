package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.balaji.mybazaar.exception.CustomNullPointerException;
import com.balaji.mybazaar.exception.ExceptionMessage;

@Provider
public class CustomNullPointerExceptionMapper implements ExceptionMapper<CustomNullPointerException> {

	@Override
	public Response toResponse(CustomNullPointerException exception) {
		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("NULL POINTER EXCEPTION");
		exMessage.setStatusCode(400);
		exMessage.setDescription("Sorry! Input cannot be empty");
		
		return Response.status(Status.BAD_REQUEST).entity(exMessage).type(MediaType.APPLICATION_JSON).build();
	
	}

}
