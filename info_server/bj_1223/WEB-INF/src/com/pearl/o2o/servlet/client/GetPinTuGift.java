package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
/*
 * get puzzle award gift
 */
public class GetPinTuGift extends BaseClientServlet {
	private static final long serialVersionUID = 8096497808135444076L;
	private static Logger log = LoggerFactory.getLogger(GetPinTuGift.class.getName());
	private static final String[] paramNames = {"pid","type"};
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			String playerPinTuFlags = nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_KEY+playerId); 
			
			if(playerPinTuFlags==null||playerPinTuFlags.length()!=Constants.PLAYER_PT_PRI_FLAGS.length()+1||!playerPinTuFlags.matches("[01]*[0-" + Constants.PLAYER_PT_TOTAL_CHANCE + "]")){
				playerPinTuFlags = Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE;
			}
			if(Constants.PLAYER_PT_CMT_FLAGS.equals(playerPinTuFlags.substring(0, playerPinTuFlags.length()-1))){
				OnlineAward misticOa=createService.completePinTu(playerId,type);
				return Converter.misticAward(misticOa);
			}else{
				log.warn("puzzle not finish, but send a getpuzzlegift request from playerId:"+playerId+" type:"+type);
				throw new Exception();
			}
		}catch (BaseException e) {
			log.warn("Error in GetPinTuGift: " , e);
			return Converter.error(e.getMessage());
		}catch (Exception e) {
			log.error("Error in GetPinTuGift: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	
	
}
