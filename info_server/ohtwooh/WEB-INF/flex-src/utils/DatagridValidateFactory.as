package utils
{
	/*
	 * datagrid validator factory  for sysitem 
	 * create validator by dataField and need paramater source
	 */
	
	import mx.validators.NumberValidator;
	import mx.validators.StringValidator;
	import mx.validators.Validator;
	
	public class DatagridValidateFactory
	{
		public static function getCharacterValidater(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="runspeed"||dataField=="walkspeed"||dataField=="accelspeed"||dataField=="crouchspeed"||dataField=="jumpspeed"||dataField=="throwspeed"){
				numValidte.source=source;
				returnValue=numValidte;
			}else if(dataField=="resourcep"||dataField=="resourcet"){
				stringValidte.source=source;
				returnValue=stringValidte;
			}else{
				numValidte.source=source;
				returnValue=numValidte;
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
		 		validators.push(getCharacterValidater("resourceP",characterView.resourceP));
		 		validators.push(getCharacterValidater("resourceT",characterView.resourceT));
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
		public static function  getValidator(dataField:String,source:Object):Validator{
			var returnValue:Validator=null;
			var stringValidte:StringValidator=new StringValidator();
			stringValidte.property="text";
		  	var numValidte:NumberValidator=new NumberValidator();
			numValidte.property="text";
			dataField=dataField.toLowerCase();
			if(dataField=="name"||dataField=="displayname"||dataField=="description"){
				stringValidte.source=source;
				stringValidte.maxLength=40;
				returnValue=stringValidte;
			}
//			else if(dataField=="need"){
//				stringValidte.source=source;
//				stringValidte.maxLength=5;
//				returnValue=stringValidte;
//			}
			else if(dataField=="modifieddesc"){
				stringValidte.source=source;
				stringValidte.maxLength=12;
				stringValidte.minLength=12;
				returnValue=stringValidte;
			}
			else if(dataField=="resourcestable"||dataField=="resourcechangeable"){
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
					||dataField=="wreadytime"||dataField=="wthrowouttime"||dataField=="whurtrange"||dataField=="whurt"||dataField=="currency"||dataField=="unittype"
					||dataField=="wrangestart"||dataField=="wrangeend"||dataField=="waccuracytime"||dataField=="waccuracytimemodefier"||dataField=="wmaxaccuracy"||dataField=="wminaccuracy"
					||dataField=="wcrosslengthbase"||dataField=="wcrosslengthfactor"||dataField=="wcrossdistancebase"||dataField=="wcrossdistancefactor"
					){
				numValidte.source=source;			
				returnValue=numValidte;
			}else{
				numValidte.source=source;			
				returnValue=numValidte;
			}
			
			return returnValue;
		}
		//for create sysitem 
		public static function getValidatorArray(type:int,sysItemView:Object=null):Array{
			var validators:Array = new Array();
		 		
		 		validators.push(getValidator("name",sysItemView.sName));
//		 		validators.push(getValidator("need",sysItemView.need));
				validators.push(getValidator("currency",sysItemView.currency));
				validators.push(getValidator("unittype",sysItemView.unitType));
		 		validators.push(getValidator("cost1",sysItemView.cost1));
		 		validators.push(getValidator("cost2",sysItemView.cost2));
		 		validators.push(getValidator("cost3",sysItemView.cost3));
		 		validators.push(getValidator("unit1",sysItemView.unit1));
		 		validators.push(getValidator("unit2",sysItemView.unit2));
		 		validators.push(getValidator("unit3",sysItemView.unit3));
		 		validators.push(getValidator("displayName",sysItemView.displayName));
		 		validators.push(getValidator("level",sysItemView.level));
		 		
		 		if(type==1){
		 			validators.push(getValidator("modifiedDesc",sysItemView.modifiedDesc));
		 		}
		 		if(type==1||type==3){
		 			validators.push(getValidator("wId",sysItemView.wId));
		 			validators.push(getValidator("wChangeInTime",sysItemView.wChangeInTime));
		 			validators.push(getValidator("wMoveSpeedOffset",sysItemView.wMoveSpeedOffset));
		 			validators.push(getValidator("wCrossOffset",sysItemView.wMoveSpeedOffset));
		 			validators.push(getValidator("wAccuracyDivisor",sysItemView.wAccuracyDivisor));
		 			validators.push(getValidator("wAccuracyOffset",sysItemView.wAccuracyOffset));
		 			validators.push(getValidator("wMaxInaccuracy",sysItemView.wMaxInaccuracy));
		 			validators.push(getValidator("wPenetration",sysItemView.wPenetration));
		 			validators.push(getValidator("wDamage",sysItemView.wDamage));
		 			validators.push(getValidator("wRangeModifier",sysItemView.wRangeModifier));
		 			validators.push(getValidator("wFireTime",sysItemView.wFireTime));
		 			validators.push(getValidator("wReloadTime",sysItemView.wReloadTime));
		 			validators.push(getValidator("wZoomTime",sysItemView.wZoomTime));
		 			validators.push(getValidator("wZoomValue",sysItemView.wZoomValue));
		 			validators.push(getValidator("wAmmoOneClip",sysItemView.wAmmoOneClip));
		 			validators.push(getValidator("wAmmoCount",sysItemView.wAmmoCount));
		 			validators.push(getValidator("wAutoFire",sysItemView.wAutoFire));
		 			validators.push(getValidator("wNormalOffset",sysItemView.wNormalOffset));
		 			validators.push(getValidator("wNormalFactor",sysItemView.wNormalFactor));
		 			validators.push(getValidator("wOnairOffset",sysItemView.wOnairOffset));
		 			validators.push(getValidator("wOnairFactor",sysItemView.wOnairFactor));
		 			validators.push(getValidator("wMoveOffset",sysItemView.wMoveOffset));
		 			validators.push(getValidator("wMoveFactor",sysItemView.wMoveFactor));
		 			validators.push(getValidator("wNormalUpBase",sysItemView.wNormalUpBase));
		 			validators.push(getValidator("wNormalLateralBase",sysItemView.wNormalLateralBase));
		 			validators.push(getValidator("wNormalUpModifier",sysItemView.wNormalUpModifier));
		 			validators.push(getValidator("wNormalUpMax",sysItemView.wNormalUpMax));
		 			validators.push(getValidator("wNormalLateralMax",sysItemView.wNormalLateralMax));
		 			validators.push(getValidator("wNormalDirChange",sysItemView.wNormalDirChange));
		 			validators.push(getValidator("wMoveUpBase",sysItemView.wMoveUpBase));
		 			validators.push(getValidator("wMoveLateralBase",sysItemView.wMoveLateralBase));
		 			validators.push(getValidator("wMoveUpModifier",sysItemView.wMoveUpModifier));
		 			validators.push(getValidator("wMoveLateralModifier",sysItemView.wMoveLateralModifier));
		 			validators.push(getValidator("wMoveUpMax",sysItemView.wMoveUpMax));
		 			validators.push(getValidator("wMoveLateralMax",sysItemView.wMoveLateralMax));
		 			validators.push(getValidator("wMoveDirChange",sysItemView.wMoveDirChange));
		 			validators.push(getValidator("wOnairUpBase",sysItemView.wOnairUpBase));
		 			validators.push(getValidator("wOnairLateralBase",sysItemView.wOnairLateralBase));
		 			validators.push(getValidator("wOnairUpModifier",sysItemView.wOnairUpModifier));
		 			validators.push(getValidator("wOnairLateralModifier",sysItemView.wOnairLateralModifier));
		 			validators.push(getValidator("wOnairUpMax",sysItemView.wOnairUpMax));
		 			validators.push(getValidator("wOnairLateralMax",sysItemView.wOnairLateralMax));
		 			validators.push(getValidator("wOnairDirChange",sysItemView.wOnairDirChange));
		 			validators.push(getValidator("wCrouchUpBase",sysItemView.wCrouchUpBase));
		 			validators.push(getValidator("wCrouchLateralBase",sysItemView.wCrouchLateralBase));
		 			validators.push(getValidator("wCrouchUpModifier",sysItemView.wCrouchUpModifier));
		 			validators.push(getValidator("wCrouchLateralModifier",sysItemView.wCrouchLateralModifier));
		 			validators.push(getValidator("wCrouchUpMax",sysItemView.wCrouchUpMax));
		 			validators.push(getValidator("wCrouchLateralMax",sysItemView.wCrouchLateralMax));
		 			validators.push(getValidator("wCrouchDirChange",sysItemView.wCrouchDirChange));
		 		}
		 		if(type==2){
		 			validators.push(getValidator("cGender",sysItemView.cGender));
		 			validators.push(getValidator("cSide",sysItemView.cSide));
		 		}
		 		return validators;
		}
	}
}