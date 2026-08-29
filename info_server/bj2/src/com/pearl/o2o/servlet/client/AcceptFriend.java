package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;


public class AcceptFriend extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static Logger log = LoggerFactory.getLogger(AcceptFriend.class.getName());
	private static final String[] paramNames = {"uid","pid","fid","type"};
	
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			final Integer playerId = StringUtil.toInt(args[1]);
			final Integer friendId = StringUtil.toInt(args[2]);
			Integer type = StringUtil.toInt(args[3]);			
			Player friend=null;
			if(friendId!=0){
				friend=getService.getSimplePlayerById(friendId);
				if(friend==null){
					throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
				}
			}
			final Player player=getService.getSimplePlayerById(playerId);
			final Player self=friend;
			
			if((int)type == Constants.GROUP){
				if((int)type == Constants.GROUP && getService.getFriendById(playerId, playerId,Constants.GROUP)==null){
					throw new BaseException(ExceptionMessage.GROUP_NOT_EXSIT);
				}
				if((int)type == Constants.GROUP && getService.getFriendList(playerId, Constants.GROUP).size()>=50){
					throw new BaseException(ExceptionMessage.GROUP_FULL);
				}
				if(getService.getFriendList(playerId, Constants.GROUP).size()>=30){
//					deleteService.deleteFriend(playerId, friendId,Constants.GROUP);
					throw new BaseException(ExceptionMessage.GROUP_SIZE);
				}
				updateService.acceptFriend(playerId, friendId, type);
				soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_GROUP_LIST, null));
//				soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_GROUP_LIST, null));
				soClient.messageInfo(friend.getName(), CommonUtil.messageFormatI18N(CommonMsg.ADD_GROUP_MSG, player.getName()));
				Runnable acceptMessageTask = new Runnable(){
					@Override
					public void run() {
						try{
						//send accept message to group
						List<Friend> list=getService.getFriendList(playerId, Constants.GROUP);
						boolean isIn=false;
						for(Friend friend:list){
							if(friend.getFriendId().equals(friendId)&&"Y".equals(friend.getAcceptted())){
								isIn=true;
							}
						}
						if(isIn){
							for(Friend friend:list){
								if(mcc.get(CacheUtil.oPlayerLocation(friend.getPlayerId()),Constants.CACHE_TIMEOUT)!=null){
									soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_GROUP_LIST, null));
									if(friend.getFriendId()!=self.getId()){
									soClient.groupInfo(friend.getName(),CommonUtil.messageFormatI18N(CommonMsg.GROUP_ADD_MSG, new Object[]{self.getName(),friend.getGroupName()}));
									}
								}
							}
						}
						}catch (Exception e){
							log.warn("Error happend during send accept message to group. exception is " + e + " cid:" + playerId,e);
						}
					}
				};
				ServiceLocator.asynTakService.execute(acceptMessageTask);
			}else{
				if(getService.getFriendList(friendId, Constants.FRIEND).size()>=50){
//					deleteService.deleteFriend(playerId, friendId, Constants.FRIEND);
					throw new BaseException(ExceptionMessage.FRIEND_SIZE);
				}
				Friend fri=getService.getFriendIndexById(playerId,friendId, type);
				if(fri==null){
					throw new BaseException(ExceptionMessage.FRIEND_FAIL);
				}
				updateService.acceptFriend(playerId, friendId, type);
				soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST, null));
				soClient.messageInfo(player.getName(), CommonUtil.messageFormatI18N(CommonMsg.ADD_FRIEND_MSG, friend.getName()));
				soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST, null));
				soClient.messageInfo(friend.getName(), CommonUtil.messageFormatI18N(CommonMsg.ADD_FRIEND_MSG, player.getName()));
				mcc.delete(CacheUtil.oFriendList(playerId));
				
				//成长任务：添加1个好友
				updateService.updatePlayerGrowthMission(player,GrowthMissionType.ADD_FRIEND);
			}

			
			return "";
		}
		catch (BaseException e) {
			log.debug("Error in AcceptFriend: " , e);
			return Converter.error(e.getMessage());
		}
		catch(Exception e){
			log.warn("Error in AcceptFriend: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
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
