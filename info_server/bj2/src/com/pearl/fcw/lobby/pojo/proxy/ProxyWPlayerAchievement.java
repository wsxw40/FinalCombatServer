package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.ListProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WPlayerAchievement;

public class ProxyWPlayerAchievement extends EntityProxy<WPlayerAchievement> {
	private IntegerProxy id;
	private PropertyProxy<java.lang.String> sysAchievementIds;
	private IntegerProxy level;
	private IntegerProxy number;
	private IntegerProxy status;
	private IntegerProxy playerId;
	private IntegerProxy totalLevel;
	private IntegerProxy group;
	private PropertyProxy<java.lang.String> backUp;
    private ListProxy<com.pearl.fcw.lobby.pojo.ParamObject<Integer>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>> sysAchievementIdsList;

	public IntegerProxy id() {
		return id;
	}

	public PropertyProxy<java.lang.String> sysAchievementIds() {
		return sysAchievementIds;
	}

	public IntegerProxy level() {
		return level;
	}

	public IntegerProxy number() {
		return number;
	}

	public IntegerProxy status() {
		return status;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy totalLevel() {
		return totalLevel;
	}

	public IntegerProxy group() {
		return group;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

    public ListProxy<com.pearl.fcw.lobby.pojo.ParamObject<Integer>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>> sysAchievementIdsList() {
		return sysAchievementIdsList;
	}

	@Override
	public void initFields() {
		WPlayerAchievement instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		sysAchievementIds = new  PropertyProxy<java.lang.String> ("sysAchievementIds", this, instance::getSysAchievementIds, instance::setSysAchievementIds);
		level = new  IntegerProxy ("level", this, instance::getLevel, instance::setLevel);
		number = new  IntegerProxy ("number", this, instance::getNumber, instance::setNumber);
		status = new  IntegerProxy ("status", this, instance::getStatus, instance::setStatus);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		totalLevel = new  IntegerProxy ("totalLevel", this, instance::getTotalLevel, instance::setTotalLevel);
		group = new  IntegerProxy ("group", this, instance::getGroup, instance::setGroup);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
        sysAchievementIdsList = new ListProxy<com.pearl.fcw.lobby.pojo.ParamObject<Integer>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>>("sysAchievementIdsList", this,
                instance::getSysAchievementIdsList, instance::setSysAchievementIdsList, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Integer>::new);
	}

	public ProxyWPlayerAchievement(WPlayerAchievement  instance) {
		super(instance);
	}

	public ProxyWPlayerAchievement(Object element, Proxy<?> owner, Supplier<WPlayerAchievement> getter,  Consumer<WPlayerAchievement> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerAchievement(Object element, Proxy<?> owner, WPlayerAchievement instance) {
		super(element, owner, instance);
	}

}