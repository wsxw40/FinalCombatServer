package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DeleteItemMod extends BaseClientServlet {
	private static final long serialVersionUID = -7126227129499698512L;
	static Logger log = LoggerFactory.getLogger(DeleteItemMod.class.getName());
	private String[] paramNames={"uid","cid","iid","t","st"};
	
	public DeleteItemMod() {
		super();
	}
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int partId=StringUtil.toInt(args[2]);
			int type=StringUtil.toInt(args[3]);
			int subType=StringUtil.toInt(args[4]);
			
//			deleteService.deleteItemMod(userId,playerId, partId,type,subType);			
			return Converter.createPackEquipment(null);
		}
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
