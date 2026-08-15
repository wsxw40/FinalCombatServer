package com.pde.info.analyser.dao;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.GPointTotalStatistic;

public interface GPointTotalDao {
	
	public void addGPointTotal(GPointTotalStatistic gPonitTotal);
	public List<GPointTotalStatistic> getStatistic(Map<String, String> param);
}
