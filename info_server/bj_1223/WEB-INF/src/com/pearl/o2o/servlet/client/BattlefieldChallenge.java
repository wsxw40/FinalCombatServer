package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.pojo.GunProperty;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.TOPTeam;

/**
 * 资源争夺战挑战赛
 * 
 * @author leo.zhang
 */
public class BattlefieldChallenge extends BaseClientServlet {
	private static final long serialVersionUID = -3928363495923777848L;

	static Logger logger = LoggerFactory.getLogger(BattlefieldChallenge.class
			.getName());

	/**
	 * lpid:挑战队伍队长ID tid:被挑战队伍的ID
	 */
	private String[] paramNames = { "lpid", "dtid", "pnames", "canRobRes", "config" };

	@Override
	protected String innerService(String... args) {

		String result = "";
		try {
			if (!checkZYZDZOpen()) {// 资源争夺战未开启
				return "";
			}
			int leaderId = StringUtil.toInt(args[0]);
			int defenceTeamId = StringUtil.toInt(args[1]);

			boolean challengeOn = checkZYZDZChallengeOpen();// 是否可以挑战
			boolean startChallengeSUC = false;// 完成发起挑战动作
			Team defenceTeam = null;
			List<Team> preZYZDZRank = null;
			Team attackTeam=null;
			Player leader=null;
			if (challengeOn) {
				preZYZDZRank = getService.getTeamTopForPreRes();// 上一周的排行榜，本周的挑战榜
				defenceTeam = getService.getTeamById(defenceTeamId);
				leader = getService.getPlayerById(leaderId);
				attackTeam = getService.getTeamByPlayerId(leader.getId());

				if (attackTeam.getId() == defenceTeamId) {
					throw new BaseException(ExceptionMessage.CANT_CHALLYOURTEAM);
				}
				if(defenceTeam==null){
					throw new BaseException(ExceptionMessage.ERROR_MESSAGE_NOTEAM);
				}

				if (preZYZDZRank.contains(defenceTeam) && attackTeam != null
						&& getService.beOkForChallenge(defenceTeam)) {// 被挑战队伍是排行榜的队伍，且挑战队伍不为空，且不能挑战自己，被挑战队伍且可以被挑战
					TOPTeam topTeam = createService.createTopTeam(defenceTeam,
							preZYZDZRank, challengeOn,attackTeam);
					startChallengeSUC = createService.createChallengeBattle(
							leader, topTeam);
				}

			}

			if (startChallengeSUC) {// 发起挑战成功
				/** 发送战场信息给进攻方队员 */
				String sPNames = args[2];
				String sCanRobRes = args[3];
				String config = args[4];
				soClient.sendBattleFieldInfoToAttactMembers(sPNames, defenceTeamId, sCanRobRes,
						config,Constants.TeamSpaceConstants.FightType.CHALLENGE,getService);
				
				soClient.updateBillBoardList( CommonUtil.messageFormatI18N(CommonMsg.CHALLENGE_START_SUCC, new Object[]{GunProperty.GOLD + "@!" + attackTeam.getName(),GunProperty.GOLD + "@!" + leader.getName(), GunProperty.GOLD + "@!" + defenceTeam.getName()}));				
			} else {// 发起挑战失败

			}

			result = Converter.BattlefieldChallenge(startChallengeSUC);
			return result;
		} catch (CRNotEnoughException e) {
			logger.warn(e.getMessage(), e);
			return Converter
					.commonFeedback(ExceptionMessage.NOT_ENOUGH_CR_FOR_CHALLENGE);
		} catch (BaseException e) {
			logger.warn(e.getMessage(), e);
			if(e.getMessage().equals(ExceptionMessage.ERROR_MESSAGE_NOTEAM)){
				return Converter.BattlefieldChallenge(false);
			}else{
				return Converter.commonFeedback(e.getMessage());
			}
			
		
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
