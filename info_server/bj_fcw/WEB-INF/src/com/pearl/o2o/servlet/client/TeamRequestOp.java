
package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;
/**
 * @author Timon
 */
public class TeamRequestOp extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamRequestOp.class.getName());
	private static final String[] paramNames = {"tid","pids","pid","action","job"};
	
	//action = join申请 | approve 批准| reject 拒绝| quit 退出|kick 开除 | handover 移交| appoint
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			String cidsStr = args[1];
			String cid = args[2];
			String [] cids = null;
			if(!StringUtil.isEmptyString(cidsStr)){
				cids = cidsStr.split(",");
			}
			String action = args[3];
			
			ServiceLocator.teamRequestOpLog.debug(LogUtils.JoinerByTab.join("args",args));
			
			PlayerTeam pt = getService.getPlayerTeamByPlayerId(Integer.valueOf(cid));
			// apply
			if (TeamConstants.TEAM_OPERATE_JOIN.equals(action)) {
				updateService.applyTeam(teamId, Integer.valueOf(cid));
				ServiceLocator.playerLog.info("player="+cid+" apply team="+teamId);
			}else if (TeamConstants.TEAM_OPERATE_APPROVE.equals(action)){
				if (pt == null || !(pt.getJob().equals(TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) || pt.getJob().equals(TeamConstants.TEAMJOB.TEAM_OFFICER.getValue()))) {
					throw new BaseException(ExceptionMessage.TEAM_OP_FAIL);
				}
				//没有选中队员
				if(null == cids || cids.length==0){
					throw new BaseException(ExceptionMessage.TEAM_KICK_OP_FAIL);
				}
				List<String> successNameList = new ArrayList<String>();
				List<String> sizeFailNameList = new ArrayList<String>();
				List<String> expireNameList = new ArrayList<String>();
				 
				updateService.approve(pt,teamId, cids, successNameList, sizeFailNameList, expireNameList);
				return Converter.teamApproveResult(successNameList, sizeFailNameList, expireNameList);
			}else if (TeamConstants.TEAM_OPERATE_REJECT.equals(action)) {
				if (pt == null || !(pt.getJob().equals(TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) || pt.getJob().equals(TeamConstants.TEAMJOB.TEAM_OFFICER.getValue()))) {
					throw new BaseException(ExceptionMessage.TEAM_OP_FAIL);
				}
				//没有选中队员
				if(null == cids || cids.length==0){
					throw new BaseException(ExceptionMessage.TEAM_KICK_OP_FAIL);
				}
				
				if(cids.length == 1 && Integer.parseInt(cids[0]) == -1){
					List<PlayerTeam> unapprovedMemberOverMaxNumber = getService.getUnapprovedMemberOverMaxNumber(teamId);
					cids = new String[unapprovedMemberOverMaxNumber.size()];
					for(int i = 0;i<unapprovedMemberOverMaxNumber.size();i++){
						cids[i] = unapprovedMemberOverMaxNumber.get(i).getPlayerId()+"";
					}
				}
				updateService.removePlayerTeam(teamId, cids);
				ServiceLocator.playerLog.info("player="+cidsStr+" reject by team="+teamId);
				final String[] rejectPlayerIds = cids;
				final Team t = getService.getTeam(teamId);
				Runnable task = new Runnable(){
					@Override
					public void run() {
						try {
							for (String failCid : rejectPlayerIds) {
								Player p;
								p = getService.getPlayerById(Integer.valueOf(failCid));
								PlayerTeam pt=getService.getPlayerTeamByPlayerId(Integer.valueOf(failCid));
								if(pt!=null&&Constants.BOOLEAN_NO.equals(pt.getApproved())&&pt.getTeamId()==t.getId()){
									messageService.sendRejectedJoinTeamNotifyMail(p, t);
								}
							}
						} catch (Exception e) {
							logger.warn("error in send reject mail to players");
						}
					}
				};
				ServiceLocator.asynTakService.submit(task);
			}else if (TeamConstants.TEAM_OPERATE_QUIT.equals(action)) {
				//检测是否输过二级密码
				if(!checkEnterSPW(Integer.valueOf(cid))){
					return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
				}
				PlayerTeam targetPlayerTeam = getService.getPlayerTeamByPlayerId(Integer.valueOf(cids[0]));
				if (pt == null || targetPlayerTeam ==null|| !pt.getTeamId().equals(teamId)){
					throw new BaseException(ExceptionMessage.TEAM_OP_FAIL);
				}
				if(targetPlayerTeam.getJob()==TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()){
					throw new BaseException(ExceptionMessage.TEAM_CAPTAIN_QUIT);
				}
				updateService.quit(teamId, Integer.valueOf(cid));
				Team team = getService.getTeamById(teamId);
				Player header = getService.getSimplePlayerById(team.getHeaderId());
				Player player = getService.getSimplePlayerById(pt.getPlayerId());
				ServiceLocator.playerLog.info("player="+cidsStr+" quit from team="+teamId);
				
				if(null != header){
					messageService.sendQuitFromTeamNotifyMail(header, player);
				}
			}else if (TeamConstants.TEAM_OPERATE_KICK.equals(action)){
				//检测是否输过二级密码
				if(!checkEnterSPW(Integer.valueOf(cid))){
					return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
				}
				PlayerTeam targetPlayerTeam = getService.getPlayerTeamByPlayerId(Integer.valueOf(cids[0]));
				if (pt == null|| targetPlayerTeam == null){
					throw new BaseException(ExceptionMessage.TEAM_OP_FAIL);
				}
				if(targetPlayerTeam.getJob()>=pt.getJob()|| 
						!(pt.getJob().equals(TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) || pt.getJob().equals(TeamConstants.TEAMJOB.TEAM_OFFICER.getValue()) )
						|| cids[0] == cid){
					throw new BaseException(TeamConstants.Not_Enough_Right);
				}
				
				
				updateService.quit(teamId, Integer.valueOf(cids[0]));
				ServiceLocator.playerLog.info("player="+cidsStr+" kicked from   team="+teamId+" by player "+cid);
				Player p = getService.getSimplePlayerById(Integer.valueOf(cids[0]));
				Team team = getService.getTeam(teamId);
				messageService.sendFiredFromTeamNotifyMail(p, team);
				soClient.puchCMDtoClient(p.getName(), CommonUtil.messageFormat(CommonMsg.TEAM_NUMBER_CHANGE));

			}else if(TeamConstants.TEAM_OPERATE_HANDOVER.equals(action)){
				PlayerTeam targetPlayerTeam = getService.getPlayerTeamByPlayerId(Integer.valueOf(cids[0]));
				updateService.handoverLeader(pt, targetPlayerTeam);
				ServiceLocator.playerLog.info("player="+pt.getPlayerId()+" deliver the job TEAM_CAPTAIN to player="+targetPlayerTeam.getPlayerId());
			}else if (TeamConstants.TEAM_OPERATE_APPOINT.equals(action)){
				PlayerTeam targetPlayerTeam = getService.getPlayerTeamByPlayerId(Integer.valueOf(cids[0]));
				int newJob = Integer.valueOf(args[4]);
				updateService.updatePlayerJob(pt, targetPlayerTeam, newJob);
				ServiceLocator.playerLog.info("player="+targetPlayerTeam.getPlayerId()+" get the job:"+newJob);
			}else {
				throw new Exception("unknow action:" + action);
			}
			return Converter.error(null);
		}catch (BaseException e) {
			return Converter.error(e.getMessage());
		}
		catch (Exception e) {
			logger.warn("team request op fail:" + e.getMessage(),e);
				return Converter.error(ExceptionMessage.ERROR_MESSAGE);
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
