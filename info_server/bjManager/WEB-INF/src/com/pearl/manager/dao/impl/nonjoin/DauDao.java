package com.pearl.manager.dao.impl.nonjoin;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.BaseDAUAbout;
import com.pearl.manager.pojo.ChargeLog;

public class DauDao extends SqlMapClientDaoSupport{
	
	@SuppressWarnings("unchecked")
	public List<BaseDAUAbout> getDauList(Date start) throws SQLException{
		Map<String, Date> map=new HashMap<String, Date>();
		if(start==null) start=new Date();
		map.put("start", start);
		return (List<BaseDAUAbout>)this.getSqlMapClient().queryForList("DAUAbout.getDauList", map);
	}
	
}
