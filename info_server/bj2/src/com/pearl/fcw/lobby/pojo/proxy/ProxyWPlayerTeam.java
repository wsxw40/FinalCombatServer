package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Supplier;
import java.util.function.Consumer;
import com.pearl.fcw.core.pojo.proxy.*;
import com.pearl.fcw.lobby.pojo.WPlayerTeam;

public class ProxyWPlayerTeam extends EntityProxy<WPlayerTeam> {
	private IntegerProxy id;
	private IntegerProxy playerId;
	private IntegerProxy teamId;
	private ByteProxy job;
	private IntegerProxy kill;
	private IntegerProxy dead;
	private IntegerProxy assist;
	private IntegerProxy score;
	private DateProxy<java.util.Date> createdTime;
	private PropertyProxy<java.lang.String> approved;
	private IntegerProxy contribution;
	private IntegerProxy times;
	private IntegerProxy point;
	private LongProxy lastBurnt;
	private IntegerProxy teamFightExp;
	private IntegerProxy personalFightExp;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy playerId() {
		return playerId;
	}

	public IntegerProxy teamId() {
		return teamId;
	}

	public ByteProxy job() {
		return job;
	}

	public IntegerProxy kill() {
		return kill;
	}

	public IntegerProxy dead() {
		return dead;
	}

	public IntegerProxy assist() {
		return assist;
	}

	public IntegerProxy score() {
		return score;
	}

	public DateProxy<java.util.Date> createdTime() {
		return createdTime;
	}

	public PropertyProxy<java.lang.String> approved() {
		return approved;
	}

	public IntegerProxy contribution() {
		return contribution;
	}

	public IntegerProxy times() {
		return times;
	}

	public IntegerProxy point() {
		return point;
	}

	public LongProxy lastBurnt() {
		return lastBurnt;
	}

	public IntegerProxy teamFightExp() {
		return teamFightExp;
	}

	public IntegerProxy personalFightExp() {
		return personalFightExp;
	}

	@Override
	public void initFields() {
		WPlayerTeam instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		playerId = new  IntegerProxy ("playerId", this, instance::getPlayerId, instance::setPlayerId);
		teamId = new  IntegerProxy ("teamId", this, instance::getTeamId, instance::setTeamId);
		job = new  ByteProxy ("job", this, instance::getJob, instance::setJob);
		kill = new  IntegerProxy ("kill", this, instance::getKill, instance::setKill);
		dead = new  IntegerProxy ("dead", this, instance::getDead, instance::setDead);
		assist = new  IntegerProxy ("assist", this, instance::getAssist, instance::setAssist);
		score = new  IntegerProxy ("score", this, instance::getScore, instance::setScore);
		createdTime = new  DateProxy<java.util.Date> ("createdTime", this, instance::getCreatedTime, instance::setCreatedTime);
		approved = new  PropertyProxy<java.lang.String> ("approved", this, instance::getApproved, instance::setApproved);
		contribution = new  IntegerProxy ("contribution", this, instance::getContribution, instance::setContribution);
		times = new  IntegerProxy ("times", this, instance::getTimes, instance::setTimes);
		point = new  IntegerProxy ("point", this, instance::getPoint, instance::setPoint);
		lastBurnt = new  LongProxy ("lastBurnt", this, instance::getLastBurnt, instance::setLastBurnt);
		teamFightExp = new  IntegerProxy ("teamFightExp", this, instance::getTeamFightExp, instance::setTeamFightExp);
		personalFightExp = new  IntegerProxy ("personalFightExp", this, instance::getPersonalFightExp, instance::setPersonalFightExp);
	}

	public ProxyWPlayerTeam(WPlayerTeam  instance) {
		super(instance);
	}

	public ProxyWPlayerTeam(Object element, Proxy<?> owner, Supplier<WPlayerTeam> getter,  Consumer<WPlayerTeam> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWPlayerTeam(Object element, Proxy<?> owner, WPlayerTeam instance) {
		super(element, owner, instance);
	}

}