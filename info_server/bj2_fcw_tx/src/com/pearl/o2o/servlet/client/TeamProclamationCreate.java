
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.service.TeamService;
import com.pearl.o2o.utils.CacheUtil;
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
 * @author 
 */
public class TeamProclamationCreate extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamProclamationCreate.class.getName());
	private static final String[] paramNames = {"ptid","tid","content","pid",};
	/**
	 * 增加战队公告
	 */
	protected String innerService(String... args) {
		try{
			int playerTeamId = StringUtil.toInt(args[0]);
			final int teamId = StringUtil.toInt(args[1]);
			String content = args[2];
			int playerId =StringUtil.toInt(args[3]) ;
			PlayerTeam playTeam= getService.getPlayerTeamDao().getPlayerTeam(teamId, playerTeamId);
			
			if(playTeam!=null){
				int  job= playTeam.getJob();
				if(job==TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()||job==TeamConstants.TEAMJOB.TEAM_OFFICER.getValue()){
					content=StringUtil.escape(content);
					TeamProclamation teamProclamation = new TeamProclamation();
					teamProclamation.setPlayerId(playerId);
					teamProclamation.setTeamId(teamId);
					teamProclamation.setContent(content);
					teamProclamation.setCreateTime(System.currentTimeMillis());
					String proContentStr = "";
					boolean isError = false;
					if (StringUtil.isEmptyString(content)) {
						isError=true;
						proContentStr += ExceptionMessage.EMPTY_STR+"\\n";
					}
					if(!KeywordFilterUtil.isLegalInput(content)){
						isError=true;
						proContentStr+=ExceptionMessage.TEAM__INVALID_PROCLAMATION+"\\n";
					}
					//FIXME
					if (content != null && content.length() > 30) {
						isError=true;
						proContentStr+=ExceptionMessage.TOO_LONG+"\\n";
					}
					if(isError){
						return Converter.error(proContentStr.substring(0, proContentStr.length()-2));
					}else{
						Map<Integer, TeamProclamation> map= getService.getTeamProclamationDao().getTeamProclamationByTeamId(teamId);
						if(map.size()>=20){
							TeamService t=new TeamService();
							int smallestId=t.getSmallestTeamRecordId(map);
							getService.getTeamProclamationDao().deleteTeam(teamService.getTeamProclamationDao().getTeamProclamationById(teamId, smallestId));
							getService.getTeamProclamationDao().createTeam(teamProclamation);
						}else{
							getService.getTeamProclamationDao().createTeam(teamProclamation);
							
						}
						final Runnable task = new Runnable() {
							@Override
							public void run() {
								try {
									TeamUtils.pushCMDToOnlineTeamPlayer(teamId,Constants.TEAM_CMD_PROCLAMATION);
								} catch (Exception e) {
									ServiceLocator.stageClearLog.warn("error happend during TeamProclamationCreate exception is " , e);
								}
							}
						};
						ServiceLocator.asynTakService.submit(task);
					}
					return "result = 1";
				}else{
					logger.warn(ExceptionMessage.TEAM_PROCLAMATION_CRATE_HAS_NO_RIGHT);
					return "result = 0";
				}
			}else{
				logger.warn(ExceptionMessage.TEAM_PROCLAMATION_CRATE_HAS_NO_RIGHT);
				return "result =0";
			}
		
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_TEAM);
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
