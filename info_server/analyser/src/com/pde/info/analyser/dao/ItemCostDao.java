package com.pde.info.analyser.dao;

import java.util.List;
import java.util.Map;

import com.pde.info.analyser.pojo.ItemCostInfo;
import com.pde.info.analyser.pojo.ItemCostStatistic;
import com.pde.info.analyser.pojo.LevelAmountStatistic;

public interface ItemCostDao {
	
	public List<ItemCostStatistic> getStatistic(Map<String, String> param);
	
	public void addItemCostInfo(ItemCostInfo itemCostInfo);
	
}
