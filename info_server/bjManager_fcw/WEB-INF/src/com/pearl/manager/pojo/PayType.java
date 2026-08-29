package com.pearl.manager.pojo;

import java.io.Serializable;

public class PayType implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7238137989393878680L;

	
	
	
	public PayType(String payType, double paySum, int sumNum) {
		super();
		this.payType = payType;
		this.paySum = paySum;
		this.sumNum = sumNum;
	}
	private String payType;
	private double paySum;
	private int sumNum;
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public double getPaySum() {
		return paySum;
	}
	public void setPaySum(double paySum) {
		this.paySum = paySum;
	}
	public int getSumNum() {
		return sumNum;
	}
	public void setSumNum(int sumNum) {
		this.sumNum = sumNum;
	}
	


	
}
