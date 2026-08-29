package com.pearl.o2o.dao.impl.nonjoin;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.StrengthenAppend;


public class StrengthenAppendDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<StrengthenAppend> getAllStreAppList()throws Exception{
		return this.getSqlMapClientTemplate().queryForList("StrengthenAppend.select");
	}
	public void createStreApp(StrengthenAppend s)throws Exception{
		this.getSqlMapClientTemplate().insert("StrengthenAppend.insert", s);
	}
	public void updateStreApp(StrengthenAppend s)throws Exception{
		this.getSqlMapClientTemplate().update("StrengthenAppend.update", s);
	}
	public void deleteStreApp(int sid)throws Exception{
		this.getSqlMapClientTemplate().delete("StrengthenAppend.delete", sid);
	}
}
