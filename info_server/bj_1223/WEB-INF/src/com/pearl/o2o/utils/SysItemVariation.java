package com.pearl.o2o.utils;

/**
 * 基于sysitem的变种，基于sysitem的payment和玩家一段时间内购买的次数，决定实际价格的类
 * 
 * @author zhang.li
 * 
 */
public abstract class SysItemVariation {
	protected int id;// sysitem.id
	protected int baseCost;// sysitem中指定的一个payment的价格
	protected int count;//已经购买的次数
	protected String iValue;
	protected int iid;//iid值
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(int baseCost) {
		this.baseCost = baseCost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getIValue() {
		return iValue;
	}

	public void setIValue(String value) {
		iValue = value;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	/**
	 * 基于购买次数，获得物品本次的价格
	 * 
	 * @param count
	 * @return
	 */
	public abstract int getThisCalCost();

	/**
	 * 基于购买次数，获得下一次的价格
	 * 
	 * @return
	 */
	public abstract int getNextCalCost();

	/**
	 * 基数方程式的结果
	 * 
	 * @return
	 */
	protected  abstract int equationResult(int count);
	
	
}
