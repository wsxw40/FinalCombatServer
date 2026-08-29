package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;


public class UpdateFriend extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static Logger log = LoggerFactory.getLogger(UpdateFriend.class.getName());
	private static final String[] paramNames = {"uid","pid","id","name","action"};
	
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			final Integer playerId = StringUtil.toInt(args[1]);
			Integer friendId = StringUtil.toInt(args[2]);
			String friendName = args[3];
			String  action 	 = args[4];			
			if(friendId==0&&!"".equals(friendName)){
				Player player=getService.getPlayerByName(friendName);
				if(player!=null){
				friendId=player.getId();
				}else{
					throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
				}
			}
			Player friend=null;
			if(friendId!=0){
				friend=getService.getSimplePlayerById(friendId);
				if(friend==null){
					throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
				}
			}
			final Player player=getService.getPlayerById(playerId);
			Friend black=getService.getBlackById(playerId, friendId);
			Friend hasFriend=getService.getFriendById(playerId, friendId);
			if(Constants.ACTION_FRIEND_ADD.equals(action)){
				if(hasFriend!=null){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_FRIEND);
				}
				if(playerId.equals(friendId)){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_SELF);
				}
				if(getService.getFriendList(playerId, Constants.FRIEND).size()>=50){
					throw new BaseException(ExceptionMessage.FRIEND_SIZE);
				}
				updateService.updateFriend(playerId, friendId, Constants.FRIEND,Constants.BOOLEAN_NO);
				soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.ACCEPT_FRIEND, new Object[]{playerId.toString(),player.getName()}));
				soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST));

				mcc.delete(CacheUtil.oReqFriendList(friendId));
			}
			else if(Constants.ACTION_FRIEND_BLACK_ADD.equals(action)){
				if(black!=null){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_BLACK);
				}
				if(playerId.equals(friendId)){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_SELF_BLACK);
				}
				if(getService.getFriendList(playerId, Constants.BLACKLIST).size()>=15){
					throw new BaseException(ExceptionMessage.BLACK_SIZE);
				}
				if(friend!=null){
					soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST));
				}
				updateService.updateFriend(playerId, friendId, Constants.BLACKLIST,Constants.BOOLEAN_YES);
				mcc.delete(CacheUtil.oBlackList(playerId));
			}
			else if(Constants.ACTION_DELETE.equals(action)){
				if(playerId.equals(friendId)){
					throw new BaseException(ExceptionMessage.CANNOT_DELETE_SELF);
				}
				//检测是否输过二级密码
				if(!checkEnterSPW(playerId)){
					return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
				}
				if(black==null){
					deleteService.deleteFriend(playerId, friendId,Constants.FRIEND);
					soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST));
					mcc.delete(CacheUtil.oFriendList(playerId));
					
					infoLogger.log("deleteFriend", Level.INFO_INT, LogUtils.JoinerByTab.join("",playerId,friendId,Constants.FRIEND));
					
				}else{
					deleteService.deleteFriend(playerId, friendId,Constants.BLACKLIST);
					soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST));
					mcc.delete(CacheUtil.oBlackList(playerId));
				}
			}
			else if(Constants.ACTION_GROUP_ADD.equals(action)){
				if(playerId.equals(friendId)){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_GROUP);
				}
				if(getService.getFriendList(playerId, Constants.GROUP).size()>=50){
					throw new BaseException(ExceptionMessage.GROUP_SIZE);
				}
				if(black==null){
					Friend owner = getService.getFriendById(playerId, playerId,Constants.GROUP);
					if(owner == null){
						throw new BaseException(ExceptionMessage.NOT_HAVE_GROUP);
					}else{
						updateService.updateGroup(playerId, friendId,Constants.GROUP,owner.getGroupName());
						
						soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.ACCEPT_GROUP, new Object[]{playerId.toString(),player.getName()}));
						
						mcc.delete(CacheUtil.oReqGroupList(friendId));
					}

				}else{

					throw new BaseException(ExceptionMessage.CANNOT_ADD_BLACK_TO_GROUP);
				}
			}
			else if(Constants.ACTION_GROUP_DELETE.equals(action)){//未区别被T和主动退出 for xingdong//TODO
				if(playerId.equals(friendId)){
					if(getService.getFriendList(playerId, Constants.GROUP).size()>1){
						throw new BaseException(ExceptionMessage.CANNOT_DELETE_GROUP);
					}
					
				}
				Friend fri=getService.getFriendById(playerId, friendId, Constants.GROUP);
				deleteService.deleteUnAcceptFriend(playerId, Constants.GROUP);
				deleteService.deleteFriend(playerId, friendId,Constants.GROUP);
				soClient.messageCMD(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_GROUP_LIST));
				soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_GROUP_LIST));
				soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.GROUP_LEAVE_MSG,new Object[]{fri.getGroupName()}));
				
				infoLogger.log("deleteFriend", Level.INFO_INT, LogUtils.JoinerByTab.join("",playerId,friendId,Constants.GROUP));
				final Player self=friend;
				Runnable kickMessageTask = new Runnable(){
				@Override
				public void run() {
					try{
					//send kick message to group
						List<Friend> list=getService.getFriendList(playerId, Constants.GROUP);
							for(Friend friend:list){
								if(mcc.get(CacheUtil.oPlayerLocation(friend.getPlayerId()),Constants.CACHE_TIMEOUT)!=null){
									soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_GROUP_LIST));
									if(friend.getFriendId()!=self.getId()){
										Object[] obj={self.getName(),friend.getGroupName()};
										soClient.groupInfo(friend.getName(), CommonUtil.messageFormat(CommonMsg.GROUP_DELETE_MSG, obj));
									}
								}
							}
					}catch (Exception e){
							log.warn("Error happend during send kick message to group. exception is " + e + " cid:" + playerId,e);
					}
				}
				};
				ServiceLocator.asynTakService.execute(kickMessageTask);
//				soClient.messageInfo(friend.getName(), CommonUtil.messageFormat(CommonMsg.KICK_GROUP_MSG, player.getName()));
				mcc.delete(CacheUtil.oGroupList(playerId));

			}
			return "";
		}
		catch (BaseException e) {
			log.debug("Error in UpdateFriend: " + e.getMessage());
			return Converter.error(e.getMessage());
		}
		catch(Exception e){
			log.warn("Error in UpdateFriend: " , e);
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
