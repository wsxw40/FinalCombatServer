package com.pde.info.analyser.dao;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.LuckyPackage;
import com.pde.info.analyser.pojo.LuckyPackageStatistic;
import com.pde.info.analyser.pojo.MedalExchange;
import com.pde.info.analyser.pojo.MedalExchangeStatistic;

public interface MedalExchangeDao {

	public void addExchangeInfo(MedalExchange medalCost);

	public List<MedalExchangeStatistic> getStatistic(Map<String, String> param);
}
