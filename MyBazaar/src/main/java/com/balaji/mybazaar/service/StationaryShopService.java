package com.balaji.mybazaar.service;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import com.balaji.mybazaar.dao.StationaryShopDao;

public class StationaryShopService {
	StationaryShopDao statDao;
	public StationaryShopService() throws SQLException {
		statDao = new StationaryShopDao();
	}

	public Response getShopDetailsByID(int shId) throws SQLException {
		// TODO Auto-generated method stub
		
		return statDao.getShopDetailsById(shId);
	}

	public Response getAllShopDetails() throws SQLException {
		// TODO Auto-generated method stub
		return statDao.getAllShopDetails();
	}

	public Response getShopDetailsByName(String shName) throws SQLException {
		// TODO Auto-generated method stub
		return statDao.getShopDetailsByName(shName);
	}

	
}
