package com.pde.info.analyser.dao;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.LuckyPackage;
import com.pde.info.analyser.pojo.LuckyPackageStatistic;

public interface LuckyPackageDao {

	public void addLuckyPackage(LuckyPackage luckyPackage);

	public List<LuckyPackageStatistic> getStatistic(Map<String, String> param);
}
