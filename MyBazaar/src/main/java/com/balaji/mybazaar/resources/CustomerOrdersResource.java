package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.mybazaar.service.OrdersService;


public class CustomerOrdersResource {

	
	OrdersService ordServ ;
	int customerId;
	public CustomerOrdersResource() throws SQLException {
		
	}
	
	public CustomerOrdersResource(int custId) throws SQLException {
		ordServ = new OrdersService();
		this.customerId = custId;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByOrderId() throws SQLException {
		
		return ordServ.getOrderByCustomerId(customerId);
	}
	
	/*@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllOrders() throws SQLException {
		
		return ordServ.getAllOrders();
	}
	*/
	@GET
	@Path("{orderID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByOrderId(@PathParam("orderID") int orderID) throws SQLException {
		
		return ordServ.getOrderByOrderId(orderID);
	}
	
	/*@GET
	@Path("/customer/{customerId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByCustomerId(@PathParam("customerId") int custId) throws SQLException {
		
		return ordServ.getOrderByCustomerId(custId);
	}*/
}
