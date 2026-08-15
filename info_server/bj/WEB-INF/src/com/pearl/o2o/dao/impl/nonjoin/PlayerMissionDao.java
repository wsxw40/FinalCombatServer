package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.SysMission;

public class PlayerMissionDao extends BaseMappingDao{
	
	public Map<Integer, PlayerMission> getPlayerMissionMap(int playerId) {
		return queryMappingBeanMapByRelatedId(PlayerMission.class, playerId);
	}	
	
	public List<PlayerMission> getPlayerMissionList(int playerId){
		Map<Integer, PlayerMission> map = getPlayerMissionMap(playerId);
		if (map != null){
			return new ArrayList<PlayerMission>(map.values());
		}else {
			return new ArrayList<PlayerMission>();
		}
	}
	
//	public List<PlayerMission> getPlayerDailyMissionList(int playerId){
//		List<PlayerMission> list = getPlayerMissionList(playerId);
//		List<PlayerMission> result = new ArrayList<PlayerMission>();
//		for(PlayerMission m : list){
//			if(Constants.NUM_ZERO == m.getType()){
//				result.add(m);
//			}
//		}
//		return result;
//	}
	
//	public List<PlayerMission> getPlayerWeekMissionList(int playerId){
//		List<PlayerMission> list = getPlayerMissionList(playerId);
//		List<PlayerMission> result = new ArrayList<PlayerMission>();
//		for(PlayerMission m : list){
//			if(Constants.NUM_ONE == m.getType()){
//				result.add(m);
//			}
//		}
//		return result;
//	}
	
	public int createPlayerMission(PlayerMission pm) throws Exception{
		return insertObjIntoDBAndCache(pm, pm.getPlayerId());
	}
	
	public void updatePlayerMission(PlayerMission pa) throws Exception{
		SysMission sysMission = pa.getSysMission();
		pa.setSysMission(null);
		updateMappingBeanInCache(pa, pa.getPlayerId());
		pa.setSysMission(sysMission);
	}
	
	public void deletePlayerMissionFromDBAndCache(PlayerMission obj) throws Exception{
		deleteObjFromDBAndCache(obj, obj.getPlayerId());
	}
}
