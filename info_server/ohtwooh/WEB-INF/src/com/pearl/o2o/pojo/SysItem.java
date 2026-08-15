package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.StringUtil;


public class SysItem implements Serializable {
	private static final long serialVersionUID = -793421422401089380L;
	public void writeByte(OutputStream out) throws Exception{
		String[] resources = resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
		String[] resOutput = new String[Constants.WEAPON_CHANGE_SIZE];
		System.arraycopy(resources, 0, resOutput, 0, resources.length);
		ArrayList<String > resourceList=new ArrayList<String>();
		for(int i=0;i<resOutput.length;i++){
			String str=resOutput[i];
			if(str!=null && !"".equals(str)){
				String array[]=str.split(Constants.DELIMITER_RESOURCE_STABLE);
				
				for(int j=0;j<array.length;j++){
					resourceList.add(array[j]);
				}
			}			
		}
		this.resources=resourceList;
		
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
			//Resources
			String[] resStable = resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);

			out.write(BinaryUtil.toByta((int)resStable.length+this.resources.size()));			
			for(String res : resStable ){
				StringBuffer path = new StringBuffer(name).append("/").append(res);
				out.write(BinaryUtil.toByta(path.toString()));				
			}
			
			if(this.resources != null){
				for(String res : this.resources){
					if(res!=null && !"".equals(res)){
						StringBuffer path = new StringBuffer(name).append("/").append(res);
						out.write(BinaryUtil.toByta(path.toString()));
					}
				}					
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
				
				if(wId == Constants.WEAPON_PISTOL_ID||wId == Constants.WEAPON_DOUBLE_PISTOL_ID){
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
	public void putOnPart(){
		if(parts.size()!=0){
			String[] resources = resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
			String[] resOutput = new String[Constants.WEAPON_CHANGE_SIZE];
			System.arraycopy(resources, 0, resOutput, 0, resources.length);
			ArrayList<String > resourceList=new ArrayList<String>();
			for(SysItem cos : parts){
				if(cos.getResourceChangeable() != null){
					//update param for weapon
					wChangeInTime += cos.getWChangeInTime();
					wMoveSpeedOffset += cos.getWMoveSpeedOffset();
					wCrossOffset += cos.getWCrossOffset();
					wAccuracyDivisor += cos.getWAccuracyDivisor();
					wAccuracyOffset += cos.getWAccuracyOffset();
					wMaxInaccuracy += cos.getWMaxInaccuracy();
					wPenetration += cos.getWPenetration();
					wDamage += cos.getWDamage();
					wRangeModifier += cos.getWRangeModifier();
					wFireTime += cos.getWFireTime();
					wReloadTime += cos.getWReloadTime();
					wZoomTime += cos.getWZoomTime();
					wZoomValue += cos.getWZoomValue();
					wAmmoOneClip += cos.getWAmmoOneClip();
					wAmmoCount += cos.getWAmmoCount();
					wAutoFire += cos.getWAutoFire();
					wTimeToIdle += cos.getWTimeToIdle();
					wNormalOffset += cos.getWNormalOffset();
					wNormalFactor += cos.getWNormalFactor();
					wOnairOffset += cos.getWOnairOffset();
					wOnairFactor += cos.getWOnairFactor();
					wMoveOffset += cos.getWMoveOffset();
					wMoveFactor += cos.getWMoveFactor();
					wNormalUpBase += cos.getWNormalUpBase();
					wNormalLateralBase += cos.getWNormalLateralBase();
					wNormalUpModifier += cos.getWNormalUpModifier();
					wNormalLateralModifier += cos.getWNormalLateralModifier();
					wNormalUpMax += cos.getWNormalUpMax();
					wNormalLateralMax += cos.getWNormalLateralMax();
					wNormalDirChange += cos.getWNormalDirChange();
					wMoveUpBase += cos.getWMoveUpBase();
					wMoveLateralBase += cos.getWMoveLateralBase();
					wMoveUpModifier += cos.getWMoveUpModifier();
					wMoveLateralModifier += cos.getWMoveLateralModifier();
					wMoveUpMax += cos.getWMoveUpMax();
					wMoveLateralMax += cos.getWMoveLateralMax();
					wMoveDirChange += cos.getWMoveDirChange();
					wOnairUpBase += cos.getWOnairUpBase();
					wOnairLateralBase += cos.getWOnairLateralBase();
					wOnairUpModifier += cos.getWOnairUpModifier();
					wOnairLateralModifier += cos.getWOnairLateralModifier();
					wOnairUpMax += cos.getWOnairUpMax();
					wOnairLateralMax += cos.getWOnairLateralMax();
					wOnairDirChange += cos.getWOnairDirChange();
					wCrouchUpBase += cos.getWCrouchUpBase();
					wCrouchLateralBase += cos.getWCrouchLateralBase();
					wCrouchUpModifier += cos.getWCrouchUpModifier();
					wCrouchLateralModifier += cos.getWCrouchLateralModifier();
					wCrouchUpMax += cos.getWCrouchUpMax();
					wCrouchLateralMax += cos.getWCrouchLateralMax();
					wCrouchDirChange += cos.getWCrouchDirChange();
					wUpModifier += cos.getWUpModifier();
					wStabTime += cos.getWStabTime();
					wStabLightTime += cos.getWStabLightTime();
					wStabHurt += cos.getWStabHurt();
					wStabLightHurt += cos.getWStabLightHurt();
					wExplodeTime += cos.getWExplodeTime();
					wReadyTime += cos.getWReadyTime();
					wThrowOutTime += cos.getWThrowOutTime();
					wHurtRange += cos.getWHurtRange();
					wHurt += cos.getWHurt();
					wShootBulletCount+=cos.getWShootBulletCount();
					wSpread+=cos.getWSpread();
					wFireMaxSpeed+=cos.getWFireMaxSpeed();
					wFireStartSpeed+=cos.getWFireStartSpeed();
					wFireAceleration+=cos.getWFireAceleration();
					wFireResistance+=cos.getWFireResistance();
					wRangeStart +=cos.getWRangeStart();
					wRangeEnd +=cos.getWRangeEnd();
					wAccuracyTime +=cos.getWAccuracyTime();
					wAccuracyTimeModefier +=cos.getWAccuracyTimeModefier();
					wMaxAccuracy +=cos.getWMaxAccuracy();
					wMinAccuracy +=cos.getWMinAccuracy();
					wCrossLengthBase +=cos.getWCrossLengthBase();
					wCrossLengthFactor +=cos.getWCrossLengthFactor();
					wCrossDistanceBase +=cos.getWCrossDistanceBase();
					wCrossDistanceFactor +=cos.getWCrossDistanceFactor();
					resOutput[cos.getSubType()-1] = cos.getResourceStable();
					if(this.modifiedDesc!=null||this.modifiedDesc!=""){
						this.modifiedDesc=StringUtil.updateModifiedDesc(this.modifiedDesc, cos.getSubType(), "2");
					}
				}
			}
			for(int i=0;i<resOutput.length;i++){
				String str=resOutput[i];
				if(str!=null&&!"".equals(str)){
					String array[]=str.split(Constants.DELIMITER_RESOURCE_STABLE);
					for(int j=0;j<array.length;j++){
						resourceList.add(array[j]);
					}
				}
			}
			this.resources=resourceList;
			if(type==Constants.DEFAULT_WEAPON_TYPE){
				String[] wpStable = new String[12];
				wpStable = resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);
				resource=new String[wpStable.length+this.resources.size()];
				int index=0;
				for(int i=0;i<wpStable.length;i++){
					if(!"".equals(wpStable[i])){
						resource[i]=name+"/"+wpStable[i];
						index=i;
					}
				}
				for(int i=0;i<this.resources.size();i++){
					if(this.resources!=null&&!"".equals(this.resources.get(i))){
						resource[i+index+1]=name+"/"+this.resources.get(i);
					}else{
						resource[i+index+1]="";
					}
				}
			}
			
			calculateParam();
		}
	}
	public void init(){
		
		//init prices 
		this.prices=new String[5];
		String payType=Constants.GP_PAY_STRING;
		String forever="-1";
		if(Constants.CR_PAY.equals(currency))
		{
			payType=Constants.CR_PAY_STRING;
		}
		switch (unitType) {
			case 1:
				prices[0]=cost1+Constants.DELIMITER_RESOURCE_STABLE+forever;
				if(cost2!=0){
					prices[1]=cost2+Constants.DELIMITER_RESOURCE_STABLE+forever;
					if(cost3!=0){
						prices[2]=cost3+Constants.DELIMITER_RESOURCE_STABLE+forever;
						if(cost4!=0){
							prices[3]=cost4+Constants.DELIMITER_RESOURCE_STABLE+forever;
							if(cost5!=0){
								prices[4]=cost5+Constants.DELIMITER_RESOURCE_STABLE+forever;
							}
						}
					}
				}
				break;
			case 2:
				prices[0]=cost1+Constants.DELIMITER_RESOURCE_STABLE+forever;
				if(cost2!=0){
					prices[1]=cost2+Constants.DELIMITER_RESOURCE_STABLE+forever;
					if(cost3!=0){
						prices[2]=cost3+Constants.DELIMITER_RESOURCE_STABLE+forever;
						if(cost4!=0){
							prices[3]=cost4+Constants.DELIMITER_RESOURCE_STABLE+forever;
							if(cost5!=0){
								prices[4]=cost5+Constants.DELIMITER_RESOURCE_STABLE+forever;
							}
						}
					}
				}
				break;	
			case 3:
				prices[0]=cost1+Constants.DELIMITER_RESOURCE_STABLE+unit1;
				if(cost2!=0){
					prices[1]=cost2+Constants.DELIMITER_RESOURCE_STABLE+unit2;
					if(cost3!=0){
						prices[2]=cost3+Constants.DELIMITER_RESOURCE_STABLE+unit3;
						if(cost4!=0){
							prices[3]=cost4+Constants.DELIMITER_RESOURCE_STABLE+unit4;
							if(cost5!=0){
								prices[4]=cost5+Constants.DELIMITER_RESOURCE_STABLE+unit5;
							}
						}
					}
				}
				break;
			case 4:
				prices[0]=cost1+Constants.DELIMITER_RESOURCE_STABLE+unit1;
				if(cost2!=0){
					prices[1]=cost2+Constants.DELIMITER_RESOURCE_STABLE+unit2;
					if(cost3!=0){
						prices[2]=cost3+Constants.DELIMITER_RESOURCE_STABLE+unit3;
						if(cost4!=0){
							prices[3]=cost4+Constants.DELIMITER_RESOURCE_STABLE+unit4;
							if(cost5!=0){
								prices[4]=cost5+Constants.DELIMITER_RESOURCE_STABLE+unit5;
							}
						}
					}
				}
				break;	
			default:
				break;
			}
		//init pSuits
		if(this.pSuitable!=null&&!"".equals(this.pSuitable)){
			pSuits=pSuitable.split(Constants.DELIMITER_RESOURCE_STABLE);
		}
		String[] res = resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);
		
		ArrayList<String > resourceList=new ArrayList<String>();
		for(int i=0;i<res.length;i++){
			String str=res[i];
			if(str!=null&&!"".equals(str)){
					resourceList.add(res[i]);
			}
		}
		this.resources=resourceList;
		
		if(type==Constants.DEFAULT_WEAPON_TYPE){
			
			String[] wpStable = new String[12];
			wpStable = resourceStable.split(Constants.DELIMITER_RESOURCE_STABLE);
			String[] resources = resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
			String[] wpChange = new String[Constants.WEAPON_CHANGE_SIZE];
			System.arraycopy(resources, 0, wpChange, 0, resources.length);
			resource=new String[resources.length+wpChange.length];
			int index=0;
			for(int i=0;i<wpStable.length;i++){
				if(!"".equals(wpStable[i])){
					resource[i]=name+"/"+wpStable[i];
					index=i;
				}
			}
			
			for(int i=0;i<wpChange.length;i++){
				if(!"".equals(wpChange[i])&&wpChange[i]!=null){
					resource[i+index+1]=name+"/"+wpChange[i];
				}else{
					resource[i+index+1]="";
				}
			}
		}
		calculateParam();
	}
	private void calculateParam(){
		this.recoil = (50-(this.wNormalUpBase+this.wNormalLateralBase+this.wNormalUpModifier+this.wNormalLateralModifier+this.wNormalUpMax
				+this.wNormalLateralMax+this.wNormalDirChange))*2;//后坐力
		this.accuracy=(10-this.wCrossOffset)*10;
		this.shootSpeed=(50-this.wFireTime*100)*2;
		this.weight=40;
		this.damange=65;
		this.stopPpower=50;
	}
	private Integer 	id;
	private String 		name;
	private String 		displayName;
	
	private String		resourceStable;
	private String		resourceChangeable;
	private Integer 	type;
	private Integer 	subType;
	private Integer 	currency;
	private Integer 	unitType;

	private Integer 	cost1;
	private Integer 	unit1;
	private Integer 	cost2;
	private Integer 	unit2;
	private Integer 	cost3;
	private Integer 	unit3;
	private Integer 	cost4;
	private Integer 	unit4;
	private Integer 	cost5;
	private Integer 	unit5;
	private Integer 	level;
	private String 		isDeleted;
	
	private String 		modifiedDesc;
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
	private Integer cSide;
	private String cGender;
	private String pSuitable;
	private String description;
	private String items;
	private Integer iBuffId;
	private Integer iId;
	private Integer cId;
	private Integer wMaxLength=0;
	private Float iValue;
	
	private Float wRangeStart;
	private Float	wRangeEnd;
	private Float	wAccuracyTime;
	private Float	wAccuracyTimeModefier;
	private Float	wMaxAccuracy;
	private Float	wMinAccuracy;
	private Float	wCrossLengthBase;
	private Float	wCrossLengthFactor;
	private Float	wCrossDistanceBase;
	private Float	wCrossDistanceFactor;
	
	private Float	wXOffset;
	private Float	wSightNormalOffset;
	private Float	wSightOnairOffset;
	private Float	wSightMoveOffset;
	private Float	wStabDistance;
	private Float	wStabLightDistance;
	private Float	wStabWidth;
	private Float	wBackFactor;
	private Float	wFlashRangeStart;
	private Float	wFlashRangeEnd;
	private Float   wFlashBackFactor;
	private Float	wTimeMax;
	private Float	wTimeFade;
	private Float	wTime;
	
	private List<SysItem> parts;
	private List<SysItem> packages;
	
	//Not Stored in Database
	private String prices[];
	private String pSuits[];
	private String	resource[];//for avatar "mk18/sill"
	private ArrayList<String>	resources;
	private int isChange;
	
	private float recoil;//后坐力
	private float accuracy;//精度
	private float shootSpeed;
	private float weight;
	private float damange;
	private float stopPpower;
	
	
	//for log
	private int logVersion;
	private Date logTime;
	private int logId;
	
	
	
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
	public String getPSuitable() {
		return pSuitable;
	}
	public void setPSuitable(String suitable) {
		pSuitable = suitable;
	}
	public String getCGender() {
		return cGender;
	}
	public void setCGender(String gender) {
		cGender = gender;
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
	public Integer getCSide() {
		return cSide;
	}
	public void setCSide(Integer side) {
		cSide = side;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the resourceStable
	 */
	public String getResourceStable() {
		return resourceStable;
	}
	/**
	 * @param resourceStable the resourceStable to set
	 */
	public void setResourceStable(String resourceStable) {
		this.resourceStable = resourceStable;
	}
	/**
	 * @return the resourceChangeable
	 */
	public String getResourceChangeable() {
		return resourceChangeable;
	}
	/**
	 * @param resourceChangeable the resourceChangeable to set
	 */
	public void setResourceChangeable(String resourceChangeable) {
		this.resourceChangeable = resourceChangeable;
	}
	/**
	 * @return the type
	 */
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
	/**
	 * @return the wId
	 */
	public Integer getWId() {
		return wId;
	}
	/**
	 * @param id the wId to set
	 */
	public void setWId(Integer id) {
		wId = id;
	}
	/**
	 * @return the wChangeInTime
	 */
	public Float getWChangeInTime() {
		return wChangeInTime;
	}
	/**
	 * @param changeInTime the wChangeInTime to set
	 */
	public void setWChangeInTime(Float changeInTime) {
		wChangeInTime = changeInTime;
	}
	/**
	 * @return the wMoveSpeedOffset
	 */
	public Float getWMoveSpeedOffset() {
		return wMoveSpeedOffset;
	}
	/**
	 * @param moveSpeedOffset the wMoveSpeedOffset to set
	 */
	public void setWMoveSpeedOffset(Float moveSpeedOffset) {
		wMoveSpeedOffset = moveSpeedOffset;
	}
	/**
	 * @return the wCrossOffset
	 */
	public Float getWCrossOffset() {
		return wCrossOffset;
	}
	/**
	 * @param crossOffset the wCrossOffset to set
	 */
	public void setWCrossOffset(Float crossOffset) {
		wCrossOffset = crossOffset;
	}
	/**
	 * @return the wAccuracyDivisor
	 */
	public Integer getWAccuracyDivisor() {
		return wAccuracyDivisor;
	}
	/**
	 * @param accuracyDivisor the wAccuracyDivisor to set
	 */
	public void setWAccuracyDivisor(Integer accuracyDivisor) {
		wAccuracyDivisor = accuracyDivisor;
	}
	/**
	 * @return the wAccuracyOffset
	 */
	public Float getWAccuracyOffset() {
		return wAccuracyOffset;
	}
	/**
	 * @param accuracyOffset the wAccuracyOffset to set
	 */
	public void setWAccuracyOffset(Float accuracyOffset) {
		wAccuracyOffset = accuracyOffset;
	}
	/**
	 * @return the wMaxInaccuracy
	 */
	public Float getWMaxInaccuracy() {
		return wMaxInaccuracy;
	}
	/**
	 * @param maxInaccuracy the wMaxInaccuracy to set
	 */
	public void setWMaxInaccuracy(Float maxInaccuracy) {
		wMaxInaccuracy = maxInaccuracy;
	}
	/**
	 * @return the wPenetration
	 */
	public Integer getWPenetration() {
		return wPenetration;
	}
	/**
	 * @param penetration the wPenetration to set
	 */
	public void setWPenetration(Integer penetration) {
		wPenetration = penetration;
	}
	/**
	 * @return the wDamage
	 */
	public Integer getWDamage() {
		return wDamage;
	}
	/**
	 * @param damage the wDamage to set
	 */
	public void setWDamage(Integer damage) {
		wDamage = damage;
	}
	/**
	 * @return the wRangeModifier
	 */
	public Float getWRangeModifier() {
		return wRangeModifier;
	}
	/**
	 * @param rangeModifier the wRangeModifier to set
	 */
	public void setWRangeModifier(Float rangeModifier) {
		wRangeModifier = rangeModifier;
	}
	/**
	 * @return the wFireTime
	 */
	public Float getWFireTime() {
		return wFireTime;
	}
	/**
	 * @param fireTime the wFireTime to set
	 */
	public void setWFireTime(Float fireTime) {
		wFireTime = fireTime;
	}
	/**
	 * @return the wReloadTime
	 */
	public Float getWReloadTime() {
		return wReloadTime;
	}
	/**
	 * @param reloadTime the wReloadTime to set
	 */
	public void setWReloadTime(Float reloadTime) {
		wReloadTime = reloadTime;
	}
	/**
	 * @return the wZoomTime
	 */
	public Float getWZoomTime() {
		return wZoomTime;
	}
	/**
	 * @param zoomTime the wZoomTime to set
	 */
	public void setWZoomTime(Float zoomTime) {
		wZoomTime = zoomTime;
	}
	/**
	 * @return the wZoomValue
	 */
	public Float getWZoomValue() {
		return wZoomValue;
	}
	/**
	 * @param zoomValue the wZoomValue to set
	 */
	public void setWZoomValue(Float zoomValue) {
		wZoomValue = zoomValue;
	}
	/**
	 * @return the wAmmoOneClip
	 */
	public Integer getWAmmoOneClip() {
		return wAmmoOneClip;
	}
	/**
	 * @param ammoOneClip the wAmmoOneClip to set
	 */
	public void setWAmmoOneClip(Integer ammoOneClip) {
		wAmmoOneClip = ammoOneClip;
	}
	/**
	 * @return the wAmmoCount
	 */
	public Integer getWAmmoCount() {
		return wAmmoCount;
	}
	/**
	 * @param ammoCount the wAmmoCount to set
	 */
	public void setWAmmoCount(Integer ammoCount) {
		wAmmoCount = ammoCount;
	}
	/**
	 * @return the wAutoFire
	 */
	public Integer getWAutoFire() {
		return wAutoFire;
	}
	/**
	 * @param autoFire the wAutoFire to set
	 */
	public void setWAutoFire(Integer autoFire) {
		wAutoFire = autoFire;
	}
	/**
	 * @return the wTimeToIdle
	 */
	public Float getWTimeToIdle() {
		return wTimeToIdle;
	}
	/**
	 * @param timeToIdle the wTimeToIdle to set
	 */
	public void setWTimeToIdle(Float timeToIdle) {
		wTimeToIdle = timeToIdle;
	}
	/**
	 * @return the wNormalOffset
	 */
	public Float getWNormalOffset() {
		return wNormalOffset;
	}
	/**
	 * @param normalOffset the wNormalOffset to set
	 */
	public void setWNormalOffset(Float normalOffset) {
		wNormalOffset = normalOffset;
	}
	/**
	 * @return the wNormalFactor
	 */
	public Float getWNormalFactor() {
		return wNormalFactor;
	}
	/**
	 * @param normalFactor the wNormalFactor to set
	 */
	public void setWNormalFactor(Float normalFactor) {
		wNormalFactor = normalFactor;
	}
	/**
	 * @return the wOnairOffset
	 */
	public Float getWOnairOffset() {
		return wOnairOffset;
	}
	/**
	 * @param onairOffset the wOnairOffset to set
	 */
	public void setWOnairOffset(Float onairOffset) {
		wOnairOffset = onairOffset;
	}
	/**
	 * @return the wOnairFactor
	 */
	public Float getWOnairFactor() {
		return wOnairFactor;
	}
	/**
	 * @param onairFactor the wOnairFactor to set
	 */
	public void setWOnairFactor(Float onairFactor) {
		wOnairFactor = onairFactor;
	}
	/**
	 * @return the wMoveOffset
	 */
	public Float getWMoveOffset() {
		return wMoveOffset;
	}
	/**
	 * @param moveOffset the wMoveOffset to set
	 */
	public void setWMoveOffset(Float moveOffset) {
		wMoveOffset = moveOffset;
	}
	/**
	 * @return the wMoveFactor
	 */
	public Float getWMoveFactor() {
		return wMoveFactor;
	}
	/**
	 * @param moveFactor the wMoveFactor to set
	 */
	public void setWMoveFactor(Float moveFactor) {
		wMoveFactor = moveFactor;
	}
	/**
	 * @return the wNormalUpBase
	 */
	public Float getWNormalUpBase() {
		return wNormalUpBase;
	}
	/**
	 * @param normalUpBase the wNormalUpBase to set
	 */
	public void setWNormalUpBase(Float normalUpBase) {
		wNormalUpBase = normalUpBase;
	}
	/**
	 * @return the wNormalLateralBase
	 */
	public Float getWNormalLateralBase() {
		return wNormalLateralBase;
	}
	/**
	 * @param normalLateralBase the wNormalLateralBase to set
	 */
	public void setWNormalLateralBase(Float normalLateralBase) {
		wNormalLateralBase = normalLateralBase;
	}
	/**
	 * @return the wNormalUpModifier
	 */
	public Float getWNormalUpModifier() {
		return wNormalUpModifier;
	}
	/**
	 * @param normalUpModifier the wNormalUpModifier to set
	 */
	public void setWNormalUpModifier(Float normalUpModifier) {
		wNormalUpModifier = normalUpModifier;
	}
	/**
	 * @return the wNormalLateralModifier
	 */
	public Float getWNormalLateralModifier() {
		return wNormalLateralModifier;
	}
	/**
	 * @param normalLateralModifier the wNormalLateralModifier to set
	 */
	public void setWNormalLateralModifier(Float normalLateralModifier) {
		wNormalLateralModifier = normalLateralModifier;
	}
	/**
	 * @return the wNormalUpMax
	 */
	public Float getWNormalUpMax() {
		return wNormalUpMax;
	}
	/**
	 * @param normalUpMax the wNormalUpMax to set
	 */
	public void setWNormalUpMax(Float normalUpMax) {
		wNormalUpMax = normalUpMax;
	}
	/**
	 * @return the wNormalLateralMax
	 */
	public Float getWNormalLateralMax() {
		return wNormalLateralMax;
	}
	/**
	 * @param normalLateralMax the wNormalLateralMax to set
	 */
	public void setWNormalLateralMax(Float normalLateralMax) {
		wNormalLateralMax = normalLateralMax;
	}
	/**
	 * @return the wNormalDirChange
	 */
	public Float getWNormalDirChange() {
		return wNormalDirChange;
	}
	/**
	 * @param normalDirChange the wNormalDirChange to set
	 */
	public void setWNormalDirChange(Float normalDirChange) {
		wNormalDirChange = normalDirChange;
	}
	/**
	 * @return the wMoveUpBase
	 */
	public Float getWMoveUpBase() {
		return wMoveUpBase;
	}
	/**
	 * @param moveUpBase the wMoveUpBase to set
	 */
	public void setWMoveUpBase(Float moveUpBase) {
		wMoveUpBase = moveUpBase;
	}
	/**
	 * @return the wMoveLateralBase
	 */
	public Float getWMoveLateralBase() {
		return wMoveLateralBase;
	}
	/**
	 * @param moveLateralBase the wMoveLateralBase to set
	 */
	public void setWMoveLateralBase(Float moveLateralBase) {
		wMoveLateralBase = moveLateralBase;
	}
	/**
	 * @return the wMoveUpModifier
	 */
	public Float getWMoveUpModifier() {
		return wMoveUpModifier;
	}
	/**
	 * @param moveUpModifier the wMoveUpModifier to set
	 */
	public void setWMoveUpModifier(Float moveUpModifier) {
		wMoveUpModifier = moveUpModifier;
	}
	/**
	 * @return the wMoveLateralModifier
	 */
	public Float getWMoveLateralModifier() {
		return wMoveLateralModifier;
	}
	/**
	 * @param moveLateralModifier the wMoveLateralModifier to set
	 */
	public void setWMoveLateralModifier(Float moveLateralModifier) {
		wMoveLateralModifier = moveLateralModifier;
	}
	/**
	 * @return the wMoveUpMax
	 */
	public Float getWMoveUpMax() {
		return wMoveUpMax;
	}
	/**
	 * @param moveUpMax the wMoveUpMax to set
	 */
	public void setWMoveUpMax(Float moveUpMax) {
		wMoveUpMax = moveUpMax;
	}
	/**
	 * @return the wMoveLateralMax
	 */
	public Float getWMoveLateralMax() {
		return wMoveLateralMax;
	}
	/**
	 * @param moveLateralMax the wMoveLateralMax to set
	 */
	public void setWMoveLateralMax(Float moveLateralMax) {
		wMoveLateralMax = moveLateralMax;
	}
	/**
	 * @return the wMoveDirChange
	 */
	public Float getWMoveDirChange() {
		return wMoveDirChange;
	}
	/**
	 * @param moveDirChange the wMoveDirChange to set
	 */
	public void setWMoveDirChange(Float moveDirChange) {
		wMoveDirChange = moveDirChange;
	}
	/**
	 * @return the wOnairUpBase
	 */
	public Float getWOnairUpBase() {
		return wOnairUpBase;
	}
	/**
	 * @param onairUpBase the wOnairUpBase to set
	 */
	public void setWOnairUpBase(Float onairUpBase) {
		wOnairUpBase = onairUpBase;
	}
	/**
	 * @return the wOnairLateralBase
	 */
	public Float getWOnairLateralBase() {
		return wOnairLateralBase;
	}
	/**
	 * @param onairLateralBase the wOnairLateralBase to set
	 */
	public void setWOnairLateralBase(Float onairLateralBase) {
		wOnairLateralBase = onairLateralBase;
	}
	/**
	 * @return the wOnairUpModifier
	 */
	public Float getWOnairUpModifier() {
		return wOnairUpModifier;
	}
	/**
	 * @param onairUpModifier the wOnairUpModifier to set
	 */
	public void setWOnairUpModifier(Float onairUpModifier) {
		wOnairUpModifier = onairUpModifier;
	}
	/**
	 * @return the wOnairLateralModifier
	 */
	public Float getWOnairLateralModifier() {
		return wOnairLateralModifier;
	}
	/**
	 * @param onairLateralModifier the wOnairLateralModifier to set
	 */
	public void setWOnairLateralModifier(Float onairLateralModifier) {
		wOnairLateralModifier = onairLateralModifier;
	}
	/**
	 * @return the wOnairUpMax
	 */
	public Float getWOnairUpMax() {
		return wOnairUpMax;
	}
	/**
	 * @param onairUpMax the wOnairUpMax to set
	 */
	public void setWOnairUpMax(Float onairUpMax) {
		wOnairUpMax = onairUpMax;
	}
	/**
	 * @return the wOnairLateralMax
	 */
	public Float getWOnairLateralMax() {
		return wOnairLateralMax;
	}
	/**
	 * @param onairLateralMax the wOnairLateralMax to set
	 */
	public void setWOnairLateralMax(Float onairLateralMax) {
		wOnairLateralMax = onairLateralMax;
	}
	/**
	 * @return the wOnairDirChange
	 */
	public Float getWOnairDirChange() {
		return wOnairDirChange;
	}
	/**
	 * @param onairDirChange the wOnairDirChange to set
	 */
	public void setWOnairDirChange(Float onairDirChange) {
		wOnairDirChange = onairDirChange;
	}
	/**
	 * @return the wCrouchUpBase
	 */
	public Float getWCrouchUpBase() {
		return wCrouchUpBase;
	}
	/**
	 * @param crouchUpBase the wCrouchUpBase to set
	 */
	public void setWCrouchUpBase(Float crouchUpBase) {
		wCrouchUpBase = crouchUpBase;
	}
	/**
	 * @return the wCrouchLateralBase
	 */
	public Float getWCrouchLateralBase() {
		return wCrouchLateralBase;
	}
	/**
	 * @param crouchLateralBase the wCrouchLateralBase to set
	 */
	public void setWCrouchLateralBase(Float crouchLateralBase) {
		wCrouchLateralBase = crouchLateralBase;
	}
	/**
	 * @return the wCrouchUpModifier
	 */
	public Float getWCrouchUpModifier() {
		return wCrouchUpModifier;
	}
	/**
	 * @param crouchUpModifier the wCrouchUpModifier to set
	 */
	public void setWCrouchUpModifier(Float crouchUpModifier) {
		wCrouchUpModifier = crouchUpModifier;
	}
	/**
	 * @return the wCrouchLateralModifier
	 */
	public Float getWCrouchLateralModifier() {
		return wCrouchLateralModifier;
	}
	/**
	 * @param crouchLateralModifier the wCrouchLateralModifier to set
	 */
	public void setWCrouchLateralModifier(Float crouchLateralModifier) {
		wCrouchLateralModifier = crouchLateralModifier;
	}
	/**
	 * @return the wCrouchUpMax
	 */
	public Float getWCrouchUpMax() {
		return wCrouchUpMax;
	}
	/**
	 * @param crouchUpMax the wCrouchUpMax to set
	 */
	public void setWCrouchUpMax(Float crouchUpMax) {
		wCrouchUpMax = crouchUpMax;
	}
	/**
	 * @return the wCrouchLateralMax
	 */
	public Float getWCrouchLateralMax() {
		return wCrouchLateralMax;
	}
	/**
	 * @param crouchLateralMax the wCrouchLateralMax to set
	 */
	public void setWCrouchLateralMax(Float crouchLateralMax) {
		wCrouchLateralMax = crouchLateralMax;
	}
	/**
	 * @return the wCrouchDirChange
	 */
	public Float getWCrouchDirChange() {
		return wCrouchDirChange;
	}
	/**
	 * @param crouchDirChange the wCrouchDirChange to set
	 */
	public void setWCrouchDirChange(Float crouchDirChange) {
		wCrouchDirChange = crouchDirChange;
	}
	/**
	 * @return the wUpModifier
	 */
	public Float getWUpModifier() {
		return wUpModifier;
	}
	/**
	 * @param upModifier the wUpModifier to set
	 */
	public void setWUpModifier(Float upModifier) {
		wUpModifier = upModifier;
	}
	/**
	 * @return the wStabTime
	 */
	public Float getWStabTime() {
		return wStabTime;
	}
	/**
	 * @param stabTime the wStabTime to set
	 */
	public void setWStabTime(Float stabTime) {
		wStabTime = stabTime;
	}
	/**
	 * @return the wStabLightTime
	 */
	public Float getWStabLightTime() {
		return wStabLightTime;
	}
	/**
	 * @param stabLightTime the wStabLightTime to set
	 */
	public void setWStabLightTime(Float stabLightTime) {
		wStabLightTime = stabLightTime;
	}
	/**
	 * @return the wStabHurt
	 */
	public Float getWStabHurt() {
		return wStabHurt;
	}
	/**
	 * @param stabHurt the wStabHurt to set
	 */
	public void setWStabHurt(Float stabHurt) {
		wStabHurt = stabHurt;
	}
	/**
	 * @return the wStabLightHurt
	 */
	public Float getWStabLightHurt() {
		return wStabLightHurt;
	}
	/**
	 * @param stabLightHurt the wStabLightHurt to set
	 */
	public void setWStabLightHurt(Float stabLightHurt) {
		wStabLightHurt = stabLightHurt;
	}
	/**
	 * @return the wExplodeTime
	 */
	public Float getWExplodeTime() {
		return wExplodeTime;
	}
	/**
	 * @param explodeTime the wExplodeTime to set
	 */
	public void setWExplodeTime(Float explodeTime) {
		wExplodeTime = explodeTime;
	}
	/**
	 * @return the wReadyTime
	 */
	public Float getWReadyTime() {
		return wReadyTime;
	}
	/**
	 * @param readyTime the wReadyTime to set
	 */
	public void setWReadyTime(Float readyTime) {
		wReadyTime = readyTime;
	}
	/**
	 * @return the wThrowOutTime
	 */
	public Float getWThrowOutTime() {
		return wThrowOutTime;
	}
	/**
	 * @param throwOutTime the wThrowOutTime to set
	 */
	public void setWThrowOutTime(Float throwOutTime) {
		wThrowOutTime = throwOutTime;
	}
	/**
	 * @return the wHurtRange
	 */
	public Float getWHurtRange() {
		return wHurtRange;
	}
	/**
	 * @param hurtRange the wHurtRange to set
	 */
	public void setWHurtRange(Float hurtRange) {
		wHurtRange = hurtRange;
	}
	/**
	 * @return the wHurt
	 */
	public Float getWHurt() {
		return wHurt;
	}
	/**
	 * @param hurt the wHurt to set
	 */
	public void setWHurt(Float hurt) {
		wHurt = hurt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Integer getUnitType() {
		return unitType;
	}
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}
	public Integer getCost1() {
		return cost1;
	}
	public void setCost1(Integer cost1) {
		this.cost1 = cost1;
	}
	public Integer getUnit1() {
		return unit1;
	}
	public void setUnit1(Integer unit1) {
		this.unit1 = unit1;
	}
	public Integer getCost2() {
		return cost2;
	}
	public void setCost2(Integer cost2) {
		this.cost2 = cost2;
	}
	public Integer getUnit2() {
		return unit2;
	}
	public void setUnit2(Integer unit2) {
		this.unit2 = unit2;
	}
	public Integer getCost3() {
		return cost3;
	}
	public void setCost3(Integer cost3) {
		this.cost3 = cost3;
	}
	public Integer getUnit3() {
		return unit3;
	}
	public void setUnit3(Integer unit3) {
		this.unit3 = unit3;
	}
	public Integer getCost4() {
		return cost4;
	}
	public void setCost4(Integer cost4) {
		this.cost4 = cost4;
	}
	public Integer getUnit4() {
		return unit4;
	}
	public void setUnit4(Integer unit4) {
		this.unit4 = unit4;
	}
	public Integer getCost5() {
		return cost5;
	}
	public void setCost5(Integer cost5) {
		this.cost5 = cost5;
	}
	public Integer getUnit5() {
		return unit5;
	}
	public void setUnit5(Integer unit5) {
		this.unit5 = unit5;
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
	public String[] getPrices() {
		return prices;
	}
	public void setPrices(String[] prices) {
		this.prices = prices;
	}
	public String[] getPSuits() {
		return pSuits;
	}
	public void setPSuits(String[] suits) {
		pSuits = suits;
	}
	public Integer getCId() {
		return cId;
	}
	public void setCId(Integer id) {
		cId = id;
	}
	public String[] getResource() {
		return resource;
	}
	public void setResource(String[] resource) {
		this.resource = resource;
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
	public Integer getWMaxLength() {
		return wMaxLength;
	}
	public void setWMaxLength(Integer maxLength) {
		wMaxLength = maxLength;
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
	public Float getWFlashBackFactor() {
		return wFlashBackFactor;
	}
	public void setWFlashBackFactor(Float flashBackFactor) {
		wFlashBackFactor = flashBackFactor;
	}
	public Float getIValue() {
		return iValue;
	}
	public void setIValue(Float value) {
		iValue = value;
	}
	
	
}
