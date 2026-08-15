package com.pearl.o2o.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.TeamConstants;

public class TeamService extends BaseService{


	public List<PlayerTeam> getPlayerTeamByTeamIdSimple(int teamId) throws Exception{
		List<PlayerTeam> players = playerTeamDao.getPlayerTeam(teamId);
		return players;
	}
	public PlayerTeam getPlayerTeamByPlayerId(int playerId) throws Exception{
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if(pt != null){
			ApplicationContext context =new ClassPathXmlApplicationContext("WEB-INF");
			GetService bean = (GetService) context.getBean("getService");
			bean.joinPlayerAndPlayerTeam(pt);
		}
		return pt;
	}
	
	public Map<Integer,TeamProclamation>getTeamProclamationsByTeamId(int teamId){
		return teamProclamationDao.getTeamProclamationByTeamId(teamId);
		
	}
	public int getSmallestTeamRecordId(Map<Integer,?> map){
	   Integer smallest=null;
		for(Iterator<Integer> it= map.keySet().iterator();it.hasNext();){
		   Integer id= it.next();
		   if(smallest==null){
			   smallest=id;
		   }
		   if(smallest>=id){
			   smallest=id;
		   }
	   }
		return smallest;
	}
	public LevelInfo getLevelInfoById(int id){
		LevelInfo levelInfo=sysLevelDao.getLevelInfoById(id);
		return levelInfo;
		
	}
	public Map<Integer,TeamRecord>   getTeamRecordByTeamId(Integer teamId){
		return teamRecordDao.getTeamRecordByTeamId(teamId);
	}
	/**
	 * 
	 * @param teamId
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public int getTeamSalary(int teamId,int playerId) throws Exception{
		PlayerTeam pt=getService.getPlayerTeamDao().getByPlayerId(playerId);
		Team team=getService.getTeam(teamId);
		int teamLevel= team.getLevel();
		int contribution= pt.getContribution();
		int level=0;
		int tempLevel=(int)(Math.log10(1-contribution*(3/Constants.TEAM_BUFF_A)*((1-Constants.TEAM_BUFF_B)/Constants.TEAM_BUFF_B))/Math.log10(Constants.TEAM_BUFF_B));
		if(tempLevel>teamLevel){
			level=teamLevel;
		}else{
			level=tempLevel;
		}
		return (int) (Math.pow(level, 2)+4*level)+1;

	}
	public int getTotalExp(int level) {
		int tmp = (int) (6958.5*(level+10)*Math.pow(1.61672, level));
		return tmp/1000*1000;
	}

	

}
