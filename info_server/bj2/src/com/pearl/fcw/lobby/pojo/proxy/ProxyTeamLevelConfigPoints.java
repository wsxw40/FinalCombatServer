package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.BeanProxy;
import com.pearl.fcw.core.pojo.proxy.FloatProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints;

public class ProxyTeamLevelConfigPoints extends BeanProxy<TeamLevelConfigPoints> {
	private IntegerProxy sysItemId;
	private FloatProxy x;
	private FloatProxy y;
	private FloatProxy z;
	private FloatProxy r1;
	private FloatProxy r2;
	private FloatProxy r3;
	private FloatProxy r4;

	public IntegerProxy sysItemId() {
		return sysItemId;
	}

	public FloatProxy x() {
		return x;
	}

	public FloatProxy y() {
		return y;
	}

	public FloatProxy z() {
		return z;
	}

	public FloatProxy r1() {
		return r1;
	}

	public FloatProxy r2() {
		return r2;
	}

	public FloatProxy r3() {
		return r3;
	}

	public FloatProxy r4() {
		return r4;
	}

	@Override
	public void initFields() {
		TeamLevelConfigPoints instance = get();
		sysItemId = new  IntegerProxy ("sysItemId", this, instance::getSysItemId, instance::setSysItemId);
		x = new  FloatProxy ("x", this, instance::getX, instance::setX);
		y = new  FloatProxy ("y", this, instance::getY, instance::setY);
		z = new  FloatProxy ("z", this, instance::getZ, instance::setZ);
		r1 = new  FloatProxy ("r1", this, instance::getR1, instance::setR1);
		r2 = new  FloatProxy ("r2", this, instance::getR2, instance::setR2);
		r3 = new  FloatProxy ("r3", this, instance::getR3, instance::setR3);
		r4 = new  FloatProxy ("r4", this, instance::getR4, instance::setR4);
	}

	public ProxyTeamLevelConfigPoints(Object element, Proxy<?> owner, Supplier<TeamLevelConfigPoints> getter,  Consumer<TeamLevelConfigPoints> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyTeamLevelConfigPoints(Object element, Proxy<?> owner, TeamLevelConfigPoints instance) {
		super(element, owner, instance);
	}

}