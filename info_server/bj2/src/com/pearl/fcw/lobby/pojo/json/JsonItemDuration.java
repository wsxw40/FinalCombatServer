package com.pearl.fcw.lobby.pojo.json;

import java.util.HashMap;
import java.util.Map;

/**
 * 道具耐久
 */
public class JsonItemDuration {
	private Float maxDuration = 100F;//最大耐久
	private Map<Integer, Float> rareTypeAndReduceFactor = new HashMap<>();//稀有度类型影响耐久减少的参数
	private Float alertDurationRatio = 0.2F;//耐久警戒百分比

	public Float getMaxDuration() {
		return maxDuration;
	}

	public void setMaxDuration(Float maxDuration) {
		this.maxDuration = maxDuration;
	}

	public Map<Integer, Float> getRareTypeAndReduceFactor() {
		return rareTypeAndReduceFactor;
	}

	public void setRareTypeAndReduceFactor(Map<Integer, Float> rareTypeAndReduceFactor) {
		this.rareTypeAndReduceFactor = rareTypeAndReduceFactor;
	}

	public Float getAlertDurationRatio() {
		return alertDurationRatio;
	}

	public void setAlertDurationRatio(Float alertDurationRatio) {
		this.alertDurationRatio = alertDurationRatio;
	}
}
