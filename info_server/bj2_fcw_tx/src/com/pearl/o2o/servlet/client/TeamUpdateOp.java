
package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;
import com.pearl.o2o.utils.TeamUtils;

/**
 * @author Timon
 */
public class TeamUpdateOp extends BaseClientServlet {
	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger=LoggerFactory.getLogger(TeamUpdateOp.class.getName());
	private static final String[] paramNames = {"pid","tid","logo","rank","description"};
	
	protected String innerService(String... args) {
		try{
			int playerId = Integer.valueOf(args[0]);
			final int teamId = Integer.valueOf(args[1]);
			String logo =args[2];
			int rank = Integer.valueOf(args[3]);
			String description = args[4];
			String descriptionStr = "";
			boolean isError = false;
//			if (StringUtil.isEmptyString(description)) {
//				isError=true;
//				descriptionStr += ExceptionMessage.EMPTY_STR+"\\n";
//			}
			if(!KeywordFilterUtil.isLegalInput(description)){
				isError=true;
				descriptionStr+=ExceptionMessage.TEAM_DESCRIPTION_INVALID+"\\n";
			}
			//FIXME
			if (description != null && description.length() > 140) {
				isError=true;
				descriptionStr+=ExceptionMessage.TOO_LONG+"\\n";
			}
			if(isError){
				return Converter.error(descriptionStr.substring(0, descriptionStr.length()-2));
			}
			PlayerTeam playerTeam = getService.getPlayerTeamByPlayerId(playerId);

			if(playerTeam != null && playerTeam.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()){
				updateService.updateTeamInfo(logo, rank,description, teamId);
				final Runnable task = new Runnable() {
					@Override
					public void run() {
						try {
							TeamUtils.pushCMDToOnlineTeamPlayer(teamId,Constants.TEAM_CMD_REMARK);
						} catch (Exception e) {
							ServiceLocator.stageClearLog.warn("error happend during TeamProclamationCreate exception is " , e);
						}
					}
				};
				ServiceLocator.asynTakService.submit(task);
			}else{
				return Converter.error(ExceptionMessage.TEAM_OP_FAIL);
			}
			// 成长任务24：加入战队
			//updateService.updatePlayerGrowthMission(player, Portal.FIRSTRECHARGE, Portal.FIRSTRECHARGE.getAction());
			return "error=nil";
		}catch (BaseException e) {
			return Converter.error(e.getMessage());
		}
		catch (Exception e) {
			logger.warn("team update op fail:" + e.getMessage(),e);
			if (StringUtil.isEmptyString(e.getMessage())) {
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}else {
				return Converter.error(e.getMessage());
			}
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getTeamLockKey(StringUtil.toInt(paramNames[1]));
	}
}