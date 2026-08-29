package com.pearl.manager.pojo;

import java.io.Serializable;

public class BaseEveryDayCharge implements Serializable {
	
	private static final long serialVersionUID = 3444590500890855453L;
	private double singleRmb;//金额
	private double userCount;//人数
	private int totalRmb;//总金额
	public BaseEveryDayCharge(double singleRmb,double userCount,int totalRmb){
		this.singleRmb=singleRmb; // xField
		this.userCount=userCount;// yField
		this.totalRmb=totalRmb;// percent
	}
	public double getSingleRmb() {
		return singleRmb;
	}
	public void setSingleRmb(double singleRmb) {
		this.singleRmb = singleRmb;
	}
	public double getUserCount() {
		return userCount;
	}
	public void setUserCount(double userCount) {
		this.userCount = userCount;
	}
	public int getTotalRmb() {
		return totalRmb;
	}
	public void setTotalRmb(int totalRmb) {
		this.totalRmb = totalRmb;
	}
	
}
