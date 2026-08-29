package com.pearl.o2o.pojo;

import java.util.Date;

public class BattleFieldRobDaily extends BaseMappingBean<BattleFieldRobDaily> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6800246107477082958L;
	private int teamId;
	private int robAmount;//抢的数量
	private int beRobAmount;//被抢的数量
	private Date robDate;//时间
	
	private int robsum;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
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
	
}
