package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.LevelTypeAmountDao;
import com.pde.info.analyser.pojo.LevelTypeAmount;
import com.pde.info.analyser.pojo.LevelTypeAmountStatistic;
import com.pde.info.analyser.service.LevelTypeAmountService;

public class LevelTypeAmountServiceImpl implements LevelTypeAmountService {
	
	private LevelTypeAmountDao levelTypeAmountDao;

	@Override
	public void addLevelTypeAmount(LevelTypeAmount levelTypeAmount) {
		levelTypeAmountDao.addLevelTypeAmount(levelTypeAmount);
	}

	@Override
	public List<LevelTypeAmountStatistic> getStatistic(Map<String, String> param) {
		return levelTypeAmountDao.getStatistic(param);
	}

	public void setLevelTypeAmountDao(LevelTypeAmountDao levelTypeAmountDao) {
		this.levelTypeAmountDao = levelTypeAmountDao;
	}

}
