package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.LevelTypeAmount;
import com.pde.info.analyser.pojo.LevelTypeAmountStatistic;


public interface LevelTypeAmountService {

	public void addLevelTypeAmount(LevelTypeAmount levelTypeAmount);

	public List<LevelTypeAmountStatistic> getStatistic(Map<String, String> param);

}
