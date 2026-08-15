package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pearl.o2o.pojo.SysTeamBuff;

public class SysTeamBuffDao extends BaseMappingDao{
	
	Multimap<Integer, SysTeamBuff> SysTeamBuffByRank = null;
	
	public Collection<SysTeamBuff> getSysTeamBuffsByRank(int rank)throws Exception {
		return ((Multimap<Integer, SysTeamBuff>)daoLocalcache.get(SysTeamBuffDao.class, new Callable<Multimap<Integer, SysTeamBuff>>() {
			@Override
			public Multimap<Integer, SysTeamBuff> call() throws Exception {
				Multimap<Integer, SysTeamBuff> multimap = SysTeamBuffByRank = HashMultimap.create();
				for (Map.Entry<Integer, SysTeamBuff> entry : getSysTeamBuffs().entrySet()) {
					multimap.put(entry.getValue().getRank(), entry.getValue());
				}
				return multimap;
			}
		})).get(rank);
	}
	
	public Map<Integer, SysTeamBuff> getSysTeamBuffs() throws Exception {
		return (Map<Integer, SysTeamBuff>) tableLocalcache.get(SysTeamBuff.class);
	}	
	
	public SysTeamBuff getSysTeamBuffById(int id) throws Exception {
		return getSysTeamBuffs().get(id);
	}
	
	public int createSysTeamBuff(SysTeamBuff sysTeamBuff) throws Exception{
		return (Integer)insertObjIntoDBAndCache(sysTeamBuff);
	}	

	public void deleteSysTeamBuff(SysTeamBuff sysTeamBuff) throws Exception {
		deleteObjFromDBAndCacheWithDefaultId(sysTeamBuff);
	}

	public void updateSysTeamBuff(SysTeamBuff sysTeamBuff) throws Exception{
		updateMappingBeanInCacheWithDefaultId(sysTeamBuff);
	}
}
