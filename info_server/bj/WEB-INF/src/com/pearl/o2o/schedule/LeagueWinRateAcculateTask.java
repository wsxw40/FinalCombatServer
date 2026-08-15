package com.pearl.o2o.schedule;

import java.util.List;

import org.slf4j.Logger;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueTeam;
import com.pearl.o2o.pojo.LeagueTeamTopImpl;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class LeagueWinRateAcculateTask implements Runnable {
	public static Logger logger = ServiceLocator.leagueWinLog;
	NoSql noSql = ServiceLocator.nosqlService.getNosql();
	private static UpdateService updateService = ServiceLocator.updateService;

	@Override
	public void run() {
		try {
			System.out.println("LeagueWinRateAcculateTask  start!");
			int count = (int) noSql.zCard(Constants.LEAGUE_TEAM_DAILY_SCORE);
			List<LeagueTeam> teams = LeagueTeamTopImpl.leagueTeamTopPage(0, count);
			for (LeagueTeam team : teams) {
				int teamId = team.getTeamId();
				Team t = updateService.getTeamDao().getTeamById(teamId);
				String mateStr = "";
				int mateId = 0;
				try {
					mateStr = noSql.hashGet(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX, teamId + "");
					mateId = Integer.valueOf(mateStr);

				} catch (Exception e) {
					continue;
				}

				double teamScore = noSql.zScore(Constants.LEAGUE_TEAM_DAILY_SCORE, teamId + "");
				double mateScore = noSql.zScore(Constants.LEAGUE_TEAM_DAILY_SCORE, mateId + "");
				String key = Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX;
				if (teamScore > mateScore) {
					int win = team.getWin() + 1;
					int fall = team.getFall();
					int scoce = team.getScoce();
					noSql.hashSet(key, teamId + "", win + "|" + fall + "|" + scoce);
					t.setLeagueWin(t.getLeagueWin() + 1);
				}
				if (teamScore < mateScore) {
					int win = team.getWin();
					int fall = team.getFall() + 1;
					int scoce = team.getScoce();
					noSql.hashSet(key, teamId + "", win + "|" + fall + "|" + scoce);
					t.setLeagueFail(t.getLeagueFail() + 1);
				}
				updateService.updateTeamInfo(t);
			}

			noSql.zRemRangeByRank(Constants.LEAGUE_TEAM_DAILY_SCORE, 0, count);
		} catch (Exception e) {
			logger.error("LeagueWinRateAcculateTask/Error:\t", e);
		}

	}

}
