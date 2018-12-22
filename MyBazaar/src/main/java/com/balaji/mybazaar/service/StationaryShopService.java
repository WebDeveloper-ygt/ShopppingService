package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.dao.StationaryShopDao;
import com.balaji.mybazaar.model.StationaryShopBean;
import com.balaji.mybazaar.resources.StationaryItemsResource;

public class StationaryShopService {
	
	Logger LOGGER = Logger.getLogger(StationaryItemsResource.class);
	private static String Invoked="Invoked StationaryShopService";
	
	StationaryShopDao statDao;
	public StationaryShopService() throws SQLException {
		statDao = new StationaryShopDao();
	}

	public Response getShopDetailsByID(int shId) throws SQLException {
		// TODO Auto-generated method stub
		
		return statDao.getShopDetailsById(shId);
	}

	public Response getAllShopDetails() throws SQLException {
		LOGGER.info(Invoked);
		return statDao.getAllShopDetails();
	}

	public Response getShopDetailsByName(String shName) throws SQLException {
		LOGGER.info(Invoked);
		return statDao.getShopDetailsByName(shName);
	}

	public Response addShops(StationaryShopBean shopBean) throws SQLException {
		// TODO Auto-generated method stub
		LOGGER.info(Invoked);
		return statDao.addShops(shopBean);
	}
	
}
