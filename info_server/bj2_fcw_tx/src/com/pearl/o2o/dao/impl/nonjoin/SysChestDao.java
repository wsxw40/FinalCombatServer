package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Map;

import com.pearl.o2o.pojo.SysChest;

/**
 * @author wuxiaofei
 *
 */
public class SysChestDao extends BaseMappingDao {
	public SysChest insert(SysChest sysChest) throws Exception{
		int id = insertObjIntoDBAndCacheWithDefaultId(sysChest);
		sysChest.setId(id);
		return sysChest;
	}
	
	public void delete(SysChest sysChest) throws Exception {
		deleteObjFromDBAndCacheWithDefaultId(sysChest);
	}
	
	public void update(SysChest sysChest) throws Exception{
		updateMappingBeanInCacheWithDefaultId(sysChest);
	}
	
	public Map<Integer, SysChest> getSysChestMap(){
		return queryMappingBeanMap(SysChest.class);
	}
}
