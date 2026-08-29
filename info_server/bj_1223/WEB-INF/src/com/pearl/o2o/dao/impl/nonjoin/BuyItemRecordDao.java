package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class BuyItemRecordDao  extends BaseMappingDao {
	public Map<Integer, BuyItemRecord> getBuyItemRecordMap(int playerId) throws DataAccessException{
		return queryMappingBeanMapByRelatedId(BuyItemRecord.class, playerId);
	}	
	public List<BuyItemRecord> getPlayerItemList(int playerId) throws DataAccessException{
		return new ArrayList<BuyItemRecord>(getBuyItemRecordMap(playerId).values());
	}
	
	public BuyItemRecord getBuyItemRecordById(final Integer id){
		return queryMappingBeanById(BuyItemRecord.class,id);
	}
	
	public int createBuyItemRecord(BuyItemRecord buyItemRecord)throws Exception{
		return insertObjIntoDBAndCache(buyItemRecord,buyItemRecord.getPlayerId());
	}
	
	public void softDeleteBuyItemRecord(BuyItemRecord bir)throws Exception{
		deleteObjFromDBAndCache(bir,bir.getPlayerId());
	}
	public void updateBuyItemRecord(BuyItemRecord bir)throws Exception{
		updateMappingBeanInCache(bir, bir.getPlayerId());
	}
	

}
