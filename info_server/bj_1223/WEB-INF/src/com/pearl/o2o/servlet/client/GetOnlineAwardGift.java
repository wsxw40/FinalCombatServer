package com.pearl.o2o.servlet.client;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.OnlineAwardReturnValue;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetOnlineAwardGift extends BaseClientServlet {
	private static final long serialVersionUID = -1878309268467910736L;
	private static Logger log = LoggerFactory.getLogger(GetOnlineAwardGift.class.getName());
	private static final String[] paramNames = { "pid" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			// TODO 逻辑
			OnlineAwardReturnValue rv = getService.getOnlineAwardGift(playerId);
			List<OnlineAward> awards = rv.getGifts();
			
			//防沉迷
//			int fcm_time = ServiceLocator.nosqlService.getFCMTime(playerId);
//			if(fcm_time>300){
//				awards=null;  //TODO 客户端禁用该按钮，此处可不作操作
//			}else if(fcm_time>180){
//				for(OnlineAward oa:awards){
//					oa.setUnit(oa.getUnit()/2);
//				}
//			}
			
			if(awards!=null&&!awards.isEmpty()){
				Collections.sort(awards,new Comparator<OnlineAward>() {
					@Override
					public int compare(OnlineAward arg0, OnlineAward arg1) {
						return arg0.getItemId()-arg1.getItemId();
					}
				});//在线时长奖励按照物品id排序
			}
			
			//发放不进行提示的奖励
			createBoundsOnlineQuietly(playerId);
			
			return Converter.onlineAward(rv.getLeftSeconds(), rv.getNextTime(), awards, rv.getErrormsg());

		} catch (Exception e) {
			log.warn("Error in GetOnlineAwardGift: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	
	/**
	 * 发放不进行提示的奖励
	 * @param playerId
	 * @throws Exception
	 */
	private void createBoundsOnlineQuietly(int playerId) throws Exception{
		//发放在线奖励
		createService.createBoundsDaily(playerId,Constants.BOUNDS.ONLINE_RES_BOUND);
	}
}
