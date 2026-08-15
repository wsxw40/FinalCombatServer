package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.MapProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WCharacterData;

public class ProxyWCharacterData extends EntityProxy<WCharacterData> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy characterId;
	private IntegerProxy kill;
	private IntegerProxy dead;
	private IntegerProxy headShot;
	private IntegerProxy knifeKill;
	private IntegerProxy usedCount;
	private IntegerProxy controlNum;
	private IntegerProxy revengeNum;
	private IntegerProxy assistNum;
	private IntegerProxy maxHeadShot;
	private IntegerProxy maxKill;
	private IntegerProxy maxHealth;
	private IntegerProxy maxDamage;
	private IntegerProxy maxAliveTime;
	private IntegerProxy mvpNum;
	private PropertyProxy<java.lang.String> backUp;



    private MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Number>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>> numberParamMap;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy characterId() {
		return characterId;
	}

	public IntegerProxy kill() {
		return kill;
	}

	public IntegerProxy dead() {
		return dead;
	}

	public IntegerProxy headShot() {
		return headShot;
	}

	public IntegerProxy knifeKill() {
		return knifeKill;
	}

	public IntegerProxy usedCount() {
		return usedCount;
	}

	public IntegerProxy controlNum() {
		return controlNum;
	}

	public IntegerProxy revengeNum() {
		return revengeNum;
	}

	public IntegerProxy assistNum() {
		return assistNum;
	}

	public IntegerProxy maxHeadShot() {
		return maxHeadShot;
	}

	public IntegerProxy maxKill() {
		return maxKill;
	}

	public IntegerProxy maxHealth() {
		return maxHealth;
	}

	public IntegerProxy maxDamage() {
		return maxDamage;
	}

	public IntegerProxy maxAliveTime() {
		return maxAliveTime;
	}

	public IntegerProxy mvpNum() {
		return mvpNum;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

    public MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Number>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>> numberParamMap() {
		return numberParamMap;
	}

	@Override
	public void initFields() {
		WCharacterData instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		characterId = new  IntegerProxy ("characterId", this, instance::getCharacterId, instance::setCharacterId);
		kill = new  IntegerProxy ("kill", this, instance::getKill, instance::setKill);
		dead = new  IntegerProxy ("dead", this, instance::getDead, instance::setDead);
		headShot = new  IntegerProxy ("headShot", this, instance::getHeadShot, instance::setHeadShot);
		knifeKill = new  IntegerProxy ("knifeKill", this, instance::getKnifeKill, instance::setKnifeKill);
		usedCount = new  IntegerProxy ("usedCount", this, instance::getUsedCount, instance::setUsedCount);
		controlNum = new  IntegerProxy ("controlNum", this, instance::getControlNum, instance::setControlNum);
		revengeNum = new  IntegerProxy ("revengeNum", this, instance::getRevengeNum, instance::setRevengeNum);
		assistNum = new  IntegerProxy ("assistNum", this, instance::getAssistNum, instance::setAssistNum);
		maxHeadShot = new  IntegerProxy ("maxHeadShot", this, instance::getMaxHeadShot, instance::setMaxHeadShot);
		maxKill = new  IntegerProxy ("maxKill", this, instance::getMaxKill, instance::setMaxKill);
		maxHealth = new  IntegerProxy ("maxHealth", this, instance::getMaxHealth, instance::setMaxHealth);
		maxDamage = new  IntegerProxy ("maxDamage", this, instance::getMaxDamage, instance::setMaxDamage);
		maxAliveTime = new  IntegerProxy ("maxAliveTime", this, instance::getMaxAliveTime, instance::setMaxAliveTime);
		mvpNum = new  IntegerProxy ("mvpNum", this, instance::getMvpNum, instance::setMvpNum);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
        numberParamMap = new MapProxy<String, com.pearl.fcw.lobby.pojo.ParamObject<Number>, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>>("numberParamMap", this,
                instance::getNumberParamMap, instance::setNumberParamMap, com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject<Number>::new);
	}

	public ProxyWCharacterData(WCharacterData  instance) {
		super(instance);
	}

	public ProxyWCharacterData(Object element, Proxy<?> owner, Supplier<WCharacterData> getter,  Consumer<WCharacterData> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWCharacterData(Object element, Proxy<?> owner, WCharacterData instance) {
		super(element, owner, instance);
	}
}