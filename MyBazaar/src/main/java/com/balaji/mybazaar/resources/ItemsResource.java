package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.mybazaar.service.StationaryItemsService;

@Path("/items")
public class ItemsResource {
	
	StationaryItemsService itemService;
	public ItemsResource() throws SQLException {
		// TODO Auto-generated constructor stub
		itemService = new StationaryItemsService();
	}
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getItems() throws SQLException {
		return itemService.getAllItems();
	}
	
	@GET
	@Path("/name")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getItemsByName(@QueryParam("itemName") String itName) throws SQLException {
		return itemService.getAllItemsByName(itName);
	}
}
