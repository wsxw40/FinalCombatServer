package com.pearl.o2o.servlet.client;

import java.util.List;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerAvatar extends BaseClientServlet {
	private static final long serialVersionUID = 6628659867111484527L;
	private static Logger log = LoggerFactory.getLogger(GetPlayerAvatar.class.getName());	
	private static final String[] paramNames = {"cid","name"};
	
	
	protected String innerService(String... args) {
		try{					
			int playerId = StringUtil.toInt(args[0]);
			String cName = args[1];
			Player player = null;
			if (!StringUtil.isEmptyString(cName)){
				player = getService.getPlayerByName(cName);
				if (player == null) {
					return Converter.error(ExceptionMessage.PALERY_NOT_EXIST);
				}
			}else{
				player = getService.getPlayerById(playerId);
			}
			if(player==null){
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
//			List<PlayerItem> cosT = getService.getCostumePackList(playerId, 1, 0);
//			List<PlayerItem> cosP = getService.getCostumePackList(playerId, 1, 1);
//			List<PlayerItem> pack = getService.getWeaponPackList(playerId, 1);
//			
//			player.putOnCostume(0, cosT);
//			player.putOnCostume(1, cosP);
			
			PlayerItem weapon = null;
//			for(PlayerItem pi : pack){
//				if(pi!=null && pi.getId()!=0){
//					weapon = pi;
//					break;
//				}
//			}
			String result = Converter.playerAvatar(player, weapon);			
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

