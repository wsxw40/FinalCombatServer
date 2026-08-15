package com.pearl.o2o.utils;

import com.pearl.o2o.service.GetService;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo;

/**
 * this converter was used to convert an item map to a string or convert a string to item map
 * 
 * @author Timon Zhang
 */
public interface GoodsInfoConverter {
	
	public  String convertItemsToPlainString(ItemsPurchaseInfo itemInfo) ;

	public  ItemsPurchaseInfo convertPlainStringToItems(String goodsInfo, GetService getService) throws Exception;
}