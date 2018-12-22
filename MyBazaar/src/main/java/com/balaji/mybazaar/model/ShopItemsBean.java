package com.balaji.mybazaar.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShopItemsBean {
	private int shopId;
	private List<ItemsBean> itemsBean;
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	@XmlElement(name="items")
	public List<ItemsBean> getItemsBean() {
		return itemsBean;
	}
	public void setItemsBean(List<ItemsBean> itemsBean) {
		this.itemsBean = itemsBean;
	}
	
	
}
