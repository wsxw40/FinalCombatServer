package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysItemLog;

public class SysItemLogDao extends SqlMapClientDaoSupport {
	public void createSysItemLog(SysItemLog sysItemLog) throws Exception{
		this.getSqlMapClientTemplate().insert("SysItemLog.insert",sysItemLog);
	}
	public int getSysItemLogVersion(int itemId) throws Exception{
		return (Integer)this.getSqlMapClientTemplate().queryForObject("SysItemLog.getSysItemLogVersion",itemId);
	}
	@SuppressWarnings("unchecked")
	public List<SysItem> getSysItemLog(int itemId) throws Exception{
		return (List<SysItem>)this.getSqlMapClientTemplate().queryForList("SysItemLog.select",itemId);
	}
}
