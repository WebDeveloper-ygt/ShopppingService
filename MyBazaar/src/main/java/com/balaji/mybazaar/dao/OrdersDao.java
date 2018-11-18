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
import com.balaji.mybazaar.model.OrderItemBean;
import com.balaji.mybazaar.model.OrdersBean;
import com.balaji.mybazaar.utils.BasicUtils;
import com.balaji.mybazaar.utils.BazaarUtils;

public class OrdersDao {

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
		// TODO Auto-generated method stub
		orders = BazaarUtils.ORDERS;
		return this.getOrdersCommon(orders);
	}
	private Response getOrdersCommon(String orders2) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = conn.prepareStatement(orders2);
		ResultSet resSet = pst.executeQuery();
		while(resSet.next()) {
			OrdersBean ordBean = new OrdersBean();
			ordBean.setOrderId(resSet.getInt(1));
			ordBean.setCustomerId(resSet.getInt(2));
			String jsontest = resSet.getString(3);
			System.out.println("String from db : "+ jsontest);
			List<OrderItemBean> itemsBean = BasicUtils.StringToListOrderItemBean(jsontest);
			System.out.println(itemsBean);
			ordBean.setItems(itemsBean);
			orderList.add(ordBean);
		}
		return Response.status(Status.OK).entity(new GenericEntity<List<OrdersBean>>(orderList) {}).build();
	}
	public Response getOrderByOrderId(int orderID) throws SQLException {
		// TODO Auto-generated method stub
		orders = BazaarUtils.ORDERS_ID + orderID;
		System.out.println(orders);
		return this.getOrdersCommon(orders);
	}
	public Response getOrderByCustomerId(int custId) throws SQLException {
		// TODO Auto-generated method stub
		orders = BazaarUtils.ORDERS_CUST_ID+ custId;
		System.out.println(orders);
		return this.getOrdersCommon(orders);
	}

}
