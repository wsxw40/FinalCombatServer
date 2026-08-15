package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class UpdatePlayerInfo extends BaseClientServlet {
	private static final long serialVersionUID = 6385243821827353337L;
	private static Logger log = LoggerFactory.getLogger(UpdatePlayerInfo.class.getName());	
	private static final String[] paramNames = {"pid","head","title","achievementList","iconLevel"};

	protected String innerService(String... args) {
		try{					
			int playerId = StringUtil.toInt(args[0]);
			String icon = args[1];
			String title = args[2];
			String achievementList = args[3];
			int iconLevel= StringUtil.toInt(args[4]);
			Player player = null;
			
			//get basic real time information

			player = getService.getPlayerById(playerId);
			if(player==null){
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
			if(null == icon || "".equals(icon)){
				icon = "01_r";
			}
			if(iconLevel > player.getIsVip()){
				return Converter.error(ExceptionMessage.NOT_VIP_BUY);
			}
			player.setIcon(icon);
			player.setTitle(title);
			player.setAchievement(achievementList);
			updateService.updatePlayerInfo(player);
			return "";			
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
	
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}