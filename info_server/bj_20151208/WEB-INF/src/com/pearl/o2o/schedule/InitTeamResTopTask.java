package com.pearl.o2o.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.Team;
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
					Date now =new Date();
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

					c.set(Calendar.HOUR_OF_DAY, 2);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
				//	c.set(Calendar.DAY_OF_WEEK, 2);
					Date oNowTime=c.getTime();
					String nowTime = sdf.format(oNowTime);
					
					//清空redis当前资源争夺战排行榜
					ServiceLocator.updateService.clearTopTeamZYZDZ(Constants.TEAM_TOP_TYPE.NOW_RES.getValue());			
					
					
					c.set(Calendar.DAY_OF_WEEK, 3);
					Date oPreTime=c.getTime();
					if(oPreTime.after(oNowTime)){
						oPreTime=new Date(oPreTime.getTime()-Constants.OneDay*7);
					}
					String preTime = sdf.format(oPreTime);

					
					List<BattleFieldRobDaily>  bfCol= ServiceLocator.getService.getBattleFieldRobDailyDao().getBattleFieldRobDailyForTop(nowTime, preTime,Constants.BattleFieldRobDailyType.MATCH);
					for(BattleFieldRobDaily battleFieldRobDaily : bfCol){
						//更新nosql中资源争夺战当前抢夺排行榜，
						ServiceLocator.updateService.updateTeamTopZYZDZ(battleFieldRobDaily.getRobsum(), battleFieldRobDaily.getAttTeamId(),  Constants.TEAM_TOP_TYPE.NOW_RES.getValue());
						Team team=ServiceLocator.getService.getTeamById( battleFieldRobDaily.getAttTeamId());
						if(team!=null){
							team.setPredayResAmount(battleFieldRobDaily.getRobsum());
							team.setCurWeekRobCount(battleFieldRobDaily.getRobcount());
							team.setCurWeekRobUpdateTime(now);
							ServiceLocator.updateService.updateTeamInfo(team);
						}
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
