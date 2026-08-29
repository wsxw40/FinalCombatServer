package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerMelting;

public class ProxyWPlayerMelting extends EntityProxy<WPlayerMelting> {
	private IntegerProxy id;
	private IntegerProxy level;
	private IntegerProxy exp;
	private PropertyProxy<java.lang.Double> remaind;
	private IntegerProxy num;
	private IntegerProxy recovery;
	private LongProxy lastInit;
	private LongProxy startTime;
	private LongProxy grandTotalTime;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy level() {
		return level;
	}

	public IntegerProxy exp() {
		return exp;
	}

	public PropertyProxy<java.lang.Double> remaind() {
		return remaind;
	}

	public IntegerProxy num() {
		return num;
	}

	public IntegerProxy recovery() {
		return recovery;
	}

	public LongProxy lastInit() {
		return lastInit;
	}

	public LongProxy startTime() {
		return startTime;
	}

	public LongProxy grandTotalTime() {
		return grandTotalTime;
	}

	@Override
	public void initFields() {
		WPlayerMelting instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		level = new  IntegerProxy ("level", this, instance::getLevel, instance::setLevel);
		exp = new  IntegerProxy ("exp", this, instance::getExp, instance::setExp);
		remaind = new  PropertyProxy<java.lang.Double> ("remaind", this, instance::getRemaind, instance::setRemaind);
		num = new  IntegerProxy ("num", this, instance::getNum, instance::setNum);
		recovery = new  IntegerProxy ("recovery", this, instance::getRecovery, instance::setRecovery);
		lastInit = new  LongProxy ("lastInit", this, instance::getLastInit, instance::setLastInit);
		startTime = new  LongProxy ("startTime", this, instance::getStartTime, instance::setStartTime);
		grandTotalTime = new  LongProxy ("grandTotalTime", this, instance::getGrandTotalTime, instance::setGrandTotalTime);
	}

	public ProxyWPlayerMelting(WPlayerMelting  instance) {
		super(instance);
	}

	public ProxyWPlayerMelting(Object element, Proxy<?> owner, Supplier<WPlayerMelting> getter,  Consumer<WPlayerMelting> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerMelting(Object element, Proxy<?> owner, WPlayerMelting instance) {
		super(element, owner, instance);
	}

}