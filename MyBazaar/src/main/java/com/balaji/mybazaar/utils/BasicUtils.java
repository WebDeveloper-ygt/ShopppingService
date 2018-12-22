package com.balaji.mybazaar.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.balaji.mybazaar.exception.CustomNullPointerException;
import com.balaji.mybazaar.model.CustomerBean;
import com.balaji.mybazaar.model.ItemsBean;
import com.balaji.mybazaar.model.OrderItemBean;
import com.balaji.mybazaar.model.StationaryShopBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BasicUtils {
	private static Logger LOGGER = Logger.getLogger(BasicUtils.class);
	static Gson gson = new Gson();

	public static List<OrderItemBean> StringToListOrderItemBean(String jsonString) {

		Type listType = new TypeToken<ArrayList<OrderItemBean>>() {
		}.getType();
		List<OrderItemBean> result = gson.fromJson(jsonString, listType);
		// LOGGER.info("The order item details has been converted to the List : "+
		// result);
		return result;
	}

	public static List<ItemsBean> StringToListShopItems(String jsonString) {

		Type listType = new TypeToken<ArrayList<ItemsBean>>() {
		}.getType();
		List<ItemsBean> result = gson.fromJson(jsonString, listType);

		System.out.println("Converted Result: " + result.toString());
		return result;
	}

	public static String ListShopItemsToString(List<ItemsBean> items) {

		String result = gson.toJson(items);
		return result;

	}

	public static boolean validateCustomerInput(CustomerBean custbean) {
		if (custbean.getEmailId().equals("") || custbean.getMobileNumber().equals("")
				|| custbean.getFirstName().equals("") || custbean.getLastName().equals("")) {
			return true;
		}
		return false;
	}

	public static boolean validateParamInput(String param) {
		try {
			Integer.parseInt(param);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	public static boolean validateListInputParam(ItemsBean itemsBean) {

		if (itemsBean.getItemBrand().equals("") || itemsBean.getItemDescription().equals("")
				|| itemsBean.getItemName().equals("") || itemsBean.getItemPrice() == 0.0
				|| itemsBean.getItemQuantity().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static int generateItemId() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int id = rand.nextInt(10000);
		return id;
	}

	public static boolean validateShopsInput(StationaryShopBean shopBean) {
		// TODO Auto-generated method stub
		if (shopBean.getShopName().equals("") || shopBean.getCity().equals("") || shopBean.getPincode() == 0
				|| shopBean.getLandmark().equals("") || shopBean.getShopName().equals("")
				|| shopBean.getStreet().equals("") || shopBean.getCountry().equals("")
				|| shopBean.getDistrict().equals("") || shopBean.getState().equals("")) {
			return false;
		}else {
			return true;
		}
	}

}