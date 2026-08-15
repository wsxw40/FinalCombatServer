package com.pearl.o2o.servlet.client;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.SecondPasswordStatus;
import com.pearl.o2o.utils.StringUtil;

public class ForgetSecondPassword extends BaseClientServlet {

	private static final long serialVersionUID = -4761305309187619954L;
	private static Logger log = LoggerFactory.getLogger(ForgetSecondPassword.class.getName());
	private String[] paramNames  = { "pid"};
	
	@Override
	protected String innerService(String... args) {
		
		if(!args[0].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		int playerId = StringUtil.toInt(args[0]) ;
	
		try {
			
			Player player=getService.getPlayerById(playerId);
			Calendar calendar=Calendar.getInstance();
			
			if(player.getHasSecondPassword()==SecondPasswordStatus.EMPTY){
				int leftSeconds=SecondPasswordStatus.THREE_DAY-((int)(calendar.getTimeInMillis()/1000)-player.getApplyResetTime());
				if(leftSeconds>0){
					return SecondPasswordStatus.HAD_APPLY_EMPTY+leftSeconds;
				}
				
			}else{
				player.setHasSecondPassword(SecondPasswordStatus.EMPTY);
				player.setApplyResetTime((int)(calendar.getTimeInMillis()/1000));
				updateService.updatePlayerInfoOnly(player);
			}
		
		} catch (Exception e) {
			log.error("Error happened when EMPTY second passowrd "+e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	
		return SecondPasswordStatus.SUCCESS;
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
