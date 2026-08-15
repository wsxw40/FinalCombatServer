package com.pearl.o2o.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class InitTeamTopTask implements Runnable {
	public static Logger logger = ServiceLocator.crtPsnTopLog;
	public static boolean isManual=false;
	@Override
	public void run() {
		try {
			String tokenKey = Constants.INIT_TEAM_TOP_MCC_KEY;
		    boolean isGet = ServiceLocator.ml.tryLockWithNoDelay(tokenKey);
			if(isGet){
				try{
					logger.info("InitTeamTopTask/Excuting:\t" + CommonUtil.simpleDateFormat.format(new Date()));
					NoSql nosql  = ServiceLocator.nosqlService.getNosql();
					for(Constants.TEAM_TOP_TYPE type : Constants.TEAM_TOP_TYPE.values()){
						if(type.getValue()==Constants.TEAM_TOP_TYPE.RESOURCE.getValue()||type.getValue()==Constants.TEAM_TOP_TYPE.NOW_RES.getValue()){//资源争夺战排行榜不在这里做
							continue;
						}
						String key = Constants.TEAMTOP_KEY_PREFIX+ type.getValue();
						int count = (int) (type==Constants.TEAM_TOP_TYPE.NEW?nosql.llen(key):nosql.zCard(key));
						if(count==0){
							List<Team> retTeams = ServiceLocator.getService.getSortedTeamsByProvinceCityType(null, null, null, type.getValue(), 0, Constants.TEAM_TOP_NUM);
							if(type==Constants.TEAM_TOP_TYPE.NEW){
								for(Team team : retTeams){
									ServiceLocator.nosqlService.getNosql().appendToQueue(key, String.valueOf(team.getId()), Constants.TEAM_TOP_NUM);
								}
							}else{
								for(Team team : retTeams){
									ServiceLocator.updateService.updateTeamTop(team, type.getValue());
								}
							}
							logger.info("InitTeamTopTask/Type:\t" + type.getValue()+"\t" +retTeams.size());
						}
					}
				}
				finally {
					ServiceLocator.ml.unlock(tokenKey);// 无论如何要在这里解锁
				}
				logger.info("InitTeamTopTask/Excuted:\t" + CommonUtil.simpleDateFormat.format(new Date()));
		}
				
			}catch (Exception e) {
				logger.error("InitTeamTopTask/Error:\t", e);
			}

	}

}
