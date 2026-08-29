package com.pearl.o2o.pojo;



public class SysBioCharacter extends BaseMappingBean<SysBioCharacter> {
	private static final long serialVersionUID = -4319563834983556389L;
	
	private int cid;
	private int sid;
	private int type;
	private String weapons;
	private String costumes;
	
	@SuppressWarnings("unused")
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
	private int isChange;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getWeapons() {
		return weapons;
	}
	public void setWeapons(String weapons) {
		this.weapons = weapons;
	}
	public String getCostumes() {
		return costumes;
	}
	public void setCostumes(String costumes) {
		this.costumes = costumes;
	}
	public String getName() {
		return "<BC" + this.id+"^0>";
	}
	public void setName(String name) {
		this.name = name;
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
	public float getAccelSpeed() {
		return accelSpeed;
	}
	public void setAccelSpeed(float accelSpeed) {
		this.accelSpeed = accelSpeed;
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
	public String getCostumeResource() {
		return costumeResource;
	}
	public void setCostumeResource(String costumeResource) {
		this.costumeResource = costumeResource;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getExHp() {
		return exHp;
	}
	public void setExHp(int exHp) {
		this.exHp = exHp;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getDefaultLevel() {
		return defaultLevel;
	}
	public void setDefaultLevel(int defaultLevel) {
		this.defaultLevel = defaultLevel;
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
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public String getNameCN() {
		return nameCN;
	}
	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}
	

}
