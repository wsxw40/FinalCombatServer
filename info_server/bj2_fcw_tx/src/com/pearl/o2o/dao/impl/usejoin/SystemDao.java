package com.pearl.o2o.dao.impl.usejoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SysConfiguration;

public class SystemDao  extends SqlMapClientDaoSupport{
	public String getValue(String key){
		return getSysConfig().get(key);
	}
	@SuppressWarnings("unchecked")
	public List<SysConfiguration> getSysConfigList(){
		return (List<SysConfiguration>)this.getSqlMapClientTemplate().queryForList("SysConfiguration.select");
	}
	public void createSysConfig(SysConfiguration sc){
		this.getSqlMapClientTemplate().insert("SysConfiguration.insert",sc);
	}
	public void deleteSysConfig(SysConfiguration sc){
		this.getSqlMapClientTemplate().delete("SysConfiguration.delete",sc);
	}
	@SuppressWarnings("unchecked")
	public Map<String, String> getSysConfig(){
		Map<String, String> result = new HashMap<String, String>();
		List<SysConfiguration> cfgs = this.getSqlMapClientTemplate().queryForList("SysConfiguration.select");
		for (SysConfiguration cfg : cfgs ) {
			result.put(cfg.getKey(), cfg.getValue());
		}
		return result;
	}
	
	public void setValue(String key, String value){
		Map param = new HashMap();
		param.put("key", key);
		param.put("value", value);
		this.getSqlMapClientTemplate().update("SysConfiguration.update", param);
	}
	
}
