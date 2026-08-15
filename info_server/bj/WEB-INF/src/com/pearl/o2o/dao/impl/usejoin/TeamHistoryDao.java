package com.pearl.o2o.dao.impl.usejoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.TeamHistory;

public class TeamHistoryDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<TeamHistory> getTeamHistoryById(Integer id) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return (List<TeamHistory>)this.getSqlMapClientTemplate().queryForList("TeamHistory.select",map);
	}
	
	public void addHistory(int teamId, String opponentName, int result, Set<String> attendees){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teamId", teamId);
		map.put("opponent", opponentName);
		map.put("result", result);
		
		this.getSqlMapClientTemplate().insert("TeamHistory.addTeamHistory", map);
	}
}
