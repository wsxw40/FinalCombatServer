package com.pearl.o2o.dao.impl.usejoin;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SysModeConfig;

public class SysModeConfigDao extends SqlMapClientDaoSupport {
	public SysModeConfig getSysModeConfig(int type) {
		return (SysModeConfig)this.getSqlMapClientTemplate().queryForObject("SysModeConfig.select", type);
	}
	public void createSysModeConfigDao(SysModeConfig config) {
		this.getSqlMapClientTemplate().insert("SysModeConfig.insert", config);
	}
	public void updateSysModeConfigDao(SysModeConfig config) {
		this.getSqlMapClientTemplate().update("SysModeConfig.update", config);
	}
}
