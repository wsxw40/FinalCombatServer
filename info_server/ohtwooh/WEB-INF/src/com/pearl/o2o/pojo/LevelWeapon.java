package com.pearl.o2o.pojo;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.StringUtil;

public class LevelWeapon implements Serializable{
	
		private static final long serialVersionUID = -1774380518543769733L;
		
		public void writeByte(OutputStream out) throws IOException {
			out.write(BinaryUtil.toByta(wId==null?0:wId));		//Weapon ID
			if(wId != null && wId > 0){
				out.write(BinaryUtil.toByta(name));				//Weapon Name
				out.write(BinaryUtil.toByta(wChangeInTime));	//Change In Time
				out.write(BinaryUtil.toByta(wMoveSpeedOffset));	//Move Speed Offset
				out.write(BinaryUtil.toByta(wCrossOffset));		//Cross Offset
				out.write(BinaryUtil.toByta(wCrossLengthBase));		//wCrossLengthBase
				out.write(BinaryUtil.toByta(wCrossLengthFactor));		//wCrossLengthFactor
				out.write(BinaryUtil.toByta(wCrossDistanceBase));		//wCrossDistanceBase
				out.write(BinaryUtil.toByta(wCrossDistanceFactor));		//wCrossDistanceFactor
				//Resources stable
				String[] resStable = resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);
				//Resources changeable
				String[] resChangeable = resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				List<String> changeableRes = new ArrayList<String>();
				for (String res : resChangeable){
					if(!StringUtil.isEmptyString(res)){
						String [] ress = res.split(Constants.DELIMITER_RESOURCE_STABLE);
						for (String s: ress) {
							if (!StringUtil.isEmptyString(s)){
								changeableRes.add(s);
							}
						}
					}
				}
				
				
				out.write(BinaryUtil.toByta(resStable.length + changeableRes.size()));			
				for(String res : resStable ){
					StringBuffer path = new StringBuffer(name).append("/").append(res);
					out.write(BinaryUtil.toByta(path.toString()));				
				}
				
				for(String changeableStr : changeableRes){
					StringBuffer path = new StringBuffer(name).append("/").append(changeableStr);
					out.write(BinaryUtil.toByta(path.toString()));
				}
				
				if(wId < Constants.WEAPON_KNIFE_ID){								
					//Gun Info
					out.write(BinaryUtil.toByta(wAccuracyDivisor));				//Accuracy Divisor
					out.write(BinaryUtil.toByta(wAccuracyOffset));				//Accuracy Offset
					out.write(BinaryUtil.toByta(wMaxInaccuracy));				//Max Inaccuracy
					out.write(BinaryUtil.toByta(wPenetration));					//Penetration
					out.write(BinaryUtil.toByta(wDamage));						//Damage
					out.write(BinaryUtil.toByta(wRangeModifier));				//Range Modifier
					out.write(BinaryUtil.toByta(wRangeStart));				//wRangeStart
					out.write(BinaryUtil.toByta(wRangeEnd));				//wRangeEnd
					out.write(BinaryUtil.toByta(wFireTime));					//Fire Time
					out.write(BinaryUtil.toByta(wReloadTime));					//Reload Time
					out.write(BinaryUtil.toByta(wZoomTime));					//Zoom Time
					out.write(BinaryUtil.toByta(wZoomValue));					//Zoom Value
					out.write(BinaryUtil.toByta(wAmmoOneClip));					//Ammo One Clip
					out.write(BinaryUtil.toByta(wAmmoCount));					//Ammo Count
					out.write(BinaryUtil.toByta(wAutoFire.byteValue()));		//Auto Fire
					out.write(BinaryUtil.toByta(wTimeToIdle));					//Time To Idle
					out.write(BinaryUtil.toByta(wNormalOffset));				//Normal Offset
					out.write(BinaryUtil.toByta(wNormalFactor));				//Normal Factor
					out.write(BinaryUtil.toByta(wOnairOffset));					//Onair Offset
					out.write(BinaryUtil.toByta(wOnairFactor));					//Onair Factor
					out.write(BinaryUtil.toByta(wMoveOffset));					//Move Offset
					out.write(BinaryUtil.toByta(wMoveFactor));					//Move Factor
					
					if(wId == Constants.WEAPON_PISTOL_ID){
						out.write(BinaryUtil.toByta(wUpModifier)); //wUpModifier
						out.write(BinaryUtil.toByta(wAccuracyTime)); //wAccuracyTime
						out.write(BinaryUtil.toByta(wAccuracyTimeModefier)); //wAccuracyTimeModefier
						out.write(BinaryUtil.toByta(wMaxAccuracy)); //wMaxAccuracy
						out.write(BinaryUtil.toByta(wMinAccuracy)); //wMinAccuracy
					}else if(wId == Constants.WEAPON_SNIPER_GUN_ID){
						out.write(BinaryUtil.toByta(wSightNormalOffset));	
						out.write(BinaryUtil.toByta(wSightOnairOffset));	
						out.write(BinaryUtil.toByta(wSightMoveOffset));			//Up Base
					}else if(wId == Constants.WEAPON_SHOT_GUN_ID){				
						out.write(BinaryUtil.toByta(wShootBulletCount));	
						out.write(BinaryUtil.toByta(wSpread));	
						out.write(BinaryUtil.toByta(wNormalUpBase));			//Up Base
						out.write(BinaryUtil.toByta(wNormalUpModifier));		//Up Modifier
						out.write(BinaryUtil.toByta(wNormalUpMax));				//Up Max
					}
					else if(wId == Constants.WEAPON_RIFFLE_ID || wId == Constants.WEAPON_SUBMACHINE_ID 
							|| wId == Constants.WEAPON_MACHINE_GUN_ID|| wId == Constants.WEAPON_MINI_GUN_ID){
						//Rifle Info	
						//Normal
						out.write(BinaryUtil.toByta(wNormalUpBase));			//Up Base
						out.write(BinaryUtil.toByta(wNormalLateralBase));		//Lateral Base
						out.write(BinaryUtil.toByta(wNormalUpModifier));		//Up Modifier
						out.write(BinaryUtil.toByta(wNormalLateralModifier));	//Lateral Modifier
						out.write(BinaryUtil.toByta(wNormalUpMax));				//Up Max
						out.write(BinaryUtil.toByta(wNormalLateralMax));		//Lateral Max
						out.write(BinaryUtil.toByta(wNormalDirChange));			//Dir Change
						//Move
						out.write(BinaryUtil.toByta(wMoveUpBase));				//Up Base
						out.write(BinaryUtil.toByta(wMoveLateralBase));			//Lateral Base
						out.write(BinaryUtil.toByta(wMoveUpModifier));			//Up Modifier
						out.write(BinaryUtil.toByta(wMoveLateralModifier));		//Lateral Modifier
						out.write(BinaryUtil.toByta(wMoveUpMax));				//Up Max
						out.write(BinaryUtil.toByta(wMoveLateralMax));			//Lateral Max
						out.write(BinaryUtil.toByta(wMoveDirChange));			//Dir Change
						//Onair
						out.write(BinaryUtil.toByta(wOnairUpBase));				//Up Base
						out.write(BinaryUtil.toByta(wOnairLateralBase));		//Lateral Base
						out.write(BinaryUtil.toByta(wOnairUpModifier));			//Up Modifier
						out.write(BinaryUtil.toByta(wOnairLateralModifier));	//Lateral Modifier
						out.write(BinaryUtil.toByta(wOnairUpMax));				//Up Max
						out.write(BinaryUtil.toByta(wOnairLateralMax));			//Lateral Max
						out.write(BinaryUtil.toByta(wOnairDirChange));			//Dir Change
						//Crouch
						out.write(BinaryUtil.toByta(wCrouchUpBase));			//Up Base
						out.write(BinaryUtil.toByta(wCrouchLateralBase));		//Lateral Base
						out.write(BinaryUtil.toByta(wCrouchUpModifier));		//Up Modifier
						out.write(BinaryUtil.toByta(wCrouchLateralModifier));	//Lateral Modifier
						out.write(BinaryUtil.toByta(wCrouchUpMax));				//Up Max
						out.write(BinaryUtil.toByta(wCrouchLateralMax));		//Lateral Max
						out.write(BinaryUtil.toByta(wCrouchDirChange));			//Dir Change
						if(wId == Constants.WEAPON_MINI_GUN_ID){
							out.write(BinaryUtil.toByta(wFireMaxSpeed));	
							out.write(BinaryUtil.toByta(wFireStartSpeed));				
							out.write(BinaryUtil.toByta(wFireAceleration));		
							out.write(BinaryUtil.toByta(wFireResistance));	
						}
					}
				}
				else if(wId == Constants.WEAPON_KNIFE_ID){
					out.write(BinaryUtil.toByta(wStabTime));					//Stab Time
					out.write(BinaryUtil.toByta(wStabLightTime));				//Stab Light Time
					
					out.write(BinaryUtil.toByta(wStabDistance));
					out.write(BinaryUtil.toByta(wStabLightDistance));
					out.write(BinaryUtil.toByta(wStabWidth));
					out.write(BinaryUtil.toByta(wBackFactor));
					
					out.write(BinaryUtil.toByta(wStabHurt));					//Stab Hurt
					out.write(BinaryUtil.toByta(wStabLightHurt));				//Stab Light Hurt	
				}
				else if(wId >= Constants.WEAPON_THROW_ID){
					out.write(BinaryUtil.toByta(wExplodeTime));					//Explode Time
					out.write(BinaryUtil.toByta(wReadyTime));					//Ready Time
					out.write(BinaryUtil.toByta(wThrowOutTime));				//Throw Out Time
					if(wId==Constants.WEAPON_GRENADE_ID){
						out.write(BinaryUtil.toByta(wHurtRange));					//Hurt Range		
						out.write(BinaryUtil.toByta(wHurt));						//Hurt
					}
					if(wId==Constants.WEAPON_FLASH_ID){
						out.write(BinaryUtil.toByta(wFlashRangeStart));
						out.write(BinaryUtil.toByta(wFlashRangeEnd));
						out.write(BinaryUtil.toByta(wTimeMax));
						out.write(BinaryUtil.toByta(wTimeFade));
						out.write(BinaryUtil.toByta(wFlashBackFactor));
					}
					if(wId==Constants.WEAPON_SMOKE_ID){
						out.write(BinaryUtil.toByta(wTime));
					}
				}
			}
		}
	
	
		private Integer id;
		private int sysItemId;
		private int sysLevelId;
		private String 		name;
		private String 		displayName;
		private String		resourceStable;
		private String		resourceChangeable;
		private Integer 	wId;
		private Float 		wChangeInTime;
		private Float 		wMoveSpeedOffset;
		private Float 		wCrossOffset;
		private Integer 	wAccuracyDivisor;
		private Float 		wAccuracyOffset;
		private Float 		wMaxInaccuracy;
		private Integer 	wPenetration;
		private Integer 	wDamage;
		private Float 		wRangeModifier;
		private Float 		wFireTime;
		private Float 		wReloadTime;
		private Float 		wZoomTime;
		private Float 		wZoomValue;
		private Integer 	wAmmoOneClip;
		private Integer 	wAmmoCount;
		private Integer 	wAutoFire;
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
		private Integer wShootBulletCount;
		private Float wSpread;
		private Float wFireMaxSpeed;
		private Float wFireStartSpeed;
		private Float wFireAceleration;
		private Float wFireResistance;
		private Float wStabTime;
		private Float wStabLightTime;
		private Float wStabHurt;
		private Float wStabLightHurt;
		private Float wExplodeTime;
		private Float wReadyTime;
		private Float wThrowOutTime;
		private Float wHurtRange;
		private Float wHurt;
		private Integer wMaxLength=0;
		
		private Float 	wRangeStart ;
		private Float	wRangeEnd ;
		private Float	wAccuracyTime;
		private Float	wAccuracyTimeModefier;
		private Float	wMaxAccuracy;
		private Float	wMinAccuracy;
		private Float	wCrossLengthBase;
		private Float	wCrossLengthFactor;
		private Float	wCrossDistanceBase;
		private Float	wCrossDistanceFactor;
		
		private Float	wXOffset;
		private Float	wFlashRangeStart=0f;
		private Float	wFlashRangeEnd=80f;
		private Float   wFlashBackFactor=0.5f;
		
		
		private Float wSightNormalOffset = 0f;
		private Float wSightOnairOffset = 0.85f;
		private Float wSightMoveOffset = 0.35f;
		private Float wStabDistance = 2.0f;
		private Float wStabLightDistance = 1.5f;
		private Float wStabWidth = 0.5f;
		private Float wBackFactor =1.0f;
		private Float wTime = 1.0f;
		
		private Float wTimeMax = 5.0f;
		private Float wTimeFade = 2.0f;
		//not in database
		private int isChange;
		
		public int getIsChange() {
			return isChange;
		}
		public void setIsChange(int isChange) {
			this.isChange = isChange;
		}
		/**
		 * @return the id
		 */
		public Integer getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(Integer id) {
			this.id = id;
		}
		/**
		 * @return the playerId
		 */
	

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
		public Float getWZoomTime() {
			return wZoomTime;
		}
		public void setWZoomTime(Float zoomTime) {
			wZoomTime = zoomTime;
		}
		public Float getWZoomValue() {
			return wZoomValue;
		}
		public void setWZoomValue(Float zoomValue) {
			wZoomValue = zoomValue;
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
		
		
	
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
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
		public Float getWXOffset() {
			return wXOffset;
		}
		public void setWXOffset(Float offset) {
			wXOffset = offset;
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
		public int getSysItemId() {
			return sysItemId;
		}
		public void setSysItemId(int sysItemId) {
			this.sysItemId = sysItemId;
		}
		public int getSysLevelId() {
			return sysLevelId;
		}
		public void setSysLevelId(int sysLevelId) {
			this.sysLevelId = sysLevelId;
		}
		public Float getWFlashBackFactor() {
			return wFlashBackFactor;
		}
		public void setWFlashBackFactor(Float flashBackFactor) {
			wFlashBackFactor = flashBackFactor;
		}
		public Integer getWMaxLength() {
			return wMaxLength;
		}
		public void setWMaxLength(Integer maxLength) {
			wMaxLength = maxLength;
		}
		
	}
	
