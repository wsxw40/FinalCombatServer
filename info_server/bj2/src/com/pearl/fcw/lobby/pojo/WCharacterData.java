package com.pearl.fcw.lobby.pojo;

import java.util.HashMap;
import java.util.Map;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WCharacterData extends DmExtModel {

    private static final long serialVersionUID = -4509498175626939712L;

    private Integer id;

    private Integer playerId;

    private Integer characterId;

    private Integer kill;

    private Integer dead;

    private Integer headShot;

    private Integer knifeKill;

    private Integer usedCount;

    private Integer controlNum;

    private Integer revengeNum;

    private Integer assistNum;

    private Integer maxHeadShot;

    private Integer maxKill;

    private Integer maxHealth;

    private Integer maxDamage;

    private Integer maxAliveTime;

    private Integer mvpNum;

    private String backUp;

    private Map<String, ParamObject<Number>> numberParamMap = new HashMap<>();

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

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
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

    public Integer getHeadShot() {
        return headShot;
    }

    public void setHeadShot(Integer headShot) {
        this.headShot = headShot;
    }

    public Integer getKnifeKill() {
        return knifeKill;
    }

    public void setKnifeKill(Integer knifeKill) {
        this.knifeKill = knifeKill;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getControlNum() {
        return controlNum;
    }

    public void setControlNum(Integer controlNum) {
        this.controlNum = controlNum;
    }

    public Integer getRevengeNum() {
        return revengeNum;
    }

    public void setRevengeNum(Integer revengeNum) {
        this.revengeNum = revengeNum;
    }

    public Integer getAssistNum() {
        return assistNum;
    }

    public void setAssistNum(Integer assistNum) {
        this.assistNum = assistNum;
    }

    public Integer getMaxHeadShot() {
        return maxHeadShot;
    }

    public void setMaxHeadShot(Integer maxHeadShot) {
        this.maxHeadShot = maxHeadShot;
    }

    public Integer getMaxKill() {
        return maxKill;
    }

    public void setMaxKill(Integer maxKill) {
        this.maxKill = maxKill;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Integer getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(Integer maxDamage) {
        this.maxDamage = maxDamage;
    }

    public Integer getMaxAliveTime() {
        return maxAliveTime;
    }

    public void setMaxAliveTime(Integer maxAliveTime) {
        this.maxAliveTime = maxAliveTime;
    }

    public Integer getMvpNum() {
        return mvpNum;
    }

    public void setMvpNum(Integer mvpNum) {
        this.mvpNum = mvpNum;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
    }

    public Map<String, ParamObject<Number>> getNumberParamMap() {
        return numberParamMap;
    }

    public void setNumberParamMap(Map<String, ParamObject<Number>> numberParamMap) {
        this.numberParamMap = numberParamMap;
    }

    @Override
    public Integer getShareId() {
        return playerId;
    }

    @Override
    public void setShareId(Integer shareId) {
        playerId = shareId;
    }
}