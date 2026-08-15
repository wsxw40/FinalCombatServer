package com.pearl.o2o.servlet.client;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class GetSimplePlayerAvatar extends BaseClientServlet {
	private static final long serialVersionUID = 6628659867111484527L;
	private static Logger log = LoggerFactory.getLogger(GetSimplePlayerAvatar.class.getName());	
	private static final String[] paramNames = {"name"};
	
	protected String innerService(String... args) {
		try{					
			String cName = args[0];
			Player player = getService.getPlayerWithAvatarAndWeapon(cName);
			
			String result = Converter.simplePlayerAvatar(player);			
			return result;			
		}
		catch (Exception e) {
			log.warn("Exception in GetPlayer: ", e);
			if (e instanceof MemcachedException
					|| e instanceof TimeoutException) {
				return Converter.error("Memcached Not Started");
			}
			else{
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);				
			}
		}	
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}

