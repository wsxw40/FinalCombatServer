package com.pearl.o2o.pojo;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;

public class LeagueTeamMateImpl {
    private static NoSql noSql = ServiceLocator.nosqlService.getNosql();

    public static void add(int myTeamId,int teamBId)  {
        try {
            noSql.hashSet(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX, myTeamId+"", teamBId+"");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static int get(int myTeamId)  {
        String hashGet = "0";
        try {
            hashGet = noSql.hashGet(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX, myTeamId+"");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Integer.parseInt(hashGet);
    }
}