package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.dao.StationaryItemsDao;

public class StationaryItemsService {

	Logger LOGGER = Logger.getLogger(StationaryItemsService.class);
	private static String Invoked = "Invoked StationaryItemsService";

	StationaryItemsDao itemsDao;

	public StationaryItemsService() throws SQLException {
		itemsDao = new StationaryItemsDao();
	}

	public Response getAllItems() throws SQLException {
		LOGGER.info(Invoked);
		return itemsDao.getAllItems();
	}

	public Response getItemsByshopId(int shopId) throws SQLException {
		LOGGER.info(Invoked);
		return itemsDao.getItemsByshopId(shopId);
	}

	public String getPresentItemsByshopId(int shopId) throws SQLException {
		LOGGER.info(Invoked);
		return itemsDao.getPresentItemsByshopId(shopId);
	}


	public Response addShopItems(int shopId, String updatedItems, UriInfo uriInfo) throws SQLException {
		LOGGER.info(Invoked);
		return itemsDao.addShopItems(shopId, updatedItems, uriInfo);
	}

}
