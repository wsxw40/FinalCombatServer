package com.pearl.fcw.lobby.pojo.json;

import java.util.HashMap;
import java.util.Map;

/**
 * 合成-镶嵌
 */
public class JsonCombineInsert {
	private Map<Integer, Map<Integer, Integer>> itemTypeAndPlayerItemLevelAndMaxHoleCount = new HashMap<>();//道具类型，玩家道具等级和道具最大可开孔数量的关系

	public Map<Integer, Map<Integer, Integer>> getItemTypeAndPlayerItemLevelAndMaxHoleCount() {
		return itemTypeAndPlayerItemLevelAndMaxHoleCount;
	}

	public void setItemTypeAndPlayerItemLevelAndMaxHoleCount(Map<Integer, Map<Integer, Integer>> itemTypeAndPlayerItemLevelAndMaxHoleCount) {
		this.itemTypeAndPlayerItemLevelAndMaxHoleCount = itemTypeAndPlayerItemLevelAndMaxHoleCount;
	}


}
