package vo
{
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysItem")]
	public class SysItem
	{
		public function SysItem()
		{
		}
		public var  id:int;
		public var  name:String="";
		public var  displayNameCN:String="";
		public var	resourceStable:String="";
		public var	resourceChangeable:String="";
		public var  type:int=0;
		public var  subType:int=0;
		public var  level:int=1;
		public var  modifiedDesc:String="";
		public var  WId:int=0;
		public var  WChangeInTime:Number=0;
		public var  WMoveSpeedOffset:Number=0;
		public var  WCrossOffset:Number=0;
		public var  WAccuracyDivisor:int;
		public var  WAccuracyOffset:Number=0;
		public var  WMaxInaccuracy:Number=0;
		public var  WPenetration:int=0;
		public var  WDamage:int=0;
		public var  WRangeModifier:Number=0;
		public var  WFireTime:Number=0;
		public var  WReloadTime:Number=0;
		public var  WAmmoOneClip:int=0;
		public var  WAmmoCount:int=0;
		public var  WAutoFire:int=0;
		public var  WTimeToIdle:Number=0;
		public var  WNormalOffset:Number=0;
		public var  WNormalFactor:Number=0;
		public var  WOnairOffset:Number=0;
		public var  WOnairFactor:Number=0;
		public var  WMoveOffset:Number=0;
		public var  WMoveFactor:Number=0;
		public var  WNormalUpBase:Number=0;
		public var  WNormalLateralBase:Number=0;
		public var  WNormalUpModifier:Number=0;
		public var  WNormalLateralModifier:Number=0;
		public var  WNormalUpMax:Number=0;
		public var  WNormalLateralMax:Number=0;
		public var  WNormalDirChange:Number=0;
		public var  WMoveUpBase:Number=0;
		public var  WMoveLateralBase:Number=0;
		public var  WMoveUpModifier:Number=0;
		public var  WMoveLateralModifier:Number=0;
		public var  WMoveUpMax:Number=0;
		public var  WMoveLateralMax:Number=0;
		public var  WMoveDirChange:Number=0;
		public var  WOnairUpBase:Number=0;
		public var  WOnairLateralBase:Number=0;
		public var  WOnairUpModifier:Number=0;
		public var  WOnairLateralModifier:Number=0;
		public var  WOnairUpMax:Number=0;
		public var  WOnairLateralMax:Number=0;
		public var  WOnairDirChange:Number=0;
		public var  WCrouchUpBase:Number=0;
		public var  WCrouchLateralBase:Number=0;
		public var  WCrouchUpModifier:Number=0;
		public var  WCrouchLateralModifier:Number=0;
		public var  WCrouchUpMax:Number=0;
		public var  WCrouchLateralMax:Number=0;
		public var  WCrouchDirChange:Number=0;
		public var  WUpModifier:Number=0;
		public var  WStabTime:Number=0;
		public var  WStabLightTime:Number=0;
		public var  WStabHurt:Number=0;
		public var  WStabLightHurt:Number=0;
		public var  WExplodeTime:Number=0;
		public var  WReadyTime:Number=0;
		public var  WThrowOutTime:Number=0;
		public var  WHurtRange:Number=0;
		public var  WHurt:Number=0;
		public var logVersion:int;
		public var logId:int;
		public var logTime:Date;
		public var IBuffId:int=0;
		public var  IId:int=0;
		public var isDeleted:String="N";
		public var isChange:int=0;
		public var descriptionCN:String="";
		public var items:String="";
		public var resources:ArrayCollection;
		public var packages:ArrayCollection;
		
		public var  WShootBulletCount:int;
		public var  WSpread:Number=0;
		public var  WFireMaxSpeed:Number=0;
		public var  WFireStartSpeed:Number=0;
		public var  WFireAceleration:Number=0;
		public var  WFireResistance:Number=0;
		public var  CId:int;
		public var 	WRangeStart:Number=0;
		public var	WRangeEnd:Number=0;
		public var	WAccuracyTime:Number=0;
		public var	WAccuracyTimeModefier:Number=0;
		public var	WMaxAccuracy:Number=0;
		public var	WMinAccuracy:Number=0;
		public var	WCrossLengthBase:Number=0;
		public var	WCrossLengthFactor:Number=0;
		public var	WCrossDistanceBase:Number=0;
		public var	WCrossDistanceFactor:Number=0;
		
		public var	WXOffset:Number=0;
		public var	WSightNormalOffset:Number=0;
		public var	WSightOnairOffset:Number=0;
		public var	WSightMoveOffset:Number=0;
		public var	WStabDistance:Number=0;
		public var	WStabLightDistance:Number=0;
		public var	WStabWidth:Number=0;
		public var	WBackFactor:Number=0;
		public var	WFlashRangeStart:Number=0;
		public var	WFlashRangeEnd:Number=0;
		public var  WFlashBackFactor:Number=0;
		public var	WTimeMax:Number=0;
		public var	WTimeFade:Number=0;
		public var	WTime:Number=0;
		
		public var  WHitSpeed:Number=0;
		public var 	WHitAcceleration:Number=0;
		public var  WHitDistance:Number=0;
		public var  WSightInfo:String="0";
		public var  IValue:String="0";
		
		public var WMaxDistance:Number=0;
		public var WAddBlood:int=0;
		public var WAmmoType:int=0;
		
		public var WFlySpeed:Number=0;
		public var WMaxaliveTime:Number=0;
		public var WGravity:int=0;
		public var WLastHurt:Number=0;
		public var WLastTime:Number=0;
		public var WSpecialDistance:Number=0;
		public var WSpecialRange:Number=0;
		public var WSpecialLasttime:Number=0;
		public var WSpecialHurt:Number=0;
		public var WSpecialLasthurt:Number=0;
		public var WParticlenum:Number=0;
		public var WShowSpeed:Number=0;
		public var WAmmopartKey:String="";
		public var WCritRate:int=0;
		public var WCritAvailable:int=0;
		public var WDamageModifer:int=0;
		public var WDmgModifyTimerMin:Number=0;
		public var WDmgModifyTimerMax:Number=0;
		public var WDmgModifyMin:Number=0;
		public var WDmgModifyMax:Number=0;
		public var WCapsuleHeight:Number=0;
		public var WCapsuleRadius:Number=0;
		
		public var gunProperty1:String="";
		public var gunProperty2:String="";
		public var gunProperty3:String="";
		public var gunProperty4:String="";
		public var gunProperty5:String="";
		public var gunProperty6:String="";
		public var gunProperty7:String="";
		public var isVip:int =0;
		public var isNew:int =0;
		public var isHot:int =0;
		public var isWeb:int =0;
		public var isPopular:int =0;
		public var isExchange:int =0;
		public var isAsset:int =0;
		public var itemIndex:int =0;
		public var WFixPrice:int =0;
		public var CResistanceFire:Number=0;
		public var CResistanceBlast:Number=0;
		public var CResistanceBullet:Number=0;
		public var CResistanceKnife:Number=0;
		public var CBloodAdd:int=0;
		public var isStrengthen:int=0;
		public var characterId:String="0";
		public var MType:int =0;
		public var MValue:int =0;
		public var rareLevel:int =0;
		public var fightNum:int =0;
		public var WBackBoostPlus:int;
		public var EvaluateRmb:Number =0;

		public var strengthLevel:int = 0;
		
		public var TeamOccupyLength:Number =0;
		
		public var TeamOccupyWidth:Number =0;
		
		public var NeedTeamPlaceLevel:Number =0;
		
		public var TeamItemUpgradePrice:Number =0;
		
		public var TimeForCreate:Number =0;


		[Transient]
		public var selected:Boolean = false;
		
		[Transient]
		public var radarSelected:Boolean = false;
		
		public function get evaluateRmb():Number{
			return Number(this.EvaluateRmb.toFixed(2));
		}
		public function set evaluateRmb(num:Number):void{
			this.EvaluateRmb=num;
		}
		public function get cResistanceKnife():Number{
			return Number(this.CResistanceKnife.toFixed(3));
		}
		public function set cResistanceKnife(num:Number):void{
			this.CResistanceKnife=num;
		}
		public function get cResistanceBullet():Number{
			return Number(this.CResistanceBullet.toFixed(3));
		}
		public function set cResistanceBullet(num:Number):void{
			this.CResistanceBullet=num;
		}
		public function get cResistanceBlast():Number{
			return Number(this.CResistanceBlast.toFixed(3));
		}
		public function set cResistanceBlast(num:Number):void{
			this.CResistanceBlast=num;
		}
		public function get cResistanceFire():Number{
			return Number(this.CResistanceFire.toFixed(3));
		}
		public function set cResistanceFire(num:Number):void{
			this.CResistanceFire=num;
		}
		public function get wCapsuleRadius():Number{
			return Number(this.WCapsuleRadius.toFixed(3));
		}
		public function set wCapsuleRadius(num:Number):void{
			this.WCapsuleRadius=num;
		}
		public function get wCapsuleHeight():Number{
			return Number(this.WCapsuleHeight.toFixed(3));
		}
		public function set wCapsuleHeight(num:Number):void{
			this.WCapsuleHeight=num;
		}
		public function get wDmgModifyMax():Number{
			return Number(this.WDmgModifyMax.toFixed(3));
		}
		public function set wDmgModifyMax(num:Number):void{
			this.WDmgModifyMax=num;
		} 
		public function get wDmgModifyMin():Number{
			return Number(this.WDmgModifyMin.toFixed(3));
		}
		public function set wDmgModifyMin(num:Number):void{
			this.WDmgModifyMin=num;
		} 
		public function get wDmgModifyTimerMax():Number{
			return Number(this.WDmgModifyTimerMax.toFixed(3));
		}
		public function set wDmgModifyTimerMax(num:Number):void{
			this.WDmgModifyTimerMax=num;
		} 
		public function get wDmgModifyTimerMin():Number{
			return Number(this.WDmgModifyTimerMin.toFixed(3));
		}
		public function set wDmgModifyTimerMin(num:Number):void{
			this.WDmgModifyTimerMin=num;
		}
		public function get wShowSpeed():Number{
			return Number(this.WShowSpeed.toFixed(3));
		}
		public function set wShowSpeed(num:Number):void{
			this.WShowSpeed=num;
		}
		public function get wParticlenum():Number{
			return Number(this.WParticlenum.toFixed(3));
		}
		public function set wParticlenum(num:Number):void{
			this.WParticlenum=num;
		}
		public function get wSpecialLasthurt():Number{
			return Number(this.WSpecialLasthurt.toFixed(3));
		}
		public function set wSpecialLasthurt(num:Number):void{
			this.WSpecialLasthurt=num;
		}
		public function get wSpecialHurt():Number{
			return Number(this.WSpecialHurt.toFixed(3));
		}
		public function set wSpecialHurt(num:Number):void{
			this.WSpecialHurt=num;
		}
		public function get wSpecialLasttime():Number{
			return Number(this.WSpecialLasttime.toFixed(3));
		}
		public function set wSpecialLasttime(num:Number):void{
			this.WSpecialLasttime=num;
		}
		public function get wSpecialRange():Number{
			return Number(this.WSpecialRange.toFixed(3));
		}
		public function set wSpecialRange(num:Number):void{
			this.WSpecialRange=num;
		}
		public function get wSpecialDistance():Number{
			return Number(this.WSpecialDistance.toFixed(3));
		}
		public function set wSpecialDistance(num:Number):void{
			this.WSpecialDistance=num;
		}
		public function get wLastTime():Number{
			return Number(this.WLastTime.toFixed(3));
		}
		public function set wLastTime(num:Number):void{
			this.WLastTime=num;
		}
		public function get wLastHurt():Number{
			return Number(this.WLastHurt.toFixed(3));
		}
		public function set wLastHurt(num:Number):void{
			this.WLastHurt=num;
		}
		public function get wMaxaliveTime():Number{
			return Number(this.WMaxaliveTime.toFixed(3));
		}
		public function set wMaxaliveTime(num:Number):void{
			this.WMaxaliveTime=num;
		}
		public function get wFlySpeed():Number{
			return Number(this.WFlySpeed.toFixed(3));
		}
		public function set wFlySpeed(num:Number):void{
			this.WFlySpeed=num;
		}
		public function get wMaxDistance():Number{
			return Number(this.WMaxDistance.toFixed(3));
		}
		public function set wMaxDistance(num:Number):void{
			this.WMaxDistance=num;
		}
		public function get wHitDistance():Number{
			return Number(this.WHitDistance.toFixed(3));
		}
		public function set wHitDistance(num:Number):void{
			this.WHitDistance=num;
		}
		public function get wHitAcceleration():Number{
			return Number(this.WHitAcceleration.toFixed(3));
		}
		public function set wHitAcceleration(num:Number):void{
			this.WHitAcceleration=num;
		}
		public function get wHitSpeed():Number{
			return Number(this.WHitSpeed.toFixed(3));
		}
		public function set wHitSpeed(num:Number):void{
			this.WHitSpeed=num;
		}
		public function get iValue():String{
			return this.IValue;
		}
		public function set iValue(str:String):void{
			this.IValue=str;
		}
		
		public function get wFlashBackFactor():Number{
			return Number(this.WFlashBackFactor.toFixed(3));
		}
		public function set wFlashBackFactor(num:Number):void{
			this.WFlashBackFactor=num;
		}
		
		public function get wTime():Number{
			return Number(this.WTime.toFixed(3));
		}
		public function set wTime(num:Number):void{
			this.WTime=num;
		}
		public function get wTimeFade():Number{
			return Number(this.WTimeFade.toFixed(3));
		}
		public function set wTimeFade(num:Number):void{
			this.WTimeFade=num;
		}
		public function get wTimeMax():Number{
			return Number(this.WTimeMax.toFixed(3));
		}
		public function set wTimeMax(num:Number):void{
			this.WTimeMax=num;
		}
		public function get wFlashRangeEnd():Number{
			return Number(this.WFlashRangeEnd.toFixed(3));
		}
		public function set wFlashRangeEnd(num:Number):void{
			this.WFlashRangeEnd=num;
		}
		public function get wFlashRangeStart():Number{
			return Number(this.WFlashRangeStart.toFixed(3));
		}
		public function set wFlashRangeStart(num:Number):void{
			this.WFlashRangeStart=num;
		}
		public function get wBackFactor():Number{
			return Number(this.WBackFactor.toFixed(3));
		}
		public function set wBackFactor(num:Number):void{
			this.WBackFactor=num;
		}
		public function get wStabWidth():Number{
			return Number(this.WStabWidth.toFixed(3));
		}
		public function set wStabWidth(num:Number):void{
			this.WStabWidth=num;
		}
		public function get wStabLightDistance():Number{
			return Number(this.WStabLightDistance.toFixed(3));
		}
		public function set wStabLightDistance(num:Number):void{
			this.WStabLightDistance=num;
		}
		public function get wStabDistance():Number{
			return Number(this.WStabDistance.toFixed(3));
		}
		public function set wStabDistance(num:Number):void{
			this.WStabDistance=num;
		}
		public function get wSightMoveOffset():Number{
			return Number(this.WSightMoveOffset.toFixed(3));
		}
		public function set wSightMoveOffset(num:Number):void{
			this.WSightMoveOffset=num;
		}
		public function get wSightOnairOffset():Number{
			return Number(this.WSightOnairOffset.toFixed(3));
		}
		public function set wSightOnairOffset(num:Number):void{
			this.WSightOnairOffset=num;
		}
		public function get wSightNormalOffset():Number{
			return Number(this.WSightNormalOffset.toFixed(3));
		}
		public function set wSightNormalOffset(num:Number):void{
			this.WSightNormalOffset=num;
		}
		public function get wXOffset():Number{
			return Number(this.WXOffset.toFixed(3));
		}
		public function set wXOffset(num:Number):void{
			this.WXOffset=num;
		}
		
		public function get wCrossDistanceFactor():Number{
			return Number(this.WCrossDistanceFactor.toFixed(3));
		}
		public function set wCrossDistanceFactor(num:Number):void{
			this.WCrossDistanceFactor=num;
		}
		public function get wCrossDistanceBase():Number{
			return Number(this.WCrossDistanceBase.toFixed(3));
		}
		public function set wCrossDistanceBase(num:Number):void{
			this.WCrossDistanceBase=num;
		}
		public function get wCrossLengthFactor():Number{
			return Number(this.WCrossLengthFactor.toFixed(3));
		}
		public function set wCrossLengthFactor(num:Number):void{
			this.WCrossLengthFactor=num;
		}
		public function get wCrossLengthBase():Number{
			return Number(this.WCrossLengthBase.toFixed(3));
		}
		public function set wCrossLengthBase(num:Number):void{
			this.WCrossLengthBase=num;
		}
		public function get wMinAccuracy():Number{
			return Number(this.WMinAccuracy.toFixed(3));
		}
		public function set wMinAccuracy(num:Number):void{
			this.WMinAccuracy=num;
		}
		public function get wMaxAccuracy():Number{
			return Number(this.WMaxAccuracy.toFixed(3));
		}
		public function set wMaxAccuracy(num:Number):void{
			this.WMaxAccuracy=num;
		}
		public function get wAccuracyTimeModefier():Number{
			return Number(this.WAccuracyTimeModefier.toFixed(3));
		}
		public function set wAccuracyTimeModefier(num:Number):void{
			this.WAccuracyTimeModefier=num;
		}
		public function get wAccuracyTime():Number{
			return Number(this.WAccuracyTime.toFixed(3));
		}
		public function set wAccuracyTime(num:Number):void{
			this.WAccuracyTime=num;
		}
		public function get wRangeEnd():Number{
			return Number(this.WRangeEnd.toFixed(3));
		}
		public function set wRangeEnd(num:Number):void{
			this.WRangeEnd=num;
		}
		public function get wRangeStart():Number{
			return Number(this.WRangeStart.toFixed(3));
		}
		public function set wRangeStart(num:Number):void{
			this.WRangeStart=num;
		}
		
		public function get wFireTime():Number{
			return Number(this.WFireTime.toFixed(3));
		}
		public function set wFireTime(num:Number):void{
			this.WFireTime=num;
		}
		public function get wRangeModifier():Number{
			return Number(this.WRangeModifier.toFixed(3));
		}
		public function set wRangeModifier(num:Number):void{
			this.WRangeModifier=num;
		}
		public function get wMaxInaccuracy():Number{
			return Number(this.WMaxInaccuracy.toFixed(3));
		}
		public function set wMaxInaccuracy(num:Number):void{
			this.WMaxInaccuracy=num;
		}
		public function get wAccuracyOffset():Number{
			return Number(this.WAccuracyOffset.toFixed(3));
		}
		
		public function set wChangeInTime(num:Number):void{
			this.WChangeInTime=num;
		}
		public function get wChangeInTime():Number{
			return Number(this.WChangeInTime.toFixed(3));
		}
		
		public function set wAccuracyOffset(num:Number):void{
			this.WAccuracyOffset=num;
		}
		public function get wCrossOffset():Number{
			return Number(this.WCrossOffset.toFixed(3));
		}
		public function set wCrossOffset(num:Number):void{
			this.WCrossOffset=num;
		}
		public function get wMoveSpeedOffset():Number{
			return Number(this.WMoveSpeedOffset.toFixed(3));
		}
		public function set wMoveSpeedOffset(num:Number):void{
			this.WMoveSpeedOffset=num;
		}
		public function get wReloadTime():Number{
			return Number(this.WReloadTime.toFixed(3));
		}
		public function set wReloadTime(num:Number):void{
			this.WReloadTime=num;
		}
		public function get wTimeToIdle():Number{
			return Number(this.WTimeToIdle.toFixed(3));
		}
		public function set wTimeToIdle(num:Number):void{
			this.WTimeToIdle=num;
		}
		public function get wNormalOffset():Number{
			return Number(this.WNormalOffset.toFixed(3));
		}
		public function set wNormalOffset(num:Number):void{
			this.WNormalOffset=num;
		}
		public function get wNormalFactor():Number{
			return Number(this.WNormalFactor.toFixed(3));
		}
		public function set wNormalFactor(num:Number):void{
			this.WNormalFactor=num;
		}
		public function get wOnairOffset():Number{
			return Number(this.WOnairOffset.toFixed(3));
		}
		public function set wOnairOffset(num:Number):void{
			this.WOnairOffset=num;
		}
		public function get wOnairFactor():Number{
			return Number(this.WOnairFactor.toFixed(3));
		}
		public function set wOnairFactor(num:Number):void{
			this.WOnairFactor=num;
		}
		public function get wMoveOffset():Number{
			return Number(this.WMoveOffset.toFixed(3));
		}
		public function set wMoveOffset(num:Number):void{
			this.WMoveOffset=num;
		}
		public function get wMoveFactor():Number{
			return Number(this.WMoveFactor.toFixed(3));
		}
		public function set wMoveFactor(num:Number):void{
			this.WMoveFactor=num;
		}
		public function get wNormalUpBase():Number{
			return Number(this.WNormalUpBase.toFixed(3));
		}
		public function set wNormalUpBase(num:Number):void{
			this.WNormalUpBase=num;
		}
		public function get wNormalLateralBase():Number{
			return Number(this.WNormalLateralBase.toFixed(3));
		}
		public function set wNormalLateralBase(num:Number):void{
			this.WNormalLateralBase=num;
		}
		public function get wNormalUpModifier():Number{
			return Number(this.WNormalUpModifier.toFixed(3));
		}
		public function set wNormalUpModifier(num:Number):void{
			this.WNormalUpModifier=num;
		}
		public function get wNormalLateralModifier():Number{
			return Number(this.WNormalLateralModifier.toFixed(3));
		}
		public function set wNormalLateralModifier(num:Number):void{
			this.WNormalLateralModifier=num;
		}
		public function get wNormalUpMax():Number{
			return Number(this.WNormalUpMax.toFixed(3));
		}
		public function set wNormalUpMax(num:Number):void{
			this.WNormalUpMax=num;
		}
		public function get wNormalLateralMax():Number{
			return Number(this.WNormalLateralMax.toFixed(3));
		}
		public function set wNormalLateralMax(num:Number):void{
			this.WNormalLateralMax=num;
		}
		public function get wNormalDirChange():Number{
			return Number(this.WNormalDirChange.toFixed(3));
		}
		public function set wNormalDirChange(num:Number):void{
			this.WNormalDirChange=num;
		}
		public function get wMoveUpBase():Number{
			return Number(this.WMoveUpBase.toFixed(3));
		}
		public function set wMoveUpBase(num:Number):void{
			this.WMoveUpBase=num;
		}
		public function get wMoveLateralBase():Number{
			return Number(this.WMoveLateralBase.toFixed(3));
		}
		public function set wMoveLateralBase(num:Number):void{
			this.WMoveLateralBase=num;
		}
		public function get wMoveUpModifier():Number{
			return Number(this.WMoveUpModifier.toFixed(3));
		}
		public function set wMoveUpModifier(num:Number):void{
			this.WMoveUpModifier=num;
		}
		public function get wMoveLateralModifier():Number{
			return Number(this.WMoveLateralModifier.toFixed(3));
		}
		public function set wMoveLateralModifier(num:Number):void{
			this.WMoveLateralModifier=num;
		}
		public function get wMoveUpMax():Number{
			return Number(this.WMoveUpMax.toFixed(3));
		}
		public function set wMoveUpMax(num:Number):void{
			this.WMoveUpMax=num;
		}
		public function get wMoveLateralMax():Number{
			return Number(this.WMoveLateralMax.toFixed(3));
		}
		public function set wMoveLateralMax(num:Number):void{
			this.WMoveLateralMax=num;
		}
		public function get wMoveDirChange():Number{
			return Number(this.WMoveDirChange.toFixed(3));
		}
		public function set wMoveDirChange(num:Number):void{
			this.WMoveDirChange=num;
		}
		public function get wOnairUpBase():Number{
			return Number(this.WOnairUpBase.toFixed(3));
		}
		public function set wOnairUpBase(num:Number):void{
			this.WOnairUpBase=num;
		}
		public function get wOnairLateralBase():Number{
			return Number(this.WOnairLateralBase.toFixed(3));
		}
		public function set wOnairLateralBase(num:Number):void{
			this.WOnairLateralBase=num;
		}
		public function get wOnairUpModifier():Number{
			return Number(this.WOnairUpModifier.toFixed(3));
		}
		public function set wOnairUpModifier(num:Number):void{
			this.WOnairUpModifier=num;
		}
		public function get wOnairLateralModifier():Number{
			return Number(this.WOnairLateralModifier.toFixed(3));
		}
		public function set wOnairLateralModifier(num:Number):void{
			this.WOnairLateralModifier=num;
		}
		public function get wOnairUpMax():Number{
			return Number(this.WOnairUpMax.toFixed(3));
		}
		public function set wOnairUpMax(num:Number):void{
			this.WOnairUpMax=num;
		}
		public function get wOnairLateralMax():Number{
			return Number(this.WOnairLateralMax.toFixed(3));
		}
		public function set wOnairLateralMax(num:Number):void{
			this.WOnairLateralMax=num;
		}
		public function get wOnairDirChange():Number{
			return Number(this.WOnairDirChange.toFixed(3));
		}
		public function set wOnairDirChange(num:Number):void{
			this.WOnairDirChange=num;
		}
		public function get wCrouchUpBase():Number{
			return Number(this.WCrouchUpBase.toFixed(3));
		}
		public function set wCrouchUpBase(num:Number):void{
			this.WCrouchUpBase=num;
		}
		public function get wCrouchLateralBase():Number{
			return Number(this.WCrouchLateralBase.toFixed(3));
		}
		public function set wCrouchLateralBase(num:Number):void{
			this.WCrouchLateralBase=num;
		}
		public function get wCrouchUpModifier():Number{
			return Number(this.WCrouchUpModifier.toFixed(3));
		}
		public function set wCrouchUpModifier(num:Number):void{
			this.WCrouchUpModifier=num;
		}
		public function get wCrouchLateralModifier():Number{
			return Number(this.WCrouchLateralModifier.toFixed(3));
		}
		public function set wCrouchLateralModifier(num:Number):void{
			this.WCrouchLateralModifier=num;
		}
		public function get wCrouchUpMax():Number{
			return Number(this.WCrouchUpMax.toFixed(3));
		}
		public function set wCrouchUpMax(num:Number):void{
			this.WCrouchUpMax=num;
		}
		public function get wCrouchLateralMax():Number{
			return Number(this.WCrouchLateralMax.toFixed(3));
		}
		public function set wCrouchLateralMax(num:Number):void{
			this.WCrouchLateralMax=num;
		}
		public function get wCrouchDirChange():Number{
			return Number(this.WCrouchDirChange.toFixed(3));
		}
		public function set wCrouchDirChange(num:Number):void{
			this.WCrouchDirChange=num;
		}
		public function get wUpModifier():Number{
			return Number(this.WUpModifier.toFixed(3));
		}
		public function set wUpModifier(num:Number):void{
			this.WUpModifier=num;
		}
		public function get wStabTime():Number{
			return Number(this.WStabTime.toFixed(3));
		}
		public function set wStabTime(num:Number):void{
			this.WStabTime=num;
		}
		public function get wStabLightTime():Number{
			return Number(this.WStabLightTime.toFixed(3));
		}
		public function set wStabLightTime(num:Number):void{
			this.WStabLightTime=num;
		}
		public function get wStabHurt():Number{
			return Number(this.WStabHurt.toFixed(3));
		}
		public function set wStabHurt(num:Number):void{
			this.WStabHurt=num;
		}
		public function get wStabLightHurt():Number{
			return Number(this.WStabLightHurt.toFixed(3));
		}
		public function set wStabLightHurt(num:Number):void{
			this.WStabLightHurt=num;
		}
		public function get wExplodeTime():Number{
			return Number(this.WExplodeTime.toFixed(3));
		}
		public function set wExplodeTime(num:Number):void{
			this.WExplodeTime=num;
		}
		public function get wReadyTime():Number{
			return Number(this.WReadyTime.toFixed(3));
		}
		public function set wReadyTime(num:Number):void{
			this.WReadyTime=num;
		}
		public function get wThrowOutTime():Number{
			return Number(this.WThrowOutTime.toFixed(3));
		}
		public function set wThrowOutTime(num:Number):void{
			this.WThrowOutTime=num;
		}
		public function get wHurtRange():Number{
			return Number(this.WHurtRange.toFixed(3));
		}
		public function set wHurtRange(num:Number):void{
			this.WHurtRange=num;
		}
		public function get wHurt():Number{
			return Number(this.WHurt.toFixed(3));
		}
		public function set wHurt(num:Number):void{
			this.WHurt=num;
		}
		public function get wSpread():Number{
			return Number(this.WSpread.toFixed(3));
		}
		public function set wSpread(num:Number):void{
			this.WSpread=num;
		}
		public function get wFireStartSpeed():Number{
			return Number(this.WFireStartSpeed.toFixed(3));
		}
		public function set wFireStartSpeed(num:Number):void{
			this.WFireStartSpeed=num;
		}
		public function get wFireMaxSpeed():Number{
			return Number(this.WFireMaxSpeed.toFixed(3));
		}
		public function set wFireMaxSpeed(num:Number):void{
			this.WFireMaxSpeed=num;
		}
		public function get wFireResistance():Number{
			return Number(this.WFireResistance.toFixed(3));
		}
		public function set wFireResistance(num:Number):void{
			this.WFireResistance=num;
		}
		public function get wFireAceleration():Number{
			return Number(this.WFireAceleration.toFixed(3));
		}
		public function set wFireAceleration(num:Number):void{
			this.WFireAceleration=num;
		}
		
		public function get teamOccupyLength():Number{
			return Number(this.TeamOccupyLength.toFixed(3));
		}
		public function set teamOccupyLength(num:Number):void{
			this.TeamOccupyLength=num;
		}
		
		
		public function get teamOccupyWidth():Number{
			return Number(this.TeamOccupyWidth.toFixed(3));
		}
		public function set teamOccupyWidth(num:Number):void{
			this.TeamOccupyWidth=num;
		}
		
		public function get teamItemUpgradePrice():Number{
			return Number(this.TeamItemUpgradePrice.toFixed(3));
		}
		public function set teamItemUpgradePrice(num:Number):void{
			this.TeamItemUpgradePrice=num;
		}
				
		public function get timeForCreate():Number{
			return Number(this.TimeForCreate.toFixed(3));
		}
		public function set timeForCreate(num:Number):void{
			this.TimeForCreate=num;
		}								
				
		public function get needTeamPlaceLevel():Number{
			return this.NeedTeamPlaceLevel;
		}
		public function set needTeamPlaceLevel(num:Number):void{
			this.NeedTeamPlaceLevel=num;
		}
	
	}
}