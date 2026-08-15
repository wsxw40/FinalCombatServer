package com.pearl.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class InterfaceRecord implements Serializable{
	
	private static final long serialVersionUID = -5045813308467854795L;

	private int id;
	
	private String account;
	
	private String roleId;
	
	private int regionId;
	
	private Date timestamp;
	
	private String sysItemId;//表示PaymentId集合 如果为0为默认的数量和消耗方式
	
	private int type;//0:给予奖励 1:发送邮件
	
	private String eventId;//当type=0时表示活动号	
	
	private String orderId;//当type=0时表示订单号
	
	private String title;//表示邮件标题
	
	private String content;//表示邮件内容
	
	private String itemnum;//表示SysitemId集合

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSysItemId() {
		return sysItemId;
	}

	public void setSysItemId(String sysItemId) {
		this.sysItemId = sysItemId;
	}

	public String getItemnum() {
		return itemnum;
	}

	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}
}
