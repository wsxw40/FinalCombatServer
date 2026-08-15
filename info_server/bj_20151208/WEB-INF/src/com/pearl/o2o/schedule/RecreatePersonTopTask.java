package com.pearl.o2o.schedule;


import java.util.Date;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;

import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
/**
 *  a scheduled task to init person top on 00:00:00 every day
 *
 */
public class RecreatePersonTopTask implements Runnable {
	public static Logger logger = ServiceLocator.crtPsnTopLog;
	public static boolean isManual=false;
	@Override
	public void run() {
		try {
				MemcachedClient mcc = ServiceLocator.updateService.getMcc();
				String tokenKey = Constants.INIT_PLAYER_TOP_MCC_KEY;
			    String dateStr = CommonUtil.dateFormatDate.format(new Date());
				GetsResponse<String> response=mcc.gets(tokenKey);
				String lastRunDate = "";
				if (response == null){//之前没有执行过任务，在缓存内设置初始值
	                mcc.set(tokenKey, 0, dateStr);
	                response = mcc.gets(tokenKey);
	            }else{
	            	lastRunDate = response.getValue();
	            }
				
				if(dateStr.equals(lastRunDate)&&!isManual){//如果上次运行时间和现在时间是同一天则不执行
					logger.info("RecreatePersonTopTask/CheckTimeStamp:\t" + CommonUtil.simpleDateFormat.format(new Date()));
					return ;
				}else{
					if(mcc.cas(tokenKey, 0,dateStr, response.getCas())){//CAS 竞争,一旦抢到则将当前时间标记为最近运行的时间
							NoSql  nosql = ServiceLocator.nosqlService.getNosql();
							logger.info("RecreatePersonTopTask/Excuting:\t" + CommonUtil.simpleDateFormat.format(new Date()));
							for(String type : Constants.PERSONAL_TOP_TYPE){
								if("kFightNum".equals(type)){
//									for(int i : Constants.CHARACTER_IDS){
//										String key = NosqlKeyUtil.fightNumInTopByType(String.valueOf(i));
//										String rKey = NosqlKeyUtil.fightNumInRealTopByType(String.valueOf(i));
//										int count =  (int)nosql.zCard(key);
//										nosql.zRemRangeByRank(key, 0, count);
//										int rCount = (int)nosql.zCard(rKey);
//										nosql.zRemRangeByRank(rKey, 0, rCount);
//										if(rCount>Constants.CURRENT_RANK_NUM){
//											nosql.zRemRangeByRank(rKey, Constants.CURRENT_RANK_NUM, rCount);
//										}
//										ZParams params = new ZParams();
//										params.aggregate(Aggregate.MAX);
//										nosql.zunionstore(key, params, rKey);
//										count =  (int)nosql.zCard(key);
//										logger.info("RecreatePersonTopTask/"+type+i+":\t" + count);
//									}
								}else{
									String key = NosqlKeyUtil.commonLevelNumInTopByType(type);
									String rKey = NosqlKeyUtil.commonLevelNumInRealTopByType(type);
									int count = (int)nosql.zCard(key);
									nosql.zRemRangeByRank(key, 0, count);
									int rCount = (int)nosql.zCard(rKey);
									if(rCount>Constants.CURRENT_RANK_NUM){
										nosql.zRemRangeByRank(rKey, Constants.CURRENT_RANK_NUM, rCount);
									}
									ZParams params = new ZParams();
									params.aggregate(Aggregate.MAX);
									nosql.zunionstore(key, params,rKey);
									count = (int)nosql.zCard(key);
									logger.info("RecreatePersonTopTask/"+type+":\t" + count);
									
								}
							}
							logger.info("RecreatePersonTopTask/Excuted:\t" + CommonUtil.simpleDateFormat.format(new Date()));
						}
					} 
				}catch (Exception e) {
				logger.error("CleanPersonalTopInReids/Error:\t", e);
				}
		}
}
