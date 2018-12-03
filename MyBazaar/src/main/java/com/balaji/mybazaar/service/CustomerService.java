package com.balaji.mybazaar.service;

import java.sql.SQLException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.dao.CustomerDao;
import com.balaji.mybazaar.model.CustomerBean;

public class CustomerService {

	Logger log = Logger.getLogger(CustomerService.class);
	CustomerDao custDao;
	
	public CustomerService() throws SQLException {
		custDao = new CustomerDao();
		log.info("Invoked CustomerService");
	}

	public Response getAllCustomers() throws SQLException {
		return custDao.getAllCustomers();
	}

	public Response getCustomersByName(String name) throws SQLException {
		return custDao.getCustomersByName(name);
	}

	public Response getCustomersById(int custId) throws SQLException {
		return custDao.getCustomersByName(custId);
	}

	public Response addCustomer(CustomerBean custbean) throws SQLException {
		return custDao.addCustomer(custbean);
	}

	

	
}
