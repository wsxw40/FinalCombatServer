package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.utils.Constants.BattleFieldRobDailyType;

public class BattleFieldRobDailyDao extends BaseMappingDao{
	@SuppressWarnings("unchecked")
	public Collection<BattleFieldRobDaily> getBattleFieldRobDailys(int teamId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attTeamId", teamId);
		return this.getSqlMapClientTemplate().queryForList("BattleFieldRobDaily.selectBattleFieldRobDailyByTeamId", map);
	}
	@SuppressWarnings("unchecked")
	public List<BattleFieldRobDaily> getBattleFieldRobDailyForTop(String nowTime,String preTime,BattleFieldRobDailyType type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowTime", nowTime);
		map.put("preTime", preTime);
		map.put("type", type.getValue());
		return this.getSqlMapClientTemplate().queryForList("BattleFieldRobDaily.selectBattleFieldRobDailyForTop", map);
	}
	public void insert(BattleFieldRobDaily battleFieldRobDaily) throws Exception{
		
		insertObjIntoDBAndCache(battleFieldRobDaily);
	}
	
	public List<BattleFieldRobDaily> getBattleFieldRobDailyByTeamAndTime(int teamId,Date nowTime,Date preTime ,BattleFieldRobDailyType type){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamId" , teamId );
		map.put("nowTime", nowTime);
		map.put("preTime", preTime);
		map.put("type", type.getValue());
		return this.getSqlMapClientTemplate().queryForList("BattleFieldRobDaily.selectBattleFieldRobDailyByTeamAndTime", map);
	}	
	
	public void cleanUnUsedBattleFieldRobDaily(Date endDate){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endDate", endDate);
		this.getSqlMapClientTemplate().delete("BattleFieldRobDaily.cleanUnUsedBattleFieldRobDaily", map);
	}
}
