package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.PlayerGrowthMission;

public class PlayerGrowthMissionDao extends BaseMappingDao {

//	public PlayerGrowthMission getPaGrowthMissions(int playerId,int growthMissionId) throws Exception {
//		Map<Integer, PlayerGrowthMission> playerGrowthMissionMap = queryMappingBeanMapByRelatedId(PlayerGrowthMission.class, playerId);
//		PlayerGrowthMission playerGrowthMission = playerGrowthMissionMap.get(growthMissionId);
//		if (null == playerGrowthMission) {
//			playerGrowthMission = new PlayerGrowthMission(playerId, growthMissionId);
//			createPlayerGrowthMission(playerId, playerGrowthMission);
//		}
//		return playerGrowthMission;
//	}
	public Map<Integer, PlayerGrowthMission> getPaGrowthMissions(int playerId) throws Exception {
		Map<Integer, PlayerGrowthMission> playerGrowthMissionMap = queryMappingBeanMapByRelatedId(PlayerGrowthMission.class, playerId);
		return playerGrowthMissionMap;
	}

//	public Map<Integer, PlayerGrowthMission> createPlayerGrowthMissions(final int playerId) throws Exception {
//		ServiceLocator.growthMissionErrLog.debug("PlayerGrowthMissionDao.createPlayerGrowthMissions playerId:"+playerId);
//		Map<Integer, PlayerGrowthMission> playerGrowthMissionMap = new HashMap<Integer, PlayerGrowthMission>();
//
//		Map<Integer, SysGrowthMission> allGrowthMissionMap = ServiceLocator.getService.getSysGrowthMissionDao().getAllGrowthMissionMap();
//		if (null == allGrowthMissionMap) {
//			return null;
//		}
//		for (SysGrowthMission sysGrowthMission : allGrowthMissionMap.values()) {
//			PlayerGrowthMission pgm = new PlayerGrowthMission(playerId, sysGrowthMission.getId());
//			playerGrowthMissionMap.put(sysGrowthMission.getId(), pgm);
//		}
//		return playerGrowthMissionMap;
//	}

	public void createPlayerGrowthMission(final int playerId, final PlayerGrowthMission pgm) throws Exception {
		insertObjIntoDBAndCache(pgm, playerId);
	}

	public void updatePlayerGrowthMissions(final PlayerGrowthMission pgm) throws Exception {
		updateMappingBeanInCache(pgm, pgm.getPlayerId());
	}
	
}
