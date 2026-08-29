package com.pearl.o2o.servlet.client;

import com.pearl.o2o.pojo.GrowthMissionVo;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetGrowthGift extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static final String[] paramNames = { "pid" ,"mid","time"};

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int missionId = StringUtil.toInt(args[1]);
			int time = StringUtil.toInt(args[2]);
			GrowthMissionVo growthMission = getService.getPlayerGrowthMissionGift(getService.getPlayerById(playerId), missionId,time);
			if(null == growthMission){
				return Converter.error(ExceptionMessage.MISSION_NOT_FIND);
			}
			return Converter.getGrowthMissionGift(growthMission);
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.warn("GetGrowthGift Exception\n" ,e);
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
