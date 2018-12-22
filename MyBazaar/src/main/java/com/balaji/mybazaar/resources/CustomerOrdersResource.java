package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomMethodNotAllowed;
import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.InputNotAllowed;
import com.balaji.mybazaar.service.OrdersService;
import com.balaji.mybazaar.utils.BasicUtils;

public class CustomerOrdersResource {

	Logger LOGGER = Logger.getLogger(CustomerOrdersResource.class);
	OrdersService ordServ ;
	int customerId;
	private static String Invoked = "Invoked CustomerOrdersResource";
	public CustomerOrdersResource() throws SQLException {
		
	}
	
	public CustomerOrdersResource(int custId) throws SQLException {
		ordServ = new OrdersService();
		this.customerId = custId;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByOrderId() throws SQLException {
		LOGGER.info(Invoked);
		Response customerName = ordServ.getOrderByCustomerId(customerId);
		LOGGER.info("Result "+ customerName.bufferEntity());
		if(customerName.getStatus() !=200) {
			throw new CustomNotFoundException();
		}
		return customerName;
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
	public Response getOrderByOrderId(@PathParam("orderID") String orderID) throws SQLException {
		LOGGER.info(Invoked);
		if(BasicUtils.validateParamInput(orderID) == true) {
			LOGGER.info("User input Order Id validation successful");
			Response shopById = ordServ.getOrderByOrderId(Integer.parseInt(orderID));
			if(shopById.getStatus() != 200) {
				LOGGER.info("Response Status : "+ shopById.getStatus());
				throw new CustomNotFoundException();
			}
			
			return shopById;
		}else {
			LOGGER.info("User input Order Id validation failed and Input value is : "+ orderID);
			throw new InputNotAllowed();
		}
	
	}
	
	/*@GET
	@Path("/customer/{customerId}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getOrderByCustomerId(@PathParam("customerId") int custId) throws SQLException {
		
		return ordServ.getOrderByCustomerId(custId);
	}*/
}
