package com.pearl.o2o.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SysModeConfig;

public class SysModeConfigDao extends SqlMapClientDaoSupport {
	public SysModeConfig getSysModeConfig(int type)throws Exception{
		return (SysModeConfig)this.getSqlMapClientTemplate().queryForObject("SysModeConfig.select", type);
	}
	public void createSysModeConfigDao(SysModeConfig config)throws Exception{
		this.getSqlMapClientTemplate().insert("SysModeConfig.insert", config);
	}
	public void updateSysModeConfigDao(SysModeConfig config)throws Exception{
		this.getSqlMapClientTemplate().update("SysModeConfig.update", config);
	}
}
