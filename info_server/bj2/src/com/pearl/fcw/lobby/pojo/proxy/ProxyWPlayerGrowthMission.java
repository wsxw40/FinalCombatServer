package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerGrowthMission;

public class ProxyWPlayerGrowthMission extends EntityProxy<WPlayerGrowthMission> {
	private IntegerProxy number;
	private PropertyProxy<java.lang.Boolean> status;
	private PropertyProxy<java.lang.Boolean> received;
	private PropertyProxy<java.lang.String> backUp;
	private IntegerProxy id;
	private IntegerProxy playerId;

	public IntegerProxy number() {
		return number;
	}

	public PropertyProxy<java.lang.Boolean> status() {
		return status;
	}

	public PropertyProxy<java.lang.Boolean> received() {
		return received;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	@Override
	public void initFields() {
		WPlayerGrowthMission instance = get();
		number = new  IntegerProxy ("number", this, instance::getNumber, instance::setNumber);
		status = new  PropertyProxy<java.lang.Boolean> ("status", this, instance::getStatus, instance::setStatus);
		received = new  PropertyProxy<java.lang.Boolean> ("received", this, instance::getReceived, instance::setReceived);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
	}

	public ProxyWPlayerGrowthMission(WPlayerGrowthMission  instance) {
		super(instance);
	}

	public ProxyWPlayerGrowthMission(Object element, Proxy<?> owner, Supplier<WPlayerGrowthMission> getter,  Consumer<WPlayerGrowthMission> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerGrowthMission(Object element, Proxy<?> owner, WPlayerGrowthMission instance) {
		super(element, owner, instance);
	}

}