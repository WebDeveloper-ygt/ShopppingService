package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomMethodNotAllowed;
import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.ExceptionOccurred;
import com.balaji.mybazaar.exception.InputNotAllowed;
import com.balaji.mybazaar.exception.ResourceNotCreated;
import com.balaji.mybazaar.model.CustomerBean;
import com.balaji.mybazaar.service.CustomerService;
import com.balaji.mybazaar.utils.BasicUtils;

@Path("/customers")
public class CustomerResource {

	Logger LOGGER = Logger.getLogger(CustomerResource.class);
	private static String Invoked="Invoked Customer Resource";
	CustomerService custService;

	public CustomerResource() throws SQLException {
		custService = new CustomerService();
		
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllCustomers(@Context UriInfo uriInfo) throws SQLException {
		LOGGER.info(Invoked);
		Response allCustomers = custService.getAllCustomers();
		if (allCustomers.getStatus() != 200) {
			LOGGER.info("Response Status : "+ allCustomers.getStatus());
			throw new ExceptionOccurred();
		}
		return allCustomers;
	}

	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCustomersByName(@QueryParam("Name") String name, @Context UriInfo uriInfo) throws SQLException {
		LOGGER.info(Invoked);
		if(BasicUtils.validateParamInput(name) == false) {
			LOGGER.info("User input name validation successful");
			Response customerResult = custService.getCustomersByName(name);
			if (customerResult.getStatus() != 200) {
				LOGGER.info("Response Status : "+ customerResult.getStatus());
				throw new CustomNotFoundException();
			}
			return customerResult;
		}else {
			LOGGER.info("User input name validation failed and Input value is : "+ name);
			throw new InputNotAllowed();
		}
	}
		
	
	
	@GET
	@Path("{custId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCustomerById(@PathParam("custId") String custId, @Context UriInfo uriInfo) throws SQLException, InputNotAllowed {
		LOGGER.info(Invoked);
		if(BasicUtils.validateParamInput(custId) == true) {
			LOGGER.info("User input id validation successful");
			Response customerResult = custService.getCustomersById(Integer.parseInt(custId));
			if (customerResult.getStatus() != 200) {
				LOGGER.info("Response Status : "+ customerResult.getStatus());
				throw new CustomNotFoundException();
			}
			return customerResult;
		}else {
			LOGGER.info("User input id validation failed and Input value is : "+ custId);
			throw new InputNotAllowed();
		}
		
	}

	@Path("{custId}/orders")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public CustomerOrdersResource getCustomerOrders(@PathParam("custId") String custId) throws SQLException {
		LOGGER.info(Invoked);
		if(BasicUtils.validateParamInput(custId) == true) {
			LOGGER.info("User input id validation successful");
			return new CustomerOrdersResource(Integer.parseInt(custId));
		}else {
			LOGGER.info("User input id validation failed and Input value is : "+ custId);			
			throw new InputNotAllowed();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addCustomer(CustomerBean custbean) throws SQLException {
		LOGGER.info(Invoked);
		if (BasicUtils.validateCustomerInput(custbean) == true) {
			LOGGER.info("Input resource validation for the customer creation filed : either of the below inputs should not be empty");
			LOGGER.info("Input details are :\n EmailId :"+custbean.getEmailId()+ " \n MobileNumber : "+ custbean.getMobileNumber()+"\n FirstName : "+ custbean.getFirstName() + "\n Last Name : "+ custbean.getLastName());
			throw new ResourceNotCreated();
		} else {
			LOGGER.info("Creating Customer for the input : "+ custbean);
			Response addCustomer = custService.addCustomer(custbean);
			if (addCustomer.getStatus() != 200) {
				throw new ResourceNotCreated();
			}
			return addCustomer;
		}

	}

	/* Not allowed methods */
	@DELETE
	public Response getAllCustomers2() {
		throw new CustomMethodNotAllowed();
	}

	@OPTIONS
	public Response getAllCustomers3() {
		throw new CustomMethodNotAllowed();
	}

	@PUT
	public Response getAllCustomers4() {
		throw new CustomMethodNotAllowed();
	}

	@HEAD
	public Response getAllCustomers5() {
		throw new CustomMethodNotAllowed();
	}

}