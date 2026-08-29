package com.pearl.fcw.gm.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.json.JsonItemSight;

@GoSchema(type = Schema.SYS)
public class WSysItem extends DmModel {

    private static final long serialVersionUID = 4270864070953393127L;

    private Integer id;

    private String name;

    private String displayName;

    private String resourceStable;

    private String resourceChangeable;

    private Integer type;

    private Integer subType;

    private Integer level;

    private String isDeleted;

    private String modifiedDesc;

    private String description;

    private Integer wId;

    private Float wChangeInTime;

    private Float wMoveSpeedOffset;

    private Float wCrossOffset;

    private Float wCrossLengthBase;

    private Float wCrossLengthFactor;

    private Float wCrossDistanceBase;

    private Float wCrossDistanceFactor;

    private Integer wAccuracyDivisor;

    private Float wAccuracyOffset;

    private Float wMaxInaccuracy;

    private Integer wPenetration;

    private Integer wDamage;

    private Float wRangeModifier;

    private Float wRangeStart;

    private Float wRangeEnd;

    private Float wFireTime;

    private Float wReloadTime;

    private Float wZoomTime;

    private Float wZoomValue;

    private Integer wAmmoOneClip;

    private Integer wAmmoCount;

    private Integer wAutoFire;

    private Float wTimeToIdle;

    private Float wNormalOffset;

    private Float wNormalFactor;

    private Float wOnairOffset;

    private Float wOnairFactor;

    private Float wMoveOffset;

    private Float wMoveFactor;

    private Float wNormalUpBase;

    private Float wNormalLateralBase;

    private Float wNormalUpModifier;

    private Float wNormalLateralModifier;

    private Float wNormalUpMax;

    private Float wNormalLateralMax;

    private Float wNormalDirChange;

    private Float wMoveUpBase;

    private Float wMoveLateralBase;

    private Float wMoveUpModifier;

    private Float wMoveLateralModifier;

    private Float wMoveUpMax;

    private Float wMoveLateralMax;

    private Float wMoveDirChange;

    private Float wOnairUpBase;

    private Float wOnairLateralBase;

    private Float wOnairUpModifier;

    private Float wOnairLateralModifier;

    private Float wOnairUpMax;

    private Float wOnairLateralMax;

    private Float wOnairDirChange;

    private Float wCrouchUpBase;

    private Float wCrouchLateralBase;

    private Float wCrouchUpModifier;

    private Float wCrouchLateralModifier;

    private Float wCrouchUpMax;

    private Float wCrouchLateralMax;

    private Float wCrouchDirChange;

    private Float wUpModifier;

    private Float wAccuracyTime;

    private Float wAccuracyTimeModifier;

    private Float wMaxAccuracy;

    private Float wMinAccuracy;

    private Integer wShootBulletCount;

    private Float wSpread;

    private Float wFireMaxSpeed;

    private Float wFireStartSpeed;

    private Float wFireAceleration;

    private Float wFireResistance;

    private Float wStabTime;

    private Float wStabTime1;

    private Float wStabLightTime;

    private Float wStabHurt;

    private Float wStabLightHurt;

    private Float wExplodeTime;

    private Float wReadyTime;

    private Float wThrowOutTime;

    private Float wHurtRange;

    private Float wHurt;

    private Integer cId;

    private Integer iId;

    private String iValue;

    private Integer wXOffset;

    private Float wSightNormalOffset;

    private Float wSightOnairOffset;

    private Float wSightMoveOffset;

    private Float wStabDistance;

    private Float wStabLightDistance;

    private Float wStabWidth;

    private Float wBackFactor;

    private Float wFlashRangeStart;

    private Float wFlashRangeEnd;

    private Float wFlashBackFactor;

    private Float wTimeMax;

    private Float wTimeFade;

    private Float wTime;

    private Float wHitSpeed;

    private Float wHitAcceleration;

    private Float wHitDistance;

    private String wSightInfo;

    private Float wMaxDistance;

    private Integer wAddBlood;

    private Integer wAmmoType;

    private Float wFlySpeed;

    private Float wMaxaliveTime;

    private Integer wGravity;

    private Float wLastHurt;

    private Float wLastTime;

    private Float wSpecialDistance;

    private Float wSpecialRange;

    private Float wSpecialLasttime;

    private Float wSpecialHurt;

    private Float wSpecialLasthurt;

    private Integer wParticlemun;

    private Integer wShowSpeed;

    private String wAmmopartKey;

    private Integer wCritRate;

    private Integer wCritAvailable;

    private String characterId;

    private Float wDmgModifyTimerMin;

    private Float wDmgModifyTimerMax;

    private Float wDmgModifyMin;

    private Float wDmgModifyMax;

    private Float wCapsuleHeight;

    private Float wCapsuleRadius;

    private Integer wDamageModifer;

    private Integer iBuffId;

    private String gunProperty1;

    private String gunProperty2;

    private String gunProperty3;

    private String gunProperty4;

    private Integer isVip;

    private Integer isNew;

    private Integer isHot;

    private String gunProperty5;

    private String items;

    private Integer backBoostPlus;

    private Integer wFixPrice;

    private Integer index;

    private String gunProperty6;

    private String gunProperty7;

    private String gunProperty8;

    private Float cResistanceFire;

    private Float cResistanceBlast;

    private Float cResistanceBullet;

    private Float cResistanceKnife;

    private Integer cBloodAdd;

    private Integer isStrengthen;

    private Integer mType;

    private Integer mValue;

    private Integer isWeb;

    private Integer fightNum;

    private Integer rareLevel;

    private Integer isPopular;

    private Integer isExchange;

    private Integer isAsset;

    private Integer strengthLevel;

    private Float evaluateRmb;

    private Float teamLevelOccupyLength;

    private Float teamLevelOccupyWidth;

    private Integer needTeamPlaceLevel;

    private Float teamItemUpgradePrice;

    private Integer timeForBuildingUp;

    private Integer timeForCreate;

    private Integer timeForBuild;

	private String gunProperty;

	private Integer shoppingType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date shoppingStartTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date shoppingEndTime;

    private Map<String, Number> numberParamMap = new HashMap<>();

	private Map<String, ItemGunProperty> gunPropertyMap = new HashMap<>();

    private List<Integer> characterIdList = new ArrayList<>();

    private List<Integer> itemsList = new ArrayList<>();

    private List<String> resourceStableList = new ArrayList<>();

    private List<List<String>> resourceChangeableList = new ArrayList<>();

	private List<JsonItemSight> wSightInfoList = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public String getResourceStable() {
        return resourceStable;
    }

    public void setResourceStable(String resourceStable) {
        this.resourceStable = resourceStable == null ? null : resourceStable.trim();
    }

    public String getResourceChangeable() {
        return resourceChangeable;
    }

    public void setResourceChangeable(String resourceChangeable) {
        this.resourceChangeable = resourceChangeable == null ? null : resourceChangeable.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getModifiedDesc() {
        return modifiedDesc;
    }

    public void setModifiedDesc(String modifiedDesc) {
        this.modifiedDesc = modifiedDesc == null ? null : modifiedDesc.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public Float getwChangeInTime() {
        return wChangeInTime;
    }

    public void setwChangeInTime(Float wChangeInTime) {
        this.wChangeInTime = wChangeInTime;
    }

    public Float getwMoveSpeedOffset() {
        return wMoveSpeedOffset;
    }

    public void setwMoveSpeedOffset(Float wMoveSpeedOffset) {
        this.wMoveSpeedOffset = wMoveSpeedOffset;
    }

    public Float getwCrossOffset() {
        return wCrossOffset;
    }

    public void setwCrossOffset(Float wCrossOffset) {
        this.wCrossOffset = wCrossOffset;
    }

    public Float getwCrossLengthBase() {
        return wCrossLengthBase;
    }

    public void setwCrossLengthBase(Float wCrossLengthBase) {
        this.wCrossLengthBase = wCrossLengthBase;
    }

    public Float getwCrossLengthFactor() {
        return wCrossLengthFactor;
    }

    public void setwCrossLengthFactor(Float wCrossLengthFactor) {
        this.wCrossLengthFactor = wCrossLengthFactor;
    }

    public Float getwCrossDistanceBase() {
        return wCrossDistanceBase;
    }

    public void setwCrossDistanceBase(Float wCrossDistanceBase) {
        this.wCrossDistanceBase = wCrossDistanceBase;
    }

    public Float getwCrossDistanceFactor() {
        return wCrossDistanceFactor;
    }

    public void setwCrossDistanceFactor(Float wCrossDistanceFactor) {
        this.wCrossDistanceFactor = wCrossDistanceFactor;
    }

    public Integer getwAccuracyDivisor() {
        return wAccuracyDivisor;
    }

    public void setwAccuracyDivisor(Integer wAccuracyDivisor) {
        this.wAccuracyDivisor = wAccuracyDivisor;
    }

    public Float getwAccuracyOffset() {
        return wAccuracyOffset;
    }

    public void setwAccuracyOffset(Float wAccuracyOffset) {
        this.wAccuracyOffset = wAccuracyOffset;
    }

    public Float getwMaxInaccuracy() {
        return wMaxInaccuracy;
    }

    public void setwMaxInaccuracy(Float wMaxInaccuracy) {
        this.wMaxInaccuracy = wMaxInaccuracy;
    }

    public Integer getwPenetration() {
        return wPenetration;
    }

    public void setwPenetration(Integer wPenetration) {
        this.wPenetration = wPenetration;
    }

    public Integer getwDamage() {
        return wDamage;
    }

    public void setwDamage(Integer wDamage) {
        this.wDamage = wDamage;
    }

    public Float getwRangeModifier() {
        return wRangeModifier;
    }

    public void setwRangeModifier(Float wRangeModifier) {
        this.wRangeModifier = wRangeModifier;
    }

    public Float getwRangeStart() {
        return wRangeStart;
    }

    public void setwRangeStart(Float wRangeStart) {
        this.wRangeStart = wRangeStart;
    }

    public Float getwRangeEnd() {
        return wRangeEnd;
    }

    public void setwRangeEnd(Float wRangeEnd) {
        this.wRangeEnd = wRangeEnd;
    }

    public Float getwFireTime() {
        return wFireTime;
    }

    public void setwFireTime(Float wFireTime) {
        this.wFireTime = wFireTime;
    }

    public Float getwReloadTime() {
        return wReloadTime;
    }

    public void setwReloadTime(Float wReloadTime) {
        this.wReloadTime = wReloadTime;
    }

    public Float getwZoomTime() {
        return wZoomTime;
    }

    public void setwZoomTime(Float wZoomTime) {
        this.wZoomTime = wZoomTime;
    }

    public Float getwZoomValue() {
        return wZoomValue;
    }

    public void setwZoomValue(Float wZoomValue) {
        this.wZoomValue = wZoomValue;
    }

    public Integer getwAmmoOneClip() {
        return wAmmoOneClip;
    }

    public void setwAmmoOneClip(Integer wAmmoOneClip) {
        this.wAmmoOneClip = wAmmoOneClip;
    }

    public Integer getwAmmoCount() {
        return wAmmoCount;
    }

    public void setwAmmoCount(Integer wAmmoCount) {
        this.wAmmoCount = wAmmoCount;
    }

    public Integer getwAutoFire() {
        return wAutoFire;
    }

    public void setwAutoFire(Integer wAutoFire) {
        this.wAutoFire = wAutoFire;
    }

    public Float getwTimeToIdle() {
        return wTimeToIdle;
    }

    public void setwTimeToIdle(Float wTimeToIdle) {
        this.wTimeToIdle = wTimeToIdle;
    }

    public Float getwNormalOffset() {
        return wNormalOffset;
    }

    public void setwNormalOffset(Float wNormalOffset) {
        this.wNormalOffset = wNormalOffset;
    }

    public Float getwNormalFactor() {
        return wNormalFactor;
    }

    public void setwNormalFactor(Float wNormalFactor) {
        this.wNormalFactor = wNormalFactor;
    }

    public Float getwOnairOffset() {
        return wOnairOffset;
    }

    public void setwOnairOffset(Float wOnairOffset) {
        this.wOnairOffset = wOnairOffset;
    }

    public Float getwOnairFactor() {
        return wOnairFactor;
    }

    public void setwOnairFactor(Float wOnairFactor) {
        this.wOnairFactor = wOnairFactor;
    }

    public Float getwMoveOffset() {
        return wMoveOffset;
    }

    public void setwMoveOffset(Float wMoveOffset) {
        this.wMoveOffset = wMoveOffset;
    }

    public Float getwMoveFactor() {
        return wMoveFactor;
    }

    public void setwMoveFactor(Float wMoveFactor) {
        this.wMoveFactor = wMoveFactor;
    }

    public Float getwNormalUpBase() {
        return wNormalUpBase;
    }

    public void setwNormalUpBase(Float wNormalUpBase) {
        this.wNormalUpBase = wNormalUpBase;
    }

    public Float getwNormalLateralBase() {
        return wNormalLateralBase;
    }

    public void setwNormalLateralBase(Float wNormalLateralBase) {
        this.wNormalLateralBase = wNormalLateralBase;
    }

    public Float getwNormalUpModifier() {
        return wNormalUpModifier;
    }

    public void setwNormalUpModifier(Float wNormalUpModifier) {
        this.wNormalUpModifier = wNormalUpModifier;
    }

    public Float getwNormalLateralModifier() {
        return wNormalLateralModifier;
    }

    public void setwNormalLateralModifier(Float wNormalLateralModifier) {
        this.wNormalLateralModifier = wNormalLateralModifier;
    }

    public Float getwNormalUpMax() {
        return wNormalUpMax;
    }

    public void setwNormalUpMax(Float wNormalUpMax) {
        this.wNormalUpMax = wNormalUpMax;
    }

    public Float getwNormalLateralMax() {
        return wNormalLateralMax;
    }

    public void setwNormalLateralMax(Float wNormalLateralMax) {
        this.wNormalLateralMax = wNormalLateralMax;
    }

    public Float getwNormalDirChange() {
        return wNormalDirChange;
    }

    public void setwNormalDirChange(Float wNormalDirChange) {
        this.wNormalDirChange = wNormalDirChange;
    }

    public Float getwMoveUpBase() {
        return wMoveUpBase;
    }

    public void setwMoveUpBase(Float wMoveUpBase) {
        this.wMoveUpBase = wMoveUpBase;
    }

    public Float getwMoveLateralBase() {
        return wMoveLateralBase;
    }

    public void setwMoveLateralBase(Float wMoveLateralBase) {
        this.wMoveLateralBase = wMoveLateralBase;
    }

    public Float getwMoveUpModifier() {
        return wMoveUpModifier;
    }

    public void setwMoveUpModifier(Float wMoveUpModifier) {
        this.wMoveUpModifier = wMoveUpModifier;
    }

    public Float getwMoveLateralModifier() {
        return wMoveLateralModifier;
    }

    public void setwMoveLateralModifier(Float wMoveLateralModifier) {
        this.wMoveLateralModifier = wMoveLateralModifier;
    }

    public Float getwMoveUpMax() {
        return wMoveUpMax;
    }

    public void setwMoveUpMax(Float wMoveUpMax) {
        this.wMoveUpMax = wMoveUpMax;
    }

    public Float getwMoveLateralMax() {
        return wMoveLateralMax;
    }

    public void setwMoveLateralMax(Float wMoveLateralMax) {
        this.wMoveLateralMax = wMoveLateralMax;
    }

    public Float getwMoveDirChange() {
        return wMoveDirChange;
    }

    public void setwMoveDirChange(Float wMoveDirChange) {
        this.wMoveDirChange = wMoveDirChange;
    }

    public Float getwOnairUpBase() {
        return wOnairUpBase;
    }

    public void setwOnairUpBase(Float wOnairUpBase) {
        this.wOnairUpBase = wOnairUpBase;
    }

    public Float getwOnairLateralBase() {
        return wOnairLateralBase;
    }

    public void setwOnairLateralBase(Float wOnairLateralBase) {
        this.wOnairLateralBase = wOnairLateralBase;
    }

    public Float getwOnairUpModifier() {
        return wOnairUpModifier;
    }

    public void setwOnairUpModifier(Float wOnairUpModifier) {
        this.wOnairUpModifier = wOnairUpModifier;
    }

    public Float getwOnairLateralModifier() {
        return wOnairLateralModifier;
    }

    public void setwOnairLateralModifier(Float wOnairLateralModifier) {
        this.wOnairLateralModifier = wOnairLateralModifier;
    }

    public Float getwOnairUpMax() {
        return wOnairUpMax;
    }

    public void setwOnairUpMax(Float wOnairUpMax) {
        this.wOnairUpMax = wOnairUpMax;
    }

    public Float getwOnairLateralMax() {
        return wOnairLateralMax;
    }

    public void setwOnairLateralMax(Float wOnairLateralMax) {
        this.wOnairLateralMax = wOnairLateralMax;
    }

    public Float getwOnairDirChange() {
        return wOnairDirChange;
    }

    public void setwOnairDirChange(Float wOnairDirChange) {
        this.wOnairDirChange = wOnairDirChange;
    }

    public Float getwCrouchUpBase() {
        return wCrouchUpBase;
    }

    public void setwCrouchUpBase(Float wCrouchUpBase) {
        this.wCrouchUpBase = wCrouchUpBase;
    }

    public Float getwCrouchLateralBase() {
        return wCrouchLateralBase;
    }

    public void setwCrouchLateralBase(Float wCrouchLateralBase) {
        this.wCrouchLateralBase = wCrouchLateralBase;
    }

    public Float getwCrouchUpModifier() {
        return wCrouchUpModifier;
    }

    public void setwCrouchUpModifier(Float wCrouchUpModifier) {
        this.wCrouchUpModifier = wCrouchUpModifier;
    }

    public Float getwCrouchLateralModifier() {
        return wCrouchLateralModifier;
    }

    public void setwCrouchLateralModifier(Float wCrouchLateralModifier) {
        this.wCrouchLateralModifier = wCrouchLateralModifier;
    }

    public Float getwCrouchUpMax() {
        return wCrouchUpMax;
    }

    public void setwCrouchUpMax(Float wCrouchUpMax) {
        this.wCrouchUpMax = wCrouchUpMax;
    }

    public Float getwCrouchLateralMax() {
        return wCrouchLateralMax;
    }

    public void setwCrouchLateralMax(Float wCrouchLateralMax) {
        this.wCrouchLateralMax = wCrouchLateralMax;
    }

    public Float getwCrouchDirChange() {
        return wCrouchDirChange;
    }

    public void setwCrouchDirChange(Float wCrouchDirChange) {
        this.wCrouchDirChange = wCrouchDirChange;
    }

    public Float getwUpModifier() {
        return wUpModifier;
    }

    public void setwUpModifier(Float wUpModifier) {
        this.wUpModifier = wUpModifier;
    }

    public Float getwAccuracyTime() {
        return wAccuracyTime;
    }

    public void setwAccuracyTime(Float wAccuracyTime) {
        this.wAccuracyTime = wAccuracyTime;
    }

    public Float getwAccuracyTimeModifier() {
        return wAccuracyTimeModifier;
    }

    public void setwAccuracyTimeModifier(Float wAccuracyTimeModifier) {
        this.wAccuracyTimeModifier = wAccuracyTimeModifier;
    }

    public Float getwMaxAccuracy() {
        return wMaxAccuracy;
    }

    public void setwMaxAccuracy(Float wMaxAccuracy) {
        this.wMaxAccuracy = wMaxAccuracy;
    }

    public Float getwMinAccuracy() {
        return wMinAccuracy;
    }

    public void setwMinAccuracy(Float wMinAccuracy) {
        this.wMinAccuracy = wMinAccuracy;
    }

    public Integer getwShootBulletCount() {
        return wShootBulletCount;
    }

    public void setwShootBulletCount(Integer wShootBulletCount) {
        this.wShootBulletCount = wShootBulletCount;
    }

    public Float getwSpread() {
        return wSpread;
    }

    public void setwSpread(Float wSpread) {
        this.wSpread = wSpread;
    }

    public Float getwFireMaxSpeed() {
        return wFireMaxSpeed;
    }

    public void setwFireMaxSpeed(Float wFireMaxSpeed) {
        this.wFireMaxSpeed = wFireMaxSpeed;
    }

    public Float getwFireStartSpeed() {
        return wFireStartSpeed;
    }

    public void setwFireStartSpeed(Float wFireStartSpeed) {
        this.wFireStartSpeed = wFireStartSpeed;
    }

    public Float getwFireAceleration() {
        return wFireAceleration;
    }

    public void setwFireAceleration(Float wFireAceleration) {
        this.wFireAceleration = wFireAceleration;
    }

    public Float getwFireResistance() {
        return wFireResistance;
    }

    public void setwFireResistance(Float wFireResistance) {
        this.wFireResistance = wFireResistance;
    }

    public Float getwStabTime() {
        return wStabTime;
    }

    public void setwStabTime(Float wStabTime) {
        this.wStabTime = wStabTime;
    }

    public Float getwStabLightTime() {
        return wStabLightTime;
    }

    public void setwStabLightTime(Float wStabLightTime) {
        this.wStabLightTime = wStabLightTime;
    }

    public Float getwStabHurt() {
        return wStabHurt;
    }

    public void setwStabHurt(Float wStabHurt) {
        this.wStabHurt = wStabHurt;
    }

    public Float getwStabLightHurt() {
        return wStabLightHurt;
    }

    public void setwStabLightHurt(Float wStabLightHurt) {
        this.wStabLightHurt = wStabLightHurt;
    }

    public Float getwExplodeTime() {
        return wExplodeTime;
    }

    public void setwExplodeTime(Float wExplodeTime) {
        this.wExplodeTime = wExplodeTime;
    }

    public Float getwReadyTime() {
        return wReadyTime;
    }

    public void setwReadyTime(Float wReadyTime) {
        this.wReadyTime = wReadyTime;
    }

    public Float getwThrowOutTime() {
        return wThrowOutTime;
    }

    public void setwThrowOutTime(Float wThrowOutTime) {
        this.wThrowOutTime = wThrowOutTime;
    }

    public Float getwHurtRange() {
        return wHurtRange;
    }

    public void setwHurtRange(Float wHurtRange) {
        this.wHurtRange = wHurtRange;
    }

    public Float getwHurt() {
        return wHurt;
    }

    public void setwHurt(Float wHurt) {
        this.wHurt = wHurt;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public String getiValue() {
        return iValue;
    }

    public void setiValue(String iValue) {
        this.iValue = iValue == null ? null : iValue.trim();
    }

    public Integer getwXOffset() {
        return wXOffset;
    }

    public void setwXOffset(Integer wXOffset) {
        this.wXOffset = wXOffset;
    }

    public Float getwSightNormalOffset() {
        return wSightNormalOffset;
    }

    public void setwSightNormalOffset(Float wSightNormalOffset) {
        this.wSightNormalOffset = wSightNormalOffset;
    }

    public Float getwSightOnairOffset() {
        return wSightOnairOffset;
    }

    public void setwSightOnairOffset(Float wSightOnairOffset) {
        this.wSightOnairOffset = wSightOnairOffset;
    }

    public Float getwSightMoveOffset() {
        return wSightMoveOffset;
    }

    public void setwSightMoveOffset(Float wSightMoveOffset) {
        this.wSightMoveOffset = wSightMoveOffset;
    }

    public Float getwStabDistance() {
        return wStabDistance;
    }

    public void setwStabDistance(Float wStabDistance) {
        this.wStabDistance = wStabDistance;
    }

    public Float getwStabLightDistance() {
        return wStabLightDistance;
    }

    public void setwStabLightDistance(Float wStabLightDistance) {
        this.wStabLightDistance = wStabLightDistance;
    }

    public Float getwStabWidth() {
        return wStabWidth;
    }

    public void setwStabWidth(Float wStabWidth) {
        this.wStabWidth = wStabWidth;
    }

    public Float getwBackFactor() {
        return wBackFactor;
    }

    public void setwBackFactor(Float wBackFactor) {
        this.wBackFactor = wBackFactor;
    }

    public Float getwFlashRangeStart() {
        return wFlashRangeStart;
    }

    public void setwFlashRangeStart(Float wFlashRangeStart) {
        this.wFlashRangeStart = wFlashRangeStart;
    }

    public Float getwFlashRangeEnd() {
        return wFlashRangeEnd;
    }

    public void setwFlashRangeEnd(Float wFlashRangeEnd) {
        this.wFlashRangeEnd = wFlashRangeEnd;
    }

    public Float getwFlashBackFactor() {
        return wFlashBackFactor;
    }

    public void setwFlashBackFactor(Float wFlashBackFactor) {
        this.wFlashBackFactor = wFlashBackFactor;
    }

    public Float getwTimeMax() {
        return wTimeMax;
    }

    public void setwTimeMax(Float wTimeMax) {
        this.wTimeMax = wTimeMax;
    }

    public Float getwTimeFade() {
        return wTimeFade;
    }

    public void setwTimeFade(Float wTimeFade) {
        this.wTimeFade = wTimeFade;
    }

    public Float getwTime() {
        return wTime;
    }

    public void setwTime(Float wTime) {
        this.wTime = wTime;
    }

    public Float getwHitSpeed() {
        return wHitSpeed;
    }

    public void setwHitSpeed(Float wHitSpeed) {
        this.wHitSpeed = wHitSpeed;
    }

    public Float getwHitAcceleration() {
        return wHitAcceleration;
    }

    public void setwHitAcceleration(Float wHitAcceleration) {
        this.wHitAcceleration = wHitAcceleration;
    }

    public Float getwHitDistance() {
        return wHitDistance;
    }

    public void setwHitDistance(Float wHitDistance) {
        this.wHitDistance = wHitDistance;
    }

    public String getwSightInfo() {
        return wSightInfo;
    }

    public void setwSightInfo(String wSightInfo) {
        this.wSightInfo = wSightInfo == null ? null : wSightInfo.trim();
    }

    public Float getwMaxDistance() {
        return wMaxDistance;
    }

    public void setwMaxDistance(Float wMaxDistance) {
        this.wMaxDistance = wMaxDistance;
    }

    public Integer getwAddBlood() {
        return wAddBlood;
    }

    public void setwAddBlood(Integer wAddBlood) {
        this.wAddBlood = wAddBlood;
    }

    public Integer getwAmmoType() {
        return wAmmoType;
    }

    public void setwAmmoType(Integer wAmmoType) {
        this.wAmmoType = wAmmoType;
    }

    public Float getwFlySpeed() {
        return wFlySpeed;
    }

    public void setwFlySpeed(Float wFlySpeed) {
        this.wFlySpeed = wFlySpeed;
    }

    public Float getwMaxaliveTime() {
        return wMaxaliveTime;
    }

    public void setwMaxaliveTime(Float wMaxaliveTime) {
        this.wMaxaliveTime = wMaxaliveTime;
    }

    public Integer getwGravity() {
        return wGravity;
    }

    public void setwGravity(Integer wGravity) {
        this.wGravity = wGravity;
    }

    public Float getwLastHurt() {
        return wLastHurt;
    }

    public void setwLastHurt(Float wLastHurt) {
        this.wLastHurt = wLastHurt;
    }

    public Float getwLastTime() {
        return wLastTime;
    }

    public void setwLastTime(Float wLastTime) {
        this.wLastTime = wLastTime;
    }

    public Float getwSpecialDistance() {
        return wSpecialDistance;
    }

    public void setwSpecialDistance(Float wSpecialDistance) {
        this.wSpecialDistance = wSpecialDistance;
    }

    public Float getwSpecialRange() {
        return wSpecialRange;
    }

    public void setwSpecialRange(Float wSpecialRange) {
        this.wSpecialRange = wSpecialRange;
    }

    public Float getwSpecialLasttime() {
        return wSpecialLasttime;
    }

    public void setwSpecialLasttime(Float wSpecialLasttime) {
        this.wSpecialLasttime = wSpecialLasttime;
    }

    public Float getwSpecialHurt() {
        return wSpecialHurt;
    }

    public void setwSpecialHurt(Float wSpecialHurt) {
        this.wSpecialHurt = wSpecialHurt;
    }

    public Float getwSpecialLasthurt() {
        return wSpecialLasthurt;
    }

    public void setwSpecialLasthurt(Float wSpecialLasthurt) {
        this.wSpecialLasthurt = wSpecialLasthurt;
    }

    public Integer getwParticlemun() {
        return wParticlemun;
    }

    public void setwParticlemun(Integer wParticlemun) {
        this.wParticlemun = wParticlemun;
    }

    public Integer getwShowSpeed() {
        return wShowSpeed;
    }

    public void setwShowSpeed(Integer wShowSpeed) {
        this.wShowSpeed = wShowSpeed;
    }

    public String getwAmmopartKey() {
        return wAmmopartKey;
    }

    public void setwAmmopartKey(String wAmmopartKey) {
        this.wAmmopartKey = wAmmopartKey == null ? null : wAmmopartKey.trim();
    }

    public Integer getwCritRate() {
        return wCritRate;
    }

    public void setwCritRate(Integer wCritRate) {
        this.wCritRate = wCritRate;
    }

    public Integer getwCritAvailable() {
        return wCritAvailable;
    }

    public void setwCritAvailable(Integer wCritAvailable) {
        this.wCritAvailable = wCritAvailable;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId == null ? null : characterId.trim();
    }

    public Float getwDmgModifyTimerMin() {
        return wDmgModifyTimerMin;
    }

    public void setwDmgModifyTimerMin(Float wDmgModifyTimerMin) {
        this.wDmgModifyTimerMin = wDmgModifyTimerMin;
    }

    public Float getwDmgModifyTimerMax() {
        return wDmgModifyTimerMax;
    }

    public void setwDmgModifyTimerMax(Float wDmgModifyTimerMax) {
        this.wDmgModifyTimerMax = wDmgModifyTimerMax;
    }

    public Float getwDmgModifyMin() {
        return wDmgModifyMin;
    }

    public void setwDmgModifyMin(Float wDmgModifyMin) {
        this.wDmgModifyMin = wDmgModifyMin;
    }

    public Float getwDmgModifyMax() {
        return wDmgModifyMax;
    }

    public void setwDmgModifyMax(Float wDmgModifyMax) {
        this.wDmgModifyMax = wDmgModifyMax;
    }

    public Float getwCapsuleHeight() {
        return wCapsuleHeight;
    }

    public void setwCapsuleHeight(Float wCapsuleHeight) {
        this.wCapsuleHeight = wCapsuleHeight;
    }

    public Float getwCapsuleRadius() {
        return wCapsuleRadius;
    }

    public void setwCapsuleRadius(Float wCapsuleRadius) {
        this.wCapsuleRadius = wCapsuleRadius;
    }

    public Integer getwDamageModifer() {
        return wDamageModifer;
    }

    public void setwDamageModifer(Integer wDamageModifer) {
        this.wDamageModifer = wDamageModifer;
    }

    public Integer getiBuffId() {
        return iBuffId;
    }

    public void setiBuffId(Integer iBuffId) {
        this.iBuffId = iBuffId;
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

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public String getGunProperty5() {
        return gunProperty5;
    }

    public void setGunProperty5(String gunProperty5) {
        this.gunProperty5 = gunProperty5 == null ? null : gunProperty5.trim();
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items == null ? null : items.trim();
    }

    public Integer getBackBoostPlus() {
        return backBoostPlus;
    }

    public void setBackBoostPlus(Integer backBoostPlus) {
        this.backBoostPlus = backBoostPlus;
    }

    public Integer getwFixPrice() {
        return wFixPrice;
    }

    public void setwFixPrice(Integer wFixPrice) {
        this.wFixPrice = wFixPrice;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public Float getcResistanceFire() {
        return cResistanceFire;
    }

    public void setcResistanceFire(Float cResistanceFire) {
        this.cResistanceFire = cResistanceFire;
    }

    public Float getcResistanceBlast() {
        return cResistanceBlast;
    }

    public void setcResistanceBlast(Float cResistanceBlast) {
        this.cResistanceBlast = cResistanceBlast;
    }

    public Float getcResistanceBullet() {
        return cResistanceBullet;
    }

    public void setcResistanceBullet(Float cResistanceBullet) {
        this.cResistanceBullet = cResistanceBullet;
    }

    public Float getcResistanceKnife() {
        return cResistanceKnife;
    }

    public void setcResistanceKnife(Float cResistanceKnife) {
        this.cResistanceKnife = cResistanceKnife;
    }

    public Integer getcBloodAdd() {
        return cBloodAdd;
    }

    public void setcBloodAdd(Integer cBloodAdd) {
        this.cBloodAdd = cBloodAdd;
    }

    public Integer getIsStrengthen() {
        return isStrengthen;
    }

    public void setIsStrengthen(Integer isStrengthen) {
        this.isStrengthen = isStrengthen;
    }

    public Integer getmType() {
        return mType;
    }

    public void setmType(Integer mType) {
        this.mType = mType;
    }

    public Integer getmValue() {
        return mValue;
    }

    public void setmValue(Integer mValue) {
        this.mValue = mValue;
    }

    public Integer getIsWeb() {
        return isWeb;
    }

    public void setIsWeb(Integer isWeb) {
        this.isWeb = isWeb;
    }

    public Integer getFightNum() {
        return fightNum;
    }

    public void setFightNum(Integer fightNum) {
        this.fightNum = fightNum;
    }

    public Integer getRareLevel() {
        return rareLevel;
    }

    public void setRareLevel(Integer rareLevel) {
        this.rareLevel = rareLevel;
    }

    public Integer getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(Integer isPopular) {
        this.isPopular = isPopular;
    }

    public Integer getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Integer isExchange) {
        this.isExchange = isExchange;
    }

    public Integer getIsAsset() {
        return isAsset;
    }

    public void setIsAsset(Integer isAsset) {
        this.isAsset = isAsset;
    }

    public Integer getStrengthLevel() {
        return strengthLevel;
    }

    public void setStrengthLevel(Integer strengthLevel) {
        this.strengthLevel = strengthLevel;
    }

    public Float getEvaluateRmb() {
        return evaluateRmb;
    }

    public void setEvaluateRmb(Float evaluateRmb) {
        this.evaluateRmb = evaluateRmb;
    }

    public Float getTeamLevelOccupyLength() {
        return teamLevelOccupyLength;
    }

    public void setTeamLevelOccupyLength(Float teamLevelOccupyLength) {
        this.teamLevelOccupyLength = teamLevelOccupyLength;
    }

    public Float getTeamLevelOccupyWidth() {
        return teamLevelOccupyWidth;
    }

    public void setTeamLevelOccupyWidth(Float teamLevelOccupyWidth) {
        this.teamLevelOccupyWidth = teamLevelOccupyWidth;
    }

    public Integer getNeedTeamPlaceLevel() {
        return needTeamPlaceLevel;
    }

    public void setNeedTeamPlaceLevel(Integer needTeamPlaceLevel) {
        this.needTeamPlaceLevel = needTeamPlaceLevel;
    }

    public Float getTeamItemUpgradePrice() {
        return teamItemUpgradePrice;
    }

    public void setTeamItemUpgradePrice(Float teamItemUpgradePrice) {
        this.teamItemUpgradePrice = teamItemUpgradePrice;
    }

    public Integer getTimeForBuildingUp() {
        return timeForBuildingUp;
    }

    public void setTimeForBuildingUp(Integer timeForBuildingUp) {
        this.timeForBuildingUp = timeForBuildingUp;
    }

    public Integer getTimeForCreate() {
        return timeForCreate;
    }

    public void setTimeForCreate(Integer timeForCreate) {
        this.timeForCreate = timeForCreate;
    }

    public Integer getTimeForBuild() {
        return timeForBuild;
    }

    public void setTimeForBuild(Integer timeForBuild) {
        this.timeForBuild = timeForBuild;
    }

	public Map<String, ItemGunProperty> getGunPropertyMap() {
		return gunPropertyMap;
	}

	public void setGunPropertyMap(Map<String, ItemGunProperty> gunPropertyMap) {
		this.gunPropertyMap = gunPropertyMap;
	}

	public List<Integer> getCharacterIdList() {
        return characterIdList;
    }

    public void setCharacterIdList(List<Integer> characterIdList) {
        this.characterIdList = characterIdList;
    }

    public Map<String, Number> getNumberParamMap() {
        return numberParamMap;
    }

    public void setNumberParamMap(Map<String, Number> numberParamMap) {
        this.numberParamMap = numberParamMap;
    }

    public List<Integer> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Integer> itemsList) {
        this.itemsList = itemsList;
    }

    public List<String> getResourceStableList() {
        return resourceStableList;
    }

    public void setResourceStableList(List<String> resourceStableList) {
        this.resourceStableList = resourceStableList;
    }

    public List<List<String>> getResourceChangeableList() {
        return resourceChangeableList;
    }

    public void setResourceChangeableList(List<List<String>> resourceChangeableList) {
        this.resourceChangeableList = resourceChangeableList;
    }

	public List<JsonItemSight> getwSightInfoList() {
		return wSightInfoList;
	}

	public void setwSightInfoList(List<JsonItemSight> wSightInfoList) {
		this.wSightInfoList = wSightInfoList;
	}

	public Float getwStabTime1() {
        return wStabTime1;
    }

    public void setwStabTime1(Float wStabTime1) {
        this.wStabTime1 = wStabTime1;
    }

	public String getGunProperty() {
		return gunProperty;
	}

	public void setGunProperty(String gunProperty) {
		this.gunProperty = gunProperty;
	}

	public Integer getShoppingType() {
		return shoppingType;
	}

	public void setShoppingType(Integer shoppingType) {
		this.shoppingType = shoppingType;
	}

	public Date getShoppingStartTime() {
		return shoppingStartTime;
	}

	public void setShoppingStartTime(Date shoppingStartTime) {
		this.shoppingStartTime = shoppingStartTime;
	}

	public Date getShoppingEndTime() {
		return shoppingEndTime;
	}

	public void setShoppingEndTime(Date shoppingEndTime) {
		this.shoppingEndTime = shoppingEndTime;
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