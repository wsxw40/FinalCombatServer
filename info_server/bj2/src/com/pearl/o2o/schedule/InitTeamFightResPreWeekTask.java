package com.pearl.o2o.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
					c.set(Calendar.DAY_OF_WEEK, 3);
					String nowTime = sdf.format(c.getTime());
					c.set(Calendar.WEEK_OF_MONTH,
							c.get(Calendar.WEEK_OF_MONTH) - 1);
					String preWeek = sdf.format(c.getTime());
					
					/**
					 * 重置活跃队伍为非活跃队伍
					 */
					List<Team> allRESActiveTeam=ServiceLocator.getService.getTeamSpaceActiveTeamIds(true);
					if(new Date().getDay()==2){//周2才更新
						for (Team team : allRESActiveTeam) {
							Team realTeam=ServiceLocator.getService.getTeamById(team.getId());
							if(realTeam!=null){
								realTeam.setTeamSpaceActive(Constants.TeamSpaceConstants.TEAM_SAPCE_DISACTIVE);
								ServiceLocator.updateService.updateTeamInfo(realTeam);
							}
						}				
					}
					
					//清空redis上周资源争夺战排行榜
					ServiceLocator.updateService.clearTopTeamZYZDZ(Constants.TEAM_TOP_TYPE.RESOURCE.getValue());					
					
					List<BattleFieldRobDaily>  bfCol= ServiceLocator.getService.getBattleFieldRobDailyDao().getBattleFieldRobDailyForTop(nowTime, preWeek,Constants.BattleFieldRobDailyType.MATCH);

					for(BattleFieldRobDaily battleFieldRobDaily : bfCol){
						//更新nosql中资源争夺战当前抢夺排行榜，
						ServiceLocator.updateService.updateTeamTopZYZDZ(battleFieldRobDaily.getRobsum(), battleFieldRobDaily.getAttTeamId(),  Constants.TEAM_TOP_TYPE.RESOURCE.getValue());
						Team realTeam=ServiceLocator.getService.getTeamById(battleFieldRobDaily.getAttTeamId());
						if(realTeam!=null){							
							realTeam.setTeamSpaceActive(Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE);
							ServiceLocator.updateService.updateTeamInfo(realTeam);
						}
					}
										
					/**
					 * 不足RES_BATTLE_MATCH_MIN个活跃队伍，则增加一些队伍
					 */
					int count=bfCol.size();
					if(count<Constants.TeamSpaceConstants.RES_BATTLE_MATCH_MIN){//可匹配的队伍不足最小匹配池
						List<Team> allRESDisActiveTeam=ServiceLocator.getService.getTeamSpaceActiveTeamIdsOrderByRecoreRankingCurr(false);
						for (Team disTeam : allRESDisActiveTeam) {
							Team realTeam=ServiceLocator.getService.getTeamById(disTeam.getId());
							if(realTeam!=null){
								realTeam.setTeamSpaceActive(Constants.TeamSpaceConstants.TEAM_SAPCE_ACTIVE);
								ServiceLocator.updateService.updateTeamInfo(realTeam);
								if(++count>=Constants.TeamSpaceConstants.RES_BATTLE_MATCH_MIN){
									break;
								}
							}
						}
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
