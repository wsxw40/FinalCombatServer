package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.MedalExchangeStatistic;
import com.pde.info.analyser.pojo.MedalExchange;

public interface MedalExchangeService {
	public void addExchangeInfo(MedalExchange medalCost);
	
	public List<MedalExchangeStatistic> getStatistic(Map<String, String> param);
}
