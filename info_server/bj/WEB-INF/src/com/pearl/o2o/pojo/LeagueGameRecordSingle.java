package com.pearl.o2o.pojo;

/**
 * 联赛游戏记录单场
 * @author zhaolianming
 */
public class LeagueGameRecordSingle {
    private int gameType;//战场类型
    private int myTeamScoce;//我方积分
    private int teambScoce;//对方战队积分

    public LeagueGameRecordSingle() {
        super();
    }

    public LeagueGameRecordSingle(int gameType, int myTeamScoce, int teambScoce) {
        super();
        this.gameType = gameType;
        this.myTeamScoce = myTeamScoce;
        this.teambScoce = teambScoce;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getMyTeamScoce() {
        return myTeamScoce;
    }

    public void setMyTeamScoce(int myTeamScoce) {
        this.myTeamScoce = myTeamScoce;
    }

    public int getTeambScoce() {
        return teambScoce;
    }

    public void setTeambScoce(int teambScoce) {
        this.teambScoce = teambScoce;
    }
    

    @Override
    public String toString() {
        return "\""+gameType+"\",\""+myTeamScoce+"\",\""+teambScoce+"\"";
    }
}
