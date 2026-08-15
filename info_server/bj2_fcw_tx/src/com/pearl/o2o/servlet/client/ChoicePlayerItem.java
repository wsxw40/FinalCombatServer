package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class ChoicePlayerItem extends BaseClientServlet {
	private static final long serialVersionUID = 3032265403231374204L;
	private static Logger log = LoggerFactory.getLogger(ChoicePlayerItem.class.getName());
	private static final String[] paramNames = { "uid", "pid", "sid", "piid",  };

	protected String innerService(String... args) {
		try {
			@SuppressWarnings("unused")
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int itemId = StringUtil.toInt(args[2]);
			int playerItemId = StringUtil.toInt(args[3]);
			createService.choicePlayerItem(playerId, playerItemId, itemId);
			return Converter.commonFeedback(null);
		} catch (NullPointerException e) {
			log.warn("exception in UsePlayerItem servlet", e);
			return Converter.commonFeedback(ExceptionMessage.NOT_PLAYER_ITEM);
		} catch (BaseException e) {
			log.debug("exception in UsePlayerItem servlet", e);
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
