package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.balaji.mybazaar.service.StationaryShopService;

@Path("/shops")
public class StationaryShopResource {

	StationaryShopService statService ;
	
	public StationaryShopResource() throws SQLException {
		statService= new StationaryShopService();
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getShopDetails() throws SQLException {
		
		return statService.getAllShopDetails();
	}
	
	@GET
	@Path("{shopId : \\d+}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getShopDetails(@PathParam("shopId") int shId) throws SQLException {
		
		return statService.getShopDetailsByID(shId);
		
	}
	
	@GET
	@Path("/name")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getShopDetails(@QueryParam("shopName") String shName) throws SQLException {
		
		return statService.getShopDetailsByName(shName);
		
	}
	

	@Path("{shopid}/items")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public StationaryItemsResource getItemsByShopId(@PathParam("shopid") int shopid) throws SQLException {

		return new StationaryItemsResource(shopid) ;
		
	}
	
}
