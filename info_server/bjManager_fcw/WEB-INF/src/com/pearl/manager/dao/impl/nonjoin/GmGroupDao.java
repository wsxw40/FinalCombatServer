package com.pearl.manager.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.GmGroup;

public class GmGroupDao extends SqlMapClientDaoSupport {
	
	@SuppressWarnings("unchecked")
	public List<GmGroup> getGmGroups(int gmUserId){
		List<GmGroup> groups = getGmUserGroups(gmUserId);
		Map<String, List> param = new HashMap<String, List>();
		param.put("groups", groups);
		return this.getSqlMapClientTemplate().queryForList("GmGroup.select", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<GmGroup> getGmGroupsByCode(String codeStr){
		Map<String, String> param = new HashMap<String, String>();
		param.put("codeStr", codeStr);
		return this.getSqlMapClientTemplate().queryForList("GmGroup.select", param);
	}
	
	@SuppressWarnings("unchecked")
	public GmGroup getGmGroupById(int groupId){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("groupId", groupId);
		List<GmGroup> list = this.getSqlMapClientTemplate().queryForList("GmGroup.select", param);
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<GmGroup> getGmUserGroups(int gmUserId){
		return this.getSqlMapClientTemplate().queryForList("GmGroup.selectUserGroups", gmUserId);
	}
	@SuppressWarnings("unchecked")
	public List<GmGroup> selectUserCreateGroups(int gmUserId){
		return this.getSqlMapClientTemplate().queryForList("GmGroup.selectUserCreateGroups", gmUserId);
	}
	
	@SuppressWarnings("unchecked")
	public List<GmGroup> selectUserCreateGroupsAndSonGroups(int gmUserId){
		List<GmGroup> groups = selectUserCreateGroups(gmUserId);
		if(groups.size()>0){
			Map<String, List> param = new HashMap<String, List>();
			param.put("groups", groups);
			List<GmGroup> result = this.getSqlMapClientTemplate().queryForList("GmGroup.select", param);
			return result;
		}
		return groups;
	}
	
	@SuppressWarnings("unchecked")
	public int createGmGroup(GmGroup group) throws DataAccessException{
		return (Integer)this.getSqlMapClientTemplate().insert("GmGroup.insert", group);
	}
	
	public void updateGmGroup(GmGroup group) throws DataAccessException{
		this.getSqlMapClientTemplate().update("GmGroup.update", group);
	}
	
	public void deleteGmGroup(List<GmGroup> groups) throws DataAccessException{
		Map<String, List<GmGroup>> param = new HashMap<String, List<GmGroup>>();
		param.put("groups", groups);
		this.getSqlMapClientTemplate().delete("GmGroup.delete", param);
	}

}
