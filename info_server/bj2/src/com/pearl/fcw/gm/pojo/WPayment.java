package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.RandomWeight;

@GoSchema(type = Schema.SYS)
public class WPayment extends DmModel implements RandomWeight {

    private static final long serialVersionUID = -5159376796459504697L;

    private Integer id;

    private Integer itemId;

    private Integer payType;

    private Integer unitType;

    private Integer cost;

    private Integer unit;

    private Byte isShow;

    private Integer level;

    private Integer finishPayType;

    private Integer finishCost;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFinishPayType() {
        return finishPayType;
    }

    public void setFinishPayType(Integer finishPayType) {
        this.finishPayType = finishPayType;
    }

    public Integer getFinishCost() {
        return finishCost;
    }

    public void setFinishCost(Integer finishCost) {
        this.finishCost = finishCost;
    }
}