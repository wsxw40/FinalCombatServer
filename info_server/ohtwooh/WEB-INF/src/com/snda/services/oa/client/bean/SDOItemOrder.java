package com.snda.services.oa.client.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.pearl.o2o.pojo.SDOItemOrderPojo;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo.ItemPurchaseEntry;

/**
 * After user account was locked successfully, an instance of this class will be created to trace this deal
 * @author Timon Zhang
 */
public class SDOItemOrder implements Serializable {
	private static final long serialVersionUID = 2088171853011275576L;
	
	private OrderState orderState;
	private Date lastModifyTime;
	private final PayType payType;
	private final String orderId;
	private final int amount;
	private final PlayerInfo playerInfo;
	private final ItemsPurchaseInfo itemsInfo;
	
	/**
	  	nType=1 点券购买道具消息
     	nType=3 寄售点券
     	nType=4 点券购买
     	nType=5 交易
    	nType=6 多币种扣费消息
	 */
	public static enum PayType{
		TICKET(3);
		private int code;
		PayType(int code){
			this.code = code;
		}
		public int getCode(){
			return code;
		}
		public static PayType getPayTypeByCode(int code){
			for (PayType payType : PayType.values()) {
				if (payType.getCode() == code) {
					return payType;
				}
			}
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	public static enum OrderState{
		NEW,COMMITTED,ROLLBACKED,COMMIT_FAIL,ROLLBACK_FAIL;
	}
	
	public SDOItemOrder(SDOItemOrderPojo pojo) throws Exception{
		this.orderId = pojo.getOrderId();
		this.payType = PayType.getPayTypeByCode(pojo.getPaytype());
		this.orderState = OrderState.valueOf(pojo.getState());
		this.amount = pojo.getAmount();
		this.lastModifyTime = pojo.getLastModifyTime();
		this.playerInfo = new PlayerInfo(pojo.getSdoUid(),pojo.getO2oUid(),pojo.getPlayerId(),pojo.getEndpointIp(),pojo.getAreaIdPlayer(),pojo.getGroupIdPlayer());
		this.itemsInfo = pojo.getItemsInfo();
	}
	
	public SDOItemOrderPojo getSDOItemOrderPojo() {
		SDOItemOrderPojo pojo = new SDOItemOrderPojo();
		
		pojo.setOrderId(orderId);
		pojo.setPaytype(payType.getCode());
		pojo.setSdoUid((playerInfo.getSdoUid()));
		pojo.setO2oUid(playerInfo.getO2oUid());
		pojo.setPlayerId(playerInfo.getPlayerId());
		pojo.setState(orderState.name());
		pojo.setAmount(amount);
		pojo.setLastModifyTime(lastModifyTime);
		pojo.setEndpointIp(playerInfo.getEndpointIp());
		pojo.setAreaIdPlayer(playerInfo.getAreaIdPlayer());
		pojo.setGroupIdPlayer(playerInfo.getGroupIdPlayer());
		pojo.setItemsInfo(itemsInfo);
		return pojo;
	}
	
	public SDOItemOrder (Set<ItemPurchaseEntry> itemsInfo, PlayerInfo playerInfo, PayType payType, String contextId) throws Exception {
		//use contextId as id of an order
		this.orderId = contextId;
		this.itemsInfo = new ItemsPurchaseInfo(itemsInfo);
		this.amount = this.itemsInfo.getTotalAmount();
		this.payType = payType;
		this.playerInfo = playerInfo;
		this.orderState = OrderState.NEW;
	}
	
	public void changeStateTo(OrderState newState) {
		this.orderState = newState;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public OrderState getOrderState() {
		return  orderState;
	}

	public int getTotalAmount() {
		return amount;
	}


	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public PayType getPayType() {
		return payType;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	

	public ItemsPurchaseInfo getItemsPurchaseInfo() {
		return itemsInfo;
	}
	
	public int getAmount() {
		return amount;
	}

	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
}
