package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Channel;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetFriendBlackList extends BaseClientServlet {
	private static final long serialVersionUID = -6341165441836448573L;
	static Logger log = LoggerFactory.getLogger(GetFriendBlackList.class.getName());
	private String[] paramNames={"uid","cid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			List<Friend> result = getService.getFriendList(playerId, Constants.BLACKLIST);
			for(Friend fri:result){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					fri.setOnline(1);
				}
			}
			return Converter.friendList(result);			
		}
		catch(Exception e){
			log.warn("Error in GetFriendBlackList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
