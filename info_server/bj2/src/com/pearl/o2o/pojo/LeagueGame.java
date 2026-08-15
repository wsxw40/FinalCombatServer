package com.pearl.o2o.pojo;

import java.util.ArrayList;

/**
 * 联赛分组
 * @author zhaolianming 配对id 战队id 战队等级 游戏类型
 */
public class LeagueGame {
    private Integer gameType;//游戏类型
    private Integer mateId;//配对id
    private Integer teamId;//战队id
    private String teamName;//战队名字
    private Integer scoce;//战队积分
    private Integer level;//战队等级

    private ArrayList<Integer> playerIds = null;//玩家id

    public String getTeamName() {

        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGameType() {
        return gameType;
    }

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

    public Integer getMateId() {
        return mateId;
    }

    public void setMateId(Integer mateId) {
        this.mateId = mateId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getScoce() {
        return scoce;
    }

    public void setScoce(Integer scoce) {
        this.scoce = scoce;
    }

    public ArrayList<Integer> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(ArrayList<Integer> playerIds) {
        this.playerIds = playerIds;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
