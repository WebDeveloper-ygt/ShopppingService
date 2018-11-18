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
import com.balaji.mybazaar.model.ItemsBean;
import com.balaji.mybazaar.model.ShopItemsBean;
import com.balaji.mybazaar.utils.BasicUtils;
import com.balaji.mybazaar.utils.BazaarUtils;

public class StationaryItemsDao {

	List<ItemsBean> itemsList;
	DbConnection dbConn;
	Connection conn;
	String Items;
	public StationaryItemsDao() throws SQLException {
		dbConn = new DbConnection();
		conn = dbConn.getDbConnection();
		itemsList = new ArrayList<>();
	}

	public Response getAllItems() throws SQLException {
		
		Items= BazaarUtils.ITEMS;
		return this.getItemsByCommon(Items);
	}
	
	private Response getItemsByCommon(String items2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Response getItemsByshopId(int shopId) throws SQLException {
		Items= BazaarUtils.ITEMS_ID + shopId;
		PreparedStatement pst = conn.prepareStatement(Items);
		ResultSet resSet = pst.executeQuery();
		ShopItemsBean shItem = new ShopItemsBean();
		while (resSet.next()) {
			
			shItem.setShopId(resSet.getInt(1));
			String itemJson = resSet.getString(2);
			System.out.println(itemJson);
			List<ItemsBean> shopItemsBean = BasicUtils.StringToListShopItems(itemJson);
			shItem.setItemsBean(shopItemsBean);
		}
		
		return Response.status(Status.OK).entity(shItem).build();

		
	}
	
	
	public Response getAllItemsByName(String itName) throws SQLException {
		// TODO Auto-generated method stub
		Items = BazaarUtils.ITEMS + " where itemName = '"+itName+ "' or itemName like '%" + itName.substring(0, 3) +"%' collate utf8_general_ci" ;
		return this.getItemsByCommon(Items);
	}

	

}
