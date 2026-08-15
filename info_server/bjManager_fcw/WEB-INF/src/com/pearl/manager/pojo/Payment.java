package com.pearl.manager.pojo;

import com.pearl.manager.utils.Constants;

public class Payment extends BaseMappingBean<Payment> {
	private static final long serialVersionUID = -6238441255585643881L;
	private int forever=-1;
	public int itemId;
	public int payType;//1 c币 ， 2 FC点
	public int unitType;//1 个数 ， 2 时间,0永久
	public int cost;
	public int unit;// 数量/时限
	public int isShow=1;
	public String priceStr;
	public int isChange = 1;
	public void init(){
		switch (payType) {
		case 1://gp
			if(unitType==0){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+forever;
			}else if(unitType==1){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}else if(unitType==2){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}
			break;
		case 2://cr
			if(unitType==0){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}else if(unitType==1){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}else if(unitType==2){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}
			break;
		case 3://voucher
			if(unitType==0){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}else if(unitType==1){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}else if(unitType==2){
				priceStr=cost+Constants.DELIMITER_RESOURCE_STABLE+unit;
			}
			break;	
		default:
			break;
		}
	}
	public Payment() {
	}
	public Payment(int unit,int unitType) {
		this.unit=unit;
		this.unitType=unitType;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getUnitType() {
		return unitType;
	}
	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public String getPriceStr() {
		return priceStr;
	}
	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
}
