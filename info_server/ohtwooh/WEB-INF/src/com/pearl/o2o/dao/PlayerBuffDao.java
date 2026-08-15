package com.pearl.o2o.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.PlayerItem;

public class PlayerBuffDao extends SqlMapClientDaoSupport {
	public void creatPlayerBuff(PlayerItem playerItem)throws DataAccessException{
		this.getSqlMapClientTemplate().insert("PlayerBuff.insert",playerItem);
	}
	public void updatePlayerBuff(PlayerItem playerItem)throws DataAccessException{
		this.getSqlMapClientTemplate().insert("PlayerBuff.update",playerItem);
	}
	public int fuzzyCountPlayerBuff(PlayerItem playerItem)throws DataAccessException{
		return (Integer)this.getSqlMapClientTemplate().queryForObject("PlayerBuff.fuzzySelect",playerItem);
	}

}
