package com.pearl.o2o.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.o2o.utils.ServiceLocator;

public class CleanOverdue implements Runnable {
	public static Logger logger = LoggerFactory.getLogger(CleanOverdue.class);

	@Override
	public void run() {
		//
		try {
			cleanMySql();
		} catch (Exception e) {
			logger.error("CleanRedisTask/Error:\t", e);
		}

	}

	private void cleanRedis() {

	}

	private void cleanMySql() throws Exception {
		cleanBattleFieldRobDaily();
	}
	
	/**
	 * 清除三个月之前的数据
	 * @throws Exception 
	 */
	private void cleanBattleFieldRobDaily() throws Exception{
		long lNow=System.currentTimeMillis();
		long threeMonth=3l*30*24*60*60*1000;
		Date endDate=new Date(lNow-threeMonth);
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		endDate=sf.parse(sf.format(endDate));
		ServiceLocator.deleteService.cleanUnUsedBattleFieldRobDailyInMysql(endDate);
	}
}
