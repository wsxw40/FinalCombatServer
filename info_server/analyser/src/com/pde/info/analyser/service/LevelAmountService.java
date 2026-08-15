package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.LevelAmountStatistic;

public interface LevelAmountService {

	public List<LevelAmountStatistic> getStatistic(Map<String, String> param);
}
