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

import com.balaji.mybazaar.main.DbConnection;
import com.balaji.mybazaar.model.CustomerBean;
import com.balaji.mybazaar.utils.BazaarUtils;

public class CustomerDao {

	
	List<CustomerBean> custList;
	DbConnection dbConn;
	Connection conn;
	String customers;
	public CustomerDao() throws SQLException {
		dbConn = new DbConnection();
		conn = dbConn.getDbConnection();
		custList = new ArrayList<>();
	}

	public Response getAllCustomers() throws SQLException {
		// TODO Auto-generated method stub
		
		customers = BazaarUtils.CUSTOMER;
		return this.getCustomersCommon(customers);
	}

	public Response getCustomersCommon(String cust) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(cust);
		ResultSet resSet = pst.executeQuery();
		System.out.println(resSet);
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
		for (CustomerBean customerBean : custList) {
			System.out.println(customerBean.getFirstName());
		}
		
		return Response.status(Status.OK).entity(new GenericEntity<List<CustomerBean>>(custList) {}).build();
	}

	public Response getCustomersByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		customers = BazaarUtils.CUSTOMER + " where firstName = '"+name+ "' or firstName like '%" + name +"%' collate utf8_general_ci" ;
		return this.getCustomersCommon(customers);
	}

	public Response getCustomersByName(int custId) throws SQLException {
		// TODO Auto-generated method stub
		customers = BazaarUtils.CUSTOMER_ID + custId;
		return this.getCustomersCommon(customers);
	}
	
}
