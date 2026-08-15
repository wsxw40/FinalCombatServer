package com.pearl.fcw.lobby.pojo.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新手保护的参数<br/>
 * iValue=((playerLevelAndFactor^index)^0.5-1)*100
 */
public class JsonNewerBuff {
	private float index;//总影响系数
	private Map<Integer, Float> playerLevelAndFactor = new HashMap<>();//玩家等级影响的系数
	private List<Integer> iBuffIdList = new ArrayList<>();//对应sysItem.iBuffId

	public Map<Integer, Float> getPlayerLevelAndFactor() {
		return playerLevelAndFactor;
	}

	public void setPlayerLevelAndFactor(Map<Integer, Float> playerLevelAndFactor) {
		this.playerLevelAndFactor = playerLevelAndFactor;
	}

	public float getIndex() {
		return index;
	}

	public void setIndex(float index) {
		this.index = index;
	}

	public List<Integer> getiBuffIdList() {
		return iBuffIdList;
	}

	public void setiBuffIdList(List<Integer> iBuffIdList) {
		this.iBuffIdList = iBuffIdList;
	}

}
