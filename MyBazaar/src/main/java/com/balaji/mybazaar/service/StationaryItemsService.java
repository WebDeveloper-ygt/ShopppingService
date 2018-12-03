package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.balaji.mybazaar.dao.StationaryItemsDao;

public class StationaryItemsService {

	StationaryItemsDao itemsDao;

	public StationaryItemsService() throws SQLException {
		itemsDao = new StationaryItemsDao();
	}

	

	public Response getAllItems() throws SQLException {
		// TODO Auto-generated method stub
		return itemsDao.getAllItems();
	}



	public Response getItemsByshopId(int shopId, UriInfo uriInfo) throws SQLException {
		// TODO Auto-generated method stub
		return itemsDao.getItemsByshopId(shopId,uriInfo);
	}



	public Response getAllItemsByName(String itName) throws SQLException {
		// TODO Auto-generated method stub  
		 return itemsDao.getAllItemsByName(itName);
	}



	public String getOnlyItemsByshopId(int shopId, UriInfo uriInfo) throws SQLException {
		// TODO Auto-generated method stub
		return itemsDao.getOnlyItemsByshopId(shopId,uriInfo);
	}



	public Response addShopItems(int shopId, String updatedItems, UriInfo uriInfo) throws SQLException {
		// TODO Auto-generated method stub
		return itemsDao.addShopItems(shopId,updatedItems,uriInfo);
	}

}
