package com.pearl.o2o.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.Player;



public class PlayerDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================
	@SuppressWarnings("unchecked")
	public Player getPlayerById(Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("playerId", playerId);
		return (Player)this.getSqlMapClientTemplate().queryForObject("Player.select", param);
	}
	@SuppressWarnings("unchecked")
	public Player getPlayerById(Integer userId,Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("playerId", playerId);
		param.put("userId", userId);
		return (Player)this.getSqlMapClientTemplate().queryForObject("Player.select", param);
	}
	
	
	public List<Player> getPlayersByIds(List<Integer> ids){
		HashMap param = new HashMap();
		param.put("playerIds", ids);
		return (List<Player>) this.getSqlMapClientTemplate().queryForList("Player.select", param);
	}
	
	
	@SuppressWarnings("unchecked")
	public Player getPlayerRankById(Integer playerId) throws DataAccessException{
		HashMap param = new HashMap();
		param.put("playerId", playerId);
		return (Player)this.getSqlMapClientTemplate().queryForObject("Player.selectPlayerById", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayer(Integer userId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userId", userId);
		return this.getSqlMapClientTemplate().queryForList("Player.select", param);
	}	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayerList() throws DataAccessException {	
		return this.getSqlMapClientTemplate().queryForList("Player.select");
	}
	
	@SuppressWarnings("unchecked")
	public List<Player> getPlayer(String name) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		return this.getSqlMapClientTemplate().queryForList("Player.select", param);
	}
	@SuppressWarnings("unchecked")
	public Player getPlayerByName(String name) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		return (Player)this.getSqlMapClientTemplate().queryForObject("Player.select", param);
	}
	@SuppressWarnings("unchecked")
	public Player getPlayer(int userId,int playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("userId", userId);
		param.put("playerId", playerId);
		return (Player)this.getSqlMapClientTemplate().queryForObject("Player.select", param);
	}
		
	public Integer fuzzyCountPlayer(String name) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Player.fuzzyCountPlayer", name);
	}		
	@SuppressWarnings("unchecked")
	public List<Player> fuzzyGetPlayer(String name, Integer start, Integer step) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("name", name);
		param.put("start", start);
		param.put("step", step);
		return this.getSqlMapClientTemplate().queryForList("Player.fuzzySelectPlayer", param);
	}	
	
	
	public Player create(Player player) throws DataAccessException {
		player.setId((Integer) this.getSqlMapClientTemplate().insert("Player.insert",player));
		return player;
	}
	
	
	public void delete(Integer id) throws DataAccessException {
		this.getSqlMapClientTemplate().update("Player.delete",id);
	}
	
	@SuppressWarnings("unchecked")
	public void updateGP(Integer gPoint,Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("gPoint", gPoint);
		param.put("playerId", playerId);
		this.getSqlMapClientTemplate().update("Player.updateGP",param);
	}
	
	@SuppressWarnings("unchecked")
	public void update(String column,Integer value,Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("column",column);
		param.put("value", value);
		param.put("playerId", playerId);
		this.getSqlMapClientTemplate().update("Player.update",param);
	}
	//for mxml
	@SuppressWarnings("unchecked")
	public void updatePlayer(Player player) throws DataAccessException {
	
		this.getSqlMapClientTemplate().update("Player.updatePlayer",player);
	}
	@SuppressWarnings("unchecked")
	public void updateWhileOnline(String ip,Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("ip", ip);
		param.put("playerId", playerId);
		this.getSqlMapClientTemplate().update("Player.updateWhileOnline",param);
	}
	@SuppressWarnings("unchecked")
	public void update(String column,String value,Integer playerId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("column",column);
		param.put("value", value);
		param.put("playerId", playerId);
		this.getSqlMapClientTemplate().update("Player.update",param);
	}
	
	@SuppressWarnings("unchecked")
	public void updateFlowerItem(Integer userId, Integer playerId, Player player,int unit, boolean isToday)throws DataAccessException,ParseException{
		HashMap param = new HashMap();
		if(isToday){
			param.put("userId", userId);				
			param.put("playerId", playerId);
			param.put("total", player.getFlowerTotal()+unit);				
			param.put("today", player.getFlowerToday()+unit);
			
		}else{
			param.put("userId", userId);				
			param.put("playerId", playerId);
			param.put("total", player.getFlowerTotal()+unit);				
			param.put("today", unit);
		}
		this.getSqlMapClientTemplate().update("Player.updateFlower",param);
	}
	
	public void updatePlayerStageClear(Player player){
		this.getSqlMapClientTemplate().update("Player.updatePlayerStageClear", player);
	}
	
	public void updatePlayerStageClear(final Collection<Player> players){
		 SqlMapClientCallback callback = new SqlMapClientCallback() {
		        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		            executor.startBatch();
		            for (Player p : players) {
		                executor.update("Player.updatePlayerStageClear", p);
		            }
		            executor.executeBatch();
		            return null;
		        }
		    };
		this.getSqlMapClientTemplate().execute(callback);
	}
}
