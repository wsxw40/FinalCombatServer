package com.pearl.o2o.schedule;

import java.util.Date;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;

import com.google.common.base.Stopwatch;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class CheckTeamLeaderLoginTask implements Runnable {
	public static Logger logger = ServiceLocator.teamCheckLog;
	
	private static final long ONE_DAY = 1000L*60*60*24;
	
	@Override
	public void run() {
		logger.info("Start teamCheck task " + CommonUtil.simpleDateFormat.format(new Date()));
		MemcachedClient mcc = ServiceLocator.updateService.getMcc();

		//1.根据KEY模式，找到所有CACHEWRAPPERMETA
		try{
			Long now = System.currentTimeMillis();
			
		    String tokenKey = CacheUtil.isRunTeamCheckTask();
			GetsResponse<Long> response=mcc.gets(tokenKey);
			if (response == null){//之前没有执行过任务，在缓存内设置初始值
                mcc.set(tokenKey, 0, now - ONE_DAY);
                response = mcc.gets(tokenKey);
            }

			Long lastRunTimeMillis = response.getValue();
			
			if(now/ONE_DAY == lastRunTimeMillis/ONE_DAY){//如果上次运行时间和现在时间的间隔小于要求同步的时间间隔则不进行同步
				logger.info("check time stamp, but not run");
				return ;
			}else{
				if(mcc.cas(tokenKey, 0, now, response.getCas())){//CAS 竞争,一旦抢到则将当前时间标记为最近运行的时间
					try{	
						Stopwatch start = new Stopwatch().start();
						logger.info("ChechTeamLeaderLoginTask;task get cas "+CommonUtil.simpleDateFormat.format(new Date()));
						ServiceLocator.updateService.checkTeamLeaderLogin();
						logger.info("ChechTeamLeaderLoginTask;syncache db task success end "+CommonUtil.simpleDateFormat.format(new Date())+" time "+start.stop());
					}catch (Exception e) {
						logger.error("ChechTeamLeaderLoginTask;run syncache happen error",e);
					}
				}
			}
		}catch (Exception e){
			logger.error("run syncache db task happened error",e);
		}
	}
}
