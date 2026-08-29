package com.pearl.o2o.pojo;

import java.util.Date;

public class GmLog {

	private int id;
	private int gmUserId;
	private String gmUserName;
	private String type;
	private String methodName;
	private String args;
	private Date recordTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGmUserId() {
		return gmUserId;
	}
	public void setGmUserId(int gmUserId) {
		this.gmUserId = gmUserId;
	}
	public String getGmUserName() {
		return gmUserName;
	}
	public void setGmUserName(String gmUserName) {
		this.gmUserName = gmUserName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
}
