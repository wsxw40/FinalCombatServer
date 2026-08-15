package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.TeamRecord;

public class TeamRecordDao extends BaseMappingDao{
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TeamRecord getTeamRecordById(Integer teamId,Integer id) throws Exception{
		return getTeamRecordByTeamId(teamId).get(id);
	}
	/**
	 * 
	 * @param teamId
	 * @return
	 */
	public Map<Integer,TeamRecord>   getTeamRecordByTeamId(Integer teamId){
		return queryMappingBeanMapByRelatedId(TeamRecord.class,teamId);
	}
	
	/**
	 * 
	 * @param team
	 * @throws Exception
	 */
	public void deleteTeamRecord(TeamRecord team) throws Exception {
		deleteObjFromDBAndCache(team,team.getTeamId());
	}
	/**
	 * 
	 * @param team
	 * @return
	 * @throws Exception
	 */
	public int createTeam(TeamRecord team) throws Exception{
		return (Integer)insertObjIntoDBAndCache(team,team.getTeamId());
	}	
	/**
	 * 
	 * @param team
	 * @throws Exception
	 */
	public void deleteTeam(TeamRecord team) throws Exception {
		deleteObjFromDBAndCache(team,team.getId());
	}
	/**
	 * 
	 * @param team
	 * @throws Exception
	 */
	public void updateTeam(TeamRecord team) throws Exception{
		updateMappingBeanInCache(team, team.getId());
	}
	@SuppressWarnings("unchecked")
	public List<TeamRecord> getTeamRecordListByTeamId(int fid){
		@SuppressWarnings("rawtypes")
		Map map=new HashMap<String,String>();
		map.put("fid", fid);
		return this.getSqlMapClientTemplate().queryForList("TeamRecord.selectBothByTeamId",map);
	}

}
