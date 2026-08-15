package com.pearl.manager.pojo;

import java.io.Serializable;

public class ChargeMonth implements Serializable  {
	


	private static final long serialVersionUID = 9020179700132981140L;
	private String chargeCount;
	private double chargeNumber;
	private int sumNumber;
	public ChargeMonth(String chargeCount,double chargeNumber,int sumNumber){
		this.chargeCount=chargeCount; // xField
		this.chargeNumber=chargeNumber;// yField
		this.sumNumber=sumNumber;// percent
	}
	
	public String getChargeCount() {
		return chargeCount;
	}

	public void setChargeCount(String chargeCount) {
		this.chargeCount = chargeCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public double getChargeNumber() {
		return chargeNumber;
	}
	public void setChargeNumber(double chargeNumber) {
		this.chargeNumber = chargeNumber;
	}
	public int getSumNumber() {
		return sumNumber;
	}
	public void setSumNumber(int sumNumber) {
		this.sumNumber = sumNumber;
	}

	
}
