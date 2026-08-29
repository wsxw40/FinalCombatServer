package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.ReChargeInfo;
import com.pde.info.analyser.pojo.ReChargeStatistic;

public interface ReChargeService {
	
	public void addReChargeInfo(ReChargeInfo reChargeInfo);
	
	public List<ReChargeStatistic> getStatistic(Map<String, String> param);
	
}
