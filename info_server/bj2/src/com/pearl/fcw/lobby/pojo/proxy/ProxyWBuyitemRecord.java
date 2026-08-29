package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WBuyitemRecord;

public class ProxyWBuyitemRecord extends EntityProxy<WBuyitemRecord> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy itemId;
	private IntegerProxy costId;
	private IntegerProxy record;
	private IntegerProxy payType;
	private IntegerProxy payAmount;
	private DateProxy<java.util.Date> lastBuyDate;
	private DateProxy<java.util.Date> createTime;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy itemId() {
		return itemId;
	}

	public IntegerProxy costId() {
		return costId;
	}

	public IntegerProxy record() {
		return record;
	}

	public IntegerProxy payType() {
		return payType;
	}

	public IntegerProxy payAmount() {
		return payAmount;
	}

	public DateProxy<java.util.Date> lastBuyDate() {
		return lastBuyDate;
	}

	public DateProxy<java.util.Date> createTime() {
		return createTime;
	}

	@Override
	public void initFields() {
		WBuyitemRecord instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		itemId = new  IntegerProxy ("itemId", this, instance::getItemId, instance::setItemId);
		costId = new  IntegerProxy ("costId", this, instance::getCostId, instance::setCostId);
		record = new  IntegerProxy ("record", this, instance::getRecord, instance::setRecord);
		payType = new  IntegerProxy ("payType", this, instance::getPayType, instance::setPayType);
		payAmount = new  IntegerProxy ("payAmount", this, instance::getPayAmount, instance::setPayAmount);
		lastBuyDate = new  DateProxy<java.util.Date> ("lastBuyDate", this, instance::getLastBuyDate, instance::setLastBuyDate);
		createTime = new  DateProxy<java.util.Date> ("createTime", this, instance::getCreateTime, instance::setCreateTime);
	}

	public ProxyWBuyitemRecord(WBuyitemRecord  instance) {
		super(instance);
	}

	public ProxyWBuyitemRecord(Object element, Proxy<?> owner, Supplier<WBuyitemRecord> getter,  Consumer<WBuyitemRecord> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWBuyitemRecord(Object element, Proxy<?> owner, WBuyitemRecord instance) {
		super(element, owner, instance);
	}

}