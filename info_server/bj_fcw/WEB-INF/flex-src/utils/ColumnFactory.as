package utils
{
	import common.Constants;
	
	import component.MyDataGridColumn;
	
	import mx.controls.CheckBox;
	import mx.core.ClassFactory;
	
	import renderer.CheckBoxIsAssetRenderer;
	import renderer.CheckBoxIsExchangeRenderer;
	import renderer.CheckBoxIsHotRenderer;
	import renderer.CheckBoxIsNewRenderer;
	import renderer.CheckBoxIsPopularRenderer;
	import renderer.CheckBoxIsVipRenderer;
	import renderer.CheckBoxIsWebRenderer;
	import renderer.CheckBoxRenderer;
	import renderer.DateTimeRenderer;
	import renderer.LogSelectRenderer;
	public class ColumnFactory
	{
		public static function getLevelColumns(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,50);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("type","Type",hiLight,40));
			columns.push(new MyDataGridColumn("name","Resource",hiLight,100));
			columns.push(new MyDataGridColumn("displayNameCN","DisplayName",hiLight,100));
			columns.push(new MyDataGridColumn("startPoints","startPoints",hiLight,400));

//			columns.push(new MyDataGridColumn("flagPoints","flagPoints",hiLight,250));
//			columns.push(new MyDataGridColumn("weaponPoints","weaponPoints",hiLight,250));
//			columns.push(new MyDataGridColumn("supplyPoints","supplyPoints",hiLight,250));
			columns.push(new MyDataGridColumn("supplies","supplies",hiLight,400));
			columns.push(new MyDataGridColumn("blastPoints","targetPoints",hiLight,400));
			columns.push(new MyDataGridColumn("lineInfo","vehicleLinePoints",hiLight,400));
			columns.push(new MyDataGridColumn("bossItems","bossItems",hiLight,150));
			columns.push(new MyDataGridColumn("vehicleInfo","vehicleInfo(addblood,x,y,z)",hiLight,150));
			
//			columns.push(new MyDataGridColumn("isSelf","isSelf",hiLight,20));
//			columns.push(new MyDataGridColumn("zombieInfo","zombieInfo",hiLight,250));
			columns.push(new MyDataGridColumn("LevelHorizon","levelHorizon",hiLight,50));
			columns.push(new MyDataGridColumn("TargetSpeed","TargetSpeed",hiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","description",hiLight,50));
			columns.push(new MyDataGridColumn("isVip","is_vip",hiLight,50));
			columns.push(new MyDataGridColumn("bloodOffset","bloodOffset",hiLight,50));
			columns.push(new MyDataGridColumn("isRushGold","isRushGold",hiLight,50));
			columns.push(new MyDataGridColumn("isMoneyReward","isMoneyReward",hiLight,50));
			columns.push(new MyDataGridColumn("rushGlodPoint","rushGlodPoint",hiLight,50));
			columns.push(new MyDataGridColumn("index","index",hiLight,40));
			columns.push(new MyDataGridColumn("isNew","isNew",hiLight,40));
			columns.push(new MyDataGridColumn("num4Team","num4Team",hiLight,40));
			return columns;
		}
		public static function getBossItems(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("itemId","itemId",hiLight,50));
			columns.push(new MyDataGridColumn("paramA","paramA",hiLight,50));
			columns.push(new MyDataGridColumn("paramB","paramB",hiLight,50));
			return columns;
		}
		public static function getLevelColumnsSimple(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","id",hiLight,30);
			id.editable=false;
			columns.push(id);
			var type:MyDataGridColumn=new MyDataGridColumn("type","type",hiLight,50);
			columns.push(type);
			columns.push(new MyDataGridColumn("name","name",hiLight,150));
			columns.push(new MyDataGridColumn("displayNameCN","displayName",hiLight,100));
			//columns.push(new MyDataGridColumn("expAdd","expAdd",hiLight,100));
			//columns.push(new MyDataGridColumn("gpAdd","gpAdd",hiLight,100));
			//columns.push(new MyDataGridColumn("activityStart","activityStart",hiLight,100));
			//columns.push(new MyDataGridColumn("activityEnd","activityEnd",hiLight,100));
			return columns;
		}
		public static function getPoint(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			return columns;
		}
		public static function getVehicleInfo(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("addBlood","addBlood",hiLight,50));
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			return columns;
		}
		public static function getGamePoint(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("teamId","teamId",hiLight,50));
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			columns.push(new MyDataGridColumn("rotate","rotate",hiLight,50));
			
			return columns;
		}
		public static function getWeaponPoint(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("weaponId","weaponId",hiLight,50));
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			columns.push(new MyDataGridColumn("rotate","rotate",hiLight,50));
			columns.push(new MyDataGridColumn("type","type",hiLight,50));
			return columns;
		}
		public static function getSupplies(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			columns.push(new MyDataGridColumn("type","type",hiLight,50));
			columns.push(new MyDataGridColumn("name","name",hiLight,50));
			columns.push(new MyDataGridColumn("value","value",hiLight,50));
			columns.push(new MyDataGridColumn("random","random",hiLight,50));
			columns.push(new MyDataGridColumn("skillTime","skillTime",hiLight,50));
			return columns;
		}
		public static function getFlagPoint(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("teamId","teamId",hiLight,50));
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			columns.push(new MyDataGridColumn("rotate","rotate",hiLight,50));
			columns.push(new MyDataGridColumn("x1","x1",hiLight,50));
			columns.push(new MyDataGridColumn("y1","y1",hiLight,50));
			columns.push(new MyDataGridColumn("z1","z1",hiLight,50));
			return columns;
		}
		public static function getBlastPoint(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			columns.push(new MyDataGridColumn("rotate","rotate",hiLight,50));
			columns.push(new MyDataGridColumn("x1","x1",hiLight,50));
			columns.push(new MyDataGridColumn("y1","y1",hiLight,50));
			columns.push(new MyDataGridColumn("z1","z1",hiLight,50));
			return columns;
		}
		public static function getVehicleLinePoint(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("lineId","lineId/阶段",hiLight,200));
			columns.push(new MyDataGridColumn("index","index",hiLight,50));
			columns.push(new MyDataGridColumn("x","x",hiLight,50));
			columns.push(new MyDataGridColumn("y","y",hiLight,50));
			columns.push(new MyDataGridColumn("z","z",hiLight,50));
			columns.push(new MyDataGridColumn("x1","x1/BOSS",hiLight,200));
			columns.push(new MyDataGridColumn("y1","y1",hiLight,50));
			columns.push(new MyDataGridColumn("z1","z1",hiLight,50));
			columns.push(new MyDataGridColumn("isSlope","isSlope/SKILL",hiLight,200));
			return columns;
		}
		public static function getZombieInfo(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns.push(new MyDataGridColumn("type","type",hiLight,50));
			columns.push(new MyDataGridColumn("level1","level1",hiLight,200));
			columns.push(new MyDataGridColumn("level2","level2",hiLight,200));
			columns.push(new MyDataGridColumn("level3","level3",hiLight,200));
			columns.push(new MyDataGridColumn("level4","level4",hiLight,200));
			columns.push(new MyDataGridColumn("skillType","skillType",hiLight,50));
			columns.push(new MyDataGridColumn("effect","effect",hiLight,100));
			columns.push(new MyDataGridColumn("effectTime","effectTime",hiLight,100));
			columns.push(new MyDataGridColumn("cooldown","cooldown",hiLight,100));
			columns.push(new MyDataGridColumn("hurtAddition","hurtAddition",hiLight,100));
			return columns;
		}
		public static function getSysItemName(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","id",hiLight,30);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("displayNameCN","displayName",hiLight,50));
			columns.push(new MyDataGridColumn("name","name",hiLight,50));
			return columns;
		}
		public static function getSysItemName2(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var ck:MyDataGridColumn=new MyDataGridColumn("selected"," ",false,20);
			ck.itemRenderer=new ClassFactory(CheckBox);
			columns.push(ck);
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,80);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("displayNameCN","DisplayName",hiLight,100));
			columns.push(new MyDataGridColumn("name","Resource",hiLight,100));
			return columns;
		}
		public static function getAchievement(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var ck:MyDataGridColumn=new MyDataGridColumn("selected"," ",false,20);
			ck.itemRenderer=new ClassFactory(CheckBox);
			columns.push(ck);
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,50);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("action","Action",hiLight,80));
			columns.push(new MyDataGridColumn("descriptionCN","Description",hiLight,50));
			return columns;
		}
		public static function getWeaponColumns(isHiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","id",isHiLight,30);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("displayNameCN","DisplayName",isHiLight,100));
			columns.push(new MyDataGridColumn("name","Name",isHiLight,100));
			columns.push(new MyDataGridColumn("itemId","sysItemId",isHiLight,100));
			columns.push(new MyDataGridColumn("sysLevelId","sysLevelId",isHiLight,100));
			columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("resourceChangeable","resourceChangeable",isHiLight,220));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
			columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
			columns.push(new MyDataGridColumn("WPenetration","wPenetration",isHiLight,100));
			columns.push(new MyDataGridColumn("WDamage","wDamage",isHiLight,100));
			columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier",isHiLight,100));
			columns.push(new MyDataGridColumn("wFireTime","wFireTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wReloadTime","wReloadTime",isHiLight,100));
			columns.push(new MyDataGridColumn("WAmmoOneClip","wAmmoOneClip",isHiLight,100));
			columns.push(new MyDataGridColumn("WAmmoCount","wAmmoCount",isHiLight,100));
			columns.push(new MyDataGridColumn("WAutoFire","wAutoFire",isHiLight,100));
			columns.push(new MyDataGridColumn("wTimeToIdle","wTimeToIdle",isHiLight,100));
			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,100,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,100,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wUpModifier","wUpModifier",isHiLight,100));
			columns.push(new MyDataGridColumn("wStabTime","wStabTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wStabLightTime","wStabLightTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wStabHurt","wStabHurt",isHiLight,100));
			columns.push(new MyDataGridColumn("wStabLightHurt","wStabLightHurt",isHiLight,100));
			columns.push(new MyDataGridColumn("wExplodeTime","wExplodeTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wReadyTime","wReadyTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wThrowOutTime","wThrowOutTime",isHiLight,100));
			columns.push(new MyDataGridColumn("wHurtRange","wHurtRange",isHiLight,100));
			columns.push(new MyDataGridColumn("wHurt","wHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wRangeStart","wRangeStart",isHiLight,50));
			columns.push(new MyDataGridColumn("wRangeEnd","wRangeEnd",isHiLight,50));
			columns.push(new MyDataGridColumn("wAccuracyTime","wAccuracyTime",isHiLight,50));
			columns.push(new MyDataGridColumn("wAccuracyTimeModefier","wAccuracyTimeModefier",isHiLight,50));
			columns.push(new MyDataGridColumn("wMaxAccuracy","wMaxAccuracy",isHiLight,50));
			columns.push(new MyDataGridColumn("wMinAccuracy","wMinAccuracy",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossLengthBase","wCrossLengthBase",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossLengthFactor","wCrossLengthFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossDistanceBase","wCrossDistanceBase",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossDistanceFactor","wCrossDistanceFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("WShootBulletCount","wShootBulletCount",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpread","wSpread",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireMaxSpeed","wFireMaxSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireStartSpeed","wFireStartSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireAceleration","wFireAceleration",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireResistance","wFireResistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wXOffset","WXOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wSightNormalOffset","WSightNormalOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wSightOnairOffset","WSightOnairOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wSightMoveOffset","WSightMoveOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wStabDistance","WStabDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wStabLightDistance","WStabLightDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wStabWidth","WStabWidth",isHiLight,50));
			columns.push(new MyDataGridColumn("wBackFactor","WBackFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlashRangeStart","WFlashRangeStart",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlashRangeEnd","WFlashRangeEnd",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlashBackFactor","wFlashBackFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("wTimeMax","WTimeMax",isHiLight,50));
			columns.push(new MyDataGridColumn("wTimeFade","WTimeFade",isHiLight,50));
			columns.push(new MyDataGridColumn("wTime","WTime",isHiLight,50));
			
			columns.push(new MyDataGridColumn("wHitSpeed","WHitSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wHitAcceleration","WHitAcceleration",isHiLight,50));
			columns.push(new MyDataGridColumn("wHitDistance","WHitDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("WSightInfo","WSightInfo",isHiLight,50));
			
			columns.push(new MyDataGridColumn("wMaxDistance","WMaxDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("WAddBlood","wAddBlood",isHiLight,50));
			columns.push(new MyDataGridColumn("WAmmoType","wAmmoType",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlySpeed","WFlySpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wMaxaliveTime","WMaxaliveTime",isHiLight,50));
			columns.push(new MyDataGridColumn("WGravity","wGravity",isHiLight,50));
			columns.push(new MyDataGridColumn("wLastHurt","WLastHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wLastTime","WLastTime",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialDistance","WSpecialDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialRange","WSpecialRange",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialLasttime","WSpecialLasttime",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialHurt","WSpecialHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialLasthurt","WSpecialLasthurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wParticlenum","WParticlenum",isHiLight,50));
			columns.push(new MyDataGridColumn("wShowSpeed","WShowSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("WAmmopartKey","wAmmopartKey",isHiLight,50));
			columns.push(new MyDataGridColumn("WCritRate","WCritRate",isHiLight,50));
			columns.push(new MyDataGridColumn("WCritAvailable","WCritAvailable",isHiLight,50));
			
			columns.push(new MyDataGridColumn("WDmgModifyTimerMin","WDmgModifyTimerMin",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyTimerMax","WDmgModifyTimerMax",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyMin","WDmgModifyMin",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyMax","WDmgModifyMax",isHiLight,50));
			columns.push(new MyDataGridColumn("WCapsuleHeight","WCapsuleHeight",isHiLight,50));
			columns.push(new MyDataGridColumn("WCapsuleRadius","WCapsuleRadius",isHiLight,50));
			columns.push(new MyDataGridColumn("WDamageModifer","WDamageModifer",isHiLight,50));
			
			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
			return columns;
		}
		public static function getCharacterColumns(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,100);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("nameCN","Name",hiLight,100));
				columns.push(new MyDataGridColumn("maxHp","maxHp",hiLight,50));
				columns.push(new MyDataGridColumn("exHp","exHp",hiLight,50));
				columns.push(new MyDataGridColumn("defaultLevel","level",hiLight,50));
				columns.push(new MyDataGridColumn("runSpeed","runSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("walkSpeed","walkSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("crouchSpeed","crouchSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("accelSpeed","AccelSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("jumpSpeed","jumpSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("throwSpeed","throwSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("costumeResource","costumeResource",hiLight,300));
				columns.push(new MyDataGridColumn("cost","cost",hiLight,50));
				columns.push(new MyDataGridColumn("isFired","isFired",hiLight,50));
				columns.push(new MyDataGridColumn("isDefault","isDefault",hiLight,50));
				return columns;
			}
		public static function getBioCharacterColumns(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,30);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("cid","ID",hiLight,50));
				columns.push(new MyDataGridColumn("sid","ItemID",hiLight,50));
				columns.push(new MyDataGridColumn("type","Type",hiLight,40));
				columns.push(new MyDataGridColumn("weapons","Weapons",hiLight,100));
				columns.push(new MyDataGridColumn("costumes","Costumes",hiLight,70));
				columns.push(new MyDataGridColumn("nameCN","Name",hiLight,120));
				
				columns.push(new MyDataGridColumn("runSpeed","runS",hiLight,50));
				columns.push(new MyDataGridColumn("walkSpeed","walkS",hiLight,50));
				columns.push(new MyDataGridColumn("crouchSpeed","crouchS",hiLight,50));
				columns.push(new MyDataGridColumn("accelSpeed","AccelS",hiLight,50));
				columns.push(new MyDataGridColumn("jumpSpeed","jumpS",hiLight,50));
				columns.push(new MyDataGridColumn("throwSpeed","throwS",hiLight,50));
				
				columns.push(new MyDataGridColumn("costumeResource","costumeResource",hiLight,120));
				columns.push(new MyDataGridColumn("isDefault","isDef",hiLight,50));

				columns.push(new MyDataGridColumn("maxHp","maxHp",hiLight,50));
				columns.push(new MyDataGridColumn("exHp","exHp",hiLight,50));
				columns.push(new MyDataGridColumn("cost","cost",hiLight,50));
				columns.push(new MyDataGridColumn("defaultLevel","level",hiLight,50));
				columns.push(new MyDataGridColumn("isFired","isFired",hiLight,50));
				columns.push(new MyDataGridColumn("resourceName","resourceName",hiLight,100));
				columns.push(new MyDataGridColumn("isEnable","isEnable",hiLight,50));
				
				columns.push(new MyDataGridColumn("controllerHeight","cHeight",hiLight,50));
				columns.push(new MyDataGridColumn("controllerRadius","cRadius",hiLight,50));
				columns.push(new MyDataGridColumn("controllerCrouchHeight","cCrouchHeight",hiLight,80));
				columns.push(new MyDataGridColumn("scoreOffset","scoreOffset",hiLight,50));
				return columns;
			}
		public static function getPlayerColumns(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,100);
			id.editable=false;
			columns.push(id);
			var userId:MyDataGridColumn=new MyDataGridColumn("userName","UserName",hiLight,100);
			userId.editable=false;
			columns.push(userId);
			var name:MyDataGridColumn=new MyDataGridColumn("name","Name",hiLight,150);
			name.editable=false;
			columns.push(name);
			return columns;
		}
		
		public static function getPlayerColumnsAll(hiLight:Boolean=false):Array{
			var columns:Array = getPlayerColumns();
			columns.push(new MyDataGridColumn("exp","Exp",hiLight,200));
			columns.push(new MyDataGridColumn("rank","Rank",hiLight,100));
			columns.push(new MyDataGridColumn("GPoint","GamePoint\C",hiLight,200));
			columns.push(new MyDataGridColumn("GScore","Score",hiLight,200));
			columns.push(new MyDataGridColumn("credit","Credit\FC",hiLight,200));
			columns.push(new MyDataGridColumn("voucher","Coucher",hiLight,200));
			return columns;
		}
		public static function getPlayerColumns1(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var ck:MyDataGridColumn=new MyDataGridColumn("selected"," ",false,20);
			ck.itemRenderer=new ClassFactory(CheckBox);
			columns.push(ck);
			for each (var item:MyDataGridColumn in getPlayerColumns(hiLight)){
				columns.push(item);
			}
			columns.push(new MyDataGridColumn("rank","Rank",hiLight,200));
			return columns;
		}
		private static function getCheckBoxColumn():Array{
				var columns:Array = new Array();
				var ck:MyDataGridColumn=new MyDataGridColumn(""," ",false,20);
				ck.itemRenderer=new ClassFactory(LogSelectRenderer);
				columns.push(ck);
				return columns;
		}	
		public static function getCharacterLogColumns(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			columns=getCharacterColumns(hiLight);
			var checkBoxColumn:Array=getCheckBoxColumn();
			checkBoxColumn.push(new MyDataGridColumn("logId","logId",false,50));
			checkBoxColumn.push(new MyDataGridColumn("logVersion","logVersion",false,100));
			var dg:MyDataGridColumn=new MyDataGridColumn("logTime","LogTime",false,150);
			dg.itemRenderer=new ClassFactory(DateTimeRenderer);
			checkBoxColumn.push(dg);
			var returnArray:Array=checkBoxColumn.concat(columns);
			return returnArray;
		}
		//get column for datagrid dynamic
        public static function getColumnCommon():Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",false,80);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("displayNameCN","DisplayName",false,150));
			columns.push(new MyDataGridColumn("name","Resource",false,100));
			var isDeleted:MyDataGridColumn=new MyDataGridColumn("isDeleted","NotOnSale",false,80);
			isDeleted.itemRenderer=new ClassFactory(CheckBoxRenderer);
			isDeleted.editable=false;
			columns.push(isDeleted);
			columns.push(new MyDataGridColumn("WFixPrice","FixPrice",false,100));
			var isVip:MyDataGridColumn=new MyDataGridColumn("isVip","IsVip",false,100);
			columns.push(isVip);
			var isWeb:MyDataGridColumn=new MyDataGridColumn("isWeb","IsInternetCafe",false,100);
			isWeb.itemRenderer=new ClassFactory(CheckBoxIsWebRenderer);
			isWeb.editable=false;
			columns.push(isWeb);
			var isNew:MyDataGridColumn=new MyDataGridColumn("isNew","IsNew",false,80);
			isNew.itemRenderer=new ClassFactory(CheckBoxIsNewRenderer);
			isNew.editable=false;
			columns.push(isNew);
			var isHot:MyDataGridColumn=new MyDataGridColumn("isHot","CancelPresent",false,80);
			isHot.itemRenderer=new ClassFactory(CheckBoxIsHotRenderer);
			isHot.editable=false;
			columns.push(isHot);
			var isPopular:MyDataGridColumn=new MyDataGridColumn("isPopular","IsHot",false,80);
			isPopular.itemRenderer=new ClassFactory(CheckBoxIsPopularRenderer);
			isPopular.editable=false;
			columns.push(isPopular);
			var isExchange:MyDataGridColumn=new MyDataGridColumn("isExchange","IsExchange",false,80);
			columns.push(isExchange);
			columns.push(new MyDataGridColumn("itemIndex","Index",false,50));
			columns.push(new MyDataGridColumn("rareLevel","RareLevel",false,50));
			columns.push(new MyDataGridColumn("evaluateRmb","Value",false,70));
			columns.push(new MyDataGridColumn("level","level",false,50));
			return columns;
		}
		public static function getColumnCommon2():Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",false,80);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("displayNameCN","DisplayName",false,200));
			columns.push(new MyDataGridColumn("name","Resource",false,150));
			var isDeleted:MyDataGridColumn=new MyDataGridColumn("isDeleted","NotOnSale",false,150);
			isDeleted.itemRenderer=new ClassFactory(CheckBoxRenderer);
			isDeleted.editable=false;
			columns.push(isDeleted);
			columns.push(new MyDataGridColumn("WFixPrice","FixPrice",false,100));
			var isVip:MyDataGridColumn=new MyDataGridColumn("isVip","IsVip",false,150);
			isVip.itemRenderer=new ClassFactory(CheckBoxIsVipRenderer);
			isVip.editable=false;
			columns.push(isVip);
			var isWeb:MyDataGridColumn=new MyDataGridColumn("isWeb","IsInternetCafe",false,100);
			isWeb.itemRenderer=new ClassFactory(CheckBoxIsWebRenderer);
			isWeb.editable=false;
			columns.push(isWeb);
			var isNew:MyDataGridColumn=new MyDataGridColumn("isNew","IsNew",false,150);
			isNew.itemRenderer=new ClassFactory(CheckBoxIsNewRenderer);
			isNew.editable=false;
			columns.push(isNew);
			var isHot:MyDataGridColumn=new MyDataGridColumn("isHot","CancelPresent",false,150);
			isHot.itemRenderer=new ClassFactory(CheckBoxIsHotRenderer);
			isHot.editable=false;
			columns.push(isHot);
			return columns;
		}
		
		public static function getActivityColumn(isHiLight:Boolean):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",false,50);
			id.editable=false;
			columns.push(id);
			var isDeleted:MyDataGridColumn=new MyDataGridColumn("theSwitch","Switch",false,50);
			isDeleted.itemRenderer=new ClassFactory(CheckBoxRenderer);
			isDeleted.editable=false;
			columns.push(isDeleted);
			columns.push(new MyDataGridColumn("value","Value1",false,50));
			columns.push(new MyDataGridColumn("targetNum","Value2",false,50));
			columns.push(new MyDataGridColumn("startTimeStr","StartTime",false,50));
			columns.push(new MyDataGridColumn("endTimeStr","EndTime",false,50));
			return columns;
		}
		
		public static function getLogColumnCommon(isHiLight:Boolean):Array{
			var columns:Array = new Array();
			//check box column
			var ck:MyDataGridColumn=new MyDataGridColumn(""," ",false,20);
			ck.itemRenderer=new ClassFactory(LogSelectRenderer);
			columns.push(ck);
			//Id column
			columns.push(new MyDataGridColumn("logId","ID",false,50));
			columns.push(new MyDataGridColumn("logVersion","Version",false,50));
			columns.push(new MyDataGridColumn("id","ItemId",false,50));
			columns.push(new MyDataGridColumn("displayNameCN","DisplayName",isHiLight,100));
			columns.push(new MyDataGridColumn("name","Name",isHiLight,100));
			columns.push(new MyDataGridColumn("isDeleted","IsDeleted",isHiLight,50));
			columns.push(new MyDataGridColumn("WFixPrice","FixPrice",false,100));
			columns.push(new MyDataGridColumn("isStrengthen","IsStrengthen",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("rareLevel","RareLevel",false,100));
			var dg:MyDataGridColumn=new MyDataGridColumn("logTime","LogTime",false,150);
			
			dg.itemRenderer=new ClassFactory(DateTimeRenderer);
			columns.push(dg);
			return columns;
		}
        public static function getColumnClothes(isHiLight:Boolean):Array{
			var columns:Array = getColumnCommon();
			var isAsset:MyDataGridColumn=new MyDataGridColumn("isAsset","CancelRenew",false,80);
			isAsset.itemRenderer=new ClassFactory(CheckBoxIsAssetRenderer);
			isAsset.editable=false;
			columns.push(isAsset);
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("CId","cId",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("isStrengthen","IsStrengthen",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("fightNum","FightNum",isHiLight,50));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
			columns.push(new MyDataGridColumn("CResistanceFire","cResistanceFire",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBlast","cResistanceBlast",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBullet","cResistanceBullet",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceKnife","cResistanceKnife",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CBloodAdd","CBloodAdd",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		 }
		 
        public static function getColumnZYZDZ(isHiLight:Boolean):Array{
        	return getColumnWeapon(isHiLight);
		 }
		 		 
		public static function getLogColumnClothes(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("CId","cId",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceFire","cResistanceFire",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBlast","cResistanceBlast",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBullet","cResistanceBullet",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceKnife","cResistanceKnife",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CBloodAdd","CBloodAdd",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("fightNum","FightNum",isHiLight,50));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
			return columns;
		}
		
		public static function getColumnItem(isHiLight:Boolean):Array{
			var columns:Array = getColumnCommon();
			var isAsset:MyDataGridColumn=new MyDataGridColumn("isAsset","CancelRenew",false,80);
			isAsset.itemRenderer=new ClassFactory(CheckBoxIsAssetRenderer);
			isAsset.editable=false;
			columns.push(isAsset);
			columns.push(new MyDataGridColumn("items","items",isHiLight,150));
			columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
			columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
			columns.push(new MyDataGridColumn("IValue","IValue",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		public static function getLogColumnItem(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("items","items",isHiLight,150));
			columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
			columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
			columns.push(new MyDataGridColumn("IValue","IValue",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		public static function getColumnPackage(isHiLight:Boolean):Array{
			var columns:Array = getColumnCommon();
			columns.push(new MyDataGridColumn("items","items",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			columns.push(new MyDataGridColumn("MType","Type",isHiLight,100));
			columns.push(new MyDataGridColumn("MValue","Value",isHiLight,100));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",false,100));
			columns.push(new MyDataGridColumn("resourceChangeable","resourceChangeable",false,100));
			return columns;
		}
		public static function getColumnPresent(isHiLight:Boolean):Array{
			var columns:Array = getColumnCommon();
			columns.push(new MyDataGridColumn("items","items",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		public static function getLogColumnPresent(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("items","items",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		public static function getColumnOpen(isHiLight:Boolean):Array{
			var columns:Array= getColumnCommon();
			columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
			columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
			columns.push(new MyDataGridColumn("IValue","IValue",isHiLight,50));
			columns.push(new MyDataGridColumn("items","items",isHiLight,50));
			columns.push(new MyDataGridColumn("MType","Type",isHiLight,100));
			columns.push(new MyDataGridColumn("MValue","Value",isHiLight,100));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		public static function getLogColumnOpen(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
			columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
			columns.push(new MyDataGridColumn("IValue","IValue",isHiLight,50));
			columns.push(new MyDataGridColumn("items","items",isHiLight,50));
			columns.push(new MyDataGridColumn("MType","Type",isHiLight,100));
			columns.push(new MyDataGridColumn("MValue","Value",isHiLight,100));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		public static function getLogColumnPackage(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("items","items",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			columns.push(new MyDataGridColumn("MType","Type",isHiLight,100));
			columns.push(new MyDataGridColumn("MValue","Value",isHiLight,100));
			return columns;
		}
		
		public static function getLogColumnZYZDZ(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("items","items",isHiLight,150));
			columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
			columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
			columns.push(new MyDataGridColumn("IValue","IValue",isHiLight,50));
			columns.push(new MyDataGridColumn("descriptionCN","Description",isHiLight,50));
			return columns;
		}
		
	 	public static function getColumnArray(type:int,isHiLight:Boolean):Array{
	  		var array:Array=new Array();
		  	switch (type){
		  		case 0: array= getColumnWeapon(isHiLight);break;
		  		case 1: array= getColumnClothes(isHiLight);break;
		  		case 2: array= getColumnClothes(isHiLight);break;
		  		case 3: array= getColumnItem(isHiLight);break;
		  		case 4: array= getColumnPackage(isHiLight);break;
		  		case 6: array= getColumnPresent(isHiLight);break;//大礼包
		  		case 7:	array= getColumnOpen(isHiLight);break;
		  		case 8:	array= getColumnZYZDZ(isHiLight);break;//资源争夺战物品
		  	}
		  	return array;
	 	}
	 	
	 	//isHiLight:use to is add logredenerer to column
	 	public static function getLogColumnArray(type:int,isHiLight:Boolean):Array{
	  		var array:Array=new Array();
		  	switch (type){
		  		case 0: array= getLogColumnWeapon(isHiLight);break;
		  		case 1: array= getLogColumnClothes(isHiLight);break;
		  		case 2: array= getLogColumnClothes(isHiLight);break;
		  		case 3: array= getLogColumnItem(isHiLight);break;
		  		case 4: array= getLogColumnPackage(isHiLight);break;
		  		case 5: array= getLogColumnCommon(isHiLight);break;
		  		case 6: array= getLogColumnPresent(isHiLight);break;
		  		case 7: array= getLogColumnOpen(isHiLight);break;
		  		case 8:	array= getLogColumnZYZDZ(isHiLight);break;//资源争夺战物品
		  	}
		  	return array;
	 	}
		public static function getColumnParts(isHiLight:Boolean):Array{
			var columns:Array = getColumnCommon();
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("CId","cId",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("isStrengthen","IsStrengthen",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
			columns.push(new MyDataGridColumn("CResistanceFire","cResistanceFire",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBlast","cResistanceBlast",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBullet","cResistanceBullet",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceKnife","cResistanceKnife",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CBloodAdd","CBloodAdd",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
			return columns;
		}
		
				
		public static function getColumnWeapon(isHiLight:Boolean):Array{
			var columns:Array =getColumnCommon();
			var isAsset:MyDataGridColumn=new MyDataGridColumn("isAsset","CancelRenew",false,80);
			isAsset.itemRenderer=new ClassFactory(CheckBoxIsAssetRenderer);
			isAsset.editable=false;
			columns.push(isAsset);
//			columns.push(new MyDataGridColumn("modifiedDesc","modifiedDesc",isHiLight,100));
			columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("resourceChangeable","resourceChangeable",isHiLight,220));
			columns.push(new MyDataGridColumn("fightNum","FightNum",isHiLight,50));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
//			columns.push(new MyDataGridColumn("isStrengthen","是否可强化",isHiLight,80,Constants.style_color,Constants.clothes_color));
//			columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
//			columns.push(new MyDataGridColumn("WPenetration","wPenetration",isHiLight,100));
//			columns.push(new MyDataGridColumn("WDamage","wDamage",isHiLight,100));
//			columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier",isHiLight,100));
//			columns.push(new MyDataGridColumn("wFireTime","wFireTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wReloadTime","wReloadTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("WAmmoOneClip","wAmmoOneClip",isHiLight,100));
//			columns.push(new MyDataGridColumn("WAmmoCount","wAmmoCount",isHiLight,100));
//			columns.push(new MyDataGridColumn("WAutoFire","wAutoFire",isHiLight,100));
//			columns.push(new MyDataGridColumn("wTimeToIdle","wTimeToIdle",isHiLight,100));
//			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,Constants.style_color,Constants.w_accuracy_color));
//			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
//			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
//			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
//			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,100,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,100,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,Constants.style_color,Constants.w_normal_color));
//			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,Constants.style_color,Constants.w_move_color));
//			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
//			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
//			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
//			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
//			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
//			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
//			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,Constants.style_color,Constants.w_onair_color));
//			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
//			columns.push(new MyDataGridColumn("wUpModifier","wUpModifier",isHiLight,100));
//			columns.push(new MyDataGridColumn("wStabTime","wStabTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wStabLightTime","wStabLightTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wStabHurt","wStabHurt",isHiLight,100));
//			columns.push(new MyDataGridColumn("wStabLightHurt","wStabLightHurt",isHiLight,100));
//			columns.push(new MyDataGridColumn("wExplodeTime","wExplodeTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wReadyTime","wReadyTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wThrowOutTime","wThrowOutTime",isHiLight,100));
//			columns.push(new MyDataGridColumn("wHurtRange","wHurtRange",isHiLight,100));
//			columns.push(new MyDataGridColumn("wHurt","wHurt",isHiLight,50));
//			columns.push(new MyDataGridColumn("wRangeStart","wRangeStart",isHiLight,50));
//			columns.push(new MyDataGridColumn("wRangeEnd","wRangeEnd",isHiLight,50));
//			columns.push(new MyDataGridColumn("wAccuracyTime","wAccuracyTime",isHiLight,50));
//			columns.push(new MyDataGridColumn("wAccuracyTimeModefier","wAccuracyTimeModefier",isHiLight,50));
//			columns.push(new MyDataGridColumn("wMaxAccuracy","wMaxAccuracy",isHiLight,50));
//			columns.push(new MyDataGridColumn("wMinAccuracy","wMinAccuracy",isHiLight,50));
//			columns.push(new MyDataGridColumn("wCrossLengthBase","wCrossLengthBase",isHiLight,50));
//			columns.push(new MyDataGridColumn("wCrossLengthFactor","wCrossLengthFactor",isHiLight,50));
//			columns.push(new MyDataGridColumn("wCrossDistanceBase","wCrossDistanceBase",isHiLight,50));
//			columns.push(new MyDataGridColumn("wCrossDistanceFactor","wCrossDistanceFactor",isHiLight,50));
//			columns.push(new MyDataGridColumn("WShootBulletCount","wShootBulletCount",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSpread","wSpread",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFireMaxSpeed","wFireMaxSpeed",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFireStartSpeed","wFireStartSpeed",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFireAceleration","wFireAceleration",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFireResistance","wFireResistance",isHiLight,50));
//			columns.push(new MyDataGridColumn("wXOffset","WXOffset",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSightNormalOffset","WSightNormalOffset",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSightOnairOffset","WSightOnairOffset",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSightMoveOffset","WSightMoveOffset",isHiLight,50));
//			columns.push(new MyDataGridColumn("wStabDistance","WStabDistance",isHiLight,50));
//			columns.push(new MyDataGridColumn("wStabLightDistance","WStabLightDistance",isHiLight,50));
//			columns.push(new MyDataGridColumn("wStabWidth","WStabWidth",isHiLight,50));
//			columns.push(new MyDataGridColumn("wBackFactor","WBackFactor",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFlashRangeStart","WFlashRangeStart",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFlashRangeEnd","WFlashRangeEnd",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFlashBackFactor","wFlashBackFactor",isHiLight,50));
//			columns.push(new MyDataGridColumn("wTimeMax","WTimeMax",isHiLight,50));
//			columns.push(new MyDataGridColumn("wTimeFade","WTimeFade",isHiLight,50));
//			columns.push(new MyDataGridColumn("wTime","WTime",isHiLight,50));
//			
//			columns.push(new MyDataGridColumn("wHitSpeed","WHitSpeed",isHiLight,50));
//			columns.push(new MyDataGridColumn("wHitAcceleration","WHitAcceleration",isHiLight,50));
//			columns.push(new MyDataGridColumn("wHitDistance","WHitDistance",isHiLight,50));
//			columns.push(new MyDataGridColumn("WSightInfo","WSightInfo",isHiLight,50));
//			columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
//			columns.push(new MyDataGridColumn("wMaxDistance","WMaxDistance",isHiLight,50));
//			columns.push(new MyDataGridColumn("WAddBlood","wAddBlood",isHiLight,50));
//			columns.push(new MyDataGridColumn("WAmmoType","wAmmoType",isHiLight,50));
//			columns.push(new MyDataGridColumn("wFlySpeed","WFlySpeed",isHiLight,50));
//			columns.push(new MyDataGridColumn("wMaxaliveTime","WMaxaliveTime",isHiLight,50));
//			columns.push(new MyDataGridColumn("WGravity","wGravity",isHiLight,50));
//			columns.push(new MyDataGridColumn("wLastHurt","WLastHurt",isHiLight,50));
//			columns.push(new MyDataGridColumn("wLastTime","WLastTime",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSpecialDistance","WSpecialDistance",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSpecialRange","WSpecialRange",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSpecialLasttime","WSpecialLasttime",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSpecialHurt","WSpecialHurt",isHiLight,50));
//			columns.push(new MyDataGridColumn("wSpecialLasthurt","WSpecialLasthurt",isHiLight,50));
//			columns.push(new MyDataGridColumn("wParticlenum","WParticlenum",isHiLight,50));
//			columns.push(new MyDataGridColumn("wShowSpeed","WShowSpeed",isHiLight,50));
//			columns.push(new MyDataGridColumn("WAmmopartKey","wAmmopartKey",isHiLight,50));
//			columns.push(new MyDataGridColumn("WCritRate","WCritRate",isHiLight,50));
//			columns.push(new MyDataGridColumn("WCritAvailable","WCritAvailable",isHiLight,50));
//			columns.push(new MyDataGridColumn("WDmgModifyTimerMin","WDmgModifyTimerMin",isHiLight,50));
//			columns.push(new MyDataGridColumn("WDmgModifyTimerMax","WDmgModifyTimerMax",isHiLight,50));
//			columns.push(new MyDataGridColumn("WDmgModifyMin","WDmgModifyMin",isHiLight,50));
//			columns.push(new MyDataGridColumn("WDmgModifyMax","WDmgModifyMax",isHiLight,50));
//			columns.push(new MyDataGridColumn("WCapsuleHeight","WCapsuleHeight",isHiLight,50));
//			columns.push(new MyDataGridColumn("WCapsuleRadius","WCapsuleRadius",isHiLight,50));
//			columns.push(new MyDataGridColumn("WDamageModifer","WDamageModifer",isHiLight,50));
//		
//			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
//
//			//columns.push(new MyDataGridColumn("isStrengthen","是否可强化",isHiLight,50));
//			columns.push(new MyDataGridColumn("mType","素材类",isHiLight,50));
//			columns.push(new MyDataGridColumn("mValue","素材值",isHiLight,50));
			return columns;
		}  
        public static function getLogColumnParts(isHiLight:Boolean):Array{
			var columns:Array = getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("CId","cId",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceFire","cResistanceFire",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBlast","cResistanceBlast",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceBullet","cResistanceBullet",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CResistanceKnife","cResistanceKnife",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("CBloodAdd","CBloodAdd",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("isStrengthen","IsStrengthen",isHiLight,80,Constants.style_color,Constants.clothes_color));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
			return columns;
		}
		public static function getLogColumnWeapon(isHiLight:Boolean):Array{
			var columns:Array =getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("modifiedDesc","modifiedDesc",isHiLight,100));
			columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("resourceChangeable","resourceChangeable",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty1","gunProperty1",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty2","gunProperty2",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty3","gunProperty3",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty4","gunProperty4",isHiLight,100));
			columns.push(new MyDataGridColumn("gunProperty5","gunProperty5",isHiLight,100));
			columns.push(new MyDataGridColumn("fightNum","FightNum",isHiLight,50));
			columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime",isHiLight));
			columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
			columns.push(new MyDataGridColumn("WPenetration","wPenetration",isHiLight));
			columns.push(new MyDataGridColumn("WDamage","wDamage",isHiLight));
			columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier",isHiLight));
			columns.push(new MyDataGridColumn("wFireTime","wFireTime",isHiLight));
			columns.push(new MyDataGridColumn("wReloadTime","wReloadTime",isHiLight));
			columns.push(new MyDataGridColumn("WAmmoOneClip","wAmmoOneClip",isHiLight));
			columns.push(new MyDataGridColumn("WAmmoCount","wAmmoCount",isHiLight));
			columns.push(new MyDataGridColumn("WAutoFire","wAutoFire",isHiLight));
			columns.push(new MyDataGridColumn("wTimeToIdle","wTimeToIdle",isHiLight));
			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,Constants.style_color,Constants.w_accuracy_color));
			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,Constants.style_color,Constants.w_normal_color));
			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,Constants.style_color,Constants.w_move_color));
			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,Constants.style_color,Constants.w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,Constants.style_color,Constants.w_onair_color));
			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,Constants.style_color,Constants.w_crouch_color));
			columns.push(new MyDataGridColumn("wUpModifier","wUpModifier",isHiLight));
			columns.push(new MyDataGridColumn("wStabTime","wStabTime",isHiLight));
			columns.push(new MyDataGridColumn("wStabLightTime","wStabLightTime",isHiLight));
			columns.push(new MyDataGridColumn("wStabHurt","wStabHurt",isHiLight));
			columns.push(new MyDataGridColumn("wStabLightHurt","wStabLightHurt",isHiLight));
			columns.push(new MyDataGridColumn("wExplodeTime","wExplodeTime",isHiLight));
			columns.push(new MyDataGridColumn("wReadyTime","wReadyTime",isHiLight));
			columns.push(new MyDataGridColumn("wThrowOutTime","wThrowOutTime",isHiLight));
			columns.push(new MyDataGridColumn("wHurtRange","wHurtRange",isHiLight));
			columns.push(new MyDataGridColumn("wHurt","wHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wRangeStart","wRangeStart",isHiLight,50));
			columns.push(new MyDataGridColumn("wRangeEnd","wRangeEnd",isHiLight,50));
			columns.push(new MyDataGridColumn("wAccuracyTime","wAccuracyTime",isHiLight,50));
			columns.push(new MyDataGridColumn("wAccuracyTimeModefier","wAccuracyTimeModefier",isHiLight,50));
			columns.push(new MyDataGridColumn("wMaxAccuracy","wMaxAccuracy",isHiLight,50));
			columns.push(new MyDataGridColumn("wMinAccuracy","wMinAccuracy",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossLengthBase","wCrossLengthBase",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossLengthFactor","wCrossLengthFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossDistanceBase","wCrossDistanceBase",isHiLight,50));
			columns.push(new MyDataGridColumn("wCrossDistanceFactor","wCrossDistanceFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("WShootBulletCount","wShootBulletCount",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpread","wSpread",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireMaxSpeed","wFireMaxSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireStartSpeed","wFireStartSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireAceleration","wFireAceleration",isHiLight,50));
			columns.push(new MyDataGridColumn("wFireResistance","wFireResistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wXOffset","WXOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wSightNormalOffset","WSightNormalOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wSightOnairOffset","WSightOnairOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wSightMoveOffset","WSightMoveOffset",isHiLight,50));
			columns.push(new MyDataGridColumn("wStabDistance","WStabDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wStabLightDistance","WStabLightDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wStabWidth","WStabWidth",isHiLight,50));
			columns.push(new MyDataGridColumn("wBackFactor","WBackFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlashRangeStart","WFlashRangeStart",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlashRangeEnd","WFlashRangeEnd",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlashBackFactor","wFlashBackFactor",isHiLight,50));
			columns.push(new MyDataGridColumn("wTimeMax","WTimeMax",isHiLight,50));
			columns.push(new MyDataGridColumn("wTimeFade","WTimeFade",isHiLight,50));
			columns.push(new MyDataGridColumn("wTime","WTime",isHiLight,50));
			
			columns.push(new MyDataGridColumn("wHitSpeed","WHitSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wHitAcceleration","WHitAcceleration",isHiLight,50));
			columns.push(new MyDataGridColumn("wHitDistance","WHitDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("WSightInfo","WSightInfo",isHiLight,50));
			columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
			columns.push(new MyDataGridColumn("wMaxDistance","WMaxDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("WAddBlood","wAddBlood",isHiLight,50));
			columns.push(new MyDataGridColumn("WAmmoType","wAmmoType",isHiLight,50));
			columns.push(new MyDataGridColumn("wFlySpeed","WFlySpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("wMaxaliveTime","WMaxaliveTime",isHiLight,50));
			columns.push(new MyDataGridColumn("WGravity","wGravity",isHiLight,50));
			columns.push(new MyDataGridColumn("wLastHurt","WLastHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wLastTime","WLastTime",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialDistance","WSpecialDistance",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialRange","WSpecialRange",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialLasttime","WSpecialLasttime",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialHurt","WSpecialHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wSpecialLasthurt","WSpecialLasthurt",isHiLight,50));
			columns.push(new MyDataGridColumn("wParticlenum","WParticlenum",isHiLight,50));
			columns.push(new MyDataGridColumn("wShowSpeed","WShowSpeed",isHiLight,50));
			columns.push(new MyDataGridColumn("WAmmopartKey","wAmmopartKey",isHiLight,50));
			columns.push(new MyDataGridColumn("WCritRate","WCritRate",isHiLight,50));
			columns.push(new MyDataGridColumn("WCritAvailable","WCritAvailable",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyTimerMin","WDmgModifyTimerMin",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyTimerMax","WDmgModifyTimerMax",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyMin","WDmgModifyMin",isHiLight,50));
			columns.push(new MyDataGridColumn("WDmgModifyMax","WDmgModifyMax",isHiLight,50));
			columns.push(new MyDataGridColumn("WCapsuleHeight","WCapsuleHeight",isHiLight,50));
			columns.push(new MyDataGridColumn("WCapsuleRadius","WCapsuleRadius",isHiLight,50));
			columns.push(new MyDataGridColumn("WDamageModifer","WDamageModifer",isHiLight,50));
		
			columns.push(new MyDataGridColumn("characterId","characterId",isHiLight,50));
			return columns;
		} 	
		public static function getBlackIPColumns(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,100);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("ip","IP",hiLight,100));
				columns.push(new MyDataGridColumn("isBanned","IsBanned(“Y”Yes，“N”No)",hiLight,200));
				//var isBanned:MyDataGridColumn=new MyDataGridColumn("isBanned","IsBanned",false,50);
				//isBanned.itemRenderer=new ClassFactory(CheckBoxRenderer3);
				//isBanned.editable=false;
				//columns.push(isBanned);
				columns.push(new MyDataGridColumn("bannedTime","封禁时间/BannedTime(min)",hiLight,150));
				columns.push(new MyDataGridColumn("description","Description",hiLight,400));
				var createTime:MyDataGridColumn=new MyDataGridColumn("createTimeStr","CreateTime",hiLight,200);
				createTime.editable=false;
				columns.push(createTime);
				return columns;
		}
		public static function getBlackWhiteListColumns(hiLight:Boolean=false):Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn = new MyDataGridColumn("id","id",hiLight,50);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("userId","UserId",hiLight,200));
			columns.push(new MyDataGridColumn("playerId","PlayerId",hiLight,200));
			columns.push(new MyDataGridColumn("playerName","PlayerName",hiLight,300));
			columns.push(new MyDataGridColumn("isBlack","IsBlack",hiLight,20));
			return columns;
		}	
	}
}