package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.BattleFieldRobDaily;

public class BattleFieldRobDailyDao extends BaseMappingDao{
	@SuppressWarnings("unchecked")
	public Collection<BattleFieldRobDaily> getBattleFieldRobDailys(int teamId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TEAM_ID", teamId);
		return this.getSqlMapClientTemplate().queryForList("BattleFieldRobDaily.selectBattleFieldRobDailyByTeamId", map);
	}
	@SuppressWarnings("unchecked")
	public List<BattleFieldRobDaily> getBattleFieldRobDailyForTop(String nowTime,String preTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowTime", nowTime);
		map.put("preTime", preTime);
		return this.getSqlMapClientTemplate().queryForList("BattleFieldRobDaily.selectBattleFieldRobDailyForTop", map);
	}
	public void insert(BattleFieldRobDaily battleFieldRobDaily) throws Exception{
		
		insertObjIntoDBAndCache(battleFieldRobDaily);
	}
}
