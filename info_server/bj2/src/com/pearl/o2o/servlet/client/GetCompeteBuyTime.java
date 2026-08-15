package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetCompeteBuyTime extends BaseClientServlet {
	private static final long serialVersionUID = 4052662283572185865L;
	private static Logger log = LoggerFactory.getLogger(GetCompeteBuyItems.class.getName());
	private String[] paramNames = { "pid"};
	
	@Override
	protected String innerService(String... args) {
		try {
		//	int playerId = StringUtil.toInt(args[0]);
//			Date now = new Date();
//			Date startTime = getService.getCompeteBuyTime(0);
//			Date endTime = getService.getCompeteBuyTime(1);
			int type = 0;    //默认0，代表抢拍结束，下次抢拍开始不在当日
			int time=0;
			String[] timeStatus = getService.getCompeteBuyTime(Constants.CMPT_BUY_STT_WEEK_DAY,Constants.CMPT_BUY_STT_TIME_HOUR,Constants.CMPT_BUY_END_WEEK_DAY,Constants.CMPT_BUY_END_TIME_HOUR);
			time=StringUtil.toInt(timeStatus[0]);
			type=StringUtil.toInt(timeStatus[1]);
//			if(DateUtils.isSameDay(now, startTime)){
//				if(now.before(startTime)){
//					time = (int)((startTime.getTime() - now.getTime())/1000); //当日，距抢拍开始还剩多久
//					type=1;
//				}else if(now.before(endTime)){
//					time=(int)((endTime.getTime() - now.getTime())/1000);   //在当日，且在抢拍时间内
//					type=2;
//				}
//			}else{
//				if(now.before(endTime)&&now.after(startTime)){
//					time=(int)((endTime.getTime() - now.getTime())/1000);   
//					type=2;
//				}
//			}

			return Converter.GetCompeteBuyTime(type,time);
		
		}catch (Exception e) {
			log.error("GetCompeteBuyTime:\t",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}

