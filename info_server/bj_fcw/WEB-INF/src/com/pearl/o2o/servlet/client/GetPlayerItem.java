package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerItem extends BaseClientServlet {

	private static final long serialVersionUID = 7213824055794726109L;
	static Logger log = LoggerFactory.getLogger(GetPlayerItem.class.getName());
	private static final String[] paramNames = {"uid","cid","t","id"};
	
	protected String innerService(String... args) {
		try {
//			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int itemId = StringUtil.toInt(args[3]);
			PlayerItem playerItem = getService.getPlayerItemByItemId(playerId, type, itemId);
			SysItem si=getService.getSysItemByItemId(playerItem.getItemId());
			return Converter.playerItem(playerItem,si);
		} catch (Exception e) {
			log.warn("Exception in GetSysItemWeapon", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	
	
}
