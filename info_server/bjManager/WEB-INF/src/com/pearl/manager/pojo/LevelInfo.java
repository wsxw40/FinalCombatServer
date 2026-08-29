package com.pearl.manager.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;



public class LevelInfo extends BaseMappingBean<LevelInfo> implements Comparable<LevelInfo>{

	private static final long serialVersionUID = -3700162506515280178L;
	private int type;
	private String name;	
	private int index;
	private int isNew;
	private String startPoints;
	private String blastPoints;
	private String flagPoints;
	private String weaponPoints;
	private String supplyPoints;
	private String supplies;
	private int isSelf;
	private String displayName;
	private String description;
	private String zombieInfo;
	private Float levelHorizon;
	private Float targetSpeed;
	private String lineInfo;
	private String vehicleInfo;
	private int bloodOffset;
	private int isRushGold;
	private int isMoneyReward;
	private String rushGlodPoint;
	private int isVip;
	//not in database
	private int isChange;
	
	private float expAdd;
	private float gpAdd;
	private Date activityStart;
	private Date activityEnd;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getStartPoints() {
		return startPoints;
	}
	public void setStartPoints(String startPoints) {
		this.startPoints = startPoints;
	}
	public String getBlastPoints() {
		return blastPoints;
	}
	public void setBlastPoints(String blastPoints) {
		this.blastPoints = blastPoints;
	}
	public String getFlagPoints() {
		return flagPoints;
	}
	public void setFlagPoints(String flagPoints) {
		this.flagPoints = flagPoints;
	}
	public String getWeaponPoints() {
		return weaponPoints;
	}
	public void setWeaponPoints(String weaponPoints) {
		this.weaponPoints = weaponPoints;
	}
	public String getSupplyPoints() {
		return supplyPoints;
	}
	public void setSupplyPoints(String supplyPoints) {
		this.supplyPoints = supplyPoints;
	}
	public String getSupplies() {
		return supplies;
	}
	public void setSupplies(String supplies) {
		this.supplies = supplies;
	}
	
	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public int getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(int isSelf) {
		this.isSelf = isSelf;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getZombieInfo() {
		return zombieInfo;
	}
	public void setZombieInfo(String zombieInfo) {
		this.zombieInfo = zombieInfo;
	}
	public Float getLevelHorizon() {
		return levelHorizon;
	}
	public void setLevelHorizon(Float levelHorizon) {
		this.levelHorizon = levelHorizon;
	}
	public String getLineInfo() {
		return lineInfo;
	}
	public void setLineInfo(String lineInfo) {
		this.lineInfo = lineInfo;
	}
	public String getVehicleInfo() {
		return vehicleInfo;
	}
	public void setVehicleInfo(String vehicleInfo) {
		this.vehicleInfo = vehicleInfo;
	}
	public Float getTargetSpeed() {
		return targetSpeed;
	}
	public void setTargetSpeed(Float targetSpeed) {
		this.targetSpeed = targetSpeed;
	}
	public int getBloodOffset() {
		return bloodOffset;
	}
	public void setBloodOffset(int bloodOffset) {
		this.bloodOffset = bloodOffset;
	}
	public int getIsRushGold() {
		return isRushGold;
	}
	public void setIsRushGold(int isRushGold) {
		this.isRushGold = isRushGold;
	}
	public int getIsMoneyReward() {
		return isMoneyReward;
	}
	public void setIsMoneyReward(int isMoneyReward) {
		this.isMoneyReward = isMoneyReward;
	}
	public String getRushGlodPoint() {
		return rushGlodPoint;
	}
	public void setRushGlodPoint(String rushGlodPoint) {
		this.rushGlodPoint = rushGlodPoint;
	}
	public int getIsVip() {
		return isVip;
	}
	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}	
	public float getExpAdd() {
		return expAdd;
	}
	public void setExpAdd(float expAdd) {
		this.expAdd = expAdd;
	}
	public float getGpAdd() {
		return gpAdd;
	}
	public void setGpAdd(float gpAdd) {
		this.gpAdd = gpAdd;
	}
	public Date getActivityStart() {
		return activityStart;
	}
	public void setActivityStart(Date activityStart) {
		this.activityStart = activityStart;
	}
	public Date getActivityEnd() {
		return activityEnd;
	}
	public void setActivityEnd(Date activityEnd) {
		this.activityEnd = activityEnd;
	}
	public int compareTo(LevelInfo o) {
		int result = o.getIndex() - this.getIndex();
		if(result == 0){
			result = this.getId() - o.getId();
		}
		return result;
	}
	
}
