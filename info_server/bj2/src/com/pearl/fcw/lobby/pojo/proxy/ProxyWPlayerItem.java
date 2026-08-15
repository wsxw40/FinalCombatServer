package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.ByteProxy;
import com.pearl.fcw.core.pojo.proxy.DateProxy;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.FloatProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.MapProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WPlayerItem;

public class ProxyWPlayerItem extends EntityProxy<WPlayerItem> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy itemId;
	private ByteProxy currency;
	private ByteProxy unitType;
	private IntegerProxy quantity;
	private FloatProxy durable;
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
	private IntegerProxy gstLevel;
	private IntegerProxy gstLevelExp;
	private PropertyProxy<java.lang.String> gunProperty;
	private MapProxy<String, ItemGunProperty, ProxyItemGunProperty> gunPropertyMap;
	private MapProxy<String, ParamObject<Number>, ProxyParamObject<Number>> numberParamMap;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
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

	public IntegerProxy quantity() {
		return quantity;
	}

	public FloatProxy durable() {
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

	public IntegerProxy gstLevel() {
		return gstLevel;
	}

	public IntegerProxy gstLevelExp() {
		return gstLevelExp;
	}

	public PropertyProxy<java.lang.String> gunProperty() {
		return gunProperty;
	}

	public MapProxy<String, ItemGunProperty, ProxyItemGunProperty> gunPropertyMap() {
		return gunPropertyMap;
	}

	public MapProxy<String, ParamObject<Number>, ProxyParamObject<Number>> numberParamMap() {
		return numberParamMap;
	}

	@Override
	public void initFields() {
		WPlayerItem instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		itemId = new  IntegerProxy ("itemId", this, instance::getItemId, instance::setItemId);
		currency = new  ByteProxy ("currency", this, instance::getCurrency, instance::setCurrency);
		unitType = new  ByteProxy ("unitType", this, instance::getUnitType, instance::setUnitType);
		quantity = new  IntegerProxy ("quantity", this, instance::getQuantity, instance::setQuantity);
		durable = new  FloatProxy ("durable", this, instance::getDurable, instance::setDurable);
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
		gstLevel = new  IntegerProxy ("gstLevel", this, instance::getGstLevel, instance::setGstLevel);
		gstLevelExp = new  IntegerProxy ("gstLevelExp", this, instance::getGstLevelExp, instance::setGstLevelExp);
		gunProperty = new  PropertyProxy<java.lang.String> ("gunProperty", this, instance::getGunProperty, instance::setGunProperty);
		gunPropertyMap = new MapProxy<String, ItemGunProperty, ProxyItemGunProperty>("gunPropertyMap", this, instance::getGunPropertyMap, instance::setGunPropertyMap, ProxyItemGunProperty::new);
		numberParamMap = new MapProxy<String, ParamObject<Number>, ProxyParamObject<Number>>("numberParamMap", this, instance::getNumberParamMap, instance::setNumberParamMap,
				ProxyParamObject<Number>::new);
	}

	public ProxyWPlayerItem(WPlayerItem  instance) {
		super(instance);
	}

	public ProxyWPlayerItem(Object element, Proxy<?> owner, Supplier<WPlayerItem> getter,  Consumer<WPlayerItem> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerItem(Object element, Proxy<?> owner, WPlayerItem instance) {
		super(element, owner, instance);
	}

}