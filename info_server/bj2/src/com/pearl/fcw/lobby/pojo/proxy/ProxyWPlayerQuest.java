package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.LongProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WPlayerQuest;

public class ProxyWPlayerQuest extends EntityProxy<WPlayerQuest> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy sysQuestId;
	private IntegerProxy status;
	private LongProxy number;
	private IntegerProxy completeCount;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy sysQuestId() {
		return sysQuestId;
	}

	public IntegerProxy status() {
		return status;
	}

	public LongProxy number() {
		return number;
	}

	public IntegerProxy completeCount() {
		return completeCount;
	}

	@Override
	public void initFields() {
		WPlayerQuest instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		sysQuestId = new  IntegerProxy ("sysQuestId", this, instance::getSysQuestId, instance::setSysQuestId);
		status = new  IntegerProxy ("status", this, instance::getStatus, instance::setStatus);
		number = new  LongProxy ("number", this, instance::getNumber, instance::setNumber);
		completeCount = new IntegerProxy("completeCount", this, instance::getCompleteCount, instance::setCompleteCount);
	}

	public ProxyWPlayerQuest(WPlayerQuest  instance) {
		super(instance);
	}

	public ProxyWPlayerQuest(Object element, Proxy<?> owner, Supplier<WPlayerQuest> getter,  Consumer<WPlayerQuest> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerQuest(Object element, Proxy<?> owner, WPlayerQuest instance) {
		super(element, owner, instance);
	}

}