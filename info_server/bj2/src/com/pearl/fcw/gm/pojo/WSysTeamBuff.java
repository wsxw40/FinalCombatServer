package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysTeamBuff extends DmModel{

	private static final long serialVersionUID = -8256148636459958350L;

	private Integer id;

    private Integer rank;

    private Integer sysItemId;

    private Integer unit;

    private Byte unitType;

    private Boolean isDelete;

    private Boolean isEnable;

    private Byte payType;

    private Integer cost;

    @Override
	public Integer getId() {
        return id;
    }

    @Override
	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getSysItemId() {
        return sysItemId;
    }

    public void setSysItemId(Integer sysItemId) {
        this.sysItemId = sysItemId;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

	@Override
	public boolean getIsRemoved() {
		return isDelete;
	}

	@Override
	public void setIsRemoved(boolean isRemoved) {
		isDelete = isRemoved;
	}
}