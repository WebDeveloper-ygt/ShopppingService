package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import com.balaji.mybazaar.dao.CustomerDao;

public class CustomerService {

	CustomerDao custDao;
	
	public CustomerService() throws SQLException {
		custDao = new CustomerDao();
	}

	public Response getAllCustomers() throws SQLException {
		// TODO Auto-generated method stub
		return custDao.getAllCustomers();
	}

	public Response getCustomersByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return custDao.getCustomersByName(name);
	}

	public Response getCustomersById(int custId) throws SQLException {
		// TODO Auto-generated method stub
		return custDao.getCustomersByName(custId);
	}

	

	
}
