package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetHummerNum extends BaseClientServlet {
	private static final long serialVersionUID = -9181735522010575779L;
	private static Logger log = LoggerFactory.getLogger("magicbox");
	private static final String[] paramNames = { "pid","sid" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int itemId = StringUtil.toInt(args[1]);
			int hummerNum =0;
			List<PlayerItem> pswCardList = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.PASSWORD_CARD.getValue());
//			hummerNum = getService.getPlayerItemsTotalQuantity(playerId,Constants.DEFAULT_MATERIAL_TYPE, itemId);
			
			for(PlayerItem pi : pswCardList){
				hummerNum += pi.getQuantity();
			}
			return "hummer_num="+hummerNum;

		} catch (Exception e) {
			log.warn("Error in DailyCheck: ", e);
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
