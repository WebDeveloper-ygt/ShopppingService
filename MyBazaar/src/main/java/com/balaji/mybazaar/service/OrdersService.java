package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.dao.OrdersDao;

public class OrdersService {

	Logger LOGGER = Logger.getLogger(OrdersService.class);
	OrdersDao ordDao;
	private static String Invoked = "Invoked OrdersService";
	public OrdersService() throws SQLException {
		ordDao = new OrdersDao();
	}

	public Response getAllOrders() throws SQLException {
		// TODO Auto-generated method stub
		LOGGER.info(Invoked);
		return ordDao.getAllOrders();
	}

	public Response getOrderByOrderId(int orderID) throws SQLException {
		LOGGER.info(Invoked);
		return ordDao.getOrderByOrderId(orderID);
	}

	public Response getOrderByCustomerId(int custId) throws SQLException {
		LOGGER.info(Invoked);		
		return ordDao.getOrderByCustomerId(custId);
	}

}
