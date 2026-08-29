package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class UseSpeak extends BaseClientServlet {
	private static Logger log = LoggerFactory.getLogger(UseSpeak.class.getName());
	private static final long serialVersionUID = -6836692174398117141L;
	private static final String[] paramNames = { "pid", "type", "msg", "server_id", "channel_id" };

	protected String innerService(String... args) {
		try {
			// int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[0]);
			int iId = StringUtil.toInt(args[1]);
			String message = args[2];
			int serverId = StringUtil.toInt(args[3]);
			int channelId = StringUtil.toInt(args[4]);
			
			Player player = getService.getPlayerById(playerId);
			if (iId == 2 || iId == 3) {
				String returnString = createService.useSpeak(player, iId, message, channelId, serverId);
				if (returnString != null) {
					return returnString;
				}
			}
			return Converter.warn(null);
		} catch (NullPointerException e) {
			log.warn("exception in UsePlayerItem servlet", e);
			return Converter.warn(ExceptionMessage.NOT_PLAYER_ITEM);
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
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}
