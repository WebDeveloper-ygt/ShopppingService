package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import com.balaji.mybazaar.dao.OrdersDao;

public class OrdersService {

	OrdersDao ordDao;
	public OrdersService() throws SQLException {
		ordDao = new OrdersDao();
	}

	public Response getAllOrders() throws SQLException {
		// TODO Auto-generated method stub
		return ordDao.getAllOrders();
	}

	public Response getOrderByOrderId(int orderID) throws SQLException {
		// TODO Auto-generated method stub
		return ordDao.getOrderByOrderId(orderID);
	}

	public Response getOrderByCustomerId(int custId) throws SQLException {
		// TODO Auto-generated method stub
		return ordDao.getOrderByCustomerId(custId);
	}

}
