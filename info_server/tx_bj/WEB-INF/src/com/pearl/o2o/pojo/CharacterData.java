package com.pearl.o2o.pojo;

import com.pearl.o2o.utils.DBRouteUtil;


public class CharacterData extends BaseMappingBean<CharacterData> {
	private static final long serialVersionUID = -3491449820340834800L;
	private Integer 	playerId;
	private Integer		characterId;
	private Integer		kill 			= 0;
	private	Integer		dead 			= 0;
	private	Integer		knifeKill 			= 0;
	private Integer		controlNum 	= 0;
	private	Integer		revengeNum 			= 0;
	private	Integer		assistNum 			= 0;
	private Integer     usedCount=0;
	private Integer     maxHeadshot=0;
	private Integer     maxKill=0;
	private Integer     maxHealth=0;
	private Integer     maxDamage=0;
	private Integer     maxAliveTime=0;
	private Integer     mvpNum=0;
	private Integer 	healthNum=0;
	public Integer getHealthNum() {
		return healthNum;
	}
	public void setHealthNum(Integer healthNum) {
		this.healthNum = healthNum;
	}
	public Integer getMaxHeadshot() {
		return maxHeadshot;
	}
	public void setMaxHeadshot(Integer maxHeadshot) {
		this.maxHeadshot = maxHeadshot;
	}
	public Integer getMaxKill() {
		return maxKill;
	}
	public void setMaxKill(Integer maxKill) {
		this.maxKill = maxKill;
	}
	public Integer getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(Integer maxHealth) {
		this.maxHealth = maxHealth;
	}
	public Integer getMaxDamage() {
		return maxDamage;
	}
	public void setMaxDamage(Integer maxDamage) {
		this.maxDamage = maxDamage;
	}
	public Integer getMaxAliveTime() {
		return maxAliveTime;
	}
	public void setMaxAliveTime(Integer maxAliveTime) {
		this.maxAliveTime = maxAliveTime;
	}
	public Integer getMvpNum() {
		return mvpNum;
	}
	public void setMvpNum(Integer mvpNum) {
		this.mvpNum = mvpNum;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public Integer getKill() {
		return kill;
	}
	public void setKill(Integer kill) {
		this.kill = kill;
	}
	public Integer getDead() {
		return dead;
	}
	public void setDead(Integer dead) {
		this.dead = dead;
	}
	public Integer getKnifeKill() {
		return knifeKill;
	}
	public void setKnifeKill(Integer knifeKill) {
		this.knifeKill = knifeKill;
	}
	public Integer getControlNum() {
		return controlNum;
	}
	public void setControlNum(Integer controlNum) {
		this.controlNum = controlNum;
	}
	public Integer getRevengeNum() {
		return revengeNum;
	}
	public void setRevengeNum(Integer revengeNum) {
		this.revengeNum = revengeNum;
	}
	public Integer getAssistNum() {
		return assistNum;
	}
	public void setAssistNum(Integer assistNum) {
		this.assistNum = assistNum;
	}
	public Integer getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerMission.class, playerId);
    }

}
