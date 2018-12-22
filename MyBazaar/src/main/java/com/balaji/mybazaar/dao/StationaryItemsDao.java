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
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.CustomNullPointerException;
import com.balaji.mybazaar.main.DbConnection;
import com.balaji.mybazaar.model.ItemsBean;
import com.balaji.mybazaar.model.OrderItemBean;
import com.balaji.mybazaar.model.OrdersBean;
import com.balaji.mybazaar.model.ShopItemsBean;
import com.balaji.mybazaar.utils.BasicUtils;
import com.balaji.mybazaar.utils.BazaarUtils;

public class StationaryItemsDao {

	Logger LOGGER = Logger.getLogger(StationaryItemsDao.class);
	private static String Invoked = "Invoked StationaryItemsDao";
	List<ItemsBean> itemsList;
	List<ShopItemsBean> shopList;
	DbConnection dbConn;
	Connection conn;
	String Items;
	public StationaryItemsDao() throws SQLException {
		dbConn = new DbConnection();
		conn = dbConn.getDbConnection();
		itemsList = new ArrayList<>();
		shopList =  new ArrayList<>();
	}

	public Response getAllItems() throws SQLException {
		LOGGER.info(Invoked);
		Items= BazaarUtils.ITEMS;
		LOGGER.info("Getting all the items from all shops");
		return this.getItemsByCommon(Items);
	}
	
	public Response getItemsByshopId(int shopId) throws SQLException {
		LOGGER.info(Invoked);
		Items= BazaarUtils.ITEMS_ID + shopId;
		LOGGER.info("Getting the items from the shopId : " + shopId);
		return this.getItemsByCommon(Items);
	}
	
	private Response getItemsByCommon(String items2) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = conn.prepareStatement(items2);
		ResultSet resSet = pst.executeQuery();
		boolean checker = false;
		while(resSet.next()) {
			checker = true;
			ShopItemsBean shopBean = new ShopItemsBean();
			shopBean.setShopId(resSet.getInt(1));
			String jsontest = resSet.getString(2);
			//LOGGER.info("Items Details for the customer order :"+ jsontest);
			List<ItemsBean> itemsBean = BasicUtils.StringToListShopItems(jsontest);
			shopBean.setItemsBean(itemsBean);
			shopList.add(shopBean);
		}
		if(checker == true) {
			return Response.status(Status.OK).entity(new GenericEntity<List<ShopItemsBean>>(shopList) {}).build();
		}else {
			LOGGER.info("Response Status : " +404);
			throw new CustomNotFoundException();
		}
		
	}

	public String getPresentItemsByshopId(int shopId) throws SQLException {
		// TODO Auto-generated method stub
		LOGGER.info(Invoked);
		Items= BazaarUtils.ITEMS_ID + shopId;
		LOGGER.info("Getting the items from the shopId : " + shopId);
		PreparedStatement pst = conn.prepareStatement(Items);
		ResultSet resSet = pst.executeQuery();
		boolean checker = false;
		String jsonPresent = null;
		while(resSet.next()) {
			checker = true;
			 jsonPresent= resSet.getString(2);
		}
		if( checker == true) {
			return jsonPresent;
		}else {
			throw new CustomNotFoundException();
		}
	}

	public Response addShopItems(int shopId, String updatedItems, UriInfo uriInfo) throws SQLException {

		String addItem = "UPDATE `bazaar`.`sh_items` SET `shopItems` =?  WHERE `shopId` ="+shopId;
		PreparedStatement pst = conn.prepareStatement(addItem);
		pst.setString(1, updatedItems);
	
		int result =pst.executeUpdate();
		if(result > 0) {
			return getItemsByshopId(shopId);
		}else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}