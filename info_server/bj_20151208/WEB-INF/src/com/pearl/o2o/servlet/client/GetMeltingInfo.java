package com.pearl.o2o.servlet.client;

import com.pearl.o2o.pojo.PlayerMelting;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetMeltingInfo extends BaseClientServlet {
	private static final long serialVersionUID = 1519975207181077962L;
	private static final String[] paramNames = { "pid" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			PlayerMelting melting = getService.getPlayerMelting(playerId, false);
			return Converter.getMeltingInfo(melting);
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

	public static void main(String[] args) {
		double mv = 1000;
		// =IF(C1<500,0.3323*LN(C1)-2.0776,0.0008*C1-0.4)
		double value1 = mv < 500 ? 0.3323 * Math.log(mv) - 2.0776 : 0.0008 * mv - 0.4;
		double value2 = Math.pow(1, value1);
		double sumValue2 = Math.pow(1, value1);
		sumValue2 += Math.pow(3, value1);
		sumValue2 += Math.pow(10, value1);
		sumValue2 += Math.pow(30, value1);
		sumValue2 += Math.pow(100, value1);
		sumValue2 += Math.pow(300, value1);
		
		int value3 = (int) (15000*value2/sumValue2);
		
		System.out.println(value3);
	}
}
