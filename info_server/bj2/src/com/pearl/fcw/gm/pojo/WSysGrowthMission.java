package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysGrowthMission extends DmModel {

    private static final long serialVersionUID = -7455310138414243574L;

    private Integer id;

    private String icon;

    private String title;

    private String description;

    private Integer number;

    private Integer rank;

    private Integer parent;

    private Integer experience;

    private Integer money;

    private Integer strengthen;

    private Integer honor;

    private Integer addsucess;

    private String gift;

    private Integer amount;

    private Integer unit;

    private Integer unitType;

    private Boolean status;

    private Boolean defaultopen;

    private Integer openmodule;

    private String backUp;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getStrengthen() {
        return strengthen;
    }

    public void setStrengthen(Integer strengthen) {
        this.strengthen = strengthen;
    }

    public Integer getHonor() {
        return honor;
    }

    public void setHonor(Integer honor) {
        this.honor = honor;
    }

    public Integer getAddsucess() {
        return addsucess;
    }

    public void setAddsucess(Integer addsucess) {
        this.addsucess = addsucess;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift == null ? null : gift.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDefaultopen() {
        return defaultopen;
    }

    public void setDefaultopen(Boolean defaultopen) {
        this.defaultopen = defaultopen;
    }

    public Integer getOpenmodule() {
        return openmodule;
    }

    public void setOpenmodule(Integer openmodule) {
        this.openmodule = openmodule;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }
}