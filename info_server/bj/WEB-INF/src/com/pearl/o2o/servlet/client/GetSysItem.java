package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetSysItem extends BaseClientServlet{
	private static final long serialVersionUID = 6557039424602831755L;
	private static Logger log = LoggerFactory.getLogger(GetSysItem.class.getName());
	private static final String[] paramNames = {"uid","cid","t","st","id"};
	
	
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int subType = StringUtil.toInt(args[3]);
			int itemId = StringUtil.toInt(args[4]);
			SysItem	sysItem = getService.getSysItemByItemId(itemId);
			return Converter.sysItem(sysItem);
		}
		catch (Exception e) {
			log.warn("Exception in GetSysItemWeapon", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}


	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
