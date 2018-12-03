package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.ExceptionMessage;

@Provider
public class CustomNotFoundExceptionMappers implements ExceptionMapper<CustomNotFoundException>{

	@Override
	public Response toResponse(CustomNotFoundException exception) {
		// TODO Auto-generated method stub
		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("NOT FOUND");
		exMessage.setStatusCode(404);
		exMessage.setDescription("Sorry! Resource not found.");
		
		return Response.status(Status.NOT_FOUND).entity(exMessage).build();
	}

}
