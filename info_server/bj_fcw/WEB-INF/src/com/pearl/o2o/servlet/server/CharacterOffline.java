package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.pojo.Friend;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;

public class CharacterOffline extends BaseServerServlet {
	private static final long serialVersionUID = 6032163311968210585L;
	static Logger logger = LoggerFactory.getLogger(CharacterOffline.class.getName());
	
	@SuppressWarnings("finally")
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		try{
			r.readInt(); // userId
			int playerId=r.readInt();
			
			final Player player=getService.getSimplePlayerById(playerId);
			mcc.delete(CacheUtil.oPlayerLocation(playerId));
			//清空是否输入二级密码的状态
			mcc.delete(CacheUtil.sPlayerSPWIE(playerId));
			updateService.updatePlayerWhileOffline(player);
			updateService.updateOnlineAward(playerId, false);
			updateService.updatePlayerMelting(getService.getPlayerMelting(playerId, false).playerOffline());
			deleteService.cleanUnUsedDataInRedis(playerId);
			deleteService.cleanUnUsedDataInMcc(playerId);
			try{
				final List<Friend> list=getService.getFriendList(playerId, Constants.FRIEND);
				final String offlineMessage = CommonUtil.messageFormatI18N(CommonMsg.FRIEND_OFFLINE,new Object[]{player.getName(),player.getName()});
				
				Runnable sendOfflineMessageTask = new Runnable(){
					@Override
					public void run() {
						try{
							for(Friend friend:list){
								try{
									if(mcc.get(CacheUtil.oPlayerLocation(friend.getFriendId()),Constants.CACHE_TIMEOUT)!=null){
										soClient.messageCMD(friend.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_ONLINE_FRIEND_LIST));
										soClient.messageOnline(friend.getName(), offlineMessage);
									}
									
								}catch(Exception e){
									logger.warn("Error happend during send offline message, exception " + e);
								}
							}
							if(ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()){
								nosqlService.addXunleiLog("5.1" + Constants.XUNLEI_LOG_DELIMITER+ player.getUserName() + 
										Constants.XUNLEI_LOG_DELIMITER+ player.getName() + 
										Constants.XUNLEI_LOG_DELIMITER+ player.getRank() + 
										Constants.XUNLEI_LOG_DELIMITER+ 1 +
										Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date()));
							}
							{
								long onlineTime=System.currentTimeMillis()-new Date(player.getLastLoginTime()*1000l).getTime();
								infoLogger.log(LogServerMessage.secondLog.name(), Level.INFO_INT, 
										LogUtils.JoinerByTab.join("2.9",player.getUserName(),CommonUtil.simpleDateFormat.format(new Date()),ConfigurationUtil.XUNLEI_SERVER_IP,onlineTime/1000,player.getId(),player.getName(),player.getRank()));
							}
						}catch (Exception e) {
							logger.warn("Error happend during CharacterOffline: ", e);
						}
					}
				};
				
				ServiceLocator.asynTakService.execute(sendOfflineMessageTask);
			}catch (Exception e){
				logger.warn("Error happend during CharacterOffline: ", e);
			}finally{
				return Constants.EMPTY_BYTE_ARRAY;
			}
		}catch (Exception e) {
			logger.warn("Error in CharacterOffline: ", e);
			throw e;
		}
	}
	@Override
	protected String getLockKey(BinaryChannelBuffer in) throws Exception {
		in.readInt();
		int playerId=in.readInt();
		return CommonUtil.getLockKey(playerId);
	}
}
