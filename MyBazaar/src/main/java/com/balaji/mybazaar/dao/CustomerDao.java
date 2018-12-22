package com.balaji.mybazaar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.ExceptionOccurred;
import com.balaji.mybazaar.main.DbConnection;
import com.balaji.mybazaar.model.CustomerBean;
import com.balaji.mybazaar.utils.BazaarUtils;

public class CustomerDao {

	Logger LOGGER = Logger.getLogger(CustomerDao.class);
	List<CustomerBean> custList;
	DbConnection dbConn;
	Connection conn;
	String customers;
	private static String Invoked = "Invoked CustomerDao ";
	
	public CustomerDao() throws SQLException {
		dbConn = new DbConnection();
		conn = dbConn.getDbConnection();
		custList = new ArrayList<>();
	}

	public Response getAllCustomers() throws SQLException {
		customers = BazaarUtils.CUSTOMER;
		LOGGER.info(Invoked);
		LOGGER.info("Fetaching Details for all the customers");
		return this.getCustomersCommon(customers);
	}

	public Response getCustomersCommon(String cust) throws SQLException {
		try {
		PreparedStatement pst = conn.prepareStatement(cust);
		ResultSet resSet = pst.executeQuery();
		while (resSet.next()) {
			CustomerBean custBean =new CustomerBean();
			custBean.setFirstName(resSet.getString(1));
			custBean.setLastName(resSet.getString(2));
			custBean.setEmailId(resSet.getString(3));
			custBean.setMobileNumber(resSet.getString(4));
			custBean.setCustomerId(resSet.getInt(5));
			custBean.setTotalOrders(resSet.getInt(6));
			
			custList.add(custBean);
		}
		
		if(custList.size() > 0) {
			LOGGER.info("Fetching Customer details is successful");
			return Response.status(Status.OK).entity(new GenericEntity<List<CustomerBean>>(custList) {}).build();
		}else {
			LOGGER.info("Fetching Customer details is Failed");
			return Response.status(Status.NOT_FOUND).entity(new GenericEntity<List<CustomerBean>>(custList) {}).build();
		}
		}catch (Exception Ex) {
			LOGGER.error(Ex.getMessage());
			throw new ExceptionOccurred();
		}
	}

	public Response getCustomersByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		customers = BazaarUtils.CUSTOMER + " where firstName = '"+name+ "' or firstName like '%" + name +"%' collate utf8_general_ci" ;
		LOGGER.info(Invoked);
		LOGGER.info("Fetaching Details for customer search string: "+ name);
		return this.getCustomersCommon(customers);
	}

	public Response getCustomersByName(int custId) throws SQLException {
		customers = BazaarUtils.CUSTOMER_ID + custId;
		LOGGER.info(Invoked);
		LOGGER.info("Fetaching Details for customer search ID: "+ custId);		
		return this.getCustomersCommon(customers);
	}

	public Response addCustomer(CustomerBean custbean) throws SQLException {
		try {
		String addUserSql = "INSERT INTO `bazaar`.`customer`(`firstName`,`lastName`,`emailId`,`phoneNumber`) VALUES(?,?,?,?)";
		
		PreparedStatement pst = conn.prepareStatement(addUserSql);
		pst.setString(1, custbean.getFirstName());
		pst.setString(2, custbean.getLastName());
		pst.setString(3, custbean.getEmailId());
		pst.setString(4, custbean.getMobileNumber());
		boolean result = pst.execute();
		if(result == false) {
			customers = BazaarUtils.CUSTOMER_NUM + custbean.getMobileNumber();
			return this.getCustomersCommon(customers);
		}else {
			return Response.status(Status.BAD_REQUEST).build();
		}
		}catch(Exception ex) {
			throw new ExceptionOccurred();
		}
		
	}
	
}
