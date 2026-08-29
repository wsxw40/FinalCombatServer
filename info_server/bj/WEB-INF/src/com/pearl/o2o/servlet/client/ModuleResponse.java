package com.pearl.o2o.servlet.client;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.ModuleStatus;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class ModuleResponse extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static final String[] paramNames = { "pid" ,"num"};

	protected String innerService(String... args) {
//		ServiceLocator.growthMissionLog.debug("ModuleResponse " + Arrays.toString(args));
		try {
			int playerId = StringUtil.toInt(args[0]);
			int num = StringUtil.toInt(args[1]);
			
			Player player = getService.getSimplePlayerById(playerId);
			char[] tutorial = getService.getPlayerTutorial(playerId);
			
			if(tutorial[num] == ModuleStatus.FLASH.getCh()){
				tutorial[num] = ModuleStatus.OPEN.getCh();
			}else{
				return Converter.commonFeedback(null);
			}

			updateService.updatePlayerTutorial(player,tutorial);
			
			return Converter.commonFeedback(null);
		} catch (Exception e) {
			ServiceLocator.growthMissionErrLog.debug("ModuleResponse Exception" ,e);
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
