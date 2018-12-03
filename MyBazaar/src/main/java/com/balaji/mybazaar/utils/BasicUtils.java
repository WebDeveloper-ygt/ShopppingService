package com.balaji.mybazaar.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.balaji.mybazaar.model.CustomerBean;
import com.balaji.mybazaar.model.ItemsBean;
import com.balaji.mybazaar.model.OrderItemBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BasicUtils {

	static Gson gson = new Gson();
	public static List<OrderItemBean> StringToListOrderItemBean(String jsonString) {
		
		
		Type listType = new TypeToken<ArrayList<OrderItemBean>>(){}.getType();
		List<OrderItemBean> result = gson.fromJson(jsonString, listType);
		
		System.out.println("Converted Result: "+ result.toString());
		return result;
	}
	
public static List<ItemsBean> StringToListShopItems(String jsonString) {
		
		
		Type listType = new TypeToken<ArrayList<ItemsBean>>(){}.getType();
		List<ItemsBean> result = gson.fromJson(jsonString, listType);
		
		System.out.println("Converted Result: "+ result.toString());
		return result;
	}

public static String ListShopItemsToString(List<ItemsBean> items) {
	
		String result = gson.toJson(items);
		return result;
	
}

public static boolean validateCustomerInput(CustomerBean custbean) {
	if(custbean.getEmailId() != null || custbean.getMobileNumber() != null || custbean.getFirstName() != null || custbean.getLastName() != null ) {
	return true;
	}
	return false;
}
}