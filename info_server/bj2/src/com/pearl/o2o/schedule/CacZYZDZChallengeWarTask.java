package com.pearl.o2o.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;

import com.pearl.o2o.dao.impl.nonjoin.BaseMappingDao;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ChallengeHillStatus;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class CacZYZDZChallengeWarTask implements Runnable {
	public static Logger logger = ServiceLocator.zyzdzSendResLog;
	public boolean isManual=false;
	
	MemcachedClient mcc = ServiceLocator.updateService.getMcc();
	public CacZYZDZChallengeWarTask(){
	}
	public CacZYZDZChallengeWarTask(boolean isManual){
		this.isManual=isManual;
	}
	@Override
	public void run() {
		logger.info("Start syncache db task " + CommonUtil.simpleDateFormat.format(new Date()));
		try{
			if(isManual){
				sendRes();
			}else{
				Calendar calendar=Calendar.getInstance();
				Calendar sendTimeCalendar=ServiceLocator.getService.getZYZDZChallengeTime(2);
				
				String mccKey=CacheUtil.isRunTeamResSendTask();
				GetsResponse<Calendar> response=mcc.gets(mccKey);
				Calendar lastRunTime  = null;
				if (response == null){//之前没有执行过任务，在缓存内设置初始值
	                mcc.set(mccKey, 0, calendar);
	                response = mcc.gets(mccKey);
	            }else{
	            	lastRunTime  = response.getValue();
	            }
				if(lastRunTime!=null&&lastRunTime.get(Calendar.YEAR)==calendar.get(Calendar.YEAR) && lastRunTime.get(Calendar.WEEK_OF_YEAR)==calendar.get(Calendar.WEEK_OF_YEAR)){
					logger.info("zyzdz res send taks had ran this week!");
				}else if(calendar.before(ServiceLocator.getService.getZYZDZChallengeTime(1)) || calendar.before(ServiceLocator.getService.getZYZDZChallengeTime(1))){
					logger.info("zyzdz challenge mode is not over!");
				}
				if(calendar.get(Calendar.DAY_OF_WEEK)==sendTimeCalendar.get(Calendar.DAY_OF_WEEK)){
					if(mcc.cas(mccKey, 0,calendar, response.getCas())){
						sendRes();
					}
				}
			}

		}catch (Exception e){
			logger.error("run send res to team task happened error",e);
		}
	}
	
	private void sendRes()throws Exception{
		
		List<Team> preZYZDZRank = ServiceLocator.getService.getTeamTopForPreRes();
		for(Team team: preZYZDZRank){
			if(team!=null){
			
				ChallengeHillStatus chs=ServiceLocator.getService.getChallengeHillStatus(team.getId());
				if(chs!=null){
					int remains=chs.getStones();
					if(remains>0){
						HashMap<String, Integer> resMap=team.getLatestTeamRes();
						team.setUsableResource(resMap.get(Team.RES)+remains);
						ServiceLocator.updateService.updateTeamInfo(team);
						String key=Constants.TeamSpaceConstants.getChallengeHillKey(team.getId());
						mcc.delete(key);
						logger.info(CommonUtil.simpleDateFormat.format(new Date())+"|"+team.getId()+" with name "+team.getName()+" get remain res "+remains);
					}
				}
			}
		}
	}
}
