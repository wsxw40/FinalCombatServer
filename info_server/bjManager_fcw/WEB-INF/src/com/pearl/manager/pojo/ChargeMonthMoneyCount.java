package com.pearl.manager.pojo;

import java.io.Serializable;

public class ChargeMonthMoneyCount implements Serializable  {
	


	
	/**
	 * 
	 */

	private String chargeMoney;
	private double chargeNumber;
	private int sumNumber;
	public ChargeMonthMoneyCount(String chargeMoney,double chargeNumber,int sumNumber){
		this.chargeMoney=chargeMoney; // xField
		this.chargeNumber=chargeNumber;// yField
		this.sumNumber=sumNumber;// percent
	}
	public String getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
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
