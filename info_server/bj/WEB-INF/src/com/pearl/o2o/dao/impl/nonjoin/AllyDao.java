package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.Ally;


public class AllyDao extends BaseMappingDao {
	@SuppressWarnings("unchecked")
	public Map<Integer, Ally> getAllyMap(int teamId) throws DataAccessException{
		return queryMappingBeanMapByRelatedId(Ally.class, teamId);
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<Ally> getAllyList(int teamId) throws DataAccessException{
		return new ArrayList(getAllyMap(teamId).values());
	}	
	
	@SuppressWarnings("unchecked")
	public Ally getAllyById(int teamId,int allyId) throws DataAccessException{
		List<Ally> allys = getAllyList(teamId);
		for(Ally ally:allys){
			if(ally.getAllyId() == allyId ){
				return ally;
			}
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	public void create(Ally ally) throws Exception {
		insertObjIntoDBAndCache(ally);
	}
	

	@SuppressWarnings("unchecked")
	public void delete(Ally ally) throws Exception {
		this.deleteObjFromDBAndCache(ally, ally.getTeamId());
	}	
}
