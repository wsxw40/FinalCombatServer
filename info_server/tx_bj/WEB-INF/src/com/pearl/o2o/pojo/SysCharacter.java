package com.pearl.o2o.pojo;

import java.util.Date;


public class SysCharacter extends BaseMappingBean<SysCharacter> {

	private static final long serialVersionUID = -5016463533351202384L;
	private String name;
	private String nameCN;
	private float runSpeed;
	private float walkSpeed;
	private float crouchSpeed;
	private float accelSpeed=8f;
	private float jumpSpeed;
	private float throwSpeed;
	private String costumeResource;
	private String isDefault;
	private int maxHp=100;
	private int exHp=100;
	private int cost=100;
	private int defaultLevel=1;
	private int isFired=1;
	private String resourceName;
	private int isEnable=1;
	private float controllerHeight;
	private float controllerRadius;
	private float controllerCrouchHeight;
	private int scoreOffset;
	private int defaultAchievement=1;
	//not exist in database
	private int isChange;
	
	private String[] costume;//resource array
	//log
	private int logVersion;
	private Date logTime;
	private int logId;
	
	public SysCharacter() {
	}
	public SysCharacter(int id,String name, float runSpeed, float jumpSpeed, float throwSpeed, String costumeResource,
			String isDefault, int maxHp, int exHp, int cost, int defaultLevel, int isFired, String resourceName, int isEnable, float controllerHeight,
			float controllerRadius, float controllerCrouchHeight, int scoreOffset) {
		this.id = id;
		this.name = name;
		this.runSpeed = runSpeed;
		this.walkSpeed = runSpeed;
		this.crouchSpeed = runSpeed;
		this.accelSpeed = runSpeed;
		this.jumpSpeed = jumpSpeed;
		this.throwSpeed = throwSpeed;
		this.costumeResource = costumeResource;
		this.isDefault = isDefault;
		this.maxHp = maxHp;
		this.exHp = exHp;
		this.cost = cost;
		this.defaultLevel = defaultLevel;
		this.isFired = isFired;
		this.resourceName = resourceName;
		this.isEnable = isEnable;
		this.controllerHeight = controllerHeight;
		this.controllerRadius = controllerRadius;
		this.controllerCrouchHeight = controllerCrouchHeight;
		this.scoreOffset = scoreOffset;
	}
	public float getRunSpeed() {
		return runSpeed;
	}
	public void setRunSpeed(float runSpeed) {
		this.runSpeed = runSpeed;
	}
	public float getWalkSpeed() {
		return walkSpeed;
	}
	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	public float getCrouchSpeed() {
		return crouchSpeed;
	}
	public void setCrouchSpeed(float crouchSpeed) {
		this.crouchSpeed = crouchSpeed;
	}
	public float getJumpSpeed() {
		return jumpSpeed;
	}
	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}
	public float getThrowSpeed() {
		return throwSpeed;
	}
	public void setThrowSpeed(float throwSpeed) {
		this.throwSpeed = throwSpeed;
	}
	
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public int getLogVersion() {
		return logVersion;
	}
	public void setLogVersion(int logVersion) {
		this.logVersion = logVersion;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getCostumeResource() {
		return costumeResource;
	}
	public void setCostumeResource(String costumeResource) {
		this.costumeResource = costumeResource;
	}
	public Integer getDefaultLevel() {
		return defaultLevel;
	}
	public void setDefaultLevel(Integer defaultLevel) {
		this.defaultLevel = defaultLevel;
	}
	public Integer getDefaultAchievement() {
		return defaultAchievement;
	}
	public void setDefaultAchievement(Integer defaultAchievement) {
		this.defaultAchievement = defaultAchievement;
	}
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public float getAccelSpeed() {
		return accelSpeed;
	}

	public void setAccelSpeed(float accelSpeed) {
		this.accelSpeed = accelSpeed;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getName() {
		if("BOSS".equals(this.nameCN)){
			return this.name;
		}
		return "<SC" + this.getId()+"^0>";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCostume() {
		return costume;
	}
	public void setCostume(String[] costume) {
		this.costume = costume;
	}
	public Integer getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}
	public int getExHp() {
		return exHp;
	}
	public void setExHp(int exHp) {
		this.exHp = exHp;
	}
	public int getIsFired() {
		return isFired;
	}
	public void setIsFired(int isFired) {
		this.isFired = isFired;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	public float getControllerHeight() {
		return controllerHeight;
	}
	public void setControllerHeight(float controllerHeight) {
		this.controllerHeight = controllerHeight;
	}
	public float getControllerRadius() {
		return controllerRadius;
	}
	public void setControllerRadius(float controllerRadius) {
		this.controllerRadius = controllerRadius;
	}
	public float getControllerCrouchHeight() {
		return controllerCrouchHeight;
	}
	public void setControllerCrouchHeight(float controllerCrouchHeight) {
		this.controllerCrouchHeight = controllerCrouchHeight;
	}
	public int getScoreOffset() {
		return scoreOffset;
	}
	public void setScoreOffset(int scoreOffset) {
		this.scoreOffset = scoreOffset;
	}
	public String getNameCN() {
		return nameCN;
	}
	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
}
