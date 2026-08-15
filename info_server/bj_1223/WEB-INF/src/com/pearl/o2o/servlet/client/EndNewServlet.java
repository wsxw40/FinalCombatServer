package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class EndNewServlet extends BaseClientServlet {
	private static final long serialVersionUID = -1402479052917824587L;
	static Logger log = LoggerFactory.getLogger(EndNewServlet.class.getName());
	private String[] paramNames={"uid","pid"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			Player player=getService.getPlayerById(playerId);
			player.setIsNew(1);
			updateService.updatePlayerInfo(player);
			return Converter.commonFeedback(null);
		}catch(Exception e){
			log.warn("Error in C_EndNewServlet: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
