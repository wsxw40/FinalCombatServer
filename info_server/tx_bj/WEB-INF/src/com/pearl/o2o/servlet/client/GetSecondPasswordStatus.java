package com.pearl.o2o.servlet.client;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.SecondPasswordStatus;
import com.pearl.o2o.utils.StringUtil;

public class GetSecondPasswordStatus extends BaseClientServlet {

	private static final long serialVersionUID = -4761305309187619954L;
	private static Logger log = LoggerFactory.getLogger(GetSecondPasswordStatus.class.getName());
	private String[] paramNames  = { "pid"};
	
	@Override
	protected String innerService(String... args) {
		
		if(!args[0].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		int playerId = StringUtil.toInt(args[0]) ;
	
		try {
			Player player=getService.getPlayerById(playerId);
			if(player!=null){
				Integer status = player.getHasSecondPassword();
				if(status==null)
				{
					status=0;
				}
				
				if(status==SecondPasswordStatus.EMPTY){
					Calendar now=Calendar.getInstance();
					int leftSeconds=SecondPasswordStatus.THREE_DAY-((int)(now.getTimeInMillis()/1000)- player.getApplyResetTime());
					if(leftSeconds<=0){
						player.setHasSecondPassword(SecondPasswordStatus.UNSET);
						player.setSecondPassword(null);
						player.setApplyResetTime(0);
						updateService.updatePlayerInfoOnly(player);
						
						return Converter.GetSecondPasswordStatus(SecondPasswordStatus.UNSET, 0);
					}else{
						return Converter.GetSecondPasswordStatus(status, leftSeconds);
					}
				}
				
				return Converter.GetSecondPasswordStatus(status, 0);
				
			}
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			
		} catch (Exception e) {
			log.error("Error happened when get second passowrd status"+e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
