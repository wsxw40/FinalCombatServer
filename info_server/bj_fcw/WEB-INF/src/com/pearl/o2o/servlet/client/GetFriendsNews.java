package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.object.playerevent.BasePlayerEvent;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetFriendsNews extends BaseClientServlet {
	static Logger log = LoggerFactory.getLogger(GetFriendsNews.class.getName());
	private static final long serialVersionUID = -6836692174398117141L;
	private String[] paramNames={"pid","cid"};
	@Override
	protected String innerService(String... args) {
		try{
//			int pid = StringUtil.toInt(args[0]);
			int cid = StringUtil.toInt(args[1]);
			List<BasePlayerEvent> list = getService.getFriendsNews( cid);
			return Converter.playerNews(list);
		}
		catch (Exception e) {
			log.warn("exception in get friends news",e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
