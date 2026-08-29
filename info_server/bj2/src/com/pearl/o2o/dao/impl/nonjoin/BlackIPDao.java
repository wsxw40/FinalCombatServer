package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.BlackIP;

public class BlackIPDao extends SqlMapClientDaoSupport {
	public BlackIP createBlackIP(BlackIP blackIP) throws DataAccessException {
		int id = (Integer) this.getSqlMapClientTemplate().insert(
				"BlackIP.insert", blackIP);
		blackIP.setId(id);
		return blackIP;
	}

	public void deleteBlackIP(int id) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("BlackIP.delete", id);
	}

	public void updateBlackIP(BlackIP blackIP) throws DataAccessException {
		this.getSqlMapClientTemplate().update("BlackIP.update", blackIP);
	}

	@SuppressWarnings("unchecked")
	public List<BlackIP> selectBlackIP() throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("BlackIP.select");
	}

	@SuppressWarnings("unchecked")
	public List<BlackIP> selectByIP(String ip) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList(
				"BlackIP.selectByIP", ip);
	}
}
