package com.pearl.fcw.gm.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.RandomWeight;

@GoSchema(type = Schema.SYS)
public class WSysOnlineAward extends DmModel implements RandomWeight {

	private static final long serialVersionUID = 7239610482244576779L;

	private Integer id;

	private Integer sysItemId;

	private Integer level;

	private Integer unit;

	private Integer unitType;

	private Integer type;

	private Integer weight;

	private Integer music;

	private String isDeleted;

	private String multiType;

	private Map<Integer, List<Integer>> multiTypeMap = new HashMap<>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysItemId() {
		return sysItemId;
	}

	public void setSysItemId(Integer sysItemId) {
		this.sysItemId = sysItemId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getUnitType() {
		return unitType;
	}

	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getMusic() {
		return music;
	}

	public void setMusic(Integer music) {
		this.music = music;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getMultiType() {
		return multiType;
	}

	public void setMultiType(String multiType) {
		this.multiType = multiType;
	}

	public Map<Integer, List<Integer>> getMultiTypeMap() {
		return multiTypeMap;
	}

	public void setMultiTypeMap(Map<Integer, List<Integer>> multiTypeMap) {
		this.multiTypeMap = multiTypeMap;
	}

	@Override
	public Integer getWeightRate() {
		return weight;
	}

	@Override
	public boolean getIsRemoved() {
		return "Y".equals(isDeleted);
	}

	@Override
	public void setIsRemoved(boolean isRemoved) {
		isDeleted = isRemoved ? "Y" : "N";
	}
}