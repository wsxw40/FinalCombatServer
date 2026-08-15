package com.pearl.o2o.servlet.client;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.service.onbuy.DailyDiscountBuy;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.StringUtil;

public class DailyDiscountBuyItem extends BaseClientServlet {

	private static final long serialVersionUID = -289010750665114416L;
	static Logger log = LoggerFactory.getLogger(DailyDiscountBuyItem.class.getName());
	private String[] paramNames = { "pid", "sid","index"};
	@Override
	protected String innerService(String... args) {
		int playerId = StringUtil.toInt(args[0]);
		int sysItemId = StringUtil.toInt(args[1]);
		int index = StringUtil.toInt(args[2]);
		
		//add the check str for log
		StringBuffer checklog=new StringBuffer(playerId+"|"+sysItemId+"-");
		try {
			
			Player player = getService.getSimplePlayerById(playerId);
			SysItem sysItem = getService.getSysItemByItemId(sysItemId);
			List<Integer> ids = Arrays.<Integer>asList(Constants.DAILY_DISCOUNT_SYSITEM_IDS);
			if(!ids.contains(sysItemId)){
				return Converter.warn(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			CommonUtil.checkNull(player,ExceptionMessage.PALERY_NOT_EXIST);
			CommonUtil.checkNull(sysItem, ExceptionMessage.NOT_FIND_ITEM);
			int result = CommonUtil.buyPlayerItem(new DailyDiscountBuy(checklog,player, sysItem,index));
			return "result=" + result;
			
		} catch (BaseException e) {
			log.warn("Exception in DailyDiscountBuyItem :" + e.getMessage());
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("Exception in DailyDiscountBuyItem :" , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}finally{
			infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
					LogUtils.JoinerByVertical.join("0.5",CommonUtil.simpleDateFormat.format(new Date()),
							checklog.toString()));
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
