
package com.pearl.o2o.servlet.client;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DailyCheck extends BaseClientServlet {

	private static final long serialVersionUID = -8841445031336394942L;
	private static Logger log = LoggerFactory.getLogger("dailycheck");
	private static final String[] paramNames = { "pid" ,"day","checktype"};

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int day = StringUtil.toInt(args[1]);
			int checkType = StringUtil.toInt(args[2]);//1补签  2当天 3预签
			Calendar sDate = Calendar.getInstance();
			Calendar cDate = Calendar.getInstance();
			if(day<=0||day>sDate.getActualMaximum(Calendar.DAY_OF_MONTH)){
				log.warn("DailyCheck/"+Constants.RPC_PARAM_ERROR_LOG +":\tday="+day);
				return Converter.warn(ExceptionMessage.PARAM_ERROR_MSG);
			}
			if(checkType<1 || checkType>3){
				log.warn("DailyCheck/"+Constants.RPC_PARAM_ERROR_LOG +":\tchecktype="+checkType);
				return Converter.warn(ExceptionMessage.PARAM_ERROR_MSG);
			}
			cDate.set(GregorianCalendar.DAY_OF_MONTH, day);
			return  createService.dailyCheckPay(playerId, checkType,cDate,sDate);
		} catch (BaseException e) {
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("DailyCheck/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}

}
