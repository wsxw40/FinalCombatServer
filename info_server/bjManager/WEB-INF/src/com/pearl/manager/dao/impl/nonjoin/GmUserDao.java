package com.pearl.manager.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.manager.pojo.GmUser;



public class GmUserDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================		
	@SuppressWarnings("unchecked")
	public List<GmUser> getGmUsers() {
		return this.getSqlMapClientTemplate().queryForList("GmUser.select");
	}	
	
	@SuppressWarnings("unchecked")
	public List<GmUser> getAllGmUsers(){
		return this.getSqlMapClientTemplate().queryForList("GmUser.selectAllGmUsers");
	}
	
//	@SuppressWarnings("unchecked")
//	public GmUser getGmUserByUserId(int userId){
//		Map<String, Integer> param = new HashMap<String, Integer>();
//		param.put("userId", userId);
//		List<GmUser> list = this.getSqlMapClientTemplate().queryForList("GmUser.selectAllGmUsers", param);
//		return list.get(0);
//	}
	
	@SuppressWarnings("unchecked")
	public List<GmUser> getGmUsersByGroupId(int groupId){
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("groupId", groupId);
		return this.getSqlMapClientTemplate().queryForList("GmUser.selectGmUserByGroupId", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<GmUser> getLoginGmUser(String gmUserName, String passWord){
		Map<String, String> param = new HashMap<String, String>();
		param.put("gmUserName", gmUserName);
		param.put("passWord", passWord);
		return this.getSqlMapClientTemplate().queryForList("GmUser.selectLoginUser", param);
	}
	
	public GmUser create(GmUser gmUser) {
		gmUser.setId((Integer) this.getSqlMapClientTemplate().insert("GmUser.insert", gmUser));
		return gmUser;
	}		
	
	public void update(GmUser gmUser) {
		this.getSqlMapClientTemplate().update("GmUser.update", gmUser);
	}
	
	public void delete(GmUser gmUser) {
		this.getSqlMapClientTemplate().update("GmUser.delete", gmUser);
	}	
	
	
}
