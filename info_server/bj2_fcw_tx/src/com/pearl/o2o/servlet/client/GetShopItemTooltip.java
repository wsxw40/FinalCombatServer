package com.pearl.o2o.servlet.client;

import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.StringUtil;

public class GetShopItemTooltip extends BaseClientServlet {
	private static final long serialVersionUID = -7822352787246516585L;
	private static final String[] paramNames = {"type","subtype","siid"};
	
	protected String innerService(String... args) {
		String result;
		try{
			int type = StringUtil.toInt(args[0]);
			int subType = StringUtil.toInt(args[1]);
			int itemId = StringUtil.toInt(args[2]);
			result=mcc.get(CacheUtil.sSysItemTooltip(itemId),Constants.CACHE_TIMEOUT);
			if(result==null){
//				result=getService.getSysItemToolTip(type,subType,itemId);
//				mcc.set(CacheUtil.sSysItemTooltip(itemId), Constants.CACHE_ITEM_TIMEOUT, result);
			}
			return result;
		}catch (Exception e) {
			return "";
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
