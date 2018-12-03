package com.balaji.mybazaar.main;

import javax.ws.rs.core.Response;

import com.balaji.mybazaar.model.ShopItemsBean;

public interface ShopInterface {

	public Response addShopItems(ShopItemsBean shItemBean,int shopId);
	public Response getShop(String shopName);
	public Response getShop(int shopId);
	public Response getAllShops();
}
