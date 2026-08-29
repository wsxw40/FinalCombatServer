package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.GmGroup;

public class GmGroupPrivilegeDao extends SqlMapClientDaoSupport {

	public void create(int groupId, int privId) throws DataAccessException{		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("groupId", groupId);
		param.put("privId", privId);
		this.getSqlMapClientTemplate().insert("GmGroupPrivilege.insert", param);
	}
	
	public void delete(int groupId) throws DataAccessException{
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("groupId", groupId);
		this.getSqlMapClientTemplate().delete("GmGroupPrivilege.delete", param);
	}
	
	public void delete(List<GmGroup> groups) throws DataAccessException{
		Map<String, List<GmGroup>> param = new HashMap<String,  List<GmGroup>>();
		param.put("groups", groups);
		this.getSqlMapClientTemplate().delete("GmGroupPrivilege.delete", param);
	}
}
