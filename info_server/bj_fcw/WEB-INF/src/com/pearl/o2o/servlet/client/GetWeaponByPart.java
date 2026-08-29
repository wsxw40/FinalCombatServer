package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class GetWeaponByPart extends BaseClientServlet {

	private static final long serialVersionUID = 1025599704114958903L;
	private static Logger log = LoggerFactory.getLogger(GetWeaponByPart.class.getName());
	private static final String[] paramNames = {"cid","id","t","st","page"};
	
	
	protected String innerService(String... args) {
		try{
//			int playerId = StringUtil.toInt(args[0]);
//			int partId = StringUtil.toInt(args[1]);
//			int type = StringUtil.toInt(args[2]);
//			int subType = StringUtil.toInt(args[3]);
			
			return "";
		}catch (Exception e) {
			log.warn("Exception happen in GetWeaponByPart",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}


	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
