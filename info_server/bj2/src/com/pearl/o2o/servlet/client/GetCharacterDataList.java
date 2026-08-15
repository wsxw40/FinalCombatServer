package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.CharacterData;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetCharacterDataList extends BaseClientServlet {
	private static final long serialVersionUID = -7782733693520784343L;
	private static Logger log = LoggerFactory.getLogger(GetCharacterDataList.class.getName());
	private static final String[] paramNames = {"pid"};
	
	protected String innerService(String... args) {
		String result;
		try{
			//int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(args[0]);
			List<CharacterData> characterDataList=getService.getCharacterDataService(playerId);
			result=Converter.playerCharDataList(characterDataList);
			log.debug(result);
			return result;
		}
		catch (Exception e) {
			log.warn("Exception happen in GetCharacterDataList",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
