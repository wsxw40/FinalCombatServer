package com.pearl.o2o.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class InitTeamFightResPreWeekTask implements Runnable {
	public static Logger logger = LoggerFactory
			.getLogger(InitTeamFightResPreWeekTask.class);

	@Override
	public void run() {
		//
		try {
			String tokenKey = Constants.INIT_TEAM_TOP_MCC_KEY;
			boolean isGet = ServiceLocator.ml.tryLockWithNoDelay(tokenKey);
			if (isGet) {
				try {
					//初始化参数
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					c.set(Calendar.HOUR_OF_DAY, 0);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.DAY_OF_WEEK, 2);
					String nowTime = sdf.format(c.getTime());
					c.set(Calendar.WEEK_OF_MONTH,
							c.get(Calendar.WEEK_OF_MONTH) - 1);
					String preWeek = sdf.format(c.getTime());
					
					
					//清空redis上周资源争夺战排行榜
					ServiceLocator.updateService.clearTopTeamZYZDZ(Constants.TEAM_TOP_TYPE.RESOURCE.getValue());					
					
					List<BattleFieldRobDaily>  bfCol= ServiceLocator.getService.getBattleFieldRobDailyDao().getBattleFieldRobDailyForTop(nowTime, preWeek);

					for(BattleFieldRobDaily battleFieldRobDaily : bfCol){
						//更新nosql中资源争夺战当前抢夺排行榜，
						ServiceLocator.updateService.updateTeamTopZYZDZ(battleFieldRobDaily.getRobsum(), battleFieldRobDaily.getTeamId(),  Constants.TEAM_TOP_TYPE.RESOURCE.getValue());
					}
					logger.info("The PREWEEK_RES_AMOUNT of team is update\t"
							+ CommonUtil.simpleDateFormat.format(System.currentTimeMillis()));
				} finally {
					ServiceLocator.ml.unlock(tokenKey);
				}
			}
		} catch (Exception e) {
			logger.error("InitTeamFightResPreWeekTask/Error:\t", e);
		}

	}

}
