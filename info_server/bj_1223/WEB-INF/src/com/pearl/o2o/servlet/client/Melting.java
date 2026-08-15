package com.pearl.o2o.servlet.client;

import java.util.ArrayList;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.MeltingReslut;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class Melting extends BaseClientServlet {
	private static final long serialVersionUID = 1519975207181077962L;
	private static final String[] paramNames = { "pid","meltingInputs","qualityProps" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			ArrayList<Integer> meltingInputs = Lists.newArrayList(Iterables.transform(MeltingConstants.splitterByColon.split(args[1]), MeltingConstants.functionStrToInt));
			if(null == meltingInputs || meltingInputs.isEmpty()){
				return Converter.error(MeltingConstants.Melting_Empty);
			}
			int qualityPropId = MeltingConstants.functionStrToInt.apply(args[2]);
			MeltingReslut meltingResult = updateService.meltingPlayerItem(playerId,meltingInputs,qualityPropId);
			return Converter.getMeltingResult(meltingResult);
		} catch (BaseException be) {
			ServiceLocator.meltingLog.error("Error in GetOnlineAward: ", be);
			return Converter.error(be.getMessage());
		} catch (Exception e) {
			ServiceLocator.meltingLog.error("Error in GetOnlineAward: ", e);
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
