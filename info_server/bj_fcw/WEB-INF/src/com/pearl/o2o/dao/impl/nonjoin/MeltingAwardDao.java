package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.MeltingAward;
import com.pearl.o2o.utils.ServiceLocator;

public class MeltingAwardDao extends BaseMappingDao {

	
	
	@SuppressWarnings("unchecked")
	public Map<Integer, MeltingAward> getAllMeltingAward(){
		return (Map<Integer, MeltingAward>) tableLocalcache.getUnchecked(MeltingAward.class);
	}
	public MeltingAward getMeltingAward(int id) throws Exception{
		MeltingAward meltingAward = getAllMeltingAward().get(id);
		meltingAward.setItem(ServiceLocator.getService.getSysItemByItemId(meltingAward.getItemId()));
		return meltingAward;
	}

	public MeltingAward updatePlayerMelting(MeltingAward meltingAward) throws Exception {
		updateMappingBeanInCache(meltingAward, meltingAward.getId());
		return meltingAward;
	}

	public void refreshLocalCache(){
		tableLocalcache.refresh(MeltingAward.class);
	}
}
