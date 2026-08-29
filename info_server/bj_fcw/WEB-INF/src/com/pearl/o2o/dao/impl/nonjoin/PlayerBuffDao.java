package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.PlayerBuff;

public class PlayerBuffDao extends BaseMappingDao  {
	
	public Map<Integer,PlayerBuff> getPlayerBuffMapByPlayerId(int playerId){
		return queryMappingBeanMapByRelatedId(PlayerBuff.class, playerId);
	}
	
	public List<PlayerBuff> getPlayerBuffListByPlayerId(int playerId)throws DataAccessException{
		return new ArrayList<PlayerBuff>(getPlayerBuffMapByPlayerId(playerId).values());
	}
	
	public void creatPlayerBuff(PlayerBuff playerBuff)throws Exception{
		insertObjIntoDBAndCache(playerBuff, playerBuff.getPlayerId());
	}
	
	public void updatePlayerBuff(PlayerBuff playerBuff)throws Exception{
		updateMappingBeanInCache(playerBuff, playerBuff.getPlayerId());
	}
	public void deletePlayerBuff(PlayerBuff playerBuff)throws Exception{
		deleteObjFromDBAndCache(playerBuff, playerBuff.getPlayerId());
	}
	public PlayerBuff fuzzyGetPlayerBuffById(int playerId, int buffId)throws Exception{
		List<PlayerBuff> list = getPlayerBuffListByPlayerId(playerId);
		if(null != list){
			for(PlayerBuff pb : list){
				if(pb.getBuffId() == buffId){
					return pb;
				}
			}
		}
		return null;
	}
}
