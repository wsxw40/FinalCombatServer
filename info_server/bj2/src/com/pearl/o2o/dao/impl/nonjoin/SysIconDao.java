package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.SysIcon;

public class SysIconDao extends BaseMappingDao{
	private static final int DEFAULT_ID = 1;
	
	@SuppressWarnings("unchecked")
	public Map<Integer, SysIcon> getSysIconMap() {
		return queryMappingBeanMapByRelatedId(SysIcon.class, DEFAULT_ID);
	}		
	
	@SuppressWarnings("unchecked")
	public List<SysIcon> getSysIconList() {
		return new ArrayList(getSysIconMap().values());
	}	
}
