package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.balaji.mybazaar.exception.ExceptionMessage;
import com.balaji.mybazaar.exception.ResourceNotCreated;

@Provider
public class ResourceNotCreatedMappers implements ExceptionMapper<ResourceNotCreated>{

	@Override
	public Response toResponse(ResourceNotCreated exception) {
		// TODO Auto-generated method stub
		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("Resource Not Created");
		exMessage.setStatusCode(400);
		exMessage.setDescription("Input validation failed against the schema");
		
		return Response.status(Status.BAD_REQUEST).entity(exMessage).build();
	}

}