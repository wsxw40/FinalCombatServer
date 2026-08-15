
package com.pearl.o2o.servlet.client;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysTeamBuff;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamBuff;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TeamConstants;

/**
 * @author bobby
 */
public class TeamBuffOp extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamBuffOp.class.getName());
	private static final String[] paramNames = {"ptid","tid","action","teambuffid"};
	//action 1: 解锁	2：激活
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		String error = null;
		
		try{
			int playerTeamId = Integer.parseInt(args[0]);
			int teamId = Integer.parseInt(args[1]);
			int action = Integer.parseInt(args[2]);
			int teambuffid = Integer.parseInt(args[3]);
			
			PlayerTeam playerTeam = getService.getPlayerTeamDao().getPlayerTeam(teamId, playerTeamId);
			Preconditions.checkNotNull(playerTeam);
			
			Player player = getService.getPlayerById(playerTeam.getPlayerId());
			Preconditions.checkNotNull(player);
			Team team = getService.getTeamDao().getTeamById(teamId);
			switch (action) {
			case 1:
				Preconditions.checkNotNull(team);
				error = updateService.teamBuffDeblock(playerTeam, team, teambuffid,player);
				break;
			case 2:
				SysTeamBuff sysTeamBuff = getService.getSysTeamBuffDao().getSysTeamBuffById(teambuffid);
				Preconditions.checkNotNull(sysTeamBuff);
				TeamBuff teamBuffById = getService.getTeamBuffDao().getTeamBuffById(teamId, teambuffid);
				error = updateService.teamBuffActivate(playerTeam, sysTeamBuff, teamBuffById,player);
				if(null == error){
					SysItem si=getService.getSysItemByItemId(sysTeamBuff.getSysItemId());
					if(si.getIBuffId()==TeamConstants.Team_Buff_MaxStrengthLevel_BuffId){
						soClient.puchCMDtoClient(player.getName(),  
								CommonUtil.messageFormat(CommonMsg.REFRESH_MAX_STRENGHT_LEVEL, getService.getMaxStrengthLevel(player.getId())));
					}
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						nosqlService.addXunleiLog("12.2"
								+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
								+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + team.getId()
								+ Constants.XUNLEI_LOG_DELIMITER + team.getName()
								+ Constants.XUNLEI_LOG_DELIMITER + team.getLevel()
								+ Constants.XUNLEI_LOG_DELIMITER+ si.getId()
								+ Constants.XUNLEI_LOG_DELIMITER+ si.getDisplayNameCN()
								+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
								);
					}
					int medolNum=getService.getMedolNumByPlayerId(playerTeam.getPlayerId());
					return "num = "+medolNum;
				}
				
				break;
			default:
				error = TeamConstants.Illegal_Rpc_Qequest;
			}
		}catch (NotBuyEquipmentException e) {
			return Converter.error(e.getMessage());
		}
		catch (BaseException e) {
			return Converter.error(e.getMessage());
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		if(null == error){
			return "error = nil";
		}else{
			return Converter.error(error);
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
