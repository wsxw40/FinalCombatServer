package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.SysMatchP;

//zlm2015-10-9-匹配
public class SysMatchPDao extends BaseMappingDao {

	private static Map<Integer, SysMatchP> allSysMatchPMap = null;

	public Map<Integer, SysMatchP> getSysMatchPMapFromDB() {
		return queryMappingBeanMap(SysMatchP.class);
	}

	public Map<Integer, SysMatchP> getAllSysMatchPMap() {
		if (null == allSysMatchPMap || allSysMatchPMap.size() == 0) {
			allSysMatchPMap = getSysMatchPMapFromDB();
		}
		return allSysMatchPMap;
	}

	public void setAllSysMatchPMap(Map<Integer, SysMatchP> allSysMatchPMap) {
		SysMatchPDao.allSysMatchPMap = allSysMatchPMap;
	}

}
