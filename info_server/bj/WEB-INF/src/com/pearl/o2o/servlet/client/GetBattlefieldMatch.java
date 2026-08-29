package com.pearl.o2o.servlet.client;

import java.util.HashMap;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.utils.BattleFieldStatus;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 进行资源争夺战队伍匹配
 * 
 * @author leo.zhang
 */
public class GetBattlefieldMatch extends BaseClientServlet {

	private static final long serialVersionUID = 7956047244525381200L;

	static Logger logger = LoggerFactory.getLogger(GetBattlefieldMatch.class
			.getName());

	/** 仅仅是一个随机数 */
	Random random = new Random();

	// private boolean debug = false;

	/** {pid:PID ;c:第N次匹配， 从1开始} */
	private String[] paramNames = { "pid", "c" };

	@Override
	protected String innerService(String... args) {
		String result = "";
		Team defanceTeam = null;
		try {
			if (!checkZYZDZOpen()) {// 资源争夺战未开启
				return "";
			}

			int playerId = StringUtil.toInt(args[0]);
			int matchCount = StringUtil.toInt(args[1]);
			int canRobUnuseRes=0;

			Player player = getService.getPlayerByIdWithTeam(playerId);
			/** 超过匹配上限，直接返回 */
			Team attackTeam = null;
			if (matchCount > Constants.TeamSpaceConstants.MAX_MATCH_TIMES) {
				return Converter.GetMatchedTeam(null,attackTeam, null, true, false,canRobUnuseRes,player);
			}
			if (player.getTeamId() != 0) {
				attackTeam = getService.getTeamById(player.getTeamId());
			}
			if (attackTeam == null) {
				throw new BaseException(ExceptionMessage.TEAM_NOT_EXIST_ZYZDZ);
			}

			HashMap<String, Integer> playerRes = player
					.getLatestPlayerRes(attackTeam.getTeamSpaceLevel());

			if (matchCount == 1) {
				if (playerRes.get(Player.ORG_RES) < Constants.TeamSpaceConstants.MATCH_COST_STONES) {
					return Converter.GetMatchedTeam(null,attackTeam, null, false, true,canRobUnuseRes,player);
				} else {// 扣除玩家黑原石
					createService.costMatchUnUsableStones(player);
				}
			}

			if (attackTeam != null) {// 进行队伍匹配
				defanceTeam = doMatch(attackTeam, matchCount);
			}

			if (defanceTeam != null) {
				TeamLevelInfo teamLevelInfo = getService.getTeamLevelInfo(
						defanceTeam.getId(),
						Constants.DEFAULT_TEAM_LEVEL_RES_ID);
				if (teamLevelInfo != null) {
					canRobUnuseRes= getService.getCanRoboRes(defanceTeam);
					teamLevelInfo.setFormatedConfig(getService
							.getFormatConfigOption(teamLevelInfo
									.getConfigPoints()));
					result = Converter.GetMatchedTeam(defanceTeam,attackTeam,
							teamLevelInfo, false, false,canRobUnuseRes,player);
				} else {
					// result = Converter.GetMatchedTeam(defanceTeam,
					// Constants.DEFAULT_TEAM_LEVEL_RES_ID, false,false);
					result = "";
				}

			}

			return result;

		} catch (BaseException e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(e.getMessage());
		}

		catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	/**
	 * 
	 * @param attackTeam
	 * @param matchCount
	 * @return
	 * @throws Exception
	 */
	private Team doMatch(Team attackTeam, int matchCount)  {
		Team result = null;
		try {
			String loseKey = Constants.TeamSpaceConstants
					.getTeamBattleFieldLoseKey(attackTeam.getId());
			Integer loseCountInCache = mcc.get(loseKey);

			// 匹配人的概率默认为1
			double matchHumanDice = 1;
			if (loseCountInCache != null) {
				if (loseCountInCache.intValue() == Constants.TeamSpaceConstants.MIN_MATCH_COMPUTER) {// 连续失败3次
					matchHumanDice = 0.3;
				} else if (loseCountInCache.intValue() > Constants.TeamSpaceConstants.MIN_MATCH_COMPUTER) {// 连续失败4次
					matchHumanDice = 0d;
				}
			}

			// 本次匹配随机参数
			double dice = random.nextDouble();
			if (matchHumanDice - dice >= 0) {// 匹配活跃队伍
				result = doMatchActiveTeam(attackTeam.getId());
			} else {// 优先匹配非活跃队伍
				result = doMatchDisActiveTeam(attackTeam.getId());
				mcc.delete(loseKey);// 匹配到死鱼了，清空失败次数记录
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}finally{
			return result;
		}
	}

	/**
	 * 匹配活跃队伍
	 * 
	 * @param myteamId
	 * @return
	 * @throws Exception
	 */
	private Team doMatchActiveTeam(int myTeamId) throws Exception {
		Team defanceTeam = null;
		int defenceTeamCount = getService.getTeamSpaceActiveTeamCount(true,myTeamId);

		if (defenceTeamCount == 0) {// 没有活跃的队伍
			return null;
		}
		defanceTeam = getMatchedTeam(defenceTeamCount, myTeamId);
		return defanceTeam;

	}

	/**
	 * 匹配非活跃队伍
	 * 
	 * @return
	 * @throws Exception
	 */
	private Team doMatchDisActiveTeam(int myTeamId) throws Exception {
		Team defanceTeam = null;
		int defenceTeamCount = getService.getTeamSpaceActiveTeamCount(false,myTeamId);
		if (defenceTeamCount != 0) {
			int index = random.nextInt(defenceTeamCount);
			defanceTeam = getService.getTeamByTeamSpaceActiveAndIndex(false, index,myTeamId);
		}
		return defanceTeam;
	}

	/**
	 * 获得匹配的队伍
	 * 
	 * @param defenceTeamList
	 * @return
	 * @throws Exception
	 */
	private Team getMatchedTeam(int defenceTeamCount, int myTeamId)
			throws Exception {
		Team defenceTeam = null;
		boolean setSucced = false;
		int index = 0;
		if (defenceTeamCount > 0) {
			index = random.nextInt(defenceTeamCount);
		}else{
			return null;
		}
		defenceTeam = getService.getTeamByTeamSpaceActiveAndIndex(true, index,myTeamId);
		if (defenceTeam != null) {
			defenceTeam = getService.getTeamById(defenceTeam.getId());
			/** 不能匹配到自己 */
			if (defenceTeam.getId() == myTeamId) {
				return null;
			}
			String battleStatusKey = Constants.TeamSpaceConstants
					.getTeamBattleStatusKey(defenceTeam.getId());
			String teamMatchKey = Constants.TeamSpaceConstants
					.getTeamMatchKey(defenceTeam.getId());

			/** 该队伍没有满足再次匹配时间，返回空 */
			if (mcc.get(teamMatchKey) != null) {
				return null;
			}

			/** 该队伍正在在战斗，且战斗场次>=5场，不进行匹配，continue */
			if (mcc.get(battleStatusKey) != null) {
				BattleFieldStatus battleFieldStatus = mcc.get(battleStatusKey);
				int beingOnFire = getService
						.getCorrectBattleCount(battleFieldStatus);
				if (beingOnFire > Constants.TeamSpaceConstants.MAX_BE_ONFIRE_TIMES) {
					return null;
				}
			}
			setSucced = mcc
					.add(
							teamMatchKey,
							Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_MATCH_LIFE_TIMEOUT,
							defenceTeam,
							Constants.TeamSpaceConstants.BATTLEFIELD_CACHE_MATCH_TIMEOUT);
		}
		if (setSucced) {
			return defenceTeam;
		} else {
			return null;
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
