package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CombineGetPrice extends BaseClientServlet {
	private static final long serialVersionUID = 6356406975633293330L;
	static Logger log = LoggerFactory.getLogger(CombineGetPrice.class.getName());
	private String[] paramNames = { "pid", "type"};
	
	final private static int[][] ITEMS = {
		{125, 127, 126},//128
		{4505},
		{},
		{4886,4890,4891,4892}//加工装置//模组
	}; 
	@Override
	protected String innerService(String... args) {
		try {
//			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);

			SysItem[] sysItemArray = new SysItem[ITEMS[type - 1].length];
			Integer[] priceArray = new Integer[ITEMS[type - 1].length];
			
			for(int i = 0; i < ITEMS[type - 1].length; i++){
				sysItemArray[i] = getService.getSysItemByItemId(ITEMS[type - 1][i]);
				Payment payment = sysItemArray[i].getAllCrPricesList().get(0);
				priceArray[i] = payment.getCost();
			}
			return Converter.combineGetPrice(priceArray, sysItemArray);
		} catch (NotBuyEquipmentException nbe) {
			log.debug("Exception in CreatePlayerItem"+nbe.getMessage());
			return Converter.createPlayerItem(Constants.NUM_ZERO, nbe.getMessage());
		} catch (Exception e) {
			log.warn("Exception in CreatePlayerItem", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
