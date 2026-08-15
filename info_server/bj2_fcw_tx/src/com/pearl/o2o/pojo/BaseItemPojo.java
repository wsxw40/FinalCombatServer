/**
 * 
 */
package com.pearl.o2o.pojo;

/**
 * @author bobby
 *
 */
public abstract class BaseItemPojo<T> extends BaseMappingBean<T> {
	private static final long serialVersionUID = 5897296108251213579L;
	protected Integer itemId;
	protected String 		name;
	protected String		resourceStable;
	protected String		resourceChangeable;
	protected Integer 	wId;
	protected Float 		wChangeInTime;
	protected Float 		wMoveSpeedOffset;
	protected Float 		wCrossOffset;
	protected Integer 	wAccuracyDivisor;
	protected Float 		wAccuracyOffset;
	protected Float 		wMaxInaccuracy;
	protected Integer 	wPenetration;
	protected Integer 	wDamage;
	protected Float 		wRangeModifier;
	protected Float 		wFireTime;
	protected Float 		wReloadTime;
	protected Integer 	wAmmoOneClip;
	protected Integer 	wAmmoCount;
	protected Integer 	wAutoFire;
	protected Float wTimeToIdle;
	protected Float wNormalOffset;
	protected Float wNormalFactor;
	protected Float wOnairOffset;
	protected Float wOnairFactor;
	protected Float wMoveOffset;
	protected Float wMoveFactor;
	protected Float wNormalUpBase;
	protected Float wNormalLateralBase;
	protected Float wNormalUpModifier;
	protected Float wNormalLateralModifier;
	protected Float wNormalUpMax;
	protected Float wNormalLateralMax;
	protected Float wNormalDirChange;
	protected Float wMoveUpBase;
	protected Float wMoveLateralBase;
	protected Float wMoveUpModifier;
	protected Float wMoveLateralModifier;
	protected Float wMoveUpMax;
	protected Float wMoveLateralMax;
	protected Float wMoveDirChange;
	protected Float wOnairUpBase;
	protected Float wOnairLateralBase;
	protected Float wOnairUpModifier;
	protected Float wOnairLateralModifier;
	protected Float wOnairUpMax;
	protected Float wOnairLateralMax;
	protected Float wOnairDirChange;
	protected Float wCrouchUpBase;
	protected Float wCrouchLateralBase;
	protected Float wCrouchUpModifier;
	protected Float wCrouchLateralModifier;
	protected Float wCrouchUpMax;
	protected Float wCrouchLateralMax;
	protected Float wCrouchDirChange;
	protected Float wUpModifier;
	
	protected Integer wShootBulletCount;
	protected Float wSpread;
	protected Float wFireMaxSpeed;
	protected Float wFireStartSpeed;
	protected Float wFireAceleration;
	protected Float wFireResistance;
	
	protected Float wStabTime;
	protected Float wStabLightTime;
	protected Float wStabHurt;
	protected Float wStabLightHurt;
	protected Float wExplodeTime;
	protected Float wReadyTime;
	protected Float wThrowOutTime;
	protected Float wHurtRange;
	protected Float wHurt;
	protected Float wRangeStart;
	protected Float	wRangeEnd;
	protected Float	wAccuracyTime;
	protected Float	wAccuracyTimeModefier;
	protected Float	wMaxAccuracy;
	protected Float	wMinAccuracy;
	protected Float	wCrossLengthBase;
	protected Float	wCrossLengthFactor;
	protected Float	wCrossDistanceBase;
	protected Float	wCrossDistanceFactor;
	
	protected Float	wXOffset;
	protected Float	wSightNormalOffset;
	protected Float	wSightOnairOffset;
	protected Float	wSightMoveOffset;
	protected Float	wStabDistance;
	protected Float	wStabLightDistance;
	protected Float	wStabWidth;
	protected Float	wBackFactor;
	protected Float	wFlashRangeStart;
	protected Float	wFlashRangeEnd;
	protected Float wFlashBackFactor;
	protected Float	wTimeMax;
	protected Float	wTimeFade;
	protected Float	wTime;
	
	protected Float wHitSpeed;
	protected Float wHitAcceleration;
	protected Float wHitDistance;
	
	protected String wSightInfo;
	
	protected Float wMaxDistance;
	protected Integer wAddBlood;
	protected Integer wAmmoType;
	
	protected Float wFlySpeed;
	protected Float wMaxaliveTime;
	protected int wGravity;
	protected Float wLastHurt;
	protected Float wLastTime;
	protected Float wSpecialDistance;
	protected Float wSpecialRange;
	protected Float wSpecialLasttime;
	protected Float wSpecialHurt;
	protected Float wSpecialLasthurt;
	protected Float wParticlenum;
	protected Float wShowSpeed;
	protected String resource[];
	protected String wAmmopartKey;
	protected Integer wCritRate;
	protected Integer wCritAvailable;
	protected Float wDmgModifyTimerMin;
	protected Float wDmgModifyTimerMax;
	protected Float wDmgModifyMin;
	protected Float wDmgModifyMax;
	protected Float wCapsuleHeight;
	protected Float wCapsuleRadius;
	protected Integer wDamageModifer=0;
	protected Integer wBackBoostPlus=0;
	
	public Integer getWBackBoostPlus() {
		return wBackBoostPlus;
	}
	public void setWBackBoostPlus(Integer backBoostPlus) {
		wBackBoostPlus = backBoostPlus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResourceStable() {
		return resourceStable;
	}
	public void setResourceStable(String resourceStable) {
		this.resourceStable = resourceStable;
	}
	public String getResourceChangeable() {
		return resourceChangeable;
	}
	public void setResourceChangeable(String resourceChangeable) {
		this.resourceChangeable = resourceChangeable;
	}
	public Integer getWId() {
		return wId;
	}
	public void setWId(Integer id) {
		wId = id;
	}
	public Float getWChangeInTime() {
		return wChangeInTime;
	}
	public void setWChangeInTime(Float changeInTime) {
		wChangeInTime = changeInTime;
	}
	public Float getWMoveSpeedOffset() {
		return wMoveSpeedOffset;
	}
	public void setWMoveSpeedOffset(Float moveSpeedOffset) {
		wMoveSpeedOffset = moveSpeedOffset;
	}
	public Float getWCrossOffset() {
		return wCrossOffset;
	}
	public void setWCrossOffset(Float crossOffset) {
		wCrossOffset = crossOffset;
	}
	public Integer getWAccuracyDivisor() {
		return wAccuracyDivisor;
	}
	public void setWAccuracyDivisor(Integer accuracyDivisor) {
		wAccuracyDivisor = accuracyDivisor;
	}
	public Float getWAccuracyOffset() {
		return wAccuracyOffset;
	}
	public void setWAccuracyOffset(Float accuracyOffset) {
		wAccuracyOffset = accuracyOffset;
	}
	public Float getWMaxInaccuracy() {
		return wMaxInaccuracy;
	}
	public void setWMaxInaccuracy(Float maxInaccuracy) {
		wMaxInaccuracy = maxInaccuracy;
	}
	public Integer getWPenetration() {
		return wPenetration;
	}
	public void setWPenetration(Integer penetration) {
		wPenetration = penetration;
	}
	public Integer getWDamage() {
		return wDamage;
	}
	public void setWDamage(Integer damage) {
		wDamage = damage;
	}
	public Float getWRangeModifier() {
		return wRangeModifier;
	}
	public void setWRangeModifier(Float rangeModifier) {
		wRangeModifier = rangeModifier;
	}
	public Float getWFireTime() {
		return wFireTime;
	}
	public void setWFireTime(Float fireTime) {
		wFireTime = fireTime;
	}
	public Float getWReloadTime() {
		return wReloadTime;
	}
	public void setWReloadTime(Float reloadTime) {
		wReloadTime = reloadTime;
	}
	public Integer getWAmmoOneClip() {
		return wAmmoOneClip;
	}
	public void setWAmmoOneClip(Integer ammoOneClip) {
		wAmmoOneClip = ammoOneClip;
	}
	public Integer getWAmmoCount() {
		return wAmmoCount;
	}
	public void setWAmmoCount(Integer ammoCount) {
		wAmmoCount = ammoCount;
	}
	public Integer getWAutoFire() {
		return wAutoFire;
	}
	public void setWAutoFire(Integer autoFire) {
		wAutoFire = autoFire;
	}
	public Float getWTimeToIdle() {
		return wTimeToIdle;
	}
	public void setWTimeToIdle(Float timeToIdle) {
		wTimeToIdle = timeToIdle;
	}
	public Float getWNormalOffset() {
		return wNormalOffset;
	}
	public void setWNormalOffset(Float normalOffset) {
		wNormalOffset = normalOffset;
	}
	public Float getWNormalFactor() {
		return wNormalFactor;
	}
	public void setWNormalFactor(Float normalFactor) {
		wNormalFactor = normalFactor;
	}
	public Float getWOnairOffset() {
		return wOnairOffset;
	}
	public void setWOnairOffset(Float onairOffset) {
		wOnairOffset = onairOffset;
	}
	public Float getWOnairFactor() {
		return wOnairFactor;
	}
	public void setWOnairFactor(Float onairFactor) {
		wOnairFactor = onairFactor;
	}
	public Float getWMoveOffset() {
		return wMoveOffset;
	}
	public void setWMoveOffset(Float moveOffset) {
		wMoveOffset = moveOffset;
	}
	public Float getWMoveFactor() {
		return wMoveFactor;
	}
	public void setWMoveFactor(Float moveFactor) {
		wMoveFactor = moveFactor;
	}
	public Float getWNormalUpBase() {
		return wNormalUpBase;
	}
	public void setWNormalUpBase(Float normalUpBase) {
		wNormalUpBase = normalUpBase;
	}
	public Float getWNormalLateralBase() {
		return wNormalLateralBase;
	}
	public void setWNormalLateralBase(Float normalLateralBase) {
		wNormalLateralBase = normalLateralBase;
	}
	public Float getWNormalUpModifier() {
		return wNormalUpModifier;
	}
	public void setWNormalUpModifier(Float normalUpModifier) {
		wNormalUpModifier = normalUpModifier;
	}
	public Float getWNormalLateralModifier() {
		return wNormalLateralModifier;
	}
	public void setWNormalLateralModifier(Float normalLateralModifier) {
		wNormalLateralModifier = normalLateralModifier;
	}
	public Float getWNormalUpMax() {
		return wNormalUpMax;
	}
	public void setWNormalUpMax(Float normalUpMax) {
		wNormalUpMax = normalUpMax;
	}
	public Float getWNormalLateralMax() {
		return wNormalLateralMax;
	}
	public void setWNormalLateralMax(Float normalLateralMax) {
		wNormalLateralMax = normalLateralMax;
	}
	public Float getWNormalDirChange() {
		return wNormalDirChange;
	}
	public void setWNormalDirChange(Float normalDirChange) {
		wNormalDirChange = normalDirChange;
	}
	public Float getWMoveUpBase() {
		return wMoveUpBase;
	}
	public void setWMoveUpBase(Float moveUpBase) {
		wMoveUpBase = moveUpBase;
	}
	public Float getWMoveLateralBase() {
		return wMoveLateralBase;
	}
	public void setWMoveLateralBase(Float moveLateralBase) {
		wMoveLateralBase = moveLateralBase;
	}
	public Float getWMoveUpModifier() {
		return wMoveUpModifier;
	}
	public void setWMoveUpModifier(Float moveUpModifier) {
		wMoveUpModifier = moveUpModifier;
	}
	public Float getWMoveLateralModifier() {
		return wMoveLateralModifier;
	}
	public void setWMoveLateralModifier(Float moveLateralModifier) {
		wMoveLateralModifier = moveLateralModifier;
	}
	public Float getWMoveUpMax() {
		return wMoveUpMax;
	}
	public void setWMoveUpMax(Float moveUpMax) {
		wMoveUpMax = moveUpMax;
	}
	public Float getWMoveLateralMax() {
		return wMoveLateralMax;
	}
	public void setWMoveLateralMax(Float moveLateralMax) {
		wMoveLateralMax = moveLateralMax;
	}
	public Float getWMoveDirChange() {
		return wMoveDirChange;
	}
	public void setWMoveDirChange(Float moveDirChange) {
		wMoveDirChange = moveDirChange;
	}
	public Float getWOnairUpBase() {
		return wOnairUpBase;
	}
	public void setWOnairUpBase(Float onairUpBase) {
		wOnairUpBase = onairUpBase;
	}
	public Float getWOnairLateralBase() {
		return wOnairLateralBase;
	}
	public void setWOnairLateralBase(Float onairLateralBase) {
		wOnairLateralBase = onairLateralBase;
	}
	public Float getWOnairUpModifier() {
		return wOnairUpModifier;
	}
	public void setWOnairUpModifier(Float onairUpModifier) {
		wOnairUpModifier = onairUpModifier;
	}
	public Float getWOnairLateralModifier() {
		return wOnairLateralModifier;
	}
	public void setWOnairLateralModifier(Float onairLateralModifier) {
		wOnairLateralModifier = onairLateralModifier;
	}
	public Float getWOnairUpMax() {
		return wOnairUpMax;
	}
	public void setWOnairUpMax(Float onairUpMax) {
		wOnairUpMax = onairUpMax;
	}
	public Float getWOnairLateralMax() {
		return wOnairLateralMax;
	}
	public void setWOnairLateralMax(Float onairLateralMax) {
		wOnairLateralMax = onairLateralMax;
	}
	public Float getWOnairDirChange() {
		return wOnairDirChange;
	}
	public void setWOnairDirChange(Float onairDirChange) {
		wOnairDirChange = onairDirChange;
	}
	public Float getWCrouchUpBase() {
		return wCrouchUpBase;
	}
	public void setWCrouchUpBase(Float crouchUpBase) {
		wCrouchUpBase = crouchUpBase;
	}
	public Float getWCrouchLateralBase() {
		return wCrouchLateralBase;
	}
	public void setWCrouchLateralBase(Float crouchLateralBase) {
		wCrouchLateralBase = crouchLateralBase;
	}
	public Float getWCrouchUpModifier() {
		return wCrouchUpModifier;
	}
	public void setWCrouchUpModifier(Float crouchUpModifier) {
		wCrouchUpModifier = crouchUpModifier;
	}
	public Float getWCrouchLateralModifier() {
		return wCrouchLateralModifier;
	}
	public void setWCrouchLateralModifier(Float crouchLateralModifier) {
		wCrouchLateralModifier = crouchLateralModifier;
	}
	public Float getWCrouchUpMax() {
		return wCrouchUpMax;
	}
	public void setWCrouchUpMax(Float crouchUpMax) {
		wCrouchUpMax = crouchUpMax;
	}
	public Float getWCrouchLateralMax() {
		return wCrouchLateralMax;
	}
	public void setWCrouchLateralMax(Float crouchLateralMax) {
		wCrouchLateralMax = crouchLateralMax;
	}
	public Float getWCrouchDirChange() {
		return wCrouchDirChange;
	}
	public void setWCrouchDirChange(Float crouchDirChange) {
		wCrouchDirChange = crouchDirChange;
	}
	public Float getWUpModifier() {
		return wUpModifier;
	}
	public void setWUpModifier(Float upModifier) {
		wUpModifier = upModifier;
	}
	public Integer getWShootBulletCount() {
		return wShootBulletCount;
	}
	public void setWShootBulletCount(Integer shootBulletCount) {
		wShootBulletCount = shootBulletCount;
	}
	public Float getWSpread() {
		return wSpread;
	}
	public void setWSpread(Float spread) {
		wSpread = spread;
	}
	public Float getWFireMaxSpeed() {
		return wFireMaxSpeed;
	}
	public void setWFireMaxSpeed(Float fireMaxSpeed) {
		wFireMaxSpeed = fireMaxSpeed;
	}
	public Float getWFireStartSpeed() {
		return wFireStartSpeed;
	}
	public void setWFireStartSpeed(Float fireStartSpeed) {
		wFireStartSpeed = fireStartSpeed;
	}
	public Float getWFireAceleration() {
		return wFireAceleration;
	}
	public void setWFireAceleration(Float fireAceleration) {
		wFireAceleration = fireAceleration;
	}
	public Float getWFireResistance() {
		return wFireResistance;
	}
	public void setWFireResistance(Float fireResistance) {
		wFireResistance = fireResistance;
	}
	public Float getWStabTime() {
		return wStabTime;
	}
	public void setWStabTime(Float stabTime) {
		wStabTime = stabTime;
	}
	public Float getWStabLightTime() {
		return wStabLightTime;
	}
	public void setWStabLightTime(Float stabLightTime) {
		wStabLightTime = stabLightTime;
	}
	public Float getWStabHurt() {
		return wStabHurt;
	}
	public void setWStabHurt(Float stabHurt) {
		wStabHurt = stabHurt;
	}
	public Float getWStabLightHurt() {
		return wStabLightHurt;
	}
	public void setWStabLightHurt(Float stabLightHurt) {
		wStabLightHurt = stabLightHurt;
	}
	public Float getWExplodeTime() {
		return wExplodeTime;
	}
	public void setWExplodeTime(Float explodeTime) {
		wExplodeTime = explodeTime;
	}
	public Float getWReadyTime() {
		return wReadyTime;
	}
	public void setWReadyTime(Float readyTime) {
		wReadyTime = readyTime;
	}
	public Float getWThrowOutTime() {
		return wThrowOutTime;
	}
	public void setWThrowOutTime(Float throwOutTime) {
		wThrowOutTime = throwOutTime;
	}
	public Float getWHurtRange() {
		return wHurtRange;
	}
	public void setWHurtRange(Float hurtRange) {
		wHurtRange = hurtRange;
	}
	public Float getWHurt() {
		return wHurt;
	}
	public void setWHurt(Float hurt) {
		wHurt = hurt;
	}
	public Float getWRangeStart() {
		return wRangeStart;
	}
	public void setWRangeStart(Float rangeStart) {
		wRangeStart = rangeStart;
	}
	public Float getWRangeEnd() {
		return wRangeEnd;
	}
	public void setWRangeEnd(Float rangeEnd) {
		wRangeEnd = rangeEnd;
	}
	public Float getWAccuracyTime() {
		return wAccuracyTime;
	}
	public void setWAccuracyTime(Float accuracyTime) {
		wAccuracyTime = accuracyTime;
	}
	public Float getWAccuracyTimeModefier() {
		return wAccuracyTimeModefier;
	}
	public void setWAccuracyTimeModefier(Float accuracyTimeModefier) {
		wAccuracyTimeModefier = accuracyTimeModefier;
	}
	public Float getWMaxAccuracy() {
		return wMaxAccuracy;
	}
	public void setWMaxAccuracy(Float maxAccuracy) {
		wMaxAccuracy = maxAccuracy;
	}
	public Float getWMinAccuracy() {
		return wMinAccuracy;
	}
	public void setWMinAccuracy(Float minAccuracy) {
		wMinAccuracy = minAccuracy;
	}
	public Float getWCrossLengthBase() {
		return wCrossLengthBase;
	}
	public void setWCrossLengthBase(Float crossLengthBase) {
		wCrossLengthBase = crossLengthBase;
	}
	public Float getWCrossLengthFactor() {
		return wCrossLengthFactor;
	}
	public void setWCrossLengthFactor(Float crossLengthFactor) {
		wCrossLengthFactor = crossLengthFactor;
	}
	public Float getWCrossDistanceBase() {
		return wCrossDistanceBase;
	}
	public void setWCrossDistanceBase(Float crossDistanceBase) {
		wCrossDistanceBase = crossDistanceBase;
	}
	public Float getWCrossDistanceFactor() {
		return wCrossDistanceFactor;
	}
	public void setWCrossDistanceFactor(Float crossDistanceFactor) {
		wCrossDistanceFactor = crossDistanceFactor;
	}
	public Float getWXOffset() {
		return wXOffset;
	}
	public void setWXOffset(Float offset) {
		wXOffset = offset;
	}
	public Float getWSightNormalOffset() {
		return wSightNormalOffset;
	}
	public void setWSightNormalOffset(Float sightNormalOffset) {
		wSightNormalOffset = sightNormalOffset;
	}
	public Float getWSightOnairOffset() {
		return wSightOnairOffset;
	}
	public void setWSightOnairOffset(Float sightOnairOffset) {
		wSightOnairOffset = sightOnairOffset;
	}
	public Float getWSightMoveOffset() {
		return wSightMoveOffset;
	}
	public void setWSightMoveOffset(Float sightMoveOffset) {
		wSightMoveOffset = sightMoveOffset;
	}
	public Float getWStabDistance() {
		return wStabDistance;
	}
	public void setWStabDistance(Float stabDistance) {
		wStabDistance = stabDistance;
	}
	public Float getWStabLightDistance() {
		return wStabLightDistance;
	}
	public void setWStabLightDistance(Float stabLightDistance) {
		wStabLightDistance = stabLightDistance;
	}
	public Float getWStabWidth() {
		return wStabWidth;
	}
	public void setWStabWidth(Float stabWidth) {
		wStabWidth = stabWidth;
	}
	public Float getWBackFactor() {
		return wBackFactor;
	}
	public void setWBackFactor(Float backFactor) {
		wBackFactor = backFactor;
	}
	public Float getWFlashRangeStart() {
		return wFlashRangeStart;
	}
	public void setWFlashRangeStart(Float flashRangeStart) {
		wFlashRangeStart = flashRangeStart;
	}
	public Float getWFlashRangeEnd() {
		return wFlashRangeEnd;
	}
	public void setWFlashRangeEnd(Float flashRangeEnd) {
		wFlashRangeEnd = flashRangeEnd;
	}
	public Float getWFlashBackFactor() {
		return wFlashBackFactor;
	}
	public void setWFlashBackFactor(Float flashBackFactor) {
		wFlashBackFactor = flashBackFactor;
	}
	public Float getWTimeMax() {
		return wTimeMax;
	}
	public void setWTimeMax(Float timeMax) {
		wTimeMax = timeMax;
	}
	public Float getWTimeFade() {
		return wTimeFade;
	}
	public void setWTimeFade(Float timeFade) {
		wTimeFade = timeFade;
	}
	public Float getWTime() {
		return wTime;
	}
	public void setWTime(Float time) {
		wTime = time;
	}
	public Float getWHitSpeed() {
		return wHitSpeed;
	}
	public void setWHitSpeed(Float hitSpeed) {
		wHitSpeed = hitSpeed;
	}
	public Float getWHitAcceleration() {
		return wHitAcceleration;
	}
	public void setWHitAcceleration(Float hitAcceleration) {
		wHitAcceleration = hitAcceleration;
	}
	public Float getWHitDistance() {
		return wHitDistance;
	}
	public void setWHitDistance(Float hitDistance) {
		wHitDistance = hitDistance;
	}
	public String getWSightInfo() {
		return wSightInfo;
	}
	public void setWSightInfo(String sightInfo) {
		wSightInfo = sightInfo;
	}
	public String[] getResource() {
		return resource;
	}
	public void setResource(String[] resource) {
		this.resource = resource;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Float getWMaxDistance() {
		return wMaxDistance;
	}
	public void setWMaxDistance(Float maxDistance) {
		wMaxDistance = maxDistance;
	}

	public Integer getWAddBlood() {
		return wAddBlood;
	}
	public void setWAddBlood(Integer addBlood) {
		wAddBlood = addBlood;
	}
	public Integer getWAmmoType() {
		return wAmmoType;
	}
	public void setWAmmoType(Integer ammoType) {
		wAmmoType = ammoType;
	}
	public Float getWFlySpeed() {
		return wFlySpeed;
	}
	public void setWFlySpeed(Float flySpeed) {
		wFlySpeed = flySpeed;
	}
	public Float getWMaxaliveTime() {
		return wMaxaliveTime;
	}
	public void setWMaxaliveTime(Float maxaliveTime) {
		wMaxaliveTime = maxaliveTime;
	}
	public Float getWLastHurt() {
		return wLastHurt;
	}
	public void setWLastHurt(Float lastHurt) {
		wLastHurt = lastHurt;
	}
	public Float getWLastTime() {
		return wLastTime;
	}
	public void setWLastTime(Float lastTime) {
		wLastTime = lastTime;
	}
	public Float getWSpecialDistance() {
		return wSpecialDistance;
	}
	public void setWSpecialDistance(Float specialDistance) {
		wSpecialDistance = specialDistance;
	}
	public Float getWSpecialRange() {
		return wSpecialRange;
	}
	public void setWSpecialRange(Float specialRange) {
		wSpecialRange = specialRange;
	}
	public Float getWSpecialLasttime() {
		return wSpecialLasttime;
	}
	public void setWSpecialLasttime(Float specialLasttime) {
		wSpecialLasttime = specialLasttime;
	}
	public Float getWSpecialHurt() {
		return wSpecialHurt;
	}
	public void setWSpecialHurt(Float specialHurt) {
		wSpecialHurt = specialHurt;
	}
	public Float getWSpecialLasthurt() {
		return wSpecialLasthurt;
	}
	public void setWSpecialLasthurt(Float specialLasthurt) {
		wSpecialLasthurt = specialLasthurt;
	}
	public Float getWParticlenum() {
		return wParticlenum;
	}
	public void setWParticlenum(Float particlenum) {
		wParticlenum = particlenum;
	}
	public Float getWShowSpeed() {
		return wShowSpeed;
	}
	public void setWShowSpeed(Float showSpeed) {
		wShowSpeed = showSpeed;
	}
	public int getWGravity() {
		return wGravity;
	}
	public void setWGravity(int gravity) {
		wGravity = gravity;
	}
	public String getWAmmopartKey() {
		return wAmmopartKey;
	}
	public void setWAmmopartKey(String ammopartKey) {
		wAmmopartKey = ammopartKey;
	}
	public Integer getWCritRate() {
		return wCritRate;
	}
	public void setWCritRate(Integer critRate) {
		wCritRate = critRate;
	}
	public Integer getWCritAvailable() {
		return wCritAvailable;
	}
	public void setWCritAvailable(Integer critAvailable) {
		wCritAvailable = critAvailable;
	}
	public Float getWDmgModifyTimerMin() {
		return wDmgModifyTimerMin;
	}
	public void setWDmgModifyTimerMin(Float dmgModifyTimerMin) {
		wDmgModifyTimerMin = dmgModifyTimerMin;
	}
	public Float getWDmgModifyTimerMax() {
		return wDmgModifyTimerMax;
	}
	public void setWDmgModifyTimerMax(Float dmgModifyTimerMax) {
		wDmgModifyTimerMax = dmgModifyTimerMax;
	}
	public Float getWDmgModifyMin() {
		return wDmgModifyMin;
	}
	public void setWDmgModifyMin(Float dmgModifyMin) {
		wDmgModifyMin = dmgModifyMin;
	}
	public Float getWDmgModifyMax() {
		return wDmgModifyMax;
	}
	public void setWDmgModifyMax(Float dmgModifyMax) {
		wDmgModifyMax = dmgModifyMax;
	}
	public Float getWCapsuleHeight() {
		return wCapsuleHeight;
	}
	public void setWCapsuleHeight(Float capsuleHeight) {
		wCapsuleHeight = capsuleHeight;
	}
	public Float getWCapsuleRadius() {
		return wCapsuleRadius;
	}
	public void setWCapsuleRadius(Float capsuleRadius) {
		wCapsuleRadius = capsuleRadius;
	}
	public Integer getWDamageModifer() {
		return wDamageModifer;
	}
	public void setWDamageModifer(Integer damageModifer) {
		wDamageModifer = damageModifer;
	}

}
