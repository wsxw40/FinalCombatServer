package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetMessageItem extends BaseClientServlet {
	private static final long serialVersionUID = -6885918862498265347L;
	static Logger log=LoggerFactory.getLogger(GetMessageItem.class.getName());
	private String[] paramNames={"uid","cid","mid","type"};
	@Override
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId= StringUtil.toInt(args[1]);
			int messageId= StringUtil.toInt(args[2]);
			int action= StringUtil.toInt(args[3]);
			createService.detachMessageItem( playerId, messageId,action);
			return Converter.createMessage(Constants.NUM_ZERO,null);
		}
		catch (BaseException be) {
			log.debug("Exception in GetMessageItem"+be.getMessage());
			return Converter.createMessage(Constants.NUM_ONE,be.getMessage());	
		}
		catch (Exception e) {
			log.warn("Exception in GetMessageItem",e);
			return Converter.createMessage(Constants.NUM_ONE,e.getMessage());	
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
