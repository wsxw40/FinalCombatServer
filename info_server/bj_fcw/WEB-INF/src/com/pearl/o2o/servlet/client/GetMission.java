package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerActivity;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.SysMission;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.CycleMissionIncome;
import com.pearl.o2o.utils.StringUtil;


public class GetMission extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static Logger log = LoggerFactory.getLogger(GetMission.class.getName());
	private static final String[] paramNames = {"pid","type","mission_type","mission_id","pageNo"};
	private static final int pageSize = 7;
	protected String innerService(String... args) {
		try{
			final int playerId = StringUtil.toInt(args[0]);
			final int type = StringUtil.toInt(args[1]);
			final int mission_type = StringUtil.toInt(args[2]);
			final int mission_id = StringUtil.toInt(args[3]);
			final int pageNo = StringUtil.toInt(args[4]);
	//		final int time = StringUtil.toInt(args[5]);
			List<PlayerMission> playerMissionList = getService.getPlayerMissionList(playerId);
			
			Player player=getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			switch (mission_type) {
			case 0:
				if(0==type){
					//获取每日任务列表
					List<PlayerMission> missionList = new ArrayList<PlayerMission>();
					for(PlayerMission pm : playerMissionList){
						if(mission_type == pm.getType()){
							//join SysMission
							SysMission sysMission = getService.getSysMissionById(pm.getSysMissionId());
							pm.setDescription(CommonUtil.messageFormat(sysMission.getDescription(), pm.getTarget()));
							pm.setSysMission(sysMission);
							pm.setCmIncome(CycleMissionIncome.getCycleMissionIncome(sysMission, pm,player.getIsVip()));
											
							missionList.add(pm);
						}
					}
					return updateService.fillCycleMissionListView(missionList,playerId);
				}else if(1==type){
					//领取每日任务奖品
					PlayerMission mission = null;
					for(PlayerMission pm : playerMissionList){
						if(mission_id == pm.getId()){
							mission = pm;
						}
					}
					if(mission==null){
						return Converter.error(ExceptionMessage.MISSION_NOT_FIND);
					}
					if(mission.getStatus() == 0){//任务未完成
						return Converter.error(ExceptionMessage.MISSION_NOT_FINISH);
					}
					if(mission.getAward() == 1){//此任务已经领取奖品
						return Converter.error(ExceptionMessage.MISSION_ALREADY_AWARD);
					}
					if(null != mission){
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
							nosqlService.addXunleiLog("16.1"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
									+ Constants.XUNLEI_LOG_DELIMITER + mission.getSysMission().getTitle()
									+ Constants.XUNLEI_LOG_DELIMITER + (mission.getStatus()>0?"Finished":"UnFinished")
									+ Constants.XUNLEI_LOG_DELIMITER + mission.getSysMission().getNormalItems()
									+ Constants.XUNLEI_LOG_DELIMITER + mission.getSysMission().getVipItems()
									+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
									);
						}
						return updateService.awardMission(mission);
					}
				}
			case 1:
				if(0==type){
					//获取每周任务列表
					List<PlayerMission> missionList = new ArrayList<PlayerMission>();
					for(PlayerMission pm : playerMissionList){
						if(mission_type == pm.getType()){
							//join SysMission
							SysMission sysMission = getService.getSysMissionById(pm.getSysMissionId());
							pm.setDescription(CommonUtil.messageFormat(sysMission.getDescription(), pm.getTarget()));
							pm.setSysMission(sysMission);
							pm.setCmIncome(CycleMissionIncome.getCycleMissionIncome(sysMission, pm,player.getIsVip()));
							missionList.add(pm);
						}
					}
					return updateService.fillCycleMissionListView(missionList,playerId);
				}else if(1==type){
					//领取每周任务奖品
					PlayerMission mission = null;
					for(PlayerMission pm : playerMissionList){
						if(mission_id == pm.getId()){
							mission = pm;
						}
					}
					if(mission==null){
						return Converter.error(ExceptionMessage.MISSION_NOT_FIND);
					}
					if(mission.getStatus() == 0){//任务未完成
						return Converter.error(ExceptionMessage.MISSION_NOT_FINISH);
					}
					if(mission.getAward() == 1){//此任务已经领取奖品
						return Converter.error(ExceptionMessage.MISSION_ALREADY_AWARD);
					}
					if(null != mission){
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
							nosqlService.addXunleiLog("16.2"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
									+ Constants.XUNLEI_LOG_DELIMITER + mission.getSysMission().getTitle()
									+ Constants.XUNLEI_LOG_DELIMITER + (mission.getStatus()>0?"Finished":"UnFinished")
									+ Constants.XUNLEI_LOG_DELIMITER + mission.getSysMission().getNormalItems()
									+ Constants.XUNLEI_LOG_DELIMITER + mission.getSysMission().getVipItems()
									+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
									);
						}
						return updateService.awardMission(mission);
					}
				}
			case 2:
				List<PlayerActivity> playerActivityList=getService.getPlayerActivityOneDayList(playerId);
				Collections.sort(playerActivityList, new Comparator<PlayerActivity>(){

					@Override
					public int compare(PlayerActivity o1, PlayerActivity o2) {
						if(o1.getStatus() == o2.getStatus()){
							if(o1.getSysActivity().getNeedAward() == o2.getSysActivity().getNeedAward()){
								if(o1.getSysActivity().getNeedAward() == 1){
									double value = (o1.getNumber() * 1.0/o1.getTarget() * 1.0) - (o2.getNumber() * 1.0/o2.getTarget() * 1.0);
									if(value==0){
										return o1.getTarget()-o2.getTarget();
									}else if(value > 0){
										return -1;
									} else {
										return 1;
									}
								} else {
									return o1.getId() - o2.getId();
								}
							} else {
								return o2.getSysActivity().getNeedAward() - o1.getSysActivity().getNeedAward();
							}
						} else {
							return o2.getStatus() - o1.getStatus();
						}
					}
					
				});
				if(0==type){
					//获取我的活动列表
					int missionNeedAward = getService.missionNeedAward(playerId);
					int no = pageNo - 1;
					int itemCount = playerActivityList.size();
					int pageCount = itemCount%pageSize == 0 ?itemCount/pageSize:(itemCount/pageSize)+1;
					if(itemCount>0){
						if(no<0||no>=pageCount){
							no = 0;
						}
						int fromIndex = no*pageSize;
						int toIndex = no == pageCount-1?itemCount:(no+1)*pageSize;
						playerActivityList = playerActivityList.subList(fromIndex, toIndex);
					}else{
						pageCount = 1;
					}
					return Converter.playerActivity(playerActivityList,missionNeedAward,pageNo,pageCount);
				}
				else if(1==type){
					//领取我的活动奖励
					playerActivityList=getService.getPlayerActivityOneDayList(playerId);
					PlayerActivity playerActivity = null;
					for(PlayerActivity pa : playerActivityList){
						if(mission_id == pa.getId()){
							playerActivity = pa;
							break;
						}
					}
					if(playerActivity==null){
						return Converter.error(ExceptionMessage.ACTIVITY_NOT_FIND);
					}
					if(playerActivity.getStatus() == 0){//活动未完成
						return Converter.error(ExceptionMessage.ACTIVITY_NOT_FINISH);
					}
					if(playerActivity.getAward() == 1){//此活动已经领取奖品
						return Converter.error(ExceptionMessage.ACTIVITY_ALREADY_AWARD);
					}
					if(null != playerActivity){
						
						if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
							nosqlService.addXunleiLog("16.3"
									+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
									+ Constants.XUNLEI_LOG_DELIMITER + player.getRank()
									+ Constants.XUNLEI_LOG_DELIMITER + playerActivity.getSysActivity().getTitle()
									+ Constants.XUNLEI_LOG_DELIMITER + (playerActivity.getStatus()>0?"Finished":"UnFinished")
									+ Constants.XUNLEI_LOG_DELIMITER + playerActivity.getSysActivity().getItems()
									+ Constants.XUNLEI_LOG_DELIMITER + playerActivity.getCreateTime()
									+ Constants.XUNLEI_LOG_DELIMITER + playerActivity.getStartTime()
									+ Constants.XUNLEI_LOG_DELIMITER + playerActivity.getEndTime()
									+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
									);
						}
						
						return updateService.awardActivity(playerActivity);
					}
				}
				
			case 3:
				return "";
			default:
				break;
			}
			return "";
		}
		catch (BaseException e) {
			log.debug("Error in getMission: "+e.getMessage());
			return Converter.warn(e.getMessage());
		}
		catch(Exception e){
			log.warn("Error in getMission: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}	
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
