package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysChest extends DmModel {

    private static final long serialVersionUID = -4080450009449994535L;

    private Integer id;

    private Integer type;

    private Integer level;

    private Integer sysItemId;

    private Integer weight;

    private Integer number;

    private Integer useType;

    private Integer isNotice;

    private Integer price;

    private Integer chipPrice;

    private Boolean isDeleted;

    private Integer weight1;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSysItemId() {
        return sysItemId;
    }

    public void setSysItemId(Integer sysItemId) {
        this.sysItemId = sysItemId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getIsNotice() {
        return isNotice;
    }

    public void setIsNotice(Integer isNotice) {
        this.isNotice = isNotice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getChipPrice() {
        return chipPrice;
    }

    public void setChipPrice(Integer chipPrice) {
        this.chipPrice = chipPrice;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getWeight1() {
        return weight1;
    }

    public void setWeight1(Integer weight1) {
        this.weight1 = weight1;
    }

    @Override
    public boolean getIsRemoved() {
        return isDeleted;
    }

    @Override
    public void setIsRemoved(boolean isRemoved) {
        isDeleted = isRemoved;
    }
}