package com.pearl.fcw.lobby.pojo.json;

import java.util.HashMap;
import java.util.Map;

/**
 * 合成-转换
 */
public class JsonCombineConvert {
	private int costGp = 1000;//转换花费的货币
	private Map<Integer, Float> itemTypeAndHoldLevelGpFactor = new HashMap<>();//道具类型和转换不掉级花费货币的计算参数
	private Map<Integer, Float> playerItemLevelAndHoldLevelRate = new HashMap<>();//玩家道具等级和转换不掉级率的关系
	public int getCostGp() {
		return costGp;
	}
	public void setCostGp(int costGp) {
		this.costGp = costGp;
	}

	public Map<Integer, Float> getItemTypeAndHoldLevelGpFactor() {
		return itemTypeAndHoldLevelGpFactor;
	}

	public void setItemTypeAndHoldLevelGpFactor(Map<Integer, Float> itemTypeAndHoldLevelGpFactor) {
		this.itemTypeAndHoldLevelGpFactor = itemTypeAndHoldLevelGpFactor;
	}
	public Map<Integer, Float> getPlayerItemLevelAndHoldLevelRate() {
		return playerItemLevelAndHoldLevelRate;
	}
	public void setPlayerItemLevelAndHoldLevelRate(Map<Integer, Float> playerItemLevelAndHoldLevelRate) {
		this.playerItemLevelAndHoldLevelRate = playerItemLevelAndHoldLevelRate;
	}


}
