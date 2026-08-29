package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
/**
 * @author WengJie
 *
 */
public class FuzzyGetPlayerList extends BaseClientServlet {
	private static final long serialVersionUID = -1225837047769195165L;
	static Logger log = LoggerFactory.getLogger(FuzzyGetPlayerList.class.getName());
	private String[] paramNames={"uid","name","page_id"};

	@Override
	protected String innerService(String... args) {
		try{
			//int userId = StringUtil.toInt(args[0]);
			String name = args[1];
			if (StringUtil.isEmptyString(name)) {
				return Converter.error(ExceptionMessage.EMPTY_STR);
			}
			name = StringUtil.escapeIndex(name);
			int page = StringUtil.toInt(args[2]);
			List<Player> players=getService.fuzzyGetPlayerByName(name, page);
			for(Player player:players){
				PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(player.getId()),Constants.CACHE_TIMEOUT);
				if (location != null) {
					player.setOnline(1);
				}
			}
			return  Converter.playerPageList((int)Math.ceil(getService.fuzzyCountPlayer(name)/(double)Constants.FRIEND_PAGE_SIZE),
					players);		
		}
		catch(Exception e){
			log.warn("Error in GetPlayerList: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
