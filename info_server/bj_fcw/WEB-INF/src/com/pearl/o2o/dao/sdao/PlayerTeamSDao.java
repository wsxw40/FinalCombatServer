package com.pearl.o2o.dao.sdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.PlayerTeam;


public class PlayerTeamSDao extends SqlMapClientDaoSupport {
	
	public PlayerTeam getByPlayerId(int id){
		return (PlayerTeam) this.getSqlMapClientTemplate().queryForObject("ssql.PlayerTeam.selectByPlayerId", id);
	}
	
	
}
