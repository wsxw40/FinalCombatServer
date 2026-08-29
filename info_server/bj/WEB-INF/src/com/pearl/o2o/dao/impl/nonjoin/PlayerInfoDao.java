package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.PlayerInfo;


public class PlayerInfoDao extends BaseMappingDao {
	@SuppressWarnings("unchecked")
	public PlayerInfo getPlayerInfoByPid(final Integer playerId) throws DataAccessException {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("playerId", playerId);
		PlayerInfo pi=(PlayerInfo)this.getSqlMapClientTemplate().queryForObject("PlayerInfo.select",map);
		return  pi;
	}
	/********************************** create  & delete **************************************************/
	
	public PlayerInfo create(PlayerInfo playerInfo) throws Exception {
		this.getSqlMapClientTemplate().insert("PlayerInfo.insert", playerInfo);
		return playerInfo;
	}
	//no use
	public void delete(PlayerInfo p ) throws Exception {
		deleteObjFromDBAndCache(p,p.getId());
	}
	
	/********************************** update  ***************************************************/
	//can not use
	public void update(final PlayerInfo playerInfo) throws Exception{
		this.getSqlMapClientTemplate().insert("PlayerInfo.update", playerInfo);
	}
}
