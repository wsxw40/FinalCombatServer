package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerGPointTotal;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.utils.CacheUtil;

public class PlayerDao extends BaseMappingDao {
	//==============================
	//Get methods
	//==============================	
	public Player getPlayerById(final Integer playerId) throws DataAccessException {
		return  queryMappingBeanById(Player.class, playerId);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Player getPlayerByName(String name) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		Integer playerId=(Integer)this.getSqlMapClientTemplate().queryForObject("Player.selectByName", param);
		if(playerId==null){
			return null;
		}
		return getPlayerById(playerId);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Player getPlayerByName(String userName,String name) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		param.put("userName", userName);
		Integer playerId=(Integer)this.getSqlMapClientTemplate().queryForObject("Player.selectByName", param);
		if(playerId==null){
			return null;
		}
		return getPlayerById(playerId);
	}
	public PlayerVO getPlayerByIdFormDb(final Integer playerId) {
		try{
			return  (PlayerVO)this.getSqlMapClientTemplate().queryForObject("Player.selectById", playerId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<PlayerVO> fuzzySelectByUserName(String userName) throws DataAccessException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("userName", userName);
		return (List<PlayerVO>)this.getSqlMapClientTemplate().queryForList("Player.fuzzySelectByUserName", param);
	}
	public void updatePlayerXunleiId(PlayerVO player){
		this.getSqlMapClientTemplate().update("Player.updateXunleiId",player);
	}
	@SuppressWarnings("unchecked")
	public List<Player> getAllPlayer() throws DataAccessException {
		return (List<Player>) this.getSqlMapClientTemplate().queryForList("Player.selectAll");

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer getPlayerIdByName(String name) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		Integer playerId=(Integer)this.getSqlMapClientTemplate().queryForObject("Player.selectByName", param);
		return playerId;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int duplicatePlayerName(String name) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.duplicatePlayerCount", param);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer getPlayerIdByUserId(String userName) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userName", userName);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.selectByUserId", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerVO> fuzzySelectByName(String userName) throws DataAccessException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("name", userName);
		return (List<PlayerVO>)this.getSqlMapClientTemplate().queryForList("Player.fuzzySelectByName", param);
	}
	@SuppressWarnings("unchecked")
	public List<PlayerVO> selectBlackWhiteList(String flag) throws DataAccessException {
		return (List<PlayerVO>)this.getSqlMapClientTemplate().queryForList("Player.selectBlackWhiteList", flag);
	}
	//----------------------------------------------------------------------------------------------------------
	//TODO  have bug 
	@SuppressWarnings("unchecked")
	public List<PlayerVO> getPlayerList() throws DataAccessException {	
		return this.getSqlMapClientTemplate().queryForList("Player.selectSimple");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int sizeOfPlayer(int userId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userId", userId);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.duplicatePlayerCount", param);
	}
	
	
	public Integer fuzzyCountPlayer(String name) throws DataAccessException {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("name", name);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.fuzzyCountPlayer", param);
	}	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Integer, Player> fuzzyGetPlayerMap(String name, Integer start, Integer stop) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		param.put("start", start);
		param.put("stop", stop);
		return this.getSqlMapClientTemplate().queryForMap("Player.fuzzySelectPlayer", param, "id");
	}		
	
	public PlayerGPointTotal getTotalGPoint() throws DataAccessException {
		return (PlayerGPointTotal)this.getSqlMapClientTemplate().queryForObject("Player.selectTotalGPoint");
	}
	
	
	/********************************** create  & delete **************************************************/
	
	public Player create(Player player) throws Exception {
		insertObjIntoDBAndCache(player);
		return player;
	}
	
	public void delete(Player p ) throws Exception {
		deleteObjFromDBAndCache(p,p.getId());
	}
	
	/********************************** update  ***************************************************/
	
	public void updatePlayerInCache(final Player p) throws Exception{
		updateMappingBeanInCache(p, p.getId());
	}
	
	
	public void updatePlayerStageClear(final Collection<Player> players) throws Exception{
		for (final Player playerWithNewData : players){
			mcc.delete(CacheUtil.oPlayer(playerWithNewData.getId()));
			mcc.delete(CacheUtil.sPlayer(playerWithNewData.getId()));
			updatePlayerInCache(playerWithNewData);
		}
	}
	
	public void updateBlackWhite(Player player) throws Exception{
		this.getSqlMapClientTemplate().update("Player.updateBlackWhite", player);
	}
}
