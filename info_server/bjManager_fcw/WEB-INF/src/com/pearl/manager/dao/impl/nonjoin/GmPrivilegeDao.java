package com.pearl.manager.dao.impl.nonjoin;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.GmPrivilege;

public class GmPrivilegeDao extends SqlMapClientDaoSupport {

	@SuppressWarnings("unchecked")
	public List<GmPrivilege> getGmPrivilegesByGroupId(int groupId){
		return this.getSqlMapClientTemplate().queryForList("GmPrivilege.selectPrivByGroupId", groupId);
	}
	
	@SuppressWarnings("unchecked")
	public List<GmPrivilege> getGmPrivilegesByUserId(int userId){
		return this.getSqlMapClientTemplate().queryForList("GmPrivilege.selectPrivsByUserId", userId);
	}
}
