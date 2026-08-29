package com.pearl.o2o.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.Constants;

public class PlayerItemDao extends SqlMapClientDaoSupport {
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getPlayerItemByPlayerId(int userId,int playerId,int type) throws DataAccessException{
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		map.put("itemType", type);
		map.put("isGift", "N");
		return this.getSqlMapClientTemplate().queryForList("PlayerItem.select", map);
	}
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getPlayerItemParts(int userId,int playerId,List weaponIds) throws DataAccessException{
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		map.put("partsList", weaponIds);
		map.put("isGift", "N");
		return this.getSqlMapClientTemplate().queryForList("PlayerItem.selectParts", map);
	}
	@SuppressWarnings("unchecked")
	public List<PlayerItem> getPlayerItemParts(int playerId,List weaponIds) throws DataAccessException{
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("partsList", weaponIds);
		map.put("isGift", "N");
		return this.getSqlMapClientTemplate().queryForList("PlayerItem.selectParts", map);
	}
	public PlayerItem getPlayerItemByPlayerItemId(int userId,int playerId,int playerItemId) throws DataAccessException{
		Map map=new HashMap();
		map.put("playerId", playerId);
		map.put("userId", userId);
		map.put("playerItemId", playerItemId);
		map.put("isGift", "N");
		return (PlayerItem)this.getSqlMapClientTemplate().queryForObject("PlayerItem.select", map);
	}
	public PlayerItem getPlayerItemByPlayerItemId(int playerItemId) throws DataAccessException{
		Map map=new HashMap();
		map.put("playerItemId", playerItemId);
		map.put("isGift", "N");
		return (PlayerItem)this.getSqlMapClientTemplate().queryForObject("PlayerItem.select", map);
	}
	public PlayerItem getPlayerItemForMessage(int playerItemId) throws DataAccessException{
		Map map=new HashMap();
		map.put("playerItemId", playerItemId);
		map.put("isGift", "Y");
		return (PlayerItem)this.getSqlMapClientTemplate().queryForObject("PlayerItem.select", map);
	}
	//get gift package by playerItemId
	public List<PlayerItem> getGiftPackageById(int userId,int playerId,int playerItemId) throws DataAccessException{
		
		Map map=new HashMap();
		map.put("playerId", playerId);
//		map.put("userId", userId);
		map.put("playerItemId", playerItemId);
		map.put("type", Constants.DEFAULT_PACKAGE_TYPE);
		return this.getSqlMapClientTemplate().queryForList("PlayerItem.getGiftPackageById", map);
	}
	
	public Integer countPlayerItemSuitable(Integer playerId, Integer id, Integer type, Integer subtype) {
		HashMap param = new HashMap();
		param.put("playerId", playerId);		
		param.put("id", id);
		param.put("type", type);
		param.put("subtype", subtype);		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("PlayerItem.countPlayerItemSuitable",param);
	}
	public Integer countPlayerItemForTeam(Integer userId,Integer playerId, int type,int subType,int iId) {		HashMap param = new HashMap();
		param.put("userId", userId);
		param.put("playerId", playerId);		
		param.put("iId", iId);
		param.put("type", type);
		param.put("subType", subType);		
		return (Integer)this.getSqlMapClientTemplate().queryForObject("PlayerItem.countPlayerItemForTeam",param);
	}
	public List<PlayerItem> getPlayerItemForTeam(Integer userId,Integer playerId, int type,int subType,int iId) {
		HashMap param = new HashMap();
		param.put("userId", userId);
		param.put("playerId", playerId);		
		param.put("iId", iId);
		param.put("itemType", type);
		param.put("subType", subType);		
		return (List<PlayerItem>)this.getSqlMapClientTemplate().queryForList("PlayerItem.select",param);
	}
	
	public List<PlayerItem> getPlayerItemSuitable(Integer playerId, Integer id, Integer start, Integer step, Integer type, Integer subtype) {
		HashMap param = new HashMap();
		param.put("playerId", playerId);				
		param.put("id", id);
		param.put("start", start);
		param.put("step", step);
		param.put("type", type);
		param.put("subtype", subtype);		
		return this.getSqlMapClientTemplate().queryForList("PlayerItem.selectPlayerItemSuitable",param);
	}		
	
	
	public int createPlayItem(Integer userId, Integer playerId, Integer itemId, String modifiedDesc, int unit,String isGift, int currency, int unitType)throws DataAccessException,ParseException{
		PlayerItem playerItem=new PlayerItem();
		playerItem.setUserId(userId);
		playerItem.setPlayerId(playerId);
		playerItem.setItemId(itemId);
		playerItem.setModifiedDesc(modifiedDesc);
		if(unitType==4){
			playerItem.setUnit1(unit);
		}else if(unitType==3){
			playerItem.setUnit1(Constants.NUM_ZERO);
			playerItem.setQuantity(unit);
		}else if(unitType==2){
			playerItem.setUnit1(Constants.NUM_ZERO);
			playerItem.setDurable(100);
		}else {
			playerItem.setUnit1(Constants.NUM_ZERO);
		}
		playerItem.setPlayerItemCurrency(currency);
		playerItem.setPlayerItemUnitType(unitType);
//		Date date=new Date();
//		Date expire=new Date(date.getTime()+duration*24*60*60*1000);
//		playerItem.setValidDate(date);
//		playerItem.setExpireDate(expire);
		playerItem.setIsBind(Constants.BOOLEAN_YES);
		playerItem.setIsGift(isGift);
		playerItem.setIsDefault(Constants.BOOLEAN_NO);
		return (Integer)this.getSqlMapClientTemplate().insert("PlayerItem.insert", playerItem);
	}
	public int createPlayItem(Integer userId, Integer playerId, Integer itemId, 
				String modifiedDesc,int unit,String isGift, int currency, int unitType,Date date,Date expire)throws DataAccessException,ParseException{
		PlayerItem playerItem=new PlayerItem();
		playerItem.setUserId(userId);
		playerItem.setPlayerId(playerId);
		playerItem.setItemId(itemId);
		playerItem.setModifiedDesc(modifiedDesc);
		playerItem.setPlayerItemCurrency(currency);
		playerItem.setPlayerItemUnitType(unitType);
		playerItem.setQuantity(unit);
		playerItem.setDurable(100);
		playerItem.setValidDate(date);
		playerItem.setExpireDate(expire);
		playerItem.setIsBind(Constants.BOOLEAN_YES);
		playerItem.setIsGift(isGift);
		playerItem.setIsDefault(Constants.BOOLEAN_NO);
		return (Integer)this.getSqlMapClientTemplate().insert("PlayerItem.insertWithDate", playerItem);
	}
	
	/*public int createDefaultKnife(int uid, int cid){
		PlayerItem playerItem=new PlayerItem();
		playerItem.setUserId(uid);
		playerItem.setPlayerId(cid);
		//TODO remove hard code
		playerItem.setItemId(5);
		playerItem.setModifiedDesc("");
		playerItem.setPlayerItemCurrency(0);
		playerItem.setPlayerItemUnitType(1);
		playerItem.setQuantity(1);
		playerItem.setDurable(100);
		Date now = new Date();
		playerItem.setValidDate(now);
		playerItem.setExpireDate(now);
		playerItem.setIsBind(Constants.BOOLEAN_YES);
		playerItem.setIsGift(Constants.BOOLEAN_NO);
		playerItem.setIsDefault(Constants.BOOLEAN_YES);
		
		return (Integer)this.getSqlMapClientTemplate().insert("PlayerItem.insertWithDate", playerItem);
	}*/
	
	
	public void batchDecreaseWeaponDurable(final Collection<Player> players, final int cost){
		 SqlMapClientCallback callback = new SqlMapClientCallback() {
		        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		            executor.startBatch();
		            for (Player p : players) {
		            	HashMap param = new HashMap();
		        		param.put("playerId", p.getId());
		        		param.put("cost", cost);
		                executor.update("PlayerItem.decreaseWeaponDurable", param);
		            }
		            executor.executeBatch();
		            return null;
		        }
		    };
		this.getSqlMapClientTemplate().execute(callback);
	}
	

	public void updateModifiedDesc(Integer userId, Integer playerId, Integer itemId, String modifiedDesc)throws DataAccessException,ParseException{
		HashMap param = new HashMap();
		param.put("userId", userId);				
		param.put("playerId", playerId);
		param.put("itemId", itemId);
		param.put("modifiedDesc", modifiedDesc);
	
		this.getSqlMapClientTemplate().update("PlayerItem.updateModifiedDesc",param);
	}
	public void updatePlayerForItem(Integer userId, Integer playerId, Integer itemId)throws DataAccessException,ParseException{
		HashMap param = new HashMap();
		param.put("userId", userId);				
		param.put("playerId", playerId);
		param.put("itemId", itemId);
		this.getSqlMapClientTemplate().update("PlayerItem.updatePlayerForItem",param);
	}
	public void deletePlayerItem(Integer userId, Integer playerId, Integer itemId)throws Exception{
		HashMap param = new HashMap();
		param.put("userId", userId);				
		param.put("playerId", playerId);
		param.put("id", itemId);
		this.getSqlMapClientTemplate().update("PlayerItem.delete",param);
	}
	public void softDeletePlayerItem(Integer userId, Integer playerId, Integer itemId)throws Exception{
		HashMap param = new HashMap();
		param.put("userId", userId);				
		param.put("playerId", playerId);
		param.put("id", itemId);
		this.getSqlMapClientTemplate().update("PlayerItem.softDelete",param);
	}
	public void updatePlayerItem(Integer userId, Integer playerId, Integer itemId,String column,Object value)throws DataAccessException,ParseException{
		HashMap param = new HashMap();
		param.put("column",column);
		param.put("value", value);
		param.put("userId", userId);				
		param.put("playerId", playerId);
		param.put("id", itemId);
		this.getSqlMapClientTemplate().update("PlayerItem.update",param);
	}
	
}
