package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WTeamItem;

public class ProxyWTeamItem extends EntityProxy<WTeamItem> {
	private IntegerProxy id;
	private IntegerProxy teamId;
	private IntegerProxy itemId;
	private ByteProxy currency;
	private ByteProxy unitType;
	private ByteProxy quantity;
	private PropertyProxy<java.lang.Float> durable;
	private DateProxy<java.util.Date> validTime;
	private DateProxy<java.util.Date> expireTime;
	private PropertyProxy<java.lang.String> isBind;
	private PropertyProxy<java.lang.String> isDefault;
	private PropertyProxy<java.lang.String> isGift;
	private PropertyProxy<java.lang.String> modifiedDesc;
	private PropertyProxy<java.lang.String> isDeleted;
	private PropertyProxy<java.lang.String> gunProperty1;
	private PropertyProxy<java.lang.String> gunProperty2;
	private PropertyProxy<java.lang.String> gunProperty3;
	private PropertyProxy<java.lang.String> gunProperty4;
	private PropertyProxy<java.lang.String> gunProperty5;
	private PropertyProxy<java.lang.String> backUp;
	private IntegerProxy leftSeconds;
	private IntegerProxy level;
	private PropertyProxy<java.lang.String> gunProperty6;
	private PropertyProxy<java.lang.String> gunProperty7;
	private PropertyProxy<java.lang.String> gunProperty8;
	private IntegerProxy usedCount;
	private PropertyProxy<java.lang.Float> bullet;
	private DateProxy<java.util.Date> startUpTime;
	private DateProxy<java.util.Date> lastBuildTime;
	private IntegerProxy buildStatus;
	private PropertyProxy<java.lang.Float> gunProperty1Value;
	private PropertyProxy<java.lang.Float> gunProperty2Value;
	private PropertyProxy<java.lang.Float> gunProperty3Value;
	private PropertyProxy<java.lang.Float> gunProperty4Value;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy teamId() {
		return teamId;
	}

	public IntegerProxy itemId() {
		return itemId;
	}

	public ByteProxy currency() {
		return currency;
	}

	public ByteProxy unitType() {
		return unitType;
	}

	public ByteProxy quantity() {
		return quantity;
	}

	public PropertyProxy<java.lang.Float> durable() {
		return durable;
	}

	public DateProxy<java.util.Date> validTime() {
		return validTime;
	}

	public DateProxy<java.util.Date> expireTime() {
		return expireTime;
	}

	public PropertyProxy<java.lang.String> isBind() {
		return isBind;
	}

	public PropertyProxy<java.lang.String> isDefault() {
		return isDefault;
	}

	public PropertyProxy<java.lang.String> isGift() {
		return isGift;
	}

	public PropertyProxy<java.lang.String> modifiedDesc() {
		return modifiedDesc;
	}

	public PropertyProxy<java.lang.String> isDeleted() {
		return isDeleted;
	}

	public PropertyProxy<java.lang.String> gunProperty1() {
		return gunProperty1;
	}

	public PropertyProxy<java.lang.String> gunProperty2() {
		return gunProperty2;
	}

	public PropertyProxy<java.lang.String> gunProperty3() {
		return gunProperty3;
	}

	public PropertyProxy<java.lang.String> gunProperty4() {
		return gunProperty4;
	}

	public PropertyProxy<java.lang.String> gunProperty5() {
		return gunProperty5;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

	public IntegerProxy leftSeconds() {
		return leftSeconds;
	}

	public IntegerProxy level() {
		return level;
	}

	public PropertyProxy<java.lang.String> gunProperty6() {
		return gunProperty6;
	}

	public PropertyProxy<java.lang.String> gunProperty7() {
		return gunProperty7;
	}

	public PropertyProxy<java.lang.String> gunProperty8() {
		return gunProperty8;
	}

	public IntegerProxy usedCount() {
		return usedCount;
	}

	public PropertyProxy<java.lang.Float> bullet() {
		return bullet;
	}

	public DateProxy<java.util.Date> startUpTime() {
		return startUpTime;
	}

	public DateProxy<java.util.Date> lastBuildTime() {
		return lastBuildTime;
	}

	public IntegerProxy buildStatus() {
		return buildStatus;
	}

	public PropertyProxy<java.lang.Float> gunProperty1Value() {
		return gunProperty1Value;
	}

	public PropertyProxy<java.lang.Float> gunProperty2Value() {
		return gunProperty2Value;
	}

	public PropertyProxy<java.lang.Float> gunProperty3Value() {
		return gunProperty3Value;
	}

	public PropertyProxy<java.lang.Float> gunProperty4Value() {
		return gunProperty4Value;
	}

	@Override
	public void initFields() {
		WTeamItem instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		teamId = new  IntegerProxy ("teamId", this, instance::getTeamId, instance::setTeamId);
		itemId = new  IntegerProxy ("itemId", this, instance::getItemId, instance::setItemId);
		currency = new  ByteProxy ("currency", this, instance::getCurrency, instance::setCurrency);
		unitType = new  ByteProxy ("unitType", this, instance::getUnitType, instance::setUnitType);
		quantity = new  ByteProxy ("quantity", this, instance::getQuantity, instance::setQuantity);
		durable = new  PropertyProxy<java.lang.Float> ("durable", this, instance::getDurable, instance::setDurable);
		validTime = new  DateProxy<java.util.Date> ("validTime", this, instance::getValidTime, instance::setValidTime);
		expireTime = new  DateProxy<java.util.Date> ("expireTime", this, instance::getExpireTime, instance::setExpireTime);
		isBind = new  PropertyProxy<java.lang.String> ("isBind", this, instance::getIsBind, instance::setIsBind);
		isDefault = new  PropertyProxy<java.lang.String> ("isDefault", this, instance::getIsDefault, instance::setIsDefault);
		isGift = new  PropertyProxy<java.lang.String> ("isGift", this, instance::getIsGift, instance::setIsGift);
		modifiedDesc = new  PropertyProxy<java.lang.String> ("modifiedDesc", this, instance::getModifiedDesc, instance::setModifiedDesc);
		isDeleted = new  PropertyProxy<java.lang.String> ("isDeleted", this, instance::getIsDeleted, instance::setIsDeleted);
		gunProperty1 = new  PropertyProxy<java.lang.String> ("gunProperty1", this, instance::getGunProperty1, instance::setGunProperty1);
		gunProperty2 = new  PropertyProxy<java.lang.String> ("gunProperty2", this, instance::getGunProperty2, instance::setGunProperty2);
		gunProperty3 = new  PropertyProxy<java.lang.String> ("gunProperty3", this, instance::getGunProperty3, instance::setGunProperty3);
		gunProperty4 = new  PropertyProxy<java.lang.String> ("gunProperty4", this, instance::getGunProperty4, instance::setGunProperty4);
		gunProperty5 = new  PropertyProxy<java.lang.String> ("gunProperty5", this, instance::getGunProperty5, instance::setGunProperty5);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
		leftSeconds = new  IntegerProxy ("leftSeconds", this, instance::getLeftSeconds, instance::setLeftSeconds);
		level = new  IntegerProxy ("level", this, instance::getLevel, instance::setLevel);
		gunProperty6 = new  PropertyProxy<java.lang.String> ("gunProperty6", this, instance::getGunProperty6, instance::setGunProperty6);
		gunProperty7 = new  PropertyProxy<java.lang.String> ("gunProperty7", this, instance::getGunProperty7, instance::setGunProperty7);
		gunProperty8 = new  PropertyProxy<java.lang.String> ("gunProperty8", this, instance::getGunProperty8, instance::setGunProperty8);
		usedCount = new  IntegerProxy ("usedCount", this, instance::getUsedCount, instance::setUsedCount);
		bullet = new  PropertyProxy<java.lang.Float> ("bullet", this, instance::getBullet, instance::setBullet);
		startUpTime = new  DateProxy<java.util.Date> ("startUpTime", this, instance::getStartUpTime, instance::setStartUpTime);
		lastBuildTime = new  DateProxy<java.util.Date> ("lastBuildTime", this, instance::getLastBuildTime, instance::setLastBuildTime);
		buildStatus = new  IntegerProxy ("buildStatus", this, instance::getBuildStatus, instance::setBuildStatus);
		gunProperty1Value = new  PropertyProxy<java.lang.Float> ("gunProperty1Value", this, instance::getGunProperty1Value, instance::setGunProperty1Value);
		gunProperty2Value = new  PropertyProxy<java.lang.Float> ("gunProperty2Value", this, instance::getGunProperty2Value, instance::setGunProperty2Value);
		gunProperty3Value = new  PropertyProxy<java.lang.Float> ("gunProperty3Value", this, instance::getGunProperty3Value, instance::setGunProperty3Value);
		gunProperty4Value = new  PropertyProxy<java.lang.Float> ("gunProperty4Value", this, instance::getGunProperty4Value, instance::setGunProperty4Value);
	}

	public ProxyWTeamItem(WTeamItem  instance) {
		super(instance);
	}

	public ProxyWTeamItem(Object element, Proxy<?> owner, Supplier<WTeamItem> getter,  Consumer<WTeamItem> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWTeamItem(Object element, Proxy<?> owner, WTeamItem instance) {
		super(element, owner, instance);
	}

}