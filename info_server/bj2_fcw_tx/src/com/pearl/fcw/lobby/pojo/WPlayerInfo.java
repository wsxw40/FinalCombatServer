package com.pearl.fcw.lobby.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.EXT)
public class WPlayerInfo extends DmModel{

    private static final long serialVersionUID = -3998644187344894164L;

    private Integer id;

    private Integer playerId;

    private Integer xunleiPoint;

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

    public Integer getXunleiPoint() {
        return xunleiPoint;
    }

    public void setXunleiPoint(Integer xunleiPoint) {
        this.xunleiPoint = xunleiPoint;
    }
}