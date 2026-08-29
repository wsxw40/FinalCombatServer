package com.pearl.o2o.servlet.client;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.StringUtil;

public class DailyNumCreate extends BaseClientServlet {

	private static final long serialVersionUID = -4833232325894162576L;
	private static Logger log = LoggerFactory.getLogger("dailynum");
	private static final String[] paramNames = { "pid", "num" };

	@Override
	protected String innerService(String... args) {
		try {
			Calendar date = new GregorianCalendar();
			date.add(GregorianCalendar.DAY_OF_MONTH, 1);
			String tomDateStr = CommonUtil.dateFormatDate.format(date.getTime());
			int playerId = StringUtil.toInt(args[0]);

			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			boolean isVip = player.getIsVip()>0;
//			System.out.println("args[0]="+args[0]+" && args[1]="+args[1]);

			// vip 玩家，可猜3个数字
			if (isVip) {

				// 获得玩家已猜过的数字
				String playerNums = nosqlService.getPlayerNums(playerId, 2, tomDateStr);

				// 获得玩家本次猜的数字，多个用逗号分隔
				String nums = args[1];
//				nums = "1,2,3";
				String[] numStr = nums.split(",");
				int[] numArray = new int[numStr.length];
				for (int i = 0; i < numArray.length; i++) {
					int num = numArray[i] = StringUtil.toInt(numStr[i]);
					// 判断猜的数字，每个都介于 1-9 之间
					if (num < 0 || num > Constants.DAILY_GUS_MAX_NUM) {
						log.warn("DailyNumCreate/" + Constants.RPC_PARAM_ERROR_LOG + ":\t num=" + num);
						return Converter.warn(ExceptionMessage.PARAM_ERROR_MSG);
					}
				}
				// 是否猜过数字
				if (playerNums.equals(nums)) {
					return Converter.commonFeedback(ExceptionMessage.DAILY_GUESS_ALREADY);
				}
				createService.dailyNumsPay(playerId, Constants.DAILY_GUS_NUM_COST, nums, tomDateStr);
				log.info("DailyNumsCreate/PlayerGus:\t" + playerId + "\t " + nums + "\t" + tomDateStr);
			}
			// 非 vip 玩家，只能猜一个
			else {
				int num = StringUtil.toInt(args[1]);
				// 判断是否已经猜过
				int myTomrrowNum = nosqlService.getPlayerNum(playerId, 2, tomDateStr);
				if (myTomrrowNum == num) {
					return Converter.commonFeedback(ExceptionMessage.DAILY_GUESS_ALREADY);
				}
				createService.dailyNumPay(playerId, Constants.DAILY_GUS_NUM_COST, num, tomDateStr);
				log.info("DailyNumCreate/PlayerGus:\t" + playerId + "\t " + num + "\t" + tomDateStr);
			}
			// 成长任务13：首次猜数字
			updateService.updatePlayerGrowthMission(player, GrowthMissionType.FIRST_GUSNUM);
			String result = Converter.commonFeedback(null);
//			System.out.println("result="+result);
			return result;
		} catch (BaseException e) {
			log.warn("DailyNumCreate/Warn:\t", e);
			return Converter.commonFeedback(e.getMessage());
		} catch (Exception e) {
			log.error("DailyNumCreate/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
