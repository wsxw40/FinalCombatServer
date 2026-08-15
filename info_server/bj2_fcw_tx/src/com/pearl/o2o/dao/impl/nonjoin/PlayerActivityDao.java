package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.SysActivity;



public class PlayerActivityDao  extends BaseMappingDao {
	
	public Map<Integer, PlayerActivity> getPlayerActivityMap(int playerId) {
		return queryMappingBeanMapByRelatedId(PlayerActivity.class,playerId);
	}	
	
	@SuppressWarnings("unchecked")
	public List<PlayerActivity> getPlayerActivityList(int playerId) {
		return new ArrayList(getPlayerActivityMap(playerId).values());
	}
	
	public void createPlayerActivity(List<PlayerActivity> newList) throws Exception{
		for(PlayerActivity pa:newList){
			createPlayerActivity(pa);
		}
	}
	public void createPlayerActivity(PlayerActivity playerActivity) throws Exception{
		insertObjIntoDBAndCache(playerActivity, playerActivity.getPlayerId());
	}
	public void updatePlayerActivity(PlayerActivity playerActivity) throws Exception{
		SysActivity sysActivity=new SysActivity();
		sysActivity=playerActivity.getSysActivity();
		playerActivity.setSysActivity(null);
		updateMappingBeanInCache(playerActivity, playerActivity.getPlayerId());
		playerActivity.setSysActivity(sysActivity);
	}
}
