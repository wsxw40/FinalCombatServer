package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetRequestFriendList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetRequestFriendList.class.getName());
	private String[] paramNames={"uid","pid"};
	
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			List<Friend> friendList = getService.getRequestFriendList(playerId, Constants.FRIEND);
			for(Friend fri:friendList){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					fri.setOnline(1);
				}
			}
		
			List<Friend> groupList = getService.getRequestFriendList(playerId, Constants.GROUP);
			for(Friend fri:groupList){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					fri.setOnline(1);
				}
			}
			
			return Converter.requestList(friendList,groupList);

		}
		catch(Exception e){
			log.warn("Error in GetRequestFriendList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
