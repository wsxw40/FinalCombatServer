package com.pearl.o2o.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;


public class PlayerTeamDao extends SqlMapClientDaoSupport {
	
	public void createPlayerTeam(PlayerTeam playerTeam) throws Exception{
		this.getSqlMapClientTemplate().insert("PlayerTeam.insert",playerTeam);
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerTeam> getUnapprovedMember(int teamId){
		return this.getSqlMapClientTemplate().queryForList("PlayerTeam.selectUnProvedMember", teamId);
	}
	
	public PlayerTeam getByPlayerId(int id){
		return (PlayerTeam) this.getSqlMapClientTemplate().queryForObject("PlayerTeam.selectById", id);
	}
	
	@SuppressWarnings("unchecked")
	public int updateTeamApply(int teamId, int playerId){
		Map map = new HashMap();
		map.put("teamId", teamId);
		map.put("id", playerId);
		
		return this.getSqlMapClientTemplate().update("PlayerTeam.updateApply", map);
	}
	
	public int  updateJob(int playerId, int newJob){
		Map map = new HashMap();
		map.put("newJob", newJob);
		map.put("id", playerId);
		
		return this.getSqlMapClientTemplate().update("PlayerTeam.updateJob", map);
	}
	
	@SuppressWarnings("unchecked")
	public int approve(Player player, int teamId){
		Map map = new HashMap();
		map.put("teamId", teamId);
		map.put("player", player);
		
		return this.getSqlMapClientTemplate().update("PlayerTeam.approveApply", map);
	}
	
	@SuppressWarnings("unchecked")
	public void remove(int teamId, int playerId){
		Map map = new HashMap();
		map.put("teamId", teamId);
		map.put("playerId", playerId);
		
		this.getSqlMapClientTemplate().delete("PlayerTeam.remove", map);
	}
	
	public void removeAllApplicationFromPlayer(int playerId){
		this.getSqlMapClientTemplate().delete("PlayerTeam.removeAllApplication", playerId);
	}
	
	public void batchRemove(final int teamId, final String[] playerIds){
		 SqlMapClientCallback callback = new SqlMapClientCallback() {
		        @SuppressWarnings("unchecked")
				public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		            executor.startBatch();
		            for (String playerId : playerIds) {
		            	Map map = new HashMap();
		        		map.put("teamId", teamId);
		        		map.put("playerId", Integer.valueOf(playerId));
		                executor.delete("PlayerTeam.remove", map);
		            }
		            executor.executeBatch();
		            return null;
		        }
		    };
		this.getSqlMapClientTemplate().execute(callback);
	}
	
	
	
}
