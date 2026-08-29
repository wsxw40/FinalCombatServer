package com.pearl.fcw.gm.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBasePoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBlastPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelBoss;
import com.pearl.fcw.lobby.pojo.json.JsonLevelItemPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelSupplyPoint;
import com.pearl.fcw.lobby.pojo.json.JsonLevelVehicle;
import com.pearl.fcw.lobby.pojo.json.JsonLevelVehicleLine;

@GoSchema(type=Schema.SYS)
public class WSysLevel extends DmModel{

    private static final long serialVersionUID = 7436438435399787918L;

    private Integer id;

	private Integer type;

    private String name;

    private String displayName;

    private Integer index;

	private Integer isnew;

    private String startPoints;

    private String blastPoints;

    private String flagPoints;

    private String weaponPoints;

    private String bossItems;

	private String boss;

	private String bossDrop;

	private Integer isSelf;

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

	private Integer isGm;

    private String supplies;

	private JsonLevelVehicle vehicleInfoEntity = new JsonLevelVehicle();
	private List<JsonLevelVehicleLine> vehicleLineInfoList = new ArrayList<>();
	private List<JsonLevelBasePoint> startPointList = new ArrayList<>();
	private List<JsonLevelBasePoint> flagPointList = new ArrayList<>();
	private List<JsonLevelBasePoint> rushGoldPointList = new ArrayList<>();
	private List<JsonLevelBlastPoint> blastPointList = new ArrayList<>();
	private List<JsonLevelItemPoint> weaponPointList = new ArrayList<>();
	private List<JsonLevelSupplyPoint> supplyList = new ArrayList<>();
	private List<JsonLevelBoss> bossList = new ArrayList<>();

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

	public Integer getIsnew() {
        return isnew;
    }

	public void setIsnew(Integer isnew) {
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

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getBossDrop() {
		return bossDrop;
	}

	public void setBossDrop(String bossDrop) {
		this.bossDrop = bossDrop;
	}

	public Integer getIsSelf() {
        return isSelf;
    }

	public void setIsSelf(Integer isSelf) {
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

	public Integer getIsGm() {
        return isGm;
    }

	public void setIsGm(Integer isGm) {
        this.isGm = isGm;
    }

    public String getSupplies() {
        return supplies;
    }

    public void setSupplies(String supplies) {
        this.supplies = supplies == null ? null : supplies.trim();
    }

	public JsonLevelVehicle getVehicleInfoEntity() {
		return vehicleInfoEntity;
	}

	public void setVehicleInfoEntity(JsonLevelVehicle vehicleInfoEntity) {
		this.vehicleInfoEntity = vehicleInfoEntity;
	}

	public List<JsonLevelBasePoint> getStartPointList() {
		return startPointList;
	}

	public void setStartPointList(List<JsonLevelBasePoint> startPointList) {
		this.startPointList = startPointList;
	}

	public List<JsonLevelBlastPoint> getBlastPointList() {
		return blastPointList;
	}

	public void setBlastPointList(List<JsonLevelBlastPoint> blastPointList) {
		this.blastPointList = blastPointList;
	}

	public List<JsonLevelVehicleLine> getVehicleLineInfoList() {
		return vehicleLineInfoList;
	}

	public void setVehicleLineInfoList(List<JsonLevelVehicleLine> vehicleLineInfoList) {
		this.vehicleLineInfoList = vehicleLineInfoList;
	}

	public List<JsonLevelBasePoint> getFlagPointList() {
		return flagPointList;
	}

	public void setFlagPointList(List<JsonLevelBasePoint> flagPointList) {
		this.flagPointList = flagPointList;
	}

	public List<JsonLevelItemPoint> getWeaponPointList() {
		return weaponPointList;
	}

	public void setWeaponPointList(List<JsonLevelItemPoint> weaponPointList) {
		this.weaponPointList = weaponPointList;
	}

	public List<JsonLevelSupplyPoint> getSupplyList() {
		return supplyList;
	}

	public void setSupplyList(List<JsonLevelSupplyPoint> supplyList) {
		this.supplyList = supplyList;
	}

	public List<JsonLevelBasePoint> getRushGoldPointList() {
		return rushGoldPointList;
	}

	public void setRushGoldPointList(List<JsonLevelBasePoint> rushGoldPointList) {
		this.rushGoldPointList = rushGoldPointList;
	}

	public List<JsonLevelBoss> getBossList() {
		return bossList;
	}

	public void setBossList(List<JsonLevelBoss> bossList) {
		this.bossList = bossList;
	}
}