package com.pearl.manager.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PayLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -984886492847342555L;
	
	private int id;
	private String userId;
	private int playerId;
	private int itemId;
	private String itemName;
	private int itemType;
	private int leftMoney;
	private int payType;
	private int payUse;//1BUY2RENEW3GIFT
	private int payAmount;
	private Date createTime;
	
	
	private String createTimeStr;
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getLeftMoney() {
		return leftMoney;
	}

	public void setLeftMoney(int leftMoney) {
		this.leftMoney = leftMoney;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getPayUse() {
		return payUse;
	}

	public void setPayUse(int payUse) {
		this.payUse = payUse;
	}

	public int getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public int getPlayerId() {
//		return playerId;
//	}
//	public void setPlayerId(int playerId) {
//		this.playerId = playerId;
//	}
//	public void setCreateTimeStr(String createTimeStr) {
//		this.createTimeStr = createTimeStr;
//	}
//	public int getItemId() {
//		return itemId;
//	}
//	public void setItemId(int itemId) {
//		this.itemId = itemId;
//	}
//	public String getItemName() {
//		return itemName;
//	}
//	public void setItemName(String itemName) {
//		this.itemName = itemName;
//	}
//	public int getPayType() {
//		return payType;
//	}
//	public void setPayType(int payType) {
//		this.payType = payType;
//	}
//	public int getPayAmount() {
//		return payAmount;
//	}
//	public void setPayAmount(int payAmount) {
//		this.payAmount = payAmount;
//	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createTime);
		this.createTime = createTime;
	}
//	public int getItemType() {
//		return itemType;
//	}
//	public void setItemType(int itemType) {
//		this.itemType = itemType;
//	}
//	public int getLeftMoney() {
//		return leftMoney;
//	}
//	public void setLeftMoney(int leftMoney) {
//		this.leftMoney = leftMoney;
//	}
//	public int getPayUse() {
//		return payUse;
//	}
//	public void setPayUse(int payUse) {
//		this.payUse = payUse;
//	}
}
