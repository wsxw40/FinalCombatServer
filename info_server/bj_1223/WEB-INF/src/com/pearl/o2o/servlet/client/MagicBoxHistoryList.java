package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class MagicBoxHistoryList extends BaseClientServlet {

	private static final long serialVersionUID = -3710281154475578947L;
	private static Logger log = LoggerFactory.getLogger("magicbox");
	private static final String[] paramNames = { };
	
	protected String innerService(String... args) {
		try {
			List<TmpPlayerItem> magicBoxPlayerItems = getService.getMagicBoxOpenTmpPlayerItems_1();
			String result = Converter.magicBoxHistoryList(magicBoxPlayerItems);
			return result;

		} catch (BaseException e) {
			log.warn("MagicBoxHistoryList/Warn:\t" , e);
			return Converter.commonFeedback(e.getMessage());
		} catch (Exception e) {
			log.error("MagicBoxHistoryList/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
