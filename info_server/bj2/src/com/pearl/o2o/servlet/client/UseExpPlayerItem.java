package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class UseExpPlayerItem extends BaseClientServlet {

	private static final long serialVersionUID = -7482850866233216483L;
	private static Logger log = LoggerFactory.getLogger(UseExpPlayerItem.class.getName());
	private static final String[] paramNames = { "uid", "pid","piid"};

	protected String innerService(String... args) {
		try {
			@SuppressWarnings("unused")
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int playerItemId = StringUtil.toInt(args[2]);
			if(playerId!=0){
				Player player = getService.getPlayerById(playerId);
				createService.useItemById(player, Constants.DEFAULT_OPEN_TYPE, playerItemId, "", 0, 0);
				PlayerItem item=getService.getPlayerItemById(playerId, playerItemId);
				Player simplePlayer=getService.getSimplePlayerById(playerId);
				return Converter.player(simplePlayer, StringUtil.toInt(item.getSysItem().getIValue()));
			}else{
				throw new BaseException(ExceptionMessage.NOT_RIGHT_PARAM);
			}
			
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
