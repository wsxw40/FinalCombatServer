package com.pde.info.analyser.service;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.ItemCostInfo;
import com.pde.info.analyser.pojo.ItemCostStatistic;

public interface ItemCostService {

	public void addItemCostInfo(ItemCostInfo itemCostInfo);
	
	
	public List<ItemCostStatistic> getStatistic(Map<String, String> param);
	
	public List<ItemCostStatistic> getStatisticSum(Map<String, String> param);
}
