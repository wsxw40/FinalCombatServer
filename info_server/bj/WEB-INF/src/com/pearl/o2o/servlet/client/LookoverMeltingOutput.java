package com.pearl.o2o.servlet.client;

import com.google.common.base.Preconditions;
import com.pearl.o2o.pojo.MeltingReslut;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class LookoverMeltingOutput extends BaseClientServlet {
	private static final long serialVersionUID = 1519975207181077962L;
	private static final String[] paramNames = { "pid","index" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int index = StringUtil.toInt(args[1]);
			Preconditions.checkPositionIndex(index, 3);

			MeltingReslut lookoverMeltingOutput = getService.lookoverMeltingOutput(playerId, index);
			
			return Converter.getMeltingResult(lookoverMeltingOutput,0);
		} catch (Exception e) {
			ServiceLocator.meltingLog.warn("Error in GetOnlineAward: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}
