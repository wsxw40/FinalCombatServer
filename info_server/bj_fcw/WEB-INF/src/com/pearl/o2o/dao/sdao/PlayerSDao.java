package com.pearl.o2o.dao.sdao;

import java.util.HashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Player;

public class PlayerSDao extends SqlMapClientDaoSupport  {
	
	public Player getPlayerById(Integer playerId) throws DataAccessException {
		HashMap<String, Integer> param = new HashMap<String, Integer>();
		param.put("playerId", playerId);
		return (Player)this.getSqlMapClientTemplate().queryForObject("ssql.Player.select", param);
	}
}
