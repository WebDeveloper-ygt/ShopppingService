package com.balaji.mybazaar.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.balaji.mybazaar.model.ItemsBean;
import com.balaji.mybazaar.service.StationaryItemsService;
import com.balaji.mybazaar.utils.BasicUtils;

public class StationaryItemsResource {
	
	int shopId;
	String itemName;
	StationaryItemsService stateItemShop;
	public StationaryItemsResource(int shId) throws SQLException {
		// TODO Auto-generated constructor stub
		this.shopId = shId;
		this.stateItemShop = new StationaryItemsService();
	}

	public StationaryItemsResource(String name) throws SQLException {
		// TODO Auto-generated constructor stub
		this.itemName = name;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getItemsByshopId(@Context UriInfo uriInfo) throws SQLException {
		return stateItemShop.getItemsByshopId(shopId,uriInfo);
		
		
	}

	/*public String getItemsByItemName() {
		return itemName;
	}*/
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addShopItems(List<ItemsBean> items, @Context UriInfo uriInfo) throws SQLException {
		String presentItems = stateItemShop.getOnlyItemsByshopId(shopId,uriInfo);
		presentItems = presentItems.replace("]", ",");
	
		String latestItems = BasicUtils.ListShopItemsToString(items);
		latestItems = latestItems.replace("[", "");
		
		String updatedItems = presentItems+latestItems;
		return stateItemShop.addShopItems(shopId,updatedItems,uriInfo);
	}

}
