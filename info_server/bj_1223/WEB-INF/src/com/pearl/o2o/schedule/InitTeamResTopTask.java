package com.pearl.o2o.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class InitTeamResTopTask implements Runnable{
	public static Logger log = LoggerFactory.getLogger(InitTeamResTopTask.class);
	@Override
	public void run() {
		try{
			String tokenKey = Constants.INIT_TEAM_TOP_MCC_KEY;
			boolean isGet = ServiceLocator.ml.tryLockWithNoDelay(tokenKey);
			if (isGet) {
				try {
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

					c.set(Calendar.HOUR_OF_DAY, 2);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
				//	c.set(Calendar.DAY_OF_WEEK, 2);
					String nowTime = sdf.format(c.getTime());
					
					//清空redis当前资源争夺战排行榜
					ServiceLocator.updateService.clearTopTeamZYZDZ(Constants.TEAM_TOP_TYPE.NOW_RES.getValue());			
					
					c.set(Calendar.DAY_OF_WEEK, 2);
					String preTime = sdf.format(c.getTime());
	
					
					
					List<BattleFieldRobDaily>  bfCol= ServiceLocator.getService.getBattleFieldRobDailyDao().getBattleFieldRobDailyForTop(nowTime, preTime);

					for(BattleFieldRobDaily battleFieldRobDaily : bfCol){
						//更新nosql中资源争夺战当前抢夺排行榜，
						ServiceLocator.updateService.updateTeamTopZYZDZ(battleFieldRobDaily.getRobsum(), battleFieldRobDaily.getTeamId(),  Constants.TEAM_TOP_TYPE.NOW_RES.getValue());
					}
					log.info("team res top is update");
				} finally {
					ServiceLocator.ml.unlock(tokenKey);
				}
			}	
			
			
		}catch (Exception e) {
			log.error("InitTeamResTopTask/Error:\t", e);
		}
		
	}

}
