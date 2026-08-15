package com.pearl.o2o.servlet.client;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Server;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetServerRemark extends BaseClientServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = LoggerFactory.getLogger(GetServerRemark.class.getName());
	private static final String[] paramNames = { "pid", "t", "page"};

	@Override
	protected String innerService(String... args) {
		try {
			int type = StringUtil.toInt(args[0]);
			List<Server> serverList=getService.getServersList();
			String str = Converter.getServerRemark(serverList);
			return str;
		} catch (Exception e) {
			log.warn("Exception in GetServerRemark", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
