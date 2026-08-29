package com.pearl.o2o.pojo;

import java.util.ArrayList;
import java.util.List;

public class LeagueGameRecord {
    private String time;//比赛时间日期
    private String teambName;//对方战队名字
    private int teambId;//对方战队id
    private int myTeamScoce;//我方积分
    private int teambScoce;//对方战队积分
    private List<LeagueGameRecordSingle> lgrSingleS = new ArrayList<LeagueGameRecordSingle>();
    private List<String> list = new ArrayList<String>();

    public void joint() {
        for (LeagueGameRecordSingle lgrSingle : this.lgrSingleS) {
            this.myTeamScoce += lgrSingle.getMyTeamScoce();
            this.teambScoce += lgrSingle.getTeambScoce();
            list.add(lgrSingle.toString());
        }
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getTeambId() {
        return teambId;
    }

    public void setTeambId(int teambId) {
        this.teambId = teambId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeambName() {
        return teambName;
    }

    public void setTeambName(String teambName) {
        this.teambName = teambName;
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

    public List<LeagueGameRecordSingle> getLgrSingleS() {
        return lgrSingleS;
    }

    public void setLgrSingleS(List<LeagueGameRecordSingle> lgrSingleS) {
        this.lgrSingleS = lgrSingleS;
    }

}
