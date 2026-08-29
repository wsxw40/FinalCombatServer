package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerPack;

public class ProxyWPlayerPack extends EntityProxy<WPlayerPack> {
	private IntegerProxy playerId;
	private IntegerProxy playerItemId;
	private PropertyProxy<java.lang.String> type;
	private PropertyProxy<java.lang.Short> packId;
	private PropertyProxy<java.lang.Short> seq;
	private DateProxy<java.util.Date> expireTime;
	private PropertyProxy<java.lang.String> backUp;
	private IntegerProxy id;
	private IntegerProxy sysCharacterId;

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy playerItemId() {
		return playerItemId;
	}

	public PropertyProxy<java.lang.String> type() {
		return type;
	}

	public PropertyProxy<java.lang.Short> packId() {
		return packId;
	}

	public PropertyProxy<java.lang.Short> seq() {
		return seq;
	}

	public DateProxy<java.util.Date> expireTime() {
		return expireTime;
	}

	public PropertyProxy<java.lang.String> backUp() {
		return backUp;
	}

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy sysCharacterId() {
		return sysCharacterId;
	}

	@Override
	public void initFields() {
		WPlayerPack instance = get();
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		playerItemId = new  IntegerProxy ("playerItemId", this, instance::getPlayerItemId, instance::setPlayerItemId);
		type = new  PropertyProxy<java.lang.String> ("type", this, instance::getType, instance::setType);
		packId = new  PropertyProxy<java.lang.Short> ("packId", this, instance::getPackId, instance::setPackId);
		seq = new  PropertyProxy<java.lang.Short> ("seq", this, instance::getSeq, instance::setSeq);
		expireTime = new  DateProxy<java.util.Date> ("expireTime", this, instance::getExpireTime, instance::setExpireTime);
		backUp = new  PropertyProxy<java.lang.String> ("backUp", this, instance::getBackUp, instance::setBackUp);
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		sysCharacterId = new  IntegerProxy ("sysCharacterId", this, instance::getSysCharacterId, instance::setSysCharacterId);
	}

	public ProxyWPlayerPack(WPlayerPack  instance) {
		super(instance);
	}

	public ProxyWPlayerPack(Object element, Proxy<?> owner, Supplier<WPlayerPack> getter,  Consumer<WPlayerPack> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerPack(Object element, Proxy<?> owner, WPlayerPack instance) {
		super(element, owner, instance);
	}

}