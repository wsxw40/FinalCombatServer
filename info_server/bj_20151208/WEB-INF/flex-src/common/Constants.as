package common
{

	import mx.collections.ArrayCollection;
	
	public final class Constants
	{
			//User Black White List
			public static const NORMAL_LIST_VALUE:String	= "0";
			public static const BLACK_LIST_VALUE:String		= "1";
			public static const WHITE_LIST_VALUE:String		= "2";
			
			//TeamRecordType
			public static const APPLY_JOIN:int			= 0;
			public static const SUCCESS_JOIN:int		= 1;
			public static const SUCCESS_EXIT:int		= 2;
			
			//System Notice Type
			public static const LOOP_NOTICE:String		= "2";
			public static const FIX_NOTICE:String		= "1";
			
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
			
			public static const style_color:String		="color";
			public static const background_color:String = "backgroundColor";
			//tips in datagrid header
			public static const accuracy_tip:String="武器精度";//精度
			public static const recoil_tip:String="后座力";//后座力
			public static const recoil_stand_tip:String="后坐力（站立）";//后坐力（站立）
			public static const recoil_run_tip:String="后坐力（跑动）";//后坐力（跑动）
			public static const recoil_jump_tip:String="后坐力（跳起）";//后坐力（跳起）
			public static const recoil_crouch_tip:String="后坐力（蹲）";//后坐力（蹲）
			
			public static const part_tip:String="配件";//配件
			//for radar chart
			public static const recoil:String 					= "后坐力";
			public static const accuracy:String					= "精度";
			public static const speed_change_gun:String			= "换枪速度";
			public static const speed_change_magazine:String	= "换弹速度";
			public static const firing_rate:String				= "射速";
			public static const firing_range:String				= "射程";
			public static const bullet_capacity:String			= "弹量";
			public static const power:String					= "威力";
			
			public static const category:ArrayCollection = new ArrayCollection([
				{label:"--ALL--",	data:0},
				{label:"自动步枪", 	data:1},
                {label:"冲锋枪", 	data:2},
                {label:"狙击枪", 	data:3},
                {label:"机枪", 		data:4},
                {label:"散弹枪", 	data:5},
                {label:"手枪", 		data:6},
                {label:"近身武器", 	data:7},
                {label:"投掷类",	data:8}
			]);
			
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
             
           /**
		   * 
		   * 
		   * 
		   * */
            public static const typeArray:Array = [
            	{label:"武器/Weapon", data:1},
                {label:"服装/Top", data:2},
                {label:"配饰/Accessory", data:3},
                {label:"道具/Items", data:4},
                {label:"素材/Material ", data:5},
                {label:"蓝图/Blueprint", data:6},
                {label:"大礼包/GiftPack", data:7},
                {label:"打开类/OpenType", data:8},
                {label:"资源争夺战/ZYZDZ", data:9}
            ];
            
            public static const subTypeArray1:Array = [
            	{label:"套装/Top", data:1}
                
            ];
            public static const subTypeArray2:Array = [
            	/*{label:"自动步枪", data:1},
                {label:"冲锋枪", data:2},
                {label:"狙击枪", data:3},
                {label:"机枪", data:4},
                {label:"散弹枪", data:5},
                {label:"手枪", data:6},
                {label:"近身武器", data:7},
                {label:"投掷类", data:8},
                {label:"治愈类",	data:9},
                {label:"投射类",	data:10},
                {label:"喷射类",	data:11},
                {label:"辅助类",	data:12}*/
                {label:"主武器/Main", data:1},
                {label:"副武器/Sub", data:2},
                {label:"近身武器/Knife", data:3},
                {label:"特殊武器/Special", data:4}
            ];
            public static const subTypeArray3:Array = [
            	{label:"帽子/Hat", data:1},
                {label:"饰品/Adornment", data:2}
            ];
             public static const subTypeArray4:Array = [
            	/*{label:"功能型", data:1},
                {label:"辅助型", data:2},
                {label:"特殊型", data:3},
                {label:"房间道具", data:4},
                 {label:"魅力型", data:5}*/
				{label:"加成类/Add", data:1},
                {label:"名片/Card", data:2},
                {label:"喷灌/Spray", data:3},
                {label:"功能类/Function", data:4},
				{label:"非卖品/NotForSale ", data:5},
				{label:"礼盒（非卖）/BoxNotForSale", data:6},
				{label:"消耗类/Consumables", data:7}
            ];
            public static const levelType:Array = [
            	{label:"团队竞技/TDM", data:1},
                {label:"占点/OCCUP", data:2},
                {label:"推车/VEH", data:3},
                {label:"新手关/New", data:4},
                 {label:"歼灭战/ELIM", data:5},
                 {label:"BOSS战/BOSS", data:6},
                 {label:"刀战/MELEE", data:7}
            ];
            public static var actions:ArrayCollection= new ArrayCollection(
                [ 
                  {label:"Choose Please", data:0},
                  {label:"Before {0} o'clock, player gets reward after reaching Lv {1}", data:1},
                  {label:"Between {0} ~ {1} o'clock, get gift for first login on that day", data:2}, 
                  {label:"Between {0} ~ {1} o'clock, randomly give gifts to online players", data:3}, 
                  {label:"Between {0} ~ {1} o'clock, get 2x EXP and C Coins ({3}x)", data:4}, 
                  {label:"Between {0} ~ {1} o'clock, complete one achievement on Ach. List", data:5}, 
                  {label:"Between {0} ~ {1} o'clock, get reward for {3} kills", data:6}, 
                  {label:"Between {0} ~ {1} o'clock, kill {3} enemies in Boss Mode", data:7}, 
                  {label:"Between {0} ~ {1} o'clock, complete mission in the mission system {3} times to get reward", data:8}, 
                  /* {label:"在{0}至{1}，内针对指定商品可以做出打折活动", data:9}, */  
                  {label:"Between {0} ~ {1} o'clock, kill boss {3} times in Boss Mode", data:10},
                  {label:"From {0} to {1}, login everyday between {3} ~ {4} o'clock to get reward", data:11},
                  {label:"Between {0} ~ {1} o'clock, award top {3} to {4} players in weekly ranking", data:12},
                  {label:"Between {0} ~ {1} o'clock, deaths aren't recorded", data:13},
                  {label:"Between {0} ~ {1} o'clock, losses aren't recorded", data:14},
                  {label:"From {0} ~ {1} o'clock, Lv {3} player gets gift for first login on that day", data:15},
                  {label:"From {0} to {1}, equipment level won't be reduced when enhancement fails between {3} ~ {4} o'clock everyday", data:16},
                  {label:"From {0} to {1}, enhancement success rate increased between {3}~{4} o'clock everyday", data:17},
                  {label:"Between {0} ~ {1} o'clock, top-up to get reward", data:18},
                  {label:"Between {0} ~ {1} o'clock, complete {3} Eliminate BIOchems to get reward", data:19},
                  {label:"Between {0} ~ {1} o'clock, spend {3} FC points to get reward", data:20},
                  {label:"Between {0} ~ {1} o'clock, enhance any weapon to Lv {3}", data:21},
                  {label:"Between {0} ~ {1} o'clock, open chest to deliver blessing", data:22},
                  {label:"Between {0} ~ {1} o'clock, enhance sustainer successful", data:23},
                  {label:"Between {0} ~ {1} o'clock, kill boss {3} times in Judge Mode", data:24},
                  {label:"During {0} to {1}, earn double EXP and donation from Clan Battle from {3} to {4} o'clock everyday", data:25},
                  {label:"Between {0} ~ {1} o'clock, enhancement max strength level for all player to Lv{3}", data:26}, 
                  {label:"Between {0} ~ {1} o'clock, complete mission in the team combat {3} count to get reward", data:27}
                ]);
           public static const subTypeArray5:Array = [ 
				{label:"合成道具/Material", data:1}
				//{label:"镶嵌素材", data:2}
				,{label:"蓝图/Blueprint",data:2} 
            ];
            public static const subTypeArray6:Array = [
            	{label:"", data:1}
            ];
            public static const subTypeArray7:Array = [
            	{label:"", data:1}
            ];
            public static const subTypeArray8:Array = [
            	{label:"", data:1},
            ];
            
            public static const subTypeArray9:Array = [
            	{label:"城墙类/defense", data:1},
            	{label:"塔类/attack", data:2},
            	{label:"消耗坦克类(个人)/consume", data:3},
				{label:"消耗类(团队)/consume", data:4},
				{label:"系统共用资源/public", data:5},
				{label:"消耗技能(个人)/psill", data:6}
            ];            
            
            //the factory method for get subtype tab array
            public static function getSubTypeArray(subType:int):Array{
		  		var array:Array=new Array();
			  	switch (subType){
			  		case 0: array= subTypeArray2;break;//武器
			  		case 1: array= subTypeArray1;break;//服装
			  		case 2: array= subTypeArray3;break;
			  		case 3: array= subTypeArray4;break;
			  		case 4: array= subTypeArray5;break;
			  		case 5: array= subTypeArray6;break;
			  		case 6: array= subTypeArray7;break;
			  		case 7: array= subTypeArray8;break;
			  		case 8: array= subTypeArray9;break;
			  	}
			  	return array;
		 	}
		 	public static const GM_LOG_TYPE:ArrayCollection = new ArrayCollection([
		 		{label:"--ALL--", value:0},
		 		{label:"Create",  value:1},
		 		{label:"Update",  value:2},
		 		{label:"Delete",  value:3}
		 	]);
		 	
		 	public static const isDeletedArray:Array=[
		 		{label:"OnSale",value:0},
		 		{label:"NotOnSale",value:1},
		 	];
	}
	
	
}