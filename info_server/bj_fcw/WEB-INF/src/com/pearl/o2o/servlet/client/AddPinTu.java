package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class AddPinTu extends BaseClientServlet {
	
	private static final long serialVersionUID = 5055998052477475279L;
	private static final String[] paramNames = { "pid","index" };
	private static Logger logger = LoggerFactory.getLogger(AddPinTu.class);
	/**
	 * 开启次数 
	 */
	private static final int TOTAL_CHANCE = Constants.PLAYER_PT_TOTAL_CHANCE;
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int index = StringUtil.toInt(args[1]);
			String key = Constants.PLAYER_PT_FLAG_KEY + playerId;
			String playerPinTuFlag = nosqlService.getNosql().get(key);
			if(index>=Constants.PLAYER_PT_PRI_FLAGS.length()||index<0){
				logger.warn("AddPinTu/" + Constants.RPC_PARAM_ERROR_LOG + ":\tindex="+index);
				return Converter.error(ExceptionMessage.PARAM_ERROR_MSG);
			}
			if(playerPinTuFlag==null||playerPinTuFlag.length()!=Constants.PLAYER_PT_PRI_FLAGS.length()+1||!playerPinTuFlag.matches("[01]*[0-" + TOTAL_CHANCE + "]")){
				playerPinTuFlag = Constants.PLAYER_PT_PRI_FLAGS + TOTAL_CHANCE;
			}
			String errorCode = null;
//			OnlineAward misticAward = null;
			int chance = Integer.parseInt(String.valueOf(playerPinTuFlag.charAt(playerPinTuFlag.length()-1)));
			if(chance <= 0){//判断是否有剩余机会
				errorCode = "0";
			}else if(playerPinTuFlag.charAt(index)=='1'){
				errorCode = "1";
			}else{
				playerPinTuFlag = StringUtil.replaceByIndex(playerPinTuFlag, index, '1');
				playerPinTuFlag = StringUtil.replaceByIndex(playerPinTuFlag, playerPinTuFlag.length()-1,(char)(chance-1 + '0'));
				ServiceLocator.getLuckyPackageLog.info("addPintu:\t" + playerId + "\t" + index);
//				if(Constants.PLAYER_PT_CMT_FLAGS.equals(playerPinTuFlag.substring(0, playerPinTuFlag.length()-1))){
//					misticAward = createService.completePinTu(playerId);
//				}else{
					nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_KEY+ playerId, playerPinTuFlag);
//				}
			}
			if(errorCode==null){
				return "error=nil";
			}
			return "error="+errorCode;
		}catch(BaseException e){
			logger.warn("BaseException in AddPinTu :" ,e);
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			logger.error("Error in AddPinTu: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
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
