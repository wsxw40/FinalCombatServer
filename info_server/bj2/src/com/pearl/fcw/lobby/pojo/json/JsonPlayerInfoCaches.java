package com.pearl.fcw.lobby.pojo.json;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * PlayerInfo表caches字段的内容
 */
public class JsonPlayerInfoCaches implements Serializable{

	private static final long serialVersionUID = 3873717314769592650L;

	private long lastHour2HourAwardTime;//上次因时段活动领奖的时间
	private Map<Float, Integer> discountAndUseFlag = new HashMap<>();//玩家使用对应折扣率的记录
	private long lastUseDiscountTime;//玩家上次使用折扣的时间
	private Map<Integer, Integer> playerItemIdAndRemainDaysInLimitTime = new HashMap<>();//限时道具的玩家道具ID和剩余天数
	private int secondPasswordFlag;//二级密码标记
	private int internetCafe;//网咖标记
	private Map<Integer, Long> rareTypeAndBuyGstExpTime = new HashMap<>();//合成中稀有度类型对应购买升星经验的时间
	private long lastMeltingTime;//最近一次熔炼的时间
	private int meltingCount;//最近一次熔炼在当天的累加次数
	private Map<Integer, Map<Integer, Integer>> meltingAward = new HashMap<>();//最近一次熔炼的预定奖励[index,[sysItemId,quantity]]
	private long lastGameTime;//最近一次进入战斗的时间
	private int voterCountInGame;//战斗可投票数量,VIP玩家3，普通玩家2

	public long getLastHour2HourAwardTime() {
		return lastHour2HourAwardTime;
	}

	public void setLastHour2HourAwardTime(long lastHour2HourAwardTime) {
		this.lastHour2HourAwardTime = lastHour2HourAwardTime;
	}

	public Map<Float, Integer> getDiscountAndUseFlag() {
		return discountAndUseFlag;
	}

	public void setDiscountAndUseFlag(Map<Float, Integer> discountAndUseFlag) {
		this.discountAndUseFlag = discountAndUseFlag;
	}

	public long getLastUseDiscountTime() {
		return lastUseDiscountTime;
	}

	public void setLastUseDiscountTime(long lastUseDiscountTime) {
		this.lastUseDiscountTime = lastUseDiscountTime;
	}

	public Map<Integer, Integer> getPlayerItemIdAndRemainDaysInLimitTime() {
		return playerItemIdAndRemainDaysInLimitTime;
	}

	public void setPlayerItemIdAndRemainDaysInLimitTime(Map<Integer, Integer> playerItemIdAndRemainDaysInLimitTime) {
		this.playerItemIdAndRemainDaysInLimitTime = playerItemIdAndRemainDaysInLimitTime;
	}

	public int getSecondPasswordFlag() {
		return secondPasswordFlag;
	}

	public void setSecondPasswordFlag(int secondPasswordFlag) {
		this.secondPasswordFlag = secondPasswordFlag;
	}

	public int getInternetCafe() {
		return internetCafe;
	}

	public void setInternetCafe(int internetCafe) {
		this.internetCafe = internetCafe;
	}

	public Map<Integer, Long> getRareTypeAndBuyGstExpTime() {
		return rareTypeAndBuyGstExpTime;
	}

	public void setRareTypeAndBuyGstExpTime(Map<Integer, Long> rareTypeAndBuyGstExpTime) {
		this.rareTypeAndBuyGstExpTime = rareTypeAndBuyGstExpTime;
	}

	public long getLastMeltingTime() {
		return lastMeltingTime;
	}

	public void setLastMeltingTime(long lastMeltingTime) {
		this.lastMeltingTime = lastMeltingTime;
	}

	public int getMeltingCount() {
		return meltingCount;
	}

	public void setMeltingCount(int meltingCount) {
		this.meltingCount = meltingCount;
	}

	public Map<Integer, Map<Integer, Integer>> getMeltingAward() {
		return meltingAward;
	}

	public void setMeltingAward(Map<Integer, Map<Integer, Integer>> meltingAward) {
		this.meltingAward = meltingAward;
	}

	public long getLastGameTime() {
		return lastGameTime;
	}

	public void setLastGameTime(long lastGameTime) {
		this.lastGameTime = lastGameTime;
	}

	public int getVoterCountInGame() {
		return voterCountInGame;
	}

	public void setVoterCountInGame(int voterCountInGame) {
		this.voterCountInGame = voterCountInGame;
	}
}
