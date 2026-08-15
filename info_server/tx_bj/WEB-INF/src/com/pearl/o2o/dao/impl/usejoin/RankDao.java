package com.pearl.o2o.dao.impl.usejoin;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Rank;

public class RankDao extends SqlMapClientDaoSupport{
	@SuppressWarnings("unchecked")
	public List<Rank> getRankList()throws DataAccessException{
		return this.getSqlMapClientTemplate().queryForList("Rank.select");
	}
}
