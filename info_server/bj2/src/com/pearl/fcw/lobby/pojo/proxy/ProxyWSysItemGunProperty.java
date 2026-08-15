package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;

public class ProxyWSysItemGunProperty extends EntityProxy<WSysItemGunProperty> {
	private IntegerProxy id;
	private IntegerProxy index;
	private IntegerProxy value;
	private IntegerProxy value2;
	private IntegerProxy time;
	private PropertyProxy<java.lang.String> isDeleted;
	private PropertyProxy<java.lang.String> multiType;
	private PropertyProxy<java.lang.String> descI18n;
	private PropertyProxy<java.lang.String> remark;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy index() {
		return index;
	}

	public IntegerProxy value() {
		return value;
	}

	public IntegerProxy value2() {
		return value2;
	}

	public IntegerProxy time() {
		return time;
	}

	public PropertyProxy<java.lang.String> isDeleted() {
		return isDeleted;
	}

	public PropertyProxy<java.lang.String> multiType() {
		return multiType;
	}

	public PropertyProxy<java.lang.String> descI18n() {
		return descI18n;
	}

	public PropertyProxy<java.lang.String> remark() {
		return remark;
	}

	@Override
	public void initFields() {
		WSysItemGunProperty instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		index = new  IntegerProxy ("index", this, instance::getIndex, instance::setIndex);
		value = new  IntegerProxy ("value", this, instance::getValue, instance::setValue);
		value2 = new  IntegerProxy ("value2", this, instance::getValue2, instance::setValue2);
		time = new  IntegerProxy ("time", this, instance::getTime, instance::setTime);
		isDeleted = new  PropertyProxy<java.lang.String> ("isDeleted", this, instance::getIsDeleted, instance::setIsDeleted);
		multiType = new  PropertyProxy<java.lang.String> ("multiType", this, instance::getMultiType, instance::setMultiType);
		descI18n = new  PropertyProxy<java.lang.String> ("descI18n", this, instance::getDescI18n, instance::setDescI18n);
		remark = new  PropertyProxy<java.lang.String> ("remark", this, instance::getRemark, instance::setRemark);
	}

	public ProxyWSysItemGunProperty(WSysItemGunProperty  instance) {
		super(instance);
	}

	public ProxyWSysItemGunProperty(Object element, Proxy<?> owner, Supplier<WSysItemGunProperty> getter,  Consumer<WSysItemGunProperty> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWSysItemGunProperty(Object element, Proxy<?> owner, WSysItemGunProperty instance) {
		super(element, owner, instance);
	}

}