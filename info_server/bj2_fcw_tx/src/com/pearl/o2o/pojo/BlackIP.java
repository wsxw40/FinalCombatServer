package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BlackIP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String ip;
	
	private String isBanned;
	
	private int bannedTime;
	
	private String description;
	
	private int createTime;
	
	private String createTimeStr;
	
	private int isChanged;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsBanned() {
		return isBanned;
	}

	public void setIsBanned(String isBanned) {
		this.isBanned = isBanned;
	}

	public int getBannedTime() {
		return bannedTime;
	}

	public void setBannedTime(int bannedTime) {
		this.bannedTime = bannedTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		long temp = createTime;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis((long)(1000L*temp));
		this.createTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
		this.createTime = createTime;
	}

	public int getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(int isChanged) {
		this.isChanged = isChanged;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String creatTimeStr) {
		this.createTimeStr = creatTimeStr;
	}
	
	
	
}
