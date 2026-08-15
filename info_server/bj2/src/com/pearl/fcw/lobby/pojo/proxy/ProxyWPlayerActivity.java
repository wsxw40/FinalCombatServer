package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerActivity;

public class ProxyWPlayerActivity extends EntityProxy<WPlayerActivity> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy action;
	private IntegerProxy status;
	private IntegerProxy number;
	private IntegerProxy target;
	private DateProxy<java.util.Date> createTime;
	private IntegerProxy award;
	private IntegerProxy activityId;
	private DateProxy<java.util.Date> startTime;
	private DateProxy<java.util.Date> endTime;
	private PropertyProxy<java.lang.String> name;
	private IntegerProxy achievementAction;
	private IntegerProxy characterId;
	private PropertyProxy<java.lang.String> backUp;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy action() {
		return action;
	}

	public IntegerProxy status() {
		return status;
	}

	public IntegerProxy number() {
		return number;
	}

	public IntegerProxy target() {
		return target;
	}

	public DateProxy<java.util.Date> createTime() {
		return createTime;
	}

	public IntegerProxy award() {
		return award;
	}

	public IntegerProxy activityId() {
		return activityId;
	}

	public DateProxy<java.util.Date> startTime() {
		return startTime;
	}

	public DateProxy<java.util.Date> endTime() {
		return endTime;
	}

	public PropertyProxy<java.lang.String> name() {
		return name;
	}

	public IntegerProxy achievementAction() {
		return achievementAction;
	}

	public IntegerProxy characterId() {
		return characterId;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

	@Override
	public void initFields() {
		WPlayerActivity instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		action = new  IntegerProxy ("action", this, instance::getAction, instance::setAction);
		status = new  IntegerProxy ("status", this, instance::getStatus, instance::setStatus);
		number = new  IntegerProxy ("number", this, instance::getNumber, instance::setNumber);
		target = new  IntegerProxy ("target", this, instance::getTarget, instance::setTarget);
		createTime = new  DateProxy<java.util.Date> ("createTime", this, instance::getCreateTime, instance::setCreateTime);
		award = new  IntegerProxy ("award", this, instance::getAward, instance::setAward);
		activityId = new  IntegerProxy ("activityId", this, instance::getActivityId, instance::setActivityId);
		startTime = new  DateProxy<java.util.Date> ("startTime", this, instance::getStartTime, instance::setStartTime);
		endTime = new  DateProxy<java.util.Date> ("endTime", this, instance::getEndTime, instance::setEndTime);
		name = new  PropertyProxy<java.lang.String> ("name", this, instance::getName, instance::setName);
		achievementAction = new  IntegerProxy ("achievementAction", this, instance::getAchievementAction, instance::setAchievementAction);
		characterId = new  IntegerProxy ("characterId", this, instance::getCharacterId, instance::setCharacterId);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
	}

	public ProxyWPlayerActivity(WPlayerActivity  instance) {
		super(instance);
	}

	public ProxyWPlayerActivity(Object element, Proxy<?> owner, Supplier<WPlayerActivity> getter,  Consumer<WPlayerActivity> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerActivity(Object element, Proxy<?> owner, WPlayerActivity instance) {
		super(element, owner, instance);
	}

}