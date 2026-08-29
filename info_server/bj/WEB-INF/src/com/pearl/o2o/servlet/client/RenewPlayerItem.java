package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class RenewPlayerItem extends BaseClientServlet {
	
	private static final long serialVersionUID = -7958477105017482799L;
	static Logger log = LoggerFactory.getLogger(RenewPlayerItem.class.getName());
	private String[] paramNames={"uid","pid","piid","t","costid"};
	@Override
	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int playerItemId=StringUtil.toInt(args[2]);
			int type=StringUtil.toInt(args[3]);
			int costId=StringUtil.toInt(args[4]);
			updateService.renewPlayerItem(playerId, playerItemId, type, costId, true);
			return Converter.error(null);
		//zlm2015-8-4-start 
		//玩家之间的游戏内市场物品购买直接赠送 IS_DEFAULT N  IS_SHOW 1 
		}catch (NotBuyEquipmentException e) {
			log.debug("NotBuyEquipmentException in CreatePlayerItem"+ e.getMessage());
			return Converter.error(e.getMessage());	
		//zlm2015-8-4-end	
		}
		catch (BaseException e) {
			log.debug("Exception in RenewPlayerItem:"+e.getMessage());
			return Converter.error(e.getMessage());	
		}
		catch (Exception e) {
			log.warn("Exception in RenewPlayerItem",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);	
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
