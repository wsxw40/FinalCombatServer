package com.pearl.fcw.lobby.pojo.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.gm.pojo.WPayment;

/**
 * 熔炼
 */
public class JsonCombineMelting {
	private int defalutMeltingEnergyNum = 3;//默认熔能格数
	private int meltingCountThreshold = 10;//每天熔炼次数超过该阈值后，熔炼价值公式有变化
	private int maxMeltingQuantity = 1314;//单次熔炼的最大价值
	private Map<Integer, Integer> levelAndExp = new HashMap<>();//当前等级到下一级的经验
	private List<WPayment> award = new ArrayList<>();//熔炼后的随机抽取的奖励道具

	public int getDefalutMeltingEnergyNum() {
		return defalutMeltingEnergyNum;
	}

	public void setDefalutMeltingEnergyNum(int defalutMeltingEnergyNum) {
		this.defalutMeltingEnergyNum = defalutMeltingEnergyNum;
	}

	public Map<Integer, Integer> getLevelAndExp() {
		return levelAndExp;
	}

	public void setLevelAndExp(Map<Integer, Integer> levelAndExp) {
		this.levelAndExp = levelAndExp;
	}

	public int getMeltingCountThreshold() {
		return meltingCountThreshold;
	}

	public void setMeltingCountThreshold(int meltingCountThreshold) {
		this.meltingCountThreshold = meltingCountThreshold;
	}

	public int getMaxMeltingQuantity() {
		return maxMeltingQuantity;
	}

	public void setMaxMeltingQuantity(int maxMeltingQuantity) {
		this.maxMeltingQuantity = maxMeltingQuantity;
	}

	public List<WPayment> getAward() {
		return award;
	}

	public void setAward(List<WPayment> award) {
		this.award = award;
	}
}
