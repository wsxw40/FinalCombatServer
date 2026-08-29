package com.pearl.fcw.lobby.pojo;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerBuff extends DmExtModel {

    private static final long serialVersionUID = -5298328567135514685L;

    private Integer id;

    private Integer userId;

    private Integer playerId;

    private Integer playerItemId;

    private Short buffId;

    private String backUp;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public Short getBuffId() {
        return buffId;
    }

    public void setBuffId(Short buffId) {
        this.buffId = buffId;
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