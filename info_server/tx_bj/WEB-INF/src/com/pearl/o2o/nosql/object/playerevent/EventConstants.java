package com.pearl.o2o.nosql.object.playerevent;

public class EventConstants {
	public static final String UNLOCK_AND_ACHIEVEMENT						= "解锁并获得了\'{0}\'成就";
	public static final String MAKE_FRIEDS_WHIT_MSG							= "和{0}成为了好友";
	public static final String BUY_MSG										= "购买了{0}";
	public static final String KILLED_BY_MSG								= "被杀死{0}次";
	public static final String KILL_COUNT_MSG								= "击杀了{0}人。";
	public static final String GAIN_EXP_MSG									= "获得了经验{0}";
	public static final String GAIN_CB_MSG									= "获得了C币{0}";
	public static final String COST_TICKET_MSG								= "消耗了抵用券{0}";
	public static final String RANK_ADD_MSG									= "等级晋升为{0}级!";
	public static final String LOGIN_CHANEL_MSG								= "登陆了频道{0}";
	public static final String COST_LEIPOINT_MSG							= "消耗了雷点{0}";
	public static final String COST_CB_MSG									= "消耗了C币{0}";
	public static final String PRESENT_FRIEND_SYSITEM_MSG					= "赠送给好友：{0}一个物品：{1}";
	public static final String IN_ROME_MSG									= "进入房间{0}";
	public static final String ACTIVE_TEAM_BUF_MSG							= "激活了战队Buff：{0}";
	public static final String UNLOCK_AND_TITLE								= "解锁并获得了\'{0}\'称号";
	public static final String VIP_RANK_ADD_MSG								= "vip等级晋升为{0}级!";
	
	public  static enum I18NEventConstantsCode{
		UNLOCK_AND_ACHIEVEMENT("206"),
		MAKE_FRIEDS_WHIT_MSG("207"),
		BUY_MSG("208"),
		KILLED_BY_MSG("209"),
		KILL_COUNT_MSG("210"),
		GAIN_EXP_MSG("211"),
		GAIN_CB_MSG("212"),
		COST_TICKET_MSG("213"),
		RANK_ADD_MSG("214"),
		LOGIN_CHANEL_MSG("215"),
		COST_LEIPOINT_MSG("216"),
		COST_CB_MSG("217"),
		PRESENT_FRIEND_SYSITEM_MSG("218"),
		IN_ROME_MSG("219"),
		ACTIVE_TEAM_BUF_MSG("220"),
		UNLOCK_AND_TITLE("221"),
		VIP_RANK_ADD_MSG("606");
		private String code;
		I18NEventConstantsCode(String code){
			this.code = code;
		}
		public String getCode(){
			return this.code;
		}
	}
}
