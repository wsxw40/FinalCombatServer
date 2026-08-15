package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.SysMission;
import com.pearl.o2o.utils.ServiceLocator;

public class SysMissionDao extends BaseMappingDao{
	
	private static Map<Integer, SysMission> allSysMissionMap = null;

	public Map<Integer, SysMission> getAllSysMissionMap() {
		if(null == allSysMissionMap || allSysMissionMap.size() == 0){
			allSysMissionMap = getSysMissionMapFromDB();
			joinMissionGift();
		}
		return allSysMissionMap;
	}

	public static void joinMissionGift(){
		if (null != allSysMissionMap && allSysMissionMap.size() > 0) {
			for (SysMission sysMission : allSysMissionMap.values()) {
				try {
					ServiceLocator.updateService.joinPrize(sysMission);
				} catch (Exception e) {
					ServiceLocator.exceptionLog.warn("error in joinPrize when getAllSysMissionMap", e);
				}
			}
		}
	}
	
	public void setAllSysMissionMap(Map<Integer, SysMission> allSysMissionMap) {
		SysMissionDao.allSysMissionMap = allSysMissionMap;
	}	
	
	public Map<Integer, SysMission> getSysMissionMapFromDB() {
		return queryMappingBeanMap(SysMission.class);
	}	
	
//	public void deleteSysMissionInDBAndCache(SysMission sysMission) throws Exception {
//		this.deleteObjFromDBAndCacheWithDefaultId(sysMission);
//	}
	
//	public int insertSysMissionInDBAndCache(SysMission sysMission) throws Exception {
//		int id = this.insertObjIntoDBAndCacheWithDefaultId(sysMission);
//		return id;
//	}
}
