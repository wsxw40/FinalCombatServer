package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.PlayerChest;

/**
 * @author wuxiaofei
 * 
 */
public class PlayerChestDao extends BaseMappingDao {
	public Map<Integer, PlayerChest> getPlayerChestMapByPlayerId(int playerId) {
		return queryMappingBeanMapByRelatedId(PlayerChest.class, playerId);
	}
	
	public List<PlayerChest> getPlayerChestListByPlayerId(int playerId) {
		return new ArrayList<PlayerChest>(getPlayerChestMapByPlayerId(playerId).values());
	}
	
	public void createPlayerChest(PlayerChest pc) throws Exception{
		insertObjIntoDBAndCache(pc, pc.getPlayerId());
	}
	
	public void updatePlayerChest(PlayerChest pc) throws Exception{
		updateMappingBeanInCache(pc, pc.getPlayerId());
	}
	
	public void deletePlayerChest(PlayerChest pc) throws Exception{
		deleteObjFromDBAndCache(pc, pc.getPlayerId());
	}
}
