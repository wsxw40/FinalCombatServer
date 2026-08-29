package com.pearl.o2o.utils;

public class Constants {
	public static final String BOOLEAN_YES				= "Y";
	public static final String BOOLEAN_NO				= "N";

	public static final String BOOLEAN_TRUE				= "true";
	public static final String BOOLEAN_FALSE			= "false";

	public static final String GENDER_MALE				= "M";
	public static final String GENDER_FEMALE			= "F";
	public static final String GENDER_BOTH				= "B";

	public static final String PART_FILTER				= "F";

	public static final int AVATAR_CHANGE_SIZE			= 7;
	public static final int	WEAPON_CHANGE_SIZE			= 12;

	//level limit
	public static final int CREATE_TEAM_LIMIT			= 10;

	public static final int	CACHE_ITEM_TIMEOUT			= 3600;
	public static final int	CACHE_TIMEOUT_DAY			= 3600*24;
	public static final int CACHE_SDO_CR_TIMEOUT 		= 900;

	public static final String DELIMITER				= "_";

	public static final String DELIMITER_RESOURCE_CHANGEABLE	= ";";
	public static final String DELIMITER_RESOURCE_STABLE		= ",";
	public static final String DOT						= ".";
	public static final String OBJECT_TYPE				= "O";
	public static final String STRING_TYPE				= "S";
	public static final String PACK_TYPE_C				= "C";
	public static final String PACK_TYPE_W				= "W";

	public static final int DEFAULT_WEAPON_TYPE         = 1;
	public static final int DEFAULT_COSTUME_TYPE        = 2;
	public static final int DEFAULT_PART_TYPE        	= 3;
	public static final int DEFAULT_ITEM_TYPE        	= 4;
	public static final int DEFAULT_PACKAGE_TYPE        = 5;
	//unitype
	public static final int DEFAULT_TIMEBASE_TYPE        = 4;
	public static final int UNITTYPE_FORERVER 			=1;

	public static final int DEFAULT_SPECIEL_ITEM_SUBTYPE        = 3;
	public static final int DEFAULT_KNIFE_SUBTYPE        = 7;

	public static final int DEFAULT_FACE_SUBTYPE         = 9;
	public static final int DEFAULT_COSTUME_SET_SUBTYPE  = 7;

	public static final int DEFAULT_KNIFE_SEQ       	 = 3;
	//team item
	public static final int DEFAULT_TEAM_IID       		 = 7;
	public static final int DEFAULT_WEAPON_PACK_SIZE    = 2;
	public static final int MAX_WEAPON_PACK_SIZE    	= 5;

	public static final int DEFAULT_COSTUME_PACK_SIZE   = 1;
	public static final int DEFAULT_COSTUME_PACK_SEQ   	= 7;//costume seq
	public static final int DEFAULT_ITEM_DURATION   	= 60;
	public static final int SEQ_NUM                 	= 6;//weapon seq

	public static final int NUM_ZERO                	= 0;
	public static final int NUM_ONE               		= 1;
	public static final int NUM_TWO              		= 2;


	public static final int TYPE_NUM                	= 5;
	public static final int SUB_TYPE_NUM                = 12;

	public static final String GET_PLAYER_BY_USER_ID 	= "Player.ByUserId";
	public static final String GET_SERVER_LIST			= "Server.List";
	public static final String GET_RANK_LIST			= "Rank.List";
	public static final String GET_CHARACTER_LIST		= "Character.List";
	public static final String GET_SYSTEMITEM_LIST		= "SysItem.List";
	public static final String SYSTEMITEM				= "SysItem";
	public static final String PLAYER					= "Player";
	public static final String PLAYER_LOCATION			= "PlayerLocation";
	public static final String TOOLTIP					= "Tooltip";
	public static final String STORAGE					= "Storage";
	public static final String PACK						= "Pack";
	public static final String MESSAGE					= "Message";
	public static final String STAGECLEAR				= "StageClear";
	public static final String CR 						= "CR";
	public static final String STORAGE_EXPIRED_TOKEN 	= "StorageExpired";

	public static final String CHANNEL_ACTION_ADD		="add";
	public static final String CHANNEL_ACTION_DELETE	="delete";
	public static final String CHANNEL_NAME				="Channel";

	public static final int CHANNEL_MAX_DEFAULT			=1000;

	public static final int DEFAULT_PART_VIEW_SIZE 		= 4;
	public static final int DEFAULT_PAGE_SIZE 			= 8;
	public static final int FRIEND_PAGE_SIZE			= 14;
	public static final int TEAM_PAGE_SIZE				= 4;

	public static final int WEAPON_PISTOL_ID			= 1;
	public static final int WEAPON_DOUBLE_PISTOL_ID		= 8;
	public static final int WEAPON_SUBMACHINE_ID		= 2;
	public static final int WEAPON_RIFFLE_ID			= 3;
	public static final int WEAPON_SNIPER_GUN_ID		= 4;
	public static final int WEAPON_SHOT_GUN_ID			= 5;
	public static final int WEAPON_MACHINE_GUN_ID		= 6;
	public static final int WEAPON_MINI_GUN_ID			= 7;
	public static final int WEAPON_KNIFE_ID				= 10;
	public static final int WEAPON_THROW_ID				= 20;
	public static final int WEAPON_GRENADE_ID			= 20;
	public static final int WEAPON_FLASH_ID				= 21;
	public static final int WEAPON_SMOKE_ID				= 22;

	public static final int ITEM_TYPE_WEAPON			= 1;
	public static final int ITEM_TYPE_PART				= 3;

	public static final String ACTION_DELETE			= "delete";
	public static final String ACTION_FRIEND_ADD		= "add_friend";
	public static final String ACTION_FRIEND_BLACK_ADD	= "add_black";

	public static final Integer GP_PAY			= 0;
	public static final Integer CR_PAY			= 1;
	public static final String GP_PAY_STRING	= "GP";
	public static final String CR_PAY_STRING	= "CR";
	public static final String EXPIRE_DATE			= "2020-10-10";

	public static final int SUBTYPE_ARRAY[]={8,9,12,3,1};

	public static final String MSG_BROADCAST		= "/info";
	public static final String MSG_DLB		= "/dlb";
	public static final String MSG_XLB		= "/xlb";
	public static final String MSG_CMD		= "/cmd";

	public static final int CHARACTER_COST				= 100;
	public static final int WEAPON_DURABLE_COST	        = 10;



	public static enum GAMETYPE{
		TEAM(0),BIO(3),FLAG(2),BLAST(1),DEATH(4);
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
}