package com.pearl.o2o.utils;

import java.util.HashSet;
import java.util.Set;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.service.GetService;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo.ItemPurchaseEntry;

public class GoodsInfoConverterImpl implements GoodsInfoConverter {
	
	/* (non-Javadoc)
	 * @see com.snda.services.oa.client.bean.GoodsInfoConvertor#convertItemsToPlainString(java.util.Map)
	 */
	public  String convertItemsToPlainString(ItemsPurchaseInfo itemsInfo){
		//"id1,number1,costType1,isGift1,totlaAmount1|id2,number2,costType2,isGift2,totalAmount2|....."
		StringBuilder goodsInfo = new StringBuilder();
		for (ItemPurchaseEntry itemInfo : itemsInfo.getItemsInfo()) {
			goodsInfo.append(itemInfo.getItem().getId());
			goodsInfo.append(",");
			goodsInfo.append(itemInfo.getQuantity());
			goodsInfo.append(",");
			goodsInfo.append(itemInfo.getCostType());
			goodsInfo.append(",");
			goodsInfo.append(itemInfo.getIsGift());
			goodsInfo.append(",");
			goodsInfo.append(itemInfo.getAmount());
			goodsInfo.append("|");
		}
		//remove last '|'
		goodsInfo.deleteCharAt(goodsInfo.length()-1);
		return goodsInfo.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.snda.services.oa.client.bean.GoodsInfoConvertor#convertPlainStringToItems(java.lang.String, com.pearl.o2o.service.GetService)
	 */
	public  ItemsPurchaseInfo convertPlainStringToItems(String goodsInfo, GetService getService) throws Exception{
		String[] entries = goodsInfo.split("\\|");
		Set<String> itemIdSet = new HashSet<String>(entries.length);
		
		//put each attributes into a array slot
		String[][] attributes = new String[entries.length][5];
		
		int count = 0;
		//each entry like 'id,number,costType,isGift,amount'
		for (String entry : entries) {
			String[] elements = entry.split(",");
			//for extends, we can add attribute in future, just keep the old sequence  
			
			//0: id, 1: quantity, 2:costType, 3:isGift, 4:totalAmount
			itemIdSet.add(elements[0]);
			for (int i=0;i<elements.length;i++) {// iterator the length of elements, so we can compatiable with older version
				attributes[count][i] = elements[i];
			}
			count ++;
		}
		
		Set<SysItem> items = getService.getSysItemByItemIds(itemIdSet);
		Set<ItemPurchaseEntry> itemInfos = new HashSet<ItemPurchaseEntry>();
		count = 0;
		for (SysItem item : items) {
			int quantity = Integer.valueOf(attributes[count][1]);
			int costType = Integer.valueOf(attributes[count][2]);
			String isGift = attributes[count][3];
			ItemPurchaseEntry itemInfo = new ItemPurchaseEntry(item,quantity,costType,isGift);
			itemInfos.add(itemInfo);
		}
		
		return new ItemsPurchaseInfo(itemInfos);
	}
}
