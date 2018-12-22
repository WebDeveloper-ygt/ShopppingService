package com.balaji.mybazaar.service;

import java.sql.SQLException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.dao.CustomerDao;
import com.balaji.mybazaar.model.CustomerBean;

public class CustomerService {

	Logger LOGGER = Logger.getLogger(CustomerService.class);
	private static String Invoked="Invoked Customer Service";
	CustomerDao custDao;
	
	public CustomerService() throws SQLException {
		custDao = new CustomerDao();
	}

	public Response getAllCustomers() throws SQLException {
		LOGGER.info(Invoked);
		return custDao.getAllCustomers();
	}

	public Response getCustomersByName(String name) throws SQLException {
		LOGGER.info(Invoked);
		return custDao.getCustomersByName(name);
	}

	public Response getCustomersById(int custId) throws SQLException {
		LOGGER.info(Invoked);
		return custDao.getCustomersByName(custId);
	}

	public Response addCustomer(CustomerBean custbean) throws SQLException {
		LOGGER.info(Invoked);
		return custDao.addCustomer(custbean);
	}

	

	
}
