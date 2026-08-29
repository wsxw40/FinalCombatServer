package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.SecondPasswordStatus;
import com.pearl.o2o.utils.StringUtil;

public class SetCheckSecondPassword extends BaseClientServlet {

	private static final long serialVersionUID = -4144447552659106380L;
	private static Logger log = LoggerFactory.getLogger(SetCheckSecondPassword.class.getName());
	private String[] paramNames  = { "pid","password","type"};
	
	@Override
	protected String innerService(String... args) {
		
		if(!args[0].matches("^\\d+$")&&!args[1].matches("^[A-Za-z0-9]+$")
				&&!args[2].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		int playerId = StringUtil.toInt(args[0]) ;
		String password = args[1];
		int type=StringUtil.toInt(args[2]);  //0:设置密码;1:验证密码
	
		try {
			Player player=getService.getPlayerById(playerId);
			if(null!=player){
				if(type==SecondPasswordStatus.SET_PASSWORD){
					String md5Password=MD5Util.md5(password);
					player.setHasSecondPassword(1);
					player.setSecondPassword(md5Password);
					
					updateService.updatePlayerInfoOnly(player);
					
				}
				else if(type==SecondPasswordStatus.CHECK_PASSWORD){
					if((player.getHasSecondPassword()==SecondPasswordStatus.SET ||SecondPasswordStatus.EMPTY==player.getHasSecondPassword())
							&& player.getSecondPassword()!=null){
						String md5Password=MD5Util.md5(password);
						if(md5Password.equals(player.getSecondPassword())){
							if(SecondPasswordStatus.EMPTY==player.getHasSecondPassword()){
								player.setHasSecondPassword(SecondPasswordStatus.SET);	//如果之前是忘记状态，现在设置为已设置状态
								updateService.updatePlayerInfoOnly(player);
							}
							mcc.set(CacheUtil.sPlayerSPWIE(playerId), Constants.CACHE_TIMEOUT_DAY,1,Constants.CACHE_TIMEOUT);			//表示用户此处登录已经输过密码
	
							return SecondPasswordStatus.SUCCESS;
						}
						return SecondPasswordStatus.FAIL;
					}
				}else{
					return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
				}
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
