package com.pearl.o2o.pojo;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;

public class PlayerItem implements Serializable {

	private static final long serialVersionUID = -1774380518543769733L;
	
	public void writeByte(OutputStream out) throws Exception{
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

			out.write(BinaryUtil.toByta((int)resStable.length+resources.size()));			
			for(String res : resStable ){
				StringBuffer path = new StringBuffer(name).append("/").append(res);
				out.write(BinaryUtil.toByta(path.toString()));				
			}
			
			if(resources != null){
				for(String res : resources){
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
		String[] resources = resourceChangeable.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
		String[] resOutput = new String[Constants.WEAPON_CHANGE_SIZE];
		System.arraycopy(resources, 0, resOutput, 0, resources.length);
		ArrayList<String > resourceList=new ArrayList<String>();
		for(PlayerItem cos : parts){
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
				
				wXOffset+=cos.getWXOffset();
				wSightNormalOffset+=cos.getWSightNormalOffset();
				wSightOnairOffset+=cos.getWSightMoveOffset();
				wSightMoveOffset+=cos.getWSightMoveOffset();
				wStabDistance+=cos.getWStabDistance();
				wStabLightDistance+=cos.getWStabLightDistance();
				wStabWidth+=cos.getWStabWidth();
				wBackFactor+=cos.getWBackFactor();
				wFlashRangeStart+=cos.getWFlashRangeStart();
				wFlashRangeEnd+=cos.getWFlashRangeEnd();
				wFlashBackFactor+=cos.getWFlashBackFactor();
				wTimeMax+=cos.getWTimeMax();
				wTimeFade+=cos.getWTimeFade();
				wTime+=cos.getWTime();
				resOutput[cos.getSubType()-1] = cos.getResourceStable();
			}
		}
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
		
		initResource();
		calculateParam();
		
	}
	public void initResource(){
		
//		this.timeLeft=(int)(this.expireDate.getTime() - new Date().getTime())/(60*1000);
		//init pSuits
		if(this.pSuitable!=null&&!"".equals(this.pSuitable)){
			pSuits=pSuitable.split(Constants.DELIMITER_RESOURCE_STABLE);
		}
		
	}
	public void calculateParam(){
		this.recoil = (50-(this.wNormalUpBase+this.wNormalLateralBase+this.wNormalUpModifier+this.wNormalLateralModifier+this.wNormalUpMax
				+this.wNormalLateralMax+this.wNormalDirChange))*2;//后坐力
		this.accuracy=(10-this.wCrossOffset)*10;
		this.shootSpeed=(50-this.wFireTime*100)*2;
		this.weight=(20-weight)*5;
		this.damange=65;
		this.stopPpower=50;
		if(this.durable<100){
			this.repairCost = (int)(this.cost1*9*(this.durable/1000.0)); 
		}else{
			this.repairCost = 0;
		}
	}
	public void init(){
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
			resourceList.clear();
			for(int i=0;i<wpChange.length;i++){
				if(!"".equals(wpChange[i])&&wpChange[i]!=null){
					
					resourceList.add(wpChange[i]);
					resource[i+index+1]=name+"/"+wpChange[i];
				}else{
					resource[i+index+1]="";
				}
			}
			this.resources=resourceList;
		}
		initResource();
		calculateParam();
	}
	private Integer id;
	private Integer userId;
	private Integer	playerId;
	private Integer itemId;
	private Date	validDate;
	private	Date	expireDate;
	private	String	isBind;
	private Integer pack;
	private Integer buff;
	private Integer seq;
	private Integer durable=100;
	private Integer playerItemCurrency;//playerItem's currency
	private Integer playerItemUnitType;
	private Integer 	quantity=1;

	private String modifiedDesc;
	private String sModifiedDesc;
	
	//join from sysItem 
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
	private String cGender;
	private Integer cSide;
	private String pSuitable;
	private String description;
	private String isGift;
	private String isDefault;
	private Integer cId;
	private Integer iBuffId;
	private Integer iId;
	private Integer wMaxLength=0;
	private Float iValue=0f;
	
	private Float wRangeStart ;
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
	
	private List<PlayerItem> parts;
	private List<PlayerItem> packages;
	
	//join table sys_package
	private List<SysItem> gifts;
	
	//Not Stored in Database
	private float recoil;//后坐力
	private float accuracy;//精度
	private float shootSpeed;
	private float weight;
	private float damange;
	private float stopPpower;
	private int repairCost;
	
	private String resource[];
	private String prices[];
	private String pSuits[];
	private ArrayList<String>	resources;
	private Integer timeLeft;
	
	//Join table ITEM_MOD
	private Integer	parentItemId;
	
	
	/**
	 * @return the parentItemId
	 */
	public Integer getParentItemId() {
		return parentItemId;
	}
	/**
	 * @param parentItemId the parentItemId to set
	 */
	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
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
	public Integer getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	/**
	 * @return the itemId
	 */
	public Integer getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the validDate
	 */
	public Date getValidDate() {
		return validDate;
	}
	/**
	 * @param validDate the validDate to set
	 */
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	/**
	 * @return the expireDate
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * @return the isBind
	 */
	public String getIsBind() {
		return isBind;
	}
	/**
	 * @param isBind the isBind to set
	 */
	public void setIsBind(String isBind) {
		this.isBind = isBind;
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
	public String getModifiedDesc() {
		return modifiedDesc;
	}
	public void setModifiedDesc(String modifiedDesc) {
		this.modifiedDesc = modifiedDesc;
	}
	public Integer getPack() {
		return pack;
	}
	public void setPack(Integer pack) {
		this.pack = pack;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSModifiedDesc() {
		return sModifiedDesc;
	}
	public void setSModifiedDesc(String modifiedDesc) {
		sModifiedDesc = modifiedDesc;
	}
	public List<PlayerItem> getParts() {
		return parts;
	}
	public void setParts(List<PlayerItem> parts) {
		this.parts = parts;
	}
	public Integer getCSide() {
		return cSide;
	}
	public void setCSide(Integer side) {
		cSide = side;
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

	public String getPSuitable() {
		return pSuitable;
	}
	public void setPSuitable(String suitable) {
		pSuitable = suitable;
	}
	public ArrayList<String> getResources() {
		return resources;
	}
	public void setResources(ArrayList<String> resources) {
		this.resources = resources;
	}
	public Integer getDurable() {
		return durable;
	}
	public void setDurable(Integer durable) {
		this.durable = durable;
	}
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}
	
	
	public List<SysItem> getGifts() {
		return gifts;
	}
	public void setGifts(List<SysItem> gifts) {
		this.gifts = gifts;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Integer getPlayerItemCurrency() {
		return playerItemCurrency;
	}
	public void setPlayerItemCurrency(Integer playerItemCurrency) {
		this.playerItemCurrency = playerItemCurrency;
	}
	public Integer getPlayerItemUnitType() {
		return playerItemUnitType;
	}
	public void setPlayerItemUnitType(Integer playerItemUnitType) {
		this.playerItemUnitType = playerItemUnitType;
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
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
	public int getTimeLeft() {
		if(timeLeft>0){
			return timeLeft;
		}else{
			return 0;
		}
	}
	public void setTimeLeft(Integer timeLeft) {
		this.timeLeft = timeLeft;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getIBuffId() {
		return iBuffId;
	}
	public void setIBuffId(Integer buffId) {
		iBuffId = buffId;
	}
	public Integer getIId() {
		return iId;
	}
	public void setIId(Integer id) {
		iId = id;
	}
	public List<PlayerItem> getPackages() {
		return packages;
	}
	public void setPackages(List<PlayerItem> packages) {
		this.packages = packages;
	}
	public Integer getBuff() {
		return buff;
	}
	public void setBuff(Integer buff) {
		this.buff = buff;
	}
	public Integer getWMaxLength() {
		return wMaxLength;
	}
	public void setWMaxLength(Integer maxLength) {
		wMaxLength = maxLength;
	}
	
	public Float getIValue() {
		return iValue;
	}
	public void setIValue(Float value) {
		iValue = value;
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
	public int getRepairCost() {
		return repairCost;
	}
	public void setRepairCost(int repairCost) {
		this.repairCost = repairCost;
	}
	public Float getWFlashBackFactor() {
		return wFlashBackFactor;
	}
	public void setWFlashBackFactor(Float flashBackFactor) {
		wFlashBackFactor = flashBackFactor;
	}
	
}
