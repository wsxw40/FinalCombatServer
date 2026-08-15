/**
 * 
 */
package com.pearl.o2o.utils;

import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.SysMission;

/**
 * @author lifengyang
 * 
 */
public interface GrowthMissionConstants {
	
//	SysItem CALSysItem = new SysItem("caladd_ico","金币","增加#$mi.money#金币",11,0);
//	SysItem EXPSysItem = new SysItem("caladd_ico_02","经验","增加#$mi.exp#经验",12,0);
	
	int MODULE_NUM = 8;
	String DEFAULTTUTORIAL = ConfigurationUtil.DEFAULT_PLAYER_MODULE;

	public static enum ModuleStatusIndex {
		Mission,//0任务系统 
		DailyMission, // 1每日任务
		Compose, // 2合成
		Online, // 3在线时长
		Present, // 4博彩
		DailyCheck, // 5每日签到
		FreeChange, // 6免费兑换区
		Shot, // 7打靶场
	}
	
	public static enum CycleMissionIncome{
		DailyMissionSIMPLE(100,50,1000,500,4,2,1),
		DailyMissionNORMAL(200,100,2000,1000,5,3,2),
		DailyMissionHARD(300,150,3000,1500,6,3,3),
		//20:25:30    1000 1250 1500
		WeekMissionSIMPLE(1000,500,10000,5000,20,10,1),
		WeekMissionNORMAL(1250,625,12500,6250,25,13,2),
		WeekMissionHARD(1500,750,15000,7500,30,15,3),
		//固定每日任务
		DailyMissionENTERNAL(300,150,3000,1500,6,3,1),
		
		//所有收益为正常一半（防沉迷）
		HALFDailyMissionSIMPLE(50,25,500,250,2,1,1),
		HALFDailyMissionNORMAL(100,50,1000,500,2,1,2),
		HALFDailyMissionHARD(150,75,1500,750,3,1,3),
		HALFWeekMissionSIMPLE(500,250,5000,2500,10,5,1),
		HALFWeekMissionNORMAL(625,312,6250,3125,12,6,2),
		HALFWeekMissionHARD(750,375,7500,3750,15,7,3),
		//所有收益为0 （防沉迷）
		NONEAWARDMISSION(0,0,0,0,0,0,0);
		
		private int exp;
		private int vipExp;
		private int cal;
		private int vipCal;
		private int medal;
		private int vipMedal;
		private int level;
		//level 表示任务的难度    1:简单  2:正常   3:困难
		private CycleMissionIncome(int exp, int vipExp, int cal, int vipCal, int medal, int vipMedal,int level) {
			this.exp = exp;
			this.vipExp = vipExp;
			this.cal = cal;
			this.vipCal = vipCal;
			this.medal = medal;
			this.vipMedal = vipMedal;
			this.level=level;
		}
		public int getExp() {
			return exp;
		}
		public int getVipExp() {
			return vipExp;
		}
		public int getCal() {
			return cal;
		}
		public int getVipCal() {
			return vipCal;
		}
		public int getMedal() {
			return medal;
		}
		public int getVipMedal() {
			return vipMedal;
		}
		public int getLevel() {
			return level;
		}
		public void setVipExp(int vipExp) {
			this.vipExp = vipExp;
		}
		public void setVipCal(int vipCal) {
			this.vipCal = vipCal;
		}
		public void setVipMedal(int vipMedal) {
			this.vipMedal = vipMedal;
		}
		public static CycleMissionIncome getCycleMissionIncome(SysMission sysMission, PlayerMission playerMission,int vipLevel){
			if(sysMission.getType() == 0){	//每日
				if(playerMission.getTarget() == sysMission.getNormalTarget()){
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(DailyMissionNORMAL, vipLevel);
					}
					return DailyMissionNORMAL;
				}else if(playerMission.getTarget() == sysMission.getHardTarget()){
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(DailyMissionHARD, vipLevel);
					}
					return DailyMissionHARD;
				}else{
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(DailyMissionSIMPLE, vipLevel);
					}
					return DailyMissionSIMPLE;
				}
			}else if(sysMission.getType() == 1){//每周
				if(playerMission.getTarget() == sysMission.getNormalTarget()){
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(WeekMissionNORMAL, vipLevel);
					}
					return WeekMissionNORMAL;
				}else if(playerMission.getTarget() == sysMission.getHardTarget()){
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(WeekMissionHARD, vipLevel);
					}
					return WeekMissionHARD;
				}else{
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(WeekMissionSIMPLE, vipLevel);
					}
					return WeekMissionSIMPLE;
				}
			}else if(sysMission.getType() == 9){//固定 难度为1且只有1个
				if(playerMission.getTarget() == sysMission.getSimpleTarget()){
					if(vipLevel>0){
						return getCycleMissionIncomeForVip(DailyMissionENTERNAL, vipLevel);
					}
					return DailyMissionENTERNAL;
				}
			}
			
			return null;
		}
		
		public static CycleMissionIncome getCycleMissionIncomeForFCM(CycleMissionIncome normalIncome){
			if(normalIncome==CycleMissionIncome.DailyMissionHARD){
				return HALFDailyMissionHARD;
			}else if(normalIncome==CycleMissionIncome.DailyMissionNORMAL){
				return HALFDailyMissionNORMAL;
			}else if(normalIncome==CycleMissionIncome.DailyMissionSIMPLE){
				return HALFDailyMissionSIMPLE;
			}else if(normalIncome==CycleMissionIncome.WeekMissionHARD){
				return HALFWeekMissionHARD;
			}else if(normalIncome==CycleMissionIncome.WeekMissionNORMAL){
				return HALFWeekMissionNORMAL;
			}else if(normalIncome==CycleMissionIncome.WeekMissionSIMPLE){
				return HALFWeekMissionSIMPLE;
			}
			
			return NONEAWARDMISSION;
		}
		
		private static CycleMissionIncome getCycleMissionIncomeForVip(CycleMissionIncome normalIncome,int vipLevel){
			if(vipLevel>0 && vipLevel<=Constants.VIP_MISSION_EX_EARN_PER.length){
				normalIncome.setVipCal((int)(normalIncome.getCal()*(Constants.VIP_MISSION_EX_EARN_PER[vipLevel-1]/100.0)));
				normalIncome.setVipExp((int)(normalIncome.getExp()*(Constants.VIP_MISSION_EX_EARN_PER[vipLevel-1]/100.0)));
				normalIncome.setVipMedal((int)(normalIncome.getMedal()*(Constants.VIP_MISSION_EX_EARN_PER[vipLevel-1]/100.0)));	
			}	
			return normalIncome;
		}
	}
	
	byte VALUE_0 = 0;
	byte VALUE_1 = 1;
	// 强化部件ID
	int UPGARDMOD_ID = 125;
	// 勋章ID
	int MEDAL_ID = 4479;
	int ADDSUCESS_ID = 127;
	//复活币
	int RELIVE_COIN = 4800;
	public static enum ModuleStatus {
		CLOSE('0'), FLASH('1'), OPEN('2');
		private char ch;

		private ModuleStatus(char ch) {
			this.ch = ch;
		}

		public char getCh() {
			return ch;
		}
	}

	public static enum BooleanBytevalue {
		FALSE(VALUE_0), TRUE(VALUE_1);

		private byte bytevalue;

		private BooleanBytevalue(byte bytevalue) {
			this.bytevalue = bytevalue;
		}

		public byte getBytevalue() {
			return bytevalue;
		}

	}
	
	public static enum GrowthMissionType {
		COMPLETE_LIMIT_1(1), 				//完成1局游戏(3人或以上有效)
		OPEN_NEW_GIFT(2), 							// 打开新手礼包 c_storage_item_use OK
		GAME_KILL_1(3), 					//1局游戏击杀3名玩家(3人或以上有效)
		ADD_FRIEND(4), 								// 添加1个好友 c_friend_accept OK
		PRESENT_BY_EMAIL(5), 						// 通过邮件赠送好友物品 c_email_create OK
		GAME_ASSIST_1(6), 					//1局游戏达成1次助攻(3人或以上有效)
		CHANGE_WAR_LIST(7), 						// 更换参战名单 c_character_set OK
		COMPLETE_LIMIT_2(8), 				//完成2局游戏(3人或以上有效)
		COMBINE_STRENGTH_MAINARM(9), 				// 强化1次主武器 c_strengthen type=1 subtype=1 Ok
		GAME_KILL_2(10), 					//1局游戏击杀5名玩家(3人或以上有效)
		COMBINE_STRENGTH_CLOTHING(11), 				// 强化1次衣服 type=2 Ok
		FIRST_GET_ONLINE_AWARD_GIFT(12), 			// 首次领取在线时长奖励 c_online_award_get OK
		GAME_ASSIST_2(13), 					//1局游戏达成2次助攻(3人或以上有效)
		COMBINE_STRENGTH_HAT(14), 					// 强化1次帽子 type=3 Ok
		FIRST_CHECKDAY(15), 						// 首次签到 c_daily_check OK
		FIRST_GUSNUM(16), 							// 首次猜数字 c_daily_num OK
		WIN_LIMIT_1(17), 					//赢得1局游戏(3人或以上有效)
		COMBINE_STRENGTH_ACCESSORY(18), 			// 强化1次配饰 type=4 Ok
		COMPLETE_LIMIT_3(19), 				//完成3局游戏(3人或以上有效)
		ARM_CONVERT(20), 							// 进行1次装备转化 c_convert OK
		GAME_KILL_3(21), 					//1局游戏击杀10名玩家(3人或以上有效)
		USE_DLB(22), 								// 使用大喇叭 OK
		GET_LOWGAMINGCHEST_BY_MEDAL(23), 			//使用勋章兑换一次青铜幸运彩盒 c_chest_get OK
		GAME_ASSIST_3(24), 					//1局游戏达成3次助攻(3人或以上有效)
		JOIN_TEAM(25), 								//加入战队c_team_request_op OK
		WIN_LIMIT_2(26), 					//赢得2局游戏(3人或以上有效)
		BUY_STRENGTH_BY_MEDAL(27), 					//使用勋章购买强化部件 Ok
		COMPLETE_LIMIT_4(28), 				//完成4局游戏(3人或以上有效)
		// OPEN_LOWGAMINGCHEST(27),//打开低级博彩宝箱 c_chest_get OK
		// USE_KINESCOPE_FUNCTION(29),//使用录像功能 new function X
		WIN_LIMIT_3(29), 					//new 赢得3局游戏(3人或以上有效)
		FIRST_RECHARGE(30), 			// 首次充值 AddLeiPoint OK
		FIRST_PURCHASE(31), 			// 使用FC点进行一次购买 c_shop_req_buy OK
		FIRST_FINISH_EVERYDAY_MISSION(32), // 首次完成每日任务 c_mission OK
		FIRST_STILETTO(33), 			// 进行一次打孔 c_slotting Ok
		FIRST_BESET(34), 				// 进行一次镶嵌 c_insert Ok
		COMBINE_STRENGTH_MAINARM_3(36), // 武器强化到3-15级 36-48
		COMBINE_STRENGTH_MAINARM_4(37), COMBINE_STRENGTH_MAINARM_5(38), COMBINE_STRENGTH_MAINARM_6(39), COMBINE_STRENGTH_MAINARM_7(40), COMBINE_STRENGTH_MAINARM_8(41), COMBINE_STRENGTH_MAINARM_9(
				42), COMBINE_STRENGTH_MAINARM_10(43), COMBINE_STRENGTH_MAINARM_11(44), COMBINE_STRENGTH_MAINARM_12(45), COMBINE_STRENGTH_MAINARM_13(46), COMBINE_STRENGTH_MAINARM_14(
				47), COMBINE_STRENGTH_MAINARM_15(48), COMBINE_STRENGTH_CLOTHING_3(49), // 衣服强化到3-15级
																						// 49-61
		COMBINE_STRENGTH_CLOTHING_4(50), COMBINE_STRENGTH_CLOTHING_5(51), COMBINE_STRENGTH_CLOTHING_6(52), COMBINE_STRENGTH_CLOTHING_7(53), COMBINE_STRENGTH_CLOTHING_8(54), COMBINE_STRENGTH_CLOTHING_9(
				55), COMBINE_STRENGTH_CLOTHING_10(56), COMBINE_STRENGTH_CLOTHING_11(57), COMBINE_STRENGTH_CLOTHING_12(58), COMBINE_STRENGTH_CLOTHING_13(59), COMBINE_STRENGTH_CLOTHING_14(
				60), COMBINE_STRENGTH_CLOTHING_15(61), COMBINE_STRENGTH_HAT_3(62), // 帽子强化到3-15级
																					// 62-74
		COMBINE_STRENGTH_HAT_4(63), COMBINE_STRENGTH_HAT_5(64), COMBINE_STRENGTH_HAT_6(65), COMBINE_STRENGTH_HAT_7(66), COMBINE_STRENGTH_HAT_8(67), COMBINE_STRENGTH_HAT_9(68), COMBINE_STRENGTH_HAT_10(
				69), COMBINE_STRENGTH_HAT_11(70), COMBINE_STRENGTH_HAT_12(71), COMBINE_STRENGTH_HAT_13(72), COMBINE_STRENGTH_HAT_14(73), COMBINE_STRENGTH_HAT_15(74), COMBINE_STRENGTH_ACCESSORY_3(
				75), // 配饰强化到3-15级 75-87
		COMBINE_STRENGTH_ACCESSORY_4(76), COMBINE_STRENGTH_ACCESSORY_5(77), COMBINE_STRENGTH_ACCESSORY_6(78), COMBINE_STRENGTH_ACCESSORY_7(79), COMBINE_STRENGTH_ACCESSORY_8(80), COMBINE_STRENGTH_ACCESSORY_9(
				81), COMBINE_STRENGTH_ACCESSORY_10(82), COMBINE_STRENGTH_ACCESSORY_11(83), COMBINE_STRENGTH_ACCESSORY_12(84), COMBINE_STRENGTH_ACCESSORY_13(85), COMBINE_STRENGTH_ACCESSORY_14(
				86), COMBINE_STRENGTH_ACCESSORY_15(87), 
				
				RECHARGE158(88),//累计充值158元
				RECHARGE188(89),//累计充值188元
				COMBINE_MELTING(90),//通过“合成系统”进行一次熔炼
				SHOOTING(91),//进行一次靶场射击
				SHOOTING_LOOK(92),//在靶场进行一次“全部偷看”
				TEAM_BRUNT(93),//为战队进行一次充能
				
				NEW_AWARD_1(94),//新兵奖励1
				NEW_AWARD_2(95),//新兵奖励
				NEW_AWARD_3(96),//新兵奖励
				NEW_AWARD_4(97),//新兵奖励
				NEW_AWARD_5(98),//新兵奖励
				NEW_AWARD_6(99),//新兵奖励
				NEW_AWARD_7(100),//新兵奖励
				NEW_AWARD_8(101),//新兵奖励
				
				
				;
		private final int growthMissionId;

		GrowthMissionType(int growthMissionId) {
			this.growthMissionId = growthMissionId;
		}

		public int getGrowthMissionId() {
			return growthMissionId;
		}

		@Override
		public String toString() {
			return String.format("growthmission name:%s,id:%s", name(), growthMissionId);
		}

//		public static GrowthMissionType valueOf(int growthMissionId) {
//			for (GrowthMissionType gmt : values()) {
//				if (gmt.getGrowthMissionId() == growthMissionId)
//					return gmt;
//			}
//			return null;
//		}
		
		private static final GrowthMissionType[] COMPLETELIMIT_GROWTH_MISSION_TYPES = {COMPLETE_LIMIT_1,COMPLETE_LIMIT_2,COMPLETE_LIMIT_3,COMPLETE_LIMIT_4};
		
		public static GrowthMissionType[] getCompleteLimitGame(){
			return COMPLETELIMIT_GROWTH_MISSION_TYPES;
		}
		
		private static final GrowthMissionType[] WINLIMIT_GROWTH_MISSION_TYPES = {WIN_LIMIT_1,WIN_LIMIT_2,WIN_LIMIT_3};

		public static GrowthMissionType[] getWinLimitGame(){
			return WINLIMIT_GROWTH_MISSION_TYPES;
		}
		private static final GrowthMissionType[] GAME_KILL_B_1 = {GAME_KILL_1};
		private static final GrowthMissionType[] GAME_KILL_B_2 = {GAME_KILL_1,GAME_KILL_2};
		private static final GrowthMissionType[] GAME_KILL_B_3 = {GAME_KILL_1,GAME_KILL_2,GAME_KILL_3};
		private static final GrowthMissionType[] GAME_KILL_EMPTY = {};

		public static GrowthMissionType[] getGameKill(int num){
			if(num >= 3){
				return GAME_KILL_B_3;
			}else if(num >= 2){
				return GAME_KILL_B_2;
			}else if(num >= 1){
				return GAME_KILL_B_1;
			}
			return GAME_KILL_EMPTY;
		}
		
		public static final GrowthMissionType[] GAME_ASSIST_B_1 = {GAME_ASSIST_1};
		public static final GrowthMissionType[] GAME_ASSIST_B_2 = {GAME_ASSIST_1,GAME_ASSIST_2};
		public static final GrowthMissionType[] GAME_ASSIST_B_3 = {GAME_ASSIST_1,GAME_ASSIST_2,GAME_ASSIST_3};
		public static final GrowthMissionType[] GAME_ASSIST_EMPTY = {};
		
		public static GrowthMissionType[] getGameAssist(int num){
			if(num >= 3){
				return GAME_ASSIST_B_3;
			}else if(num >= 2){
				return GAME_ASSIST_B_2;
			}else if(num >= 1){
				return GAME_ASSIST_B_1;
			}
			return GAME_ASSIST_EMPTY;
		}

		public static GrowthMissionType getCombineStrengthGrowthMissionTypeBy(int type, int subtype) {
			GrowthMissionType gmt = null;
			switch (type) {
			case 1:
				if (subtype == 1) {
					gmt = COMBINE_STRENGTH_MAINARM;
				}
				break;
			case 2:
				gmt = COMBINE_STRENGTH_CLOTHING;
				break;
			case 3:
				if(subtype == 1){
					gmt = COMBINE_STRENGTH_HAT;
				}else if(subtype == 2){
					gmt = COMBINE_STRENGTH_ACCESSORY;
				}
				break;
			}
			return gmt;
		}

		public static GrowthMissionType getCombineStrengthGrowthMissionTypeBy(int type, int subtype, int level) {
			int id = 0;
			if (level > 2 && level < 16) {
				switch (type) {
					case 1:// 武器强化到3-15级 36-48
						id = level + 33;
						break;
					case 2:// 衣服强化到3-15级 49-61
						id = level + 46;
						break;
					case 3:
						if(subtype == 1){// 帽子强化到3-15级 62-74
							id = level + 59;
						}else if(subtype == 2){// 配饰强化到3-15级 75-87
							id = level + 72;
						}
						break;
				}
			}
			if( id > 0 ){
				return values()[id - 2];
			}
			return null;
		}
	}
}
