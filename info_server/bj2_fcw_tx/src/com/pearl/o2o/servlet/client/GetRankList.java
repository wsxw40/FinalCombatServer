package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class GetRankList extends BaseClientServlet {
	private static final long serialVersionUID = -1034688895474825054L;
	private static Logger log = LoggerFactory.getLogger(GetRankList.class.getName());
	private static final String[] paramNames = {};
	
	protected String innerService(String... args) {
		try {
			String strkey=CacheUtil.sRankList();
			String result=mcc.get(strkey,Constants.CACHE_TIMEOUT);
			if(result==null){
				result = getService.getRankList();
				mcc.set(strkey, Constants.CACHE_ITEM_TIMEOUT, result,Constants.CACHE_TIMEOUT);
			}
			return result;
		} catch (Exception e) {
			log.warn("Error in GetRankList: " ,e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
