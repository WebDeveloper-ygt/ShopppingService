package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.mybazaar.service.CustomerService;

@Path("/customers")
public class CustomerResource {

	CustomerService custService;
	
	public CustomerResource() throws SQLException {
		custService = new CustomerService();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllCustomers() throws SQLException {
		return custService.getAllCustomers();
	}
	
	@GET
	@Path("/name")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCustomersByName(@QueryParam("Name") String name) throws SQLException {
		
		return custService.getCustomersByName(name);
	}
	
	@GET
	@Path("{custId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getCustomerById(@PathParam("custId") int custId) throws SQLException {
		return custService.getCustomersById(custId);
	}
	
	
	@Path("{custId}/orders")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public CustomerOrdersResource getCustomerOrders(@PathParam("custId") int custId) throws SQLException {
		return new CustomerOrdersResource(custId);
	}
}