package com.pearl.o2o.servlet.client;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DailyDiscoutItem extends BaseClientServlet {

	private static final long serialVersionUID = -4222124160816133552L;
	static Logger log = LoggerFactory.getLogger(DailyDiscoutItem.class);
	private String[] paramNames = { "pid"};
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			SysItem si = getService.getSysItemByItemId(Constants.DAILY_DISCOUNT_SYSITEM_IDS[0]);
			int flag = nosqlService.getPlayerDailyDiscountBuyFlag(playerId, Constants.DAILY_DISCOUNT_SYSITEM_IDS[0]);
			StringBuilder flagStr = new StringBuilder();
			for(int i=0;i<Constants.DAILY_DISCOUNTS.length;i++){
				flagStr.append((flag & (int)Math.pow(2,i))==0?"0":"1");
				if(i!=Constants.DAILY_DISCOUNTS.length-1){
					flagStr.append(",");
				}
			}
			String discounts = StringUtils.join(Constants.DAILY_DISCOUNTS, ",");
			return Converter.dailyDiscoutItem(si,flagStr.toString(),discounts);
		} catch (Exception e) {
			log.error("DailyDiscoutItem : " ,e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
