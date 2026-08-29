package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.OnlineAward;


public class OnlineAwardDao extends BaseMappingDao {
	public List<OnlineAward> getOnlineAwardList() throws Exception{
		Map<Integer,OnlineAward> result= queryMappingBeanMap(OnlineAward.class);
		return new ArrayList<OnlineAward>(result.values());
	}
	
	public OnlineAward createOnlineAward(OnlineAward award)throws Exception{
		try{
		int a = insertObjIntoDBAndCacheWithDefaultId(award);
		award.setId(a);
		}catch(Exception e){
			e.printStackTrace();
		}
		return award;
	}
	public void updateOnlineAward(OnlineAward award)throws Exception{
		updateMappingBeanInCacheWithDefaultId(award);
	}
	
	public void deleteOnlineAward(OnlineAward award)throws Exception{
		deleteObjFromDBAndCacheWithDefaultId(award);
	}
	public OnlineAward getOnlineAwardById(int id) throws Exception{
		List<OnlineAward> allOnlineAwards = getOnlineAwardList();
		for(OnlineAward oa : allOnlineAwards){
			if(oa.getId()==id)
			return oa;
		}
		return null;
	}
	public List<OnlineAward> getOnlineAwardByLevelAndType(int level,int type) throws Exception{
		List<OnlineAward> retOnlineAwards = new ArrayList<OnlineAward>();
		List<OnlineAward> allOnlineAwards = getOnlineAwardList();
		for(OnlineAward oa : allOnlineAwards){
			if(oa.getType()==type&&oa.getLevel()==level)
			retOnlineAwards.add(oa);
		}
		return retOnlineAwards;
	}
	
	public List<OnlineAward> getOnlineAwardByType(int type) throws Exception{
		List<OnlineAward> retOnlineAwards = new ArrayList<OnlineAward>();
		List<OnlineAward> allOnlineAwards = getOnlineAwardList();
		for(OnlineAward oa : allOnlineAwards){
			if(oa.getType()==type)
			retOnlineAwards.add(oa);
		}
		return retOnlineAwards;
	}
	
}
