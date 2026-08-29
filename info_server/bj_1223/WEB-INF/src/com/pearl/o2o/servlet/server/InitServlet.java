	package com.pearl.o2o.servlet.server;


import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.o2o.schedule.CacZYZDZChallengeWarTask;
import com.pearl.o2o.schedule.CheckTeamLeaderLoginTask;
import com.pearl.o2o.schedule.CleanRealPersonTopTask;
import com.pearl.o2o.schedule.CompeteBuyOverTask;
import com.pearl.o2o.schedule.DailyInitTask;
import com.pearl.o2o.schedule.DailyNumCreateTask;
import com.pearl.o2o.schedule.InitTeamFightResPreWeekTask;
import com.pearl.o2o.schedule.InitTeamResTopTask;
import com.pearl.o2o.schedule.InitTeamTopTask;
import com.pearl.o2o.schedule.PlayerOnlineCountPushTask;
import com.pearl.o2o.schedule.RecreatePersonTopTask;
import com.pearl.o2o.schedule.SynCacheWithDBTask;
import com.pearl.o2o.schedule.TopPlayerActivity;
import com.pearl.o2o.socket.PlayerCountSocketServer;
import com.pearl.o2o.socket.SocketServer;
import com.pearl.o2o.utils.ActivityLogParser;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = -4241967861991330409L;
	static Logger log = LoggerFactory.getLogger(InitServlet.class.getName());

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
		
		try{
		    BeanFactory context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		    ConfigurationUtil.beanFactory = context;
		    ServiceLocator.getService.flushSysBioCharacterCache();
		    //delete cached while init 
		    
			int appPort = Integer.valueOf(ConfigurationUtil.SOCKET_PORT);
			int playerCountPort = Integer.valueOf(ConfigurationUtil.SOCKET_SNDA_PLAYERCOUNT_PORT);
//			log.info("start server with port" + appPort);
			
			SocketServer server = SocketServer.getInstance(appPort);
			server.start();
			
			PlayerCountSocketServer sndaPlayerCountServer = PlayerCountSocketServer.getInstance(playerCountPort);
			sndaPlayerCountServer.start();
			log.info("server start!!!!");
			ServiceLocator.getService.initStrengthAppend();//初始换合成参数
			
			//每周一2点刷新team的前一周的抢资源值
			if(Constants.TEAM_FIGHT_RES_PREWEEK==1){
				new InitTeamFightResPreWeekTask().run();
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new InitTeamFightResPreWeekTask(),CommonUtil.minutesToNextMonday() , Constants.TOP_PLAYER_ACTIVITY_MINUTES, TimeUnit.MINUTES);
			}
			
			//每天2点刷新team当前抢资源值
			if(Constants.TEAM_RES_TOP==1){
				new InitTeamResTopTask().run();
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new InitTeamResTopTask(),CommonUtil.millisecondToNextday2(),24*60*60*1000l, TimeUnit.MILLISECONDS);
			}
			
			//	初始化排行榜
			ServiceLocator.scheduledExecutorService.execute(new InitTeamTopTask());
			
			
			ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new DailyInitTask(), CommonUtil.millisecondToNextday(), 24*60*60*1000l, TimeUnit.MILLISECONDS);
			if(Constants.SWITCH_TOPACTIVITY==1){
				//周一的早上两点排名活动发奖品
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new TopPlayerActivity(), CommonUtil.minutesToNextMonday(), Constants.TOP_PLAYER_ACTIVITY_MINUTES, TimeUnit.MINUTES);
			}
			
			
			if(Constants.SWITCH_SEND_TEAM_RES==1){ //挑战赛结束后发送剩余资源
				new CacZYZDZChallengeWarTask().run();
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(
						new CacZYZDZChallengeWarTask(),  Constants.CACHE_TIMEOUT_DAY, 7*24*60*60*1000l, TimeUnit.MILLISECONDS);
			}
			
			//保证只有一台服务器执行该任务
			if(Constants.SWITCH_COMPETEBUYSENDTASK==1){//抢拍结束后,定时开始发奖励

				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new CompeteBuyOverTask(), CommonUtil.minutesToDate(ServiceLocator.getService.getCompeteBuyTime(2)), 7*24*60, TimeUnit.MINUTES);
				//TODO  just for test, run every day
		//		ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new CompeteBuyOverTask(), CommonUtil.minutesToDate(ServiceLocator.getService.getCompeteBuyTime(2)), 60, TimeUnit.MINUTES);
			}
			if(Constants.SWITCH_CRT_PSN_TOP==1){
				new CleanRealPersonTopTask().run();
				new RecreatePersonTopTask().run();
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new CleanRealPersonTopTask(), Constants.CACHE_SYN_INTERVAL_SECONDS, 60*5, TimeUnit.SECONDS);
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new RecreatePersonTopTask(), CommonUtil.millisecondToNextday(), 24*60*60*1000l, TimeUnit.MILLISECONDS);
			}
			if(ConfigurationUtil.SWITCH_ONLINE.getIsOn()){
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(
						new PlayerOnlineCountPushTask(), 60*1000l, 60*1000l, TimeUnit.MILLISECONDS);
			}
			if(Constants.SWITCH_DAILYNUM==1){
				new DailyNumCreateTask().run();
				//daily num create,the task only need be run once 
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new DailyNumCreateTask(), CommonUtil.millisecondToNextday(), 24*60*60*1000l, TimeUnit.MILLISECONDS);
			}
			if(Constants.SWITCH_SYNTODB==1){
				ServiceLocator.createService.initConfigInCache();//initial task status
				new SynCacheWithDBTask().run();
				new CheckTeamLeaderLoginTask().run();
				//for avoid all app servers run the schedule, the task only need be run once 
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new SynCacheWithDBTask(), Constants.CACHE_SYN_INTERVAL_SECONDS, 60*5, TimeUnit.SECONDS);
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new CheckTeamLeaderLoginTask(), Constants.CACHE_TIMEOUT_HALF_DAY, 60*5, TimeUnit.SECONDS);
				
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(new TimerTask()  {
					@Override
					public void run()  {
						try{
							log.info("Start get award_list");
							String path = ConfigurationUtil.LOTTERY_PATH;
							ActivityLogParser.readLogFile(path);
							ActivityLogParser.writeAwardList(path);
						}catch (Exception e) {
							ServiceLocator.lotteryLogger.warn("Error in InitServlet: " , e);
						}
					}
					
				},CommonUtil.millisecondToEndOfToday(),24*60*60l,TimeUnit.SECONDS);
				
			}else{
				ServiceLocator.getService.getAvailableActivitiesMap();
			}
			// 添加 c币汇总信息到analyse库
			if (Constants.ADD_GPOINT==1) {
				ServiceLocator.playerGPointPushTask.run();
				// 考虑每天零点统计前一天的数据
				ServiceLocator.scheduledExecutorService.scheduleWithFixedDelay(
						ServiceLocator.playerGPointPushTask,  Constants.CACHE_TIMEOUT_DAY, 24*60*60*1000l, TimeUnit.MILLISECONDS);
			}
			
		}
		catch(Exception e){
			log.error("Error in InitServlet: " , e);
		}	
	}
	@Override
	public void destroy() {
		super.destroy();
		log.info("server is going to shut down,commit the cached data.");
		new SynCacheWithDBTask().run();
		System.exit(0);
	}
}
