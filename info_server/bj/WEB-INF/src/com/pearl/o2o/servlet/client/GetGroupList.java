package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetGroupList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetGroupList.class.getName());
	private String[] paramNames={"uid","pid","displayOnline"};
	
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = StringUtil.toInt(args[1]);
			int  displayOnline=StringUtil.toInt(args[2]);
			List<Friend> result = getService.getFriendList(playerId, Constants.GROUP);
			if(result!=null&&result.size()!=0){
			Friend self = result.remove(0);
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
			
			result.add(0, self);
			}
			List<Integer> ids = getService.getGroupOwers(playerId);
			List<List<Friend>> addGroups = new ArrayList<List<Friend>>();
			
			for (Integer id : ids) {
				if(id.equals(playerId)){

				}else{
					if(getService.getFriendById(id, playerId, Constants.GROUP)!=null && getService.getFriendById(id, playerId, Constants.GROUP).getAcceptted().equals(Constants.BOOLEAN_YES)){
						List<Friend> group=getService.getFriendList(id, Constants.GROUP);
						if(group!=null&&group.size()!=0){
						Friend leader = group.remove(0);
						for(Friend fri:group){
							PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(fri.getFriendId()),Constants.CACHE_TIMEOUT);
							if (location != null) {
								fri.setOnline(1);
							}
						}
						
						Collections.sort(group, new Comparator<Friend>(){
							@Override
							public int compare(Friend o1, Friend o2) {
								return (o1.getOnline()>=o2.getOnline()?-1:1);
							}
						});
						group.add(0, leader);
						addGroups.add(group);
						}
					}
					
				}
				
			}
			
//			if(displayOnline==1){
//				return Converter.groupList(friendOnlineList,addGroups);
//			}else{
				return Converter.groupList(result,addGroups);
//			}
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
