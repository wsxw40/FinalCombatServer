package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerPack extends WPlayerPackKey {

    private static final long serialVersionUID = 7521850385090886405L;

    private Integer playerId;

    private Integer playerItemId;

    private String type;

    private Short packId;

    private Short seq;

    private Date expireTime;

    private String backUp;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerItemId() {
        return playerItemId;
    }

    public void setPlayerItemId(Integer playerItemId) {
        this.playerItemId = playerItemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Short getPackId() {
        return packId;
    }

    public void setPackId(Short packId) {
        this.packId = packId;
    }

    public Short getSeq() {
        return seq;
    }

    public void setSeq(Short seq) {
        this.seq = seq;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getBackUp() {
        return backUp;
    }

    public void setBackUp(String backUp) {
        this.backUp = backUp == null ? null : backUp.trim();
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