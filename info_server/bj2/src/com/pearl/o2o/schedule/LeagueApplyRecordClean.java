package com.pearl.o2o.schedule;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.LeagueMemberImpl;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛比赛名单跟报名的记录清理
 * @author zhaolianming
 *
 */
public class LeagueApplyRecordClean implements Runnable {
    public static Logger log = LoggerFactory.getLogger(LeagueApplyRecordClean.class);
    private static NoSql noSql = ServiceLocator.nosqlService.getNosql();
    @Override
    public void run() {
       
        try {
            Set<String> teamIds = noSql.keys(Constants.LEAGUE_ATTEND_MEMBER_INFO_KEY_PREFIX+"*");
            for (String teamIdStr : teamIds) {
                int teamId = Integer.valueOf(teamIdStr.substring(4));
                LeagueMemberImpl.delTeamById(teamId);
                Team team = ServiceLocator.getService.getTeam(teamId);
                team.setApply(0);
                ServiceLocator.updateService.updateTeamInfo(team);
            }
            /*
             String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
             Set<Tuple> tuples = noSql.zrangeWithScores(key, 0, -1);
                for (Tuple tuple : tuples) {
                Integer teamId = Integer.valueOf(tuple.getElement());
                LeagueMemberImpl.delTeamById(teamId);
                Team team = ServiceLocator.getService.getTeam(teamId);
                team.setApply(0);
                ServiceLocator.updateService.updateTeamInfo(team);
            }*/
            noSql.delete(Constants.LEAGUE_ATTEND_TIME_KEY_PREFIX);
            noSql.delete(Constants.LEAGUE_GAME_TIME_KEY_PREFIX);
            noSql.delete(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX);
        } catch (Exception e) {
            log.warn("Error in "+LeagueApplyRecordClean.class.getName()+": " , e);
            e.printStackTrace();
        }
    }
}
