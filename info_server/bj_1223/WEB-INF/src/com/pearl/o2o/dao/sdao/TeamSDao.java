package com.pearl.o2o.dao.sdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Team;

public class TeamSDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public int createTeam(Team team) throws Exception{
		return (Integer)this.getSqlMapClientTemplate().insert("Team.insert",team);
	}
	@SuppressWarnings("unchecked")
	public Team getTeamById(Integer id) throws DataAccessException{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		return (Team)this.getSqlMapClientTemplate().queryForObject("ssq.Team.select",map);
	}
	@SuppressWarnings("unchecked")
	public List<Team> getTeamByName(String name) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.select",map);
	}
	
	@SuppressWarnings("unchecked")
	public Team getTeamBySpecificName(String name) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("specificName", name);
		return (Team) this.getSqlMapClientTemplate().queryForObject("Team.select",map);
	}
}
