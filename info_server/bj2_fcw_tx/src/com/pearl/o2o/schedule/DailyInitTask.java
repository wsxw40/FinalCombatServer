package com.pearl.o2o.schedule;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class DailyInitTask implements Runnable {
	public static Logger logger = LoggerFactory.getLogger(DailyInitTask.class);
	@Override
	public void run() {
		try {
			boolean isGet = ServiceLocator.ml.tryLockWithNoDelay(Constants.DAILY_INIT_LOCK_MCC_KEY);
			if (isGet) {
				try {
					MemcachedClient mcc = ServiceLocator.updateService.getMcc();
					Boolean isRun = mcc.get(Constants.DAILY_INIT_MCC_KEY);
					if(isRun==null||!isRun){
						for (int sysItemId : Constants.DAILY_DISCOUNT_SYSITEM_IDS) {
							ServiceLocator.nosqlService.deleteByKey(Constants.PLAYER_DAILY_DISCOUNT_PREX+ sysItemId);
						}
						//每天重置vip能否获得升级经验的状态位
						logger.info("Start delete vip_up_exp_status:");
						ServiceLocator.nosqlService.getNosql().delete(Constants.VIP_UPGRADE_EXP_STATUS);
						mcc.set(Constants.DAILY_INIT_MCC_KEY, Constants.CACHE_TIMEOUT_HALF_DAY, true, Constants.CACHE_TIMEOUT);
					}
				} catch (Exception e) {
					logger.error("DailyInitTask error happens : ",e);
				}finally{
					ServiceLocator.ml.unlock(Constants.DAILY_INIT_LOCK_MCC_KEY);
				}
			}
		} catch (Exception e) {
			logger.error("DailyInitTask error happens when try lock on key : "  + Constants.DAILY_INIT_LOCK_MCC_KEY, e);
		}

	}
	
	public static void main(String[] args) {
		Boolean bl = true;
		if(bl){
			System.out.println(bl);
		}
	}

}
