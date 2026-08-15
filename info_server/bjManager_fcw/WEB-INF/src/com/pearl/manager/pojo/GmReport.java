package com.pearl.manager.pojo;

import java.io.Serializable;
import java.util.Date;

public class GmReport extends BaseMappingBean<GmReport> implements Serializable {

	private static final long serialVersionUID = 6556687832054785984L;
	
	private int reportPlayerId;
	private String reportPlayerName;
	private int targetPlayerId;
	private String targetPlayerName;
	private String msg;
	private Date reportDate;
	private int type;
	private Date handleTime;
	private String isHandle;
	
	public int getReportPlayerId() {
		return reportPlayerId;
	}
	public void setReportPlayerId(int reportPlayerId) {
		this.reportPlayerId = reportPlayerId;
	}
	public int getTargetPlayerId() {
		return targetPlayerId;
	}
	public void setTargetPlayerId(int targetPlayerId) {
		this.targetPlayerId = targetPlayerId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIsHandle() {
		return isHandle;
	}
	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}
	public String getReportPlayerName() {
		return reportPlayerName;
	}
	public void setReportPlayerName(String reportPlayerName) {
		this.reportPlayerName = reportPlayerName;
	}
	public String getTargetPlayerName() {
		return targetPlayerName;
	}
	public void setTargetPlayerName(String targetPlayerName) {
		this.targetPlayerName = targetPlayerName;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

}
