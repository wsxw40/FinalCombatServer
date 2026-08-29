package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.DateProxy;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.ListProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WTeamLevel;

public class ProxyWTeamLevel extends EntityProxy<WTeamLevel> {
	private IntegerProxy id;
	private IntegerProxy teamId;
	private IntegerProxy levelType;
	private IntegerProxy orgLevelId;
	private PropertyProxy<java.lang.String> name;
	private PropertyProxy<java.lang.String> displayName;
	private PropertyProxy<java.lang.String> configPoints;
	private DateProxy<java.util.Date> createTime;
	private IntegerProxy lastUpdateId;
	private DateProxy<java.util.Date> lastUpdateTime;
	private DateProxy<java.util.Date> lastBeginUpdateTime;
	private PropertyProxy<java.lang.String> isEditable;
	private PropertyProxy<java.lang.String> isDeleted;
    private ListProxy<com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints, com.pearl.fcw.lobby.pojo.proxy.ProxyTeamLevelConfigPoints> configPointsList;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy teamId() {
		return teamId;
	}

	public IntegerProxy levelType() {
		return levelType;
	}

	public IntegerProxy orgLevelId() {
		return orgLevelId;
	}

	public PropertyProxy<java.lang.String> name() {
		return name;
	}

	public PropertyProxy<java.lang.String> displayName() {
		return displayName;
	}

	public PropertyProxy<java.lang.String> configPoints() {
		return configPoints;
	}

	public DateProxy<java.util.Date> createTime() {
		return createTime;
	}

	public IntegerProxy lastUpdateId() {
		return lastUpdateId;
	}

	public DateProxy<java.util.Date> lastUpdateTime() {
		return lastUpdateTime;
	}

	public DateProxy<java.util.Date> lastBeginUpdateTime() {
		return lastBeginUpdateTime;
	}

	public PropertyProxy<java.lang.String> isEditable() {
		return isEditable;
	}

	public PropertyProxy<java.lang.String> isDeleted() {
		return isDeleted;
	}

    public ListProxy<com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints, com.pearl.fcw.lobby.pojo.proxy.ProxyTeamLevelConfigPoints> configPointsList() {
		return configPointsList;
	}

	@Override
	public void initFields() {
		WTeamLevel instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		teamId = new  IntegerProxy ("teamId", this, instance::getTeamId, instance::setTeamId);
		levelType = new  IntegerProxy ("levelType", this, instance::getLevelType, instance::setLevelType);
		orgLevelId = new  IntegerProxy ("orgLevelId", this, instance::getOrgLevelId, instance::setOrgLevelId);
		name = new  PropertyProxy<java.lang.String> ("name", this, instance::getName, instance::setName);
		displayName = new  PropertyProxy<java.lang.String> ("displayName", this, instance::getDisplayName, instance::setDisplayName);
		configPoints = new  PropertyProxy<java.lang.String> ("configPoints", this, instance::getConfigPoints, instance::setConfigPoints);
		createTime = new  DateProxy<java.util.Date> ("createTime", this, instance::getCreateTime, instance::setCreateTime);
		lastUpdateId = new  IntegerProxy ("lastUpdateId", this, instance::getLastUpdateId, instance::setLastUpdateId);
		lastUpdateTime = new  DateProxy<java.util.Date> ("lastUpdateTime", this, instance::getLastUpdateTime, instance::setLastUpdateTime);
		lastBeginUpdateTime = new  DateProxy<java.util.Date> ("lastBeginUpdateTime", this, instance::getLastBeginUpdateTime, instance::setLastBeginUpdateTime);
		isEditable = new  PropertyProxy<java.lang.String> ("isEditable", this, instance::getIsEditable, instance::setIsEditable);
		isDeleted = new  PropertyProxy<java.lang.String> ("isDeleted", this, instance::getIsDeleted, instance::setIsDeleted);
        configPointsList = new ListProxy<com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints, com.pearl.fcw.lobby.pojo.proxy.ProxyTeamLevelConfigPoints>("configPointsList", this,
                instance::getConfigPointsList, instance::setConfigPointsList, com.pearl.fcw.lobby.pojo.proxy.ProxyTeamLevelConfigPoints::new);
	}

	public ProxyWTeamLevel(WTeamLevel  instance) {
		super(instance);
	}

	public ProxyWTeamLevel(Object element, Proxy<?> owner, Supplier<WTeamLevel> getter,  Consumer<WTeamLevel> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWTeamLevel(Object element, Proxy<?> owner, WTeamLevel instance) {
		super(element, owner, instance);
	}

}