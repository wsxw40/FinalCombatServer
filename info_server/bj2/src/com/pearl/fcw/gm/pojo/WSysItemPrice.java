package com.pearl.fcw.gm.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.RandomWeight;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.enums.EUnitType;

@GoSchema(type=Schema.SYS)
public class WSysItemPrice extends DmModel implements RandomWeight, Cloneable {

	private static final long serialVersionUID = -6151723196437676817L;

	private Integer id;

	private Integer sysItemId = 0;

	private Integer payType = EPayType.PAY_NONE.getNumber();

	private Integer unitType = EUnitType.QUANTITY.getNumber();

	private Integer cost = 0;

	private Integer unit = 1;

	private Integer isTarget = 1;

	private Integer payGroup = 0;

	private String multiType;

	private Integer level = 0;

	private Integer vipLevel = 0;

	private Integer teamLevel = 0;

	private Integer weightRate = 0;

	private Float probability = 1F;

	private String isDeleted = "N";
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getIsTarget() {
        return isTarget;
    }

    public void setIsTarget(Integer isTarget) {
        this.isTarget = isTarget;
    }

    public Integer getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(Integer payGroup) {
        this.payGroup = payGroup;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

	public Integer getTeamLevel() {
		return teamLevel;
	}

	public void setTeamLevel(Integer teamLevel) {
		this.teamLevel = teamLevel;
	}

	@Override
	public Integer getWeightRate() {
        return weightRate;
    }

    @Override
	public void setWeightRate(Integer weightRate) {
        this.weightRate = weightRate;
    }

    @Override
	public Float getProbability() {
        return probability;
    }

    @Override
	public void setProbability(Float probability) {
        this.probability = probability;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    @Override
	public Date getCreateTime() {
        return createTime;
    }

    @Override
	public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
	public Date getUpdateTime() {
        return updateTime;
    }

    @Override
	public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
	public boolean getIsRemoved() {
		return "Y".equals(isDeleted);
	}

	@Override
	public void setIsRemoved(boolean isRemoved) {
		isDeleted = isRemoved ? "Y" : "N";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}