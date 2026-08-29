package com.pearl.fcw.lobby.pojo;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerTrack extends DmExtModel {

    private static final long serialVersionUID = 8424205159157714426L;

    private Integer playerId;

    private String giftTime;

    private Integer medalCount;

    private Integer goldCount;

    private Double firstCost;

    private String firstCostTime;

    private String newRenewItem;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getGiftTime() {
        return giftTime;
    }

    public void setGiftTime(String giftTime) {
        this.giftTime = giftTime == null ? null : giftTime.trim();
    }

    public Integer getMedalCount() {
        return medalCount;
    }

    public void setMedalCount(Integer medalCount) {
        this.medalCount = medalCount;
    }

    public Integer getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(Integer goldCount) {
        this.goldCount = goldCount;
    }

    public Double getFirstCost() {
        return firstCost;
    }

    public void setFirstCost(Double firstCost) {
        this.firstCost = firstCost;
    }

    public String getFirstCostTime() {
        return firstCostTime;
    }

    public void setFirstCostTime(String firstCostTime) {
        this.firstCostTime = firstCostTime == null ? null : firstCostTime.trim();
    }

    public String getNewRenewItem() {
        return newRenewItem;
    }

    public void setNewRenewItem(String newRenewItem) {
        this.newRenewItem = newRenewItem == null ? null : newRenewItem.trim();
    }

    @Override
    public Integer getShareId() {
        return playerId;
    }

    @Override
    public void setShareId(Integer shareId) {
        playerId = shareId;
    }

    @Override
    public Integer getId() {
        return playerId;
    }

    @Override
    public void setId(Integer id) {
        playerId = id;
    }

    @Override
    public Integer getShareAmount() {
        return null;
    }
}