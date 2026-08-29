package com.pearl.o2o.pojo;

import java.util.Date;

public class BattleFieldRobDaily extends BaseMappingBean<BattleFieldRobDaily> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6800246107477082958L;
	private int attTeamId;
	private int defTeamId;
	private int robAmount;//抢的数量
	private int beRobAmount;//被抢的数量
	private Date robDate;//时间
	private int type;//1匹配 2挑战
	
	private int robsum;
	
	private int robcount;//抢别人的次数
	
	private int beRobcount;//被抢的次数
	
	public int getRobAmount() {
		return robAmount;
	}
	public void setRobAmount(int robAmount) {
		this.robAmount = robAmount;
	}
	public int getBeRobAmount() {
		return beRobAmount;
	}
	public void setBeRobAmount(int beRobAmount) {
		this.beRobAmount = beRobAmount;
	}
	

	public Date getRobDate() {
		return robDate;
	}
	public void setRobDate(Date robDate) {
		this.robDate = robDate;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public int getRobsum() {
		return robsum;
	}
	public void setRobsum(int robsum) {
		this.robsum = robsum;
	}
	public int getRobcount() {
		return robcount;
	}
	public void setRobcount(int robcount) {
		this.robcount = robcount;
	}
	public int getBeRobcount() {
		return beRobcount;
	}
	public void setBeRobcount(int beRobcount) {
		this.beRobcount = beRobcount;
	}
	public int getAttTeamId() {
		return attTeamId;
	}
	public void setAttTeamId(int attTeamId) {
		this.attTeamId = attTeamId;
	}
	public int getDefTeamId() {
		return defTeamId;
	}
	public void setDefTeamId(int defTeamId) {
		this.defTeamId = defTeamId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
