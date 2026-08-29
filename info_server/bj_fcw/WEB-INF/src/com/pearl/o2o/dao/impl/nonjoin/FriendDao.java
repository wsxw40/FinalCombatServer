package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.utils.Constants;



public class FriendDao  extends BaseMappingDao{
	//==============================
	//Get methods
	//==============================	
	public Map<Integer, Friend> getFriendMap(Integer playerId) throws DataAccessException {
		return queryMappingBeanMapByRelatedId(Friend.class, playerId);
	}
	public List<Friend> getFriendAll(Integer playerId,Integer type) throws DataAccessException {
		List<Friend> returnList = new ArrayList<Friend>();
		Map<Integer, Friend> frientMap = getFriendMap(playerId);
		//返回
		if(type == null){
			return new ArrayList<Friend>(frientMap.values());
		}
		for(Map.Entry<Integer, Friend> entry: frientMap.entrySet()) {  
			Friend fri=frientMap.get(entry.getKey());
			if(type.equals(fri.getType())){
				returnList.add(fri);
			}
		}
		Collections.sort(returnList, new Comparator<Friend>(){

			@Override
			public int compare(Friend o1, Friend o2) {
				return ((Integer)o1.getFriendId()).compareTo((Integer)o2.getFriendId());
			}  
		});
		return returnList;
	}
	
	
	public List<Friend> getFriend(Integer playerId,Integer type) throws DataAccessException {
		List<Friend> friendList = getFriendAll(playerId,type);
		List<Friend> result = new ArrayList<Friend>();
		for(Friend f:friendList){
			if(f.getAcceptted().equals(Constants.BOOLEAN_YES)){
				result.add(f);
			}
		}

		return result;
	}
	public Friend getFriendIndex(int playerId,int friendId,int type) throws DataAccessException {
		List<Friend> friendList = getFriendAll(playerId,type);
		Friend result =null;
		for(Friend f:friendList){
			if(f.getPlayerId()==playerId&&f.getFriendId()==friendId&&type==f.getType()){
				result=f;
				break;
			}
		}

		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Integer> getGroupOwners(Integer friendId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("friendId", friendId);
		List<Integer> playerIds=(List<Integer>)this.getSqlMapClientTemplate().queryForList("Friend.selectByFriendId", param);
		return playerIds;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Friend> getRequestList(Integer friendId,Integer type) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("friendId", friendId);
		param.put("type", type);
		List<Friend> result=(List<Friend>)this.getSqlMapClientTemplate().queryForList("Friend.selectRequestByFriendId", param);
		return result;
	}
	
	public int fuzzyCountGroup(String name) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Friend.fuzzyCountGroup", name);
	}
	
	/**
	 * @param playerId
	 * @param friendId
	 * @return relationship between playerId and friendId
	 * if no relationship return null
	 */
	public Friend getFriend(Integer playerId,Integer friendId,Integer type){
		Friend returnObj =null;
		List<Friend> friendList = getFriendAll(playerId,type);
		for(Friend fri:friendList){
			if(friendId.equals(fri.getFriendId())&&type.equals(fri.getType())){
				returnObj=fri;
				break;
			}
		}
		return returnObj;
	}
	public List<Friend> getFriendAllById(Integer playerId,Integer friendId){
		Integer type=null;
		List<Friend> returnList = new ArrayList<Friend>();
		List<Friend> friendList = getFriendAll(playerId,type);
		for(Friend fri:friendList){
			if(friendId.equals(fri.getFriendId())){
				returnList.add(fri);
			}
		}
		return returnList;
	}
	public List<Friend> getFriendById(Integer playerId,Integer friendId){
		Integer type=null;
		List<Friend> returnList = new ArrayList<Friend>();
		List<Friend> friendList = getFriend(playerId,type);
		for(Friend fri:friendList){
			if(friendId.equals(fri.getFriendId())){
				returnList.add(fri);
			}
		}
		return returnList;
	}
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IFriendDao#getFriendRankList(java.lang.Integer)
	 */
//	public List<Friend> getFriendRankList(Integer playerId) throws DataAccessException {
//		HashMap param = new HashMap();
//		param.put("playerId", playerId);
//		return this.getSqlMapClientTemplate().queryForList("Friend.selectFriendRankList", param);
//	}	
//	
//	/* (non-Javadoc)
//	 * @see com.pearl.o2o.dao.IFriendDao#getFriendForMsg(java.lang.Integer, java.lang.Integer)
//	 */
//	@SuppressWarnings("unchecked")
//	public List<Friend> getFriendForMsg(Integer userId, Integer playerId) throws DataAccessException {
//		HashMap param = new HashMap();
//		param.put("userId", userId);		
//		param.put("playerId", playerId);
//		return this.getSqlMapClientTemplate().queryForList("Friend.selectFriend", param);
//	}
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IFriendDao#create(com.pearl.o2o.pojo.Friend)
	 */
	public Integer create(Friend friend) throws Exception {
		return insertObjIntoDBAndCache(friend, friend.getPlayerId());
	}		
	
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IFriendDao#update(com.pearl.o2o.pojo.Friend)
	 */
	public void update(Friend friend) throws Exception {
		updateMappingBeanInCache(friend,friend.getPlayerId());
	}			
	
	/* (non-Javadoc)
	 * @see com.pearl.o2o.dao.IFriendDao#delete(com.pearl.o2o.pojo.Friend)
	 */
	public void delete(Friend friend) throws Exception {
		deleteObjFromDBAndCache(friend, friend.getPlayerId());
	}	
	public void deleteHard(Friend friend,Integer type)throws Exception{
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("playerId", friend.getPlayerId());
		map.put("type", type);
		this.getSqlMapClientTemplate().delete("Friend.deleteHard", map);
//		for(Friend fri:list){
//			delete(fri);
//		}
		delete(friend);
	}
}
