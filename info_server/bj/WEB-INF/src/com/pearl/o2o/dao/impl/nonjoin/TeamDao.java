package com.pearl.o2o.dao.impl.nonjoin;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamScoreIncrement;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TeamConstants;

public class TeamDao extends BaseMappingDao {
	public Team getTeamById(Integer id) throws Exception{
		Team result = queryMappingBeanById(Team.class, id);
		if(null == result){
			return null;
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public int createTeam(Team team) throws Exception{
		return (Integer)insertObjIntoDBAndCache(team);
	}	
	
	public void deleteTeam(Team team) throws Exception {
		deleteObjFromDBAndCache(team,team.getId());
	}
	
	public void updateTeam(Team team) throws Exception{
		updateMappingBeanInCache(team, team.getId());
	}
	
	
	@SuppressWarnings("unchecked")
	public Map<Integer, Team> getAllTeamMap() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		return this.getSqlMapClientTemplate().queryForMap("Team.selectComplex", map, "id");
	}
	@SuppressWarnings("unchecked")
    public List<Team> getTeamsByApply(int apply) throws Exception{
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("apply", apply);
        return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.getTeamsByApply",map);
    }
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeamByName(String name) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.selectComplex",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Team> getTeamByNameProvinceCity(String name, String province, String city) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		if(null != name && !("".equals(name))){
			map.put("name", name);
		}
		if(null != province && !("".equals(province))){
			map.put("province", province);
		}
		if(null != city && !("".equals(city))){
			map.put("city", city);
		}
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.selectComplex",map);
	}
	@SuppressWarnings("unchecked")
	public List<Team> getTeamByProvinceCity(String province, String city,String name,String sql,Integer start ,Integer end) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		if(null != province && !("".equals(province))){
			map.put("province", province);
		}
		if(null != city && !("".equals(city))){
			map.put("city", city);
		}
		if(null != name && !("".equals(name))){
			map.put("name", name);
		}
		if(null!=start){
			map.put("start", start);
		}
		if(null!=end){
			map.put("end", end);
		}
		if(null==sql||"".equals(sql)){
			sql = "Team.selectComplex";
		}
		return (List<Team>)this.getSqlMapClientTemplate().queryForList(sql,map);
	}
	
	public int getTeamTotalNumByNameProvinceCity(String name, String province, String city) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		if(null != name && !("".equals(name))){
			map.put("name", name);
		}
		if(null != province && !("".equals(province))){
			map.put("province", province);
		}
		if(null != city && !("".equals(city))){
			map.put("city", city);
		}
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Team.getTotal",map);
	}
	@SuppressWarnings("unchecked")
	public List<Team> getTeamList() throws Exception{
		List<Team> resultList = (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.selectAll");
		for(Team team : resultList){
			if(team.getDeleted().equals("N")){
				int headerid = getTeamHeader(team.getId());
				team.setHeaderId(headerid);
			}
		}
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public int getTeamHeader(int teamid) throws Exception{
		Map<Integer, PlayerTeam> playerTeams = ServiceLocator.getService.getPlayerTeamDao().getPlayerTeams(teamid);
		for (PlayerTeam pt : playerTeams.values()) {
			if(pt.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()){
				return pt.getPlayerId();
			}
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public Team getTeamBySpecificName(String name) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("specificName", name);
		return (Team) this.getSqlMapClientTemplate().queryForObject("Team.selectComplex",map);
	}
	
	
	public int fuzzyCountTeam(String name) throws DataAccessException {
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Team.fuzzyCountTeam", name);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Team> getDefaultRecommendTeamList(){
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("pageStartIndex", (page-1)*pageSize);
//		map.put("pageSize", pageSize);
//		return this.getSqlMapClientTemplate().queryForList("Team.getRecommendTeamList", map);
		return this.getSqlMapClientTemplate().queryForList("Team.getRecommendTeamList");
	}
	
	
	public int countRecommendTeam(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Team.countRecommendTeam");
	}
	
	//batch update for team
	public void updateTeamGameInfo(final Collection<TeamScoreIncrement> teamIncrements) throws SQLException {
		if (teamIncrements == null || teamIncrements.isEmpty()) {
			return ;
		}
		 SqlMapClientCallback callback = new SqlMapClientCallback() {
		        public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
		            executor.startBatch();
		            for (TeamScoreIncrement tsi : teamIncrements) {
		                executor.update("Team.updateTeamScore", tsi);
		            }
		            executor.executeBatch();
		            return null;
		        }
		    };
		this.getSqlMapClientTemplate().execute(callback);
	}
	
	public int getMaxTeamLevel(){
		Integer result = (Integer) this.getSqlMapClientTemplate().queryForObject("Team.maxTeamLevel");
		return result==null?1:result;
	}
	
	public int getTeamRankingByGwin(int teamId) throws Exception{
		SqlMapClient sqlMapClient = this.getSqlMapClientTemplate().getSqlMapClient();
		sqlMapClient.startTransaction();
		sqlMapClient.update("Team.updateTeamRankingByGwin");
		Integer queryForObject = (Integer) sqlMapClient.queryForObject("Team.selectTeamRankingByGwin",teamId);
		sqlMapClient.commitTransaction();
		return queryForObject;
	}
	
	public List<Team> getTeamsByOriginStone(int maxUnusableResource,int minUnusableResource) {
		List<Team> result=null;
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("maxUnusableResource", maxUnusableResource);
		map.put("minUnusableResource", minUnusableResource);
		result= (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.getTeamsByOriginStone",map);
		return result;
	}	
	
	public List<Team> getTeamsByTeamSpaceActive(boolean isActive) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("teamSpaceActive", isActive?Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE:Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE);
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.getTeamsByTeamSpaceActive",map);
	}
	
	public int getTeamSpaceActiveTeamCount(boolean isActive,int myTeamId) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("teamSpaceActive", isActive?Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE:Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE);
		map.put("myTeamId", myTeamId);
		List<Integer> resList=this.getSqlMapClientTemplate().queryForList("Team.getTeamCountByTeamSpaceActive",map);
		int result=0;
		if(resList!=null){
			result=resList.get(0);
		}
		return result;
	}	
	
	public Team getTeamByTeamSpaceActiveAndIndex(boolean isActive,int index,int myTeamId) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("teamSpaceActive", isActive?Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE:Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE);
		map.put("index", index);
		map.put("myTeamId", myTeamId);
		List<Team> resList=this.getSqlMapClientTemplate().queryForList("Team.getTeamByTeamSpaceActiveAndIndex",map);
		Team result=null;
		if(resList!=null&&resList.size()!=0){
			result=resList.get(0);
		}
		return result;
	}		
	
	public List<Team> getTeamIdsByTeamSpaceActive(boolean isActive) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("teamSpaceActive", isActive?Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE:Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE);
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.getTeamIdsByTeamSpaceActive",map);
	}		
	
	public List<Team> getTeamSpaceActiveTeamIdsOrderByRecoreRankingCurr(boolean isActive) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("teamSpaceActive", isActive?Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE:Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE);
		return (List<Team>)this.getSqlMapClientTemplate().queryForList("Team.getTeamSpaceActiveTeamIdsOrderByRecoreRankingCurr",map);
	}		
	
}
