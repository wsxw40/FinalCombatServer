package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.TeamProclamation;



public class TeamProclamationDao extends BaseMappingDao {
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TeamProclamation getTeamProclamationById(Integer teamId,Integer id) throws Exception{
		return getTeamProclamationByTeamId(teamId).get(id);
	}
	/**
	 * 
	 * @param teamId
	 * @return
	 */
	public Map<Integer,TeamProclamation>   getTeamProclamationByTeamId(Integer teamId){
		return queryMappingBeanMapByRelatedId(TeamProclamation.class,teamId);
	}
	/**
	 * 
	 * @param team
	 * @return
	 * @throws Exception
	 */
	public int createTeam(TeamProclamation team) throws Exception{
		return (Integer)insertObjIntoDBAndCache(team,team.getTeamId());
	}	
	/**
	 * 
	 * @param team
	 * @throws Exception
	 */
	public void deleteTeam(TeamProclamation team) throws Exception {
		deleteObjFromDBAndCache(team,team.getTeamId());
	}
	/**
	 * 
	 * @param team
	 * @throws Exception
	 */
	public void updateTeam(TeamProclamation team) throws Exception{
		updateMappingBeanInCache(team, team.getTeamId());
	}
	
}
