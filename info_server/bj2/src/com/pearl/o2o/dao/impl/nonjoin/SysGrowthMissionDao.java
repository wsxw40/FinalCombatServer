package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.AwardItemVo;
import com.pearl.o2o.pojo.SysGrowthMission;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.ServiceLocator;

public class SysGrowthMissionDao extends BaseMappingDao {

	private static Map<Integer, SysGrowthMission> allSysGrowthMissionByIdMap = null;

	private Map<Integer, SysGrowthMission> getGrowthMissionMapFromDB() {
		return queryMappingBeanMap(SysGrowthMission.class);
	}

	public Map<Integer, SysGrowthMission> getAllGrowthMissionMap() throws Exception {
		if (null == allSysGrowthMissionByIdMap) {
			synchronized (this) {
				if (null == allSysGrowthMissionByIdMap) {
					allSysGrowthMissionByIdMap = getGrowthMissionMapFromDB();
					joinSysItem(allSysGrowthMissionByIdMap);
				}
			}
		}
		return allSysGrowthMissionByIdMap;
	}

	private void joinSysItem(Map<Integer, SysGrowthMission> allSysGrowthMissionByIdMap) {
		if (null != allSysGrowthMissionByIdMap) {
			try {

				SysItem strengthenSysItem = ServiceLocator.getService.getUpgardmodSysItem();
				SysItem honorSysItem = ServiceLocator.getService.getMedalSysItem();
				SysItem addSucessSysItem = ServiceLocator.getService.getAddSucessSysItem();

				for (SysGrowthMission sa : allSysGrowthMissionByIdMap.values()) {
					List<AwardItemVo> awardItemVos = new ArrayList<AwardItemVo>();

					if (sa.getStrengthen() > 0) {
						awardItemVos.add(new AwardItemVo(0, strengthenSysItem, sa.getStrengthen()));
					}
					if (sa.getHonor() > 0) {
						awardItemVos.add(new AwardItemVo(0, honorSysItem, sa.getHonor()));
					}
					if (sa.getAddSucess() > 0) {
						awardItemVos.add(new AwardItemVo(0, addSucessSysItem, sa.getAddSucess()));
					}

					if (!"0".equals(sa.getGift())) {
                        //FCW 增加sysGrowthMission gift 字段解析错误提示
						try {
                            String[] split = sa.getGift().split(";");
                            for (String string : split) {
                            	String[] giftString = string.split(":");
                            	awardItemVos.add(new AwardItemVo(0, ServiceLocator.getService.getSysItemByItemId(Integer.parseInt(giftString[0])), Integer.parseInt(giftString[1])));
                            }
                        } catch (Exception e) {
                            logger.error("sysGrowthMission.id=" + sa.getId(), e);
                        }
                        //FCW
					}

					sa.setAwardItemVos(awardItemVos);
				}
			} catch (Exception e) {
				ServiceLocator.exceptionLog.warn("SysAchievementDao-getAllSysAchievementMap getSysItemByItemId()", e);
			}
		}
	}

	public SysGrowthMission getGrowthMission(GrowthMissionType gmType) throws Exception {
		return getGrowthMission(gmType.getGrowthMissionId());
	}

	public SysGrowthMission getGrowthMission(int id) throws Exception {
		return getAllGrowthMissionMap().get(id);
	}

	public static void main(String[] args) {
		String str = "4016:1;4683:1";
		String[] split = str.split(";");
		for (String string : split) {
			String[] giftString = string.split(":");
			int id = Integer.parseInt(giftString[0]);
			int num = Integer.parseInt(giftString[1]);
			System.out.println(id + ":" + num);
		}
	}
}
