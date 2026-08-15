package com.pde.info.analyser.dao;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.LevelAmountStatistic;

public interface LevelAmountDao {

	public List<LevelAmountStatistic> getStatistic(Map<String, String> param);
}
