package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WTeamItem extends DmExtModel {

    private static final long serialVersionUID = 2036290598431798529L;

    private Integer id;

    private Integer teamId;

    private Integer itemId;

    private Byte currency;

    private Byte unitType;

    private Byte quantity;

    private Float durable;

    private Date validTime;

    private Date expireTime;

    private String isBind;

    private String isDefault;

    private String isGift;

    private String modifiedDesc;

    private String isDeleted;

    private String gunProperty1;

    private String gunProperty2;

    private String gunProperty3;

    private String gunProperty4;

    private String gunProperty5;

    private String backUp;

    private Integer leftSeconds;

    private Integer level;

    private String gunProperty6;

    private String gunProperty7;

    private String gunProperty8;

    private Integer usedCount;

    private Float bullet;

    private Date startUpTime;

    private Date lastBuildTime;

    private Integer buildStatus;

    private Float gunProperty1Value;

    private Float gunProperty2Value;

    private Float gunProperty3Value;

    private Float gunProperty4Value;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Byte getCurrency() {
        return currency;
    }

    public void setCurrency(Byte currency) {
        this.currency = currency;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

    public Byte getQuantity() {
        return quantity;
    }

    public void setQuantity(Byte quantity) {
        this.quantity = quantity;
    }

    public Float getDurable() {
        return durable;
    }

    public void setDurable(Float durable) {
        this.durable = durable;
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getIsBind() {
        return isBind;
    }

    public void setIsBind(String isBind) {
        this.isBind = isBind == null ? null : isBind.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getIsGift() {
        return isGift;
    }

    public void setIsGift(String isGift) {
        this.isGift = isGift == null ? null : isGift.trim();
    }

    public String getModifiedDesc() {
        return modifiedDesc;
    }

    public void setModifiedDesc(String modifiedDesc) {
        this.modifiedDesc = modifiedDesc == null ? null : modifiedDesc.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getGunProperty1() {
        return gunProperty1;
    }

    public void setGunProperty1(String gunProperty1) {
        this.gunProperty1 = gunProperty1 == null ? null : gunProperty1.trim();
    }

    public String getGunProperty2() {
        return gunProperty2;
    }

    public void setGunProperty2(String gunProperty2) {
        this.gunProperty2 = gunProperty2 == null ? null : gunProperty2.trim();
    }

    public String getGunProperty3() {
        return gunProperty3;
    }

    public void setGunProperty3(String gunProperty3) {
        this.gunProperty3 = gunProperty3 == null ? null : gunProperty3.trim();
    }

    public String getGunProperty4() {
        return gunProperty4;
    }

    public void setGunProperty4(String gunProperty4) {
        this.gunProperty4 = gunProperty4 == null ? null : gunProperty4.trim();
    }

    public String getGunProperty5() {
        return gunProperty5;
    }

    public void setGunProperty5(String gunProperty5) {
        this.gunProperty5 = gunProperty5 == null ? null : gunProperty5.trim();
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public Integer getLeftSeconds() {
        return leftSeconds;
    }

    public void setLeftSeconds(Integer leftSeconds) {
        this.leftSeconds = leftSeconds;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getGunProperty6() {
        return gunProperty6;
    }

    public void setGunProperty6(String gunProperty6) {
        this.gunProperty6 = gunProperty6 == null ? null : gunProperty6.trim();
    }

    public String getGunProperty7() {
        return gunProperty7;
    }

    public void setGunProperty7(String gunProperty7) {
        this.gunProperty7 = gunProperty7 == null ? null : gunProperty7.trim();
    }

    public String getGunProperty8() {
        return gunProperty8;
    }

    public void setGunProperty8(String gunProperty8) {
        this.gunProperty8 = gunProperty8 == null ? null : gunProperty8.trim();
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Float getBullet() {
        return bullet;
    }

    public void setBullet(Float bullet) {
        this.bullet = bullet;
    }

    public Date getStartUpTime() {
        return startUpTime;
    }

    public void setStartUpTime(Date startUpTime) {
        this.startUpTime = startUpTime;
    }

    public Date getLastBuildTime() {
        return lastBuildTime;
    }

    public void setLastBuildTime(Date lastBuildTime) {
        this.lastBuildTime = lastBuildTime;
    }

    public Integer getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(Integer buildStatus) {
        this.buildStatus = buildStatus;
    }

    public Float getGunProperty1Value() {
        return gunProperty1Value;
    }

    public void setGunProperty1Value(Float gunProperty1Value) {
        this.gunProperty1Value = gunProperty1Value;
    }

    public Float getGunProperty2Value() {
        return gunProperty2Value;
    }

    public void setGunProperty2Value(Float gunProperty2Value) {
        this.gunProperty2Value = gunProperty2Value;
    }

    public Float getGunProperty3Value() {
        return gunProperty3Value;
    }

    public void setGunProperty3Value(Float gunProperty3Value) {
        this.gunProperty3Value = gunProperty3Value;
    }

    public Float getGunProperty4Value() {
        return gunProperty4Value;
    }

    public void setGunProperty4Value(Float gunProperty4Value) {
        this.gunProperty4Value = gunProperty4Value;
    }

    @Override
    public Integer getShareId() {
        return teamId;
    }

    @Override
    public void setShareId(Integer shareId) {
        teamId = shareId;
    }

    @Override
    public Integer getShareAmount() {
        return null;
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