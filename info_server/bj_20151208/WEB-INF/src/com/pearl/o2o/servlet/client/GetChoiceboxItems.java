package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetChoiceboxItems extends BaseClientServlet {

	private static final long serialVersionUID = -7752080488786503013L;
	private static Logger log = LoggerFactory.getLogger(GetChoiceboxItems.class.getName());
	private String[] paramNames = {"index"};
	
	@Override
	protected String innerService(String... args) {
		try {
			int index = StringUtil.toInt(args[0]);
			int sysitemId = Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[index][0];
			SysItem si = getService.getSysItemByItemId(sysitemId);
			return createService.useChoiceBox(si);
		}catch (BaseException be) {
			log.warn("GetCompeteAwardItems:\t",be);
			return Converter.warn(be.getMessage());
		} 
		catch (Exception e) {
			log.error("GetCompeteAwardItems:\t",e );
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	

}
