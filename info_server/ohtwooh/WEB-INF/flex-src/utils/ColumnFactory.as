package utils
{
	import common.Constants;
	
	import component.MyDataGridColumn;
	
	import mx.core.ClassFactory;
	
	import renderer.DateTimeRenderer;
	import renderer.LogSelectRenderer;
	
	public class ColumnFactory
	{
		
		public static function getLevelColumns(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","id",hiLight,30);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("type","type",hiLight,30));
				columns.push(new MyDataGridColumn("name","name",hiLight,50));
				columns.push(new MyDataGridColumn("startPoints","startPoints",hiLight,250));
				columns.push(new MyDataGridColumn("blastPoints","blastPoints",hiLight,250));
				columns.push(new MyDataGridColumn("flagPoints","flagPoints",hiLight,250));
				columns.push(new MyDataGridColumn("weaponPoints","weaponPoints",hiLight,250));
				columns.push(new MyDataGridColumn("supplyPoints","supplyPoints",hiLight,250));
				columns.push(new MyDataGridColumn("supplies","supplies",hiLight,250));
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
				return columns;
			}
			public static function getSupplies(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				
				columns.push(new MyDataGridColumn("type","type",hiLight,50));
				columns.push(new MyDataGridColumn("name","name",hiLight,50));
				columns.push(new MyDataGridColumn("teamId","teamId",hiLight,50));
				columns.push(new MyDataGridColumn("value","value",hiLight,50));
				columns.push(new MyDataGridColumn("random","random",hiLight,50));
				columns.push(new MyDataGridColumn("skillTime","skillTime",hiLight,50));
				return columns;
			}
			public static function getBlastPoint(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				
				columns.push(new MyDataGridColumn("x","x",hiLight,50));
				columns.push(new MyDataGridColumn("y","y",hiLight,50));
				columns.push(new MyDataGridColumn("z","z",hiLight,50));
				return columns;
			}
			public static function getSysItemName(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				
				var id:MyDataGridColumn=new MyDataGridColumn("id","id",hiLight,30);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("displayName","displayName",hiLight,50));
				columns.push(new MyDataGridColumn("name","name",hiLight,50));
				return columns;
			}
			public static function getWeaponColumns(isHiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","id",isHiLight,30);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("displayName","DisplayName",isHiLight,100));
				columns.push(new MyDataGridColumn("name","Name",isHiLight,100));
				columns.push(new MyDataGridColumn("sysItemId","sysItemId",isHiLight,100));
				columns.push(new MyDataGridColumn("sysLevelId","sysLevelId",isHiLight,100));
				columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
				columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
				columns.push(new MyDataGridColumn("resourceChangeable","resourceChangeable",isHiLight,220));
				columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime",isHiLight,100));
				columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
				columns.push(new MyDataGridColumn("WPenetration","wPenetration",isHiLight,100));
				columns.push(new MyDataGridColumn("WDamage","wDamage",isHiLight,100));
				columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier",isHiLight,100));
				columns.push(new MyDataGridColumn("wFireTime","wFireTime",isHiLight,100));
				columns.push(new MyDataGridColumn("wReloadTime","wReloadTime",isHiLight,100));
				columns.push(new MyDataGridColumn("wZoomTime","wZoomTime",isHiLight,100));
				columns.push(new MyDataGridColumn("wZoomValue","wZoomValue",isHiLight,100));
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
				return columns;
			}
		public static function getCharacterColumns(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","CharacterID",hiLight,100);
				id.editable=false;
				columns.push(id);
				columns.push(new MyDataGridColumn("runSpeed","runSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("walkSpeed","walkSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("crouchSpeed","crouchSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("accelSpeed","AccelSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("jumpSpeed","jumpSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("throwSpeed","throwSpeed",hiLight,100));
				columns.push(new MyDataGridColumn("resourceP","resourceP",hiLight,300));
				columns.push(new MyDataGridColumn("resourceT","resourceT",hiLight,300));
				columns.push(new MyDataGridColumn("isDefault","isDefault",hiLight,50));
				columns.push(new MyDataGridColumn("cost","cost",hiLight,50));
				columns.push(new MyDataGridColumn("gender","gender",hiLight,50));
				return columns;
			}
			public static function getPlayerColumns(hiLight:Boolean=false):Array{
				var columns:Array = new Array();
				var id:MyDataGridColumn=new MyDataGridColumn("id","ID",hiLight,100);
				id.editable=false;
				columns.push(id);
				var userId:MyDataGridColumn=new MyDataGridColumn("userId","用户ID",hiLight,100);
				userId.editable=false;
				columns.push(userId);
				var name:MyDataGridColumn=new MyDataGridColumn("name","角色名",hiLight,150);
				name.editable=false;
				columns.push(name);
				columns.push(new MyDataGridColumn("exp","经验",hiLight,200));
				columns.push(new MyDataGridColumn("rank","等级",hiLight,100));
				columns.push(new MyDataGridColumn("GPoint","GP",hiLight,200));
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

	}
}