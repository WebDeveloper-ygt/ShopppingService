package com.balaji.mybazaar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.balaji.mybazaar.main.DbConnection;
import com.balaji.mybazaar.model.StationaryShopBean;
import com.balaji.mybazaar.utils.BazaarUtils;

public class StationaryShopDao {

	List<StationaryShopBean> shopList = new ArrayList<StationaryShopBean>();
	Connection conn;
	String ShopById;
	
	public StationaryShopDao() throws SQLException {
		DbConnection DbConn = new DbConnection();
		conn = DbConn.getDbConnection();
	}
	
	public Response getShopDetailsById(int shId) throws SQLException {
		// TODO Auto-generated method stub
		ShopById = BazaarUtils.SHOPS_ID + shId + " or pincode = "+ shId;
		System.out.println(ShopById);
		return this.getShopDetails(ShopById);
	}
	
	public Response getAllShopDetails() throws SQLException {
		// TODO Auto-generated method stub
		ShopById = BazaarUtils.SHOPS;
		return this.getShopDetails(ShopById);
	}
	
	public Response getShopDetailsByName(String shName) throws SQLException {
		// TODO Auto-generated method stub

		ShopById = BazaarUtils.SHOPS + " where shopName = '"+shName+ "' or shopName like '%" + shName.substring(0, 3) +"%' collate utf8_general_ci" ;
		System.out.println(ShopById);
		return this.getShopDetails(ShopById);
	}
	
	public Response getShopDetails(String ShopById) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(ShopById);
		ResultSet resSet =pst.executeQuery();
	
		while(resSet.next()) {
			StationaryShopBean shopBean =new StationaryShopBean();
			shopBean.setShopId(resSet.getInt(1));
			shopBean.setShopName(resSet.getString(2));
			shopBean.setCountry(resSet.getString(3));
			shopBean.setState(resSet.getString(4));
			shopBean.setDistrict(resSet.getString(5));
			shopBean.setTaluk(resSet.getString(6));
			shopBean.setCity(resSet.getString(7));
			shopBean.setPincode(resSet.getInt(8));
			shopBean.setStreet(resSet.getString(9));
			shopBean.setLandmark(resSet.getString(10));
			
			shopList.add(shopBean);
		}
		
		for (StationaryShopBean stationaryShopBean : shopList) {
			System.out.println(stationaryShopBean.getCity());
		}
		return Response.status(Status.OK).entity(new GenericEntity<List<StationaryShopBean>>(shopList) {}).build();
	}
}