package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.SysActivity;



public class SysActivityDao  extends BaseMappingDao {
	
	public Map<Integer, SysActivity> getSysActivityMap() {
		return queryMappingBeanMap(SysActivity.class);
	}	
	
	@SuppressWarnings("unchecked")
	public List<SysActivity> getSysActivityList() {
		return new ArrayList(getSysActivityMap().values());
	}
	
	public void insert(SysActivity sa) throws Exception{
		this.insertObjIntoDBAndCacheWithDefaultId(sa);
	}
	
	public void delete(SysActivity sa) throws Exception{
		this.deleteObjFromDBAndCacheWithDefaultId(sa);
	}
}
