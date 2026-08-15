package com.pearl.o2o.servlet.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetExpireBubble extends BaseClientServlet {
	
	private static final long serialVersionUID = 1481641700619768867L;
	private static Logger log = LoggerFactory.getLogger(GetExpireBubble.class.getName());	
	private static final String[] paramNames = {"pid"};

	protected String innerService(String... args) {
		try{					
			int playerId = StringUtil.toInt(args[0]);
			if(playerId==0){
				log.warn("GetExpireBubble get a playerId==0");
			}
			final Player player=getService.getPlayerById(playerId);
			Runnable getExpiredTask = new Runnable() {
				@Override
				public void run() {
					/** notify the client that the item is expired since last login */
					try {
						int expireFlag = getService.getExpiredPlayerItemSinceLastLogin(player.getId(), new Date(player.getLastLoginTime()*1000l),player);
						int undurableFlag = getService.getDurableNotFull(player.getId(), new Date(player.getLastLoginTime()),player);
						
						if(expireFlag==2||undurableFlag==2){
							soClient.puchCMDtoClient(player.getName(), CommonUtil.messageFormat(CommonMsg.REFRESH_EXPIRE_BUBBLE,null));
						}
					} catch (Exception e) {
						log.warn("error happend during send mail to player while player online, exception is "+ e);
					}
				}
			};
			 ServiceLocator.asynTakService.submit(getExpiredTask);
			return Converter.commonFeedback(null);		
		}catch (BaseException e) {
			log.debug(e.getMessage());
			return  Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn(e.getMessage(),e);
			return  Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
