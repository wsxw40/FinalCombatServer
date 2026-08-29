package com.pearl.o2o.schedule;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;

import com.pearl.o2o.dao.impl.nonjoin.BaseMappingDao;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class SynCacheWithDBTask implements Runnable {
	private BaseMappingDao baseMappingDao = ServiceLocator.baseMappingDao;
	public static Logger logger = ServiceLocator.syncacheLog;
	public static boolean isManual=false;
	
	@Override
	public void run() {
		logger.info("Start syncache db task " + CommonUtil.simpleDateFormat.format(new Date()));
		MemcachedClient mcc = ServiceLocator.updateService.getMcc();

		//1.根据KEY模式，找到所有CACHEWRAPPERMETA
		try{
		    String tokenKey = CacheUtil.isRunSynCacheTask();
		    Long now = System.currentTimeMillis();
			GetsResponse<String> response=mcc.gets(tokenKey);
			if (response == null){//之前没有执行过任务，在缓存内设置初始值
                mcc.set(tokenKey, 0, now.toString());
                response = mcc.gets(tokenKey);
            }

			Long lastRunTimeMillis = Long.valueOf(response.getValue());

			if(!isManual&&( now - lastRunTimeMillis < Constants.CACHE_SYN_INTERVAL_SECONDS * 1000||Constants.SWITCH_SYNTODB!=1)){//如果上次运行时间和现在时间的间隔小于要求同步的时间间隔则不进行同步
				logger.info("check time stamp, but not run");
				return ;
			}else{
				if(mcc.cas(tokenKey, 0,now.toString(), response.getCas())){//CAS 竞争,一旦抢到则将当前时间标记为最近运行的时间
					try{	
						long time = System.currentTimeMillis();
						logger.info("SynCacheWithDBTask;task get cas "+CommonUtil.simpleDateFormat.format(new Date()));
						//get dirtyMappingBeanKeys from redis
						Map<String, Set<Integer>> dirtyMappingBeanKeys = baseMappingDao.getDirtyMappingBeanKeys();
						//flush to db
						baseMappingDao.synCacheToDB(dirtyMappingBeanKeys);
						time = System.currentTimeMillis() - time;
						logger.info("SynCacheWithDBTask;syncache db task success end "+CommonUtil.simpleDateFormat.format(new Date())+" time "+time+" ms");
					}catch (Exception e) {
						logger.error("SynCacheWithDBTask;run syncache happen error",e);
					}
				}
			}
		}catch (Exception e){
			logger.error("run syncache db task happened error",e);
		}
	}
}
