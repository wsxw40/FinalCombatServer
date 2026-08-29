package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.GmGroup;

public class GmUserGroupDao extends SqlMapClientDaoSupport {

	public void create(int userId, int groupId) throws DataAccessException{
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("userId", userId);
		param.put("groupId", groupId);
		this.getSqlMapClientTemplate().insert("GmUserGroup.insert", param);
	}
	
	public void delete(int userId, int groupId) throws DataAccessException{
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("userId", userId);
		param.put("groupId", groupId);
		this.getSqlMapClientTemplate().delete("GmUserGroup.delete", param);
	}
	
	public void delete(int groupId) throws DataAccessException{
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("groupId", groupId);
		this.getSqlMapClientTemplate().delete("GmUserGroup.delete", param);
	}
	
	public void deleteByUserId(int userId) throws DataAccessException{
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("userId", userId);
		this.getSqlMapClientTemplate().delete("GmUserGroup.delete", param);
	}
	
	public void delete(List<GmGroup> groups) throws DataAccessException{
		Map<String, List<GmGroup>> param = new HashMap<String, List<GmGroup>>();
		param.put("groups", groups);
		this.getSqlMapClientTemplate().delete("GmUserGroup.delete", param);
	}
}
