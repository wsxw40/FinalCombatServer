package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class AllyDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public void addAlly(int teamId, int allyId){
		Map map = new HashMap();
		map.put("teamId", teamId);
		map.put("allyId", allyId);
		this.getSqlMapClientTemplate().insert("Team.addAlly",map);
	}
	
	@SuppressWarnings("unchecked")
	public void removeAlly(int teamId, int allyId){
		Map map = new HashMap();
		map.put("teamId", teamId);
		map.put("allyId", allyId);
		this.getSqlMapClientTemplate().insert("Team.removeAlly",map);
	}
	
}
