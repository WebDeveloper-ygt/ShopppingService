package com.balaji.mybazaar.resources;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.InputNotAllowed;
import com.balaji.mybazaar.exception.ResourceNotCreated;
import com.balaji.mybazaar.model.ItemsBean;
import com.balaji.mybazaar.service.StationaryItemsService;
import com.balaji.mybazaar.utils.BasicUtils;

public class StationaryItemsResource {

	Logger LOGGER = Logger.getLogger(StationaryItemsResource.class);
	private static String Invoked = "Invoked StationaryItemsResource";
	int shopId;
	String itemName;
	StationaryItemsService stateItemShop;

	public StationaryItemsResource(int shId) throws SQLException {
		// TODO Auto-generated constructor stub
		this.shopId = shId;
		this.stateItemShop = new StationaryItemsService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getItemsByshopId(@Context UriInfo uriInfo) throws SQLException {
		LOGGER.info(Invoked);
		Response shopItems = stateItemShop.getItemsByshopId(shopId);
		if (shopItems.getStatus() != 200) {
			throw new CustomNotFoundException();
		} else {
			return shopItems;
		}

	}

	/*
	 * public String getItemsByItemName() { return itemName; }
	 */

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addShopItems(List<ItemsBean> items, @Context UriInfo uriInfo) throws SQLException {
		LOGGER.info(Invoked);
		String presentItems = stateItemShop.getPresentItemsByshopId(shopId);
		presentItems = presentItems.replace("]", ",");
		
		Iterator<ItemsBean> iter = items.iterator();
		int id=BasicUtils.generateItemId();
		while (iter.hasNext()) {
			ItemsBean bean = iter.next();
			boolean valid= BasicUtils.validateListInputParam(bean);
			LOGGER.info(valid);
			if( valid == true) {
				bean.setItemId(id);
				id++;	
			}else {
				throw new ResourceNotCreated();
			}
			
		}
		
		String latestItems = BasicUtils.ListShopItemsToString(items);
		latestItems = latestItems.replace("[", "");
		
		String updatedItems = presentItems+latestItems;
		return stateItemShop.addShopItems(shopId,updatedItems,uriInfo);
		
	}
}
