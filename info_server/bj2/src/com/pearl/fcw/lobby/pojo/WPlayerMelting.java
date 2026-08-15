package com.pearl.fcw.lobby.pojo;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerMelting extends DmExtModel {

    private static final long serialVersionUID = 1431383614266207060L;

    private Integer id;

    private Integer level;

    private Integer exp;

    private Double remaind;

    private Integer num;

    private Integer recovery;

    private Long lastInit;

    private Long startTime;

    private Long grandTotalTime;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Double getRemaind() {
        return remaind;
    }

    public void setRemaind(Double remaind) {
        this.remaind = remaind;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getRecovery() {
        return recovery;
    }

    public void setRecovery(Integer recovery) {
        this.recovery = recovery;
    }

    public Long getLastInit() {
        return lastInit;
    }

    public void setLastInit(Long lastInit) {
        this.lastInit = lastInit;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getGrandTotalTime() {
        return grandTotalTime;
    }

    public void setGrandTotalTime(Long grandTotalTime) {
        this.grandTotalTime = grandTotalTime;
    }

    @Override
    public Integer getShareId() {
        return id;
    }

    @Override
    public void setShareId(Integer shareId) {
        id = shareId;
    }

    @Override
    public Integer getShareAmount() {
        return null;
    }
}