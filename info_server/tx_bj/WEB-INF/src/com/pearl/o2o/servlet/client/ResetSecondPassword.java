package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.SecondPasswordStatus;
import com.pearl.o2o.utils.StringUtil;

public class ResetSecondPassword extends BaseClientServlet {

	private static final long serialVersionUID = -69155512930083756L;
	private static Logger log = LoggerFactory.getLogger(ResetSecondPassword.class.getName());
	private String[] paramNames  = { "pid","oldpassword","newpassword"};
	
	@Override
	protected String innerService(String... args) {
		
		if(!args[0].matches("^\\d+$")&&!args[1].matches("^[A-Za-z0-9]+$")
				&&!args[2].matches("^[A-Za-z0-9]+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		int playerId = StringUtil.toInt(args[0]) ;
		String oldPassword = args[1];
		String newPassword =args[2];
		try {
			String md5OldPass=MD5Util.md5(oldPassword);
			Player player=getService.getPlayerById(playerId);
			
			if(player.getHasSecondPassword()!=0 && md5OldPass.endsWith(player.getSecondPassword())){
				String md5NewPass=MD5Util.md5(newPassword);
				player.setHasSecondPassword(1);
				player.setSecondPassword(md5NewPass);
				
				updateService.updatePlayerInfoOnly(player);
			}else{
				log.warn("Incorrect input for reset second password for player "+playerId);
				return SecondPasswordStatus.FAIL;
			}
			
		} catch (Exception e) {
			log.error("Error happened when set second passowrd "+e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	
		return SecondPasswordStatus.SUCCESS;
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
