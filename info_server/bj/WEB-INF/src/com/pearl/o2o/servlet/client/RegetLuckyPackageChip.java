package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class RegetLuckyPackageChip extends BaseClientServlet {

	private static final long serialVersionUID = 7087995444913116674L;
	private static Logger log = LoggerFactory.getLogger(GetLuckyPackageList.class.getName());
	private static final String[] paramNames = {"pid"};
	protected String innerService(String... args) {
		try{
			int playerId = StringUtil.toInt(args[0]);
			int chipNum				 = getService.getMedolNumByPlayerId(playerId);
			int copperyKeyNum = getService.getLuckypackageCardNum(playerId, 1);
			int silverKeyNum = getService.getLuckypackageCardNum(playerId, 2);
			int goldKeyNum = getService.getLuckypackageCardNum(playerId, 3);
			List<SysItem> luckyPackages = getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.LUCKYPACKAGE_CARD.getValue(),Constants.DEFAULT_MATERIAL_TYPE);
			return Converter.luckPackagePrice(chipNum, null ,copperyKeyNum,silverKeyNum,goldKeyNum,luckyPackages);
		} catch (Exception e) {
			log.warn("Error in GetLuckyPackageList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}	
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
