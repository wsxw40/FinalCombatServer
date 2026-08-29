package com.pde.info.analyser.pojo;

import java.util.Date;

public class MedalExchange {
	private int id;
	
	private int playerId;
	private int paymentId;
	private int itemId;	
	private String itemName;
	private int cost;
	private int paymentUnit;
	private int paymentUnitType;
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getPaymentUnit() {
		return paymentUnit;
	}
	public void setPaymentUnit(int paymentUnit) {
		this.paymentUnit = paymentUnit;
	}
	public int getPaymentUnitType() {
		return paymentUnitType;
	}
	public void setPaymentUnitType(int paymentUnitType) {
		this.paymentUnitType = paymentUnitType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	
}
