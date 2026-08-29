package com.pearl.o2o.schedule;

import java.util.Date;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class CleanRealPersonTopTask implements Runnable{
	public static Logger logger = ServiceLocator.crtPsnTopLog;
	public static boolean isManual=false;
	@Override
	public void run() {
		try {
				MemcachedClient mcc = ServiceLocator.updateService.getMcc();
				String tokenKey = Constants.CLN_PLAYER_TOP_MCC_KEY;
				Long now = System.currentTimeMillis();
				GetsResponse<Long> response=mcc.gets(tokenKey);
				if (response == null){//之前没有执行过任务，在缓存内设置初始值
	                mcc.set(tokenKey, 0, now);
	                response = mcc.gets(tokenKey);
	            }
				Long lastRunTimeMillis = response.getValue();
				if(now - lastRunTimeMillis < Constants.CACHE_SYN_INTERVAL_SECONDS * 1000&&!isManual){//如果上次运行时间和现在时间是同一天则不执行
					logger.info("CleanPersonTopTask/CheckTimeStamp:\t" + CommonUtil.simpleDateFormat.format(new Date()));
					return ;
				}else{
					if(mcc.cas(tokenKey, 0,now, response.getCas())){//CAS 竞争,一旦抢到则将当前时间标记为最近运行的时间
							NoSql  nosql = ServiceLocator.nosqlService.getNosql();
							logger.info("RecreatePersonTopTask/Excuting:\t" + CommonUtil.simpleDateFormat.format(new Date()));
							for(String type : Constants.PERSONAL_TOP_TYPE){
								if("kFightNum".equals(type)){
									for(int i : Constants.CHARACTER_IDS){
										String rKey = NosqlKeyUtil.fightNumInRealTopByType(String.valueOf(i));
										int rCount = (int)nosql.zCard(rKey);
										if(rCount>Constants.MAX_CACHE_REAL_TOP_RANK_NUM){
											nosql.zRemRangeByRank(rKey, Constants.REAL_TOP_RANK_NUM, -1);
											logger.info("CleanPersonTopTask/"+type+i+":\t" +rCount + "\t"+ (rCount-Constants.REAL_TOP_RANK_NUM));
										}
									}
								}else{
									String rKey = NosqlKeyUtil.commonLevelNumInRealTopByType(type);
									int rCount = (int)nosql.zCard(rKey);
									if(rCount>Constants.MAX_CACHE_REAL_TOP_RANK_NUM){
										nosql.zRemRangeByRank(rKey, Constants.REAL_TOP_RANK_NUM, -1);
										logger.info("CleanPersonTopTask/"+type+":\t" +rCount + "\t"+ (rCount-Constants.REAL_TOP_RANK_NUM));
									}
								}
							}
							//清理连射排行多余数据
							String dartleTopRedisKey = Constants.DARTLE_TOP_REDIS_KEY;
							int dCount = (int)nosql.zCard(dartleTopRedisKey);
							if(dCount > Constants.MAX_CACHE_DARTLE_TOP_NUM){
								nosql.zRemRangeByRank(dartleTopRedisKey, Constants.DARTLE_TOP_NUM, -1);
								logger.info("CleanPersonTopTask/Dartle:\t" +dCount + "\t"+ (dCount-Constants.DARTLE_TOP_NUM));
							}
							//清理战队排行多余数据
							for(int type=1;type<=3;type++){
								String teamTopRedisKey = Constants.TEAMTOP_KEY_PREFIX + type;
								int tCount = (int)nosql.zCard(teamTopRedisKey);
								if(tCount>Constants.TEAM_TOP_NUM){
									nosql.zRemRangeByRank(teamTopRedisKey, 0,-(Constants.TEAM_TOP_NUM+1));
									logger.info("CleanPersonTopTask/Team："+type+":\t" +tCount + "\t"+ (tCount-Constants.TEAM_TOP_NUM));
								}
							}
							logger.info("CleanPersonTopTask/Excuted:\t" + CommonUtil.simpleDateFormat.format(new Date()));
						}
					} 
				}catch (Exception e) {
					logger.error("CleanPersonalTopInReids/Error:\t", e);
				}
		}
}
