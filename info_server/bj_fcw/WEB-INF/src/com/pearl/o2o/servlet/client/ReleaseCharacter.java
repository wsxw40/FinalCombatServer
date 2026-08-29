package com.pearl.o2o.servlet.client;



import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class ReleaseCharacter extends BaseClientServlet {
	private static final long serialVersionUID = -8713469614143163658L;
	private static Logger log = LoggerFactory.getLogger(ReleaseCharacter.class.getName());
	private static final String[] paramNames = {"pid"};
	
	protected String innerService(String... args) {
		try{
			int playerId=StringUtil.toInt(args[0]);
			Player player=getService.getPlayerById(playerId);
			if(player.getCharacterSize()>Constants.MAX_CHARACTER_SIZE){
				throw new BaseException(ExceptionMessage.NOT_MAX_CHARACTER_SIZE);
			}else if(player.getGPoint()<Constants.DEFAULT_COST_RELEASE_CHARACTER_SIZE){
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_GP_RELEASE);
			}
//TODO CharacterSize			
			player.setCharacterSize(player.getCharacterSize()+1);
			player.setGPoint(player.getGPoint()-Constants.DEFAULT_COST_RELEASE_CHARACTER_SIZE);
			updateService.updatePlayerInfo(player);
			soClient.messageUpdatePlayerMoney(player);
			String 	result=Converter.commonFeedback(null);
			return result;
		}catch (BaseException e) {
			log.debug("BaseException happen in ReleaseCharacter:"+e.getMessage());
			return Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn("Exception happen in ReleaseCharacter",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
