package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.ExceptionOccurred;
import com.balaji.mybazaar.exception.InputNotAllowed;
import com.balaji.mybazaar.service.OrdersService;
import com.balaji.mybazaar.utils.BasicUtils;

@Path("/orders")
public class OrdersResource {

	Logger LOGGER = Logger.getLogger(OrdersResource.class);
	private static String Invoked = "Invoked OrdersRespource";
	OrdersService ordServ ;
	public OrdersResource() throws SQLException {
		ordServ = new OrdersService();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrders() throws SQLException {
		LOGGER.info(Invoked);
		Response orders =  ordServ.getAllOrders();
		if(orders.getStatus() != 200) {
			throw new ExceptionOccurred();
		}else {
			return orders;
		}
	}
	
	@GET
	@Path("{orderId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByOrderId(@PathParam("orderId") String ordId) throws SQLException {
		LOGGER.info(Invoked);
		if(BasicUtils.validateParamInput(ordId) == true) {
			LOGGER.info("User input order id validation successful");
			Response orders = ordServ.getOrderByOrderId(Integer.parseInt(ordId));
			if (orders.getStatus() != 200) {
				LOGGER.info("Response Status : "+ orders.getStatus());
				throw new CustomNotFoundException();
			}else {
				return orders;
			}
		}else {
			LOGGER.info("User input order id validation failed and Input value is : "+ ordId);
			throw new InputNotAllowed();
		}
	}
	
	
}
