package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;


public class PlayerTeamDao extends BaseMappingDao {
	
	public void createPlayerTeam(PlayerTeam playerTeam) throws Exception{
		insertObjIntoDBAndCache(playerTeam, playerTeam.getTeamId());
	}
	
	public void deletePlayerTeam(PlayerTeam playerTeam) throws Exception{
		deleteObjFromDBAndCache(playerTeam, playerTeam.getTeamId());
	}
	
	public void updatePlayerTeam(PlayerTeam playerTeam) throws Exception{
		updateMappingBeanInCache(playerTeam, playerTeam.getTeamId());
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerTeam> getUnapprovedMember(int teamId){
		List<PlayerTeam> result = new ArrayList<PlayerTeam>();
		List<PlayerTeam> players = getPlayerTeamBase(teamId);
		for(PlayerTeam pt:players){
			if(Constants.BOOLEAN_NO.equals(pt.getApproved())){
				result.add(pt);
			}
		}
		return result;
	}
	
	public List<PlayerTeam> getPlayerTeamBase(int teamId){
		ArrayList<PlayerTeam> list = new ArrayList<PlayerTeam>( getPlayerTeams(teamId).values());
		return list;
	}
	
	public List<PlayerTeam> getPlayerTeam(int teamId){
		List<PlayerTeam> result = new ArrayList<PlayerTeam>();
		List<PlayerTeam> players = getPlayerTeamBase(teamId);
		for(PlayerTeam pt:players){
			if(Constants.BOOLEAN_YES.equals(pt.getApproved())){
				result.add(pt);
			}
		}
		return result;
	}
	
	public Map<Integer, PlayerTeam> getPlayerTeams(int teamId){
		return queryMappingBeanMapByRelatedId(PlayerTeam.class, teamId);
	}
	
	public PlayerTeam getPlayerTeam(int teamId,int playerTeamId){
		return getPlayerTeams(teamId).get(playerTeamId);
	}
	
	public PlayerTeam getByPlayerId(int playerid){
		PlayerTeam temp =  (PlayerTeam) this.getSqlMapClientTemplate().queryForObject("PlayerTeam.selectById", playerid);
		if(temp != null)
		{
			Map<Integer,PlayerTeam> map =  getPlayerTeams(temp.getTeamId());
			return map.get(temp.getId());
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<PlayerTeam> selectCheckTeamList(){
		return this.getSqlMapClientTemplate().queryForList("PlayerTeam.selectCheckTeamList");
	}
	
	@SuppressWarnings("unchecked")
	public List<PlayerTeam> getTeamByPlayerIds(List<Integer> playerIds){
		List<PlayerTeam> resultList = new ArrayList<PlayerTeam>();
		for(Integer playerId : playerIds){
			PlayerTeam pt = getByPlayerId(playerId);
			if(null != pt){
				resultList.add(pt);
			}
		}
		return resultList;
	}	
	
	@SuppressWarnings("unchecked")
	public void updateTeamApply(int teamId, int playerId) throws Exception{
		PlayerTeam playerTeam= getByPlayerId(playerId);
		playerTeam.setTeamId(teamId);
		updateMappingBeanInCache(playerTeam, playerTeam.getTeamId());
	}
	
	public void  updateJob(PlayerTeam playerTeam, int newJob) throws Exception{
		if(null != playerTeam){
			playerTeam.setJob(newJob);
			updateObjInDB(playerTeam);
			updateMappingBeanInCache(playerTeam, playerTeam.getTeamId());
		} 
	}
	
	@SuppressWarnings("unchecked")
	public int approve(Player player, int teamId){
		
		PlayerTeam playerTeam= getByPlayerId(player.getId());
		if(playerTeam==null){
			return 0;
		}
		try{
			//vip有上次退出战队保留的贡献
			if(player.getIsVip()>=1){
				playerTeam.setContribution(player.getVipRemainsTeamExp());
				player.setVipRemainsTeamExp(0);
				updateMappingBeanInCache(player, player.getId());
			}
			//上次离队时的贡献没过期，则返还之
			if(player.getLastExitTeamId()==teamId){
				if((System.currentTimeMillis()/1000-player.getLastTeamExpExpireTime())<=Constants.TEAM_EXP_STANDARD_EXPIRE_TIME){
					playerTeam.setContribution(player.getLastTeamExp());
				}
			}
			ServiceLocator.playerLog.info("player approve team exp:"+player.getVipRemainsTeamExp()+":"+player.getLastTeamExp());
			playerTeam.setApproved("Y");
			playerTeam.setCreateTime(System.currentTimeMillis()/1000);
			updateMappingBeanInCache(playerTeam, playerTeam.getTeamId());
		} catch(Exception e){
			e.printStackTrace();
		}
		return 1;
		
	}
	
	@SuppressWarnings("unchecked")
	public void remove(int teamId, int playerId) throws Exception{
		PlayerTeam playerTeam= getByPlayerId(playerId);
		deleteObjFromDBAndCache(playerTeam,playerTeam.getTeamId());
	}
	
	public void removeAllApplicationFromPlayer(int playerId){
		this.getSqlMapClientTemplate().delete("PlayerTeam.removeAllApplication", playerId);
	}
	
	public void batchRemove(final int teamId, final String[] playerIds) throws Exception{
			for (String playerId : playerIds) {
				PlayerTeam playerTeam= getByPlayerId(Integer.valueOf(playerId));
				if(playerTeam.getApproved().equals(Constants.BOOLEAN_NO)&&playerTeam.getTeamId()==teamId){
					deleteObjFromDBAndCache(playerTeam,teamId);
				}
			}
	}
	
	
	
}
