package com.pearl.o2o.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.pearl.fcw.gm.service.WSysConfigService;

public class Constants {
	//proxy
	public static final byte[] EMPTY_BYTE_ARRAY						= new byte[0];
	//online award
	public static final String  ONLINEAWARDSTATE                    = "onlineAwardState";
	public static final String  PLAYERREMAINVOTERNUM                = "playerRemainVoterNum";
	public static final int  LEVEL_MINS[]							= {15,30,45};
	public static final int  LEVEL_MINS_VIP[]						= {7,15,22}; // half time of level minutes
	public static final int  DARTLE_LEVEL_NUMS[]					= {15,40,99};
	public static final int[] MEDOL_LIST							= {125,126};
	public static final int[] EXPRANK = { 1000, 2000, 3000 }; 	//在线奖励三档经验
	//strengthen append
	public static final String STRENGTHEN_APPEND					= "strengthenAppend";

	//init num
	public static final int  DEFAULT_PLAYER_CR						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_CR, 0);
	public static final int  DEFAULT_PLAYER_RANK					= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_RANK, 1);
	public static final int  DEFAULT_PLAYER_GP						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_GP, 0);
	public static final int  DEFAULT_PLAYER_V						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_V, 0);
	public static final int  DEFAULT_PLAYER_EXP						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_EXP, 0);
	public static final int  DEFAULT_PLAYER_FIGHTNUM				= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_FIGHTNUM, 2206);
	public static final int  DEFAULT_COST_RELEASE_CHARACTER_SIZE	= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_COST_RELEASE_CHARACTER_SIZE, 1000);
	public static final int  DEFAULT_PLAYER_UNUSABLE_RESOURCE		= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_UNUSABLE_RESOURCE, 100);
	public static final int  DEFAULT_PLAYER_USABLE_RESOURCE			= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_USABLE_RESOURCE, 0);
	public static final int  DEFAULT_TEAM_UNUSABLE_RESOURCE		= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_TEAM_UNUSABLE_RESOURCE, 1000000);
	public static final int  DEFAULT_TEAM_USABLE_RESOURCE			= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_TEAM_USABLE_RESOURCE, 100000);
	public static final String[]  DEFAULT_XUNLEI_VIP_GIFT			= StringUtil.getStringArrayParam(ConfigurationUtil.DEFAULT_XUNLEI_VIP_GIFT, new String[]{"4006","3560"});
	//CACHE TIME
	public static final int	CACHE_TIMEOUT							= StringUtil.getIntParam(ConfigurationUtil.CACHE_TIMEOUT, 15000);//millisecond
	public static final int	CACHE_ITEM_TIMEOUT						= StringUtil.getIntParam(ConfigurationUtil.CACHE_ITEM_TIMEOUT, 3600);
	public static final int	CACHE_TIMEOUT_DAY						= StringUtil.getIntParam(ConfigurationUtil.CACHE_TIMEOUT_DAY, 3600*24);
	public static final int	CACHE_TIMEOUT_HALF_DAY					= StringUtil.getIntParam(ConfigurationUtil.CACHE_TIMEOUT_DAY, 3600*12);
	public static final int CACHE_SYN_INTERVAL_SECONDS  			= StringUtil.getIntParam(ConfigurationUtil.CACHE_SYN_INTERVAL_SECONDS, 60*15);//TODO change later
	//level limit
	//TODO modify after test
	public static final int CREATE_TEAM_LIMIT						= StringUtil.getIntParam(ConfigurationUtil.CREATE_TEAM_LIMIT, 20);
	public static final int JOIN_TEAM_LIMIT							= StringUtil.getIntParam(ConfigurationUtil.JOIN_TEAM_LIMIT, 1);
	public static final int DURABLE_NOTIFY							= StringUtil.getIntParam(ConfigurationUtil.DURABLE_NOTIFY, 40);
	public static final int DEFAULT_DB_NUM							= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_DB_NUM, 10000);
	public static final int DEFAULT_DB_SIZE							= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_DB_SIZE, 400);
	//	public static final int DEFAULT_MAX_DB_TABLES					= 1000;  //最大分表数
	public static final int SWITCH_SYNTODB							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_SYNTODB, 1);
	public static final int SWITCH_SEND_TEAM_RES					= StringUtil.getIntParam(ConfigurationUtil.SWITCH_SEND_TEAM_RES, 1);
	public static final int SWITCH_DAILYNUM							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_DAILYNUM, 1);
	public static final int SWITCH_CRT_PSN_TOP							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_CRT_PSN_TOP, 1);
	public static final int SWITCH_TOPACTIVITY							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_TOPACTIVITY, 1);
	public static final int SWITCH_COMPETEBUY 						= StringUtil.getIntParam(ConfigurationUtil.SWITCH_COMPETEBUY, 1);
	public static final int SWITCH_COMPETEBUYSENDTASK               =StringUtil.getIntParam(ConfigurationUtil.SWITCH_COMPETEBUYSENDTASK,1);
	public static final int AUTO_UNEQUIP							= StringUtil.getIntParam(ConfigurationUtil.AUTO_UNEQUIP, 3);
	public static final int AUTO_DESTORY							= StringUtil.getIntParam(ConfigurationUtil.AUTO_DESTORY, 7);
	//analyser project
	public static final int ADD_GPOINT 								= StringUtil.getIntParam(ConfigurationUtil.SWITCH_IS_ADD_GPOINT, 1);
	public static final int SWITCH_ANALYSER_IS_ON	                = StringUtil.getIntParam(ConfigurationUtil.SWITCH_ANALYSER, 1);

	public static final int TEAM_FIGHT_RES_PREWEEK	                = StringUtil.getIntParam(ConfigurationUtil.TEAM_FIGHT_RES_PREWEEK, 1);
	public static final int TEAM_RES_TOP	                = StringUtil.getIntParam(ConfigurationUtil.TEAM_RES_TOP, 1);

	public static final int CLEAN_OVERDUE	                = StringUtil.getIntParam(ConfigurationUtil.CLEAN_OVERDUE, 0);

	public static final String DEFAULT_CHARACTERS = "1,2,3,4,5,6";
	public static final String DEFAULT_CHARACTERS_SELECT = "2,3,4";

	//memcached来缓存的前缀
	public static final String ATT_TEAM_TODAY_RES    				= "attTeam_today_Res_";
	public static final String ATT_TEAM_RES_RATIO_STATUS    		= "attTeam_Res_ratio_status_";
	public static final String PLAYER_TODAY_RES    					= "player_today_Res_";
	public static final String PLAYER_RES_RATIO_STATUS    			= "player_Res_ratio_status_";
	public static final String SCMS_BOX_COUNT = "scms_box_count_";//生存模式开了几次黄金宝箱
	public static final String MATE_AWARD_COUNT = "mate_award_count_";//匹配团竞的物品记录
	public static final String MATE_CHIP_COUNT = "mate_chip_count_";//匹配团竞的物品记录
	//online flag 标志位，上次退出时已经达到领取条件未领取
	public static final String FLAG_NOT_GET_AWARD					= "1";
	public static final String FLAGE_OTHER							= "0";
	//online nextTime
	public static final String LEVEL_IS_OUT_LINE					= "0";
	public static final int  ONLINE_AWARD_LEVEL_ONE					= 1;

	public static final int CHANNEL_MAX_DEFAULT						= 1000;
	//装备最大耐久度
	public static final float	MAX_DURABLE							= 100f;
	public static final int  DEFAULT_BOSS_CHARACTER_ID				= 120;//boss1

	public static  enum BOSS3CHARACTERID{
		DEFAULT_BOSS31_CHARACTER_ID(121),DEFAULT_BOSS32_CHARACTER_ID(122),DEFAULT_BOSS33_CHARACTER_ID(123),DEFAULT_BOSS34_CHARACTER_ID(124),DEFAULT_BOSS35_CHARACTER_ID(125);
		private int value;

		public int getValue(){
			return this.value;
		}

		BOSS3CHARACTERID(int value){
			this.value = value;
		}
	}

	public static final int  DEFAULT_BOSS2_CHARACTER_ID				= 100;//boss pve
	public static final int  DEFAULT_BOSS3_CHARACTER_ID				= 101;//boss pve
	public static final int  DEFAULT_BOSS4_CHARACTER_ID				= 102;//boss pve
	public static final int  DEFAULT_BOSS5_CHARACTER_ID				= 103;//boss pve
	public static final int FIRST_BIOCHEMICAL_CHARACTER_ID = 42;
	public static final int NORMAL_BIOCHEMICAL_CHARACTER_ID = 41;
	public static final int FINAL_BIOCHEMICAL_CHARACTER_ID = 40;
	public static final int NEW_BIOCHEMICAL_ONK_CHARACTER_ID=43;
	public static final int NEW_BIOCHEMICAL_HW_CHARACTER_ID=44;
	public static final int NEW_BIOCHEMICAL_HK_CHARACTER_ID=45;

	public static final int NEW_CHARACTER_FOR_AMAZINGWAR=128;
	public static final int TEAM_ZYZDZ_TANK1_CHARACTER=130;
	public static final int TEAM_ZYZDZ_TANK2_CHARACTER=131;
	public static final int TEAM_ZYZDZ_TANK3_CHARACTER=132;
	public static final int RISK_ISLET_GHOST_CHARACTER=134;

	public static final int MAX_CHARACTER_SIZE    					= 7;
	public static final int MAX_SELECT_CHARACTER_SIZE = 3;
	//NOSQL
	public static  final int PLAYER_NEWS_MAX_SIZE 					= 50;
	public static  final int TEAM_HISTORY_MAX_SIZE 					= 50;
	public static  final int OUT_BOX_MAX_SIZE 						= 50;
	public static  final int TEAM_RECORD_MAX_SIZE 					= 50;
	public static  final long PLAYER_NEWS_EXPIRE 					= 7 * 24 * 3600 ;// a week
	public static  final int PLAYER_RATE_HISTORY_SIZE 				= 5;
	public static  final int PLAYER_RECENTPLAYER_SIZE 				= 15;
	public static  final int PLAYER_RANDOM_SIZE 					= 2000;
	public static  final int PLAYER_RANDOM_TIME 					= 600000;

	public static final int  DEFAULT_BASEMAPPINGBEAB_EXPR          	= 0;
	public static final int  DEFAULT_BASEMAPPINGBEAB_SYNINTERVAL    = 0;
	public static final int  DEFAULT_USERID             			= 0;
	public static final String CHARACTER_LIST 						= "CharacterList";
	public static final String CHARACTER_OBJECT 					= "Character";

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
	public static final String TEAM_LOCK_TYPE						= "TEAMLOCK";
	public static final String STRING_TYPE							= "S";
	public static final String XUNLEI_LOG_WRITE 					= "XunleiLogWrite";
	public static final String SYN_CACHE_DB 						= "SynCacheDB";
	public static final String SYN_TEAM_CHECK 						= "SynTeamCheck";
	public static final String DAILY_NUM_REDIS 						= "DailyNumRedis";
	public static final String WEEKLY_SEND_TEAM_RES 				= "weekSendTeamRes";
	//------------------------
	public static final Date EXPIREDATEOBJECT 						= new Date(0);
	public static final String ENCODING								= "UTF-8";
	public static final String SYSTEM_NAME							= CommonUtil.messageFormatI18N("1");
	public static final String SYSTEM_SPEAKER						= CommonUtil.messageFormatI18N("2");
	public static final String BOOLEAN_YES							= "Y";
	public static final String BOOLEAN_NO							= "N";
	public static final String DLB 									= CommonUtil.messageFormatI18N("3");
	public static final String XLB 									= CommonUtil.messageFormatI18N("4");
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

	public static final int DEFAULT_WEAPON_TYPE = 1;//武器
	public static final int DEFAULT_COSTUME_TYPE = 2;//角色
	public static final int DEFAULT_PART_TYPE = 3;//配饰
	public static final int DEFAULT_ITEM_TYPE = 4;//道具
	public static final int DEFAULT_MATERIAL_TYPE = 5;//素材
	public static final int DEFAULT_BLUEPRINT_TYPE = 6;//蓝图
	public static final int DEFAULT_PACKAGE_TYPE = 7;//大礼包
	public static final int DEFAULT_OPEN_TYPE = 8;//打开类
	public static final int DEFAULT_ZYZDZ_TYPE = 9;//资源争夺战类
	public static final int DEFAULT_IS_POPULAR = 10;//推荐类，只在商城分类中使用，不作为实际的类型

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

	public static final int DEFAULT_VIP_STRENGTH_BUFF		=83;

	//月卡 i_buff_id
	public static final int DEFAULT_ON_CARD_BUFF		= 84;
	//天天礼 i_buff_id
	public static final int DEFAULT_EVERY_DAY_GIFT_BUFF		= 85;
	//VIP新年充值天天礼包 i_buff_id
	public static final int NWE_YEAR_EVERY_DAY_GIFT_BUFF	= 86;

	//team item
	public static final int DEFAULT_TEAM_IID       			= 7;
//	public static final int DEFAULT_WEAPON_PACK_SIZE    	= 2;
//	public static final int MAX_WEAPON_PACK_SIZE    		= 5;
	//TODO CharacterSize
	public static final int DEFAULT_CHARACTER_SIZE = 3;//must not change
	public static final int DEFAULT_SEQ_SIZE    			= 4;
	public static final int DEFAULT_COSTUME_SEQ_SIZE    	= 3;
	//pack iid
	public static final int DEFAULT_PACK_IID       			= 5;
	public static final int DEFAULT_MEDAL_IID				= 17;

	//mtype
	public static final int DEFAULT_MEDAL_MTYPE				= 6;

	//pageSize
	public static final int DEFAULT_A_PAGE_SIZE				=28;
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
	public static final int NUM_FIVE             		= 5;

	public static final int NUM_NEG_TWO					= -2;
	public static final int TRY_LOCK_COUNT            = 2;
	public static final int RANDOM_NUM_LOCK_TIME        = 500;//ms
	public static final int STAGE_CLEAR_LOCK_TIME       = 500;//ms
	public static final double FUNDUS_NUM              	= 7/12d;
	//about item
	public static final int ITEM_BULLETPROOF_IBUFFID    = 21;

	public static final int TYPE_NUM                	= 10;//The max number of SYS_ITEM.TYPE
	public static final int SUB_TYPE_NUM                = 9;
	public static final int SYS_CHARACTER_NUM           = 10;
	public static final int HIGH_RARE_LEVEL				= 40;
	public static final int BILL_BOARD_RARE_LEVEL		= 60;
	public static final int[] SHOOT_HIGH_RARE_LEVEL		= {50,50};
	public static final int BRONZE_HIGH_RARE_LEVEL		= 10;
	public static final int SILVER_HIGH_RARE_LEVEL		= 40;
	public static final int GOLD_HIGH_RARE_LEVEL		= 40;
	public static final int MAGIC_BOX_HIGH_RARE_LEVEL	= 40;
	//generate a fake system item id for purchase character
	public static final int SYS_ITEM_CHARACTER_ID  		= 99999999;
	//keys in memcache
	public static final String STRENGTH_FAIL_TIME		= "T.S.F";
	public static final String GET_PLAYER_BY_USER_ID 	= "P.B";
	public static final String GET_SERVER_LIST			= "S.L";
	public static final String GET_RANK_LIST			= "R.L";
	public static final String GET_SYSCHARACTER_LIST	= "SC.L";
	public static final String GET_SYSBIOCHARACTER_LIST	= "SBC.L";
	public static final String TEAM_LIST				= "TL";
	public static final String GET_SYSTEMITEM_LIST		= "SI.L";
	public static final String SYSTEMITEM				= "SI";
	public static final String LUCKYPACKAGE				= "L.P";
	public static final String LUCKYPACKAGERANDOMNAMES	= "L.P.R";
	public static final String PLAYER					= "P";
	public static final String PLAYER_INFO				= "P_I";
	public static final String ONLINEAWARD				= "OA";
	public static final String ONLINEAWARD_LIST			= "OA.L";
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
	public static final String TEAM_ITEM				= "TeamItem";
	public static final String SYS_CFG 					= "SysConf";
	public static final String SYSITEM_LIST = "SIL";//所有的sysitem
	public static final String PAYMENT_LIST 			= "P.L";
	public static final String LEVEL_LIST 				= "LL";
	public static final String TEAM_LEVEL_LIST 			= "TLL";
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
	public static final String FCM_TIME = "FCM.time";     //防沉迷时间
	public static final String SECOND_PASSWORD_IS_ENTER = "SPW.IE";     //是否输入过二级密码

	public static final String PLAYERITEMSTOPLIMITCGOLD = "PILCG";     // 地图活动中，道具C币
	public static final String PLAYERITEMSTOPLIMITMERIT = "PILMT";     // 地图活动中，道具勋章
	public static final String PLAYERITEMSTOPLIMITSTRENGTH = "PILSG";     // 地图活动中，道具部件
	public static final String PLAYERITEMSTOPLIMITBOXA = "PILBA";     // 地图活动中，道具BoxA
	public static final String PLAYERITEMSTOPLIMITBOXB = "PILBB";     // 地图活动中，道具BoxB
	public static final String PLAYERITEMSTOPLIMITBOXC = "PILBC";     // 地图活动中，道具BoxC
	public static final String PLAYERITEMSTOPLIMITBOXD = "PILBD";     // 地图活动中，道具BoxD
	public static final int DEFAULT_PART_VIEW_SIZE 		= 4;
	public static final int DEFAULT_PAGE_SIZE 			= 8;
	public static final int FRIEND_PAGE_SIZE			= 50;
	public static final int TEAM_PAGE_SIZE				= 6;
	public static final int TOP_PAGE_SIZE				= 10;
	public static final int PAGE_SIZE_MESSAGE_ITEM[]	= {12,10,20,20,20,32,32,32};
	public static final int PAGE_SIZE[]					= {8,8,16,28,28,28,28,28};
	public static final int STRENGTH_PAGE_SIZE[]		= {8,8,16,16,16,32,32,32};
	public static final int STRENGTH_MUST_TIMES[]		= {1,1,1,1,2,3,6,10,15,20};
	//wid
	public static final int WEAPON_PISTOL_ID = 1;//手枪
	public static final int WEAPON_SUBMACHINE_ID = 2;//冲锋	枪
	public static final int WEAPON_RIFFLE_ID = 3;//自动步枪
	public static final int WEAPON_SNIPER_GUN_ID = 4;//狙击枪
	public static final int WEAPON_SHOT_GUN_ID = 5;//散弹枪
	public static final int WEAPON_MACHINE_GUN_ID = 6;//机枪
	public static final int WEAPON_MINI_GUN_ID = 7;//mini枪
	public static final int WEAPON_DOUBLE_PISTOL_ID = 8;//双枪
	public static final int WEAPON_MEDITNEEDLE_GUN_ID = 9;//MeditNeedleGun针筒枪
	//	public static final int WEAPON_MED_GUN_ID			= 12;//医疗塔
	public static final int WEAPON_JQT_GUN_ID = 13;//机枪塔
	public static final int WEAPON_SIGNAL_GUN_ID = 14;//信号枪
	public static final int WEAPON_CURE_GUN_ID = 15;//治疗枪
	public static final int WEAPON_FLAME_GUN_ID = 16;//喷火枪
	public static final int WEAPON_ROCKET_LUNCHER_ID = 17;//火箭筒
	public static final int WEAPON_BOW_ID = 18;//弓
	public static final int WEAPON_KNIFE_ID = 19;//刀
	public static final int WEAPON_THROW_ID				= 20;
	public static final int WEAPON_GRENADE_ID			= 20;
	public static final int WEAPON_FLASH_ID				= 21;
	public static final int WEAPON_SMOKE_ID				= 22;
	public static final int WEAPON_BOSSPVE_ID			= 10;
	public static final int WEAPON_AMMO_GRENADE_ID = 42;//箭
	public static final int WEAPON_ARROW_ID = 45;//箭
	public static final int WEAPON_WAR_DRUM				= 47;
	public static final int WEAPON_MILK					= 48;
	public static final int WEAPON_GLASS				= 49;
	public static final int WEAPON_BIOCHEMICAL			= 51;
	public static final int WEAPON_BIOCHEMICAL_AVO		= 52;
	public static final int WEAPON_ZYZDZ_EDIT_DEFAULT = 54;//默认资源争夺战 编辑模式带入武器

	public static final int WEAPON_TEAM_ELEMENTARY_TOWER = 100;//初级防御塔
	public static final int WEAPON_TEAM_MIDDLE_TOWER = 101;//中级防御塔
	public static final int WEAPON_TEAM_ADVANCED_TOWER = 102;//高级防御塔
	public static final int WEAPON_ZYZDZ_OIL_POT         =103;

	public static final int ITEM_TYPE_WEAPON			= 1;
	public static final int ITEM_TYPE_PART				= 3;

	public static final int ITEM_UNIT_TYPE_TIMEBASE		= 2;

	public static final String ACTION_DELETE			= "delete";
	public static final String ACTION_FRIEND_ADD		= "add_friend";
	public static final String ACTION_FRIEND_BLACK_ADD	= "add_black";
	public static final String ACTION_GROUP_ADD			= "add_group";
	public static final String ACTION_GROUP_DELETE		= "delete_group";

	public static final int GP_PAY						= 1;//gp
	public static final int CR_PAY						= 2;//lei point
	public static final int V_PAY						= 3;//voucher
	public static final int MD_PAY						= 4;//medal
	public static final int RES_PAY = 5;//个人黑晶石
	public static final int RES_PAY_TEAM = 6;//团队黑晶石
	public static final int RES_DIS_PAY = 7;//个人黑原石
	public static final int RES_DIS_PAY_TEAM = 8;//团队黑原石
	public static final int REVIVE_COIN_PAY = 9; //复活币消费
	public static final int VIP_EXP_PAY = 10;//VIP 经验条消费
	public static final int A_CHIP_PAY = 101;//A币消费
	public static final int B_CHIP_PAY = 102;//B碎片消费
	public static final int C_CHIP_PAY = 103;//C碎片消费
	public static final int OFF_CHIP_PAY = 104;//过期碎片消费

	public static final String GP_PAY_STRING			= "GP";
	public static final String CR_PAY_STRING			= "CR";
	public static final String EXPIRE_DATE				= "2020-10-10";

	public static final int SUBTYPE_ARRAY[]				= {8,9,7,5,1};
	public static final String MSG_NOTICE				= "/sys";
	public static final String MSG_INFO					= "/info";
	public static final String MSG_ONLINE				= "/online";
	public static final String MSG_DLB					= "/dlb";
	public static final String MSG_XLB					= "/xlb";
	public static final String MSG_CMD					= "/cmd";
	public static final String MSG_GROUP					= "/group";
	public static final String MSG_NBA = "/nba";//炫耀大喇叭
	public static final int WEAPON_DURABLE_COST	        = 1;
	//SIDE
	public static final int SIDE_RED 					= 0;
	public static final int SIDE_BLUE 					= 1;
	public static final int SIDE_OBSERVER 				= 2;

	public static final int ACHIEVEMENT_BASE 				= 1;
	public static final int ACHIEVEMENT_HUOYAN				= 2;

	//lucky package
	public static final int PACKAGE_LEVEL_ONE				= 1;
	public static final int PACKAGE_LEVEL_TWO				= 2;
	public static final int PACKAGE_LEVEL_THREE				= 3;
	public static final int PACKAGE_TYPE_FIX				= 1;
	public static final int PACKAGE_TYPE_RANDOM				= 2;
	public static final int PACKAGE_LEVEL_NUM				= 3;

	//NOSQL KEY
	public static final String TEAMTOP_KEY_PREFIX 	= "tmtppk";
	public static final String PLAYERTOP_ROWNUM_KEY_PREFIX 	= "pt:rn:";
	public static final String PLAYERTOP_RANK_KEY_PREFIX 	= "pt:r:";
	public static final String PLAYERTOP_REAL_RANK_KEY_PREFIX 	= "pt:r:r:";
	public static final String PLAYERTOP_FIGHTNUM_RANK_KEY_PREFIX 	= "pt:fn:r:";
	public static final String PLAYERTOP_FIGHTNUM_REAL_RANK_KEY_PREFIX 	= "pt:fn:r:r:";
	public static final String PLAYERTOP_FST_LST_RCD_KEY_PREFIX = "pt:rcd:";//记录第一和最后计入排名的数值
	public static final String PLAYERTOP_WEAPON_PREFIX 		= "playerTop:weaponActivity:";
	public static final String TEAMRECORD_PREFIX 			= "TeamRecord:";
	public static final String DAILY_MISSION_PREFIX 		= "dm:";//DailyMission
	public static final String WEEK_MISSION_PREFIX 			= "wm:";//WeekMission
	public static final String MONTH_MISSION_PREFIX 		= "mm:";//MonthMission
	public static final String DAILY_GAME_PREFIX 			= "dg:";//MonthMission
	public static final String DAILY_CHECK_PREFIX 			="dc:";
	public static final String DAILY_CHECK_TIMES_PREFIX		="dct:";
	public static final String DAILY_GIFT_DATE_PREFIX 		="dgd:";
	public static final String VIP_DAILY_CHECK_TIMES_PREFIX		="vdct:";
	public static final String TEAM_SALARY_DATE_PREFIX = "ts:";//战队薪水
	public static final String CHECK_ITEM_PREFIX 			="ci:";
	public static final String YESTODAY_DATA	 			= "SD:";//StayData
	public static final String DAILY_RANDOM_NUM_KEY			="DRNK";//dailyRandomNum
	public static final String DAILY_RANDOM_NUM_MCC_KEY			="drdnmc";
	public static final String INIT_PLAYER_TOP_MCC_KEY			="iptmc";
	public static final String INIT_TEAM_TOP_MCC_KEY			="ittmc";
	public static final String CLN_PLAYER_TOP_MCC_KEY			="cptmc";
	public static final String DAILY_PLAYER_NUM_PREFIX		="dpn:";//playerDailyNum
	public static final String DAILY_PLAYER_TOM_NUM_PREFIX		="dptn:";//playerDailyTomorrowNum
	public static final String  IS_DAILY_GUS_AWARD_PREFIX	="idga:";// is player get award when guess num
	public static final String  IS_INIT_PLAYER_NUM_PREFIX	="iipn:";// is init player num

	public static final String  UPDATESETKEY				="pj_dirtyMappingBeanKeys_update_set";// is init player num
	public static final String  DUMPSETKEY					="pj_dirtyMappingBeanKeys_dump_set";// is init player num

	//all key for different top, except weapon record
    public static final String[] PLAYERTOP_TYPE = { "kCommon", "kTeam", "kZombie", "kBanner", "kTeamDead", "kExplode", "kCr", "kGp", "kGrenade", "kDrop", "kGun", "kReequip", "kFlower", "kDead",
            "kTreasure" };

	public static enum TEAM_TOP_TYPE{
		//1.战绩  2.等级  3.热门  4.最新    5.上周资源争夺排名  6.当前资源争夺排名
		BATTLERESULT(1),RANK(2),HOT(3),NEW(4),RESOURCE(5),NOW_RES(6);
		private TEAM_TOP_TYPE(int value) {
			this.value =value;
		}

		private int value;

		public int getValue(){
			return value;
		}

		public TEAM_TOP_TYPE getTypeByValue(int value){
			for(TEAM_TOP_TYPE type : TEAM_TOP_TYPE.values() ){
				if (type.value == value) {
					return type;
				}
			}
			return null;
		}
	}

	public static final String[] PERSONAL_TOP_TYPE = {"kFightNum","kScore","kAssist","kKillDead","kWinRate"};
	public static final int[] CHARACTER_IDS = {0,1,2,3,4,5,6,7};
	public static final int CURRENT_RANK_NUM = 10000;
	public static final int REAL_TOP_RANK_NUM = 15000;
	public static final int MAX_CACHE_REAL_TOP_RANK_NUM = 20000;
	public static final int RANDOM_RANK_NUM = 1000;

	public static final int TEAM_TOP_NUM = 800;
	public static final int TEAM_TOP_ZYZDZ_NUM = 20;
	public static final int TEAM_TOP_PAGESIZE = 8;

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

    /**
	 * 游戏模式
	 * @author zhaolianming
	 */
	public static enum GAMETYPE{
		TEAM(0) //      团队竞技    0
		, TARGET(1) //      占点  1
		, DELIVER(2) //     推车  2
		, NEWTRAIN(3) //        新手关 3
		, DESTROY(4) //     歼灭  4
        , HITBOSS(5) //     BOSS    5
		, KNIFE(6) //       刀战  6
		, BLAST(7) //       爆破  7
		, STREETBOY(8) //       英雄  8
		, BIOCHEMICAL(9) //     老生化 9
		, BOSS2(10) //      审判  10
		, BIOCHEMICAL2(11) //       生化感染    11
		, HITBOSS2(12) //       BOSS末日进化    12
		, AMAZINGWAR(13) //     道具战 13
		, TEAMEDIT(14) //       资源争夺战编辑模式   14
		, TEAMZYZDZ(15) //      资源争夺战   15
		, AGRAVITY(16) //       勇者攀登        16
		, AGRAVITY2(17) //      圣诞跳跳乐   17
		, RISK_ISLET(18) //     冒险岛（生存模式）   18
        , BOSS_PVE(19) //       BOSS PVE        19
		, TEAM_MATCH(20) //          匹配模式的团队竞技20
		, TEAM_MATCH_100(100) //          匹配模式的团队竞技100
		, DESTROY_MATCH(104) //           匹配模式的歼灭104
		, BIOCHEMICAL2_MATCH(111); //     匹配模式的生化感染111
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

        public int getValue() {
            return value;
        }
    }

    /**
	 * 游戏模式
	 * @author zhaolianming
	 */
    public static enum MATCH_TYPE {
		QW(6) //        群雄争霸        6
		, SOLO(7) //        个人功勋匹配  7
		, LEAGUE(8); //     联赛      8
        private int value;

        MATCH_TYPE(int value) {
            this.value = value;
        }

		public int getValue(){
			return value;
		}
	}

	public static enum ROOMITEM {
		// be aware the sequence
		GP(9),EXP(10),TEAM_BOXING(11),TEAM_CHALLENGE(12),BOXING(13),CHALLENGE(14);
		private int value;

		ROOMITEM(int value){
			this.value = value;
		}

		public static ROOMITEM getTypeByValue(int value){
			for(ROOMITEM type : ROOMITEM.values() ){
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

	public enum ACTION_M{
		MAP1_MODEL1(1), //金色农场的占点模式战役
		MAP2_MODEL1(2),		//风车小镇的占点模式战役
		MAP3_MODEL1(3),		//乡间伐木场占点模式战役
		MAP2_MODEL2(4),		//风车小镇的团战模式战役
		MAP3_MODEL2(5),		//乡间伐木场团战模式战役
		MAP4_MODEL2(6),		//盛夏农场的团战模式战役
		MAP5_MODEL3(7),		//法老神殿的推车模式战役
		MAP6_MODEL3(8),		//神庙遗迹的推车模式战役

		MAP6_MODEL4(9), //神庙遗迹的歼灭模式战役
		MAP4_MODEL4(10),		//盛夏农场的歼灭模式战役
		MAP1_MODEL5(22),		//阴霾教堂的BOSS模式
		MAP1_MODEL7(23),		//阿拉伯古城的爆破模式
		MAP1_MODEL8(24),		//九龙街区的英雄模式
		MAP1_MODEL9(25),		//生化研究所的生化模式

		WIN(11), //赢得胜利
		GENERALKILL(12),		//击杀敌人
		AWARDKILL(13),		//赏金击杀
		REVENGEKILL(14),		//复仇击杀
		CLOSEKILL(15),		//近身击杀
		DOUBLEDKILL(16),		//暴击击杀
		CONTINUEKILL(17),		//完成连杀2人
		HELPKILL(18),		//助攻
		MVP(19),//MVP
		MAXD(20),		//最大破坏力1200以上
		COMPLETE(21),//MVP
		MAP32_MODEL0(26),		//激战麦田团战
		MAP33_MODEL0(27),		//埃及古墓团战
		MAP34_MODEL3(28), //金色农场的推车模式战役
		MAP35_MODEL13(29), //地心遗迹的道具战模式战役
		MAP36_MODEL13(30), //北斗导航战的道具战模式战役
		MAP39_MODEL0(39),		//勇者攀登，糖果星球激战
		MAP40_MODEL0(40), //冲锋之王每日X战
		MAP41_MODEL0(41), //冲锋之王每日X胜
		MAP42_MODEL0(42),        //冲锋之王每周X胜
		MAP43_MODEL0(43),        //累积伤害（匹配）
		MAP44_MODEL0(44),        //拾取补给（匹配）
		MAP45_MODEL0(45);//累积积分（匹配）
		private int value;

		ACTION_M(int value){
			this.value = value;
		}

		public int getValue(){
			return this.value;
		}
	}

	public enum ACTION{
		MILESTONE(0), //里程碑成就
		LEVELUP(1),		//升级成就
		NEWPLAYER(2),		//新手训练成就
		GENERALKILL(4),		//通用击杀
		CONTROLKILL(5),		//控制击杀
		REVENGEKILL(6),		//复仇击杀
		CHARCTERKILL(9),		//击杀指定职业
		BLOODBAG(7),		//吃血包
		BULLETBAG(8),		//吃弹药包
		DALABA(11),		//刷大喇叭
		FRIENDS(12),		//好友数量
		USECR(13),		//消费雷点
		GIFTCR(14),		//赠送雷点
		FIRSTKILL(15),		//第一滴血
		FIRSTDEAD(16),		//第一滴被血
		CONTINUEKILL(17),		//连杀
		GETWEAPON(21),		//获得主武器
		GETKNIFE(22),		//获得近战武器
		GETCOSTUME(23),		//获得衣服
		KNIFEKILL(24),		//近战武器击杀
		MAXDAMAGE(25),		//最大伤害量
		ASSISTNUM(26),		//助攻数
		BOOSTKILL(27),		//暴击击杀
		MAXALIVE(28),		//最大存活时间
		MVPNUM(30),		//获得mvp次数
		HEADSHOT(31),		//爆头击杀
		GRENADEKILL(32),		//雷杀
		SUSTAINKILL(33),		//余焰击杀
		FINISHGAME(34),		//完成比赛
		HEALTHNUM(35),		//治疗量
		HEALTHMVP(36),//mvp
		HEALTHMAX(37),		//最大治疗量
		GAMEWIN(3);//赢得游戏

		private int value;

		ACTION(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum COMBINE_TYPE{
		STRENGTH(1),CONVERT(2),SLOT(3),INSERT(4);
		private int value;

		COMBINE_TYPE(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}

	}

	public enum COMBINE_STRENGTH_ACTION{
		STRENGTH_WEAPEON_BLUE(38), //强化武器到绿色
		STRENGTH_WEAPEON_GREEN(39),		//强化武器到蓝色
		STRENGTH_WEAPEON_PURPLE(40),		//强化武器到紫色
		STRENGTH_COSTUME_BLUE(41),		//强化衣服到绿色
		STRENGTH_COSTUME_GREEN(42),		//强化衣服到蓝色
		STRENGTH_COSTUME_PURPLE(43),		//强化衣服到紫色
		STRENGTH_PART_BLUE(44),		//强化饰品到绿色
		STRENGTH_PART_GREEN(45),		//强化饰品到蓝色
		STRENGTH_PART_PURPLE(46),		//强化饰品到紫色
		STRENGTH_WEAPEON_TIME(47),		//强化武器成功到一定次数
		STRENGTH_COSTUME_TIME(48),		//强化服装成功到一定次数
		STRENGTH_PART_TIME(49),		//强化饰品成功到一定次数
		STRENGTH_WEAPEON_WITH_SAFE_TIME(50),		//使用安定装置强化武器成功到一定次数
		STRENGTH_COSTUME_WITH_SAFE_TIME(51),		//使用安定装置强化服装成功到一定次数
		STRENGTH_PART_WITH_SAFE_TIME(52),		//使用安定装置强化饰品成功到一定次数
		STRENGTH_WEAPEON_WITH_STABLE_TIME(53),		//使用增幅装置强化武器成功到一定次数
		STRENGTH_COSTUME_WITH_STABLE_TIME(54),		//使用增幅装置强化服装成功到一定次数
		STRENGTH_PART_WITH_STABLE_TIME(55),		//使用增幅装置强化饰品成功到一定次数
		STRENGTH_WEAPEON_WITH_SAFE_STABLE_TIME(56),		//使用安定装置和增幅装置强化武器成功到一定次数
		STRENGTH_COSTUME_WITH_SAFE_STABLE_TIME(57),		//使用安定装置和增幅装置强化服装成功到一定次数
		STRENGTH_PART_WITH_SAFE_STABLE_TIME(58);//使用安定装置和增幅装置强化饰品成功到一定次数

		private int value;

		COMBINE_STRENGTH_ACTION(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum COMBINE_CONVERT_ACTION{
		CONVERT_COMMON_WEAPEON_TIME(68), //转换普通武器成功次数
		CONVERT_EXCELLENT_WEAPEON_TIME(69),		//转换精良武器成功次数
		CONVERT_COMMON_COSTUME_TIME(70),		//转换普通衣服成功次数
		CONVERT_EXCELLENT_COSTUME_TIME(71),		//转换精良衣服成功次数
		CONVERT_COMMON_PART_TIME(72),		//转换普通配饰成功次数
		CONVERT_EXCELLENT_PART_TIME(73);//转换精良配饰成功次数

		private int value;

		COMBINE_CONVERT_ACTION(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum COMBINE_SLOT_ACTION{
		SLOT_WEAPEON_TIME(59), //武器打孔成功次数
		SLOT_COSTUME_TIME(60),		//衣服打孔成功次数
		SLOT_PART_TIME(61);//配饰打孔成功次数

		private int value;

		COMBINE_SLOT_ACTION(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum COMBINE_INSERT_ACTION{
		INSERT_WEAPEON_TWO(62), //镶嵌武器两个属性石
		INSERT_WEAPEON_THREE(63),		//镶嵌武器三个属性石
		INSERT_COSTUME_TWO(64),		//镶嵌衣服两个属性石
		INSERT_COSTUME_THREE(65),		//镶嵌衣服三个属性石
		INSERT_PART_TWO(66),		//镶嵌饰品两个属性石
		INSERT_PART_THREE(67);//镶嵌饰品三个属性石

		private int value;

		COMBINE_INSERT_ACTION(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	//成就数量计算方式0,累计，1，单次最大数
	public enum STATTYPE{
		TOTAL(0),MAX(1);
		private int value;

		STATTYPE(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum ACTIVITY_TYPE{
		TOTAL(0),MAX(1);
		private int value;

		ACTIVITY_TYPE(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum ACTIVITY_AWARD{
		SYSITEM(0),MONEY(1);
		private int value;

		ACTIVITY_AWARD(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public enum ACTION_ACTIVITY{
        LEVEL_ACTIVITY(1), LOGIN_ACTIVITY(2), RANDOM_ACTIVITY(3), ONLINE_ACTIVITY(4), ACHIEVEMENT_ACTIVITY(5), KILL_ACTIVITY(6), TASK_ACTIVITY(8), BOSS_KILL_ACTIVITY(7), KILL_BOSS_ACTIVITY(10), DISCOUNT_ACTIVITY(
                9), HOUR2HOUR_ACTIVITY(11), TOP_PLAYER_ACTIVITY(12), IGNORE_DEAD(13), IGNORE_LOSE(14), LEVEL_FIRST_LOGIN(15), STRENGTH_NON_LOSE(16), STRENGTH_ADD(17), CHARGE_FC(18), KILL_BIOCHEMICAL(
				19), //生化
        PAY_FC(20), STRENGTH_TO_RANK(21), OPEN_LUCKYPACKAGE(22), STRENGTH_MUST(23), KILL_BOSS2_ACTIVITY(24), TEAM_BATTLE_ADD(25), ENHANCE_MAX_STRENGTH_LEVEL(26), TEAM_COMBAT_FINISH(27), START_END_LEVEL_FIRST_LOGIN(
                31);
		private int value;

		ACTION_ACTIVITY(int value){
			this.value = value;
		}

		public int getValue(){
			return this.value;
		}
	}

	//int[gametype][index]
    public static double[][] numParam = { { 0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5, }, { 0, 14, 1.2, 10, 0, 70, 1.2, 9, 150, 8, 1.5 }, { 0, 14, 1.2, 10, 0, 70, 1.2, 7, 150, 8, 1.5 }, {

    },
			{//歼灭
            0, 14, 2.5, 10, 0, 70, 2.5, 7, 150, 8, 1.5 }, { 0, 14, 2, 10, 0, 70, 2, 7, 150, 8, 1.5 }, { 0, 14, 1.5, 10, 0, 70, 1.6, 7, 150, 8, 1.5 }, { 0, 14, 1.5, 10, 0, 70, 1.6, 7, 150, 8, 1.5 },
            { 0, 14, 1.5, 10, 0, 70, 1.6, 7, 150, 8, 1.5 }, { 0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//boss2
            0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//bio2
            0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//boss3
			0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//道具战
			0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//资源争夺战编辑模式

			}, {//资源争夺战

			}, {//勇者攀登		[结算分数跟团战一致]
			0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//圣诞跳跳乐  [结算分数跟团战一致]
			0, 14, 1.5, 10, 0, 70, 1.5, 7, 150, 8, 1.5 }, {//冒险岛        [结算分数跟歼灭一致]
            0, 14, 2.5, 10, 0, 70, 2.5, 7, 150, 8, 1.5 } };
	//double[gametype]
	public static double[] gameTypeNum = { 1.1, 1.3, 1.3, 1, 0.6, 1.9, 1.2, 0.6, 0.7, 1.1, 0.7, 1.0, 1.0, 1.2, 1.1, 1.1, 1.1/*勇者攀登**/, 1.1/*跳跳乐*/, 0.6/*冒险岛*/, };
	//default item
	public static int[] defalutItems = { 4671, 6401 };
	//int[characterid][sid]
	//FCW 玩家初始武器
	//    public static int[][] weaponPack = { { 1132, 1128, 1136, 4663, }, { 1233, 1230, 1236, 4664, }, { 1332, 1330, 1335, 4665, }, { 1432, 1430, 1435, 1438, }, { 1532, 1531, 1535, 4667, },
	//            { 17, 16, 9, 4668, }, { 5244, 5245, 5246, 5250, }, };
	public static int[][] weaponPack = { { 1132, 1128, 1136, 4663, }, { 1233, 1230, 1236, 4664, }, { 1332, 1330, 1335, 4665, }, { 1432, 1431, 1435, 1438, }, { 1532, 1531, 1535, 4667, },
			{ 17, 16, 9, 4668, }, { 5244, 5245, 5246, 5250, }, };
	//int[characterid][sid]
    public static int[][] costumePack = { { 2000, 3000, 3500, }, { 2103, 3100, 3510, }, { 2202, 3200, 3520, }, { 2301, 3301, 3530, }, { 2402, 3400, 3540, }, { 4418, 4419, 4420, },
            { 5247, 5248, 5249 }, };
	//bronze\silver \gold\ vip\ activity
	public static int[][] giftPool={
		{296,302,308,},
            { 1138, 1127, 1133, 1120, 1121, 1125, 3001, 3002, 4008, 4017, 1334, 1320, 1338, 1337, 1322, 1325, 2100, 3103, 4009, 4018, 1434, 1420, 1431, 1437, 1124, 1422, 1423, 2200, 3202, 4010, 310,
                    1537, 1525, 1533, 1520, 1521, 1523, 2300, 3303, 4011, 1234, 1220, 1238, 1224, 1221, 1223, 2303, 3402, 4007, },
            { 1113, 1112, 1114, 1110, 1310, 1312, 1313, 1309, 1412, 1413, 1414, 1410, 1513, 1511, 1514, 1510, 1211, 1219, 1213, 1210, },//changed in 20111123
		};

	//==================================dailycheck=============================================
	public static enum DAILY_CHECK_AWARD_LEVEL{
		//初级签到礼盒		中级签到礼盒		高级签到礼盒		特级签到礼盒		豪华签到礼盒
		PRIMARY_CHECK(1),MIDDLE_CHECK(2),HIGH_CHECK(3),SUPER_CHECK(4),LUXURY_CHECK(7),DAILY_GIFT(5),DAILY_GUESS(6);
		private int value;

		DAILY_CHECK_AWARD_LEVEL(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public static final String RPC_PARAM_ERROR_LOG	= "ParamIllegal";
	public static final int DAILY_CHECK_AGO_CHANCE = 32;
	public static final int DAILY_CHECK_FUTURE_CHANCE = 32;

	//public static final int[] DAILY_CHECK_RESULTS = {0,1,2,3,4};//0:FC不足，1:补签已3次，2：预签已3次，3：成功，4：客户端与服务器时间不同步

	public enum DAILY_CHECK_RESULTS {//0:FC不足，1:补签已3次，2：预签已3次，3：成功，4：客户端与服务器时间不同步
		FC_NOT_ENOUGH(0),NO_CHECK_AGO_CHANCE(1),NO_CHECK_FUTURE_CHANCE(2),CHECK_SUCCESS(3),C_S_NOT_SYN(4);
		private int value;

		DAILY_CHECK_RESULTS(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

    /*
	 * 1 在线时长
	 * 2 每日签到 3 神秘锦囊 4 VIP保险箱 5 密码箱 6 过关结算翻牌子 7 靶场 8 连续射击（银） 9 连续射击（金） 10 彩盒 11 拼图  12资源争夺战兑换礼盒	  13圣诞跳跳乐箱子  15暑假礼包
	 */
	public enum ONLINE_AWARD_TYPES{
        ONLINE_TIME(1), DAILY_CHECK(2), MYSTIC_BAG(3), VIP_SAFECABINET(4), PASSWARD_BOX(5), STAGE_CLEAR(6), SHOOTING_AWARD(7), SILVER_DARTLE_AWARD(8), GOLD_DARTLE_AWARD(9), LUCK_PACKAGE(10), MISTIC_PINTU(
                11), RES_MAGIC_BOX(12), AGRAVITYBOXITEM(13), ONE_TO_MANY_BOX(15);
		private int value;

		ONLINE_AWARD_TYPES(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

    /**
	 * 使用类道具IID 18神秘口袋
	 */
	public enum SPECIAL_ITEM_IIDS{
        VIP(15), MEDEL(17), MYSTIC_BAG(18), CHECK_GIFTBOX(19), VIP_SAFECABINET(20), PASSWORD_BOX(22), RELIVE_COIN(28), PASSWORD_CARD(29), LUCKYPACKAGE_CARD(30), SHOOTING_AMMO(31), SHOOTING_LOOK(32), DARTLE_ADD(
                33), NULL_BOTTLE(34), NEXT_SHOOT_FREE(35), SHOP_GIFT_PACKAGE(36), NEW_AWARD(37), BLOOD_BOTTLE(39), BULLET_BOTTLE(40), TANK(41), ZYZDZ_BUFF_ATT(43), ZYZDZ_BUFF_DEF(44), ZYZDZ_BUFF_MOVE(
                46), ZYZDZ_BUFF_ATT_SP(47),
		//超级能量块 1~9
        SUPER_POWER1(49), SUPER_POWER2(50), SUPER_POWER3(51), SUPER_POWER4(52), SUPER_POWER5(53), SUPER_POWER6(54), SUPER_POWER7(55), SUPER_POWER8(56), SUPER_POWER9(57), AGRAVITYBOXITEM(58)
		//zlm2015-5-7-限时装备-开始 里面物品有时限的多选一礼包的类型
		, XSZB_CHOICE_BOX(61),			//里面物品有时限的多选一礼包
		//zlm2015-5-7-限时装备-结束;
		ONE_TO_MANY_BOX(62) //一对多礼包，该礼包
		, SUPER_POWER10(63) //一对多礼包，该礼包
			;
		private int value;

		SPECIAL_ITEM_IIDS(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

    /**
	 * 素材IID
	 */
	public enum MATERIAL_IIDS{
		OFF_Chip(104),A_Chip(101),B_Chip(102),C_Chip(103);
		private int value;

		MATERIAL_IIDS(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	public static int[][] BOSS2_PARAM={{1,6,36,216},{1,5,25,125},{1,4,16,64},{1,3,9,27}};
	public static int[] BOSS2_AWARDS_ID = { 5077, 5078, 5079, 5080 };//Boss2掉落物品

	        /**
	 * 玩家每日签到获得奖励累计天数
	 */
	public static final int DAILY_CHECK_1ST_DAY =3;
	public static final int DAILY_CHECK_2ND_DAY =7;
	public static final int DAILY_CHECK_3RD_DAY =14;
	public static final int DAILY_CHECK_4TH_DAY =21;

	public static final int DAILY_CHECK_5TH_DAY() {	//全勤礼包 自然月
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(sdf.format(c.getTime())));
		Date date = new Date(c.getTimeInMillis());
		return Integer.parseInt(sdf.format(date));
	}

	public static  enum DAILY_CHECK_LEVELS_DAYS{
		PRIMARY(3),MIDDLE(7),HIGH(14),SUPER(27);
		private int value;

		DAILY_CHECK_LEVELS_DAYS(int value){
			this.value = value;
		}

		public int getValue(){
			return value;
		}
	}

	        /**
	 * 猜数字需要的C币
	 */
	public static int DAILY_GUS_NUM_COST = 1000;//C币
	public static int DAILY_GUS_MAX_NUM = 9;
	//=====================
	public static int DAILY_CHECK_AGO_COST = 150;//FC
	public static int DAILY_CHECK_FUTURE_COST = 30;//FC

	//================================PassGameBalance===========================================

	//针对各个模式积分系数
	public static final float GAME_MODE_TEAM_MODULUS = 0.9f;
	public static final float GAME_MODE_ZD_MODULUS = 1.0f;
	public static final float GAME_MODE_TCH_MODULUS = 1.0f;
	public static final float GAME_MODE_JM_MODULUS = 0.6f;
	public static final float GAME_MODE_BOSS_MODULUS = 0.9f;
	public static final float GAME_MODE_KNIF_MODULUS = 0.9f;
	        /**
	 * 网吧等级 1：金 2：银 3：铜
	 */
	public static final int[] NET_BAR_LEVELS = {1,2,3};
	        /**
	 * 过关结算mcc
	 */
	public static final int STAGE_DEFAULT_AWARD_BRAND_ID = 247;//如果获取牌子为null，将返回玩家强化部件
	public static final int STAGE_DEFAULT_AWARD_SYSITEM_ID = 125;//如果获取Sysitem为null，将返回玩家强化部件

	public static final String BOSS2_STAGE_AWARD_PREFIX = "b2sap:";
	public static final String BOSS2_STAGE_LEVEL_PREFIX = "b2slp:";
	public static final String STAGE_BRAND_OPEN_PREFIX = "stbo:";
	public static final String STAGE_OPEN_CHANCE_PREFIX = "stocp:";
	public static final String STAGE_BRAND_LIST_PREFIX = "rbrdlst:";
	public static final String STAGE_BRAND_OPEN_COUNT_PREFIX = "stboc:";
	public static final int STAGE_BRAND_OPEN_NUM = 9;
	public static final int SCMS_BRAND_OPEN_NUM = 3;//生存模式翻牌总张数
	public static final String STAGE_GET_CHANCE_PREFIX = "sgch:";
	public static final String STAGE_GET_SCORE_PREFIX = "sgsc:";
	public static final String STAGE_GET_EXP_PREFIX = "sgex:";
	public static final String STAGE_GET_GP_PREFIX = "sggp:";
	public static final String BRAND_MCC_KEY_PREFIX = "bmkp:";
	public static final int[] VIP_EXTRA_GP_PERCENT = {50,70,90,110,130,150,160,180,180};
	public static final int[] VIP_EXTRA_EXP_PERCENT = {50,70,90,110,130,150,160,180,180};
	public static final int[] netbarExtraGpPercent ={10,20,30} ;
	public static final int[] netbarExtraExpPercent ={10,15,20} ;
	public static final int ACTIVITY_EXTRA_GP_PERCENT = 0;
	public static final int ACTIVITY_EXTRA_EXP_PERCENT = 0;

	public static final int NO_OPEN_BRAND_CHANCE_FLAG = -1;

	public static final int BRAND_CACHE_ITEM_TIMEOUT = 60;//second
	public static final int[] OPEN_BRAND_NUM_LEVEL = {20,40};

	        /**
	 * 玩家牌子list nosql key
	 */
	public static final String PLAYER_BRAND_ID_LIST_PREFIX = "pbil:";
	        /**
	 * 神秘锦囊
	 */
	public static final int MYSTIC_BAG_TYPE =3;
	public static final int MYSTIC_BAG_LEVEL1_TYPE =1;
	public static final int MYSTIC_BAG_LEVEL2_TYPE =2;
	public static final int MYSTIC_BAG_LEVEL3_TYPE =3;

	//==============================VipRandom===================================================
	public static final String NO_VALUE_FIND_FROM_REDIS	    = "not found in Redis or null";
	public static final String VIP_START_INDEX_REDIS=" VipRandomStartIndex Key ";
	public static final String VIP_RANDOM_VSC_LIST_PREFIX = "vrdvl:";
	public static final String VIP_RANDOM_START_LIST_PREFIX = "vrdsl:";
	public static final int VIP_OPEN_CHANCE_NUM = 5;
	public static final int VIP_RANDOM_SYSITEM_NUM = 15;
	public static final float VIP_SOCRE_EXTRA = 0.2f;
	public static final int[] VIP_SAFE_CABINET_LEVELS = {1,2,3};
	public static final int[] VIP_SAFE_CABINET_TYPE_NUMS = {1,11,3};
	public static final int[] VIP_START_COSTS ={1,2,4,8,16};
	public static final int NOT_IN_CHANNEL_ID=0;
	public static final int NOT_IN_SERVER_ID=0;

	//============================MagicBox==========================================

	public static final int OPEN_MAGIC_BOX_NEED_NUM = 1;
	public static final int SYSITEM_HUMMER_ID = 4485;
	public static final String MAGIC_BOX_ITEM_IDS_KEY="MBIIK";
	public static final String RES_MAGIC_BOX_ITEM_IDS_KEY="RMBIIK";
	public static final int MAGIC_BOX_ITEM_NUM = 20;
	public static final int RES_MAGIC_BOX_ITEM_NUM = 20;
	public static final int MAGIC_BOX_GIFT_PAGESIZE = 16;
	public static final int RES_MAGIC_BOX_GIFT_PAGESIZE = 16;

	public static final String SHOOTING_MCC_KEY_PREFX = "stmck:";
	public static final String SHOOTING_AWARD_LEVEL_KEY_PREFX = "salk:";
	public static final String SHOOTING_BOUT_NUM_KEY_PREFX = "sbnk:";
	public static final String SHOOTING_DARTLE_NUM_KEY_PREFX = "sdnk:";
	public static final String SHOOTING_NEXT_FREE_KEY_PREFX = "snfk:";
	public static final String SHOOTING_IS_LOOKED_KEY_PREFX = "silk:";
	public static final String SHOOT_RDM_GIFTS_ID_MCC_KEY_PRFX = "srgik:";
	public static final String DARTLE_TOP_REDIS_KEY= "dttprk";
	public static final String DARTLE_AWARD_TOP_REDIS_KEY= "dtatprk";
	public static final int SHOOTING_BOTTLE_NUM= 7;
	public static final int LOOK_ALL_COST_NUM= 5;
	public static final int DARTLE_TOP_NUM = 10;
	public static final int MAX_CACHE_DARTLE_TOP_NUM = 20;

	        /**
	 * 二维数组 第一行，表示武器的subtype（同客户端和sysitem的subtype） 第二行，表示付款方式（0全部，1FC点，2C币）
	 */
	//weapon
    public static int[][] weaponTypeArray = { { 0, 1, 2, 3, 4, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//cloth
    public static int[][] clothTypeArray = { { 0, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//配饰
    public static int[][] partTypeArray = { { 0, 1, 2, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//道具
    public static int[][] itemTypeArray = { { 0, 1, 2, 3, 4, 5, 7, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//素材
    public static int[][] materialTypeArray = { { 0, 1, 2, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//蓝图
    public static int[][] blueTypeArray = { { 0, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//大礼包
    public static int[][] packageTypeArray = { { 0, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };
	//打开类
    public static int[][] useTypeArray = { { 0, 1, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, 1, 2, } };

	//资源争夺战类
    public static int[][] zyzdzArray = { { 0, 1, 2, 3, 4, 6, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, GP_PAY, CR_PAY, RES_PAY, RES_PAY_TEAM, RES_DIS_PAY, RES_DIS_PAY_TEAM } };

	//推荐
    public static int[][] popularArray = { { 0, 1, 2, 3, 4, 6, SHOP_VIP_INDEX, SHOP_WEB_INDEX, }, { 0, GP_PAY, CR_PAY } };

	        /**
	 * 强化加强数据 第一维：表示等级 第二维：表示物品类型（武器，衣服，帽子，配饰） 第三维：属性1加成，属性2加成，所需强化部件个数，强化所需C币，成功概率，
	 * 失败不掉级概率，失败掉级概率，失败爆装概率，开孔的成功率，转换掉1级概率
	 */
	        /*public static double[][][] strengthenAppend1 = {
	{//lv1
	{
	0.071,0.012,6,6000,1,0,0,0,0,0,
	},//武器
	{
	0.021,0.021,3,3000,1,0,0,0,0,0,
	},//衣服
	{
	0.007,0.007,1,1000,1,0,0,0,0,0,
	},//帽子
	{
	0.014,0.014,2,2000,1,0,0,0,0,0,
	}//配饰
	},
	{//lv2
	{
	0.147,0.025,6,6000,0.7,0.3,0,0,0,0,
	},
	{
	0.042,0.042,3,3000,0.7,0.3,0,0,0,0,
	},
	{
	0.014,0.014,1,1000,0.7,0.3,0,0,0,0,
	},
	{
	0.028,0.028,2,2000,0.7,0.3,0,0,0,0,
	}
	},
	{//lv3
	{
	0.229,0.037,12,12000,0.6,0.4,0,0,0,0,
	},
	{
	0.064,0.064,6,6000,0.6,0.4,0,0,0,0,
	},
	{
	0.021,0.021,2,2000,0.6,0.4,0,0,0,0,
	},
	{
	0.043,0.043,4,4000,0.6,0.4,0,0,0,0,
	}
	},
	{//lv4
	{
	0.316,0.05,12,12000,0.5,0.25,0.25,0,1,0,
	},
	{
	0.088,0.088,6,6000,0.5,0.25,0.25,0,0,0,
	},
	{
	0.029,0.029,2,2000,0.5,0.25,0.25,0,0,0,
	},
	{
	0.058,0.058,4,4000,0.5,0.25,0.25,0,0,0,
	}
	},
	{//lv5
	{
	0.409,0.063,18,18000,0.4,0.3,0.3,0,1,0,
	},
	{
	0.112,0.112,9,9000,0.4,0.3,0.3,0,0,0,
	},
	{
	0.037,0.037,3,3000,0.4,0.3,0.3,0,0,0,
	},
	{
	0.075,0.075,6,6000,0.4,0.3,0.3,0,0,0,
	}
	},
	{//lv6
	{
	0.51,0.076,24,24000,0.3,0.35,0.35,0,1,0,
	},
	{
	0.137,0.137,12,12000,0.3,0.35,0.35,0,0,0,
	},
	{
	0.046,0.046,4,4000,0.3,0.35,0.35,0,0,0,
	},
	{
	0.092,0.092,8,8000,0.3,0.35,0.35,0,0,0,
	}
	},
	{//lv7
	{
	0.617,0.089,30,30000,0.2,0.4,0.4,0,0.5,0,
	},
	{
	0.163,0.163,15,15000,0.2,0.4,0.4,0,0.5,0,
	},
	{
	0.054,0.054,5,5000,0.2,0.4,0.4,0,0.5,0,
	},
	{
	0.109,0.109,10,10000,0.2,0.4,0.4,0,0.5,0,
	}
	},
	{//lv8
	{
	0.732,0.102,36,36000,0.12,0.44,0.44,0,0.5,0,
	},
	{
	0.191,0.191,18,18000,0.12,0.44,0.44,0,0.5,0,
	},
	{
	0.064,0.064,6,6000,0.12,0.44,0.44,0,0.5,0,
	},
	{
	0.127,0.127,12,12000,0.12,0.44,0.44,0,0.5,0,
	}
	},
	{//lv9
	{
	0.855,0.116,48,48000,0.09,0.455,0.455,0,0.5,0,
	},
	{
	0.219,0.219,24,24000,0.09,0.455,0.455,0,0.5,0,
	},
	{
	0.073,0.073,8,8000,0.09,0.455,0.455,0,0.5,0,
	},
	{
	0.146,0.146,16,16000,0.09,0.455,0.455,0,0.5,0,
	}
	},
	{//lv10
	{
	0.987,0.129,66,66000,0.06,0.47,0.47,0,0.25,0.1,
	},
	{
	0.249,0.249,33,33000,0.06,0.47,0.47,0,0.5,0.1,
	},
	{
	0.083,0.083,11,11000,0.06,0.47,0.47,0,0.5,0.1,
	},
	{
	0.166,0.166,22,22000,0.06,0.47,0.47,0,0.5,0.1,
	}
	},
	{//lv11
	{
	1.128,0.143,84,84000,0.37,0,0.315,0.315,0.25,0.15,
	},
	{
	0.28,0.28,42,42000,0.37,0,0.315,0.315,0.5,0.15,
	},
	{
	0.093,0.093,14,14000,0.37,0,0.315,0.315,0.5,0.15,
	},
	{
	0.187,0.187,28,28000,0.37,0,0.315,0.315,0.5,0.15,
	}
	},
	{//lv12
	{
	1.279,0.157,108,108000,0.37,0,0.315,0.315,0.2,0.2,
	},
	{
	0.312,0.312,54,54000,0.37,0,0.315,0.315,0.2,0.2,
	},
	{
	0.104,0.104,18,18000,0.37,0,0.315,0.315,0.2,0.2,
	},
	{
	0.208,0.208,36,36000,0.37,0,0.315,0.315,0.2,0.2,
	}
	},
	{//lv13
	{
	1.441,0.171,138,138000,0.37,0,0.315,0.315,0.2,0.25,
	},
	{
	0.345,0.345,69,69000,0.37,0,0.315,0.315,0.2,0.25,
	},
	{
	0.115,0.115,23,23000,0.37,0,0.315,0.315,0.2,0.25,
	},
	{
	0.23,0.23,46,46000,0.37,0,0.315,0.315,0.2,0.25,
	}
	},
	{//lv14
	{
	1.614,0.186,180,180000,0.37,0,0.315,0.315,0.2,0.3,
	},
	{
	0.38,0.38,90,90000,0.37,0,0.315,0.315,0.2,0.3,
	},
	{
	0.127,0.127,30,30000,0.37,0,0.315,0.315,0.2,0.3,
	},
	{
	0.254,0.254,60,60000,0.37,0,0.315,0.315,0.2,0.3,
	}
	},
	{//lv15
	{
	1.8,0.2,234,234000,0.37,0,0.315,0.315,0.1,0.4,
	},
	{
	0.417,0.417,117,117000,0.37,0,0.315,0.315,0.1,0.4,
	},
	{
	0.139,0.139,39,39000,0.37,0,0.315,0.315,0.1,0.4,
	},
	{
	0.278,0.278,78,78000,0.37,0,0.315,0.315,0.1,0.4,
	}
	}
	};*/

	        /**
	 * 新手礼包 第一个二位数组是C币奖励 第二个三维数组是物品奖励
	 */
	public static int[][][] GREEN_GR_AWARD={
            { { 4633, 3000 }, { 4634, 4000 }, { 4635, 5000 }, { 4636, 6000 }, { 4637, 7000 }, { 4638, 8000 }, { 4639, 9000 }, { 4640, 10000 }, { 4641, 50000 }, { 4642, 55000 }, { 4643, 60000 },
                    { 4644, 65000 }, { 4645, 70000 }, { 4646, 75000 }, { 4647, 80000 }, { 4648, 85000 }, { 4649, 90000 }, { 4650, 95000 }, { 4651, 100000 }, { 4652, 400000 }, { 4653, 450000 } },
            { { 5919, 0 }, { 5920, 0 }, { 5921, 0 }, { 5922, 0 }, { 5923, 0 }, { 5924, 0 }, { 5925, 0 }, { 5926, 0 }, { 5927, 100000 }, { 5928, 0 }, { 5929, 0 }, { 5930, 0 }, { 5931, 0 } },
            { { 5932, 0 } } };

    public static int[][][][] GREEN_ITEM_AWARD = { { {//lv1
            { 312, 2, 1 }, { 4634, 1, 1 } }, {//lv3
            { 311, 2, 1 }, { 4635, 1, 1 } }, {//lv5
            { 4306, 2, 1 }, { 4636, 1, 1 } }, {//lv7
            { 4202, 2, 3 }, { 4637, 1, 1 } }, {//lv9
            { 4200, 2, 3 }, { 4638, 1, 1 } }, {//lv11
            { 4205, 2, 3 }, { 4639, 1, 1 } }, {//lv13
            { 125, 1, 9 }, { 127, 1, 1 }, { 4479, 1, 18 }, { 126, 1, 1 }, { 4640, 1, 1 } }, {//lv15
            { 125, 1, 10 }, { 127, 1, 1 }, { 4479, 1, 20 }, { 126, 1, 1 }, { 4678, 1, 1 }, { 4641, 1, 1 } }, {//lv20
            { 125, 1, 50 }, { 127, 1, 1 }, { 4479, 1, 100 }, { 126, 1, 1 }, { 4642, 1, 1 } }, {//lv25
			{ 125, 1, 55 }, { 127, 1, 1 }, { 4479, 1, 110 }, { 126, 1, 1 }, { 4643, 1, 1 }				//新的隔日礼包 （战斗补给箱）,{5815,1,1}
            }, {//lv30
				{125,1,60},{127,1,1},{4479,1,120},{126,1,1}
			//				终止在三十级,{4644,1,1}
            }, {//lv35
            { 125, 1, 65 }, { 127, 1, 1 }, { 4479, 1, 130 }, { 126, 1, 1 }, { 4645, 1, 1 } }, {//lv40
            { 125, 1, 70 }, { 127, 1, 1 }, { 4479, 1, 140 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4646, 1, 1 } }, {//lv45
            { 125, 1, 75 }, { 127, 1, 1 }, { 4479, 1, 150 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4647, 1, 1 } }, {//lv50
            { 125, 1, 80 }, { 127, 1, 1 }, { 4479, 1, 160 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4648, 1, 1 } }, {//lv55
            { 125, 1, 85 }, { 127, 1, 1 }, { 4479, 1, 170 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4649, 1, 1 } }, {//lv60
            { 125, 1, 90 }, { 127, 1, 1 }, { 4479, 1, 180 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4650, 1, 1 } }, {//lv65
            { 125, 1, 95 }, { 127, 1, 1 }, { 4479, 1, 190 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4651, 1, 1 } }, {//lv70
            { 125, 1, 100 }, { 127, 1, 1 }, { 4479, 1, 200 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4487, 1, 1 }, { 4522, 1, 1 }, { 4652, 1, 1 } }, {//lv75
            { 125, 1, 400 }, { 127, 1, 1 }, { 4479, 1, 800 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4487, 1, 1 }, { 4522, 1, 1 }, { 4653, 1, 1 } }, {//lv80
            { 125, 1, 450 }, { 127, 1, 1 }, { 4479, 1, 900 }, { 126, 1, 1 }, { 4486, 1, 1 }, { 4521, 1, 1 }, { 4487, 1, 1 }, { 4522, 1, 1 } } }, { {//lv1
            { 4479, 1, 3 }, { 125, 1, 2 }, { 5920, 1, 1 } }, {//lv3
            { 4479, 1, 5 }, { 126, 1, 1 }, { 5921, 1, 1 } }, {//lv5
            { 125, 1, 5 }, { 127, 1, 2 }, { 5922, 1, 1 } }, {//lv7
            { 4485, 1, 5 }, { 4479, 1, 10 }, { 5923, 1, 1 } }, {//lv9
            { 4879, 1, 1 }, { 125, 1, 10 }, { 5924, 1, 1 } }, {//lv11
            { 125, 1, 15 }, { 4479, 1, 10 }, { 126, 1, 2 }, { 5925, 1, 1 } }, {//lv13
            { 4879, 1, 2 }, { 125, 1, 15 }, { 4485, 1, 10 }, { 5926, 1, 1 } }, {//lv15
            { 4879, 1, 3 }, { 4479, 1, 20 }, { 126, 1, 3 }, { 5927, 1, 1 } }, {//lv17
            { 4479, 1, 20 }, { 125, 1, 25 }, { 5928, 1, 1 } }, {//lv19
            { 5050, 1, 1 }, { 125, 1, 30 }, { 126, 1, 3 }, { 5929, 1, 1 } }, {//lv21
            { 5049, 1, 1 }, { 4479, 1, 20 }, { 125, 1, 25 }, { 5930, 1, 1 } }, {//lv23
            { 5048, 1, 1 }, { 4879, 1, 3 }, { 125, 1, 30 }, { 5931, 1, 1 } }, {//lv25
            { 4879, 1, 5 }, { 5934, 1, 1 }, { 5936, 0, 1 }, { 4479, 1, 30 }, { 125, 1, 30 } } }, { {//lv25
            { 5933, 1, 1 }, { 5048, 1, 1 }, { 5049, 1, 1 }, { 5936, 0, 1 }, { 4879, 1, 5 }, { 4479, 1, 60 }, { 125, 1, 60 }, { 126, 1, 5 }, { 127, 1, 10 }, { 5935, 1, 1 } } } };

	final public static int[][] ADVANCED_LEVEL_PACKAGE = { { 125, 30, 1 },//强化部件        30
			{ 126, 3, 1 },//安定装置		3
			{ 4479, 25, 1 },//勋章		25
			{ 4505, 2, 1 },//改装装置		2
			{ 4486, 2, 1 },//武器属性石LV1		2
			{ 4521, 1, 1 },//服饰属性石LV1		1
			{ 4626, 1, 1 },//密码箱        1
			{ 4623, 1, 1 },//VIP保险柜     1
			{ 4305, 3, 2 },//VIP道具
	};
	final public static int SUCCESS						= 0;
	final public static int FAILED_NOT_LOSE_LEVEL 		= 1;
	final public static int FAILED_LOSE_LEVEL 			= 2;
	final public static int FAILED_BREAK_ITEM 			= 3;

	final public static int SUCCESSED		 			= 0;
	final public static int FAILED			 			= 1;

	//强化装置类型
	final public static int STABLE_ITEM = 2;//安定装置
	final public static int SAFE_ITEM = 3;//保险装置
	final public static int UNBREAK_ITEM = 4;//保障装置
	final public static int SUCCESS_ITEM = 5;//增幅安定装置

	final public static int MUST_SUCCESS_LEVEL 			= 10;

	final public static int MAX_STACK_SIZE = 300;//素材最大重叠数
	final public static int MAX_ZYZDZ_STACK_SIZE = 0x7fffffff;//素材最大重叠数

	final public static double STABLE_ITEM_1T3			= 0.01;
	final public static double STABLE_ITEM_4T10			= 0.005;
	final public static double STABLE_ITEM_11T15		= 0.005;

	final public static int SLOTTING_COST = 1000;
	final public static int CONVERT_COST = 1000;
	final public static int REMOVE_COST = 1000;
	final public static int[] INSERT_COST = {250, 500, 1000};
	public static final int MAX_STRENGTH_LEVEL = 18;//最高武器等级
	public static final int MAX_STRENGTH_LEVEL_DEFAULT = 9;//默认可以升到的最高武器等级
	public static final int MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP5 = 13;//5级vip可以升到的最高武器等级
	public static final int MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP6 = 14;
	public static final int MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP7 = 15;
	public static final int MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP8 = 16;
	public static final int MAX_STRENGTH_LEVEL_DEFAULT_FOR_VIP9 = 16;

    final public static int[][] NEW_PACKAGE = { { 4002, 1, 1 }, { 125, 1, 1 }, { 311, 2, 2 }, { 312, 2, 2 }, { 4560, 0, 1 }, };
    final public static int[][] NEW_PACKAGE2 = { { 4479, 1, 10 }, { 125, 1, 10 },
//		{4895,1,1},
            { 5816, 1, 1 }, };
    final public static int[][] NEW_PACKAGE3 = { { 4479, 1, 12 }, { 125, 1, 12 }, { 4879, 1, 1 }, { 5817, 1, 1 }, };
    final public static int[][] NEW_PACKAGE4 = { { 4479, 1, 14 }, { 125, 1, 14 }, { 4879, 1, 2 }, { 5818, 1, 1 }, };
    final public static int[][] NEW_PACKAGE5 = { { 4479, 1, 16 }, { 125, 1, 16 }, { 4879, 1, 3 }, { 5819, 1, 1 }, };
    final public static int[][] NEW_PACKAGE6 = { { 4479, 1, 18 }, { 125, 1, 18 }, { 4879, 1, 5 }, { 5820, 1, 1 }, };
    final public static int[][] NEW_PACKAGE7 = { { 4479, 1, 20 }, { 125, 1, 20 }, { 5051, 1, 1 }, { 5821, 1, 1 }, };
    final public static int[][] NEW_PACKAGE8 = { { 4479, 1, 22 }, { 125, 1, 22 }, { 5050, 1, 1 }, { 5822, 1, 1 }, };
    final public static int[][] NEW_PACKAGE9 = { { 4479, 1, 24 }, { 125, 1, 24 }, { 5049, 1, 1 }, };

	final public static double[][] FIGHT_PARAM = { { 1000, 1.085, 30, 0, 50, 25, 16.7 },//主武器
			{ 300, 1.085, 30, 0, 50, 25, 16.7 },//近身武器
			{ 1000, 1.042, 30, 0, 50, 25, 16.7 },//服装
			{ 1000, 1.014, 30, 0, 50, 25, 16.7 },//帽子
			{ 1000, 1.028, 30, 0, 50, 25, 16.7 },//配饰
			{ 600, 1.085, 30, 0, 50, 25, 16.7 },//副武器
	};

	final public static double[] FIGHT_PARAM1 ={0.85,0.05,0.1,0.0002,0.001,0.001,0.001};
    final public static int[][] CHOICE_BOX = { { 4690, 4692, 4696, 4698, 4701, 4669 }, { 4689, 4693, 4695, 4699, 4702, 4670 }, { 4688, 4691, 4694, 4697, 4703, 4729 },
            { 4509, 4512, 4515, 4518, 4525, 4527 }, { 4530, 4540, 4542, 4547, 4552, 4597 }, { 4557, 4559 }, { 4409, 4410, 4017, 4018 },//unitType=2,unit=7
            { 4601, 4603, 4605, 4607, 4609, 4630 }, { 1131, 1232, 1329, 1440, 1530, 4423 },//unitType=2,unit=3
		{4905,4906,4907,4908,4909,4910,5346},	//158	X
		{4748,4749,4750,4751,4752,4753},		//188	Xi
            { 4760, 4761, 4762, 4763, 4764, 4765 },//
            { 4748, 4753, 4750, 4749, 4751, 4752 },//
			{ 5353, 5354, 5355, 5356, 5357, 5358 },//限时抢购稀有宝箱
			{ 4748, 4753, 4750, 4749, 4751, 4752 },//限时抢购卓越宝箱
			{ 4766, 4767, 4768, 4769, 4770, 4771 },//限时抢购精良宝箱
			{ 5842, 5843, 5844, 5845, 5846, 5847, 5848 },//金钻武器
			{ 5849, 5850, 5851, 5852, 5853, 5854, 5855 },//金钻衣服18
			{ 4903, 4904, 5113, 5114, 5195, 5216 },	//卓越翅膀
			{ 5093, 5094, 5095, 5096, 5097, 5098, 5345 },	//卓越衣服20
			{ 5944, 5946, 5947, 5948, 5949, 5950, 5951 },//蓝钻贵族武器 迅雷VIP
			{ 6304, 6305, 6306, 6307, 6308, 6309, 6310 },//比赛蓝图武器多选一礼盒
			{ 6316, 6317, 6318, 6319, 6320, 6321, 6322 } //黄金珍藏版传说多选一武器礼盒

	};

	public final static int STRENGTH_PART = 1;//强化部件
	public final static int ADD_S = 2;//增幅装置
	public final static int NOT_BREAK = 3;//安定装置
	public final static int NOT_BACK_LEVEL = 4;//保障装置
	public final static int STRENGTH_ADD_S = 5;//强化增幅装置
	public final static int HONOR = 6;//勋章
	public final static int PASSWORD_CARD = 7;//密码卡
	public final static int WEAPON_PART = 8;//武器属性部件
	public final static int CLOTH_PART = 9;//服饰属性部件
	public final static int CONVERT_PART = 10;//转换工具（拆）
	public final static int INSERT_PART = 11;//改装部件

	public static final byte  PLAYER_NORMAL_REMAIN_VOTERNUM = 2;
	public static final byte  PLAYER_VIP_REMAIN_VOTERNUM = 3;
	public final static int TEAME_MAX_SIZE            =100;
	//  一个战队经验==一个战队贡献值
	public final static int TEAMEXP_TEAM_LOSE = 50;//战队战 所属战队输  个人对战队贡献值
	public final static int TEAMEXP_TEAM_WIN = 100;//战队战 所属战队赢   个人对战队贡献值
	public final static int ZYZDZ_TEAMEXP_TEAM_LOSE = 10;//资源争夺战战队战 所属战队输  个人对战队贡献值
	public final static int ZYZDZ_TEAMEXP_TEAM_WIN = 20;//资源争夺战战队战 所属战队赢   个人对战队贡献值
	public final static int TEAMEXP_TEAM_DAY_MAX = 400;//战队战，每天最多可以获得的战队经验

	public final static int TEAMEXP_TEAM_FULL = -1;//当天可以获得的经验值已满

	public final static int TEAMEXP_PERSONAL_LOSE = 10;//非战队战输 个人对战队贡献值
	public final static int TEAMEXP_PERSONAL_WIN = 20;//非战队战输赢个人对战队贡献值

	public final static int TEAMEXP_PERSONAL_DAY_MAX = 100;//普通战役，每天最多可以获得的战队经验
	public final static int TEAM_RECORD_COUNT_MAX = 20;//没个战队最多存最新的二十条记录
	public final static double TEAM_BUFF_A               =1391.7;
	public final static double 	TEAM_BUFF_B	             =1.61672;
	public final static int  TEAM_BUFF_MAX_LEVEL      =10;

	public final static int TEAM_CMD_PROCLAMATION = 1;//战队公告，新增
	public final static int TEAM_CMD_RECORD = 2;//战队战绩，新增
	public final static int TEAM_CMD_REMARK = 3;//战队介绍新增
	public final static int TEAM_CMD_DEL_PROCLAMATION = 4;//战队公告删除
	public static final String PLAYER_PT_FLAG_KEY = "PLPTFK:";//玩家拼图标志位redis key
	public static final String PLAYER_PT_PRI_FLAGS = "0000000000000000";//玩家最初拼图标志位
	public static final String PLAYER_PT_CMT_FLAGS = "1111111111111111";//玩家完成拼图标志
	public static final String PLAYER_PT_GET_WEIGHTS_KEY = "PLPTGWTK";//玩家获得拼图概率 redis key
	public static final double PLAYER_PT_GET_DFT_WT = 0.165;//玩家默认获得拼图概率
	public static final String PLAYER_PT_FLAG_GET_WEIGHTS_KEY = "PLPTFETK:";//玩家拼图标志位（0，1）概率比重 redis key
	public static final String[] PLAYER_PT_FLAG_GET_WEIGHTS_DFT_VALS = { "2:1", "2:1" };//默认标志位（0，1）概率比
	public static final int PLAYER_PT_TOTAL_CHANCE = 1;
	public static final int[] COMBINE_FAIL_AWARD_IDS = { 5208, 5209, 5210, 5211, 5239 };//lv1-lv5碎片
	public static final String PLAYER_DAILY_DISCOUNT_PREX = "PDLDCP:";
	public static final String DAILY_DISCOUNT_SYSITEM_KEY = "DLDCSIK";
	public static final Integer[] DAILY_DISCOUNT_SYSITEM_IDS = {4879,4878};
    public static Float[][] DAILY_DISCOUNTS = { { 0.25f, 0.5f, 0.75f }, { 0.25f, 0.5f, 0.75f } };
	public static String DAILY_INIT_LOCK_MCC_KEY = "DLITLCMK";
	public static String DAILY_INIT_MCC_KEY = "DLITMK";
	public static String CMPT_BUY_TSK_LOCK_MCC_KEY = "CBTLMK";
	public static String CMPT_BUY_TSK_MCC_KEY = "CBTMK";
	public static final String COMPETE_BUY_ITEM_KEY_PREX = "CMTBYITM:";
	public static final String COMPETE_BUY_BACKUP_ITEM_KEY_PREX = "CMTBYBPITM:";
	//VIP 是否可获得升级经验状态位
	public static final String VIP_UPGRADE_EXP_STATUS="VIPUES";
	//第1位:签到 ;第2位是在线时长，由于在线时长有三次领取，故累加到3;第3到5位对应每日任务(简单、正常、困难);第6位:vip经验块;最后2位统计当天过关结算的次数
	public static final String VIP_UPGRADE_EXP_STATUS_INIT="00000000";  // full is "132111FF"
//	public static final int CMPT_BUY_STT_TIME_HOUR = 10;
//	public static final int CMPT_BUY_END_TIME_HOUR = 16;
//	public static final int CMPT_BUY_OVR_TSK_TIME_HOUR = 17;
	//TODO change hours just for test
	public static final int CMPT_BUY_STT_TIME_HOUR = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_STT_TIME_HOUR, 11);
	public static final int CMPT_BUY_END_TIME_HOUR = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_END_TIME_HOUR, 18);
	public static final int CMPT_BUY_STT_WEEK_DAY  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_STT_WEEK_DAY, 7);
	public static final int CMPT_BUY_END_WEEK_DAY  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_END_WEEK_DAY, 7);
	public static final int CMPT_BUY_OVR_TSK_TIME_HOUR = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_OVR_TSK_TIME_HOUR, 19);
	public static final int CMPT_BUY_SENDITEM_DELAY = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_SENDITEM_DELAY, 0);
	public static final int CMPT_BUY_SENDITEM_START_WEEK_DAY = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_BUY_SENDITEM_START_WEEK_DAY, 7);
	public static int[][] CMPT_ITEM_ID_NUM_COST_ID_NUMS = {{5274,4879,1,1},{5275,4879,1,10},{5276,4479,1,100}};

	//用于新手保护的buff参数
    public static final double[] HIDE_BUFF_FOR_NEWER = { 3.827870, 3.705238, 3.586535, 3.471634, 3.360415, 3.252758, 3.166978, 3.083460, 3.002144, 2.922973, 2.845890, 2.770839, 2.557466, 2.360524,
            2.006487, 1.705886, 1.615657, 1.530201, 1.449264, 1.374493, 1.303579, 1.236324, 1.196598, 1.158148, 1.120934, 1.084915, 1.050054, 1.033097, 1.016414, 1.000000 };

	//新手保护：第一次抽黄金彩盒时必得的精良武器或饰品
    public static final HashMap<Integer, Integer> CHEST_WEAPON_FOR_NEWER = new HashMap<Integer, Integer>() {
		/**
		 *
		 */
		private static final long serialVersionUID = 2591852305479653215L;
		//characterID,sys_item_ID
        {
            put(1, 4766);
        }
        {
            put(2, 4767);
        }
        {
            put(3, 4768);
        }
        {
            put(4, 4769);
        }
        {
            put(5, 4770);
        }
        {
            put(6, 4771);
        }
        {
            put(7, 5337);
        }
	};
//	public static final HashMap<Integer, Integer> CHEST_CLOTHES_FOR_NEWER=new HashMap<Integer, Integer>()
//	{
//		/**
//		 *
//		 */
//		private static final long serialVersionUID = -362605340279211849L;
//		 //characterID,sys_item_ID
//		{put(1,4847);}
//		{put(2,4848);}
//		{put(3,4849);}
//		{put(4,4850);}
//		{put(5,4851);}
//		{put(6,4852);}
//		{put(7,5340);}
//	};

	//不同vip等级所需经验
	public static final int[] VIP_LEVEL_EXP={0,18240,52800,115840,216000,361920,562240,825600,24420600};
	//vip 每日，每周任务多获得的奖励百分比
	public static final int[] VIP_MISSION_EX_EARN_PER={50,60,70,80,90,100,110,120,120};
	//vip 退出战队保留战队经验百分比
	public static final int[] VIP_REMAINS_TEAM_EXP_PER={30,40,50,60,60};
	//vip 每日战队战可或贡献上限增加值
	public static final int[] VIP_TEAM_EXP_ADD={100,200,400,400,400};
	//vip 充能额外获得百分比
	public static final float[] VIP_BURN_EX_EARN_PER={1.5f,1.6f,1.7f,1.8f,1.9f,2.0f,2.1f,2.2f,2.2f};
	//vip 各种行为获得的升级经验
	//签到  , 在线时长 , 每日任务 , 过关结算
	public static final int[][] VIP_UPGRADE_EXP={{400},{120,240,360},{160,200,240},{40,20,10}};
	public static final int VIP_CHARGE_EXP = 100;   //1RMB , 非vip充值也可获得
	public static final int VIP_BURN_ITEM_EXP=1000;
	//vip 免费预补签次数
	public static final int[] VIP_DAILY_CHECK_COUNT = {0,3,3,4,4,5,5,6,6,6};
	//vip 额外增加的强化成功率
	public static final int[] VIP_STRENGTHEN_EX_PER={5,10,15,20,20};

	public  static enum VIP_EARN_EXP_METHODS{
		DAILYCHECK(0), ONLINETIME(1), DAILYMISSIONSIMPLE(2), DAILYMISSIONNORMAL(3), DAILYMISSIONHARD(4), VIPEXPITEM(5), STAGECLEAR(6), CHARGE(7); //充值

		final int value;

		private VIP_EARN_EXP_METHODS(int value){
			this.value=value;
		}

		public int getValue(){
			return this.value;
		}
	}

	public static final int UNABLE_ENTER_TEAM_TIME=1*24*60*60;

	public static final int TEAM_EXP_STANDARD_EXPIRE_TIME=5*24*60*60;

	public static final int DEFAULT_TEAM_LEVEL_RES_ID = 185;				//默认所有战队地图的基地图ID

	/**
	 * @author zhang.li
	 */
	public enum BOUNDS{
		/** 每日奖励数值 */
		DAILY_CHECK_DIS_RES_BOUND(1000),
		/** 在线时长奖励黑原石数基数 */
		ONLINE_DIS_RES_BOUND_BASIC(100);
		private int bounds;

		private BOUNDS(int bounds){
			this.bounds=bounds;
		}

		public int getBounds(){
			return this.bounds;
		}
	}

    /**
	 * 资源争夺战Constant类
	 * @author zhang.li
	 */
	public static class TeamSpaceConstants {
        /***************************************************************************************************************
		 * 公有
		 **************************************************************************************************************/
		public enum FightType{
			MATCH(0) ,CHALLENGE(1);
			private int value;

			private FightType(int value){
				this.value=value;
			}

			public int getValue(){
				return this.value;
			}

			public static FightType getFightByValue(int value){
				switch (value) {
                case 0:
                    return MATCH;
                case 1:
                    return CHALLENGE;
                default:
                    return null;
				}
			}
		}

		//资源争夺战兑换物品锁定Exchange值
		public static final int EXCHANGE_LOCK=3;
		//资源争夺战兑换物品解锁Exchange值
		public static final int EXCHANGE_UNLOCK=2;

		public static final int FIGHT_TYPE_MATCH=1;
		public static final int FIGHT_TYPE_CHALLENGE=2;

		/** 秒建造物品类型 */
		/** 秒一半 */
		public static final int FINISH_TYPE_HALF=1;
		/** 全额秒 */
		public static final int FINISH_TYPE_FULL=2;

		/** 资源争夺攻城站开始时间每日HOUR */
		public static final int CMPT_RESWAR_STT_TIME_HOUR = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_RESWAR_STT_TIME_HOUR, 18);
		/** 资源争夺攻城站开始日 SUNDAY = 1 到 SATURDAY = 7 */
		public static final int CMPT_RESWAR_STT_WEEK_DAY = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_RESWAR_STT_WEEK_DAY, 7);
		/** 资源争夺攻城站结束时间每日HOUR */
		public static final int CMPT_RESWAR_END_TIME_HOUR  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_RESWAR_END_TIME_HOUR, 21);
		/** 资源争夺攻城站结束日 SUNDAY = 1 到 SATURDAY = 7 */
		public static final int CMPT_RESWAR_END_WEEK_DAY  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_RESWAR_END_WEEK_DAY, 7);

		public static final int CMPT_RESWAR_SEND_RES_WEEK_HOUR  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_RESWAR_SEND_RES_WEEK_HOUR, 2);
		public static final int CMPT_RESWAR_SEND_RES_WEEK_DAY  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_CMPT_RESWAR_SEND_RES_WEEK_DAY, 2);

		/** 资源争夺战可匹配队伍最小数量 */
		public static final int RES_BATTLE_MATCH_MIN  = StringUtil.getIntParam(ConfigurationUtil.DEFAULT_RES_BATTLE_MATCH_MIN, 200);

		/** 商店中适用于团队的subtype */
		public static final int[] SHOP_TEAM_SUPTYPES = { 1, 2 };
		/** 商店中适用于个人的subtype */
		public static final int[] SHOP_PERSONAL_SUPTYPES = {3 };

		/** 战队基础资源转换率 */
		public static final int TRANSITION_RESOURCE_TEAM_BASIC = 100000;

		/** 资源争夺战活跃 */
		public static final int TEAM_SAPCE_ACTIVE = 1;
		/** 资源争夺战不活跃 */
		public static final int TEAM_SAPCE_DISACTIVE = 0;

		/** 玩家基础资源转换率 */
		public static final int TRANSITION_RESOURCE_PLAYER_BASIC = 200;

		/** 每个队伍最多被开战的次数 */
		public static final int MAX_BE_ONFIRE_TIMES = 5;

		/** 最多进行匹配的次数 */
		public static final int MAX_MATCH_TIMES = 100;

		/** 进行匹配玩家所消耗的黑晶石数量 */
		public static final int MATCH_COST_STONES = 100;

		/** 已经失败N次，则尝试进行人机匹配的基数N */
		public static final int MIN_MATCH_COMPUTER = 3;

		/** 每次能抢到当前资源的1/10 */
		public static final int robDivide = 10;

		/** 死鱼队伍可以被抢的可用资源数，后期要改成可以配置的 */
		public static final int DEAD_FISH_RES_FOR_ROB=100000;

		// CACHE TIME
		/** 匹配锁定时长 */
		public static final int BATTLEFIELD_CACHE_MATCH_LIFE_TIMEOUT = 40;// 40秒秒

		/** 匹配锁定时长 */
		public static final int BATTLEFIELD_CACHE_MATCH_TIMEOUT = 40000;// 40秒

		/** 匹配每局时长 */
		public static final int BATTLEFIELD_CACHE_GAMEROUND_LIFE_TIMEOUT = 600;// 10分钟

		/** 匹配每局时长 */
		public static final int BATTLEFIELD_CACHE_GAMEROUND_TIMEOUT = 360000;//6分钟

		/** 挑战赛每局时长 */
		public static final int BATTLEFIELD_CACHE_CHALLENGE_LIFE_TIMEOUT = 360;// 6分钟

		/** 每局挑战赛游戏最长时间 */
		public static final int BATTLEFIELD_CACHE_CHALLENGE_TIMEOUT = 360000;// 6分钟

		/** 挑战赛资源情况记录 */
		public static final int BATTLEFIELD_CACHE_HILLSTU_LIFE_TIMEOUT = 18000;//5个小时

		/** 挑战赛资源情况记录 */
		public static final int BATTLEFIELD_CACHE_HILLSTU_TIMEOUT = 18000000;// 5小时

		/** 失败次数记时时间 */
		public static final int BATTLEFIELD_CACHE_LOSE_LIFE_TIMEOUT = 10800;// 3小时

		/** 失败次数记时时间 */
		public static final int BATTLEFIELD_CACHE_LOSE_TIMEOUT = 10800000;// 3小时

		//进行原油转换（购买） 个人商店使用的FC基数
		public static final int[] SHOP_TRANSFORM_PERSONAL_BASIC_FC={100,200,400,800,1600};
		//进行原油转换（购买） 团队商店使用的FC基数
		public static final int[] SHOP_TRANSFORM_TEAM_BASIC_FC={100,500,2500,12500,62500};
		//转换比例
		/** 个人转换黑原石,1 FC= 能够转换 12 个人黑晶石 ，并消耗100倍个人黑原石 */
		public static final int P_TF_RATE=12;
		/** 消耗100倍个人黑原石 */
		public static final int P_TF_COST_DIS_RATE=100;
		/** 团队转换黑原石,1 FC= 能够转换 0.3 团队黑晶石 ，并消耗100倍团队黑原石 */
		public static final double T_TF_RATE=0.3;
		/** 消耗100倍团队黑原石 */
		public static final int T_TF_COST_DIS_RATE=100;
		/** 团队直接购买 黑原石,1 FC= 能够购买0.25 团队黑晶石 ，不消耗团队黑原石 */
		public static final double T_BUY_RATE=0.25;
		/** 帮助团队进行资源争夺战贡献的返利，1FC返利 个人 6黑晶石 */
		public static final int P_RE_RATE=6;

		/** 个人转换type */
		public static final int SHOP_P_TF=1;
		/** 团队转换type */
		public static final int SHOP_T_TF=2;
		/** 个人给团队购买type */
		public static final int SHOP_T_BUY=3;

        /***************************************************************************************************************
		 * 页面显示的转换购买的商店
		 * @author zhang.li
		 */
		public static enum TransformSHOP {
			//转换个人
			PersonalTransform(SHOP_P_TF, new int[][] { //消耗的FC                          ，消耗的原石                                                          ,得到的黑晶石                                  ，奖励的个人黑晶石
									{SHOP_TRANSFORM_PERSONAL_BASIC_FC[0], SHOP_TRANSFORM_PERSONAL_BASIC_FC[0]*P_TF_RATE*P_TF_COST_DIS_RATE  ,SHOP_TRANSFORM_PERSONAL_BASIC_FC[0]*P_TF_RATE,0},
									{SHOP_TRANSFORM_PERSONAL_BASIC_FC[1], SHOP_TRANSFORM_PERSONAL_BASIC_FC[1]*P_TF_RATE*P_TF_COST_DIS_RATE  ,SHOP_TRANSFORM_PERSONAL_BASIC_FC[1]*P_TF_RATE,0},
									{SHOP_TRANSFORM_PERSONAL_BASIC_FC[2], SHOP_TRANSFORM_PERSONAL_BASIC_FC[2]*P_TF_RATE*P_TF_COST_DIS_RATE  ,SHOP_TRANSFORM_PERSONAL_BASIC_FC[2]*P_TF_RATE,0},
									{SHOP_TRANSFORM_PERSONAL_BASIC_FC[3], SHOP_TRANSFORM_PERSONAL_BASIC_FC[3]*P_TF_RATE*P_TF_COST_DIS_RATE  ,SHOP_TRANSFORM_PERSONAL_BASIC_FC[3]*P_TF_RATE,0},
                            { SHOP_TRANSFORM_PERSONAL_BASIC_FC[4], SHOP_TRANSFORM_PERSONAL_BASIC_FC[4] * P_TF_RATE * P_TF_COST_DIS_RATE, SHOP_TRANSFORM_PERSONAL_BASIC_FC[4] * P_TF_RATE, 0 } }),
			//转换团队
			TeamTransofrm(SHOP_T_TF, new int[][] {
					//消耗的FC							，消耗的原石									   									 ,得到的黑晶石									 ，奖励的个人黑晶石
                    { SHOP_TRANSFORM_TEAM_BASIC_FC[0], new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[0] * T_TF_RATE * T_TF_COST_DIS_RATE).intValue(),
                            new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[0] * T_TF_RATE).intValue(), SHOP_TRANSFORM_TEAM_BASIC_FC[0] * P_RE_RATE },
                    { SHOP_TRANSFORM_TEAM_BASIC_FC[1], new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[1] * T_TF_RATE * T_TF_COST_DIS_RATE).intValue(),
                            new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[1] * T_TF_RATE).intValue(), SHOP_TRANSFORM_TEAM_BASIC_FC[1] * P_RE_RATE },
                    { SHOP_TRANSFORM_TEAM_BASIC_FC[2], new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[2] * T_TF_RATE * T_TF_COST_DIS_RATE).intValue(),
                            new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[2] * T_TF_RATE).intValue(), SHOP_TRANSFORM_TEAM_BASIC_FC[2] * P_RE_RATE },
                    { SHOP_TRANSFORM_TEAM_BASIC_FC[3], new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[3] * T_TF_RATE * T_TF_COST_DIS_RATE).intValue(),
                            new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[3] * T_TF_RATE).intValue(), SHOP_TRANSFORM_TEAM_BASIC_FC[3] * P_RE_RATE },
                    { SHOP_TRANSFORM_TEAM_BASIC_FC[4], new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[4] * T_TF_RATE * T_TF_COST_DIS_RATE).intValue(),
                            new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[4] * T_TF_RATE).intValue(), SHOP_TRANSFORM_TEAM_BASIC_FC[4] * P_RE_RATE } }),
			//团队购买
			TeamBuy(SHOP_T_BUY, new int[][] {
					//消耗的FC				  ，消耗的原石  ,得到的黑晶石									                                          ，奖励的个人黑晶石
										{SHOP_TRANSFORM_TEAM_BASIC_FC[0], 0,new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[0]*T_BUY_RATE).intValue(),SHOP_TRANSFORM_TEAM_BASIC_FC[0]*P_RE_RATE},
										{SHOP_TRANSFORM_TEAM_BASIC_FC[1], 0,new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[1]*T_BUY_RATE).intValue(),SHOP_TRANSFORM_TEAM_BASIC_FC[1]*P_RE_RATE},
										{SHOP_TRANSFORM_TEAM_BASIC_FC[2], 0,new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[2]*T_BUY_RATE).intValue(),SHOP_TRANSFORM_TEAM_BASIC_FC[2]*P_RE_RATE},
										{SHOP_TRANSFORM_TEAM_BASIC_FC[3], 0,new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[3]*T_BUY_RATE).intValue(),SHOP_TRANSFORM_TEAM_BASIC_FC[3]*P_RE_RATE},
                    { SHOP_TRANSFORM_TEAM_BASIC_FC[4], 0, new Double(SHOP_TRANSFORM_TEAM_BASIC_FC[4] * T_BUY_RATE).intValue(), SHOP_TRANSFORM_TEAM_BASIC_FC[4] * P_RE_RATE } });
			private int type;
			private int[][] costs;//{消耗FC ，需要转换的原油 ，转换所得的原油，奖励的个人原油数}

			private TransformSHOP(int type, int[][] costs) {
				this.type = type;
				this.costs = costs;
			}

			public int getType() {
				return type;
			}

			public void setType(int type) {
				this.type = type;
			}

			public int[][] getCosts() {
				return costs;
			}

			public void setCosts(int[][] costs) {
				this.costs = costs;
			}
		}

		                /**
		 * 挑战队长获得黑晶石的数量
		 * @param fc 消耗的FC点
		 * @param scale 打掉的血量比
		 * @return
		 */
		public static int getLeaderReturn(int fc,double scale){
			return (int)(fc*scale*3*6);
		}

		                /**
		 * 挑战队员获得黑原石的数量
		 * @param fc 消耗的FC点
		 * @param scale 打掉的血量比
		 * @return
		 */
		public static int getMemberDisReturn(int fc,double scale){
			return (int)(fc*scale*100);
		}

        /***************************************************************************************************************
		 * 私有
		 **************************************************************************************************************/

        /***************************************************************************************************************
		 * mcc前缀
		 **************************************************************************************************************/
		/** 战场匹配前缀 */
		private static final String BATTLEFIELD_MATCH_PREFIX = "bfmatchpre:";

		/** 资源争夺战战场状况前缀 */
		public static final String BATTLEFIELD_STATUS_PREFIX = "bfstupre:";
		/** 资源争夺战开战前缀 */
		public static final String BATTLEFIELD_START_PREFIX = "bfstartpre:";
		/** 资源争夺战连续失败前缀 */
		private static final String BATTLEFIELD_LOSE_PREFIX = "bflosepre:";
		/** 资源争夺战挑战赛前缀 */
		public static final String BATTLEFIELD_CHALLENGE_PREFIX = "bfchapre:";
		/** 资源争夺战挑战赛矿山资源前缀 */
		public static final String BATTLEFIELD_CHALLENGE_HILL_PREFIX = "bfhillpre:";

		public static final int CHALLENGE_BASIC_COST=1000;

		                /**
		 * 根据矿山剩余资源获得挑战需要的费用
		 * @param hillLeftZY
		 * @return
		 */
		public static int getChallengeFCCostByKSZY(int hillLeftZY){
			Double add=hillLeftZY/25*0.05*0.25*100;
			return add.intValue();
		}

		                /**
		 * 根据rank排名获得矿山初始化资源数
		 * @param rank
		 * @return
		 */
		public static int getdefHillStoneByRank(int rank){
			return new Double(6000*Math.pow(1.10409, (15-rank))).intValue();
		}

		                /**
		 * 获得根据队伍获得队伍teamBattleKey
		 * @param team
		 * @return
		 */
		public static String getTeamBattleStatusKey(int teamId) {
			return BATTLEFIELD_STATUS_PREFIX + teamId;

		}

		                /**
		 * 获得根据队伍获得防守队伍被挑战的key
		 * @param team
		 * @return
		 */
		public static String getZYZDZChallengeKey(int defTeamId) {
			return BATTLEFIELD_CHALLENGE_PREFIX + defTeamId;

		}

		                /**
		 * 获得根据队伍获得防守队伍矿山信息key
		 * @param team
		 * @return
		 */
		public static String getChallengeHillKey(int defTeamId) {
			return BATTLEFIELD_CHALLENGE_HILL_PREFIX + defTeamId;

		}

        /**
		 * 获得根据队伍获得队伍teamBattleKey
		 * @param team
		 * @return
		 */
		public static String getTeamBattleStartKey(int teamId) {
			return BATTLEFIELD_START_PREFIX + teamId+"-"+java.util.UUID.randomUUID();

		}

		public static String getTeamBattleStartKey2Client(String key) {
			String [] sArea=key.split(":");
			String result=sArea[0]+"="+"\""+sArea[1]+"\"";
			return result;
		}

		                /**
		 * 获得资源争夺站队伍失败次数key
		 * @param teamId
		 * @return
		 */
		public static String getTeamBattleFieldLoseKey(int teamId){

			return BATTLEFIELD_LOSE_PREFIX + teamId;
		}

        /**
		 * 获得根据队伍获得队伍teamMatchKey
		 * @param team
		 * @return
		 */
		public static String getTeamMatchKey(int teamId) {
			return BATTLEFIELD_MATCH_PREFIX + teamId;
		}

		public static final int TEAM_ORG_RES_CONVERT_INTERVAL = 3600;   //秒
		public static final int TEAM_ORG_RES_PRODUCT_INTERVAL = 3600 * 4;   //秒
		public static final int PERSON_ORG_RES_CONVERT_INTERVAL = 3600;   //秒
		public static final int PERSON_BASE_NON_CONVERT_RES = 5000;  //个人 转换时 的下限
		public static final int TEAM_ORG_TO_RES_SCALE = 100;  //战队 原石转换精石比例
		public static final int PERSON_ORG_TO_RES_SCALE = 100;  //个人 原石转换精石 比例

	}

	final public static int MAX_TEAM_PLACE_LEVEL = 9;
//	/**
	//	 * 战队空间可配置物品的数量
//	 *
	//	 * @param placeLevel  战队空间等级
//	 *
//	 * @return
//	 */
//	public static  Map<Integer, Integer> getTeamItemDeploy(int placeLevel){
	//		//map[战队空间等级，[teamItemId，数量]]
//		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer,Integer>>();
//
//			Map<Integer, Integer> m = new HashMap<Integer, Integer>();
//			m.put(5434, 10);
//			m.put(5435, 10);
//			map.put(1, m);
//			Map<Integer, Integer> m2= new HashMap<Integer, Integer>();
//			m2.put(5434, 20);
//			m2.put(5435, 20);
//			map.put(2, m2);
//			//...
//		return map.get(placeLevel);
//	}
	//	public static final int TEAM_ITEM_MAX_UP_LEVEL      = 30;//最高升级等级
//	public static final int PRE_WEEK_RES_TOP_SIZE       = 15;
//
//
//	public enum TEAM_STORAGE_TYPE{
	//		DEFENSE_TOWER(1),//防御塔1,城墙台阶2
//		WALL(2);
//		//STAIR(3);
//		private int value;
//
//		private TEAM_STORAGE_TYPE(int value) {
//			this.value = value;
//		}
//		public int getValue(){
//			return this.value;
//		}
//	}
//	public enum TEAM_ITEM_MANAGE_TYPE{
	//		TEAM_PALCE_UP(1),//升级战队空间
	//		//DEPLOY(2),//配置
	//		//REPAIR_TEAM_ITEM(3),//修理
	//		//SUPPLY(4),//补给
//
	//		TEAM_ITEM_UP(5);//升级//INTENSIFY_TEAM_ITEM(2),//强化
	//		//SUPPLY_ALL(6),//全部补给
	//		//REPAIR_ALL(7),//全部修理
	//		//DELETE_TEAM_ITEM(8);//删除
//
//
//		private int value;
//		private TEAM_ITEM_MANAGE_TYPE(int value){
//			this.value = value;
//		}
//		public int getValue(){
//			return this.value;
//		}
//	}
//
//	/**
	//	 * 修理，补给参数
	//	 * 每小时低保精制油*24*Ingame系数K*损耗度/战队防御设施总数系数M
//	 * @param durable
//	 * @return
//	 */
//	public static final double ONE_HOUR_CONSUME=10000;
//	public static final double INGAME_K = 0.1;
//	public static final Map<Integer, Integer> TEAM_PLACE_LEVEL_PARAM = new HashMap<Integer, Integer>(){
//		private static final long serialVersionUID = -362605340279211849L;
//		{put(1,16);}
//		{put(2,24);}
//		{put(3,32);}
//		{put(4,40);}
//		{put(4,48);}
//	};
//	/**
	//	 * 获得强化属性
	//	 * 能力上涨公式：最大能力幅度^(本级强化等级/最大强化等级）
//	 * @param baseValue
//	 * @param level
//	 * @return
//	 */
//	public static double getTeamItemIntensify(int intensifyType,int level){
//		double baseValue = TeamItemPropertyType.getTeamItemPropertyType(intensifyType).getBaseValue();
//		double value = Math.pow(baseValue, (double)(level)/90);
//		BigDecimal b = new BigDecimal(value);
//		MathContext mathContext = new MathContext(5);
//
//		return b.round(mathContext).doubleValue();
//	}
//	public enum TeamItemPropertyType{
//
	//		POWER(1,2.00),//威力
	//		FIRE_SPEED(2,1.20),//射速
//		HP(3,2.00),
	//		FIRE_DISTANCE(4,1.20),//射程
	//		ROTATE_SPEED(5,2.00);//转速
//		private int type;
	//		//最大能力幅度
//		private double baseValue;
//		public int getType() {
//			return type;
//		}
//
//		public double getBaseValue() {
//			return baseValue;
//		}
//
//		TeamItemPropertyType(int type,double baseValue){
//			this.baseValue = baseValue;
//			this.type = type;
//		}
//		public static TeamItemPropertyType getTeamItemPropertyType(int type){
//			for(TeamItemPropertyType t:TeamItemPropertyType.values()){
//				if(t.getType()==type){
//					return t;
//				}
//			}
//			return null;
//		}
//	}
//	public static final Map<TeamItemPropertyType,Integer> TEAM_ITEM_PROPERTY_AND_ADD = new HashMap<Constants.TeamItemPropertyType, Integer>(){
//		private static final long serialVersionUID = 1775629518746546345L;
//		{put(TeamItemPropertyType.POWER,5);}
//		{put(TeamItemPropertyType.HP,4);}
//		{put(TeamItemPropertyType.ROTATE_SPEED,3);}
//		{put(TeamItemPropertyType.FIRE_SPEED,2);}
//		{put(TeamItemPropertyType.FIRE_DISTANCE,1);}
//	};
//	/**
	//	 * 获得强化价格
//	 * @param level
//	 * @param type
//	 * @return
//	 */
//	public static int getTeamItemIntensifyPrice(int currency,int level,int type){
	//		//cr的价格
//		int price=(int)Math.round(36*TEAM_ITEM_PROPERTY_AND_ADD.get(TeamItemPropertyType.getTeamItemPropertyType(type))*Math.pow(1.1005,level));
//		if(currency==CR_PAY){
//
//		}else if(currency==RES_PAY_TEAM){
//			price = price*10;
//		}
//		return price;
//	}
	//	//wId塔种类倍率
//
//	/**
	//	 * 将钱统一转成exp
//	 * @param currency
//	 * @param value
//	 * @return
//	 */
//	public static int CRAndRESChangeToExp(int currency,int value){
//		if(currency==CR_PAY){
//			return value*10;
//		}else if(currency==RES_PAY_TEAM){
//			return value;
//		}
//		return 0;
//	}/**
	//	 * 将exp转成钱
//	 * @param currency
//	 * @param value
//	 * @return
//	 */
//	public static int expChangeToCROrRES(int currency,double curExp){
//		if(currency==CR_PAY){
//			return (int)Math.round(curExp/10);
//		}else if(currency==RES_PAY_TEAM){
//			return (int)Math.round(curExp);
//		}
//		return 0;
//	}

	public static final float TEAM_RES_PRECENT_AT_END_OF_ZYZDZ = 0.99f; //资源争夺战 战队获得资源比例
	public static final float PERSONAL_RES_PRECENT_AT_END_OF_ZYZDZ = 0.01f; //资源争夺战 个人获得资源比例
	public static final float CAPTAIN_PERCENT_AT_END_OF_ZYZDZ = 0.2f;  //资源争夺战 过关结算 队长权重
	public static final float DEF_PERCENT_AT_END_OF_ZYZDZ = 1 / 4f;			//防守 方奖励力度
	public static final float ATT_WINNER_ADDED_PER_AT_END_OF_ZYZDZ = 0.1f; //进攻胜利方 额外加成
	public static final float DEF_WINNER_ADDED_PER_AT_END_OF_ZYZDZ = 0.1f; //防守胜利方 额外加成
	public static final int ZYZDZ_ATT_TEAM_MAX_PERSON = 6; //资源争夺战 进攻方参战人数
	public static final int ZYZDZ_DEF_TEAM_MAX_PERSON = 4; //资源争夺战 防守方参战人数

	public static final int SYSTEM_TEAM_HEAD_QUARTERS = 5454;  //资源争夺战中大本营
	public static final int SYSTEM_PUT_WEAPON_FOR_ZYZDZ = 5426; //资源争夺战编辑模式默认带入摆放道具

	public static enum BattleFieldRobDailyType{
		MATCH(1),CHALLENGE(2);
		int value;

		private BattleFieldRobDailyType(int value){
			this.value=value;
		}

		public  int getValue(){
			return this.value;
		}
	}

	public static enum TEAMSPACELEVELCONSTANTS{
		//基数
		TEAMSPACELEVELBASE(0,		10000000, 24000,		100000000,	500000,		1000 ,			200,150000,100,8 ,8 ,1,1,1),
		//																			基数1000 		//基数200
		//				   战队等级	战队原石上限 个人原石上限    战队精石上限   个人精石上线  战队资源转换数加成  个人资源转换数加成
//		TEAMSPACELEVEL1   (1		,10000000,24000,		1000000,  	500000,		0    ,			0   ,			150000,100,8 ,8 ,1,1,1),
//		TEAMSPACELEVEL2   (2		,12000000,25200,		1200000,  	500000,		200  ,			10  ,			180000,120,10,10,2,2,1),
//		TEAMSPACELEVEL3   (3		,14000000,26400,		1400000,  	500000,		400  ,			20  ,			210000,140,13,13,2,2,2),
//		TEAMSPACELEVEL4   (4		,16000000,27600,		1600000,  	500000,		600  ,			30  ,			240000,160,16,16,3,3,2),
//		TEAMSPACELEVEL5   (5		,18000000,28800,		1800000,  	500000,		800  ,			40  ,			270000,180,20,20,4,4,3);

        TEAMSPACELEVEL1(1, 10000000, 2400000, 500000000, 500000000, 0, 0, 100000 * 4, 100, 8, 8, 1, 1, 1), TEAMSPACELEVEL2(2, 12000000, 2520000, 500000000, 500000000, 200, 10, 120000 * 4, 120, 10,
                10, 2, 2, 1),
//		TEAMSPACELEVEL1   (1		,10000000,999924000,	500000000,  500000000,		0    ,			0   ,			150000,100,8 ,8 ,1,1,1),
//		TEAMSPACELEVEL2   (2		,12000000,999925200,	500000000,  500000000,		200  ,			10  ,			180000,120,10,10,2,2,1),
        TEAMSPACELEVEL3(3, 14000000, 2640000, 500000000, 500000000, 400, 20, 140000 * 4, 140, 13, 13, 2, 2, 2), TEAMSPACELEVEL4(4, 16000000, 2760000, 500000000, 500000000, 600, 30, 160000 * 4, 160,
                16, 16, 3, 3, 2), TEAMSPACELEVEL5(5, 18000000, 2880000, 500000000, 500000000, 800, 40, 180000 * 4, 180, 20, 20, 4, 4, 3);
		private int level;							//战队等级
		private int tMaxOrgRes;						//战队原石上线
		private int pMaxOrgRes;						//个人原石上线
		private int tMaxRes;						//战队精石上线
		private int pMaxRes;						//个人精石上线
		private int tOrgResConvertNum;				//战队资源转换数
		private int pOrgResConvertNum;				//个人资源转换数
		private int tOutPutOrgRes;					//战队单位时间产出原石量 (4小时结算一次)
		private int tGiftRes;						//单位时间系统送给战队精石量
		private int maxCreateTowerTower;			//可以建造每种塔的上限
		private int maxtakeInTowerTower;				//可以带入游戏每种塔的上限
		private int maxCreateWall;				//可以建造的城墙
		private int maxtakeInWall;				//可以带入游戏的城墙
		private int maxCreateColletter;				//可以建造资源收集器数量

        TEAMSPACELEVELCONSTANTS(int level, int tMaxOrgRes, int pMaxOrgRes, int tMaxRes, int pMaxRes, int tOrgResConvertNum, int pOrgResConvertNum, int tOutPutOrgRes, int tGiftRes,
                int maxCreateTowerTower, int maxtakeInTowerTower, int maxCreateWall, int maxtakeInWall, int maxCreateColletter) {
			this.level=level;
			this.tMaxOrgRes=tMaxOrgRes;
			this.pMaxOrgRes=pMaxOrgRes;
			this.tMaxRes=tMaxRes;
			this.pMaxRes=pMaxRes;
			this.tOrgResConvertNum=tOrgResConvertNum;
			this.pOrgResConvertNum=pOrgResConvertNum;
			this.tOutPutOrgRes=tOutPutOrgRes;
			this.tGiftRes=tGiftRes;
			this.maxCreateTowerTower=maxCreateTowerTower;
			this.maxtakeInTowerTower=maxtakeInTowerTower;
			this.maxCreateWall=maxCreateWall;
			this.maxCreateColletter=maxCreateColletter;
			this.maxtakeInWall=maxtakeInWall;
		}

		public int gettMaxOrgRes() {
			return tMaxOrgRes;
		}

		public int getpMaxOrgRes() {
			return pMaxOrgRes;
		}

		public int gettOrgResConvertNum() {
			return tOrgResConvertNum;
		}

		public int getpOrgResConvertNum() {
			return pOrgResConvertNum;
		}

		public int gettOutPutOrgRes() {
			return tOutPutOrgRes;
		}

		public int gettGiftRes() {
			return tGiftRes;
		}

		public int getLevel() {
			return level;
		}

		public int gettMaxRes() {
			return tMaxRes;
		}

		public int getpMaxRes() {
			return pMaxRes;
		}

		public int getMaxCreateTowerTower() {
			return maxCreateTowerTower;
		}

		public int getMaxtakeInTowerTower() {
			return maxtakeInTowerTower;
		}

		public int getMaxtakeInWall() {
			return maxtakeInWall;
		}

		public int getMaxCreateWall() {
			return maxCreateWall;
		}

		public int getMaxCreateColletter() {
			return maxCreateColletter;
		}

		public static TEAMSPACELEVELCONSTANTS getTeamSpaceLevelConstants(int teamSpaceLevel){
			for(TEAMSPACELEVELCONSTANTS tsl : TEAMSPACELEVELCONSTANTS.values() ){
				if (tsl.level == teamSpaceLevel) {
					return tsl;
				}
			}
			return null;
		}
	}

	public static final int DEFAULT_DIE_BUFF_IID = 84;  //死亡奖励 iid

	public static long OneDay=1000l*60*60*24;
	//死亡奖励的各种buff
//	enum EEffect
//	{
//		kEffect_None = 0,
//
	//		// 时长+间隔Start
	//		kEffect_IntervalBase_Start = 10,		//时长+间隔类型开始标志（无实际用途）
//
	//		kEffect_Sustain_HurtBurn_Replace,		//替换型持续燃烧伤害，value=伤害值
	//		kEffect_Sustain_HurtBloodshed_Replace,	//替换型持续流血伤害，value=伤害值
	//		kEffect_Sustain_HurtPoison_Replace,		//替换型持续毒伤害，value=伤害值
	//		kEffect_Sustain_HurtBurn,				//持续燃烧伤害，value=伤害值
	//		kEffect_Sustain_HurtBloodshed,			//持续流血伤害，value=伤害值
	//		kEffect_Sustain_HurtPoison,				//持续毒伤害，value=伤害值
	//		kEffect_Sustain_HpRecover,				//持续回复Hp（受攻击暂时停止），value=回复值
	//		kEffect_Sustain_HpRecover2,				//持续回复Hp（受攻击和移动暂时停止），value=回复值
	//		kEffect_Sustain_AmmoRecover,			//持续回复Ammo（受攻击暂时停止），value=回复值
	//		kEffect_Sustain_AmmoRecover2,			//持续回复Ammo（受攻击和移动暂时停止），value=回复值
	//		kEffect_Sustain_ArmorRecover,			//持续回复Armor（受攻击暂时停止），value=回复值
	//		kEffect_Sustain_ArmorRecover2,			//持续回复Armor（受攻击和移动暂时停止），value=回复值
//
	//		kEffect_IntervalBase_End,				//时长+间隔类型结束标志（无实际用途）
	//		// 时长+间隔End
//
	//		// 时长Start
	//		kEffect_TimeBase_Start = 500,			//时长类型开始标志（无实际用途）
//
	//		kEffect_Invincible,						//无敌，value=未使用
	//		kEffect_Infect_Score,					//分数+，value=分数变动百分比
	//		kEffect_Infect_Damage,					//威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id1,		//对火箭兵威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id2,		//对重机枪手威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id3,		//对狙击手威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id4,		//对突击手威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id5,		//对火焰兵威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id6,		//对医疗兵威力+，value=威力变动百分比
	//		kEffect_Infect_CareerDamage_Id7,		//对工程兵威力+，value=威力变动百分比
	//		kEffect_Infect_HpInfectDamage,			//血量影响威力+，value=威力变动百分比
	//		kEffect_Infect_ResistanceAll,			//抗性All+，value=抗性变动百分比
	//		kEffect_Infect_ResistanceBullet,		//抗性Bullet+，value=抗性变动百分比
	//		kEffect_Infect_ResistanceExplode,		//抗性Explode+，value=抗性变动百分比
	//		kEffect_Infect_ResistanceClose,			//抗性Close+，value=抗性变动百分比
	//		kEffect_Infect_ResistanceFlame,			//抗性Flame+，value=抗性变动百分比
	//		kEffect_Infect_SelfResistanceAll,		//自我抗性All+，value=抗性变动百分比
	//		kEffect_Infect_SelfResistanceBullet,	//自我抗性Bullet+，value=抗性变动百分比
	//		kEffect_Infect_SelfResistanceExplode,	//自我抗性Explode+，value=抗性变动百分比
	//		kEffect_Infect_SelfResistanceClose,		//自我抗性Close+，value=抗性变动百分比
	//		kEffect_Infect_SelfResistanceFlame,		//自我抗性Flame+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id1,	//对火箭兵抗性All+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id2,	//对重机枪手抗性All+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id3,	//对狙击手抗性All+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id4,	//对突击手抗性All+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id5,	//对火焰兵抗性All+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id6,	//对医疗兵抗性All+，value=抗性变动百分比
	//		kEffect_Infect_CareerResistance_Id7,	//对工程兵抗性All+，value=抗性变动百分比
	//		kEffect_Infect_HpInfectResistance,		//血量影响抗性All+，value=抗性变动百分比
	//		kEffect_Infect_Rebirth,					//重生速度+，value=重生速度变动值
	//		kEffect_Infect_SupplyAmmo,				//弹药包补弹药量+，value=补弹药量变动百分比
	//		kEffect_Infect_SupplyHpRecover,			//医疗包补血量+，value=补血量变动百分比
	//		kEffect_Infect_CureHpRecover,			//治疗补血量+，value=补血量变动百分比
	//		kEffect_Infect_SelfHpRecover,			//自身回复补血量+，value=补血量变动百分比
	//		kEffect_Infect_Cure,					//对他人治疗量+，value=治疗量变动百分比
	//		kEffect_Infect_CureEnergy,				//治疗充能速度+，value=治疗充能速度变动百分比
	//		kEffect_Infect_GunEnergy,				//枪充能速度+，value=枪充能速度变动百分比
	//		kEffect_Infect_JumpHeight,				//跳跃高度+，value=跳跃高度变动百分比
	//		kEffect_Infect_MoveSpeed,		//539		//移动速度+，value=移动速度变动百分比
	//		kEffect_Infect_HpInfectMoveSpeed,		//血量影响移动速度+，value=移动速度变动百分比
	//		kEffect_Infect_MaxHp,					//血量上限+，value=血量上限变动百分比
	//		kEffect_Infect_AmmoOneClip,				//上膛量+，value=上膛量变动百分比
	//		kEffect_Infect_AmmoCount,				//载弹量+，value=载弹量变动百分比
	//		kEffect_Infect_FireTime,				//攻击速度+，value=攻击速度变动百分比(负值为加快)
	//		kEffect_Infect_ReloadTime,				//换弹速度+，value=换弹速度变动百分比(负值为加快)
	//		kEffect_Infect_ChangeInTime,			//切换武器速度+，value=切换武器速度变动百分比(负值为加快)
	//		kEffect_Infect_AmmoFlySpeed,			//炮弹飞行速度+，value=炮弹飞行速度变动百分比
	//		kEffect_Infect_AmmoExplodeRange,		//炮弹爆炸范围+，value=炮弹爆炸范围变动百分比
	//		kEffect_Infect_FOV,						//FOV+，value=FOV变动值
	//		kEffect_Infect_HitBack,					//被击退+，value=被击退变动值
//
	//		kEffect_Invalid_MoveSpeed = 1000,		//不受移动速度+影响，value=未使用
	//		kEffect_Invalid_Sustain_HurtBurn,		//不受持续燃烧伤害影响，value=未使用
	//		kEffect_Invalid_Sustain_HurtBloodshed,	//不受持续流血伤害影响，value=未使用
	//		kEffect_Invalid_Sustain_HurtPoison,		//不受持续毒伤害影响，value=未使用
//
	//		kEffect_Special_CannotOutFlame = 1500,	//不能使用灭火功能，value=未使用
	//		kEffect_Special_CannotHide,				//无法隐形，value=未使用
	//		kEffect_Special_CannotFire,				//无法开火，value=未使用
	//		kEffect_Special_ReversalMouse,			//鼠标反转，value=未使用
	//		kEffect_Special_ReversalMouse2,			//鼠标反转，value=未使用
	//		kEffect_Special_ReversalKeyBoard,		//键盘反转，value=未使用
	//		kEffect_Special_ViewLost,				//视野模糊，value=未使用
	//		kEffect_Special_ViewLost2,				//视野模糊，value=未使用
	//		kEffect_Special_HitTargetJump,			//被击中的玩家跳，value=跳跃比例
	//		kEffect_Special_HittedSelfJump,			//自身被击中后跳，value=跳跃比例
	//		kEffect_Special_HitAddScore,			//击中对方后加分，value=加分
	//		kEffect_Special_UsingSkill,				//正在使用技能，value=未使用
	//		kEffect_Special_Invisible,				//隐身，value=未使用
	//		kEffect_Special_Smog,					//毒气，value=未使用
//
	//		kEffect_TimeBase_End,					//时长类型结束标志（无实际用途）
	//		// 时长End
//	};

	        /**
	 * 最大精英合成等级
	 * @date 20140808 18:10
	 * @author OuYangGuang
	 */
	public static final int MAX_GST_LEVEL = 5;

    /*******************************************************************************************************************
	 * 精英合成 普通 >> 传说 每个星级对应的等级经验（固定矩形）
	 * @date 20140805 16:50
	 * @author OuYangGuang
	 ******************************************************************************************************************/
	        /*
	* public static final int[][] GST_LEVEL_EXP={ // 0-1 1-2 2-3 3-4 4-5 {
	* 100,500,1000,3000,5000, //普通 },{ 200,240,288,346,415, //精良 },{
	* 1000,1200,1440,1728,2074, //卓越 },{ 5000,6000,7200,8640,10368, //稀有 },{
	* 25000,30000,36000,43200,51840, //大师 }, };
	*/

	        /**
	 * 精英合成 普通 >> 传说 每个星级对应的等级经验（动态算法）
	 * @param gstLevel 武器当前等级
	 * @param rareLevel 武器稀有度
	 * @date 20140807 14:32
	 * @update 20150914 17:42
	 * @author OuYangGuang
	 */
	public static long getGst_Level_Exp(int gstLevel, int rareLevel) {
		// 采用算法，当前最高等级为 5(MAX_GST_LEVEL) 级
		// startLevel = startLevel > 4 ? 4 : startLevel;
		//        return Math.round((getQualityLevel(rareLevel) > 4 ? 50 : 200) * Math.pow(1.2, startLevel) * Math.pow(5, ((rareLevel - 26) / 25)));
		//FCW
		if (gstLevel >= WSysConfigService.getPlayerItemMaxGstLevel()) {//最大星级限制
			return 0;
		}
		//经验计算
		double exp = 200 * (gstLevel + 1) * ((rareLevel - 1) / 25.0 * 0.6 + 1);
		return (int) Math.round(exp);
	}

	        /**
	 * 精英合成 普通 >> 传说 武器充当材料经验（动态算法）
	 * @param startLevel 武器稀有度
	 * @date 20140807 14:32
	 * @author OuYangGuang
	 */
	public static long getGst_Level_Exp(int rareLevel) {
		//		return Math.round(47.63 * Math.pow(4.0, (rareLevel - 1.0) / 25.0));
		//FCW
		return (long) (200 * 1.6 * (rareLevel - 1) / 25);
	}

	        /**
	 * 武器稀有度区间（精良 - 传说）
	 * @date 20140814 11:27 1-25 26-50 51-75 76-100 101-125 126-150
	 */
	public static final int[] RARE_LEVEL = { 26, 51, 76, 101, 126};

	        /**
	 * 每日一次对应等级的C币花销（精良 - 传说）
	 * @date 20140814 11:27 1-25 26-50 51-75 76-100 101-125
	 */
	public static final int[] EVERY_DAY_C_POINT = { 500, 1000, 1500, 2000, 3000};

	        /**
	 * 武器稀有度区间对应品级（精良 - 传说）
	 * @date 20140805 16:50
	 * @param rareLevel 武器稀有度
	 * @return 0普通 1精良 2卓越 3稀有 4大师 5传说
	 */
	public static final int getQualityLevel(int rareLevel) {
		return (int) (Math.ceil((rareLevel-1)/25));
	}

	        /**
	 * 精英合成 武器免费每日经验 （精良>>传说）
	 * @date 20140807 13:33
	 * @author OuYangGuang
	 */
	public static final int[] EVERY_DAY_EXPERIENCE = { 100, 100, 265, 545, 675};

	        /**
	 * 武器每日免费经验领取标志位redis key
	 * @date 20140807 13:33
	 * @author OuYangGuang
	 */
	public static final String PLAYER_EVERY_DAY_E_FLAG_KEY = "RSHCFREE:";

	        /**
	 * 传说~精良 基础百分比
	 * @date 20140815 18:06
	 * @author OuYangGuang
	 */
	public static final double[][] BASE_PERCEN0T = { { 3.0, 3.0, 3.0, 3.0, 4.0 }, //武器 传说 大师 稀有 卓越 精良
			{ 3.0, 3.0, 3.0, 3.0, 3.0 },	//服饰 传说 大师 稀有 卓越 精良
			{ 3.0, 3.0, 3.0, 3.0, 3.0 }	//帽子 传说 大师 稀有 卓越 精良
		};

	        /**
	 * 星级武器根据品质不同得到的每个等级加成百分比
	 * @param rareLevel 稀有度 startLevel 星级 type 类型
	 * @return int 该装备星级对应的攻击加成百分比,稀有与大师另外增加1.2
	 * @date 20140815 18:06
	 * @author OuYangGuang
	 */
	public static final double getQualityPercent(int rareLevel,int startLevel,int type) {
		// 加成百分比
		return (BASE_PERCEN0T[type-1][BASE_PERCEN0T[type-1].length-getQualityLevel(rareLevel)])*startLevel;
	}

	        /**
	 * VIP 领取，1~14档需要经验，物品Id
	 */
    public static final int VIP_EXP_ITEMS[][] = {

	{ 1475600, 5832 }		//VIP 武器1
			, { 2125600, 5833 }		//VIP 服饰1

			, { 2883933, 5857 }		//VIP 武器2
			, { 3642266, 5859 }		//VIP 服饰2

			, { 4552266, 5858 }		//VIP 武器3
			, { 5462266, 5860 }		//VIP 服饰3

			, { 6599766, 5863 }		//VIP 武器4
			, { 7737266, 5867 }		//VIP 服饰4

			, { 9253933, 5864 }		//VIP 武器5
			, { 10770600, 5868 }	//VIP 服饰5

			, { 13045600, 5865 }	//VIP 武器6
			, { 15320600, 5869 }	//VIP 服饰6

			, { 19870600, 5866 }	//VIP 武器7
			, { 24420600, 5870 }	//VIP 服饰7

		};

	        /**
	 * 跳跳乐箱子
	 */
	public static final int[] AGRAVITYBOXITEM = { 5875, //GM 顶
			5874,	//GM 中
			5873,	//普通 顶
			5834	//普通 中
	};

	        /**
	 * 资源争夺战，团队每日原石上线 无门槛上限，2/1门槛上限
	 */
    public static final int[][] ZYZDZ_TEAM_RES_RATIO = { { 2400000, 100000 }, { 2880000, 120000 }, { 3360000, 140000 }, { 3840000, 160000 }, { 4320000, 180000 }, };

	        /**
	 * 资源争夺战，玩家每日原石上线 无门槛上限，2/1门槛上限
	 */
    public static final int[][] ZYZDZ_PLAYER_RES_RATIO = { { 480000, 20000 }, { 504000, 21000 }, { 528000, 22000 }, { 552000, 23000 }, { 576000, 24000 }, };

	        /**
	 * 生存模式，玩家获得的箱子 VIP等级,一般箱子，高积分获得的箱子
	 */
    public static final int[][] SCMS_PLAYER_BOX = { { 0, 6052, 6057 }, { 1, 6053, 6058 }, { 4, 6054, 6059 }, { 7, 6055, 6060 }, { 8, 6056, 6061 }, };
	        /**
	 * VIP箱子一天最多可以开几次
	 */
	public static final int  SCMS_BOX_COUNT_MAX				= 5;//boss pve

	public static enum KICK_PLAYER_TYPE{
		GM_KICK(CommonMsg.GM_KICK),			//GM 后台踹人
		GAMEGUARD_KICK(CommonMsg.GAMEGUARD_KICK);		//接口踹人 1\反外挂

		String value;

		private KICK_PLAYER_TYPE(String value){
			this.value=value;
		}

		public String getValue() {
			return this.value;
		}
	}

	//zlm2015-5-7-限时装备-开始
	public static final String PROVISIONAL_ITEM_FLAG_KEY = "XSZB:";//限时装备的续费标记的key
	public static final int PROVISIONAL_ITEM_SCOPE_BEGIN = 6064;//这批装备的起
	public static final int PROVISIONAL_ITEM_SCOPE_END = 6105;//这批装备的末
	public static final int PROVISIONAL_ITEM_SCOPE_BEGIN_2 = 6426;//这批装备的起
	public static final int PROVISIONAL_ITEM_SCOPE_END_2 = 6467;//这批装备的末
	final public static int[][] SXZB_CHOICE_BOX = { { 6064, 6065, 6066, 6067, 6068, 6069, 6070 },//新手武器多选一礼盒(6)6106
			{ 6071, 6072, 6073, 6074, 6075, 6076, 6077 },//进阶武器多选一礼盒(9)6107
			{ 6078, 6079, 6080, 6081, 6082, 6083, 6084 },//高手武器多选一礼盒(10)6108
			{ 6085, 6086, 6087, 6088, 6089, 6090, 6091 },//新手服装多选一礼盒(6)6109
			{ 6092, 6093, 6094, 6095, 6096, 6097, 6098 },//进阶服装多选一礼盒(9)6110
			{ 6099, 6100, 6101, 6102, 6103, 6104, 6105 },//高手服装多选一礼盒(10)6111

			{ 6426, 6429, 6432, 6435, 6438, 6441, 6444 },//新手限时武器礼包(6)6468
			{ 6427, 6430, 6433, 6436, 6439, 6442, 6445 },//进阶限时武器礼包(10)6469
			{ 6428, 6431, 6434, 6437, 6440, 6443, 6446 },//高手限时武器礼包(13)6470
			{ 6447, 6450, 6453, 6456, 6459, 6462, 6465 },//新手限时衣服礼包(6)6471
			{ 6448, 6451, 6454, 6457, 6460, 6463, 6466 },//进阶限时衣服礼包(10)6472
			{ 6449, 6452, 6455, 6458, 6461, 6464, 6467 } //高手限时衣服礼包(13)6473
	};
	//zlm2015-5-7-限时装备-结束
	//==========================
	public static final String HIT_BOSS2_BOX_COUNT_KEY    					= "hitBOSS2:b:c:";
	public static final int HIT_BOSS2__BOX_COUNT_MAX = 5;//末日进化开了几次黄金宝箱
	        /**
	 * 末日进化，玩家获得的箱子 VIP等级,一般箱子，高积分获得的箱子
	 */
    public static final int[][] HIT_BOSS2_PLAYER_BOX = { { 0, 6238, 6243 }, { 1, 6239, 6244 }, { 4, 6240, 6245 }, { 7, 6241, 6246 }, { 8, 6242, 6247 } };

	public static final String QW_DAY_KEY_PREFIX = "qw:d:";//用户单日//
	public static final String QW_SUM_KEY_PREFIX = "qw:s:";//用户总
	public static final String QW_TOP_KEY_PREFIX = "qw:t:";//排行榜
	public static final String QWZB_TOP_MCC_KEY					="qtmc";
	//K值兑换积分
    final public static int[][] QW_K_RATIO = { { 2, 10 }, { 5, 150 }, { 8, 430 }, { 11, 520 }, { 14, 930 }, { 17, 1020 }, { 20, 1510 }, { 23, 2770 }, { 26, 4270 }, { 29, 4500 }, { 32, 6010 } };
	//段位分数
    final public static int[] QW_RATIO = { 420, 923, 1500, 2760, 4267, 6000, 8520, 11535, 15000, 19200, 24225, 999999 };
	//局数
    final public static int[][] QW_BOUT_RATIO = { { 10, 100 }, { 20, 80 }, { 30, 50 }, { 40, 15 }, { 50, 5 }, { 99999, 0 } };
	//控制
	public static final int QW_SCOCE_CLEAN               =StringUtil.getIntParam(ConfigurationUtil.QW_SCOCE_CLEAN,1);
	//玩家角色等级对应强化等级的表
    public static int[][] RANK_AND_STRENGTH_LEVEL = { { 15, 10 }, { 21, 11 }, { 25, 12 }, { 29, 13 }, { 32, 14 }, { 36, 15 }, { 40, 16 } };
	//联赛=============start
	/** 联赛参加人员名单 */
	//(lami teamid) (pid) (战场type|积分|名字)
    public static final String LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX = "LAMI";
	/** 联赛参加战队积分排行榜 有序集合 */
	//(LATIS)(temaid) (积分)
    public static final String LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX = "LATIS";
	/** 联赛上赛季排行榜 有序集合 */
    //(LATIS)(rand) (Strings)
    public static final String LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX = "LATISO";
	/** 联赛参加战队胜负 list集合 */
	//(LATI)(temaid) (胜|负|积分)
    public static final String LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX = "LATI";
	/** 联赛参加战队胜负 list */
	//"lagrs:temaid", "日期+temabid+战场type" "100:10"
    public static final String LEAGUE_GAME_RECORD_KEY_PREFIX = "LGR";
	//"LAT", 0 false 1 true  是否在报名时间
    public static final String LEAGUE_ATTEND_TIME_KEY_PREFIX = "LAT";
	//"LGT", 0 false 1 true  是否在比赛时间
    public static final String LEAGUE_GAME_TIME_KEY_PREFIX = "LGT";
	//"LTM", (mytId) (tId)战队配对表
    public static final String LEAGUE_TEAM_MATE_KEY_PREFIX = "LTM";
	//联赛=============end
}