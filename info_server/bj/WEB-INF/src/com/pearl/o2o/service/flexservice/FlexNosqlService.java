package com.pearl.o2o.service.flexservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.object.deathrecord.DeathRecord;
import com.pearl.o2o.service.NosqlService;


public class FlexNosqlService extends NosqlService {
	private static Logger logger = LoggerFactory.getLogger(FlexNosqlService.class);
	
	public List<DeathRecord> getDeathRecord(int gameType, int level) throws Exception{
		String keyPattern = "DR:" + gameType + ":" + level+":*";
		long start = System.currentTimeMillis();
		Set<String> keys = nosql.getKeysByPattern(keyPattern);
		
		List<DeathRecord> result = new ArrayList<DeathRecord>(keys.size());
		
//		for (String key : keys){
//			int value = Integer.valueOf(nosql.get(key));
//			String[] fields = key.split(":");
//			if (fields.length <8) {//TODO ignore abnormal data
//				continue;
//			}
//			int x = Integer.valueOf(fields[3]);
//			int y = Integer.valueOf(fields[4]);
//			int z = Integer.valueOf(fields[5]);
//			int team = Integer.valueOf(fields[6]);
//			int type = Integer.valueOf(fields[7]);
//			
//			DeathRecord dr = new DeathRecord();
//			dr.setX(x);dr.setY(y);dr.setZ(z);dr.setTeam(team);dr.setType(type);
//			dr.setValue(value);
//			result.add(dr);
//		}
		long end = System.currentTimeMillis();
		
		logger.error("getDeathRecordCost" + (end - start) + "  count " + keys.size());
		return result;
	}
}
