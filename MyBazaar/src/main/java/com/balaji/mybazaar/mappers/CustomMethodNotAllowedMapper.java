package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.balaji.mybazaar.exception.CustomMethodNotAllowed;
import com.balaji.mybazaar.exception.ExceptionMessage;

@Provider
public class CustomMethodNotAllowedMapper implements ExceptionMapper<CustomMethodNotAllowed> {

	@Override
	public Response toResponse(CustomMethodNotAllowed exception) {
		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("METHOD NOT ALLOWED");
		exMessage.setStatusCode(405);
		exMessage.setDescription("Sorry! Method is not allowed. Please check the method used or URI.");
		
		return Response.status(Status.METHOD_NOT_ALLOWED).entity(exMessage).type(MediaType.APPLICATION_JSON).build();
	}

}
