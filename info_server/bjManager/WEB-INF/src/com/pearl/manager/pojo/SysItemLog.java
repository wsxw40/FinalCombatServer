package com.pearl.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysItemLog implements Serializable{
	private static final long serialVersionUID = 6108625730230483070L;
	private int logVersion;
	private SysItem sysItem;
	private Integer id;
	private Date createTime;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getLogVersion() {
		return logVersion;
	}
	public void setLogVersion(int logVersion) {
		this.logVersion = logVersion;
	}
	public SysItem getSysItem() {
		return sysItem;
	}
	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
