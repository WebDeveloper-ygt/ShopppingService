package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.InputNotAllowed;
import com.balaji.mybazaar.service.StationaryItemsService;
import com.balaji.mybazaar.utils.BasicUtils;

@Path("/items")
public class ItemsResource {

	Logger LOGGER = Logger.getLogger(ItemsResource.class);
	private static String Invoked = "Invoked ItemsResource";
	StationaryItemsService itemService;

	public ItemsResource() throws SQLException {
		// TODO Auto-generated constructor stub
		itemService = new StationaryItemsService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getItems() throws SQLException {
		LOGGER.info(Invoked);
		Response items = itemService.getAllItems();
		if (items.getStatus() != 200) {
			LOGGER.info("Response Status : " + items.getStatus());
			throw new CustomNotFoundException();
		}
		return items;
	}
/*
	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getItemsByName(@QueryParam("itemName") String itName) throws SQLException {
		return itemService.getAllItemsByName(itName);
	}*/

	@GET
	@Path("{shopId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getItemsByShopId(@PathParam("shopId") String shId, @Context UriInfo uriInfo) throws SQLException {
		LOGGER.info(Invoked);
		if (BasicUtils.validateParamInput(shId) == true) {
			LOGGER.info("User input shop id validation successful");
			Response itemResult = itemService.getItemsByshopId(Integer.parseInt(shId));
			if (itemResult.getStatus() != 200) {
				LOGGER.info("Response Status : " + itemResult.getStatus());
				throw new CustomNotFoundException();
			}
			return itemResult;
		} else {
			LOGGER.info("User input shop id validation failed and Input value is : " + shId);
			throw new InputNotAllowed();
		}

	}
}
