package com.balaji.mybazaar.main;

import javax.ws.rs.core.Response;

import com.balaji.mybazaar.model.CustomerBean;

public interface CustomerInterface {

	public Response addCustomer(CustomerBean custBean);
	public Response getCustomer(String name);
	public Response getCustomer(int custId);
	public Response getAllCustomer();
	
	public Response getCustomerOrders(int custId);
	public Response getCustomerOrdersByorderId(int custId, int orderId);
	
}

