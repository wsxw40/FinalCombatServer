package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class ShootingAmmo extends BaseClientServlet {
	
	private static final long serialVersionUID = -7291586145315384769L;
	private Logger logger = LoggerFactory.getLogger("shooting");
	private static final String[] paramNames = {"pid"};
	
	@Override
	protected String innerService(String... strings) {
		try {
			int playerId = StringUtil.toInt(strings[0]);
			List<SysItem> shootingAmmos = getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.SHOOTING_AMMO.getValue(), Constants.DEFAULT_ITEM_TYPE);
			List<SysItem> retList = new ArrayList<SysItem>();
			for(SysItem si : shootingAmmos){
				si = si.clone();
				int quantity = getService.getPlayerItemsTotalQuantity(playerId, Constants.DEFAULT_ITEM_TYPE, si.getId());
				si.setUnit(quantity);
				retList.add(si);
			}
			return Converter.shootingAmmo(retList);
		}catch (BaseException e) {
			logger.warn("ShootingAmmo/Warn:\t",e);
			return Converter.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("ShootingAmmo/Error:\t", e);
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
