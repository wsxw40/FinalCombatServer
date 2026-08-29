package com.pearl.o2o.pojo;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
/**
 * 联赛战队两方的对战关系
 * 只支持按战队id分组配对的规则
 * @author zhaolianming
 *
 */
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
    /**
     * 根据myTeamId获得本轮联赛战队对战方的id
     * @param myTeamId 本战队
     * @return
     */
    public static int get(int myTeamId)  {
        String hashGet = "0";
        try {
            hashGet = noSql.hashGet(Constants.LEAGUE_TEAM_MATE_KEY_PREFIX, myTeamId+"");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(hashGet==null)
            return 0;
        try {
            return  Integer.parseInt(hashGet);
        } catch (Exception e) {
            return 0;
        }
    }
}