package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.SysAchievement;
import com.pearl.o2o.utils.ServiceLocator;

public class SysAchievementDao extends BaseMappingDao{
	
	@SuppressWarnings("unused")
	private static Map<Integer, SysAchievement> allSysAchievementMap = null;
	
	
	public Map<Integer, SysAchievement> getSysAchievementMapFromDB() {
		return queryMappingBeanMap(SysAchievement.class);
	}	
	
	public Map<Integer, SysAchievement> getAllSysAchievementMap() {
		if(null == allSysAchievementMap || allSysAchievementMap.size() == 0){
			allSysAchievementMap = getSysAchievementMapFromDB();
//			joinAchievementGift();
		}
		return allSysAchievementMap;
	}
	
//	public static void joinAchievementGift(){
//		if(null != allSysAchievementMap && allSysAchievementMap.size() > 0){
//			for(SysAchievement sa : allSysAchievementMap.values()){
//				if (!"0".equals(sa.getGift())) {
//					try {
//						sa.setSysItem(ServiceLocator.getService.getSysItemByItemId(Integer.parseInt(sa.getGift())));
//					} catch (Exception e) {
//						ServiceLocator.exceptionLog.warn("SysAchievementDao-getAllSysAchievementMap getSysItemByItemId()", e);
//					}
//				}
//			}
//		}
//	}

	public void setAllSysAchievementMap(Map<Integer, SysAchievement> allSysAchievementMap) {
		SysAchievementDao.allSysAchievementMap = allSysAchievementMap;
	}	
}
