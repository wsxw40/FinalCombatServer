package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerBuff;

public class ProxyWPlayerBuff extends EntityProxy<WPlayerBuff> {
	private IntegerProxy id;
	private IntegerProxy userId;
	private IntegerProxy playerId;
	private IntegerProxy playerItemId;
	private PropertyProxy<java.lang.Short> buffId;
	private PropertyProxy<java.lang.String> backUp;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy userId() {
		return userId;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy playerItemId() {
		return playerItemId;
	}

	public PropertyProxy<java.lang.Short> buffId() {
		return buffId;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

	@Override
	public void initFields() {
		WPlayerBuff instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		userId = new  IntegerProxy ("userId", this, instance::getUserId, instance::setUserId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		playerItemId = new  IntegerProxy ("playerItemId", this, instance::getPlayerItemId, instance::setPlayerItemId);
		buffId = new  PropertyProxy<java.lang.Short> ("buffId", this, instance::getBuffId, instance::setBuffId);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
	}

	public ProxyWPlayerBuff(WPlayerBuff  instance) {
		super(instance);
	}

	public ProxyWPlayerBuff(Object element, Proxy<?> owner, Supplier<WPlayerBuff> getter,  Consumer<WPlayerBuff> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerBuff(Object element, Proxy<?> owner, WPlayerBuff instance) {
		super(element, owner, instance);
	}

}