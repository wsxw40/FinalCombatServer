package com.pearl.o2o.servlet.client;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.QuestService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 在线奖励
 */
public class GetOnlineAward extends BaseClientServlet implements Servletable {
	private static final long serialVersionUID = 1519975207181077962L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String[] paramNames = { "pid", "awardSysQuestId" };
	@Resource
	private GetOnlineAward c_online_award;
	@Resource
	private QuestService questService;

	@Override
	protected String innerService(String... args) {
		try {
			return c_online_award.rpc(args);
		} catch (BaseException e) {
			logger.error("Error in c_online_award : " + getLockedKey(args), e);
			return Smarty4jConverter.error(e.getMessage());
		} catch (Exception e) {
			logger.error("Error in c_online_award : " + getLockedKey(args), e);
			return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		//		try {
		//			int playerId = StringUtil.toInt(args[0]);
		//			OnlineAwardReturnValue rv =getService.getOnlineAward(playerId);
		//			List<OnlineAward> awards = rv.getGifts();
		////
		//			//			//防沉迷
		////			int fcm_time = ServiceLocator.nosqlService.getFCMTime(playerId);
		////			if(fcm_time>300){
		//			//				awards=null;  //TODO 客户端禁用该按钮，此处可不作操作
		////			}else if(fcm_time>180){
		////				for(OnlineAward oa:awards){
		////					oa.setUnit(oa.getUnit()/2);
		////				}
		////			}
//
		//			if(awards!=null&&!awards.isEmpty()){
		//				Collections.sort(awards,new Comparator<OnlineAward>() {
		//					@Override
		//					public int compare(OnlineAward arg0, OnlineAward arg1) {
		//						return arg0.getItemId()-arg1.getItemId();
		//					}
		//				});//在线时长奖励按照物品id排序
//			}
		//			return Converter.onlineAward(rv.getLeftSeconds(),rv.getCExp(),rv.getNextTime(),awards,null);
		//
		//		} catch (Exception e) {
		//			logger.warn("Error in GetOnlineAward: ", e);
		//			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		//		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int sysQuestId = 0;
		try {
			sysQuestId = Integer.parseInt(args[1]);
		} catch (Exception e) {
		}
		String str = questService.getOnlineAward(playerId, sysQuestId);
		return str;
	}

	@Override
	public String getLockedKey(String... args) {
		return args[0];
	}
}
