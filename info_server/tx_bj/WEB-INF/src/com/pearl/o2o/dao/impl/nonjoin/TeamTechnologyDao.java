package com.pearl.o2o.dao.impl.nonjoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.TeamTechnology;

import flex.messaging.io.ArrayList;

public class TeamTechnologyDao extends BaseMappingDao {
	private static boolean mapInit = false;

	private static Map<Integer, TeamTechnology> classifyTechnologyMap = null;

	@SuppressWarnings("unchecked")
	public List<TeamTechnology> getAllTeamTechnology() {
		return new ArrayList(getSqlMapClientTemplate().queryForMap(
				"TeamTechnology.selectAll", null, "id").values());
	}

	public TeamTechnology selectByID(int teamItemId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", teamItemId);
		return (TeamTechnology) getSqlMapClientTemplate().queryForObject(
				"TeamTechnology.selectById", map);
	}

	public TeamTechnology selectByCurNode(int curNode) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("curNode", curNode);
		return (TeamTechnology) getSqlMapClientTemplate().queryForObject(
				"TeamTechnology.selectByCurNode", map);
	}

	public Map<Integer, TeamTechnology> getClassifyTeamTechnologyMap()
			throws Exception {
		if (null == classifyTechnologyMap || classifyTechnologyMap.isEmpty()) {
			initClassifyTeamTechnologyMap();
		}
		return classifyTechnologyMap;
	}

	private synchronized void initClassifyTeamTechnologyMap() {
		if (!mapInit) {
			classifyTechnologyMap = new HashMap<Integer, TeamTechnology>();
			List<TeamTechnology> list = getAllTeamTechnology();
			if (list != null) {
				for (TeamTechnology teamTechnology : list) {
					classifyTechnologyMap.put(new Integer(teamTechnology
							.getCurNode()), teamTechnology);
				}
			}
			mapInit = true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TeamTechnology> getTeamTechnologyByType(int type){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("treeType",type);
		return new ArrayList(getSqlMapClientTemplate().queryForMap("TeamTechnology.selectByTreeType", map, "id").values());
	}
}
