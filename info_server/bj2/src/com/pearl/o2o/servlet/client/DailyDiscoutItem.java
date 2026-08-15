package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

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
			
			List<SysItem> sis=new ArrayList<SysItem>();
			
			for(int itemId:Constants.DAILY_DISCOUNT_SYSITEM_IDS){
				sis.add(getService.getSysItemByItemId(itemId));
			}
			
			StringBuilder flagStr = new StringBuilder();
			
			//黄金卡三档折扣率，白银卡三档折扣率（暂用黄金卡折扣率）
			String[] discounts = {StringUtils.join(Constants.DAILY_DISCOUNTS[0], ","),StringUtils.join(Constants.DAILY_DISCOUNTS[1], ",")};
			
			for(int c=0;c<discounts.length;c++){
				
				int flag = nosqlService.getPlayerDailyDiscountBuyFlag(playerId, Constants.DAILY_DISCOUNT_SYSITEM_IDS[c]);
				
				flagStr.append("{");
				for(int i=0;i<Constants.DAILY_DISCOUNTS[0].length;i++){
					flagStr.append((flag & (int)Math.pow(2,i))==0?"0":"1");
					if(i!=Constants.DAILY_DISCOUNTS[0].length-1){
						flagStr.append(",");
					}else{
						flagStr.append("},");
					}
				}
			}
			
			String discountsplit =  "{"+StringUtils.join(discounts,"},{")+"}";
			
			return Converter.dailyDiscoutItem(sis,flagStr.toString().substring(0,flagStr.length()-1),discountsplit);
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
