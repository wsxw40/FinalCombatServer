package com.pearl.o2o.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamScoreIncrement;

public class TeamDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public int createTeam(Team team) throws Exception{
		return (Integer)this.getSqlMapClientTemplate().insert("Team.insert",team);
	}
	@SuppressWarnings("unchecked")
	public Team getTeamById(Integer id) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return (Team)this.getSqlMapClientTemplate().queryForObject("Team.select",map);
	}
	@SuppressWarnings("unchecked")
	public List<Team> getTeamByName(String name) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.select",map);
	}
	public int fuzzyCountTeam(String name) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Team.fuzzyCountTeam", name);
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeamByIds(Set<Integer> ids){
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(ids);
		return this.getSqlMapClientTemplate().queryForList("Team.selectByIds", list);
	}
	
	public Team getTeamByPlayerId(int playerId){
		return (Team) this.getSqlMapClientTemplate().queryForObject("Team.selectByPlayerId", playerId);
	}
	
	
	public void updateSize(int teamId,int size){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teamId", teamId);
		map.put("size", size);
		 this.getSqlMapClientTemplate().update("Team.updateSize", map);
	}
	
	@SuppressWarnings("unchecked")
	public void updateTeamInfo(String field, String value, int teamId){
		Map paramMap = new HashMap();
		//field can be 'declare | desc | board', see the interface 'c_team_update_op'
		paramMap.put(field, value);
		paramMap.put("id", teamId);
		this.getSqlMapClientTemplate().update("Team.updateTeamInfo", paramMap);
	}
	
	public void updateTeamGameInfo(TeamScoreIncrement teamIncrement){
		 this.getSqlMapClientTemplate().update("Team.updateTeamScore", teamIncrement);
	}
	
	//batch update for team
	public void updateTeamGameInfo(final Collection<TeamScoreIncrement> teamIncrements) throws SQLException {
		 SqlMapClientCallback callback = new SqlMapClientCallback() {
		        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		            executor.startBatch();
		            for (TeamScoreIncrement tsi : teamIncrements) {
		                executor.update("Team.updateTeamScore", tsi);
		            }
		            executor.executeBatch();
		            return null;
		        }
		    };
		this.getSqlMapClientTemplate().execute(callback);
	}
	public void deleteTeamById(Integer userId,Integer playerId,Integer teamId) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("playerId", playerId);
		map.put("teamId", teamId);
		this.getSqlMapClientTemplate().delete("Team.delete",map);
	}
	public void deletePlayerTeamById(Integer userId,Integer playerId,Integer teamId) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("playerId", playerId);
		map.put("teamId", teamId);
		this.getSqlMapClientTemplate().delete("Team.deletePlayerTeam",map);
	}
}
