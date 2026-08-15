package com.pearl.o2o.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Rank;

public class RankDao extends SqlMapClientDaoSupport{
	@SuppressWarnings("unchecked")
	public List<Rank> getRankList()throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("Rank.select");
	}
	@SuppressWarnings("unchecked")
	public Rank getRankTitle(int exp)throws DataAccessException{
		return (Rank)this.getSqlMapClientTemplate().queryForObject("Rank.selectTitle",exp);
	}
	@SuppressWarnings("unchecked")
	public Rank getRankExp(int id)throws DataAccessException{
		return (Rank)this.getSqlMapClientTemplate().queryForObject("Rank.selectExp",id);
	}
}
