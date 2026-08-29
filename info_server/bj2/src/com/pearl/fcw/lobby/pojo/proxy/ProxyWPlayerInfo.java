package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WPlayerInfo;

public class ProxyWPlayerInfo extends EntityProxy<WPlayerInfo> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy xunleiPoint;
	private PropertyProxy<com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches> cachesEntity;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy xunleiPoint() {
		return xunleiPoint;
	}

	public PropertyProxy<com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches> cachesEntity() {
		return cachesEntity;
	}

	@Override
	public void initFields() {
		WPlayerInfo instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		xunleiPoint = new  IntegerProxy ("xunleiPoint", this, instance::getXunleiPoint, instance::setXunleiPoint);
		cachesEntity = new  PropertyProxy<com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches> ("cachesEntity", this, instance::getCachesEntity, instance::setCachesEntity);
	}

	public ProxyWPlayerInfo(WPlayerInfo  instance) {
		super(instance);
	}

	public ProxyWPlayerInfo(Object element, Proxy<?> owner, Supplier<WPlayerInfo> getter,  Consumer<WPlayerInfo> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerInfo(Object element, Proxy<?> owner, WPlayerInfo instance) {
		super(element, owner, instance);
	}

}