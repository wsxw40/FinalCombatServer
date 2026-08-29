package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.BlackWhiteList;

public class BlackWhiteListDao extends SqlMapClientDaoSupport {

	public BlackWhiteList createBlackWhiteList(BlackWhiteList blackWhiteList)
			throws DataAccessException {
		int id = (Integer) this.getSqlMapClientTemplate().insert(
				"BlackWhiteList.insert", blackWhiteList);
		blackWhiteList.setId(id);
		return blackWhiteList;
	}

	public void deleteBlackWhiteList(int id) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("BlackWhiteList.delete", id);
	}

	public void updateBlackWhiteList(BlackWhiteList blackWhiteList)
			throws DataAccessException {
		this.getSqlMapClientTemplate().update("BlackWhiteList.update",
				blackWhiteList);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlackWhiteList> selectBlackWhiteList() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("BlackWhiteList.select");
	}
}
