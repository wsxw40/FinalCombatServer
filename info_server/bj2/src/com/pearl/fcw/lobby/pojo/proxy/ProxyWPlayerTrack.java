package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerTrack;

public class ProxyWPlayerTrack extends EntityProxy<WPlayerTrack> {
	private IntegerProxy playerId;
	private PropertyProxy<java.lang.String> giftTime;
	private IntegerProxy medalCount;
	private IntegerProxy goldCount;
	private PropertyProxy<java.lang.Double> firstCost;
	private PropertyProxy<java.lang.String> firstCostTime;
	private PropertyProxy<java.lang.String> newRenewItem;

	public IntegerProxy playerId() {
		return playerId;
	}

	public PropertyProxy<java.lang.String> giftTime() {
		return giftTime;
	}

	public IntegerProxy medalCount() {
		return medalCount;
	}

	public IntegerProxy goldCount() {
		return goldCount;
	}

	public PropertyProxy<java.lang.Double> firstCost() {
		return firstCost;
	}

	public PropertyProxy<java.lang.String> firstCostTime() {
		return firstCostTime;
	}

	public PropertyProxy<java.lang.String> newRenewItem() {
		return newRenewItem;
	}

	@Override
	public void initFields() {
		WPlayerTrack instance = get();
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		giftTime = new  PropertyProxy<java.lang.String> ("giftTime", this, instance::getGiftTime, instance::setGiftTime);
		medalCount = new  IntegerProxy ("medalCount", this, instance::getMedalCount, instance::setMedalCount);
		goldCount = new  IntegerProxy ("goldCount", this, instance::getGoldCount, instance::setGoldCount);
		firstCost = new  PropertyProxy<java.lang.Double> ("firstCost", this, instance::getFirstCost, instance::setFirstCost);
		firstCostTime = new  PropertyProxy<java.lang.String> ("firstCostTime", this, instance::getFirstCostTime, instance::setFirstCostTime);
		newRenewItem = new  PropertyProxy<java.lang.String> ("newRenewItem", this, instance::getNewRenewItem, instance::setNewRenewItem);
	}

	public ProxyWPlayerTrack(WPlayerTrack  instance) {
		super(instance);
	}

	public ProxyWPlayerTrack(Object element, Proxy<?> owner, Supplier<WPlayerTrack> getter,  Consumer<WPlayerTrack> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerTrack(Object element, Proxy<?> owner, WPlayerTrack instance) {
		super(element, owner, instance);
	}

}