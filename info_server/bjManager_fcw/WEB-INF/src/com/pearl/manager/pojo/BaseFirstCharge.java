package com.pearl.manager.pojo;

import java.io.Serializable;

public class BaseFirstCharge implements Serializable {
	
	private static final long serialVersionUID = 3444590500890855453L;
	private double singleRmb;
	private double timeNumber;
	private int totalRmb;
	public BaseFirstCharge(double singleRmb,double timeNumber,int totalRmb){
		this.singleRmb=singleRmb; // xField
		this.timeNumber=timeNumber;// yField
		this.totalRmb=totalRmb;// percent
	}
	public double getSingleRmb() {
		return singleRmb;
	}
	public void setSingleRmb(double singleRmb) {
		this.singleRmb = singleRmb;
	}
	public double getTimeNumber() {
		return timeNumber;
	}
	public void setTimeNumber(double timeNumber) {
		this.timeNumber = timeNumber;
	}
	public int getTotalRmb() {
		return totalRmb;
	}
	public void setTotalRmb(int totalRmb) {
		this.totalRmb = totalRmb;
	}
	
}
