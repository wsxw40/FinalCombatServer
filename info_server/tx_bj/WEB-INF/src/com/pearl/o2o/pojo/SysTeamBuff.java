package com.pearl.o2o.pojo;



public class SysTeamBuff extends BaseMappingBean<SysTeamBuff> {

	/**
	 * eclipse 自动生成的
	 */
	
	private static final long serialVersionUID = -8526877189256221917L;
	
	private int rank;
	private int sysItemId;
	private int unit;
	private int unitType;
	private boolean isDelete;
	private boolean isEnable;
	
	private int payType;
	private int cost;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getSysItemId() {
		return sysItemId;
	}
	public void setSysItemId(int sysItemId) {
		this.sysItemId = sysItemId;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getUnitType() {
		return unitType;
	}
	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public boolean isEnable() {
		return isEnable;
	}
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
