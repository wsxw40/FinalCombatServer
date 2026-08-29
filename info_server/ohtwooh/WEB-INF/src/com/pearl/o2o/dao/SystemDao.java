package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SystemDao  extends SqlMapClientDaoSupport{
	public String getValue(String key){
		return (String) this.getSqlMapClientTemplate().queryForObject("SysConfiguration.select",key);
	}
	
	public void setValue(String key, String value){
		Map param = new HashMap();
		param.put("key", key);
		param.put("value", value);
		this.getSqlMapClientTemplate().update("SysConfiguration.update", param);
	}
}
