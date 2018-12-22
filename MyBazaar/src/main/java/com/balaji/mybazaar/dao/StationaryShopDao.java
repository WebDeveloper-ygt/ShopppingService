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

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.ExceptionOccurred;
import com.balaji.mybazaar.exception.ResourceNotCreated;
import com.balaji.mybazaar.main.DbConnection;
import com.balaji.mybazaar.model.StationaryShopBean;
import com.balaji.mybazaar.utils.BazaarUtils;

public class StationaryShopDao {

	Logger LOGGER = Logger.getLogger(StationaryShopDao.class);
	private static String Invoked="Invoked StationaryShopDao";
	
	List<StationaryShopBean> shopList = new ArrayList<StationaryShopBean>();
	Connection conn;
	String ShopById;
	
	public StationaryShopDao() throws SQLException {
		DbConnection DbConn = new DbConnection();
		conn = DbConn.getDbConnection();
	}
	
	public Response getShopDetailsById(int shId) throws SQLException {
		LOGGER.info(Invoked);
		ShopById = BazaarUtils.SHOPS_ID + shId + " or pincode = "+ shId;
		LOGGER.info("Fetching the shop details for the pincode or shopid : "+ shId);
		return this.getShopDetailsCommon(ShopById);
	}
	
	public Response getAllShopDetails() throws SQLException {
		LOGGER.info(Invoked);
		ShopById = BazaarUtils.SHOPS;
		LOGGER.info("Fetching all the shop details");
		return this.getShopDetailsCommon(ShopById);
	}
	
	public Response getShopDetailsByName(String shName) throws SQLException {
		LOGGER.info(Invoked);
		ShopById = BazaarUtils.SHOPS + " where shopName = '"+shName+ "' or shopName like '%" + shName +"%' collate utf8_general_ci" ;
		LOGGER.info("Fetching the shop details by Name : "+ shName);
		return this.getShopDetailsCommon(ShopById);
	}
	
	public Response getShopDetailsCommon(String ShopById) throws SQLException {
		PreparedStatement pst = conn.prepareStatement(ShopById);
		ResultSet resSet =pst.executeQuery();
		boolean checker =false;
		while(resSet.next()) {
			checker = true;
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
		if(checker == true) {
			return Response.status(Status.OK).entity(new GenericEntity<List<StationaryShopBean>>(shopList) {}).build();
		}else {
			LOGGER.info("Response Status : "+ 404);
			throw new CustomNotFoundException();
		}
	
	}

	public Response addShops(StationaryShopBean shop) throws SQLException {
		// TODO Auto-generated method stub
		try {
			String addShopSql = "INSERT INTO `bazaar`.`st_shops`(`shopName`,`country`,`state`,`district`,`taluk`,`city`,`pincode`,`street`, `landmark`) VALUES(?,?,?,?,?,?,?,?,?)";

			PreparedStatement pst = conn.prepareStatement(addShopSql);
			pst.setString(1, shop.getShopName());
			pst.setString(2, shop.getCountry());
			pst.setString(3, shop.getState());
			pst.setString(4, shop.getDistrict());
			pst.setString(5, shop.getTaluk());
			pst.setString(6, shop.getCity());
			pst.setInt(7, shop.getPincode());
			pst.setString(8, shop.getStreet());
			pst.setString(9, shop.getLandmark());
			boolean result = pst.execute();
			if(result == false) {
				String shops = BazaarUtils.SHOPS_ID + shop.getShopId();
				LOGGER.info("Created the shop of name : " + shop.getShopName()+ " and shopId : "+shop.getShopId() );
				boolean shopItemRes = this.addShopItems(shop.getShopId());
				if(shopItemRes == true) {
					LOGGER.info("Returning the created shops details");
					return this.getShopDetailsCommon(shops);
				}else {
					throw new ResourceNotCreated();
				}
				
				
			}else {
				return Response.status(Status.BAD_REQUEST).build();
			}
			}catch(Exception ex) {
				throw new ExceptionOccurred();
			}
	}
	
	public boolean addShopItems(int shopId) throws SQLException {
		String addItems = "INSERT INTO `bazaar`.`sh_items` (`shopId`,`shopItems`)VALUES(?,?)";
		String items = "[{}]";
		LOGGER.info("Creating the shop items for the shopId : "+ shopId );
		PreparedStatement pst = conn.prepareStatement(addItems);
		pst.setInt(1,shopId);
		pst.setString(2, items);
		boolean result = pst.execute();
		if(result == false) {
			LOGGER.info("Created the shopId: "+shopId); 
			return true;
		}
		return false;
	}
}