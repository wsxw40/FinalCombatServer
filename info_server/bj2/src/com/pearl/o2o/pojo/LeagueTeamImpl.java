package com.pearl.o2o.pojo;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 联赛参加战队胜负积分
 * @author zhaolianming
 */
//(LATI:)(temaid) (胜|负|积分)
public class LeagueTeamImpl {
    private static NoSql noSql = ServiceLocator.nosqlService.getNosql();
    
    public static LeagueTeam getTeamById(int teamId) throws Exception {
        String values = noSql.hashGet(Constants.LEAGUE_ATTEND_TEAM_INFO_KEY_PREFIX, teamId + "");
        String[] valueArray = values.split("\\|");
        LeagueTeam leagueTeam = new LeagueTeam();
        if (valueArray.length == 3) {
            leagueTeam.setWin(Integer.valueOf(valueArray[0]));
            leagueTeam.setFall(Integer.valueOf(valueArray[1]));
            leagueTeam.setScoce(Integer.valueOf(valueArray[2]));
        }
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
