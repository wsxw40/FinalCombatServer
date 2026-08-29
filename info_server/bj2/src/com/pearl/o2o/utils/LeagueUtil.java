package com.pearl.o2o.utils;

/**
 * 联赛公共方法
 * @author zhaolianming
 *
 */
public class LeagueUtil {
 	/**
     * 游戏类型转联赛游戏类型
     * @param gameTpye
     * @return
     */
    public static int leagueGameTypeTogameType(int gameTpye) {
        switch (gameTpye) {
        case 0://高级战场（歼灭）
            return 4;
        case 1://中级战场（推车）
            return 2;
        case 2://低级战场（团队竞技）
            return 0;
        case 3://低级战场（占点）
            return 1;
        case 4://低级战场（刀战）
            return 6;
        case 5://特殊战场（跳跳乐）
            return 17;
        }
        return 0;
    }
    /**
     * 游戏类型转联赛游戏类型
     * @param gameTpye
     * @return
     */
    public static int gameTypeToleagueGameType(int gameTpye) {
        switch (gameTpye) {
        case 4://高级战场（歼灭）
            return 0;
        case 2://中级战场（推车）
            return 1;
        case 0://低级战场（团队竞技）
            return 2;
        case 1://低级战场（占点）
            return 3;
        case 6://低级战场（刀战）
            return 4;
        case 17://特殊战场（跳跳乐）
            return 5;
        }
        return -1;
    }
    /**
     * 联赛积分
     * @param isMatch
     * @param stageClearInfo
     * @param gameTpye 
     * @param playerInfo 
     * @param player2 
     */
    public static int leagueGameScore(int bout, int gameTpye) {
        int Score = 0;
        switch (gameTpye) {
        case 4://高级战场（歼灭）
            Score = leagueGameScoreAdvanced(bout);
            break;
        case 2://中级战场（推车）
            Score = leagueGameScoreMiddle(bout);
            break;
        case 0://低级战场（团队竞技）
            Score = leagueGameScoreLow(bout);
            break;
        case 1://低级战场（占点）
            Score = leagueGameScoreMiddle(bout);
            break;
        case 6://低级战场（刀战）
            Score = leagueGameScoreMiddle(bout);
            break;
        case 17://特殊战场（跳跳乐）
            Score = 3;
            break;
        }
        return Score;
        
    }
    /**
     * 联赛高级战场结算
     * @param Score
     * @return
     */
    private static int leagueGameScoreAdvanced(int Score) {
        switch (Score) {
        case 3: Score=120;break;
        case 2: Score=96;break;
        case 1: Score=72;break;
        case -1: Score=48;break;
        case -2: Score=24;break;
        case -3: Score=6;break;
        }
        return Score;
    }
    /**
     * 联赛中级战场结算
     * @param Score
     * @return
     */
    private static int leagueGameScoreMiddle(int Score) {
        switch (Score) {
        case 3: Score=120;break;
        case 2: Score=96;break;
        case 1: Score=72;break;
        case -1: Score=48;break;
        case -2: Score=24;break;
        case -3: Score=6;break;
        }
        return Score;
    }
    /**
     * 联赛低级战场结算
     * @param Score
     * @return
     */
    private static int leagueGameScoreLow(int Score) {
        switch (Score) {
        case 3: Score=120;break;
        case 2: Score=96;break;
        case 1: Score=72;break;
        case -1: Score=48;break;
        case -2: Score=24;break;
        case -3: Score=6;break;
        }
        return Score;
    }

}