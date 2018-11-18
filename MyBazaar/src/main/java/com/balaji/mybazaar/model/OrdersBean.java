package com.balaji.mybazaar.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Orders")
public class OrdersBean {

	private int orderId;
	private int customerId;
	private List<OrderItemBean> items;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public List<OrderItemBean> getItems() {
		return items;
	}
	public void setItems(List<OrderItemBean> items) {
		this.items = items;
	}
	
	
	
	
	
}
