package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPinTuStates extends BaseClientServlet {

	private static final long serialVersionUID = 661949539508628790L;
	private static final String[] paramNames = { "pid"};
	private static Logger logger = LoggerFactory.getLogger(GetPinTuStates.class);
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			String playerPinTuFlags = nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_KEY+playerId);
			if(playerPinTuFlags==null||!playerPinTuFlags.matches("[01]{"+Constants.PLAYER_PT_PRI_FLAGS.length()+"}[0-" + Constants.PLAYER_PT_TOTAL_CHANCE + "]")){
				playerPinTuFlags = Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE;
				playerPinTuFlags=createService.getPinTuStatesOpenForVip(playerId,playerPinTuFlags);		
			}
			if(playerPinTuFlags.equals(Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE)){
				playerPinTuFlags=createService.getPinTuStatesOpenForVip(playerId,playerPinTuFlags);	
			}
			
			String playerPinTuFlagsRet = playerPinTuFlags.replaceAll("", ",").replaceFirst(",", "");//返回给客户端的当前拼图状态
			return "flags={" + playerPinTuFlagsRet + "}";
		}catch (BaseException e) {
			logger.warn("Exception in GetPinTuStates :" + e.getMessage());
			return Converter.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("GetPinTuStates error happens : ",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
