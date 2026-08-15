package com.pearl.o2o.pojo;

import com.pearl.o2o.utils.DBRouteUtil;

public class PlayerAchievement0 extends BaseMappingBean<PlayerAchievement0>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8248904266142657983L;
	private int playerId;
	private String sysAchievementIds;
	private int level;
	private int number;
	private int status;
	private int group;
	private int totalLevel;
	public int getTotalLevel() {
		return totalLevel;
	}
	public void setTotalLevel(int totalLevel) {
		this.totalLevel = totalLevel;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getSysAchievementIds() {
		return sysAchievementIds;
	}
	public void setSysAchievementIds(String sysAchievementIds) {
		this.sysAchievementIds = sysAchievementIds;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerAchievement0.class, playerId);
    }
}
