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

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.main.DbConnection;
import com.balaji.mybazaar.model.OrderItemBean;
import com.balaji.mybazaar.model.OrdersBean;
import com.balaji.mybazaar.utils.BasicUtils;
import com.balaji.mybazaar.utils.BazaarUtils;

public class OrdersDao {

	Logger LOGGER = Logger.getLogger(OrdersDao.class); 
	private static String Invoked = "Invoked OrdersDao";
	
	List<OrdersBean> orderList;
	DbConnection dbConn;
	Connection conn;
	String orders;
	public OrdersDao() throws SQLException {
		dbConn = new DbConnection();
		conn = dbConn.getDbConnection();
		orderList = new ArrayList<>();
	}
	public Response getAllOrders() throws SQLException {
		LOGGER.info(Invoked);
		orders = BazaarUtils.ORDERS;
		return this.getOrdersCommon(orders);
	}
	private Response getOrdersCommon(String orders2) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(orders2);
		ResultSet resSet = pst.executeQuery();
		boolean checker = false;
		while(resSet.next()) {
			checker = true;
			OrdersBean ordBean = new OrdersBean();
			ordBean.setOrderId(resSet.getInt(1));
			ordBean.setCustomerId(resSet.getInt(2));
			String jsontest = resSet.getString(3);
			//LOGGER.info("Items Details for the customer order :"+ jsontest);
			List<OrderItemBean> itemsBean = BasicUtils.StringToListOrderItemBean(jsontest);
			ordBean.setItems(itemsBean);
			orderList.add(ordBean);
		}
		if(checker == true) {
			return Response.status(Status.OK).entity(new GenericEntity<List<OrdersBean>>(orderList) {}).build();
		}else {
			throw new CustomNotFoundException();
		}
	}
	public Response getOrderByOrderId(int orderID) throws SQLException {
		// TODO Auto-generated method stub
		orders = BazaarUtils.ORDERS_ID + orderID;
		LOGGER.info("Fetching order details for orderid : "+ orderID);
		return this.getOrdersCommon(orders);
	}
	public Response getOrderByCustomerId(int custId) throws SQLException {
		orders = BazaarUtils.ORDERS_CUST_ID+ custId;
		LOGGER.info(Invoked);
		LOGGER.info("Fetching order details for the customer id : "+ custId);
		return this.getOrdersCommon(orders);
	}

}
