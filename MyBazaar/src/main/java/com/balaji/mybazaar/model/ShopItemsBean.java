package com.balaji.mybazaar.model;

import java.util.List;

public class ShopItemsBean {
	private int shopId;
	private List<ItemsBean> itemsBean;
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public List<ItemsBean> getItemsBean() {
		return itemsBean;
	}
	public void setItemsBean(List<ItemsBean> itemsBean) {
		this.itemsBean = itemsBean;
	}
	
	
}
