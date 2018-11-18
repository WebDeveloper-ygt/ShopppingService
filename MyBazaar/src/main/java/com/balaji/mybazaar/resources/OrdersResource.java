package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.mybazaar.service.OrdersService;

@Path("/orders")
public class OrdersResource {

	OrdersService ordServ ;
	public OrdersResource() throws SQLException {
		ordServ = new OrdersService();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrders() throws SQLException {
		
		return ordServ.getAllOrders();
	}
	
	@GET
	@Path("{orderId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByOrderId(@PathParam("orderId") int ordId) throws SQLException {
		return ordServ.getOrderByOrderId(ordId);
	}
	
	
}
