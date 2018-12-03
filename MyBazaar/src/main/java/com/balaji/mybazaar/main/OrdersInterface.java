package com.balaji.mybazaar.main;

import javax.ws.rs.core.Response;

public interface OrdersInterface {

	public Response getorders(int custId);
	public Response getAllOrders();
	
}
