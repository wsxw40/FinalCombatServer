package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;



public class ChangeNamePer extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(ChangeNamePer.class.getName());
	private String[] paramNames={"pid","newname"};
	public ChangeNamePer(){
		super();
	}	
	@Override
	protected String innerService(String... args) {
//		Player player = null;
		try{
//			int playerId = StringUtil.toInt(args[0]);
			String newName = args[1];
			Player temp = getService.getPlayerByName(newName);
			if(temp != null){
				return Converter.commonFeedback(ExceptionMessage.NAME_EXIST);
			}
			if (StringUtil.filter(newName)) {
				return Converter.commonFeedback(
						ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
			}
			if (!KeywordFilterUtil.isLegalInput(newName)) {
				return Converter.commonFeedback(
						ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
			}
			//FIXME
			if(newName == null || "".equals(newName)){
				return Converter.commonFeedback(ExceptionMessage.NAME_CANT_NULL);
			} else if(newName.length() > StringUtil.toInt(getService.getSysConfig().get("playername.maxlength"))){
				return Converter.commonFeedback(ExceptionMessage.TOO_LONG_CREATE_PLAYER);
			} else if(newName.length() < StringUtil.toInt(getService.getSysConfig().get("playername.minlength"))){
				return Converter.commonFeedback(ExceptionMessage.TOO_SHORT_CREATE_PLAYER);
			}
			return Converter.commonFeedback(null);
		}
		catch(NotBuyEquipmentException be){
			return Converter.createPlayer(0,be.getMessage(),null);
		}
//		catch(BaseException be){
//			return Converter.createPlayer(0,null,be.getMessage());
//		}		
		catch(Exception e){
			log.warn("Error in CreatePlayer: ", e);
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
