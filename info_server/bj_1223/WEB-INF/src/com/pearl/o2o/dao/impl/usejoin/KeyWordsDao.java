package com.pearl.o2o.dao.impl.usejoin;


import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class KeyWordsDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public String getKeyWords() throws DataAccessException{
		return (String)this.getSqlMapClientTemplate().queryForObject("KeyWord.select");
	}
}
