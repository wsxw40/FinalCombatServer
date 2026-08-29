package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.snda.services.oa.client.bean.ItemsPurchaseInfo;

/**
 * 
 * @author Timon
 */
public class SDOItemOrderPojo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String orderId;
	private int paytype;
	private String sdoUid;//it's snda userId
	private int o2oUid;
	private int playerId;
	private String state;
	private int amount;
	private Date lastModifyTime;
	private String endpointIp;
	private int areaIdPlayer;
	private int groupIdPlayer;
	//when this instance was persisted,  field 'itemsInfo' will be convert to 'goodsInfo' to be persisted
	private String goodsinfo;
	private ItemsPurchaseInfo itemsInfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public int getPaytype() {
		return paytype;
	}
	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}
	
	
	public String getSdoUid() {
		return sdoUid;
	}
	public void setSdoUid(String sdoUid) {
		this.sdoUid = sdoUid;
	}
	
	
	
	public int getO2oUid() {
		return o2oUid;
	}
	public void setO2oUid(int uid) {
		o2oUid = uid;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getEndpointIp() {
		return endpointIp;
	}
	public void setEndpointIp(String endpointIp) {
		this.endpointIp = endpointIp;
	}
	public String getGoodsinfo() {
		return goodsinfo;
	}
	public void setGoodsinfo(String goodsinfo) {
		this.goodsinfo = goodsinfo;
	}
	
	public int getAreaIdPlayer() {
		return areaIdPlayer;
	}
	public void setAreaIdPlayer(int areaIdPlayer) {
		this.areaIdPlayer = areaIdPlayer;
	}
	public int getGroupIdPlayer() {
		return groupIdPlayer;
	}
	public void setGroupIdPlayer(int groupIdPlayer) {
		this.groupIdPlayer = groupIdPlayer;
	}
	
	public ItemsPurchaseInfo getItemsInfo() {
		return itemsInfo;
	}
	public void setItemsInfo(ItemsPurchaseInfo itemsInfo) {
		this.itemsInfo = itemsInfo;
	}
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
}
