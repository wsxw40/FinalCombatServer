package com.pearl.o2o.servlet.client;

import java.util.List;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.BattleFieldStatus;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 处理资源争夺战匹配（取消，开打，战斗结束）
 * 
 * @author leo.zhang
 */
public class HandleBattlefieldBattle extends BaseClientServlet {
	private static final long serialVersionUID = -3928363495923777848L;

	static Logger logger = LoggerFactory
			.getLogger(HandleBattlefieldBattle.class.getName());

	/** {tid:防守队伍的ID ;t:类型{0:取消战斗;1:开始战斗}; 进攻方队员pid集合，用-分割;可以掠夺资源数;地图信息} */
	private String[] paramNames = { "tid", "t", "pnames", "canRobRes", "config" };

	@Override
	protected String innerService(String... args) {

		String result = "";
		try {
			int matchedTeamId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			String sPNames = args[2];
			String sCanRobRes = args[3];
			String config = args[4];

			Team machedTeam = getService.getSimpleTeamById(matchedTeamId);
			if (machedTeam == null) {
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_NOTEAM);
			}
			switch (type) {
			case 0:
				handleCancel(matchedTeamId);
				;
				break;
			case 1:
				result = handleStartBattle(machedTeam, sPNames, sCanRobRes,
						config);
				break;
			default:
				;
				break;
			}
			return result;
		} catch (BaseException e) {
			logger.warn(e.getMessage(), e);
			if(e.getMessage().equals(ExceptionMessage.ERROR_MESSAGE_NOTEAM)){
				return Converter.commonNoSuchTeam();
			}else{
				return Converter.commonFeedback(e.getMessage());
			}
			
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	/**
	 * 处理取消匹配
	 * 
	 * @param matchedTeamId
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	private void handleCancel(int matchedTeamId) throws TimeoutException,
			InterruptedException, MemcachedException {
		mcc.delete(Constants.TeamSpaceConstants.getTeamMatchKey(matchedTeamId));
	}

	/**
	 * 处理开始战斗
	 * 
	 * @param matchedTeamId
	 * @throws Exception
	 */
	private String handleStartBattle(Team machedTeam, String sPNames,
			String sCanRobRes, String config) throws Exception {

		// 本次开战的key
		String newBattleStartKey = Constants.TeamSpaceConstants
				.getTeamBattleStartKey(machedTeam.getId());
		// 被匹配队伍的战场交火信息key
		String battleStatusKey = Constants.TeamSpaceConstants
				.getTeamBattleStatusKey(machedTeam.getId());

		// 被开战队伍的交火信息
		BattleFieldStatus battleFieldStatus = mcc.get(battleStatusKey);

		if (battleFieldStatus == null) {// 被开战队伍的交火信息为空
			battleFieldStatus = new BattleFieldStatus();
		} else {// 被开战队伍存在交火信息
			List<String> correctBattleStartKeys = getService
					.getCorrectBattleStartKeys(battleFieldStatus);
			battleFieldStatus.setBattleStartKeys(correctBattleStartKeys);
		}

		battleFieldStatus.addNewBattle(newBattleStartKey);

		/** 缓存单场交火信息 */

		mcc
				.add(
						newBattleStartKey,
						Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_GAMEROUND_LIFE_TIMEOUT,
						getService.getCanRoboRes(machedTeam),
						Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_GAMEROUND_TIMEOUT);

		/** 缓存被匹配到战场的信息 */
		mcc
				.set(
						battleStatusKey,
						Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_GAMEROUND_LIFE_TIMEOUT,
						battleFieldStatus,
						Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_GAMEROUND_TIMEOUT);

		/** 发送战场信息给进攻方队员 */
		soClient.sendBattleFieldInfoToAttactMembers(sPNames, machedTeam.getId(),
				sCanRobRes, config,
				Constants.TeamSpaceConstants.FightType.MATCH, getService);
		
		
		/**清空防守队伍匹配中的状态*/
		mcc.delete(Constants.TeamSpaceConstants.getTeamMatchKey(machedTeam.getId()));

		return Constants.TeamSpaceConstants
				.getTeamBattleStartKey2Client(newBattleStartKey);
	}

	/**
	 * 处理战斗结束
	 * 
	 * @param matchedTeamId
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	// private void handleEndBattle(int matchedTeamId) throws TimeoutException,
	// InterruptedException, MemcachedException {
	// mcc
	// .delete(Constants.TeamSpaceConstants
	// .getTeamBattleKey(matchedTeamId));
	// }
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
