package com.balaji.mybazaar.utils;

public class BazaarUtils {

	public static final String SHOPS="select * from st_shops";
	public static final String SHOPS_ID="select * from st_shops where shopId = ";
	public static final String ITEMS="select * from sh_items";
	public static final String ITEMS_ID="select * from sh_items where shopId = ";
	public static final String CUSTOMER = "select * from customer";
	public static final String CUSTOMER_ID = "select * from customer where customerId = ";
	public static final String CUSTOMER_EMAIL= "select * from bazaar.customer where emailId = ";
	public static final String CUSTOMER_NUM= "select * from bazaar.customer where phoneNumber = ";
	public static final String ORDERS =" select * from  orders";
	public static final String ORDERS_ID = "select * from bazaar.orders where orderId= ";
	public static final String ORDERS_CUST_ID = "select * from bazaar.orders where customerId= ";

}
