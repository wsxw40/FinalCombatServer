package common
{

	import component.MyDataGridColumn;
	
	import mx.core.ClassFactory;
	
	import renderer.CheckBoxRenderer;
	import renderer.DateTimeRenderer;
	import renderer.LogSelectRenderer;
	
	public final class Constants
	{
		
			public static const BOOLEAN_YES:String				= "Y";
			public static const BOOLEAN_NO:String				= "N";
			//color style
			public static const black_color:Object=0x000000;
			public static const different_color:Object=0xFF0000;
			public static const normal_color:Object=0x0033ff;
			public static const clothes_color:Object=0x6600ff;
			//color in datagrid header display
			public static const w_normal_color:Object=0x0033ff;
			public static const w_move_color:Object=0x6600ff;
			public static const w_onair_color:Object=0xcc33ff;
			public static const w_crouch_color:Object=0x66ff66;
			public static const w_accuracy_color:Object=0xcc33ff;
			public static const w_recoil_color:Object=0x66ff66;
			//tips in datagrid header
			public static const accuracy_tip:String="武器精度";//精度
			public static const recoil_tip:String="后座力";//后座力
			public static const recoil_stand_tip:String="后坐力（站立）";//后坐力（站立）
			public static const recoil_run_tip:String="后坐力（跑动）";//后坐力（跑动）
			public static const recoil_jump_tip:String="后坐力（跳起）";//后坐力（跳起）
			public static const recoil_crouch_tip:String="后坐力（蹲）";//后坐力（蹲）
			
			public static const part_tip:String="配件";//配件
			
			
			public static const style_color:String="color";
			
			//the mamp for header tips
			public static const headTip:Object = new Object();
			headTip["wCrossOffset"]=accuracy_tip;
			headTip["wAccuracyDivisor"]=accuracy_tip;
			headTip["wAccuracyOffset"]=accuracy_tip;
			headTip["wMaxInaccuracy"]=accuracy_tip;
			//normal 
			headTip["wNormalUpBase"]=recoil_stand_tip;
			headTip["wNormalLateralBase"]=recoil_stand_tip;
			headTip["wNormalUpModifier"]=recoil_stand_tip;
			headTip["wNormalLateralModifier"]=recoil_stand_tip;
			
			headTip["wNormalUpMax"]=recoil_stand_tip;
			headTip["wNormalLateralMax"]=recoil_stand_tip;
			headTip["wNormalDirChange"]=recoil_stand_tip;
			//recoil
			headTip["wNormalOffset"]=recoil_tip;
			headTip["wNormalFactor"]=recoil_tip;
			headTip["wOnairOffset"]=recoil_tip;
			headTip["wOnairFactor"]=recoil_tip;
			headTip["wMoveOffset"]=recoil_tip;
			headTip["wMoveFactor"]=recoil_tip;
			//recoil move
			headTip["wMoveUpBase"]=recoil_run_tip;
			headTip["wMoveLateralBase"]=recoil_run_tip;
			headTip["wMoveUpModifier"]=recoil_run_tip;
			headTip["wMoveLateralModifier"]=recoil_run_tip;
			headTip["wMoveUpMax"]=recoil_run_tip;
			headTip["wMoveLateralMax"]=recoil_run_tip;
			headTip["wMoveDirChange"]=recoil_run_tip;
			
			//recoil jump
			headTip["wOnairUpBase"]=recoil_jump_tip;
			headTip["wOnairLateralBase"]=recoil_jump_tip;
			headTip["wOnairUpModifier"]=recoil_jump_tip;
			headTip["wOnairLateralModifier"]=recoil_jump_tip;
			headTip["wOnairUpMax"]=recoil_jump_tip;
			headTip["wOnairLateralMax"]=recoil_jump_tip;
			headTip["wOnairDirChange"]=recoil_jump_tip;
			//recoil crouch
			headTip["wCrouchUpBase"]=recoil_crouch_tip;
			headTip["wCrouchLateralBase"]=recoil_crouch_tip;
			headTip["wCrouchUpModifier"]=recoil_crouch_tip;
			headTip["wCrouchLateralModifier"]=recoil_crouch_tip;
			headTip["wCrouchUpMax"]=recoil_crouch_tip;
			headTip["wCrouchLateralMax"]=recoil_crouch_tip;
			headTip["wCrouchDirChange"]=recoil_crouch_tip;
             
           // tab array
            public static const typeArray:Array = [
            	{label:"武器", data:1},
                {label:"服装", data:2},
                {label:"配件", data:3},
                {label:"道具", data:4},
                {label:"大礼包", data:5}
            ];
            
            public static const subTypeArray1:Array = [
            	{label:"头盔", data:1},
            	{label:"面罩", data:2},
                {label:"上衣", data:3},
                {label:"手套", data:4},
                {label:"裤子", data:5},
                {label:"鞋", data:6},
                {label:"套装", data:7},
                {label:"徽章", data:8},
                {label:"面容", data:9}
                
            ];
            public static const subTypeArray2:Array = [
            	{label:"自动步枪", data:1},
                {label:"冲锋枪", data:2},
                {label:"狙击枪", data:3},
                {label:"机枪", data:4},
                {label:"散弹枪", data:5},
                {label:"手枪", data:6},
                {label:"近身武器", data:7},
                {label:"投掷类", data:8}
            ];
            public static const subTypeArray3:Array = [
            	{label:"枪托", data:1},
                {label:"瞄准镜", data:2},
                {label:"瞄具", data:3},
                {label:"机锤组", data:4},
                {label:"板机组", data:5},
                {label:"弹夹", data:6},
                {label:"导轨", data:7},
                {label:"前握把", data:8},
                {label:"枪管", data:9},
                {label:"消焰器", data:10},
                {label:"消音器", data:11},
                {label:"战术部件", data:12}
            ];
            public static const subTypeArray4:Array = [
            	{label:"防弹衣", data:1},
                {label:"喷罐", data:2}
            ];
             public static const subTypeArray5:Array = [
            	{label:"功能型", data:1},
                {label:"辅助型", data:2},
                {label:"特殊型", data:3}
            ];
           public static const subTypeArray6:Array = [
            	{label:"", data:1}
            ];
            //the factory method for get subtype tab array
            public static function getSubTypeArray(subType:int):Array{
		  		var array:Array=new Array();
			  	switch (subType){
			  		case 0: array= subTypeArray2;break;//武器
			  		case 1: array= subTypeArray1;break;//服装
			  		case 2: array= subTypeArray3;break;
			  		case 3: array= subTypeArray5;break;
			  		case 4: array= subTypeArray6;break;
			  	}
			  	return array;
		 	}
     
            
            
			//get column for datagrid dynamic
           public static function getColumnCommon():Array{
			var columns:Array = new Array();
			var id:MyDataGridColumn=new MyDataGridColumn("id","ID",false,50);
			id.editable=false;
			columns.push(id);
			columns.push(new MyDataGridColumn("displayName","DisplayName",false,100));
			columns.push(new MyDataGridColumn("name","Name",false,100));
			var isDeleted:MyDataGridColumn=new MyDataGridColumn("isDeleted","IsDeleted",false,50);
			isDeleted.itemRenderer=new ClassFactory(CheckBoxRenderer);
			isDeleted.editable=false;
			columns.push(isDeleted);
			columns.push(new MyDataGridColumn("currency","currency",false,50));
			columns.push(new MyDataGridColumn("unitType","unitType",false,50));
			columns.push(new MyDataGridColumn("cost1","Cost1",false,50));
			columns.push(new MyDataGridColumn("unit1","unit1",false,50));
			columns.push(new MyDataGridColumn("cost2","Cost2",false,50));
			columns.push(new MyDataGridColumn("unit2","unit2",false,50));
			columns.push(new MyDataGridColumn("cost3","Cost3",false,50));
			columns.push(new MyDataGridColumn("unit3","unit3",false,50));
			columns.push(new MyDataGridColumn("level","Level",false,50));
			
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
				columns.push(new MyDataGridColumn("displayName","DisplayName",isHiLight,100));
				columns.push(new MyDataGridColumn("name","Name",isHiLight,100));
				columns.push(new MyDataGridColumn("isDeleted","IsDeleted",isHiLight,50));
				columns.push(new MyDataGridColumn("currency","currency",false,50));
				columns.push(new MyDataGridColumn("unitType","unitType",false,50));
				columns.push(new MyDataGridColumn("cost1","Cost1",isHiLight,50));
				columns.push(new MyDataGridColumn("unit1","unit1",isHiLight,50));
				columns.push(new MyDataGridColumn("cost2","Cost2",isHiLight,50));
				columns.push(new MyDataGridColumn("unit2","unit2",isHiLight,50));
				columns.push(new MyDataGridColumn("cost3","Cost3",isHiLight,50));
				columns.push(new MyDataGridColumn("unit3","unit3",isHiLight,50));
				columns.push(new MyDataGridColumn("level","Level",isHiLight,50));
				
				
				var dg:MyDataGridColumn=new MyDataGridColumn("logTime","LogTime",false,150);
				dg.itemRenderer=new ClassFactory(DateTimeRenderer);
				columns.push(dg);
				return columns;
			}
           public static function getColumnClothes(isHiLight:Boolean):Array{
				var columns:Array = getColumnCommon();
				columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
				var CSide:MyDataGridColumn=new MyDataGridColumn("CSide","CSide",isHiLight,50,style_color,clothes_color);
				var CGender:MyDataGridColumn=new MyDataGridColumn("CGender","CGender",isHiLight,80,style_color,clothes_color);
				columns.push(CSide);
				columns.push(CGender);
				columns.push(new MyDataGridColumn("CId","cId",isHiLight,80,style_color,clothes_color));
				columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
				return columns;
			}
			public static function getLogColumnClothes(isHiLight:Boolean):Array{
				var columns:Array = getLogColumnCommon(isHiLight);
				columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
				var CSide:MyDataGridColumn=new MyDataGridColumn("CSide","CSide",isHiLight,50,style_color,clothes_color);
				var CGender:MyDataGridColumn=new MyDataGridColumn("CGender","CGender",isHiLight,80,style_color,clothes_color);
				columns.push(CSide);
				columns.push(CGender);
				columns.push(new MyDataGridColumn("CId","cId",isHiLight,80,style_color,clothes_color));
				
				return columns;
			}
			
			public static function getColumnItem(isHiLight:Boolean):Array{
				var columns:Array = getColumnCommon();
				columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
				columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
				columns.push(new MyDataGridColumn("iValue","iValue",isHiLight,50));
				columns.push(new MyDataGridColumn("WMaxLength","WMaxLength",isHiLight,50));
				columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
				return columns;
			}
				public static function getLogColumnItem(isHiLight:Boolean):Array{
				var columns:Array = getLogColumnCommon(isHiLight);
				columns.push(new MyDataGridColumn("IBuffId","IBuffId",isHiLight,50));
				columns.push(new MyDataGridColumn("IId","IId",isHiLight,50));
				columns.push(new MyDataGridColumn("IValue","IValue",isHiLight,50));
				columns.push(new MyDataGridColumn("WMaxLength","WMaxLength",isHiLight,50));
				columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
				return columns;
			}
			public static function getColumnPackage(isHiLight:Boolean):Array{
				var columns:Array = getColumnCommon();
				columns.push(new MyDataGridColumn("items","items",isHiLight,50));
				columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
				return columns;
			}
			public static function getLogColumnPackage(isHiLight:Boolean):Array{
				var columns:Array = getLogColumnCommon(isHiLight);
				columns.push(new MyDataGridColumn("items","items",isHiLight,50));
				columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
				return columns;
			}
		 	public static function getColumnArray(type:int,isHiLight:Boolean):Array{
		  		var array:Array=new Array();
			  	switch (type){
			  		case 0: array= getColumnWeapon(isHiLight);break;
			  		case 1: array= getColumnClothes(isHiLight);break;
			  		case 2: array= getColumnParts(isHiLight);break;
			  		case 3: array= getColumnItem(isHiLight);break;
			  		case 4: array= getColumnPackage(isHiLight);break;
			  	}
			  	return array;
		 	}
		 	//isHiLight:use to is add logredenerer to column
		 	public static function getLogColumnArray(type:int,isHiLight:Boolean):Array{
		  		var array:Array=new Array();
			  	switch (type){
			  		case 0: array= getLogColumnWeapon(isHiLight);break;
			  		case 1: array= getLogColumnClothes(isHiLight);break;
			  		case 2: array= getLogColumnParts(isHiLight);break;
			  		case 3: array= getLogColumnItem(isHiLight);break;
			  		case 4: array= getLogColumnPackage(isHiLight);break;
			  		case 5: array= getLogColumnCommon(isHiLight);break;
			  	}
			  	return array;
		 	}
			public static function getColumnParts(isHiLight:Boolean):Array{
	
			var columns:Array =getColumnCommon();
			columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime"));
			columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
			columns.push(new MyDataGridColumn("WPenetration","wPenetration"));
			columns.push(new MyDataGridColumn("WDamage","wDamage"));
			columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier"));
			columns.push(new MyDataGridColumn("wFireTime","wFireTime"));
			columns.push(new MyDataGridColumn("wReloadTime","wReloadTime"));
			columns.push(new MyDataGridColumn("wZoomTime","wZoomTime"));
			columns.push(new MyDataGridColumn("wZoomValue","wZoomValue"));
			columns.push(new MyDataGridColumn("WAmmoOneClip","wAmmoOneClip"));
			columns.push(new MyDataGridColumn("WAmmoCount","wAmmoCount"));
			//accuracy info
			columns.push(new MyDataGridColumn("WAutoFire","wAutoFire"));
			columns.push(new MyDataGridColumn("wTimeToIdle","wTimeToIdle"));
			
			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,style_color,w_accuracy_color));
			//normal 
			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,100,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,style_color,w_normal_color));
			
			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,100,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,style_color,w_normal_color));
			//move
			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,style_color,w_move_color));
			//recoil
			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,style_color,w_recoil_color));
			
		
			
			
			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,style_color,w_onair_color));
			
			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,style_color,w_crouch_color));
			
			columns.push(new MyDataGridColumn("wUpModifier","wUpModifier"));
			columns.push(new MyDataGridColumn("wStabTime","wStabTime"));
			columns.push(new MyDataGridColumn("wStabLightTime","wStabLightTime"));
			columns.push(new MyDataGridColumn("wStabHurt","wStabHurt"));
			columns.push(new MyDataGridColumn("wStabLightHurt","wStabLightHurt"));
			columns.push(new MyDataGridColumn("wExplodeTime","wExplodeTime"));
			columns.push(new MyDataGridColumn("wReadyTime","wReadyTime"));
			columns.push(new MyDataGridColumn("wThrowOutTime","wThrowOutTime"));
			columns.push(new MyDataGridColumn("wHurtRange","wHurtRange"));
			columns.push(new MyDataGridColumn("wHurt","wHurt",isHiLight,50));
			columns.push(new MyDataGridColumn("PSuitable","PSuitable"));
			columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
			return columns;
			}
			
					
			public static function getColumnWeapon(isHiLight:Boolean):Array{
	
			var columns:Array =getColumnCommon();
			columns.push(new MyDataGridColumn("modifiedDesc","modifiedDesc",isHiLight,100));
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
			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,style_color,w_accuracy_color));
			
			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,100,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,style_color,w_normal_color));
			
			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,100,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,style_color,w_normal_color));
			
			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,style_color,w_move_color));
			
			
			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,style_color,w_recoil_color));
			
		
			
			
			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,style_color,w_onair_color));
			
			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,style_color,w_crouch_color));
			
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
			columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
			return columns;
			}  
           public static function getLogColumnParts(isHiLight:Boolean):Array{
				
			
			var columns:Array =getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime",isHiLight));
			columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
			columns.push(new MyDataGridColumn("WPenetration","wPenetration",isHiLight));
			columns.push(new MyDataGridColumn("WDamage","wDamage",isHiLight));
			columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier",isHiLight));
			columns.push(new MyDataGridColumn("wFireTime","wFireTime",isHiLight));
			columns.push(new MyDataGridColumn("wReloadTime","wReloadTime",isHiLight));
			columns.push(new MyDataGridColumn("wZoomTime","wZoomTime",isHiLight));
			columns.push(new MyDataGridColumn("wZoomValue","wZoomValue",isHiLight));
			columns.push(new MyDataGridColumn("WAmmoOneClip","wAmmoOneClip",isHiLight));
			columns.push(new MyDataGridColumn("WAmmoCount","wAmmoCount",isHiLight));
			
			columns.push(new MyDataGridColumn("WAutoFire","wAutoFire",isHiLight));
			columns.push(new MyDataGridColumn("wTimeToIdle","wTimeToIdle",isHiLight));
			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,style_color,w_accuracy_color));
			
			
			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,style_color,w_normal_color));
			
			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,style_color,w_normal_color));
			
			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,style_color,w_move_color));
			
			
			
			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,style_color,w_recoil_color));
			
		
			
			
			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,style_color,w_onair_color));
			
			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,style_color,w_crouch_color));
			
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
			columns.push(new MyDataGridColumn("PSuitable","PSuitable",isHiLight));
			columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
			return columns;
			}
			 public static function getLogColumnWeapon(isHiLight:Boolean):Array{
	
			var columns:Array =getLogColumnCommon(isHiLight);
			columns.push(new MyDataGridColumn("modifiedDesc","modifiedDesc",isHiLight,100));
			columns.push(new MyDataGridColumn("WId","wId",isHiLight,50));
			columns.push(new MyDataGridColumn("resourceStable","resourceStable",isHiLight,100));
			columns.push(new MyDataGridColumn("resourceChangeable","resourceChangeable",isHiLight,100));
			columns.push(new MyDataGridColumn("wChangeInTime","wChangeInTime",isHiLight));
			columns.push(new MyDataGridColumn("wMoveSpeedOffset","wMoveSpeedOffset",isHiLight,120));
			columns.push(new MyDataGridColumn("WPenetration","wPenetration",isHiLight));
			columns.push(new MyDataGridColumn("WDamage","wDamage",isHiLight));
			columns.push(new MyDataGridColumn("wRangeModifier","wRangeModifier",isHiLight));
			columns.push(new MyDataGridColumn("wFireTime","wFireTime",isHiLight));
			columns.push(new MyDataGridColumn("wReloadTime","wReloadTime",isHiLight));
			columns.push(new MyDataGridColumn("wZoomTime","wZoomTime",isHiLight));
			columns.push(new MyDataGridColumn("wZoomValue","wZoomValue",isHiLight));
			columns.push(new MyDataGridColumn("WAmmoOneClip","wAmmoOneClip",isHiLight));
			columns.push(new MyDataGridColumn("WAmmoCount","wAmmoCount",isHiLight));
			
			columns.push(new MyDataGridColumn("WAutoFire","wAutoFire",isHiLight));
			columns.push(new MyDataGridColumn("wTimeToIdle","wTimeToIdle",isHiLight));
			columns.push(new MyDataGridColumn("wCrossOffset","wCrossOffset",isHiLight,100,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("WAccuracyDivisor","wAccuracyDivisor",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wAccuracyOffset","wAccuracyOffset",isHiLight,120,style_color,w_accuracy_color));
			columns.push(new MyDataGridColumn("wMaxInaccuracy","wMaxInaccuracy",isHiLight,120,style_color,w_accuracy_color));
			
			columns.push(new MyDataGridColumn("wNormalUpBase","wNormalUpBase",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralBase","wNormalLateralBase",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalUpModifier","wNormalUpModifier",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralModifier","wNormalLateralModifier",isHiLight,120,style_color,w_normal_color));

			columns.push(new MyDataGridColumn("wNormalUpMax","wNormalUpMax",isHiLight,120,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalLateralMax","wNormalLateralMax",isHiLight,150,style_color,w_normal_color));
			columns.push(new MyDataGridColumn("wNormalDirChange","wNormalDirChange",isHiLight,120,style_color,w_normal_color));
			
			columns.push(new MyDataGridColumn("wMoveUpBase","wMoveUpBase",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralBase","wMoveLateralBase",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpModifier","wMoveUpModifier",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralModifier","wMoveLateralModifier",isHiLight,150,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveUpMax","wMoveUpMax",isHiLight,100,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveLateralMax","wMoveLateralMax",isHiLight,120,style_color,w_move_color));
			columns.push(new MyDataGridColumn("wMoveDirChange","wMoveDirChange",isHiLight,120,style_color,w_move_color));
			
			
			columns.push(new MyDataGridColumn("wNormalOffset","wNormalOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wNormalFactor","wNormalFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairOffset","wOnairOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wOnairFactor","wOnairFactor",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveOffset","wMoveOffset",isHiLight,100,style_color,w_recoil_color));
			columns.push(new MyDataGridColumn("wMoveFactor","wMoveFactor",isHiLight,100,style_color,w_recoil_color));
			
		
			
			
			columns.push(new MyDataGridColumn("wOnairUpBase","wOnairUpBase",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralBase","wOnairLateralBase",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpModifier","wOnairUpModifier",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralModifier","wOnairLateralModifier",isHiLight,150,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairUpMax","wOnairUpMax",isHiLight,100,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairLateralMax","wOnairLateralMax",isHiLight,120,style_color,w_onair_color));
			columns.push(new MyDataGridColumn("wOnairDirChange","wOnairDirChange",isHiLight,120,style_color,w_onair_color));
			
			columns.push(new MyDataGridColumn("wCrouchUpBase","wCrouchUpBase",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralBase","wCrouchLateralBase",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpModifier","wCrouchUpModifier",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralModifier","wCrouchLateralModifier",isHiLight,150,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchUpMax","wCrouchUpMax",isHiLight,100,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchLateralMax","wCrouchLateralMax",isHiLight,120,style_color,w_crouch_color));
			columns.push(new MyDataGridColumn("wCrouchDirChange","wCrouchDirChange",isHiLight,120,style_color,w_crouch_color));
			
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
			
			columns.push(new MyDataGridColumn("description","Description",isHiLight,50));
			return columns;
			} 
	}
}