package com.pearl.o2o.dao.sdao;

import java.util.HashMap;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Player;

public class PlayerSDao extends SqlMapClientDaoSupport  {
	
	@SuppressWarnings("unchecked")
	public Player getPlayerById(Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("playerId", playerId);
		return (Player)this.getSqlMapClientTemplate().queryForObject("ssql.Player.select", param);
	}
}
