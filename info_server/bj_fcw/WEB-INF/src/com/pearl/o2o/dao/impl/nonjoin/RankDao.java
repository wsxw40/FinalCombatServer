package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.Rank;

public class RankDao extends BaseMappingDao{
	private static final int DEFAULT_ID = 1;
	
	public Map<Integer, Rank> getRankMap() {
		return queryMappingBeanMapByRelatedId(Rank.class, DEFAULT_ID);
	}		
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Rank> getRankList() {
		return new ArrayList(getRankMap().values());
	}	
}
