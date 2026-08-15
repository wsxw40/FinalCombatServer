package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;

public class TeamLevelDao extends BaseMappingDao {
	private static final int DEFAULTID = 1;
	private Map<Integer, TeamLevelInfo> getAllLevelsMap() {
		return queryMappingBeanMapByRelatedId(TeamLevelInfo.class, DEFAULTID);
	}
	public List<TeamLevelInfo> getAllLevelsList(){
		Map<Integer, TeamLevelInfo> map=getAllLevelsMap();
		List<TeamLevelInfo> levelList=new ArrayList<TeamLevelInfo>(map.values());
	
		return levelList;
	
	}
	
	public TeamLevelInfo getTeamLevelById(final Integer teamLevelId) throws DataAccessException {
		return  queryMappingBeanById(TeamLevelInfo.class, teamLevelId);
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Integer, TeamLevelInfo> getTeamLevelByTL(final Integer teamId,final Integer levelId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("teamId", teamId);
		param.put("refLevelId", levelId);
		return  this.getSqlMapClientTemplate().queryForMap("TeamLevelInfo.selectByResLevelId",param,"id");
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Integer, TeamLevelInfo> getTeamLevelByTeam(final Integer teamId) throws DataAccessException {
		HashMap param = new HashMap();
		param.put("teamId", teamId);
		return  this.getSqlMapClientTemplate().queryForMap("TeamLevelInfo.selectAllForTeam",param,"id");
	}
	
	/********************************** create  & delete **************************************************/
	
	public TeamLevelInfo create(TeamLevelInfo teamLevelInfo) throws Exception {
		insertObjInDB(teamLevelInfo);
		return teamLevelInfo;
	}
	
	public void delete(TeamLevelInfo tl ) throws Exception {
		deleteObjFromDBAndCache(tl,tl.getId());
	}
	
	/********************************** update  ***************************************************/
	
	public void updateTeamLevelInfoInCache(final TeamLevelInfo tl) throws Exception{
		updateMappingBeanInCache(tl, tl.getId());
		String tlKeyString= CacheUtil.oTeamLevelByTL(tl.getTeamId(),tl.getRefLevelId());
		mcc.set(tlKeyString, Constants.CACHE_TIMEOUT_DAY, tl,Constants.CACHE_TIMEOUT);
	}
	
	public void updateTeamLevelInfoConfig(final TeamLevelInfo tl)throws Exception{
		this.getSqlMapClientTemplate().update("TeamLevel.updateConfig", tl);
	}
}
