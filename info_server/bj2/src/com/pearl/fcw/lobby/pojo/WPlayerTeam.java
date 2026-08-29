package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerTeam extends DmExtModel {

    private static final long serialVersionUID = -4281962124254449487L;

    private Integer id;

    private Integer playerId;

    private Integer teamId;

    private Byte job;

    private Integer kill;

    private Integer dead;

    private Integer assist;

    private Integer score;

    private Date createdTime;

    private String approved;

    private Integer contribution;

    private Integer times;

    private Integer point;

    private Long lastBurnt;

    private Integer teamFightExp;

    private Integer personalFightExp;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Byte getJob() {
        return job;
    }

    public void setJob(Byte job) {
        this.job = job;
    }

    public Integer getKill() {
        return kill;
    }

    public void setKill(Integer kill) {
        this.kill = kill;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved == null ? null : approved.trim();
    }

    public Integer getContribution() {
        return contribution;
    }

    public void setContribution(Integer contribution) {
        this.contribution = contribution;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Long getLastBurnt() {
        return lastBurnt;
    }

    public void setLastBurnt(Long lastBurnt) {
        this.lastBurnt = lastBurnt;
    }

    public Integer getTeamFightExp() {
        return teamFightExp;
    }

    public void setTeamFightExp(Integer teamFightExp) {
        this.teamFightExp = teamFightExp;
    }

    public Integer getPersonalFightExp() {
        return personalFightExp;
    }

    public void setPersonalFightExp(Integer personalFightExp) {
        this.personalFightExp = personalFightExp;
    }

    @Override
    public Integer getShareId() {
        return teamId;
    }

    @Override
    public void setShareId(Integer shareId) {
        teamId = shareId;
    }

    @Override
    public Integer getShareAmount() {
        return null;
    }
}