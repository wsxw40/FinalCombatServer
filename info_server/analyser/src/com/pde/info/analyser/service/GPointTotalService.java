package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.GPointTotalStatistic;

public interface GPointTotalService {

	public void addGPointTotal(GPointTotalStatistic gPonitTotal);
	public List<GPointTotalStatistic> getStatistic(Map<String, String> param);
}
