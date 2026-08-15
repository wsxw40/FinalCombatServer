package com.pearl.o2o.utils;

import java.util.Date;

public class Constants2 {
	//online award
	public static final String  ONLINEAWARDSTATE                    = "onlineAwardState";
	public static final int  LEVEL_MINS[]							= {20,40,60};
	public static final int[] MEDOL_LIST							= {125,126};
	
	//init num
	public static final int  DEFAULT_PLAYER_CR						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_CR, 0);
	public static final int  DEFAULT_PLAYER_RANK					= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_RANK, 1);
	public static final int  DEFAULT_PLAYER_GP						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_GP, 0);
	public static final int  DEFAULT_PLAYER_V						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_V, 0);
	public static final int  DEFAULT_PLAYER_EXP						= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_PLAYER_EXP, 0);
	public static final int  DEFAULT_COST_RELEASE_CHARACTER_SIZE	= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_COST_RELEASE_CHARACTER_SIZE, 1000);
	public static final String[]  DEFAULT_XUNLEI_VIP_GIFT			= StringUtil.getStringArrayParam(ConfigurationUtil.DEFAULT_XUNLEI_VIP_GIFT, new String[]{"4006","3560"});
	//CACHE TIME
	public static final int	CACHE_TIMEOUT							= StringUtil.getIntParam(ConfigurationUtil.CACHE_TIMEOUT, 15000);//millisecond
	public static final int	CACHE_ITEM_TIMEOUT						= StringUtil.getIntParam(ConfigurationUtil.CACHE_ITEM_TIMEOUT, 3600);
	public static final int	CACHE_TIMEOUT_DAY						= StringUtil.getIntParam(ConfigurationUtil.CACHE_TIMEOUT_DAY, 3600*24);
	public static final int	CACHE_TIMEOUT_HALF_DAY						= StringUtil.getIntParam(ConfigurationUtil.CACHE_TIMEOUT_DAY, 3600*12);
	public static final int CACHE_SYN_INTERVAL_SECONDS  			= StringUtil.getIntParam(ConfigurationUtil.CACHE_SYN_INTERVAL_SECONDS, 60*15);//TODO change later
	//level limit
	//TODO modify after test
	public static final int CREATE_TEAM_LIMIT						= StringUtil.getIntParam(ConfigurationUtil.CREATE_TEAM_LIMIT, 20);
	public static final int JOIN_TEAM_LIMIT							= StringUtil.getIntParam(ConfigurationUtil.JOIN_TEAM_LIMIT, 1);
	public static final int DURABLE_NOTIFY							= StringUtil.getIntParam(ConfigurationUtil.DURABLE_NOTIFY, 40);
	public static final int DEFAULT_DB_NUM							= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_DB_NUM, 10000);
	public static final int DEFAULT_DB_SIZE							= StringUtil.getIntParam(ConfigurationUtil.DEFAULT_DB_SIZE, 400);
	public static final int SWITCH_SYNTODB							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_SYNTODB, 1);
	public static final int SWITCH_DAILYNUM							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_DAILYNUM, 1);
	public static final int SWITCH_TOPACTIVITY							= StringUtil.getIntParam(ConfigurationUtil.SWITCH_TOPACTIVITY, 1);
	public static final int AUTO_UNEQUIP							= StringUtil.getIntParam(ConfigurationUtil.AUTO_UNEQUIP, 3);
	public static final int AUTO_DESTORY							= StringUtil.getIntParam(ConfigurationUtil.AUTO_DESTORY, 7);
	//

	public static final String DEFAULT_CHARACTERS    				= "1,2,3,4,5,6";
	public static final String DEFAULT_CHARACTERS_SELECT    		= "1,2,3,4,5";
	
	//online flag 标志位，上次退出时已经达到领取条件未领取
	public static final String FLAG_NOT_GET_AWARD					= "1";
	public static final String FLAGE_OTHER							= "0";
	//online nextTime 
	public static final String LEVEL_IS_OUT_LINE					= "0";
	public static final int  ONLINE_AWARD_LEVEL_ONE					= 1;

	public static final int CHANNEL_MAX_DEFAULT						= 1000;
	//装备最大耐久度
	public static final float	MAX_DURABLE							= 100f;
	public static final int  DEFAULT_BOSS_CHARACTER_ID				= 120;
	public static final int MAX_CHARACTER_SIZE    					= 6;
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



	//team request operate
	public static final String TEAM_OPERATE_JOIN			= "join";
	public static final String TEAM_OPERATE_APPROVE			= "approve";
	public static final String TEAM_OPERATE_REJECT			= "reject";
	public static final String TEAM_OPERATE_QUIT			= "quit";
	public static final String TEAM_OPERATE_HANDOVER		= "handover";
	public static final String TEAM_OPERATE_APPOINT			= "appoint";
	public static final String TEAM_OPERATE_KICK			= "kick";

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
	//unitype
	public static final int DEFAULT_TIMEBASE_TYPE       	= 2;//time base
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


	public static final int DEFAULT_COSTUME_PACK_SIZE   = 1;
	public static final int DEFAULT_COSTUME_PACK_SEQ   	= 7;//costume seq
	public static final int SEQ_NUM                 	= 6;//weapon seq

	public static final int NUM_ZERO                	= 0;
	public static final int NUM_ONE               		= 1;
	public static final int NUM_TWO              		= 2;
	public static final int NUM_THREE              		= 3;
	//about item
	public static final int ITEM_BULLETPROOF_IBUFFID    = 21;

	public static final int TYPE_NUM                	= 7;
	public static final int SUB_TYPE_NUM                = 9;
	public static final int SYS_CHARACTER_NUM           = 10;
	//generate a fake system item id for purchase character
	public static final int SYS_ITEM_CHARACTER_ID  		= 99999999;
	//keys in memcache
	public static final String GET_PLAYER_BY_USER_ID 	= "P.B";
	public static final String GET_SERVER_LIST			= "S.L";
	public static final String GET_RANK_LIST			= "R.L";
	public static final String GET_SYSCHARACTER_LIST	= "SC.L";
	public static final String TEAM_LIST				= "TL";
	public static final String GET_SYSTEMITEM_LIST		= "SI.L";
	public static final String SYSTEMITEM				= "SI";
	public static final String PLAYER					= "P";
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
	public static final String BANNED_WORD 				= "BW";
	public static final String FRIEND_LIST 				= "FL";
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
	public static final int TEAM_PAGE_SIZE				= 15;
	public static final int TOP_PAGE_SIZE				= 10;
	public static final int PAGE_SIZE_MESSAGE_ITEM[]	= {12,10,20,20,20,32,32};
	public static final int PAGE_SIZE[]					= {8,8,16,32,32,32,32};
	public static final int STRENGTH_PAGE_SIZE[]		= {8,8,16,32,16,32,32};
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
	public static final int WEAPON_ARROW_ID				= 45;//箭
	public static final int WEAPON_WAR_DRUM				= 47;
	public static final int WEAPON_MILK					= 48;
	public static final int WEAPON_GLASS				= 49;
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

	//NOSQL KEY
	public static final String PLAYERTOP_ROWNUM_KEY_PREFIX 	= "pt:rn:";
	public static final String PLAYERTOP_RANK_KEY_PREFIX 	= "pt:r:";
	public static final String PLAYERTOP_WEAPON_PREFIX 		= "playerTop:weaponActivity:";
	public static final String TEAMRECORD_PREFIX 			= "TeamRecord:";
	public static final String DAILY_MISSION_PREFIX 		= "dm:";//DailyMission
	public static final String WEEK_MISSION_PREFIX 			= "wm:";//WeekMission
	public static final String MONTH_MISSION_PREFIX 		= "mm:";//MonthMission 
	public static final String DAILY_CHECK_PREFIX 			="dc:";
	public static final String DAILY_GIFT_DATE_PREFIX 		="dgd:";
	public static final String CHECK_ITEM_PREFIX 			="ci:";
	public static final String YESTODAY_DATA	 			= "SD:";//StayData
	public static final String DAILY_RANDOM_NUM_KEY			="D_R_N_K";//dailyRandomNum
	public static final String DAILY_RANDOM_NUM_MCC_KEY			="dlyrdmnmmcc";
	public static final String DAILY_PLAYER_NUM_PREFIX		="dpn:";//playerDailyNum
	public static final String DAILY_PLAYER_TOM_NUM_PREFIX		="dptn:";//playerDailyTomorrowNum
	public static final String  IS_DAILY_GUS_AWARD_PREFIX	="idga:";// is player get award when guess num 

	//all key for different top, except weapon record
	public static final String[] PLAYERTOP_TYPE 			= {"kCommon","kTeam","kZombie","kBanner","kTeamDead","kExplode","kCr",
			"kGp","kGrenade","kDrop","kGun","kReequip","kFlower","kDead","kTreasure"};

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
		TEAM(0),TARGET(1),DELIVER(2),NEWTRAIN(3),DESTROY(4),HITBOSS(5),KNIFE(6),BLAST(7),STREETBOY(8);
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

	public static enum TEAMJOB {
		// be aware the sequence
		TEAM_CAPTAIN(4),TEAM_OFFICER(3),TEAM_HIGH_MEMBER(2),TEAM_MEMBER(1),TEAM_BLACK_ROOM(0);
		private int value;

		TEAMJOB(int value){
			this.value = value;
		}

		public boolean isGreateThan(TEAMJOB teamJob){
			return this.ordinal() < teamJob.ordinal();
		}

		public static TEAMJOB getTypeByValue(int value){
			for(TEAMJOB type : TEAMJOB.values() ){
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
		MAP1_MODEL1(1),//金色农场的占点模式战役
		MAP2_MODEL1(2),//风车小镇的占点模式战役
		MAP3_MODEL1(3),//乡间伐木场占点模式战役
		MAP2_MODEL2(4),//风车小镇的团战模式战役
		MAP3_MODEL2(5),//乡间伐木场团战模式战役
		MAP4_MODEL2(6),//盛夏农场的团战模式战役
		MAP5_MODEL3(7),//法老神殿的推车模式战役
		MAP6_MODEL3(8),//神庙遗迹的推车模式战役
		MAP6_MODEL4(9),//神庙遗迹的歼灭模式战役
		MAP4_MODEL4(10),//盛夏农场的歼灭模式战役
		WIN(11),//赢得胜利
		GENERALKILL(12),//击杀敌人
		AWARDKILL(13),//赏金击杀
		REVENGEKILL(14),//复仇击杀
		CLOSEKILL(15),//近身击杀
		DOUBLEDKILL(16),//暴击击杀
		CONTINUEKILL(17),//完成连杀10人
		HELPKILL(18),//助攻
		MVP(19),//MVP
		MAXD(20);//最大破坏力1200以上
		private int value;
		ACTION_M(int value){
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	public enum ACTION{
		MILESTONE(0),//里程碑成就
		LEVELUP(1),//升级成就
		NEWPLAYER(2),//新手训练成就
		GENERALKILL(4),//通用击杀
		CONTROLKILL(5),//控制击杀
		REVENGEKILL(6),//复仇击杀
		CHARCTERKILL(9),//击杀指定职业
		BLOODBAG(7),//吃血包
		BULLETBAG(8),//吃弹药包
		DALABA(11),//刷大喇叭
		FRIENDS(12),//好友数量
		USECR(13),//消费雷点
		GIFTCR(14),//赠送雷点
		FIRSTKILL(15),//第一滴血
		FIRSTDEAD(16),//第一滴被血
		CONTINUEKILL(17),//连杀
		GETWEAPON(21),//获得主武器
		GETKNIFE(22),//获得近战武器
		GETCOSTUME(23),//获得衣服
		KNIFEKILL(24),//近战武器击杀
		MAXDAMAGE(25),//最大伤害量
		ASSISTNUM(26),//助攻数
		BOOSTKILL(27),//暴击击杀
		MAXALIVE(28),//最大存活时间
		MVPNUM(30),//获得mvp次数
		HEADSHOT(31),//爆头击杀
		GRENADEKILL(32),//雷杀
		SUSTAINKILL(33),//余焰击杀
		FINISHGAME(34),//完成比赛
		HEALTHNUM(35),//治疗量
		HEALTHMVP(36),//mvp
		HEALTHMAX(37),//最大治疗量
		GAMEWIN(3);//赢得游戏

		private int value;
		ACTION(int value){
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
		LEVEL_ACTIVITY(1),
		LOGIN_ACTIVITY(2),
		RANDOM_ACTIVITY(3),
		ONLINE_ACTIVITY(4),
		ACHIEVEMENT_ACTIVITY(5),
		KILL_ACTIVITY(6),
		TASK_ACTIVITY(8),
		BOSS_KILL_ACTIVITY(7),
		KILL_BOSS_ACTIVITY(10),
		DISCOUNT_ACTIVITY(9),
		HOUR2HOUR_ACTIVITY(11),
		TOP_PLAYER_ACTIVITY(12),
		IGNORE_DEAD(13),
		IGNORE_LOSE(14),
		LEVEL_FIRST_LOGIN(15);
		private int value;
		ACTION_ACTIVITY(int value){
			this.value = value;
		}
		public int getValue(){
			return this.value;
		}
	}
	//int[gametype][index]
	public static double[][] numParam={
		{
			0,14,1.5,10,0,70,1.5,7,150,8,1.5,
		},{
			0,14,1.2,10,0,70,1.2,9,150,8,1.5
		},{
			0,14,1.2,10,0,70,1.2,7,150,8,1.5
		},{

		},{
			0,14,2.5,10,0,70,2.5,7,150,8,1.5
		},{
			0,14,2,10,0,70,2,7,150,8,1.5
		},{
			0,14,1.5,10,0,70,1.6,7,150,8,1.5
		},
		
		{
			0,14,1.5,10,0,70,1.6,7,150,8,1.5
		},
		{
			0,14,1.5,10,0,70,1.6,7,150,8,1.5
		},
	};
	//double[gametype]
	public static double[] gameTypeNum={
		0.9,1,1,1,0.6,0.9,0.9,0.9,0.9,
	};
	//int[characterid][sid]
	public static int[][] weaponPack={
		{
			1132,1128,1136,0,
		},{
			1233,1230,1236,0,
		},{
			1332,1330,1335,0,
		},{
			1432,1430,1435,1438,
		},{
			1532,1531,1535,0,
		},{
			17,16,9,0
		},
	};
	//int[characterid][sid]
	public static int[][] costumePack={
		{
			2000,3000,3500,
		},{
			2103,3100,3510,
		},{
			2202,3200,3520,
		},{
			2301,3301,3530,
		},{
			2402,3400,3540,
		},{
			4418,4419,4420
		},
	};
	//bronze\silver \gold\ vip\ activity
	public static int[][] giftPool={
		{296,302,308,},
		{
			1138,1127,1133,1120,1121,1125,3001,3002,4008,4017,
			1334,1320,1338,1337,1322,1325,2100,3103,4009,4018,
			1434,1420,1431,1437,1124,1422,1423,2200,3202,4010,310,
			1537,1525,1533,1520,1521,1523,2300,3303,4011,
			1234,1220,1238,1224,1221,1223,2303,3402,4007,
		},
		{
			1113,1112,1114,1110,
			1310,1312,1313,1309,
			1412,1413,1414,1410,
			1513,1511,1514,1510,
			1211,1219,1213,1210,
		},//changed in 20111123
		};
	//==================================dailycheck=============================================
	public static final int DAILY_CHECK_AWARD_TYPE =2;
	public static final int DAILY_CHECK_AWARD_IID = 19;
	public static final int DAILY_CHECK_3D_AWARD_LEVEL =1;
	public static final int DAILY_CHECK_7D_AWARD_LEVEL =2;
	public static final int DAILY_CHECK_14D_AWARD_LEVEL =3;
	public static final int DAILY_CHECK_27D_AWARD_LEVEL =4;
	public static final int DAILY_GIFT_AWARD_LEVAL =5;
	public static final int DAILY_GUESS_AWARD_LEVEL =6;
	//每日领取返回信息
	public static final String DAILY_GIFT_ALREADY_MSG ="您今天已经领取";
	public static final String DAILY_GIFT_FAILED_MSG ="对不起，领取失败";
	//每日签到累计天数物品tips
	public static final String[] DAILY_CHECK_GIFT_TIPS = {"强化部件×1，勋章×2","强化部件×2，勋章×4，增幅装置×1，安定装置×1","强化部件×5，勋章×10，低级属性石×1，开孔钻头×1","密钥×3"};
	/**
	 * 每日签到各级礼品盒id
	 */
	public static final int[] dailyCheckGiftboxIDs ={4481,4482,4483,4484};
	/**
	 * 玩家每日签到获得奖励累计天数
	 */
	public static final int DAILY_CHECK_1ST_DAY =3;
	public static final int DAILY_CHECK_2ND_DAY =7;
	public static final int DAILY_CHECK_3RD_DAY =14;
	public static final int DAILY_CHECK_4TH_DAY =27;
	
	/**
	 * 猜数字需要的CR币
	 */
	public static int DAILY_GUS_NUM_COST = 100;
	
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
	public static final String STAGE_BRAND_OPEN_PREFIX = "stbo:";
	public static final int STAGE_BRAND_OPEN_NUM = 9;
	public static final String STAGE_GET_CHANCE_PREFIX = "sgch:";
	public static final String STAGE_GET_SCORE_PREFIX = "sgsc:";
	public static final String STAGE_GET_EXP_PREFIX = "sgex:";
	public static final String STAGE_GET_GP_PREFIX = "sggp:";
	public static final int VIP_EXTRA_GP_PERCENT = 10;
	public static final int VIP_EXTRA_EXP_PERCENT = 10;
	public static final int[] netbarExtraGpPercent ={10,20,30} ;
	public static final int[] netbarExtraExpPercent ={10,15,20} ;
	public static final int ACTIVITY_EXTRA_GP_PERCENT = 0;
	public static final int ACTIVITY_EXTRA_EXP_PERCENT = 0;
	
	public static final int NO_OPEN_BRAND_CHANCE_FLAG = -1;
	
	public static final int BRAND_CACHE_ITEM_TIMEOUT = 15;//second
	/**
	 * nosql 中获得所有牌子的key
	 */
	public static final String ALL_STAGE_CLEAR_BRAND_KEY ="A_S_C_B_K";
	/**
	 * 玩家牌子list nosql key
	 */
	public static final String PLAYER_BRAND_ID_LIST_PREFIX = "pbdil:";
	public static final long STAGE_OPEN__WAIT_TIME = 10000;
	/**
	 * 神秘锦囊
	 */
	public static final int MYSTIC_BAG_TYPE =3;
	public static final int MYSTIC_BAG_LEVEL1_TYPE =1;
	public static final int MYSTIC_BAG_LEVEL2_TYPE =2;
	public static final int MYSTIC_BAG_LEVEL3_TYPE =3;
	/**
	 * 神秘锦囊随机物品
	 */
	public static final int[][]  mysticBagItemList ={
		{1, 4479 , 500 , 1 , 80 , 1},//游戏币500
		{2, 4485 , 1 , 1 ,10 , 1},//密码箱
		{3, 4623 , 1 , 1 ,10 , 1},//VIP保险柜
		{4, 4479 , 500 , 1 , 70 , 2},//游戏币500
		{5, 125  , 1 ,1, 10 , 2 },//强化部件
		{6, 4485 , 1 , 1 ,10 , 2 },//密码箱
		{7, 4623  , 1 , 1 ,10 , 2 },//VIP保险柜
		{8, 4479 , 500 , 1 , 60 , 3 },//游戏币500
		{9, 125  , 1 ,1, 10 , 3 },//强化部件
		{10, 126 , 1 ,1, 10 , 3 },//安定装置
		{11, 4485 , 1 , 1 ,10 , 3 },//密码箱
		{12, 4623  , 1 , 1 ,10 , 3 }//VIP保险柜
		} ;
	//==============================VIP保险柜====================================================
	public static final int[][]  vipSafeCabinetItemList ={
		{ 1 , 125 , 1 , 1 , 500 , 1 },//强化部件
		{ 2 , 311 , 2 , 3 , 200 , 2 },//C币加成(大)
		{ 3 , 4101 , 2 , 3 , 200 , 2 },//经验加成(小)
		{ 4 , 312 , 2 , 3 , 200 , 2 },//经验加成(大)
		{ 5 , 4100 , 2 , 3 , 200 , 2 },//经验加成(小)
		{ 6 , 4202 , 2 , 3 , 200 , 2 },//强效血清
		{ 7 , 4203 , 2 , 3 , 200 , 2 },//速效血清
		{ 8 , 4200 , 2 , 3 , 200 , 2 },//弹药专家
		{ 9 , 4201 , 2 , 3 , 200 , 2 },//弹药达人
		{ 10 , 4204 , 2 , 3 , 200 , 2 },//加速复活
		{ 11 , 4306 , 2 , 1 , 200 , 2 },//不死之身
		{ 12 , 4205 , 2 , 3 , 200 , 2 },//追加弹夹
		{ 13 , 1530 , 2 , 1 , 200 , 2 },//压缩气罐
		{ 14 , 1232 , 2 , 1 , 200 , 2 },//奶瓶
		{ 15 , 1131 , 2 , 1 , 200 , 2 },//战鼓
		{ 16 , 1329 , 2 , 1 , 200 , 2 },//射击辅助眼镜
		{ 17 , 1440 , 2 , 3 , 200 , 2 },//高爆手雷
		{ 18 , 1441 , 2 , 3 , 200 , 2 },//南瓜手雷
		{ 19 , 4505 , 1 , 1 , 200 , 2 },//改装部件
		{ 20 , 129 , 1 , 1 , 200 , 2 },//增幅装置
		{ 21 , 127 , 1 , 1 , 200 , 2 },//安定装置
		{ 22 ,  4486, 1 , 1 , 200 , 2 },//武器属性部件LV1
		{ 23 , 4521 , 1 , 1 , 200 , 2 },//服饰属性部件LV1
		{ 24 , 4487 , 1 , 1 , 10 , 3 },//武器属性部件LV2
		{ 25 , 4504 , 1 , 1 , 10 , 3 },//武器属性部件LV3
		{ 26 , 4522, 1 , 1 , 10 , 3 },// 服饰属性部件LV2
		{ 27 , 4523 , 1 , 1 , 10 , 3 },//服饰属性部件LV3
		{ 28 , 4614 , 1 , 1 , 10 , 3 },//方形火箭炮(可强化)
		{ 29 , 4615 , 1 , 1 , 10 , 3 },//加特林机枪(可强化)
		{ 30 , 4616 , 1 , 1 , 10 , 3 },//QBZ95突击步枪(可强化)
		{ 31 , 4617 , 1 , 1 , 10 , 3 },//L96A1狙击步枪(可强化)
		{ 32 , 4618 , 1 , 1 , 10 , 3 },//军用轻型喷火器(可强化)
		{ 33 , 4584 , 1 , 1 , 10 , 3 },//medic01008(可强化)
		{ 34 , 4511 , 1 , 1 , 10 , 3 },//杀手贝恩套装(可强化)
		{ 35 , 4514 , 1 , 1 , 10 , 3 },//胖子罗迪套装(可强化)
		{ 36 , 4520 , 1 , 1 , 10 , 3 },//跑酷杰瑞套装(可强化)
		{ 37 , 4517 , 1 , 1 , 10 , 3 },//白发凯西套装 (可强化)
		{ 38 , 4526 , 1 , 1 , 10 , 3 },//篮球手维恩套装(可强化)
		{ 39 , 4528 , 1 , 1 , 10 , 3 },//红十字贝琪套装(可强化)
		{ 40 , 4532 , 1 , 1 , 10 , 3 },//“5”中队消防帽(可强化)
		{ 41 , 4533 , 1 , 1 , 10 , 3 },//超级朱里奥帽(可强化)
		{ 42 , 4536 , 1 , 1 , 10 , 3 }//鲨鱼猎食者头巾(可强化)
		} ;
	//==============================VipRandom===================================================
	public static final String VIP_START_INDEX_REDIS=" VipRandomStartIndex Key ";
	public static final String VIP_RANDOM_VSC_LIST_PREFIX = "vrdvscl:";
	public static final String VIP_RANDOM_START_LIST_PREFIX = "vrdsl:";
	public static final int VIP_OPEN_CHANCE_NUM = 5;
	public static final int VIP_RANDOM_SYSITEM_NUM = 15;
	public static final int[] vipRandomSysItemId = {1400,1401,1402,1403,1404,1410,1411,1412,1421,1422,1432,1433,1434,1500,1501};
	public static final String NO_VIP_OPEN_CHANCE_MSG ="没有开启机会了";
	public static final float VIP_SOCRE_EXTRA = 0.2f;
	public static final int[] VIP_SAFE_CABINET_LEVELS = {1,2,3};
	public static final int VIP_SAFE_CABINET_TYPE = 4;
	public static final int[] VIP_SAFE_CABINET_TYPE_NUMS = {1,11,3};
	public static final int[] VIP_START_COSTS ={1,2,4,8,16};
	public static final int VIP_SAFE_CABINET_ID = 4623;
	public static final int VIP_SAFE_CABINET_ITEM_IID = 20;
	public static final int NOT_IN_CHANNEL_ID=0;
	public static final int NOT_IN_SERVER_ID=0;
	public static final int STAGE_CLEAR_ONLINE_AWARD_TYPE = 6;
	
//	public static final int[][] stageClearBrandList ={
//		{1, 4485 , 1 , 1 ,600 ,1 },//密码箱
//		{2, 4623  , 1 , 1 ,600 ,1 },//VIP保险柜
//		{3, 4479 , 1 , 1 ,1000 ,1 },//勋章
//		{4, 4000  , 100 ,1 ,2250 ,0 },//游戏币 100
//		{5, 295  , 300 , 1 , 750 , 0 },//游戏币 300
//		{6, 4631  , 500 , 1 , 450 , 0 },//游戏币 500
//		{7, 4403  , 1000 ,1 , 225 , 1 },//游戏币 1000
//		{8, 125  , 1 ,1, 100 , 1 },//强化部件 1
//		{9, 125 , 5 ,1, 20 , 1 },//强化部件 5
//		{10, 125  , 10 ,1, 10 , 1 },//强化部件 10
//		{11, 129  , 1 ,1, 12 , 1 },//增幅装置 1
//		{12, 126 , 1 ,1, 12 , 1 },//安定装置 1
//		{13, 4486  , 1 ,1, 25 , 1 },//属性部件LV1 1
//		{14, 4487  , 1 ,1, 5 , 1 },//属性部件LV2 1
//		{15, 4504  , 1 ,1, 1 , 1 },//属性部件LV3 1
//		{16, 4505  , 1 ,1, 5 , 1 },//改装部件 1
//		{17, 4480  , 1 ,1, 600 , 1 },//神秘锦囊I 1
//		{18, 4570  , 1 ,1, 400 , 1 },//神秘锦囊II 1
//		{19, 4621  , 1 ,1, 200 , 1 }//神秘锦囊III 1
//		} ;
//	
	//============================MagicBox==========================================
	
	public static final int MAGIC_BOX_ONELINE_AWARD_TYPE = 5;
	public static final int OPEN_MAGIC_BOX_NEED_NUM = 1;
	public static final int SYSITEM_HUMMER_ID = 4485;
	public static final int SYSITEM_MAGIC_BOX_ID = 4626;
	public static final String MAGIC_BOX_ITEM_IDS_KEY="M_B_I_I_K";
	public static final int MAGIC_BOX_ITEM_NUM = 10;
	public static final int MAGIC_BOX_GIFT_PAGESIZE = 32;
	
	/**
	 * 二维数组
	 * 第一行，表示武器的subtype（同客户端和sysitem的subtype）
	 * 第二行，表示付款方式（0全部，1FC点，2C币）
	 */
	//weapon
	public static int[][] weaponTypeArray = {
		{
			0, 1, 2, 3, 4, SHOP_VIP_INDEX, SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	//cloth
	public static int[][] clothTypeArray = {
		{
			0,SHOP_VIP_INDEX,SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	//配饰
	public static int[][] partTypeArray = {
		{
			0, 1, 2, SHOP_VIP_INDEX, SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	//道具
	public static int[][] itemTypeArray = {
		{
			0, 1, 2, 3, 4, 5, SHOP_VIP_INDEX, SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	//素材
	public static int[][] materialTypeArray = {
		{
			0, 1, 2, SHOP_VIP_INDEX, SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	//蓝图
	public static int[][] blueTypeArray = {
		{
			0,SHOP_VIP_INDEX,SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	//大礼包
	public static int[][] packageTypeArray = {
		{
			0,SHOP_VIP_INDEX,SHOP_WEB_INDEX,
		},
		{
			0, 1, 2,
		}
	};
	
	/**
	 * 强化加强数据
	 * 第一维：表示等级
	 * 第二维：表示物品类型（武器，衣服，帽子，配饰）
	 * 第三维：属性1加成，属性2加成，所需素材个数，强化所需C币，成功概率，	失败不掉级概率，失败掉级概率，失败爆装概率，开孔的成功率，转换掉1级概率
	 */
	public static double[][][] strengthenAppend = { 
		{//lv1
			{
				0.12,0.013,6,6000,1,0,0,0,0,0,
			},
			{
				0.033,0.033,3,3000,1,0,0,0,0,0,
			},
			{
				0.011,0.011,1,1000,1,0,0,0,0,0,
			},
			{
				0.022,0.022,2,2000,1,0,0,0,0,0,
			}
		}, 
		{//lv2
			{
				0.24,0.027,6,6000,0.7,0.3,0,0,0,0,
			},
			{
				0.064,0.064,3,3000,0.7,0.3,0,0,0,0,
			},
			{
				0.021,0.021,1,1000,0.7,0.3,0,0,0,0,
			},
			{
				0.043,0.043,2,2000,0.7,0.3,0,0,0,0,
			}
		}, 
		{//lv3
			{
				0.36,0.04,12,12000,0.6,0.4,0,0,0,0,
			},
			{
				0.095,0.095,6,6000,0.6,0.4,0,0,0,0,
			},
			{
				0.032,0.032,2,2000,0.6,0.4,0,0,0,0,
			},
			{
				0.063,0.063,4,4000,0.6,0.4,0,0,0,0,
			}
		}, 
		{//lv4
			{
				0.48,0.053,12,12000,0.5,0.25,0.25,0,1,0,
			},
			{
				0.124,0.124,6,6000,0.5,0.25,0.25,0,0,0,
			},
			{
				0.041,0.041,2,2000,0.5,0.25,0.25,0,0,0,
			},
			{
				0.083,0.083,4,4000,0.5,0.25,0.25,0,0,0,
			}
		}, 
		{//lv5
			{
				0.6,0.067,18,18000,0.4,0.3,0.3,0,1,0,
			},
			{
				0.153,0.153,9,9000,0.4,0.3,0.3,0,0,0,
			},
			{
				0.051,0.051,3,3000,0.4,0.3,0.3,0,0,0,
			},
			{
				0.102,0.102,6,9000,0.4,0.3,0.3,0,0,0,
			}
		}, 
		{//lv6
			{
				0.72,0.08,24,24000,0.3,0.35,0.35,0,1,0,
			},
			{
				0.181,0.181,12,12000,0.3,0.35,0.35,0,0,0,
			},
			{
				0.06,0.06,4,4000,0.3,0.35,0.35,0,0,0,
			},
			{
				0.121,0.121,8,8000,0.3,0.35,0.35,0,0,0,
			}
		}, 
		{//lv7
			{
				0.84,0.093,30,30000,0.2,0.4,0.4,0,0.5,0,
			},
			{
				0.209,0.209,15,15000,0.2,0.4,0.4,0,0.5,0,
			},
			{
				0.07,0.07,5,5000,0.2,0.4,0.4,0,0.5,0,
			},
			{
				0.139,0.139,10,10000,0.2,0.4,0.4,0,0.5,0,
			}
		}, 
		{//lv8
			{
				0.96,0.107,36,36000,0.12,0.44,0.44,0,0.5,0,
			},
			{
				0.236,0.236,18,18000,0.12,0.44,0.44,0,0.5,0,
			},
			{
				0.079,0.079,6,6000,0.12,0.44,0.44,0,0.5,0,
			},
			{
				0.158,0.158,12,12000,0.12,0.44,0.44,0,0.5,0,
			}
		}, 
		{//lv9
			{
				1.08,0.12,48,48000,0.09,0.455,0.455,0,0.5,0,
			},
			{
				0.263,0.263,24,24000,0.09,0.455,0.455,0,0.5,0,
			},
			{
				0.088,0.088,8,8000,0.09,0.455,0.455,0,0.5,0,
			},
			{
				0.175,0.175,16,16000,0.09,0.455,0.455,0,0.5,0,
			}
		}, 
		{//lv10
			{
				1.2,0.133,66,66000,0.06,0.47,0.47,0,0.25,0.1,
			},
			{
				0.289,0.289,33,33000,0.06,0.47,0.47,0,0.5,0.1,
			},
			{
				0.096,0.096,11,11000,0.06,0.47,0.47,0,0.5,0.1,
			},
			{
				0.193,0.193,22,22000,0.06,0.47,0.47,0,0.5,0.1,
			}
		}, 
		{//lv11
			{
				1.32,0.147,84,84000,0.37,0,0.315,0.315,0.25,0.15,
			},
			{
				0.316,0.316,42,42000,0.37,0,0.315,0.315,0.5,0.15,
			},
			{
				0.105,0.105,14,14000,0.37,0,0.315,0.315,0.5,0.15,
			},
			{
				0.21,0.21,28,28000,0.37,0,0.315,0.315,0.5,0.15,
			}
		}, 
		{//lv12
			{
				1.44,0.16,108,1084000,0.37,0,0.315,0.315,0.2,0.2,
			},
			{
				0.341,0.341,54,54000,0.37,0,0.315,0.315,0.2,0.2,
			},
			{
				0.114,0.114,18,18000,0.37,0,0.315,0.315,0.2,0.2,
			},
			{
				0.227,0.227,36,36000,0.37,0,0.315,0.315,0.2,0.2,
			}
		}, 
		{//lv13
			{
				1.56,0.173,138,138000,0.37,0,0.315,0.315,0.2,0.25,
			},
			{
				0.366,0.366,69,69000,0.37,0,0.315,0.315,0.2,0.25,
			},
			{
				0.122,0.122,23,23000,0.37,0,0.315,0.315,0.2,0.25,
			},
			{
				0.244,0.244,46,46000,0.37,0,0.315,0.315,0.2,0.25,
			}
		}, 
		{//lv14
			{
				1.68,0.187,180,180000,0.37,0,0.315,0.315,0.2,0,3,
			},
			{
				0.392,0.392,90,90000,0.37,0,0.315,0.315,0.2,0,3,
			},
			{
				0.131,0.131,30,30000,0.37,0,0.315,0.315,0.2,0,3,
			},
			{
				0.261,0.261,60,60000,0.37,0,0.315,0.315,0.2,0,3,
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
	};
	final public static int[] LEVEL_PACKAGE={
		
	};
	final public static int SUCCESS						= 0;
	final public static int FAILED_NOT_LOSE_LEVEL 		= 1;
	final public static int FAILED_LOSE_LEVEL 			= 2;
	final public static int FAILED_BREAK_ITEM 			= 3;
	
	final public static int SUCCESSED		 			= 0;
	final public static int FAILED			 			= 1;
	
	//强化装置类型
	final public static int STABLE_ITEM 				= 2;//安定装置
	final public static int SAFE_ITEM 					= 3;//保险装置
	final public static int UNBREAK_ITEM 				= 4;//保障装置
	final public static int SUCCESS_ITEM 				= 5;//增幅安定装置
	
	final public static int MAX_MATERIAL_SIZE 			= 300;//素材最大重叠数
	
	final public static double STABLE_ITEM_1T3			= 0.01;
	final public static double STABLE_ITEM_4T10			= 0.005;
	final public static double STABLE_ITEM_11T15		= 0.005;
	
	final public static int SLOTTING_COST = 1000;
	final public static int CONVERT_COST = 1000;
	final public static int REMOVE_COST = 100;
	final public static int[] INSERT_COST = {250, 500, 1000};
}