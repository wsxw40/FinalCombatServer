package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetItemWillBeExpiredInShortTime extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetItemWillBeExpiredInShortTime.class.getName());
	private String[] paramNames={"uid","pid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			
			List<PlayerItem> returnList=getService.getPlayerItemsWillBeExpired(userId, playerId);
			List<PlayerItem> durableList=getService.getPlayerItemsNotDurable(userId, playerId);
			return Converter.expiredItemInShortTime(returnList,durableList);
		}
		catch(Exception e){
			log.warn("Error in GetItemPackList: " , e);
			return "list={}";
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
