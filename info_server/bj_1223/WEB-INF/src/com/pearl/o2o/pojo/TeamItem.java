package com.pearl.o2o.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.MathOperator;

public class TeamItem extends BaseMappingBean<TeamItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5940920293448295261L;
	private int teamId;
	private int itemId;
	private int currency;
	private int unitType;
	private int quantity;
	private int showQuantity;
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

	/** 购买物品的CD时间 */
	private long buyCD;

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

	public int getShowQuantity() {
		try {
			if (getBuyCD() > 0) {
				this.showQuantity = quantity - 1 < 0 ? 0 : quantity - 1;
			} else {
				this.showQuantity = quantity;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return this.showQuantity;
	}

	public void setShowQuantity(int showQuantity) {
		this.showQuantity = showQuantity;
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

	public Map<String, Number> getProperty1() throws NumberFormatException,
			Exception {
		return ItemIntensifyUtil.getPropertyBasic(gunProperty1, ItemIntensifyUtil.TeamItemPropertyType.POWER, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWDamage());
	}

	public Map<String, Number> getProperty2() throws NumberFormatException,
			Exception {
		return ItemIntensifyUtil.getPropertyBasic(gunProperty2, ItemIntensifyUtil.TeamItemPropertyType.FIRE_SPEED, MathOperator.DIV,this.getLevel(),sysItem,sysItem.getWFireTime());
	}

	public Map<String, Number> getProperty3() throws NumberFormatException,
			Exception {
		return ItemIntensifyUtil.getPropertyBasic(gunProperty3, ItemIntensifyUtil.TeamItemPropertyType.HP, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWHitAcceleration());
	}

	public Map<String, Number> getProperty4() throws NumberFormatException,
			Exception {
		return ItemIntensifyUtil.getPropertyBasic(gunProperty4, ItemIntensifyUtil.TeamItemPropertyType.FIRE_DISTANCE, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWHurtRange());
	}

	public Map<String, Number> getProperty5() throws NumberFormatException,
			Exception {
		return ItemIntensifyUtil.getPropertyBasic(gunProperty5, ItemIntensifyUtil.TeamItemPropertyType.ROTATE_SPEED, MathOperator.MUTIP,this.getLevel(),sysItem,sysItem.getWShowSpeed());
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
	public int getTowerIsUpgrade() throws NumberFormatException, Exception{
		return isUpgrade(ItemIntensifyUtil.TEAM_STORAGE_TYPE.DEFENSE_TOWER
				.getValue())?1:0;
	}
	public int getOilOrWallUpgrade() throws NumberFormatException, Exception{
		
		return isUpgrade(ItemIntensifyUtil.TEAM_STORAGE_TYPE.OIL_TANK
					.getValue())?1:0;
	}
	
	public boolean isUpgrade(int subType) throws NumberFormatException,
			Exception {
		try{
			String[] p1 = gunProperty1.split(",");
			String[] p2 = gunProperty2.split(",");
			String[] p3 = gunProperty3.split(",");
			String[] p4 = gunProperty4.split(",");
			String[] p5 = gunProperty5.split(",");
			int maxLevel = ItemIntensifyUtil.getMaxLevel(sysItem.getSubType());
			if (subType == ItemIntensifyUtil.TEAM_STORAGE_TYPE.DEFENSE_TOWER
					.getValue()) {// 塔有5种属性
				if (maxLevel == Integer.valueOf(p1[0])
						&& maxLevel == Integer.valueOf(p2[0])
						&& maxLevel == Integer.valueOf(p3[0])
						&& maxLevel == Integer.valueOf(p4[0])
						&& maxLevel == Integer.valueOf(p5[0])) {
					return true;
				}
			} else if (subType == ItemIntensifyUtil.TEAM_STORAGE_TYPE.OIL_TANK
					.getValue()
					|| subType == ItemIntensifyUtil.TEAM_STORAGE_TYPE.WALL
							.getValue()) {// 墙和油罐只有一种属性
				if (maxLevel == Integer.valueOf(p3[0])) {// 血量
					return true;
				}
			}
			return false;
		}catch (ArrayIndexOutOfBoundsException e) {
			
			return false;
		}
	}

	public long getBuyCD() throws ParseException {
		long result = 0l;
		if (sysItem.getTimeForCreate() != 0 && this.getLastBuildTime() != null
				&& !"".equals(this.getLastBuildTime())) {
			Date dLastCreateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(this.getLastBuildTime());
			long past = System.currentTimeMillis() - dLastCreateTime.getTime();
			past = past < 0 ? 0 : past;
			result = sysItem.getTimeForCreateMsec() - past;
		}

		buyCD = result < 0 ? 0 : result;
		return buyCD;
	}

	public void setBuyCD(long buyCD) {
		this.buyCD = buyCD;
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

	public int getRepairPrice() {
		return Math.round((100 - durable)
				* ItemIntensifyUtil.TEAM_ITEM_REPAIR_PRICE);
	}

	public int getSupplyPrice() {
		return Math.round((100 - bullet)
				* ItemIntensifyUtil.TEAM_ITEM_SUPPLY_PRICE);
	}
}
