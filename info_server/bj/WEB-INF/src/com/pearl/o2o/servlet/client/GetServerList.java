package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetServerList extends BaseClientServlet {
	private static final long serialVersionUID = -7772792623360674823L;
	static Logger log = LoggerFactory.getLogger(GetServerList.class.getName());	
	private static final String[] paramNames = {};
	
	public GetServerList() {
		super();
	}
	
	protected String innerService(String... args) {
		/*try{		
			String result = getService.getServerList();
			return result;
		}
		catch(Exception e){
			log.error("Error in GetServerList: " + e);
			return Converter.error("系统出现异常错误，请联系GM");
		}	*/	
		return "";
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
