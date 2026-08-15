package com.pearl.o2o.schedule;

import java.util.Date;

import org.slf4j.Logger;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
/**
 * a scheduled task to create daily num on 00:00:00 every day
 * @author wangzhilong
 *
 */
public class DailyNumCreateTask implements Runnable {
	public static Logger logger = ServiceLocator.dailyNumLog;
	private static String dailyRandomKey = Constants.DAILY_RANDOM_NUM_MCC_KEY;
	@Override
	public void run() {
		try{
			String dateStr = CommonUtil.dateFormatDate.format(new Date());
			logger.info("DailyNumCreateTask/Starting:\t" + CommonUtil.simpleDateFormat.format(new Date())) ;
			int todayNum = ServiceLocator.nosqlService.getDailyRandomNum(dateStr);
			if(todayNum==-1){
			todayNum = ServiceLocator.createService.dailyRandomNumCreate(todayNum, dailyRandomKey);
//			logger.info("DailyNumCreateTask : daily num:"+todayNum + "create, task success end "+CommonUtil.simpleDateFormat.format(new Date()));
			logger.info("DailyNumCreateTask/CreateNum:\t"+todayNum + "\t"+CommonUtil.simpleDateFormat.format(new Date()));
			}else{
				logger.info("DailyNumCreateTask/Excuted:\t"+ CommonUtil.simpleDateFormat.format(new Date()));
			}
		}catch (Exception e) {
			logger.error("DailyNumCreateTask/Error:\t",e);
		}
	}
	
}
