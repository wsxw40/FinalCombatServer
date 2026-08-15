package com.pearl.fcw.gm.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type=Schema.SYS)
public class WSysLevel extends DmModel{

    private static final long serialVersionUID = 7436438435399787918L;

    private Integer id;

    private Byte type;

    private String name;

    private String displayName;

    private Integer index;

    private Byte isnew;

    private String startPoints;

    private String blastPoints;

    private String flagPoints;

    private String weaponPoints;

    private String bossItems;

    private Byte isSelf;

    private String description;

    private String zombieInfo;

    private Float levelHorizon;

    private String vehicleLineInfo;

    private Integer vehicleAddNum;

    private String vehicleInfo;

    private Float targetSpeed;

    private Integer bloodOffset;

    private Integer isRushGold;

    private Integer isMoneyReward;

    private String rushGoldPoint;

    private Integer isVip;

    private Float expAdd;

    private Float gpAdd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityStart;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activityEnd;

    private Integer numForTeam;

    private Byte isGm;

    private String supplies;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Byte getIsnew() {
        return isnew;
    }

    public void setIsnew(Byte isnew) {
        this.isnew = isnew;
    }

    public String getStartPoints() {
        return startPoints;
    }

    public void setStartPoints(String startPoints) {
        this.startPoints = startPoints == null ? null : startPoints.trim();
    }

    public String getBlastPoints() {
        return blastPoints;
    }

    public void setBlastPoints(String blastPoints) {
        this.blastPoints = blastPoints == null ? null : blastPoints.trim();
    }

    public String getFlagPoints() {
        return flagPoints;
    }

    public void setFlagPoints(String flagPoints) {
        this.flagPoints = flagPoints == null ? null : flagPoints.trim();
    }

    public String getWeaponPoints() {
        return weaponPoints;
    }

    public void setWeaponPoints(String weaponPoints) {
        this.weaponPoints = weaponPoints == null ? null : weaponPoints.trim();
    }

    public String getBossItems() {
        return bossItems;
    }

    public void setBossItems(String bossItems) {
        this.bossItems = bossItems == null ? null : bossItems.trim();
    }

    public Byte getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Byte isSelf) {
        this.isSelf = isSelf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getZombieInfo() {
        return zombieInfo;
    }

    public void setZombieInfo(String zombieInfo) {
        this.zombieInfo = zombieInfo == null ? null : zombieInfo.trim();
    }

    public Float getLevelHorizon() {
        return levelHorizon;
    }

    public void setLevelHorizon(Float levelHorizon) {
        this.levelHorizon = levelHorizon;
    }

    public String getVehicleLineInfo() {
        return vehicleLineInfo;
    }

    public void setVehicleLineInfo(String vehicleLineInfo) {
        this.vehicleLineInfo = vehicleLineInfo == null ? null : vehicleLineInfo.trim();
    }

    public Integer getVehicleAddNum() {
        return vehicleAddNum;
    }

    public void setVehicleAddNum(Integer vehicleAddNum) {
        this.vehicleAddNum = vehicleAddNum;
    }

    public String getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(String vehicleInfo) {
        this.vehicleInfo = vehicleInfo == null ? null : vehicleInfo.trim();
    }

    public Float getTargetSpeed() {
        return targetSpeed;
    }

    public void setTargetSpeed(Float targetSpeed) {
        this.targetSpeed = targetSpeed;
    }

    public Integer getBloodOffset() {
        return bloodOffset;
    }

    public void setBloodOffset(Integer bloodOffset) {
        this.bloodOffset = bloodOffset;
    }

    public Integer getIsRushGold() {
        return isRushGold;
    }

    public void setIsRushGold(Integer isRushGold) {
        this.isRushGold = isRushGold;
    }

    public Integer getIsMoneyReward() {
        return isMoneyReward;
    }

    public void setIsMoneyReward(Integer isMoneyReward) {
        this.isMoneyReward = isMoneyReward;
    }

    public String getRushGoldPoint() {
        return rushGoldPoint;
    }

    public void setRushGoldPoint(String rushGoldPoint) {
        this.rushGoldPoint = rushGoldPoint == null ? null : rushGoldPoint.trim();
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Float getExpAdd() {
        return expAdd;
    }

    public void setExpAdd(Float expAdd) {
        this.expAdd = expAdd;
    }

    public Float getGpAdd() {
        return gpAdd;
    }

    public void setGpAdd(Float gpAdd) {
        this.gpAdd = gpAdd;
    }

    public Date getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Date activityStart) {
        this.activityStart = activityStart;
    }

    public Date getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(Date activityEnd) {
        this.activityEnd = activityEnd;
    }

    public Integer getNumForTeam() {
        return numForTeam;
    }

    public void setNumForTeam(Integer numForTeam) {
        this.numForTeam = numForTeam;
    }

    public Byte getIsGm() {
        return isGm;
    }

    public void setIsGm(Byte isGm) {
        this.isGm = isGm;
    }

    public String getSupplies() {
        return supplies;
    }

    public void setSupplies(String supplies) {
        this.supplies = supplies == null ? null : supplies.trim();
    }
}