package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

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



	public Response getItemsByshopId(int shopId) throws SQLException {
		// TODO Auto-generated method stub
		return itemsDao.getItemsByshopId(shopId);
	}



	public Response getAllItemsByName(String itName) throws SQLException {
		// TODO Auto-generated method stub  
		 return itemsDao.getAllItemsByName(itName);
	}

}
