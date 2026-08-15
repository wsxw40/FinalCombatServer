package com.pde.info.analyser.service.impl;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.dao.LevelAmountDao;
import com.pde.info.analyser.pojo.LevelAmountStatistic;
import com.pde.info.analyser.service.LevelAmountService;

public class LevelAmountServiceImpl implements LevelAmountService {
	
	private LevelAmountDao levelAmountDao;

	@Override
	public List<LevelAmountStatistic> getStatistic(Map<String, String> param) {
		return levelAmountDao.getStatistic(param);
	}

	public void setLevelAmountDao(LevelAmountDao levelAmountDao) {
		this.levelAmountDao = levelAmountDao;
	}
}
