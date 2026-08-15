package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class UsePlayerItem extends BaseClientServlet {
	private static Logger log = LoggerFactory.getLogger(UsePlayerItem.class.getName());
	private static final long serialVersionUID = -6836692174398117141L;
	private static final String[] paramNames = { "uid", "pid", "type", "playeritemid", "msg", "server_id", "channel_id" };

	protected String innerService(String... args) {
		try {
			@SuppressWarnings("unused")
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int playerItemId = StringUtil.toInt(args[3]);
			String message = args[4];
			int serverId = StringUtil.toInt(args[5]);
			int channelId = StringUtil.toInt(args[6]);
			Player player = getService.getPlayerById(playerId);
			if (type == Constants.DEFAULT_ITEM_TYPE||type == Constants.DEFAULT_OPEN_TYPE) {
				String returnString=createService.useItemById(player, type, playerItemId, message, channelId, serverId);
				if(returnString!=null){
					return returnString;
				}
			}else if(type == Constants.DEFAULT_ZYZDZ_TYPE){//资源争夺战个人物品
				
			}
			return Converter.commonFeedback(null);
		} catch (NullPointerException e) {
			log.warn("exception in UsePlayerItem servlet:"+e.getMessage());
			return Converter.commonFeedback(ExceptionMessage.NOT_PLAYER_ITEM);
		} catch (BaseException e) {
			log.debug("exception in UsePlayerItem servlet:"+e.getMessage());
			return Converter.commonFeedback(e.getMessage());
		} catch (Exception e) {
			log.warn("exception in UsePlayerItem servlet", e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);

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
