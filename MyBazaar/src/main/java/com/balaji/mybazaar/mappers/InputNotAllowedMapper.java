package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.balaji.mybazaar.exception.ExceptionMessage;
import com.balaji.mybazaar.exception.InputNotAllowed;

@Provider
public class InputNotAllowedMapper implements ExceptionMapper<InputNotAllowed> {

	@Override
	public Response toResponse(InputNotAllowed exception) {
		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("INPUT NOT ALLOWED");
		exMessage.setStatusCode(400);
		exMessage.setDescription("Sorry! We cannot give any result for the given wrong input type");
		
		return Response.status(Status.BAD_REQUEST).entity(exMessage).type(MediaType.APPLICATION_JSON).build();
	}

}
