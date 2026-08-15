package com.pearl.o2o.pojo;

/**
 * 联赛参加战队胜负积分
 * @author zhaolianming
 */
//(LATI:)(temaid) (胜|负|积分)
public class LeagueTeam {

    private int teamId;//战队id
    private int win; //胜利
    private int fall; //失败
    private int scoce;//积分
    private int index;//排行
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getFall() {
        return fall;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setFall(int fall) {
        this.fall = fall;
    }

    public int getScoce() {
        return scoce;
    }

    public void setScoce(int scoce) {
        this.scoce = scoce;
    }

}
