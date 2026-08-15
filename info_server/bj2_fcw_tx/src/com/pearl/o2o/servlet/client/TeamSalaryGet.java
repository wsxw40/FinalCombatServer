package com.pearl.o2o.servlet.client;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants;
import com.pearl.o2o.utils.StringUtil;

public class TeamSalaryGet extends BaseClientServlet{
	
	private static final long serialVersionUID = 2028735459339006028L;
	private static Logger log = LoggerFactory.getLogger("dailycheck");
	private static final String[] paramNames = {"pid","tid"};
	
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int teamId = StringUtil.toInt(args[1]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			String dateStr = CommonUtil.dateFormatDate.format(new Date());
			String msg = null;
			int isSalary = nosqlService.isTeamSalary(playerId);
	
		
			
			int salary= teamService.getTeamSalary(teamId,playerId);
			
			int medel= Constants.SPECIAL_ITEM_IIDS.MEDEL.getValue();
			SysItem si=getService.getSysItemByIID(medel,Constants.DEFAULT_ITEM_TYPE).get(0);
			Payment pm=new Payment();
			pm.setUnit(salary);
			pm.setUnitType(Constants.DEFAULT_NUMBASE_TYPE);
//			List<OnlineAward> dailyAwards = getService.getDailyCheckGifts(Constants.DAILY_CHECK_AWARD_LEVEL.DAILY_GIFT.getValue());
			if(isSalary==1){
				return Converter.sendTeamSalary(ExceptionMessage.TEAM_SALARY_ALREADY_MSG, si,isSalary,pm);
			}else{
				msg=CommonMsg.TEAM_SALARY_SUCCESS_MSG;
			}
			createService.awardToPlayer(player, si, pm, null, Constants.BOOLEAN_YES);
			nosqlService.saveTeamSalary(playerId, dateStr);
			
			int chipNum=getService.getMedolNumByPlayerId(playerId);
			if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
				nosqlService.addXunleiLog("7.4"
						+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
						+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
						+ Constants.XUNLEI_LOG_DELIMITER + 1
						+ Constants.XUNLEI_LOG_DELIMITER + salary
						+ Constants.XUNLEI_LOG_DELIMITER + chipNum
						+ Constants.XUNLEI_LOG_DELIMITER+ 997
						+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
						);
			}
			
			return Converter.sendTeamSalary(msg, si,isSalary,pm);
			
		} catch (BaseException e) {
			return Converter.warn(e.getMessage());
		} catch (Exception e) {
			log.error(ExceptionMessage.TEAM_SALARY_FAILED_MSG , e);
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
