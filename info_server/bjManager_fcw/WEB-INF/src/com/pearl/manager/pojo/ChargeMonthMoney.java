package com.pearl.manager.pojo;

import java.io.Serializable;

public class ChargeMonthMoney implements Serializable  {
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -728892904655448164L;
	private String chargeMoney;
	private double chargeMoneySum;
	private int sumNumber;
	public ChargeMonthMoney(String chargeMoney,double chargeNumber,int sumNumber){
		this.chargeMoney=chargeMoney; // xField
		this.chargeMoneySum=chargeNumber;// yField
		this.sumNumber=sumNumber;// percent
	}
	public String getChargeMoney() {
		return chargeMoney;
	}
	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
	}
	
	public double getChargeMoneySum() {
		return chargeMoneySum;
	}
	public void setChargeMoneySum(double chargeMoneySum) {
		this.chargeMoneySum = chargeMoneySum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getSumNumber() {
		return sumNumber;
	}
	public void setSumNumber(int sumNumber) {
		this.sumNumber = sumNumber;
	}

	
}
