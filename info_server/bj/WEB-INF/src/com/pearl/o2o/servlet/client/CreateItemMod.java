package com.pearl.o2o.servlet.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CreateItemMod extends BaseClientServlet {
	private static final long serialVersionUID = -6515353905004343636L;
	static Logger log = LoggerFactory.getLogger(CreateItemMod.class.getName());
	private String[] paramNames={"uid","cid","pid","array"};
	public CreateItemMod() {
		super();
	}
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int weaponId = StringUtil.toInt(args[2]);
			String array = args[3];
			String dec="";
			return Converter.createItemMod(null,dec);
		}
//		catch (BaseException be) {
//			log.error("Exception in CreateItemMod",be);
//			return Converter.createItemMod(be.getMessage(),"");	
//		}
		catch (Exception e) {
			log.warn("Exception in CreateItemMod",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);	
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
