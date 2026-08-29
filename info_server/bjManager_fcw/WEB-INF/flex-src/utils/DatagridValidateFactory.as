package utils
{
	/*
	 * datagrid validator factory  for sysitem 
	 * create validator by dataField and need paramater source
	 */
	
	import mx.validators.NumberValidator;
	import mx.validators.StringValidator;
	import mx.validators.Validator;
	import mx.controls.Alert;

	public class DatagridValidateFactory
	{
		public static function getServerValidater(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="max"||dataField=="min"||dataField=="maxonline"||dataField=="serverid"){
				numValidte.source=source;
				returnValue=numValidte;
			}else if(dataField=="name"){
				stringValidte.source=source;
				returnValue=stringValidte;
			}else{
				numValidte.source=source;
				returnValue=numValidte;
			}
			return returnValue;
		}
		public static function getCharacterValidater(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="runspeed"||dataField=="walkspeed"||dataField=="accelspeed"||dataField=="crouchspeed"
			||dataField=="jumpspeed"||dataField=="throwspeed"||dataField=="cost"){
				numValidte.source=source;
				returnValue=numValidte;
			}else if(dataField=="resourcep"||dataField=="resourcet"){
				stringValidte.source=source;
				returnValue=stringValidte;
			}else if(dataField=="isdefault"){
				stringValidte.source=source;
				stringValidte.maxLength=1;
				returnValue=stringValidte;
			}
			else{
				stringValidte.source=source;
				returnValue=stringValidte;
			}
			return returnValue;
		}
		
		public static function getCharacterValidateArray(characterView:Object):Array{
			var validators:Array = new Array();
				validators.push(getCharacterValidater("runSpeed",characterView.runSpeed));
		 		validators.push(getCharacterValidater("walkSpeed",characterView.walkSpeed));
		 		validators.push(getCharacterValidater("crouchSpeed",characterView.crouchSpeed));
		 		validators.push(getCharacterValidater("accelSpeed",characterView.crouchSpeed));
		 		validators.push(getCharacterValidater("jumpSpeed",characterView.jumpSpeed));
		 		validators.push(getCharacterValidater("throwSpeed",characterView.throwSpeed));
//		 		validators.push(getCharacterValidater("resourceP",characterView.resourceP));
//		 		validators.push(getCharacterValidater("resourceT",characterView.resourceT));
		 		validators.push(getCharacterValidater("isDefault",characterView.isDefault));
		 		validators.push(getCharacterValidater("cost",characterView.cost));
//		 		validators.push(getCharacterValidater("gender",characterView.gender));
			return validators;
		}
		public static function getBioCharacterValidater(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			if(dataField=="runSpeed"||dataField=="walkSpeed"||dataField=="accelSpeed"||dataField=="crouchSpeed"||dataField=="jumpSpeed"||dataField=="throwSpeed"
				||dataField=="cost"||dataField=="cid"||dataField=="sid"||dataField=="type"
				||dataField=="maxHp"||dataField=="exHp"||dataField=="cost"||dataField=="defaultLevel"||dataField=="isFired"||dataField=="isEnable"
				||dataField=="controllerHeight"||dataField=="controllerRadius"||dataField=="controllerCrouchHeight"||dataField=="scoreOffset"){
				numValidte.source=source;
				returnValue=numValidte;
			}else if(dataField=="resourcep"||dataField=="resourcet"){
				stringValidte.source=source;
				returnValue=stringValidte;
			}else if(dataField=="isdefault"){
				stringValidte.source=source;
				stringValidte.maxLength=1;
				returnValue=stringValidte;
			}else{
				stringValidte.source=source;
				returnValue=stringValidte;
			}
			return returnValue;
		}
		public static function getBioCharacterValidateArray(characterView:Object):Array{
			var validators:Array = new Array();
				validators.push(getBioCharacterValidater("cid",characterView.cid));
				validators.push(getBioCharacterValidater("sid",characterView.sid));
				validators.push(getBioCharacterValidater("type",characterView.type));
				validators.push(getBioCharacterValidater("weapons",characterView.weapons));
				validators.push(getBioCharacterValidater("costumes",characterView.costumes));
				validators.push(getBioCharacterValidater("name",characterView.sname));
				
				validators.push(getBioCharacterValidater("runSpeed",characterView.runSpeed));
		 		validators.push(getBioCharacterValidater("walkSpeed",characterView.walkSpeed));
		 		validators.push(getBioCharacterValidater("crouchSpeed",characterView.crouchSpeed));
		 		validators.push(getBioCharacterValidater("accelSpeed",characterView.crouchSpeed));
		 		validators.push(getBioCharacterValidater("jumpSpeed",characterView.jumpSpeed));
		 		validators.push(getBioCharacterValidater("throwSpeed",characterView.throwSpeed));
		 		
		 		validators.push(getBioCharacterValidater("costumeResource",characterView.costumeResource));
		 		validators.push(getBioCharacterValidater("isDefault",characterView.isDefault));
		 		
		 		validators.push(getBioCharacterValidater("maxHp",characterView.maxHp));
		 		validators.push(getBioCharacterValidater("exHp",characterView.exHp));
		 		validators.push(getBioCharacterValidater("cost",characterView.cost));
		 		validators.push(getBioCharacterValidater("defaultLevel",characterView.defaultLevel));
		 		validators.push(getBioCharacterValidater("isFired",characterView.isFired));
		 		validators.push(getBioCharacterValidater("resourceName",characterView.resourceName));
		 		validators.push(getBioCharacterValidater("isEnable",characterView.isEnable));
		 		
		 		validators.push(getBioCharacterValidater("controllerHeight",characterView.controllerHeight));
		 		validators.push(getBioCharacterValidater("controllerRadius",characterView.controllerRadius));
		 		validators.push(getBioCharacterValidater("controllerCrouchHeight",characterView.controllerCrouchHeight));
		 		validators.push(getBioCharacterValidater("scoreOffset",characterView.scoreOffset));
		 						

			return validators;
		}
		public static function getPlayerValidater(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="gpoint"){
				numValidte.source=source;
				returnValue=numValidte;
			}else if(dataField=="exp"){
				numValidte.source=source;
				numValidte.minValue=0;
				returnValue=numValidte;
			}
			else if(dataField=="rank"){
				numValidte.source=source;
				numValidte.minValue=1;
				numValidte.maxValue=50;
				returnValue=numValidte;
			}
			 else{
				numValidte.source=source;
				returnValue=numValidte;
			}
			return returnValue;
		}
		
		//create validate fro sysitem table edit
		public static function  getSysitemValidator(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="name"||dataField=="displayname"||dataField=="wammopartkey"||dataField=="items"){
				stringValidte.source=source;
				stringValidte.maxLength=40;
				returnValue=stringValidte;
			}
			else if(dataField=="description"){
				stringValidte.source=source;
				stringValidte.maxLength=200;
				returnValue=stringValidte;
			}
			else if(dataField=="modifieddesc"){
				stringValidte.source=source;
				stringValidte.maxLength=7;
				stringValidte.minLength=7;
				returnValue=stringValidte;
			}
			else if(dataField=="resourcestable"||dataField=="resourcechangeable"||dataField=="psuitable"||dataField=="wsightinfo"
			||dataField=="ivalue"||dataField=="characterid"||dataField=="wammopartkey"){
				stringValidte.source=source;
//				stringValidte.maxLength=100;
				returnValue=stringValidte;
			}
			else if(dataField=="cgender"){
				stringValidte.source=source;
				stringValidte.maxLength=1;
				returnValue=stringValidte;
			}else if(dataField=="type"||dataField=="subtype"||dataField=="level"||dataField=="wautofire"||dataField=="cside"){
				numValidte.source=source;
				numValidte.maxValue=9999;			
				returnValue=numValidte;
			}else if(dataField=="cost1"||dataField=="cost2"||dataField=="cost3"||dataField=="unit1"||dataField=="unit2"||dataField=="unit3"
					||dataField=="wid"||dataField=="wchangeintime"||dataField=="wmovespeedoffset"||dataField=="wcrossoffset"
					||dataField=="waccuracydivisor"||dataField=="waccuracyoffset"||dataField=="wmaxinaccuracy"||dataField=="wpenetration"
					||dataField=="wdamage"||dataField=="wrangemodifier"||dataField=="wfiretime"||dataField=="wreloadtime"
					||dataField=="wzoomtime"||dataField=="wzoomvalue"||dataField=="wammooneclip"||dataField=="wammocount"
					||dataField=="wtimetoidle"||dataField=="wnormaloffset"||dataField=="wnormalfactor"||dataField=="wonairoffset"
					||dataField=="wonairfactor"||dataField=="wmoveoffset"||dataField=="wmovefactor"||dataField=="wnormalupbase"
					||dataField=="wnormallateralbase"||dataField=="wnormalupmodifier"||dataField=="wnormalupmax"||dataField=="wnormallateralmax"
					||dataField=="wnormaldirchange"||dataField=="wmoveupbase"||dataField=="wmovelateralbase"||dataField=="wmoveupmodifier"
					||dataField=="wmovelateralmodifier"||dataField=="wmoveupmax"||dataField=="wmovelateralmax"||dataField=="wmovedirchange"
					||dataField=="wonairupbase"||dataField=="wonairlateralbase"||dataField=="wonairupmodifier"||dataField=="wonairlateralmodifier"
					||dataField=="wonairupmax"||dataField=="wonairlateralmax"||dataField=="wonairdirchange"||dataField=="wcrouchupbase"
					||dataField=="wcrouchlateralbase"||dataField=="wcrouchupmodifier"||dataField=="wcrouchlateralmodifier"||dataField=="wcrouchupmax"
					||dataField=="wcrouchlateralmax"||dataField=="wcrouchdirchange"||dataField=="wupmodifier"||dataField=="wstabtime"
					||dataField=="wstablighttime"||dataField=="wstabhurt"||dataField=="wstablighthurt"||dataField=="wexplodetime"
					||dataField=="wreadytime"||dataField=="wthrowouttime"||dataField=="whurtrange"||dataField=="whurt"||dataField=="unittype"
					||dataField=="wrangestart"||dataField=="wrangeend"||dataField=="waccuracytime"||dataField=="waccuracytimemodefier"||dataField=="wmaxaccuracy"||dataField=="wminaccuracy"
					||dataField=="wcrosslengthbase"||dataField=="wcrosslengthfactor"||dataField=="wcrossdistancebase"||dataField=="wcrossdistancefactor"
					||dataField=="fightnum"||dataField=="rarelevel"
					){
				numValidte.source=source;			
				returnValue=numValidte;
			}else if(dataField=="gunproperty1" || dataField=="gunproperty2" || dataField=="gunproperty3" || dataField=="gunproperty4" || 
				dataField=="gunproperty5" || dataField=="gunproperty6" || dataField=="gunproperty7"){
				stringValidte.source=source;
				stringValidte.maxLength=45;
				stringValidte.required=false;
				returnValue=stringValidte;
			}else{
				numValidte.source=source;			
				returnValue=numValidte;
			}
			
			return returnValue;
		}
		//for create sysitem 
		public static function getSysitemValidatorArray(type:int,sysItemView:Object=null):Array{
				var validators:Array = new Array();
		 		
		 		validators.push(getSysitemValidator("name",sysItemView.sName));
//		 		validators.push(getSysitemValidator("need",sysItemView.need));
		 		
		 		validators.push(getSysitemValidator("displayName",sysItemView.displayName));
		 		validators.push(getSysitemValidator("level",sysItemView.level));
		 		if(type==1){
		 			validators.push(getSysitemValidator("modifiedDesc",sysItemView.modifiedDesc));
		 		}
		 		if(type==1||type==3){
		 			validators.push(getSysitemValidator("wId",sysItemView.wId));
		 			validators.push(getSysitemValidator("wChangeInTime",sysItemView.wChangeInTime));
		 			validators.push(getSysitemValidator("wMoveSpeedOffset",sysItemView.wMoveSpeedOffset));
		 			validators.push(getSysitemValidator("wCrossOffset",sysItemView.wMoveSpeedOffset));
		 			validators.push(getSysitemValidator("wAccuracyDivisor",sysItemView.wAccuracyDivisor));
		 			validators.push(getSysitemValidator("wAccuracyOffset",sysItemView.wAccuracyOffset));
		 			validators.push(getSysitemValidator("wMaxInaccuracy",sysItemView.wMaxInaccuracy));
		 			validators.push(getSysitemValidator("wPenetration",sysItemView.wPenetration));
		 			validators.push(getSysitemValidator("wDamage",sysItemView.wDamage));
		 			validators.push(getSysitemValidator("wRangeModifier",sysItemView.wRangeModifier));
		 			validators.push(getSysitemValidator("wFireTime",sysItemView.wFireTime));
		 			validators.push(getSysitemValidator("wReloadTime",sysItemView.wReloadTime));
		 			validators.push(getSysitemValidator("wAmmoOneClip",sysItemView.wAmmoOneClip));
		 			validators.push(getSysitemValidator("wAmmoCount",sysItemView.wAmmoCount));
		 			validators.push(getSysitemValidator("wAutoFire",sysItemView.wAutoFire));
		 			validators.push(getSysitemValidator("wNormalOffset",sysItemView.wNormalOffset));
		 			validators.push(getSysitemValidator("wNormalFactor",sysItemView.wNormalFactor));
		 			validators.push(getSysitemValidator("wOnairOffset",sysItemView.wOnairOffset));
		 			validators.push(getSysitemValidator("wOnairFactor",sysItemView.wOnairFactor));
		 			validators.push(getSysitemValidator("wMoveOffset",sysItemView.wMoveOffset));
		 			validators.push(getSysitemValidator("wMoveFactor",sysItemView.wMoveFactor));
		 			validators.push(getSysitemValidator("wNormalUpBase",sysItemView.wNormalUpBase));
		 			validators.push(getSysitemValidator("wNormalLateralBase",sysItemView.wNormalLateralBase));
		 			validators.push(getSysitemValidator("wNormalUpModifier",sysItemView.wNormalUpModifier));
		 			validators.push(getSysitemValidator("wNormalUpMax",sysItemView.wNormalUpMax));
		 			validators.push(getSysitemValidator("wNormalLateralMax",sysItemView.wNormalLateralMax));
		 			validators.push(getSysitemValidator("wNormalDirChange",sysItemView.wNormalDirChange));
		 			validators.push(getSysitemValidator("wMoveUpBase",sysItemView.wMoveUpBase));
		 			validators.push(getSysitemValidator("wMoveLateralBase",sysItemView.wMoveLateralBase));
		 			validators.push(getSysitemValidator("wMoveUpModifier",sysItemView.wMoveUpModifier));
		 			validators.push(getSysitemValidator("wMoveLateralModifier",sysItemView.wMoveLateralModifier));
		 			validators.push(getSysitemValidator("wMoveUpMax",sysItemView.wMoveUpMax));
		 			validators.push(getSysitemValidator("wMoveLateralMax",sysItemView.wMoveLateralMax));
		 			validators.push(getSysitemValidator("wMoveDirChange",sysItemView.wMoveDirChange));
		 			validators.push(getSysitemValidator("wOnairUpBase",sysItemView.wOnairUpBase));
		 			validators.push(getSysitemValidator("wOnairLateralBase",sysItemView.wOnairLateralBase));
		 			validators.push(getSysitemValidator("wOnairUpModifier",sysItemView.wOnairUpModifier));
		 			validators.push(getSysitemValidator("wOnairLateralModifier",sysItemView.wOnairLateralModifier));
		 			validators.push(getSysitemValidator("wOnairUpMax",sysItemView.wOnairUpMax));
		 			validators.push(getSysitemValidator("wOnairLateralMax",sysItemView.wOnairLateralMax));
		 			validators.push(getSysitemValidator("wOnairDirChange",sysItemView.wOnairDirChange));
		 			validators.push(getSysitemValidator("wCrouchUpBase",sysItemView.wCrouchUpBase));
		 			validators.push(getSysitemValidator("wCrouchLateralBase",sysItemView.wCrouchLateralBase));
		 			validators.push(getSysitemValidator("wCrouchUpModifier",sysItemView.wCrouchUpModifier));
		 			validators.push(getSysitemValidator("wCrouchLateralModifier",sysItemView.wCrouchLateralModifier));
		 			validators.push(getSysitemValidator("wCrouchUpMax",sysItemView.wCrouchUpMax));
		 			validators.push(getSysitemValidator("wCrouchLateralMax",sysItemView.wCrouchLateralMax));
		 			validators.push(getSysitemValidator("wCrouchDirChange",sysItemView.wCrouchDirChange));
		 			validators.push(getSysitemValidator("WSightInfo",sysItemView.WSightInfo));
		 			
		 		}
		 		return validators;
		}
		public static function getBlackWhiteListValidater(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="playerId"){
				numValidte.source=source;
				returnValue=numValidte;
			}else if(dataField=="userId"||dataField=="playerName"){
				stringValidte.source=source;
				returnValue=stringValidte;
			}else if(dataField=="isBlack"){
				stringValidte.source=source;
				stringValidte.maxLength=1;
				//TODO:
				returnValue=stringValidte;
			}
			else{
				stringValidte.source=source;
				returnValue=stringValidte;
			}
			return returnValue;
		}
	}
}