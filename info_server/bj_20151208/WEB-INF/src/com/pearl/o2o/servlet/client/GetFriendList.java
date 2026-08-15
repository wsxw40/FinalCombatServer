package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.Rank;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetFriendList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetFriendList.class.getName());
	private String[] paramNames={"uid","pid","displayOnline"};
	//请求战斗伙伴时相当于取出50个人的信息，这个需要一定的时间
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			int  displayOnline=StringUtil.toInt(args[2]);
			List<Friend> result = getService.getFriendList(playerId, Constants.FRIEND); 
			List<Friend> friendOnlineList=new ArrayList<Friend>();
			for(Friend fri:result){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					fri.setOnline(1);
					if(displayOnline==1){
						friendOnlineList.add(fri);
					}
				}
			}
			
			Collections.sort(result, new Comparator<Friend>(){
				@Override
				public int compare(Friend o1, Friend o2) {
					return (o1.getOnline()>=o2.getOnline()?-1:1);
				}
			});
			List<Friend> blackList = getService.getFriendList(playerId, Constants.BLACKLIST);
			for(Friend fri:blackList){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					fri.setOnline(1);
				}
			}
			
			Collections.sort(blackList, new Comparator<Friend>(){
				@Override
				public int compare(Friend o1, Friend o2) {
					return (o1.getOnline()>=o2.getOnline()?-1:1);
				}
			});
			
			if(displayOnline==1){
				return Converter.friendAllList(friendOnlineList,blackList);
			}else{
				return Converter.friendAllList(result,blackList);
			}
		}
		catch(Exception e){
			log.warn("Error in GetFriendList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
