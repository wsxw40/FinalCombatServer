package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.o2o.utils.ConfigurationUtil;

@GoSchema(type = Schema.EXT)
public class WBuyitemRecord extends DmExtModel {

    private static final long serialVersionUID = 2532058150011703039L;

    private Integer id;

    private Integer playerId;

    private Integer itemId;

    private Integer costId;

    private Integer record;

    private Integer payType;

    private Integer payAmount;

    private Date lastBuyDate;

    private Date createTime;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Date getLastBuyDate() {
        return lastBuyDate;
    }

    public void setLastBuyDate(Date lastBuyDate) {
        this.lastBuyDate = lastBuyDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    public Integer getShareAmount() {
        return 10 * Integer.parseInt(ConfigurationUtil.DEFAULT_DB_NUM);
    }
}