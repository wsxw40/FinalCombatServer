package com.pde.info.analyser.pojo;

import java.util.Date;


public class ItemCostInfo {
	
	private int playerId;
	private int cost;
	private Date createDate;
	private int itemId;
	private int itemType;
	private String itemName;
	private int payUnit;
	private int payType;
	private int payUnitType;
	private int payUse;  //1 buy; 2 renew; 3 present
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPayUnit() {
		return payUnit;
	}
	public void setPayUnit(int payUnit) {
		this.payUnit = payUnit;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getPayUnitType() {
		return payUnitType;
	}
	public void setPayUnitType(int payUnitType) {
		this.payUnitType = payUnitType;
	}
	public int getPayUse() {
		return payUse;
	}
	public void setPayUse(int payUse) {
		this.payUse = payUse;
	}

	
		
}
