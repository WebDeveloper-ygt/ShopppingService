package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.CustomerNotCreated;
import com.balaji.mybazaar.model.CustomerBean;
import com.balaji.mybazaar.service.CustomerService;
import com.balaji.mybazaar.utils.BasicUtils;

@Path("/customers")
public class CustomerResource {

	Logger log = Logger.getLogger(CustomerResource.class);

	CustomerService custService;

	public CustomerResource() throws SQLException {
		custService = new CustomerService();
		log.info("Invoked Customer Resource");
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllCustomers(@Context UriInfo uriInfo) throws SQLException {
		Response allCustomers = custService.getAllCustomers();
		if (allCustomers.getStatus() != 200) {
			throw new CustomNotFoundException(uriInfo);
		}
		return allCustomers;
	}

	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCustomersByName(@QueryParam("Name") String name,@Context UriInfo uriInfo) throws SQLException {
		log.info("Requested userInfo Name: " + name);
		Response customerResult = custService.getCustomersByName(name);
		if(customerResult.getStatus() != 200) {
			throw new CustomNotFoundException(uriInfo);
		}
		return customerResult;
	}

	@GET
	@Path("{custId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCustomerById(@PathParam("custId") int custId, @Context UriInfo uriInfo) throws SQLException {

		log.info("Requested userInfo id: " + custId);
		Response customerResult = custService.getCustomersById(custId);
		if (customerResult.getStatus() != 200) {
			throw new CustomNotFoundException(uriInfo);
		}
		return customerResult;
	}

	@Path("{custId}/orders")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public CustomerOrdersResource getCustomerOrders(@PathParam("custId") int custId) throws SQLException {
		return new CustomerOrdersResource(custId);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addCustomer(CustomerBean custbean) throws SQLException {
		boolean valid = BasicUtils.validateCustomerInput(custbean);
		if(valid == true) {
			Response addCustomer = custService.addCustomer(custbean);
			if(addCustomer.getStatus() != 200) {
				throw new CustomerNotCreated();
			}
			return  addCustomer;
		}else {
			throw new CustomerNotCreated();
		}
		

	}
}