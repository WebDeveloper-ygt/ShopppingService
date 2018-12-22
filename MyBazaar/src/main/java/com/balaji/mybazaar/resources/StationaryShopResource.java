package com.balaji.mybazaar.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNotFoundException;
import com.balaji.mybazaar.exception.ExceptionOccurred;
import com.balaji.mybazaar.exception.InputNotAllowed;
import com.balaji.mybazaar.exception.ResourceNotCreated;
import com.balaji.mybazaar.model.StationaryShopBean;
import com.balaji.mybazaar.service.StationaryShopService;
import com.balaji.mybazaar.utils.BasicUtils;

@Path("/shops")
public class StationaryShopResource {

	Logger LOGGER = Logger.getLogger(StationaryItemsResource.class);
	private static String Invoked = "Invoked StationaryShopResource";

	StationaryShopService statService;

	public StationaryShopResource() throws SQLException {
		statService = new StationaryShopService();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getShopDetails() throws SQLException {
		LOGGER.info(Invoked);
		Response allShops = statService.getAllShopDetails();
		if (allShops.getStatus() != 200) {
			LOGGER.info("Response Status : " + allShops.getStatus());
			throw new ExceptionOccurred();
		}
		return allShops;
	}

	@GET
	@Path("{shopId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getShopDetailsByID(@PathParam("shopId") String shId) throws SQLException {
		LOGGER.info(Invoked);
		if (BasicUtils.validateParamInput(shId) == true) {
			LOGGER.info("User input shopId or Pincode validation successful");
			Response shopById = statService.getShopDetailsByID(Integer.parseInt(shId));
			if (shopById.getStatus() != 200) {
				LOGGER.info("Response Status : " + shopById.getStatus());
				throw new CustomNotFoundException();
			}

			return shopById;
		} else {
			LOGGER.info("User input id validation failed and Input value is : " + shId);
			throw new InputNotAllowed();
		}

	}

	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getShopDetails(@QueryParam("shopName") String shName) throws SQLException {
		LOGGER.info(Invoked);
		if (BasicUtils.validateParamInput(shName) == false) {
			LOGGER.info("User input name validation successful");
			Response shopByName = statService.getShopDetailsByName(shName);
			if (shopByName.getStatus() != 200) {
				LOGGER.info("Response Status : " + shopByName.getStatus());
				throw new CustomNotFoundException();
			}
			return shopByName;
		} else {
			LOGGER.info("User input name validation failed and Input value is : " + shName);
			throw new InputNotAllowed();
		}

	}

	@Path("{shopid}/items")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public StationaryItemsResource getItemsByShopId(@PathParam("shopid") String shopid) throws SQLException {
		LOGGER.info(Invoked);
		if (BasicUtils.validateParamInput(shopid) == true) {
			LOGGER.info("User input shopId validation successful");
			return new StationaryItemsResource(Integer.parseInt(shopid));
		} else {
			LOGGER.info("User input shopId validation failed");
			throw new InputNotAllowed();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addShops(StationaryShopBean shopBean) throws SQLException {
		LOGGER.info(Invoked);
		if (BasicUtils.validateShopsInput(shopBean) == true) {
			LOGGER.info("Creating Shops for the input : "+ shopBean);
			Response added = statService.addShops(shopBean);
			if (added.getStatus() != 200) {
				throw new ResourceNotCreated();
			} else {
				return added;
			}
		} else {
			LOGGER.info(
					"Input resource validation for the shops creation filed : either of the below inputs should not be empty");
			LOGGER.info("Input details are :\n city :" + shopBean.getCity() + " \n country : " + shopBean.getCountry()
					+ "\n District : " + shopBean.getDistrict() + "\n LandMark: " + shopBean.getLandmark()
					+ "\n Pincode: " + shopBean.getPincode() + "\n shopName: " + shopBean.getShopName() + "\n state: "
					+ shopBean.getState() + "\n street: " + shopBean.getStreet() + "\n taluk : " + shopBean.getTaluk());
			throw new ResourceNotCreated();
		}
	}
}
