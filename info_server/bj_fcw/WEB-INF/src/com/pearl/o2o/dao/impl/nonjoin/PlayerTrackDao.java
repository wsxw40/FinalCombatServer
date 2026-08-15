package com.pearl.o2o.dao.impl.nonjoin;

import com.pearl.o2o.pojo.PlayerTrack;

public class PlayerTrackDao extends BaseMappingDao {
	public PlayerTrack getPlayerTrackById(int playerId){
		return queryMappingBeanById(PlayerTrack.class, playerId);
	}
	
	public int insertPlayerTrack(PlayerTrack pt)throws Exception{
		return insertObjIntoDBAndCache(pt);
	}
	
	public void updatePlayerTrack(PlayerTrack pt)throws Exception{
		updateMappingBeanInCache(pt, pt.getPlayerId());
	}
}
