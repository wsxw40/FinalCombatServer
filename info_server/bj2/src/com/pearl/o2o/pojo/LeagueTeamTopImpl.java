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
     * 把本赛季的数据放入上赛季，并且删除
     * @throws Exception 
     */
    public static void delLeagueTeamTopToOld() throws Exception{
      //entryList.add("\"排名A\",\"战队图标\",\"战队名称A\",\"胜率\A",\"胜/负场次\A",\"等级\",\"战斗力\",\"人数\",\"积分\",\"创建时间\"");
        List<LeagueTeam> leagueTeams = LeagueTeamTopImpl.leagueTeamTopPage(0, -1);
        int rankIndex = 1;
        for (LeagueTeam leagueTeam : leagueTeams) {
            noSql.zAdd(Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_OLD_KEY_PREFIX, rankIndex, LeagueTeamTopImpl.joint(leagueTeam,++rankIndex));
            rankIndex++;
        }
    }
    /**
     * 联赛参加战队排行榜分页
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public static List<LeagueTeam> leagueTeamTopPage(int start,int end) throws Exception {
        String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
        Set<Tuple> tuples = noSql.revRangeWithScores(key, start, end);
        List<LeagueTeam> leagueTeams = new ArrayList<LeagueTeam>();
        for (Tuple tuple : tuples) {
            LeagueTeam leagueTeam = new LeagueTeam();
            int teamId = Integer.valueOf(tuple.getElement());
            if (teamId == 0) continue;
            Team team = getService.getTeam(teamId);
            leagueTeam.setTeam(team);
            leagueTeam.setTeamId(teamId);
            leagueTeam.setScoce((int)tuple.getScore());
            leagueTeams.add(leagueTeam);
        }
        return leagueTeams;
    }
    
    /**
     * 根据id获得LeagueTeam
     * @param teamId
     * @return
     * @throws Exception
     */
    public static LeagueTeam getLeagueTeam(int teamId) throws Exception {
        String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
        long index = noSql.revRankInSortedSet(key, teamId+"")+1;
        double score = noSql.zScore(key, teamId+"");
        LeagueTeam leagueTeam = new LeagueTeam();
        leagueTeam.setIndex((int)index);
        leagueTeam.setScoce((int)score);
        return leagueTeam;
    }
    /**
     * 获得总数目
     * @return
     * @throws Exception
     */
    public static int getNumber() throws Exception{
        String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
        return (int)noSql.zCard(key);//总数目
    }
    
    /**
     * 更新战队积分
     * @param teamId
     * @param score
     * @throws Exception
     */
    public static void updataLeagueTeamTop(int teamId,int score) throws Exception{
        String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
        noSql.zAdd(key, score, teamId+"");
    }
    /**
     * 插入战队积分,redis没有对应数据就插入，有就不改动
     * @param teamId
     * @param score
     * @throws Exception
     */
    public static void insertLeagueTeamTop(int teamId,int score) throws Exception{
        String key = Constants.LEAGUE_ATTEND_TEAM_INFO_LIST_KEY_PREFIX;
        //真不知道之前的人这么想的不用封装的，倒是无法返回null，淦
        //现在返回-11表示为空
        long index = noSql.revRankInSortedSet(key, teamId + "");
        if (index == -11)
            noSql.zAdd(key, score, teamId + "");
    }
    /**
     * 拼接
     * @param lTeam
     * @param rankIndex
     * @return
     */
    public static String  joint (LeagueTeam lTeam, int rankIndex) {
        //entryList.add("\"排名\",\"战队图标\",\"战队名称\",\"胜率\",\"胜/负场次\",\"等级\",\"战斗力\",\"人数\",\"积分\",\"创建时间\"");
        //胜率
        String winPercent = "-";
        String marks = "\"";
        if (lTeam.getWin() + lTeam.getFall() > 0) {
            int winPercent_int = lTeam.getWin() * 1000 / (lTeam.getWin() + lTeam.getFall());
            double winPercent_1 = winPercent_int / 10;
            winPercent = winPercent_1 + "%";
        }
        
        Team team = lTeam.getTeam();
        
        StringBuilder result = new StringBuilder();
        result.append(marks).append(rankIndex)//排名
        .append("\",").append(marks).append(team.getLogo()).append(marks+",")//战队图标
        .append(marks).append(team.getName()).append(marks+",")//战队名称
        .append(marks).append(winPercent).append(marks+",")//胜率
        .append(marks).append(lTeam.getWin()+"/"+ lTeam.getFall()).append(marks+",")//胜/负场次
        .append(marks).append(team.getLevel()).append(marks+",")//等级
        .append(marks).append(team.getFight()).append(marks+",")//战斗力
        .append(marks).append(team.getNumber() + "/" + team.getSize()).append(marks+",")//人数
        .append(marks).append(lTeam.getScoce()).append(marks+",")//积分
        .append(marks).append(new SimpleDateFormat("yyyy-MM-dd").format(team.getCreateTime())).append(marks+",");//创建时间
        return result.toString();
    }
}
