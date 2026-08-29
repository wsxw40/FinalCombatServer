package com.pearl.o2o.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.Friend;



public class FriendDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================		
	@SuppressWarnings("unchecked")
	public List<Friend> getFriend(Integer userId, Integer playerId, Integer friendId, String isBlackList) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userId", userId);		
		param.put("playerId", playerId);
		param.put("friendId", friendId);
		param.put("isBlackList", isBlackList);
		return this.getSqlMapClientTemplate().queryForList("Friend.select", param);
	}	
	@SuppressWarnings("unchecked")
	public List<Friend> getFriendForMsg(Integer userId, Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userId", userId);		
		param.put("playerId", playerId);
		return this.getSqlMapClientTemplate().queryForList("Friend.selectFriend", param);
	}
	@SuppressWarnings("unchecked")
	public void create(Friend friend) throws DataAccessException {
		this.getSqlMapClientTemplate().insert("Friend.insert", friend);
	}		
	
	@SuppressWarnings("unchecked")
	public void update(Friend friend) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Friend.update", friend);
	}			
	
	@SuppressWarnings("unchecked")
	public void delete(Friend friend) throws DataAccessException {
		this.getSqlMapClientTemplate().delete("Friend.delete", friend);
	}	
}
