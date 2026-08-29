package com.snda.services.oa.client.bean;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.pearl.o2o.pojo.SysItem;


/**
 * This class represent the detail information of a deal
 * 
 * @author Timon Zhang
 */
public class ItemsPurchaseInfo {
	private final Set<ItemPurchaseEntry> itemsInfo ;
	//redundancy field
	private final int amount;
	private final int itemNums;
	
	
	
	public ItemsPurchaseInfo(Set<ItemPurchaseEntry> itemInfos){
		this.itemsInfo = Collections.unmodifiableSet( itemInfos);
		this.amount = calculateAmount();
		this.itemNums =  calculateItemNums();
	}
	
	private int calculateItemNums(){
		//TODO 
		Set<Integer> itemIds = new HashSet<Integer>();
		for (ItemPurchaseEntry itemInfo : itemsInfo) {
			itemIds.add(itemInfo.getItem().getId());
		}
		return itemIds.size();
	}
	
	private int calculateAmount(){
		int amount = 0;
		for (ItemPurchaseEntry itemInfo : itemsInfo) {
			amount += itemInfo.getAmount();
		}
		return amount;
	}
	
	public int getTotalAmount(){
		return amount;
	}
	
	public Set<ItemPurchaseEntry> getItemsInfo() {
		return itemsInfo;
	}
	
	public int getItemNums() {
		return itemNums;
	}


	public static class ItemPurchaseEntry{
		private final SysItem item;
		private final int quantity;
		private final int costType;
		private final String isGift;
		private final int amount;
		//this field was add later for special deal like purchase character.
		//if this field was set to true, the related items will be persisted in some way, most deal like buy weapon or items. 
		//if this field was set to false, the related sysItem will not be persisted. like purchase character, it's has no real
		//related sysItem. so it will not persist the related sysItem
		private boolean needPersist = true;
		
		public ItemPurchaseEntry(SysItem item, int quantity, int costType, String isGift) {
			this.item = item;
			this.quantity = quantity;
			this.costType = costType;
			this.amount = calculateAmount();
			this.isGift = isGift;
		}

		private int calculateAmount() {
			try {//REFACTOR later
				Method getCostMethod = SysItem.class.getMethod("getCost"+ costType);
				return (Integer) getCostMethod.invoke(item) * quantity;
			} catch (Exception e) {
				return -1;
			}
		}

		public SysItem getItem() {
			return item;
		}

		public int getQuantity() {
			return quantity;
		}

		public int getCostType() {
			return costType;
		}

		public int getAmount() {
			return amount;
		}
		
		
		public String getIsGift() {
			return isGift;
		}
		
		public boolean isNeedPersist() {
			return needPersist;
		}

		public void setNeedPersist(boolean needPersist) {
			this.needPersist = needPersist;
		}

		@Override
		public String toString() {
			 return ReflectionToStringBuilder.toString(this);
		}
	}
}
