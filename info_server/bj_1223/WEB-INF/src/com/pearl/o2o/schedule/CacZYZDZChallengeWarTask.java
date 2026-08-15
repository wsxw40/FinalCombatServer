package com.pearl.o2o.schedule;

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
	public static Logger logger = ServiceLocator.syncacheLog;
	public static boolean isManual=false;
	
	@Override
	public void run() {
		logger.info("Start syncache db task " + CommonUtil.simpleDateFormat.format(new Date()));
		MemcachedClient mcc = ServiceLocator.updateService.getMcc();
		
		try{
			String key=CacheUtil.isRunTeamResSendTask();
			
			Long now = System.currentTimeMillis();
			GetsResponse<String> response=mcc.gets(key);
			if (response == null){//之前没有执行过任务，在缓存内设置初始值
	             mcc.set(key, 0, now.toString());
	             response = mcc.gets(key);
	        }
	
			Long lastRunTimeMillis = Long.valueOf(response.getValue());
	
			if(!isManual&&( now - lastRunTimeMillis < 7*24*3600*1000L||Constants.SWITCH_SEND_TEAM_RES!=1)){//如果上次运行时间和现在时间的间隔小于要求同步的时间间隔则不进行同步
				logger.info("check time stamp, but not run");
				return ;
			}else{
				if(mcc.cas(key, 0, now.toString(), response.getCas())){
					List<Team> preZYZDZRank = ServiceLocator.getService.getTeamTopForPreRes();
					for(Team team: preZYZDZRank){
						if(team!=null){
							ChallengeHillStatus chs=ServiceLocator.getService.getChallengeHillStatus(team.getId());
							int remains=chs.getStones();
							if(remains>0){
								HashMap<String, Integer> resMap=team.getLatestTeamRes();
								team.setUsableResource(resMap.get(Team.RES)+remains);
								ServiceLocator.updateService.updateTeamInfo(team);
							}
							
						}
					}
				}
				
			}
		}catch (Exception e){
			logger.error("run send res to team task happened error",e);
		}
	}
}
