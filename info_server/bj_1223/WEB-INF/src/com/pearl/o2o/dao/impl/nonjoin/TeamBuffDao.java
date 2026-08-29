package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.TeamBuff;



public class TeamBuffDao extends BaseMappingDao {

	public TeamBuff getTeamBuffById(int teamId,int id) throws Exception{
		return getTeamBuffsByTeamId(teamId).get(id);
	}
	
	public Map<Integer,TeamBuff>   getTeamBuffsByTeamId(int teamId){
		return queryMappingBeanMapByRelatedId(TeamBuff.class,teamId);
	}

	public int createTeamBuff(TeamBuff teamBuff) throws Exception{
		return (Integer)insertObjIntoDBAndCache(teamBuff,teamBuff.getTeamId());
	}	

	public void deleteTeamBuff(TeamBuff teamBuff) throws Exception {
		deleteObjFromDBAndCache(teamBuff,teamBuff.getTeamId());
	}

	public void updateTeamBuff(TeamBuff teamBuff) throws Exception{
		updateMappingBeanInCache(teamBuff, teamBuff.getTeamId());
	}
}
