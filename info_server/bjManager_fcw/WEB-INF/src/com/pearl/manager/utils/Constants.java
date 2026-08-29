package com.pearl.manager.utils;

import java.util.Date;

public class Constants {
	public static enum DAULEVEL{
		LEVEL0(0,"未充值用户"),LEVEL1(1,"个位数"),LEVEL2(11,"十位数"),LEVEL3(101,"百位数"),LEVEL4(1001,"千位数"),LEVEL5(10001,"万位数"),LEVEL6(100001,"10万以上");
		private int value;
		private String des;
		DAULEVEL(int value,String des){
			this.value = value;
			this.des = des;
		}
		public static DAULEVEL getTypeByValue(int value){
			for(DAULEVEL type : DAULEVEL.values() ){
				if (type.value == value) {
					return type;
				}
			}
			return null;
		}
		public int getValue(){
			return value;
		}
		public String getDes() {
			return des;
		}
	}
	

	public static final String CHARACTER_LIST 						= "CharacterList";

	public static final String BLACK_PLAYER_LIST 					= "BlackPlayerList";
	public static final String WHITE_PLAYER_LIST 					= "WhitePlayerList";
	public static final String SYS_CONFIG_MAP 						= "SysConfigMap";
	public static final String BLACK_LIST_FLAG						= "1";
	public static final String WHITE_LIST_FLAG						= "2";

	public static final String SYS_NOTICE_LIST 						= "SysNoticeList";

	public static final String PACK_TYPE_C							= "C";
	public static final String PACK_TYPE_W							= "W";

	public static final String DELIMITER							= "_";
	public static final String XUNLEI_LOG_DELIMITER					= "|";
	public static final String COMMA								=",";
	public static final String SEMICOLON							=";";
	public static final String DEFAULT_PLAYER_ICON					= "01_r";

	public static final String DELIMITER_RESOURCE_CHANGEABLE		= ";";
	public static final String DELIMITER_RESOURCE_STABLE			= ",";
	public static final String DOT									= ".";
	public static final String OBJECT_TYPE							= "O";
	public static final String LOCK_TYPE							= "LOCK";
	public static final String STRING_TYPE							= "S";
	public static final String XUNLEI_LOG_WRITE 					= "XunleiLogWrite";
	public static final String SYN_CACHE_DB 						= "SynCacheDB";
	public static final String DAILY_NUM_REDIS 						= "DailyNumRedis";
	//------------------------
	public static final Date EXPIREDATEOBJECT 						= new Date(0);
	public static final String ENCODING								= "GBK";
	public static final String SYSTEM_NAME							= "系统邮件";
	public static final String SYSTEM_SPEAKER						= "系统公告";
	public static final String BOOLEAN_YES							= "Y";
	public static final String BOOLEAN_NO							= "N";
	public static final String DLB 									= "大喇叭";
	public static final String XLB 									= "小喇叭";
	public static final String BOOLEAN_TRUE							= "true";
	public static final String BOOLEAN_FALSE						= "false";


	public static final int AVATAR_CHANGE_SIZE						= 3;
	public static final int	WEAPON_CHANGE_SIZE						= 7;

	//gameResult
	public static final int	GAMERESULT_WIN							= 1;
	public static final int	GAMERESULT_DRAW							= 0;
	public static final int	GAMERESULT_LOSE							= -1;

	public static final int IS_DELETED_NO							=0;
	public static final int IS_DELETED_YES							=1;


	

	public static final int	TOP_PLAYER_ACTIVITY_MINUTES		= 7 * 24 * 60;

	public static final int FRIEND							= 1;
	public static final int BLACKLIST						= 2;
	public static final int GROUP							= 3;
	
	public static final int SHOP_VIP_INDEX					= 8;
	public static final int SHOP_WEB_INDEX					= 9;

	public static final int DEFAULT_WEAPON_TYPE         	= 1;//武器
	public static final int DEFAULT_COSTUME_TYPE        	= 2;//角色
	public static final int DEFAULT_PART_TYPE        		= 3;//配饰
	public static final int DEFAULT_ITEM_TYPE        		= 4;//道具
	public static final int DEFAULT_MATERIAL_TYPE       	= 5;//素材
	public static final int DEFAULT_BLUEPRINT_TYPE      	= 6;//蓝图
	public static final int DEFAULT_PACKAGE_TYPE        	= 7;//大礼包
	public static final int DEFAULT_OPEN_TYPE        		= 8;//打开类
	
	public static  enum DEFAULT_ITEM_SUBTYPE{
		ADD(1),CARD(2),SPRAY(3),FUNCTION(4),NOTSALE(5),GIFTBOX(6),COST(7);
		private int value;
		public int getValue(){
			return this.value;
		}
		DEFAULT_ITEM_SUBTYPE(int value){
			this.value = value;
		}
	}
	//unitype
	public static final int DEFAULT_TIMEBASE_TYPE       	= 2;//time base
	public static final int DEFAULT_NUMBASE_TYPE       	= 1;//num base
	public static final int UNITTYPE_FOREVER 				= 0;//forever
	//items
	public static final int DEFAULT_SPECIEL_ITEM_SUBTYPE    = 3;
	public static final int DEFAULT_KNIFE_SUBTYPE       	= 3;
	public static final int DEFAULT_RELEASE_SIZE_ID       	= 33;
	public static final int DEFAULT_CARD_BUFF_ID       		= 32;
	public static final int ITEM_SPRAY_IBUFFID				= 6;
	public static final int DEFAULT_FACE_SUBTYPE        	= 9;
	public static final int DEFAULT_COSTUME_SET_SUBTYPE 	= 7;
	public static final int DEFAULT_KNIFE_SEQ       		= 3;
	//team item
	public static final int DEFAULT_TEAM_IID       			= 7;
//	public static final int DEFAULT_WEAPON_PACK_SIZE    	= 2;
//	public static final int MAX_WEAPON_PACK_SIZE    		= 5;
	//TODO CharacterSize
	public static final int DEFAULT_CHARACTER_SIZE    		= 5;//must not change
	public static final int DEFAULT_SEQ_SIZE    			= 4;
	public static final int DEFAULT_COSTUME_SEQ_SIZE    	= 3;
	//pack iid
	public static final int DEFAULT_PACK_IID       			= 5;
	public static final int DEFAULT_MEDAL_IID				= 17;
	
	//mtype
	public static final int DEFAULT_MEDAL_MTYPE				= 6;

	//pageSize
	public static final int DEFAULT_A_PAGE_SIZE				=32;
	public static final int DEFAULT_B_PAGE_SIZE				=16;
	public static final int DEFAULT_C_PAGE_SIZE				=8;

	public static final int DEFAULT_COSTUME_PACK_SIZE   = 1;
	public static final int DEFAULT_COSTUME_PACK_SEQ   	= 7;//costume seq
	public static final int SEQ_NUM                 	= 6;//weapon seq

	public static final int NUM_ZERO                	= 0;
	public static final int NUM_ONE               		= 1;
	public static final int NUM_TWO              		= 2;
	public static final int NUM_THREE              		= 3;
	public static final int NUM_FOUR              		= 4;
	public static final int NUM_NEG_TWO					= -2;
	public static final int TRY_LOCK_COUNT            = 2;
	public static final int RANDOM_NUM_LOCK_TIME        = 500;//ms
	public static final int STAGE_CLEAR_LOCK_TIME       = 500;//ms
	public static final double FUNDUS_NUM              	= 7/12d;
	//about item
	public static final int ITEM_BULLETPROOF_IBUFFID    = 21;

	public static final int TYPE_NUM                	= 8;
	public static final int SUB_TYPE_NUM                = 9;
	public static final int SYS_CHARACTER_NUM           = 10;
	public static final int HIGH_RARE_LEVEL				= 40;
	public static final int BRONZE_HIGH_RARE_LEVEL		= 10;
	public static final int SILVER_HIGH_RARE_LEVEL		= 40;
	public static final int GOLD_HIGH_RARE_LEVEL		= 40;
	public static final int MAGIC_BOX_HIGH_RARE_LEVEL	= 40;
	//generate a fake system item id for purchase character
	public static final int SYS_ITEM_CHARACTER_ID  		= 99999999;
	//keys in memcache
	public static final String GET_PLAYER_BY_USER_ID 	= "P.B";
	public static final String GET_SERVER_LIST			= "S.L";
	public static final String GET_RANK_LIST			= "R.L";
	public static final String GET_SYSCHARACTER_LIST	= "SC.L";
	public static final String GET_SYSBIOCHARACTER_LIST	= "SBC.L";
	public static final String TEAM_LIST				= "TL";
	public static final String GET_SYSTEMITEM_LIST		= "SI.L";
	public static final String SYSTEMITEM				= "SI";
	public static final String LUCKYPACKAGE				= "LUCKYPACKAGE";
	public static final String LUCKYPACKAGERANDOMNAMES	= "LUCKYPACKAGERANDOMNAMES";
	public static final String PLAYER					= "P";
	public static final String PLAYER_INFO					= "P_I";
	public static final String ONLINEAWARD					= "OA";
	public static final String ONLINEAWARD_LIST					= "OA.L";
	public static final String PLAYERID					= "PI";
	public static final String PLAYERWITHRANK			= "PWR";
	public static final String PLAYER_LOCATION			= "PL";
	public static final String TOOLTIP					= "Tooltip";
	public static final String STORAGE					= "S";
	public static final String PACK						= "Pack";
	public static final String MESSAGE					= "M";
	public static final String OUT_BOX_MESSAGE			= "OB";
	public static final String STAGECLEAR				= "SC";
	public static final String STAGEQUIT				= "SQ";
	public static final String TEAM						= "Team";
	public static final String SYS_CFG 					= "SysConf";
	public static final String SYSITEM_LIST 			= "SIL";//所有的sysitem
	public static final String PAYMENT_LIST 			= "PaymentList";
	public static final String LEVEL_LIST 				= "LL";
	public static final String BLACK_IP_LIST 			= "BIL";
	public static final String BLACK_LIST 				= "BL";
	public static final String PLAYER_MISSION_LIST	 	= "PML";
	public static final String SYS_MISSION_MAP 			= "SM";
	public static final String GET_AWARD 				= "GAM";
	public static final String D_MISSION 				= "DMM";
	public static final String W_MISSION 				= "WMM";
	public static final String D_MISSION_DATE 			= "DMD";
	public static final String W_MISSION_DATE 			= "WMD";
	public static final String M_MISSION_DATE 			= "MMD";
	public static final String D_GAME_DATE 				= "MGD";
	public static final String BANNED_WORD 				= "BW";
	public static final String FRIEND_LIST 				= "FL";
	public static final String PLAYER_TEAM_LIST 		= "PTL";
	public static final String GROUP_LIST 				= "GL";
	public static final String REQ_BLACK_LIST 			= "RBL";
	public static final String REQ_FRIEND_LIST 			= "RFL";
	public static final String REQ_GROUP_LIST 			= "RGL";
	public static final String PLAYER_ACHIEVEMENT_LIST	= "PAL";
	public static final String SYS_ACHIEVEMENT_MAP		= "SAM";
	public static final String SYS_ACHIEVEMENT_BASE_MAP = "SABM";
	public static final String RANK 					= "Rank";
	public static final String PLAYER_RATE_LAST_MODIFY 	= "PR";
	public static final String SERVER_LIST				= "SL";
	public static final String CHANNEL_LIST				= "CL";
	public static final String CHANNEL_ACTION_ADD		= "add";
	public static final String CHANNEL_ACTION_DELETE	= "delete";
	public static final String CHANNEL_NAME				= "C";
	public static final String CURRENT_ACTIVITY			= "A";
	public static final String PLAYER_CURRENT_ACTIVITY	= "P.A";


	public static final int DEFAULT_PART_VIEW_SIZE 		= 4;
	public static final int DEFAULT_PAGE_SIZE 			= 8;
	public static final int FRIEND_PAGE_SIZE			= 50;
	public static final int TEAM_PAGE_SIZE				= 8;
	public static final int TOP_PAGE_SIZE				= 10;
	public static final int PAGE_SIZE_MESSAGE_ITEM[]	= {12,10,20,20,20,32,32,32};
	public static final int PAGE_SIZE[]					= {8,8,16,32,32,32,32,32};
	public static final int STRENGTH_PAGE_SIZE[]		= {8,8,16,32,16,32,32,32};
	//wid
	public static final int WEAPON_PISTOL_ID			= 1;//手枪
	public static final int WEAPON_SUBMACHINE_ID		= 2;//冲锋	枪
	public static final int WEAPON_RIFFLE_ID			= 3;//自动步枪
	public static final int WEAPON_SNIPER_GUN_ID		= 4;//狙击枪
	public static final int WEAPON_SHOT_GUN_ID			= 5;//散弹枪
	public static final int WEAPON_MACHINE_GUN_ID		= 6;//机枪
	public static final int WEAPON_MINI_GUN_ID			= 7;//mini枪
	public static final int WEAPON_DOUBLE_PISTOL_ID		= 8;//双枪
	public static final int WEAPON_MEDITNEEDLE_GUN_ID	= 9;//MeditNeedleGun针筒枪
	public static final int WEAPON_SIGNAL_GUN_ID		= 14;//信号枪
	public static final int WEAPON_CURE_GUN_ID			= 15;//治疗枪
	public static final int WEAPON_FLAME_GUN_ID			= 16;//喷火枪
	public static final int WEAPON_ROCKET_LUNCHER_ID	= 17;//火箭筒
	public static final int WEAPON_BOW_ID				= 18;//弓
	public static final int WEAPON_KNIFE_ID				= 19;//刀
	public static final int WEAPON_THROW_ID				= 20;
	public static final int WEAPON_GRENADE_ID			= 20;
	public static final int WEAPON_FLASH_ID				= 21;
	public static final int WEAPON_SMOKE_ID				= 22;
	public static final int WEAPON_BOSSPVE_ID			= 10;
	public static final int WEAPON_AMMO_GRENADE_ID		= 42;//箭
	public static final int WEAPON_ARROW_ID				= 45;//箭
	public static final int WEAPON_WAR_DRUM				= 47;
	public static final int WEAPON_MILK					= 48;
	public static final int WEAPON_GLASS				= 49;
	public static final int WEAPON_BIOCHEMICAL			= 51;
	public static final int WEAPON_BIOCHEMICAL_AVO		= 52;
	public static final int ITEM_TYPE_WEAPON			= 1;
	public static final int ITEM_TYPE_PART				= 3;

	public static final int ITEM_UNIT_TYPE_TIMEBASE		= 2;




	public static final String GP_PAY_STRING			= "GP";
	public static final String CR_PAY_STRING			= "CR";
	public static final String EXPIRE_DATE				= "2020-10-10";

	public static final int SUBTYPE_ARRAY[]				= {8,9,7,5,1};
	public static final int WEAPON_DURABLE_COST	        = 1;
	//SIDE
	public static final int SIDE_RED 					= 0;
	public static final int SIDE_BLUE 					= 1;
	public static final int SIDE_OBSERVER 				= 2;

	public static final int ACHIEVEMENT_BASE 				= 1;
	public static final int ACHIEVEMENT_HUOYAN				= 2;

	//NOSQL KEY
	public static final String PLAYERTOP_ROWNUM_KEY_PREFIX 	= "pt:rn:";
	public static final String PLAYERTOP_RANK_KEY_PREFIX 	= "pt:r:";
	public static final String PLAYERTOP_REAL_RANK_KEY_PREFIX 	= "pt:r:r:";
	public static final String PLAYERTOP_FIGHTNUM_RANK_KEY_PREFIX 	= "pt:fn:r:";
	public static final String PLAYERTOP_FIGHTNUM_REAL_RANK_KEY_PREFIX 	= "pt:fn:r:r:";
	public static final String PLAYERTOP_FST_LST_RCD_KEY_PREFIX 	= "pt:rcd:";//记录第一和最后计入排名的数值
	public static final String PLAYERTOP_WEAPON_PREFIX 		= "playerTop:weaponActivity:";
	public static final String TEAMRECORD_PREFIX 			= "TeamRecord:";
	public static final String DAILY_MISSION_PREFIX 		= "dm:";//DailyMission
	public static final String WEEK_MISSION_PREFIX 			= "wm:";//WeekMission
	public static final String MONTH_MISSION_PREFIX 		= "mm:";//MonthMission 
	public static final String DAILY_GAME_PREFIX 			= "dg:";//MonthMission 
	public static final String DAILY_CHECK_PREFIX 			="dc:";
	public static final String DAILY_CHECK_TIMES_PREFIX		="dct:";
	public static final String DAILY_GIFT_DATE_PREFIX 		="dgd:";
	public static final String CHECK_ITEM_PREFIX 			="ci:";
	public static final String YESTODAY_DATA	 			= "SD:";//StayData
	public static final String DAILY_RANDOM_NUM_KEY			="DRNK";//dailyRandomNum
	public static final String DAILY_RANDOM_NUM_MCC_KEY			="drdnmc";
	public static final String INIT_PLAYER_TOP_MCC_KEY			="iptmc";
	public static final String CLN_PLAYER_TOP_MCC_KEY			="cptmc";
	public static final String DAILY_PLAYER_NUM_PREFIX		="dpn:";//playerDailyNum
	public static final String DAILY_PLAYER_TOM_NUM_PREFIX		="dptn:";//playerDailyTomorrowNum
	public static final String  IS_DAILY_GUS_AWARD_PREFIX	="idga:";// is player get award when guess num 
	public static final String  IS_INIT_PLAYER_NUM_PREFIX	="iipn:";// is init player num
	
	public static final String  UPDATESETKEY				="pj_dirtyMappingBeanKeys_update_set";// is init player num
	public static final String  DUMPSETKEY					="pj_dirtyMappingBeanKeys_dump_set";// is init player num

	
	public static final int[] CHARACTER_IDS = {0,1,2,3,4,5,6};
	public static final int CURRENT_RANK_NUM = 10000;
	public static final int REAL_TOP_RANK_NUM = 15000;
	public static final int MAX_CACHE_REAL_TOP_RANK_NUM = 20000;
	public static final int RANDOM_RANK_NUM = 1000;
	
	public static final int CURRENT_WEEK_RANK_NUM = 400;
	//SYS CONFIG KEY
	public static final String CFG_CLIENT_VERSION 			= "client.version";

	//EXPIRED ITEM
	public static  final int EXPIRED_ITEM_NOTIFY_HOUR = 0;

	public static  final int EXPIRED_ITEM_MAIL_HOUR = 24;

	//TUTORIAL
	public static final String TUTORIAL_DEFAULT 			= "0111111101";

	//player who reached level 10  will receiver a gift
	public static final int SYS_GIFT_LEVEL 					= 10;
	//game type  key
	public static enum GAMETYPESTR{
		kTeam, kTarget, kDeliver, kNewTrain, kDestory, kHitBoss,kKnife;
	}
	public static enum GAMETYPE{
		TEAM(0),TARGET(1),DELIVER(2),NEWTRAIN(3),DESTROY(4),HITBOSS(5),KNIFE(6),BLAST(7),STREETBOY(8),BIOCHEMICAL(9),BOSS2(10);
		private int value;
		GAMETYPE(int value){
			this.value = value;
		}
		public static GAMETYPE getTypeByValue(int value){
			for(GAMETYPE type : GAMETYPE.values() ){
				if (type.value == value) {
					return type;
				}
			}
			return null;
		}
		public int getValue(){
			return value;
		}
	}
	final public static double[][] FIGHT_PARAM = {
		{1000, 1.085, 30, 0, 50, 25, 16.7},//主武器
		{300, 1.085, 30, 0, 50, 25, 16.7},//近身武器
		{1000, 1.042, 30, 0, 50, 25, 16.7},//服装
		{1000, 1.014, 30, 0, 50, 25, 16.7},//帽子
		{1000, 1.028, 30, 0, 50, 25, 16.7},//配饰
		{600, 1.085, 30, 0, 50, 25, 16.7},//副武器
	};
	final public static double[] FIGHT_PARAM1 ={0.85,0.05,0.1,0.0002,0.001,0.001,0.001};
	
	public static final byte  PLAYER_NORMAL_REMAIN_VOTERNUM = 2;
	public static final byte  PLAYER_VIP_REMAIN_VOTERNUM = 3;

	public static final String  MONTH_CHARGE_MONEY_LEVEL_1 ="0-1";
	public static final String  MONTH_CHARGE_MONEY_LEVEL_2 ="2-10";
	public static final String  MONTH_CHARGE_MONEY_LEVEL_3 ="11-100";
	public static final String  MONTH_CHARGE_MONEY_LEVEL_4 ="101-1000";
	public static final String  MONTH_CHARGE_MONEY_LEVEL_5 ="1001-10000";
	public static final String  MONTH_CHARGE_MONEY_LEVEL_6 ="10001-100000";
	
	
	public static final String  MONTH_CHARGE_COUNT_LEVEL_1 ="1-1";
	public static final String  MONTH_CHARGE_COUNT_LEVEL_2 ="2-3";
	public static final String  MONTH_CHARGE_COUNT_LEVEL_3 ="4-7";
	public static final String  MONTH_CHARGE_COUNT_LEVEL_4 ="8-15";
	public static final String  MONTH_CHARGE_COUNT_LEVEL_5 ="16-31";
	public static final String  MONTH_CHARGE_COUNT_LEVEL_6 ="32-+++";

	public static final int 	MONTH_CHARGE_MEONEY_LEVEL_1_BOTTOM=0;
	public static final int 	MONTH_CHARGE_MEONEY_LEVEL_1_TOP=10;
	public static final int 	MONTH_CHARGE_MEONEY_LEVEL_2_TOP=100;
	public static final int 	MONTH_CHARGE_MEONEY_LEVEL_3_TOP=1000;
	public static final int 	MONTH_CHARGE_MEONEY_LEVEL_4_TOP=10000;
	public static final int     MONTH_CHARGE_MEONEY_LEVEL_5_TOP=100000;
	
	public static final String  PAY_TYPE_1="打造核心类";//id=125 
	public static final String  PAY_TYPE_2="打造辅助类";//id in (126,127,4505)
	public static final String  PAY_TYPE_3="彩盒";//id in(4877,4878,4879)
	public static final String  PAY_TYPE_4="密码卡";//id=4485
	public static final String  PAY_TYPE_5="道具-vip";//id=4305 
	public static final String  PAY_TYPE_6="道具-其它";// (type=4且id!=4305)
	public static final String  PAY_TYPE_7="靶场";//id in (4918,4925)
	public static final String  PAY_TYPE_8="战队";//id in (5012,5013,5014)
	public static final String  PAY_TYPE_9="熔炼";//id in (4886,4890,4891,4892)
	public static final String  PAY_TYPE_10="装备";//type in (1,2,3); 
	
	
}