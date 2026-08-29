package com.pearl.o2o.pojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Tuple;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛参加战队排行榜
 * @author zhaolianming
 */
//(LATIS:)(temaid) (积分)
public class LeagueTeamTopImpl {
	private static NoSql noSql = ServiceLocator.nosqlService.getNosql();
	private static GetService getService = ServiceLocator.getService;

	/**
	 * 删除联赛参加战队积分排行榜里的对应id战队
	 * @param teamId
	 * @throws Exception
	 */
	public static void delTeamById(int teamId) throws Exception {
		noSql.zRem(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX, teamId + "");
		noSql.hashdel(Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX, teamId + "");
	}

	/**
	 * 上赛季联赛参加战队排行榜分页
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<LeagueTeam> oldLeagueTeamTopPage(int start, int end) throws Exception {
		String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX;
		Set<Tuple> tuples = noSql.revRangeWithScores(key, start, end);
		List<LeagueTeam> leagueTeams = new ArrayList<LeagueTeam>();
		for (Tuple tuple : tuples) {
			int teamId = Integer.valueOf(tuple.getElement());
			if (teamId == 0)
				continue;
			LeagueTeam leagueTeam = LeagueTeamImpl.getTeamById(teamId, 1);
			/*	Team team = getService.getTeam(teamId);
				leagueTeam.setTeam(team);*/
			leagueTeam.setTeamId(teamId);
			leagueTeam.setScoce((int) tuple.getScore());
			leagueTeams.add(leagueTeam);
		}
		return leagueTeams;
	}

	/**
	 * 联赛参加战队排行榜分页
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<LeagueTeam> leagueTeamTopPage(int start, int end) throws Exception {
		String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
		Set<Tuple> tuples = noSql.revRangeWithScores(key, start, end);
		List<LeagueTeam> leagueTeams = new ArrayList<LeagueTeam>();
		for (Tuple tuple : tuples) {
			int teamId = Integer.valueOf(tuple.getElement());
			if (teamId == 0)
				continue;
			LeagueTeam leagueTeam = LeagueTeamImpl.getTeamById(teamId, 1);
			/*			Team team = getService.getTeam(teamId);
						leagueTeam.setTeam(team);*/
			leagueTeam.setTeamId(teamId);
			leagueTeam.setScoce((int) tuple.getScore());
			leagueTeams.add(leagueTeam);
		}
		return leagueTeams;
	}

	/**
	 * 获得总数目
	 * @return
	 * @throws Exception
	 */
	public static int getNumber(int seasonType) throws Exception {
		String key;
		if (seasonType == 1) {
			key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
		} else {
			key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX;
		}
		return (int) noSql.zCard(key);//总数目
	}

	/**
	 * 获得排行榜战队积分
	 * @param teamId 战队id
	 * @throws Exception
	 */
	public static int getScoce(int teamId) throws Exception {
		String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
		return (int) noSql.zScore(key, teamId + "");
	}

	public static int getDailyScoce(int teamId) throws Exception {
		String key = Constants.LEAGUE_TEAM_DAILY_SCORE;
		return (int) noSql.zScore(key, teamId + "");
	}

	/**
	 * 更新战队排行榜积分
	 * @param teamId
	 * @param score
	 * @throws Exception
	 */
	public static void updataLeagueTeamTop(int teamId, int score) throws Exception {
		String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
		noSql.zAdd(key, score, teamId + "");
	}

	/**
	 * 拼接
	 * @param lTeam
	 * @param rankIndex
	 * @return
	 * @throws Exception
	 */
	public static String joint(LeagueTeam lTeam, int rankIndex) throws Exception {
		//entryList.add("\"排名\",\"战队图标\",\"战队名称\",\"胜率\",\"胜/负场次\",\"等级\",\"战斗力\",\"人数\",\"积分\",\"创建时间\"");
		//胜率
		String winPercent = "-";
		String marks = "\"";
		if (lTeam.getWin() + lTeam.getFall() > 0) {
			int winPercent_int = lTeam.getWin() * 1000 / (lTeam.getWin() + lTeam.getFall());
			double winPercent_1 = winPercent_int / 10;
			winPercent = winPercent_1 + "%";
		}

		Team team = getService.getTeam(lTeam.getTeamId());

		StringBuilder result = new StringBuilder();
		if (null != team) {
			result.append(marks).append(rankIndex)//排名
					.append("\",").append(marks).append(team.getLogo()).append(marks + ",")//战队图标
					.append(marks).append(team.getName()).append(marks + ",")//战队名称
					.append(marks).append(winPercent).append(marks + ",")//胜率
					.append(marks).append(lTeam.getWin() + "/" + lTeam.getFall()).append(marks + ",")//胜/负场次
					.append(marks).append(team.getLevel()).append(marks + ",")//等级
					.append(marks).append(team.getFight()).append(marks + ",")//战斗力
					.append(marks).append(team.getNumber() + "/" + team.getSize()).append(marks + ",")//人数
					.append(marks).append(lTeam.getScoce()).append(marks + ",")//积分
					.append(marks).append(new SimpleDateFormat("yyyy-MM-dd").format(team.getCreateTime())).append(marks + ",")//创建时间
					.append(marks).append(team.getId()).append(marks + ",");
		}

		return result.toString();
	}

	public static void updataDailyScoreTop(int teamId, int score) {
		String day_key = Constants.LEAGUE_TEAM_DAILY_SCORE;
		try {
			noSql.zAdd(day_key, score, teamId + "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
