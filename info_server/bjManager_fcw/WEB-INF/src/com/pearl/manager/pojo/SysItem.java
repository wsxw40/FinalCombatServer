package com.pearl.manager.pojo;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.pearl.manager.pojo.GunProperty.Property;
import com.pearl.manager.utils.BinaryUtil;
import com.pearl.manager.utils.Constants;
import com.pearl.manager.utils.StringUtil;


public class SysItem extends BaseItemPojo<SysItem>  {
	private static final long serialVersionUID = -793421422401089380L;
	
	public SysItem() {
	}
	public SysItem(String name, String displayName,String description, int type,  int color) {
		this.name = name;
		this.displayName = displayName;
		this.description = description;
		this.type = type;
		this.gunProperty = new GunProperty();
		gunProperty.setColor(color);
	}
	private void writeProperty(OutputStream out)throws Exception{
		out.write(BinaryUtil.toByta((byte)7));
		
		if(gunPropertyList1.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList1.size()/4)));
			for(int num:gunPropertyList1){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
		if(gunPropertyList2.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList2.size()/4)));
			for(int num:gunPropertyList2){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
		if(gunPropertyList3.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList3.size()/4)));
			for(int num:gunPropertyList3){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
		if(gunPropertyList4.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList4.size()/4)));
			for(int num:gunPropertyList4){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
		if(gunPropertyList5.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList5.size()/4)));
			for(int num:gunPropertyList5){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
		if(gunPropertyList6.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList6.size()/4)));
			for(int num:gunPropertyList6){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
		if(gunPropertyList7.size()==0){
			out.write(BinaryUtil.toByta((byte)0));
		}else{
			out.write(BinaryUtil.toByta((byte)(gunPropertyList7.size()/4)));
			for(int num:gunPropertyList7){
				out.write(BinaryUtil.toByta((short)num));
			}
		}
	}
	private void writeWeaponBaseInfo(OutputStream out) throws Exception{
		out.write(BinaryUtil.toByta(this.id));
		out.write(BinaryUtil.toByta(this.id));
		out.write(BinaryUtil.toByta(this.displayName));			//Weapon Display Name
		out.write(BinaryUtil.toByta(this.name));				//Weapon Name
		out.write(BinaryUtil.toByta((byte)this.gunProperty.getColor()));
		out.write(BinaryUtil.toByta((byte)0));
		out.write(BinaryUtil.toByta((byte)100));		
		writeProperty(out);
		if(this.resource!=null){
			// Resources
			out.write(BinaryUtil.toByta(this.resource.length));			
			for(String res:resource){
				out.write(BinaryUtil.toByta(res));	
			}	
		}else{
			// Resources stable
			String[] resStable = this.resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);
			// Resources changeable
			String[] resChangeable = this.resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
			List<String > resourceList=new ArrayList<String>();
			if(resChangeable!=null){
				List<String> tmpList=new ArrayList<String>();
				for(int i=0;i<resStable.length;i++){
					if(!"".equals(resStable[i])){
						tmpList.add(this.name+"/"+resStable[i]);
					}
				}
				for(int i=0;i<resChangeable.length;i++){
					if(!"".equals(resChangeable[i])&&resChangeable[i]!=null){
						String[] changebles=resChangeable[i].split(Constants.DELIMITER_RESOURCE_STABLE);
						for(int j=0;j<changebles.length;j++){// resChangeable有两个资源
							tmpList.add(this.name+"/"+changebles[j]);
						}
					}
				}
				resourceList=tmpList;
			
			}else{
				for(int i=0;i<resStable.length;i++){
					String str=resStable[i];
					if(str!=null&&!"".equals(str)){
						resourceList.add(resStable[i]);
					}
				}
			}
			out.write(BinaryUtil.toByta(resourceList.size()));			
			for(String res:resourceList){
				out.write(BinaryUtil.toByta(res));	
			}
		}
	
	}
	private void writeCostumeBaseInfo(OutputStream out,SysCharacter sysCharacter)throws Exception{
		out.write(BinaryUtil.toByta(this.id));
		out.write(BinaryUtil.toByta(this.id));
		out.write(BinaryUtil.toByta(this.displayName));			// Weapon Display Name
		out.write(BinaryUtil.toByta(this.name));				// Weapon Name
		out.write(BinaryUtil.toByta((byte)this.gunProperty.getColor()));
		out.write(BinaryUtil.toByta((byte)0));
		// durable or expire
		out.write(BinaryUtil.toByta((byte)100));
		
		writeProperty(out);
		String[] costume = sysCharacter.getCostume();
		int size = 0;
		for (String cos : costume){
			if(cos!=null && !"".equals(cos)){
				size ++;
			}
		}
		out.write(BinaryUtil.toByta(size));
		for(String cos : costume){
			if(cos!=null && !"".equals(cos)){			
				out.write(BinaryUtil.toByta(cos));
			}
		}
	}
	public void writeWeapon(OutputStream out) throws Exception {
		writeWeaponBaseInfo(out);
		out.write(BinaryUtil.toByta(this.wId==null?0:this.wId));		//Weapon ID
		if(this.wId != null && this.wId > 0){//base info
			out.write(BinaryUtil.toByta(this.wChangeInTime));	//Change In Time
			out.write(BinaryUtil.toByta(this.wMoveSpeedOffset));	//Move Speed Offset
			out.write(BinaryUtil.toByta(this.wCrossOffset));		//Cross Offset
			out.write(BinaryUtil.toByta(this.wCrossLengthBase));		//wCrossLengthBase
			out.write(BinaryUtil.toByta(this.wCrossLengthFactor));		//wCrossLengthFactor
			out.write(BinaryUtil.toByta(this.wCrossDistanceBase));		//wCrossDistanceBase
			out.write(BinaryUtil.toByta(this.wCrossDistanceFactor));		//wCrossDistanceFactor
			out.write(BinaryUtil.toByta(this.wHitSpeed));		//wHitSpeed
			out.write(BinaryUtil.toByta(this.wHitAcceleration));		//wHitAcceleration
			out.write(BinaryUtil.toByta(this.wHitDistance));		//wHitDistance
			out.write(BinaryUtil.toByta(this.wCritRate));
			out.write(BinaryUtil.toByta(this.wCritAvailable));
			out.write(BinaryUtil.toByta(this.wDamageModifer));
			
			out.write(BinaryUtil.toByta(this.wTimeToIdle));					// Time
																					// To
																					// Idle
			//武器强化等级 int
			//out.write(BinaryUtil.toByta(getLevel()));
			//武器战斗力 int
			out.write(BinaryUtil.toByta(getFightNum()));
			//武器颜色 int
			//out.write(BinaryUtil.toByta(getGunProperty().getColor()));
			calculateParam();
			//武器攻击力 float
			out.write(BinaryUtil.toByta(getDamange()));
			//武器攻击速度 float
			out.write(BinaryUtil.toByta(getShootSpeed()));
			if(this.wId < Constants.WEAPON_KNIFE_ID){								
				//Gun Info
				out.write(BinaryUtil.toByta(this.wAccuracyDivisor));				//Accuracy Divisor
				out.write(BinaryUtil.toByta(this.wAccuracyOffset));				//Accuracy Offset
				out.write(BinaryUtil.toByta(this.wMaxInaccuracy));				//Max Inaccuracy
				out.write(BinaryUtil.toByta(this.wPenetration));					//Penetration
				out.write(BinaryUtil.toByta(this.wDamage));						//Damage
				out.write(BinaryUtil.toByta(this.wRangeModifier));				//Range Modifier
				out.write(BinaryUtil.toByta(this.wRangeStart));				//wRangeStart
				out.write(BinaryUtil.toByta(this.wRangeEnd));				//wRangeEnd
				out.write(BinaryUtil.toByta(this.wFireTime));					//Fire Time
				out.write(BinaryUtil.toByta(this.wReloadTime));					//Reload Time
				out.write(BinaryUtil.toByta(this.wAmmoOneClip));					//Ammo One Clip
				out.write(BinaryUtil.toByta(this.wAmmoCount));					//Ammo Count
				out.write(BinaryUtil.toByta(this.wAutoFire.byteValue()));		//Auto Fire
//				out.write(BinaryUtil.toByta(this.WTimeToIdle));					//Time To Idle
				out.write(BinaryUtil.toByta(this.wNormalOffset));				//Normal Offset
				out.write(BinaryUtil.toByta(this.wNormalFactor));				//Normal Factor
				out.write(BinaryUtil.toByta(this.wOnairOffset));					//Onair Offset
				out.write(BinaryUtil.toByta(this.wOnairFactor));					//Onair Factor
				out.write(BinaryUtil.toByta(this.wMoveOffset));					//Move Offset
				out.write(BinaryUtil.toByta(this.wMoveFactor));					//Move Factor
				
				
				if(this.wSightInfo!=null&&!"0".equals(this.wSightInfo)){
					String[] sightInfo=this.wSightInfo.split(";");
					out.write(BinaryUtil.toByta(sightInfo.length));
					// SightInfo
					for(int i=0;i<sightInfo.length;i++){
						String[] info=sightInfo[i].split(",");
						if(info.length==5){
							out.write(BinaryUtil.toByta(Float.parseFloat(info[0])));	// level;
							out.write(BinaryUtil.toByta(Float.parseFloat(info[1])));	// mouse_sensitivity;
							out.write(BinaryUtil.toByta(Float.parseFloat(info[2])));	// move_speed_offset;
							out.write(BinaryUtil.toByta(Float.parseFloat(info[3])));	// move_speed_offset;
							out.write(BinaryUtil.toByta(Float.parseFloat(info[4])));	// move_speed_offset;
						}else{
							throw new Exception("wSightInfo参数不对");
						}
					}
				}else{
					out.write(BinaryUtil.toByta(0));
				}
				
				if(this.wId == Constants.WEAPON_PISTOL_ID||this.wId == Constants.WEAPON_DOUBLE_PISTOL_ID){// pistol
					out.write(BinaryUtil.toByta(this.wUpModifier)); // wUpModifier
					out.write(BinaryUtil.toByta(this.wAccuracyTime)); // wAccuracyTime
					out.write(BinaryUtil.toByta(this.wAccuracyTimeModefier)); // wAccuracyTimeModefier
					out.write(BinaryUtil.toByta(this.wMaxAccuracy)); // wMaxAccuracy
					out.write(BinaryUtil.toByta(this.wMinAccuracy)); // wMinAccuracy
					
					out.write(BinaryUtil.toByta(this.wNormalUpBase));			//Up Base
					out.write(BinaryUtil.toByta(this.wNormalLateralBase));		//Lateral Base
					out.write(BinaryUtil.toByta(this.wNormalUpModifier));		//Up Modifier
					out.write(BinaryUtil.toByta(this.wNormalLateralModifier));	//Lateral Modifier
					out.write(BinaryUtil.toByta(this.wNormalUpMax));				//Up Max
					out.write(BinaryUtil.toByta(this.wNormalLateralMax));		//Lateral Max
					out.write(BinaryUtil.toByta(this.wNormalDirChange));			//Dir Change
					//Move
					out.write(BinaryUtil.toByta(this.wMoveUpBase));				//Up Base
					out.write(BinaryUtil.toByta(this.wMoveLateralBase));			//Lateral Base
					out.write(BinaryUtil.toByta(this.wMoveUpModifier));			//Up Modifier
					out.write(BinaryUtil.toByta(this.wMoveLateralModifier));		//Lateral Modifier
					out.write(BinaryUtil.toByta(this.wMoveUpMax));				//Up Max
					out.write(BinaryUtil.toByta(this.wMoveLateralMax));			//Lateral Max
					out.write(BinaryUtil.toByta(this.wMoveDirChange));			//Dir Change
					//Onair
					out.write(BinaryUtil.toByta(this.wOnairUpBase));				//Up Base
					out.write(BinaryUtil.toByta(this.wOnairLateralBase));		//Lateral Base
					out.write(BinaryUtil.toByta(this.wOnairUpModifier));			//Up Modifier
					out.write(BinaryUtil.toByta(this.wOnairLateralModifier));	//Lateral Modifier
					out.write(BinaryUtil.toByta(this.wOnairUpMax));				//Up Max
					out.write(BinaryUtil.toByta(this.wOnairLateralMax));			//Lateral Max
					out.write(BinaryUtil.toByta(this.wOnairDirChange));			//Dir Change
					//Crouch
					out.write(BinaryUtil.toByta(this.wCrouchUpBase));			//Up Base
					out.write(BinaryUtil.toByta(this.wCrouchLateralBase));		//Lateral Base
					out.write(BinaryUtil.toByta(this.wCrouchUpModifier));		//Up Modifier
					out.write(BinaryUtil.toByta(this.wCrouchLateralModifier));	//Lateral Modifier
					out.write(BinaryUtil.toByta(this.wCrouchUpMax));				//Up Max
					out.write(BinaryUtil.toByta(this.wCrouchLateralMax));		//Lateral Max
					out.write(BinaryUtil.toByta(this.wCrouchDirChange));			//Dir Change
				}else if(this.wId == Constants.WEAPON_SNIPER_GUN_ID){
					out.write(BinaryUtil.toByta(wSightNormalOffset));	
					out.write(BinaryUtil.toByta(wSightOnairOffset));	
					out.write(BinaryUtil.toByta(wSightMoveOffset));	
					out.write(BinaryUtil.toByta(wReadyTime));
				}else if(wId == Constants.WEAPON_SHOT_GUN_ID||wId == Constants.WEAPON_MACHINE_GUN_ID){//散弹枪	机枪		
					out.write(BinaryUtil.toByta(wShootBulletCount));	
					out.write(BinaryUtil.toByta(wSpread));	
					out.write(BinaryUtil.toByta(wNormalUpBase));			//Up Base
					out.write(BinaryUtil.toByta(wNormalUpModifier));		//Up Modifier
					out.write(BinaryUtil.toByta(wNormalUpMax));				//Up Max
					if(wId == Constants.WEAPON_MACHINE_GUN_ID){//机枪
						out.write(BinaryUtil.toByta(wFireMaxSpeed));	
						out.write(BinaryUtil.toByta(wFireStartSpeed));				
						out.write(BinaryUtil.toByta(wFireAceleration));		
						out.write(BinaryUtil.toByta(wFireResistance));	
						out.write(BinaryUtil.toByta(wReadyTime));
					}
				}
				else if(wId == Constants.WEAPON_SUBMACHINE_ID ||wId == Constants.WEAPON_RIFFLE_ID){//冲锋	枪,自动步枪
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
					
				}else if(wId == Constants.WEAPON_CURE_GUN_ID ){//治愈类
					out.write(BinaryUtil.toByta(wMaxDistance));
					out.write(BinaryUtil.toByta(wAddBlood));
				}
				else if(wId == Constants.WEAPON_MEDITNEEDLE_GUN_ID 
						||wId == Constants.WEAPON_ROCKET_LUNCHER_ID ||wId == Constants.WEAPON_BOW_ID ||wId == Constants.WEAPON_ARROW_ID||wId == Constants.WEAPON_BOSSPVE_ID){//投射类
					out.write(BinaryUtil.toByta(wAmmoType));
					out.write(BinaryUtil.toByta(wFlySpeed));
					out.write(BinaryUtil.toByta(wSpread));
					out.write(BinaryUtil.toByta(wNormalUpBase));
					out.write(BinaryUtil.toByta(wNormalUpModifier));
					out.write(BinaryUtil.toByta(wNormalUpMax));
					out.write(BinaryUtil.toByta(wMaxaliveTime));
					out.write(BinaryUtil.toByta((byte)wGravity));
					out.write(BinaryUtil.toByta(wHurt));
					out.write(BinaryUtil.toByta(wAmmopartKey));
					out.write(BinaryUtil.toByta(wHurtRange));
					out.write(BinaryUtil.toByta(wThrowOutTime));
					
					out.write(BinaryUtil.toByta(wDmgModifyTimerMin));
					out.write(BinaryUtil.toByta(wDmgModifyTimerMax));
					out.write(BinaryUtil.toByta(wDmgModifyMin));
					out.write(BinaryUtil.toByta(wDmgModifyMax));
					out.write(BinaryUtil.toByta(wCapsuleHeight));
					out.write(BinaryUtil.toByta(wCapsuleRadius));

				}
				else if(wId == Constants.WEAPON_FLAME_GUN_ID ){//喷火枪
//					out.write(BinaryUtil.toByta(wLastHurt));
//					out.write(BinaryUtil.toByta(wLastTime));
					out.write(BinaryUtil.toByta(wSpecialDistance));
					out.write(BinaryUtil.toByta(wSpecialRange));
					out.write(BinaryUtil.toByta(wSpecialLasttime));
//					out.write(BinaryUtil.toByta(wSpecialHurt));
//					out.write(BinaryUtil.toByta(wSpecialLasthurt));
					out.write(BinaryUtil.toByta(wParticlenum));
					out.write(BinaryUtil.toByta(wShowSpeed));
					out.write(BinaryUtil.toByta(wHurtRange));
				}
				
			}
			else if(wId == Constants.WEAPON_KNIFE_ID){//刀
				out.write(BinaryUtil.toByta(wStabTime));					//Stab Time
				out.write(BinaryUtil.toByta(wStabLightTime));				//Stab Light Time
				
				out.write(BinaryUtil.toByta(wStabDistance));
				out.write(BinaryUtil.toByta(wStabLightDistance));
				out.write(BinaryUtil.toByta(wStabWidth));
				out.write(BinaryUtil.toByta(wBackFactor));
				
				out.write(BinaryUtil.toByta(wStabHurt));					//Stab Hurt
				out.write(BinaryUtil.toByta(wStabLightHurt));				//Stab Light Hurt	
				out.write(BinaryUtil.toByta(wBackBoostPlus));
			}
			else if(wId == Constants.WEAPON_BIOCHEMICAL){//僵尸主武器
				out.write(BinaryUtil.toByta(wStabTime));					//Stab Time
				out.write(BinaryUtil.toByta(wStabLightTime));				//Stab Light Time
				
				out.write(BinaryUtil.toByta(wStabDistance));
				out.write(BinaryUtil.toByta(wStabLightDistance));
				out.write(BinaryUtil.toByta(wStabWidth));
				out.write(BinaryUtil.toByta(wBackFactor));
				
				out.write(BinaryUtil.toByta(wStabHurt));					//Stab Hurt
				out.write(BinaryUtil.toByta(wStabLightHurt));				//Stab Light Hurt	
				out.write(BinaryUtil.toByta(wBackBoostPlus));
				
				out.write(BinaryUtil.toByta(wLastTime));
			}
			else if(wId == Constants.WEAPON_BIOCHEMICAL_AVO){//僵尸副武器
				out.write(BinaryUtil.toByta(wLastTime));//冷却时间
				out.write(BinaryUtil.toByta(wAccuracyTime));//技能持续时间
				out.write(BinaryUtil.toByta(wStabHurt));//技能伤害
			}
			else if(wId >= Constants.WEAPON_THROW_ID){//雷
				out.write(BinaryUtil.toByta(wExplodeTime));					//Explode Time
				out.write(BinaryUtil.toByta(wReadyTime));					//Ready Time
				out.write(BinaryUtil.toByta(wThrowOutTime));				//Throw Out Time
				if(wId==Constants.WEAPON_GRENADE_ID||wId == 23){//手雷
					out.write(BinaryUtil.toByta(wHurtRange));					//Hurt Range		
					out.write(BinaryUtil.toByta(wHurt));						//Hurt
				}
				if(wId==Constants.WEAPON_FLASH_ID){//闪光
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
	public void writeCostume(OutputStream out,SysCharacter sysCharacter)throws Exception{
		writeCostumeBaseInfo(out, sysCharacter);
		out.write(BinaryUtil.toByta(this.cResistanceFire));
		out.write(BinaryUtil.toByta(this.cResistanceBlast));
		out.write(BinaryUtil.toByta(this.cResistanceBullet));
		out.write(BinaryUtil.toByta(this.cResistanceKnife));
		out.write(BinaryUtil.toByta(this.cBloodAdd));
	}
	public void putOnPart(){
	
	}
	private void initPSuits(){
		if(this.characterId!=null&&!"".equals(this.characterId)){
			String[] characters=characterId.split(Constants.DELIMITER_RESOURCE_STABLE);
			if(characters.length!=0&&characterList.size()==0){
				for(String s:characters){
					characterList.add(StringUtil.toInt(s));
				}
			}
		}
	}
	public void initGunProperty(){
		int color=0;
		List<Property> propertyList=new ArrayList<Property>();
		if(this.gunProperty1!=null){
			gunPropertyList1=new ArrayList<Integer>();
			if("".equals(this.gunProperty1)){
				
			}else{
				String[] gunPropertys=gunProperty1.split(Constants.DELIMITER_RESOURCE_STABLE);
				if(gunPropertys.length!=0&&gunPropertys.length%4==0&&gunPropertyList1.size()==0){
					for(int i=0;i<gunPropertys.length;i++){
						Integer index=StringUtil.toInt(gunPropertys[i]);
						Integer value=StringUtil.toInt(gunPropertys[++i]);
						Integer value2=StringUtil.toInt(gunPropertys[++i]);
						Integer time=StringUtil.toInt(gunPropertys[++i]);
//						Property p=new Property(index, value, value2, time,color2);
//						propertyList.add(p);
						gunPropertyList1.add(index);
						gunPropertyList1.add(value);
						gunPropertyList1.add(value2);
						gunPropertyList1.add(time);
					}
				}
			}
		}
		if(this.gunProperty2!=null){
			gunPropertyList2=new ArrayList<Integer>();
			if("".equals(this.gunProperty2)){
				
			}else{
				String[] gunPropertys=gunProperty2.split(Constants.DELIMITER_RESOURCE_STABLE);
				if(gunPropertys.length!=0&&gunPropertys.length%4==0&&gunPropertyList2.size()==0){
					for(int i=0;i<gunPropertys.length;i++){
						Integer index=StringUtil.toInt(gunPropertys[i]);
//						int color2=1;
//						if(index <= 14){
//							color2 = 2;
//						}else if(index <= 45 && index >= 30){
//							color2 = 3;
//						}
//						else if(index <= 102 && index >= 60){
//							color2 = 4;
//						}
						
						Integer value=StringUtil.toInt(gunPropertys[++i]);
//						if(value < 0){
//							color2=1;
//						}
						color=4;
						Integer value2=StringUtil.toInt(gunPropertys[++i]);
						Integer time=StringUtil.toInt(gunPropertys[++i]);
						Property p=new Property(index, value, value2, time,1);
						propertyList.add(p);
						gunPropertyList2.add(index);
						gunPropertyList2.add(value);
						gunPropertyList2.add(value2);
						gunPropertyList2.add(time);
					}
				}
			}
		}
		if(this.gunProperty3!=null){
			gunPropertyList3=new ArrayList<Integer>();
			if("".equals(this.gunProperty3)){
				
			}else{
				String[] gunPropertys=gunProperty3.split(Constants.DELIMITER_RESOURCE_STABLE);
				if(gunPropertys.length!=0&&gunPropertys.length%4==0&&gunPropertyList3.size()==0){
					for(int i=0;i<gunPropertys.length;i++){
						Integer index=StringUtil.toInt(gunPropertys[i]);
//						int color2=1;
//						if(index <= 14){
//							color2 = 2;
//						}else if(index <= 45 && index >= 30){
//							color2 = 3;
//						}
//						else if(index <= 102 && index >= 60){
//							color2 = 4;
//						}
//						
						Integer value=StringUtil.toInt(gunPropertys[++i]);
//						if(value < 0){
//							color2=1;
//						}
							color=3;
						Integer value2=StringUtil.toInt(gunPropertys[++i]);
						Integer time=StringUtil.toInt(gunPropertys[++i]);
						Property p=new Property(index, value, value2, time,1);
						propertyList.add(p);
						gunPropertyList3.add(index);
						gunPropertyList3.add(value);
						gunPropertyList3.add(value2);
						gunPropertyList3.add(time);
					}
				}
			}
		}
		if(this.gunProperty4!=null){
			gunPropertyList4=new ArrayList<Integer>();
			if("".equals(this.gunProperty4)){
				
			}else{
				String[] gunPropertys=gunProperty4.split(Constants.DELIMITER_RESOURCE_STABLE);
				if(gunPropertys.length!=0&&gunPropertys.length%4==0&&gunPropertyList4.size()==0){
					for(int i=0;i<gunPropertys.length;i++){
						Integer index=StringUtil.toInt(gunPropertys[i]);
//						int color2=1;
//						if(index <= 14){
//							color2 = 2;
//						}else if(index <= 45 && index >= 30){
//							color2 = 3;
//						}
//						else if(index <= 102 && index >= 60){
//							color2 = 4;
//						}
						
						Integer value=StringUtil.toInt(gunPropertys[++i]);
//						if(value < 0){
//							color2=1;
//						}
							color=2;
						Integer value2=StringUtil.toInt(gunPropertys[++i]);
						Integer time=StringUtil.toInt(gunPropertys[++i]);
						Property p=new Property(index, value, value2, time,1);
						propertyList.add(p);
						gunPropertyList4.add(index);
						gunPropertyList4.add(value);
						gunPropertyList4.add(value2);
						gunPropertyList4.add(time);
					}
				}
			}
		}
		if(this.gunProperty5!=null){
			gunPropertyList5=new ArrayList<Integer>();
			if("".equals(this.gunProperty5)){
				
			}else{
				String[] gunPropertys=gunProperty5.split(Constants.DELIMITER_RESOURCE_STABLE);
				if(gunPropertys.length!=0&&gunPropertys.length%4==0&&gunPropertyList5.size()==0){
					for(int i=0;i<gunPropertys.length;i++){
						Integer index=StringUtil.toInt(gunPropertys[i]);
//						int color2=1;
//						if(index <= 14){
//							color2 = 2;
//						}else if(index <= 45 && index >= 30){
//							color2 = 3;
//						}
//						else if(index <= 102 && index >= 60){
//							color2 = 4;
//						}
						
						Integer value=StringUtil.toInt(gunPropertys[++i]);
//						if(value < 0){
//							color2=1;
//						}
							color=5;
						Integer value2=StringUtil.toInt(gunPropertys[++i]);
						Integer time=StringUtil.toInt(gunPropertys[++i]);
						Property p=new Property(index, value, value2, time,1);
						propertyList.add(p);
						gunPropertyList5.add(index);
						gunPropertyList5.add(value);
						gunPropertyList5.add(value2);
						gunPropertyList5.add(time);
					}
				}
			}
		}
		Collections.sort(propertyList, new Comparator<Property>(){
			@Override
			public int compare(Property o1, Property o2) {
				return Integer.valueOf(o2.getColor()).compareTo(Integer.valueOf(o1.getColor()));
			}
		});
		if(Constants.NUM_ONE == this.isStrengthen){
			if(strengthLevel >= 4 && strengthLevel < 7){
				color = GunProperty.GREEND;
			} else if(strengthLevel >= 7 && strengthLevel < 10){
				color = GunProperty.BLUE;
			} else if(strengthLevel >= 10 && strengthLevel < 12){
				color = GunProperty.PURPLE;
			} else if(strengthLevel >= 12 && strengthLevel < 14){
				color = GunProperty.ORANGE;
			} else if(strengthLevel == 14){
				color = GunProperty.UNKNOWN;
			} else if(strengthLevel == 15){
				color = GunProperty.GOLD;
			}
		}
		gunProperty.setPropertyList(propertyList);
		gunProperty.setColor(color);
	}
	private void initPrice(){
	
	}
	public void init(){
		initPSuits();
		initGunProperty();
		String[] resStable = resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);
		String[] resChangeable = resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
		initResource(resStable,resChangeable);
		calculateParam();
		initPrice();
		if(this.type ==Constants.DEFAULT_WEAPON_TYPE){
//			this.seq=CommonUtil.getWeaponSeq(this.wId);
		}else if(this.type ==Constants.DEFAULT_COSTUME_TYPE){
//			this.seq=CommonUtil.getCotumeSeq(this.cId);
		}else if(this.type ==Constants.DEFAULT_PART_TYPE){
//			this.seq=CommonUtil.getCotumeSeq(this.cId);
		}else{
			this.seq=0;
		}
		
	}
	private void calculateParam(){}
	private void initResource(String[] resStable,String[] resChangeable){
		ArrayList<String > resourceList=new ArrayList<String>();
		if(type==Constants.DEFAULT_WEAPON_TYPE&&resChangeable!=null){
			List<String> tmpList=new ArrayList<String>();
			for(int i=0;i<resStable.length;i++){
				if(!"".equals(resStable[i])){
					tmpList.add(name+"/"+resStable[i]);
				}
			}
			resourceList.clear();
			for(int i=0;i<resChangeable.length;i++){
				if(!"".equals(resChangeable[i])&&resChangeable[i]!=null){
					String[] changebles=resChangeable[i].split(Constants.DELIMITER_RESOURCE_STABLE);
					for(int j=0;j<changebles.length;j++){//resChangeable有两个资源
						resourceList.add(changebles[j]);
						tmpList.add(name+"/"+changebles[j]);
					}
				}
			}
			resource=new String[tmpList.size()];
			tmpList.toArray(resource);
			this.resources=resourceList;
		}else{
			for(int i=0;i<resStable.length;i++){
				String str=resStable[i];
				if(str!=null&&!"".equals(str)){
					resourceList.add(resStable[i]);
				}
			}
			this.resources=resourceList;
		}
	}
	private String 		displayName;
	private Integer 	type;
	private Integer 	subType;
	private Integer 	level = 0;
	private String 		isDeleted;
	private String 		modifiedDesc;
	private String description;
	private String items;
	private Integer iBuffId;
	private Integer iId;
	private Integer cId;
	private Float cResistanceFire=0F;
	private Float cResistanceBlast=0F;
	private Float cResistanceBullet=0F;
	private Float cResistanceKnife=0F;
	private Float cBloodAdd;
	private Integer isStrengthen=0;
	private String iValue;
	private Integer wFixPrice = 0;
	private String characterId;
	private String gunProperty1="";
	private String gunProperty2="";
	private String gunProperty3="";
	private String gunProperty4="";
	private String gunProperty5="";
	private String gunProperty6="";
	private String gunProperty7="";
	private int isVip;
	private int isNew;
	private int isHot;
	private int isWeb;
	private int isPopular;
	private int isExchange;
	private int isAsset;
	private Integer itemIndex;
	private List<SysItem> parts;
	private List<SysItem> packages;
	
	private Integer mType=0;
	private Integer mValue=0;
	private Integer unit=null;
	private Integer unitType=null;
	
	//Not Stored in Database
	private List<Integer> gunPropertyList1=new ArrayList<Integer>();
	private List<Integer> gunPropertyList2=new ArrayList<Integer>();
	private List<Integer> gunPropertyList3=new ArrayList<Integer>();
	private List<Integer> gunPropertyList4=new ArrayList<Integer>();
	private List<Integer> gunPropertyList5=new ArrayList<Integer>();
	private List<Integer> gunPropertyList6=new ArrayList<Integer>();
	private List<Integer> gunPropertyList7=new ArrayList<Integer>();
	private List<Integer> characterList=new ArrayList<Integer>();
	private List<Payment> gpPricesList=new ArrayList<Payment>();
	private List<Payment> crPricesList=new ArrayList<Payment>();
	private List<Payment> voucherPricesList=new ArrayList<Payment>();
	private List<Payment> medalPricesList=new ArrayList<Payment>();
	
	//包括隐藏的payment
	private List<Payment> allGpPricesList = new ArrayList<Payment>();
	private List<Payment> allCrPricesList = new ArrayList<Payment>();
	private List<Payment> allVoucherPricesList = new ArrayList<Payment>();
	
//	private String	resource[];//for avatar "mk18/sill"
	private ArrayList<String>	resources;
	private int isChange;
	private GunProperty gunProperty=new GunProperty();
	
	
	private float recoil;//后坐力
	private float accuracy;//精度
	private float shootSpeed;
	private float weight;
	private float damange;
	private float damangeAdd = 0F;
	private float shootSpeedAdd = 0F;
	private float stopPpower;
	private float range;
	private int seq;
	//for log
	private int logVersion;
	private Date logTime;
	private int logId;
	
	private int rareLevel;
	private int fightNum = 0;
	private transient int figthNumOutput = 0;
	private int strengthLevel = 0;
	private float evaluateRmb;
	
	public float getEvaluateRmb() {
		return evaluateRmb;
	}
	public void setEvaluateRmb(float evaluateRmb) {
		this.evaluateRmb = evaluateRmb;
	}
	public GunProperty getGunProperty() {
		return gunProperty;
	}
	public void setGunProperty(GunProperty gunProperty) {
		this.gunProperty = gunProperty;
	}
	public int getLogVersion() {
		return logVersion;
	}
	public void setLogVersion(int logVersion) {
		this.logVersion = logVersion;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	public int getIsChange() {
		return isChange;
	}
	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}
	public ArrayList<String> getResources() {
		return resources;
	}
	public void setResources(ArrayList<String> resources) {
		this.resources = resources;
	}

	public List<SysItem> getParts() {
		return parts;
	}
	public void setParts(List<SysItem> parts) {
		this.parts = parts;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the subType
	 */
	public Integer getSubType() {
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(Integer subType) {
		this.subType = subType;
	}

	/**
	 * @return the modifiedDesc
	 */
	public String getModifiedDesc() {
		return modifiedDesc;
	}
	/**
	 * @param modifiedDesc the modifiedDesc to set
	 */
	public void setModifiedDesc(String modifiedDesc) {
		this.modifiedDesc = modifiedDesc;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIBuffId() {
		return iBuffId;
	}
	public void setIBuffId(Integer buffId) {
		iBuffId = buffId;
	}
	public List<SysItem> getPackages() {
		return packages;
	}
	public void setPackages(List<SysItem> packages) {
		this.packages = packages;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public Integer getCId() {
		return cId;
	}
	public void setCId(Integer id) {
		cId = id;
	}

	public float getRecoil() {
		return recoil;
	}
	public void setRecoil(float recoil) {
		this.recoil = recoil;
	}
	public float getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}
	public float getShootSpeed() {
		return shootSpeed;
	}
	public void setShootSpeed(float shootSpeed) {
		this.shootSpeed = shootSpeed;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getDamange() {
		return damange;
	}
	public void setDamange(float damange) {
		this.damange = damange;
	}
	
	public float getStopPpower() {
		return stopPpower;
	}
	public void setStopPpower(float stopPpower) {
		this.stopPpower = stopPpower;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Integer getIId() {
		return iId;
	}
	public void setIId(Integer id) {
		iId = id;
	}
	public String[] getIValues() {
		String[] array={"","",""};
		array=iValue.split(Constants.DELIMITER_RESOURCE_STABLE);
		return array;
	}
	public String getIValue() {
		return iValue;
	}
	public void setIValue(String value) {
		iValue = value;
	}
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
	}
	public String[] getCharacterIds() {
		String[] array=characterId.split(Constants.DELIMITER_RESOURCE_STABLE);
		return array;
	}
	public String getCharacterId() {
		return characterId;
	}
	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}
	public List<Payment> getGpPricesList() {
		return gpPricesList;
	}
	public void setGpPricesList(List<Payment> gpPricesList) {
		this.gpPricesList = gpPricesList;
	}
	public List<Payment> getCrPricesList() {
		return crPricesList;
	}
	public void setCrPricesList(List<Payment> crPricesList) {
		this.crPricesList = crPricesList;
	}
	public List<Payment> getVoucherPricesList() {
		return voucherPricesList;
	}
	public void setVoucherPricesList(List<Payment> voucherPricesList) {
		this.voucherPricesList = voucherPricesList;
	}
	public List<Integer> getCharacterList() {
		return characterList;
	}
	public void setCharacterList(List<Integer> characterList) {
		this.characterList = characterList;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getGunProperty1() {
		return gunProperty1;
	}
	public void setGunProperty1(String gunProperty1) {
		this.gunProperty1 = gunProperty1;
	}
	public String getGunProperty2() {
		return gunProperty2;
	}
	public void setGunProperty2(String gunProperty2) {
		this.gunProperty2 = gunProperty2;
	}
	public String getGunProperty3() {
		return gunProperty3;
	}
	public void setGunProperty3(String gunProperty3) {
		this.gunProperty3 = gunProperty3;
	}
	public String getGunProperty4() {
		return gunProperty4;
	}
	public void setGunProperty4(String gunProperty4) {
		this.gunProperty4 = gunProperty4;
	}
	public List<Integer> getGunPropertyList1() {
		return gunPropertyList1;
	}
	public void setGunPropertyList1(List<Integer> gunPropertyList1) {
		this.gunPropertyList1 = gunPropertyList1;
	}
	public List<Integer> getGunPropertyList2() {
		return gunPropertyList2;
	}
	public void setGunPropertyList2(List<Integer> gunPropertyList2) {
		this.gunPropertyList2 = gunPropertyList2;
	}
	public List<Integer> getGunPropertyList3() {
		return gunPropertyList3;
	}
	public void setGunPropertyList3(List<Integer> gunPropertyList3) {
		this.gunPropertyList3 = gunPropertyList3;
	}
	public List<Integer> getGunPropertyList4() {
		return gunPropertyList4;
	}
	public void setGunPropertyList4(List<Integer> gunPropertyList4) {
		this.gunPropertyList4 = gunPropertyList4;
	}
	public int getIsVip() {
		return isVip;
	}
	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}
	public String getGunProperty5() {
		return gunProperty5;
	}
	public void setGunProperty5(String gunProperty5) {
		this.gunProperty5 = gunProperty5;
	}
	public int getIsNew() {
		return isNew;
	}
	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}
	public int getIsHot() {
		return isHot;
	}
	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}
	public List<Integer> getGunPropertyList5() {
		return gunPropertyList5;
	}
	public void setGunPropertyList5(List<Integer> gunPropertyList5) {
		this.gunPropertyList5 = gunPropertyList5;
	}
	public Integer getWFixPrice() {
		return wFixPrice;
	}
	public void setWFixPrice(Integer fixPrice) {
		wFixPrice = fixPrice;
	}
	public Integer getItemIndex() {
		return itemIndex;
	}
	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}
	public List<Payment> getAllGpPricesList() {
		return allGpPricesList;
	}
	public void setAllGpPricesList(List<Payment> allGpPricesList) {
		this.allGpPricesList = allGpPricesList;
	}
	public List<Payment> getAllCrPricesList() {
		return allCrPricesList;
	}
	public void setAllCrPricesList(List<Payment> allCrPricesList) {
		this.allCrPricesList = allCrPricesList;
	}
	public List<Payment> getAllVoucherPricesList() {
		return allVoucherPricesList;
	}
	public void setAllVoucherPricesList(List<Payment> allVoucherPricesList) {
		this.allVoucherPricesList = allVoucherPricesList;
	}
	public Float getCResistanceFire() {
		return cResistanceFire;
	}
	public void setCResistanceFire(Float resistanceFire) {
		cResistanceFire = resistanceFire;
	}
	public Float getCResistanceBlast() {
		return cResistanceBlast;
	}
	public void setCResistanceBlast(Float resistanceBlast) {
		cResistanceBlast = resistanceBlast;
	}
	public Float getCResistanceBullet() {
		return cResistanceBullet;
	}
	public void setCResistanceBullet(Float resistanceBullet) {
		cResistanceBullet = resistanceBullet;
	}
	public Float getCResistanceKnife() {
		return cResistanceKnife;
	}
	public void setCResistanceKnife(Float resistanceKnife) {
		cResistanceKnife = resistanceKnife;
	}
	public Float getCBloodAdd() {
		return cBloodAdd;
	}
	public void setCBloodAdd(Float bloodAdd) {
		cBloodAdd = bloodAdd;
	}
	public Integer getIsStrengthen() {
		return isStrengthen;
	}
	public void setIsStrengthen(Integer isStrengthen) {
		this.isStrengthen = isStrengthen;
	}
	public Integer getMType() {
		return mType;
	}
	public void setMType(Integer type) {
		mType = type;
	}
	public Integer getMValue() {
		return mValue;
	}
	public void setMValue(Integer value) {
		mValue = value;
	}
	public List<Integer> getGunPropertyList6() {
		return gunPropertyList6;
	}
	public void setGunPropertyList6(List<Integer> gunPropertyList6) {
		this.gunPropertyList6 = gunPropertyList6;
	}
	public List<Integer> getGunPropertyList7() {
		return gunPropertyList7;
	}
	public void setGunPropertyList7(List<Integer> gunPropertyList7) {
		this.gunPropertyList7 = gunPropertyList7;
	}
	public String getGunProperty6() {
		return gunProperty6;
	}
	public void setGunProperty6(String gunProperty6) {
		this.gunProperty6 = gunProperty6;
	}
	public String getGunProperty7() {
		return gunProperty7;
	}
	public void setGunProperty7(String gunProperty7) {
		this.gunProperty7 = gunProperty7;
	}
	public int getIsWeb() {
		return isWeb;
	}
	public void setIsWeb(int isWeb) {
		this.isWeb = isWeb;
	}
	public int getIsPopular() {
		return isPopular;
	}
	public void setIsPopular(int isPopular) {
		this.isPopular = isPopular;
	}
	public int getRareLevel() {
		return rareLevel;
	}
	public void setRareLevel(int rareLevel) {
		this.rareLevel = rareLevel;
	}
	public int getFightNum() {
		return fightNum;
	}
	public void initFightNumFront() {
		if (this.type == Constants.DEFAULT_WEAPON_TYPE || this.type == Constants.DEFAULT_COSTUME_TYPE || this.type == Constants.DEFAULT_PART_TYPE) {
			initFightNum();
		}
	}
	private void initFightNum(){
		if(this.fightNum >= 0){
			figthNumOutput = this.fightNum;
			return;
		}
		int lowPropertyNum = 0;
		int middlePropertyNum = 0;
		int highPropertyNum = 0;
		if (this.getIsStrengthen() == Constants.NUM_ZERO) {
			highPropertyNum = getDefaultPropertySize();
		}
		if (this.getType() == Constants.DEFAULT_WEAPON_TYPE) {
			if (this.getSubType() == Constants.NUM_ONE) {// 主武器
				double multiplier3 = (1 + lowPropertyNum / Constants.FIGHT_PARAM[0][4] + middlePropertyNum / Constants.FIGHT_PARAM[0][5] + highPropertyNum / Constants.FIGHT_PARAM[0][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[0][0] * Math.pow(Constants.FIGHT_PARAM[0][1], this.strengthLevel + ((this.rareLevel - 1) / Constants.FIGHT_PARAM[0][2]) - Constants.FIGHT_PARAM[0][3]) * multiplier3);
			} else if(this.getSubType() == Constants.NUM_TWO){//副武器
				double multiplier3 = (1 + lowPropertyNum/Constants.FIGHT_PARAM[5][4] + middlePropertyNum/Constants.FIGHT_PARAM[5][5] + highPropertyNum/Constants.FIGHT_PARAM[5][6]);
				figthNumOutput = (int)(Constants.FIGHT_PARAM[5][0] * Math.pow(Constants.FIGHT_PARAM[5][1], this.strengthLevel + ((this.rareLevel - 1)/Constants.FIGHT_PARAM[5][2]) - Constants.FIGHT_PARAM[5][3]) * multiplier3);
			} else if (this.getSubType() == Constants.NUM_THREE) {// 近身器
				double multiplier3 = (1 + lowPropertyNum / Constants.FIGHT_PARAM[1][4] + middlePropertyNum / Constants.FIGHT_PARAM[1][5] + highPropertyNum / Constants.FIGHT_PARAM[1][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[1][0] * Math.pow(Constants.FIGHT_PARAM[1][1], this.strengthLevel + ((this.rareLevel - 1) / Constants.FIGHT_PARAM[1][2]) - Constants.FIGHT_PARAM[1][3]) * multiplier3);
			}
		} else if (this.getType() == Constants.DEFAULT_COSTUME_TYPE) {// 服装
			double multiplier3 = (1 + lowPropertyNum / Constants.FIGHT_PARAM[2][4] + middlePropertyNum / Constants.FIGHT_PARAM[2][5] + highPropertyNum / Constants.FIGHT_PARAM[2][6]);
			figthNumOutput = (int) (Constants.FIGHT_PARAM[2][0] * ((Math.pow(Constants.FIGHT_PARAM[2][1], this.strengthLevel + ((this.rareLevel - 1) / Constants.FIGHT_PARAM[2][2]) - Constants.FIGHT_PARAM[2][3]) * multiplier3) - 1));
		} else if (this.getType() == Constants.DEFAULT_PART_TYPE) {
			if (this.getSubType() == Constants.NUM_ONE) {// 帽子
				double multiplier3 = (1 + lowPropertyNum / Constants.FIGHT_PARAM[3][4] + middlePropertyNum / Constants.FIGHT_PARAM[3][5] + highPropertyNum / Constants.FIGHT_PARAM[3][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[3][0] * ((Math.pow(Constants.FIGHT_PARAM[3][1], this.strengthLevel + ((this.rareLevel - 1) / Constants.FIGHT_PARAM[3][2]) - Constants.FIGHT_PARAM[3][3]) * multiplier3) - 1));
			} else if (this.getSubType() == Constants.NUM_TWO) {// 配饰
				double multiplier3 = (1 + lowPropertyNum / Constants.FIGHT_PARAM[4][4] + middlePropertyNum / Constants.FIGHT_PARAM[4][5] + highPropertyNum / Constants.FIGHT_PARAM[4][6]);
				figthNumOutput = (int) (Constants.FIGHT_PARAM[4][0] * ((Math.pow(Constants.FIGHT_PARAM[4][1], this.strengthLevel + ((this.rareLevel - 1) / Constants.FIGHT_PARAM[4][2]) - Constants.FIGHT_PARAM[4][3]) * multiplier3) - 1));
			}
		}
		if(figthNumOutput < 0){
			figthNumOutput = 0;
		}
	}
	public void setFightNum(int fightNum) {
		this.fightNum = fightNum;
	}
	public int getIsExchange() {
		return isExchange;
	}
	public void setIsExchange(int isExchange) {
		this.isExchange = isExchange;
	}
	public List<Payment> getMedalPricesList() {
		return medalPricesList;
	}
	public void setMedalPricesList(List<Payment> medalPricesList) {
		this.medalPricesList = medalPricesList;
	}
	public int getDefaultPropertySize(){
		return (this.gunPropertyList2.size()/4 + this.gunPropertyList3.size()/4 + this.gunPropertyList4.size()/4 + this.gunPropertyList5.size()/4 + this.gunPropertyList6.size()/4 + this.gunPropertyList7.size()/4);
	}
	public int getIsAsset() {
		return isAsset;
	}
	public void setIsAsset(int isAsset) {
		this.isAsset = isAsset;
	}
	public Integer getUnit() {
		if(null == unit){
			return getDefaultPayment().getUnit();
		}
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	@Override
	public SysItem clone() throws CloneNotSupportedException {
		return (SysItem)super.clone();
	}
	public int getFigthNumOutput() {
		if(figthNumOutput == 0){
			initFightNum();
		}
		return figthNumOutput;
	}
	public void setFigthNumOutput(int figthNumOutput) {
		this.figthNumOutput = figthNumOutput;
	}
	public int getStrengthLevel() {
		return strengthLevel;
	}
	public void setStrengthLevel(int strengthLevel) {
		this.strengthLevel = strengthLevel;
	}
	
	public Integer getUnitType() {
		if(null == unitType){
			return getDefaultPayment().getUnitType();
		}
		return unitType;
	}
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}
	private Payment defaultPayment;
	public Payment getDefaultPayment(){
		if(null == defaultPayment){
			int unitType = 0;
			int unit = 1;
			if (null != this.getAllGpPricesList()&& this.getAllGpPricesList().size() > 0) {
				unitType = this.getAllGpPricesList().get(0).getUnitType();
				unit = this.getAllGpPricesList().get(0).getUnit();
			} else if (null != this.getAllCrPricesList()&& this.getAllCrPricesList().size() > 0) {
				unitType = this.getAllCrPricesList().get(0).getUnitType();
				unit = this.getAllCrPricesList().get(0).getUnit();
			} else if (null != this.getAllVoucherPricesList()&& this.getAllVoucherPricesList().size() > 0) {
				unitType = this.getAllVoucherPricesList().get(0).getUnitType();
				unit = this.getAllVoucherPricesList().get(0).getUnit();
			}
			defaultPayment = new Payment(unit,unitType);
		}
		return defaultPayment;
	}
	public float getDamangeAdd() {
		return damangeAdd;
	}
	public void setDamangeAdd(float damangeAdd) {
		this.damangeAdd = damangeAdd;
	}
	public float getShootSpeedAdd() {
		return shootSpeedAdd;
	}
	public void setShootSpeedAdd(float shootSpeedAdd) {
		this.shootSpeedAdd = shootSpeedAdd;
	}
}
