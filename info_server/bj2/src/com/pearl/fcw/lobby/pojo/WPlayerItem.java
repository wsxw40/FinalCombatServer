package com.pearl.fcw.lobby.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerItem extends DmExtModel {

    private static final long serialVersionUID = -5750184204285199173L;

    private Integer id;

    private Integer playerId;

    private Integer itemId;

    private Byte currency;

    private Byte unitType;

    private Integer quantity;

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

    private Integer gstLevel;

    private Integer gstLevelExp;

	private String gunProperty;

	private Map<String, ItemGunProperty> gunPropertyMap = new HashMap<>();

    private Map<String, ParamObject<Number>> numberParamMap = new HashMap<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public Integer getGstLevel() {
        return gstLevel;
    }

    public void setGstLevel(Integer gstLevel) {
        this.gstLevel = gstLevel;
    }

    public Integer getGstLevelExp() {
        return gstLevelExp;
    }

    public void setGstLevelExp(Integer gstLevelExp) {
        this.gstLevelExp = gstLevelExp;
    }

	public String getGunProperty() {
		return gunProperty;
	}

	public void setGunProperty(String gunProperty) {
		this.gunProperty = gunProperty;
	}

	public Map<String, ItemGunProperty> getGunPropertyMap() {
		return gunPropertyMap;
	}

	public void setGunPropertyMap(Map<String, ItemGunProperty> gunPropertyMap) {
		this.gunPropertyMap = gunPropertyMap;
	}

	public Map<String, ParamObject<Number>> getNumberParamMap() {
        return numberParamMap;
    }

    public void setNumberParamMap(Map<String, ParamObject<Number>> numberParamMap) {
        this.numberParamMap = numberParamMap;
    }

    @Override
    public Integer getShareId() {
        return playerId;
    }

    @Override
    public void setShareId(Integer shareId) {
        playerId = shareId;
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