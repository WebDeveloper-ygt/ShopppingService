package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.mybazaar.service.StationaryItemsService;

public class StationaryItemsResource {
	
	int shopId;
	String itemName;
	public StationaryItemsResource(int shId) throws SQLException {
		// TODO Auto-generated constructor stub
		this.shopId = shId;
	}

	public StationaryItemsResource(String name) throws SQLException {
		// TODO Auto-generated constructor stub
		this.itemName = name;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Response getItemsByshopId() throws SQLException {
		return new StationaryItemsService().getItemsByshopId(shopId);
		
		
	}

	public String getItemsByItemName() {
		return itemName;
	}
}
