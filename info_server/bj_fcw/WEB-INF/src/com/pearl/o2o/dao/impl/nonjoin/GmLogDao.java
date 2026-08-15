package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.GmLog;

public class GmLogDao extends SqlMapClientDaoSupport {

	public void createGmLog(GmLog gl) throws DataAccessException{
		this.getSqlMapClientTemplate().insert("GmLog.insert", gl);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GmLog> getGmLogs(int gmUserId, String gmUserName, String type,  Date start, Date end) throws DataAccessException{
		Map param = new HashMap();
		param.put("gmUserId", gmUserId);
		param.put("gmUserName", gmUserName);
		param.put("type", type);
		param.put("start", start);
		param.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("GmLog.select", param);
	}
}
