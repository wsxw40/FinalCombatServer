package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Id;

public class TeamItem extends BasePojo {
	private static final long serialVersionUID = -5940920293448295261L;

	@Id
	private int id;
	private int teamId;
	private int itemId;
	private int currency;
	private int unitType;
	private int quantity;
	private float durable;// 耐久百分比
	private String validTime;
	private String expireTime;
	private String isBind;
	private String isDefault;
	private String isGift;
	private String modifiedDesc;
	private String isDeleted;
	private String gunProperty1;// 威力 wDamage 当前强化等级,当前经验值,属性加成%
	private String gunProperty2;// 射速 wFireTime
	private String gunProperty3;// hp wHitAcceleration
	private String gunProperty4;// 射程 WhurtRange
	private String gunProperty5;// 转速 WshowSpeed
	private String backUp;
	private int leftSeconds;
	private int level;
	private String gunProperty6;
	private String gunProperty7;
	private String gunProperty8;

	private int usedCount;// 已摆放摆放资源数
	private float bullet;// 弹药百分比
	private String startUpTime;
	private String lastBuildTime;
	private int buildStatus;

	private int timeLeft;
	private SysItem sysItem;

	private int maxQuantity;// 可以购买的上限数

	// 根据当前战队空间等级获得指定的payment,需要手动set
	private Payment currentCreatePayMent;

	public String getStartUpTime() {
		return startUpTime;
	}

	public void setStartUpTime(String startUpTime) {
		this.startUpTime = startUpTime;
	}

	public int getBuildStatus() {
		return buildStatus;
	}

	public void setBuildStatus(int buildStatus) {
		this.buildStatus = buildStatus;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setShowQuantity(int showQuantity) {
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getDurable() {
		return durable;
	}

	public void setDurable(float durable) {
		this.durable = durable;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getIsBind() {
		return isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getGunProperty1() {
		return gunProperty1;
	}

	public void setGunProperty1(String gunProperty1) {
		this.gunProperty1 = gunProperty1;
	}

	public String getGunProperty2() {
		return gunProperty2;
	}

	public void setGunProperty2(String gunProperty2) {
		this.gunProperty2 = gunProperty2;
	}

	public String getGunProperty3() {
		return gunProperty3;
	}

	public void setGunProperty3(String gunProperty3) {
		this.gunProperty3 = gunProperty3;
	}

	public String getGunProperty4() {
		return gunProperty4;
	}

	public void setGunProperty4(String gunProperty4) {
		this.gunProperty4 = gunProperty4;
	}

	public String getGunProperty5() {
		return gunProperty5;
	}

	public void setGunProperty5(String gunProperty5) {
		this.gunProperty5 = gunProperty5;
	}

	public String getBackUp() {
		return backUp;
	}

	public void setBackUp(String backUp) {
		this.backUp = backUp;
	}

	public int getLeftSeconds() {
		return leftSeconds;
	}

	public void setLeftSeconds(int leftSeconds) {
		this.leftSeconds = leftSeconds;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getGunProperty6() {
		return gunProperty6;
	}

	public void setGunProperty6(String gunProperty6) {
		this.gunProperty6 = gunProperty6;
	}

	public String getGunProperty7() {
		return gunProperty7;
	}

	public void setGunProperty7(String gunProperty7) {
		this.gunProperty7 = gunProperty7;
	}

	public String getGunProperty8() {
		return gunProperty8;
	}

	public void setGunProperty8(String gunProperty8) {
		this.gunProperty8 = gunProperty8;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(int usedCount) {
		this.usedCount = usedCount;
	}

	public SysItem getSysItem() {
		return sysItem;
	}

	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}

	public float getBullet() {
		return bullet;
	}

	public void setBullet(float bullet) {
		this.bullet = bullet;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public String getModifiedDesc() {
		return modifiedDesc;
	}

	public void setModifiedDesc(String modifiedDesc) {
		this.modifiedDesc = modifiedDesc;
	}

	public String getLastBuildTime() {
		return lastBuildTime;
	}

	public void setLastBuildTime(String lastBuildTime) {
		this.lastBuildTime = lastBuildTime;
	}

	public String getIntensifyInfo(int type) {
		String property = "";
		switch (type) {
		case 1:
			property = gunProperty1;
			break;
		case 2:
			property = gunProperty2;
			break;
		case 3:
			property = gunProperty3;
			break;
		case 4:
			property = gunProperty4;
			break;
		case 5:
			property = gunProperty5;
			break;
		}
		return property;
	}

	public void setIntensifyInfo(int type, String property) {
		switch (type) {
		case 1:
			gunProperty1 = property;
			break;
		case 2:
			gunProperty2 = property;
			break;
		case 3:
			gunProperty3 = property;
			break;
		case 4:
			gunProperty4 = property;
			break;
		case 5:
			gunProperty5 = property;
			break;
		}
	}


	public void setBuyCD(long buyCD) {
	}

	public Payment getCurrentCreatePayMent() {
		return currentCreatePayMent;
	}

	public void setCurrentCreatePayMent(Payment currentCreatePayMent) {
		this.currentCreatePayMent = currentCreatePayMent;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id=id;
	}
}
