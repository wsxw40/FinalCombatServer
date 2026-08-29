package com.pearl.o2o.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛参加战队胜负积分
 * @author zhaolianming
 */
//(LATI:)(temaid) (胜|负|积分)
public class LeagueTeamImpl {
	private static Logger logger = LoggerFactory.getLogger(LeagueTeamImpl.class);
	private static NoSql noSql = ServiceLocator.nosqlService.getNosql();

	/**
	 * 获得联赛参加战队胜负积分
	 * seasonType=1当前赛季
	 * @param teamId 战队id
	 * @return
	 * @throws Exception
	 */
	public static LeagueTeam getTeamById(int teamId, int seasonType) throws Exception {
		String values = "";
		String key = "";
		if (seasonType == 1) {
			values = noSql.hashGet(Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX, teamId + "");
			key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
		} else {
			values = noSql.hashGet(Constants.LEAGUE_ATTEND_TEAM_INFO_OLD_KEY_PREFIX, teamId + "");
			key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX;
		}
		double score = noSql.zScore(key, teamId + "");
		LeagueTeam leagueTeam = new LeagueTeam();
		leagueTeam.setTeamId(teamId);
		leagueTeam.setScoce((int) score);
		values = null == values ? "" : values;
		String[] valueArray = values.split("\\|");
		leagueTeam.setTeamId(teamId);
		if (valueArray.length == 3 || valueArray.length == 2) {
			leagueTeam.setWin(Integer.valueOf(valueArray[0]));
			leagueTeam.setFall(Integer.valueOf(valueArray[1]));
		}
		long index = noSql.revRankInSortedSet(key, teamId + "") + 1;
		leagueTeam.setIndex((int) index);
		return leagueTeam;
	}

	/**
	 * 插入到联赛战队胜负积分表,redis没有对应数据就插入，有就不改动
	 * @param teamId
	 * @param leagueTeam
	 * @throws Exception
	 * @throws Exception
	 */
	public static void insertTeam(int teamId, LeagueTeam leagueTeam) throws Exception {
		String key = Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX;
		String hashGet = noSql.hashGet(key, teamId + "");
		if (hashGet != null)
			return;
		updataTeam(teamId, leagueTeam);
	}

	/**
	 * 更新到联赛战队胜负积分表
	 * @param teamId
	 * @param leagueTeam
	 * @throws Exception
	 * @throws Exception
	 */
	public static void updataTeam(int teamId, LeagueTeam leagueTeam) throws Exception {
		String key = Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX;
		int win = leagueTeam.getWin();
		int fall = leagueTeam.getFall();
		int scoce = leagueTeam.getScoce();
		noSql.hashSet(key, teamId + "", win + "|" + fall + "|" + scoce);
	}
}
