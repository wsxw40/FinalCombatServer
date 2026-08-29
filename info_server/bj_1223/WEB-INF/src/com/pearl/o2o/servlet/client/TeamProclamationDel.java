
package com.pearl.o2o.servlet.client;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerLocation;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;

/**
 * @author 
 */
public class TeamProclamationDel extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamProclamationDel.class.getName());
	private static final String[] paramNames = {"tid","tpid","pid"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int tid = StringUtil.toInt(args[0]);//战队id
			int tpid = StringUtil.toInt(args[1]);//公告id
			int playerid=StringUtil.toInt(args[2]);//玩家id
			TeamProclamation teamProclamation = getService.getTeamProclamationDao().getTeamProclamationById(tid, tpid);
			PlayerTeam playTeam= getService.getPlayerTeamDao().getByPlayerId(playerid);
			if(playTeam!=null){
				int  job= playTeam.getJob();
				if(job==TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()||job==TeamConstants.TEAMJOB.TEAM_OFFICER.getValue()){
					if(teamProclamation!=null){
						getService.getTeamProclamationDao().deleteTeam(teamProclamation);
						List<PlayerTeam>  list= getService.getPlayerTeamByTeamIdSimple(tid);
						for(Iterator<PlayerTeam> it=list.iterator();it.hasNext();){
							PlayerTeam pt= it.next();
							  PlayerLocation location=mcc.get(CacheUtil.oPlayerLocation(pt.getPlayerId()),Constants.CACHE_TIMEOUT);
								if (location != null) {
									soClient.puchCMDtoClient(getService.getPlayerDao().getPlayerById(pt.getPlayerId()).getName(), Converter.needRefreshProclamation(Constants.TEAM_CMD_DEL_PROCLAMATION));
								}
						}
						return "result = 1";
					}else{
						return "result = 0";
					}
				}else{
					logger.warn("删除战队公告失败，没有权限");
					return "result=0";
				}
			}else{
				logger.warn("删除战队公告失败");
				return "result=0";
			}
//			Team team=getService.getTeamById(tpId);
//			int currentNum = getService.getPlayerTeamByTeamIdSimple(tpId).size();
//			return Converter.team(team, currentNum);
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getTeamLockKey(StringUtil.toInt(paramNames[0]));
	}
}
