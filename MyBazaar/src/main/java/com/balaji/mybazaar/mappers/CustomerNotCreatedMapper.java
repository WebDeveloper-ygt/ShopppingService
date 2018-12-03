package com.balaji.mybazaar.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.balaji.mybazaar.exception.CustomerNotCreated;
import com.balaji.mybazaar.exception.ExceptionMessage;

public class CustomerNotCreatedMapper implements ExceptionMapper<CustomerNotCreated> {

	@Override
	public Response toResponse(CustomerNotCreated exception) {

		ExceptionMessage exMessage = new ExceptionMessage();
		exMessage.setMessage("NO CONTENT");
		exMessage.setStatusCode(204);
		exMessage.setDescription("Sorry! We cannot created the customer for the given input");
		
		return Response.status(Status.NO_CONTENT).entity(exMessage).build();
	}

}
