package com.pearl.o2o.utils;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;

public interface TeamConstants {
	// team request operate
	String TEAM_OPERATE_JOIN = "join";
	String TEAM_OPERATE_APPROVE = "approve";
	String TEAM_OPERATE_REJECT = "reject";
	String TEAM_OPERATE_QUIT = "quit";
	String TEAM_OPERATE_HANDOVER = "handover";
	String TEAM_OPERATE_APPOINT = "appoint";
	String TEAM_OPERATE_KICK = "kick";

	String Max_Team_Level = "MAX_TEAM_LEVEL";
	//战队等级 限定 11级
	int Team_Level_Limit = 11;
	
	//
	int Team_Buff_Strengthen_BuffId = 62;
	int Team_Buff_MaxStrengthLevel_BuffId = 61;
	int Team_Buff_Exp_Add_BuffId = 66;
	int Team_Buff_Gp_Add_BuffId = 65;
	
	int Team_Burnt_IId = 17;

	ImmutableSet<Integer> Burnt_Id_Set = ImmutableSet.of( 5012, 5013, 5014 );
	int BigBurnt =  5014 ;
	
	long OneDay = 1000L*60*60*24;
	
	public enum TEAMJOB {
		// be aware the sequence
		//队长				管理员									普通队员
		TEAM_CAPTAIN(4,TEAM_CAPTAIN_STR), TEAM_OFFICER(3,TEAM_OFFICER_STR), TEAM_HIGH_MEMBER(2,""), TEAM_MEMBER(1,TEAM_MEMBER_STR), TEAM_BLACK_ROOM(0,"");
		private int value;
		private String title;

		TEAMJOB(int value,String title) {
			this.value = value;
			this.title = title;
		}

		public boolean isGreateThan(TEAMJOB teamJob) {
			return this.ordinal() < teamJob.ordinal();
		}

		public static TEAMJOB getTypeByValue(int value) {
			for (TEAMJOB type : TEAMJOB.values()) {
				if (type.value == value) {
					return type;
				}
			}
			return null;
		}
		public String getTitle() {
			return title;
		}
		public int getValue() {
			return value;
		}
	}
	
	Function<Integer, Integer> calcSalaryByTeamLevel = new Function<Integer, Integer>() {
		@Override
		public Integer apply(Integer level) {
			return (level*level+level)/2+1;
		}
	};
	
	String TEAM_CAPTAIN_STR=CommonUtil.messageFormatI18N("184");
	String TEAM_OFFICER_STR=CommonUtil.messageFormatI18N("185");
	String TEAM_MEMBER_STR=CommonUtil.messageFormatI18N("186");
	String NOT_ENOUGH_ITEMS = CommonUtil.messageFormatI18N("187");
	String ONE_TIME_DAYLY = CommonUtil.messageFormatI18N("188");
	String TEAM_EXP_FULL = CommonUtil.messageFormatI18N("189");
	String Commit_Error_ITMES = CommonUtil.messageFormatI18N("190");
	String Team_Not_Exist = CommonUtil.messageFormatI18N("191");
	String Illegal_Rpc_Qequest = CommonUtil.messageFormatI18N("192");
	String Not_Enough_Right = CommonUtil.messageFormatI18N("193");
	String Not_Enough_Rank = CommonUtil.messageFormatI18N("194");
	String NOT_CREATE_TEAM				= CommonUtil.messageFormatI18N("195");
	String NOT_CREATE_TEAM_LEVEL		= CommonUtil.messageFormatI18N("196");
	String BUFF_NOT_DEBLOCK		= CommonUtil.messageFormatI18N("197");
	String BUFF_NOT_EXIST		= CommonUtil.messageFormatI18N("198");
	String BUFF_CONTRIBUTIONLIMIT		= CommonUtil.messageFormatI18N("199");
	String BUFF_YET_GET		= CommonUtil.messageFormatI18N("200");
	String BUFF_LOW_GET		= CommonUtil.messageFormatI18N("201");
	String BUFF_NOT_ENOUGH_CONTRIBUTION		= CommonUtil.messageFormatI18N("202");
	String TEAM_DISMISS_ERR		= CommonUtil.messageFormatI18N("203");
	String BUFF_ACTIVATE_FAIL				= CommonUtil.messageFormatI18N("204");

}
