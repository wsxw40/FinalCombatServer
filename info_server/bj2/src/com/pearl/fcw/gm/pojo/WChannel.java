package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WChannel extends DmModel{

    private static final long serialVersionUID = 6003371560296460704L;

    private Integer id;

    private Integer serverId;

    private String name;

    private Integer maxLevel;

    private Integer minLevel;

    private Integer maxOnline;

    private Integer channelId;

    private Integer isTcp;

    private Integer maxTeamLevel;

    private Integer minTeamLevel;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Integer getMaxOnline() {
        return maxOnline;
    }

    public void setMaxOnline(Integer maxOnline) {
        this.maxOnline = maxOnline;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getIsTcp() {
        return isTcp;
    }

    public void setIsTcp(Integer isTcp) {
        this.isTcp = isTcp;
    }

    public Integer getMaxTeamLevel() {
        return maxTeamLevel;
    }

    public void setMaxTeamLevel(Integer maxTeamLevel) {
        this.maxTeamLevel = maxTeamLevel;
    }

    public Integer getMinTeamLevel() {
        return minTeamLevel;
    }

    public void setMinTeamLevel(Integer minTeamLevel) {
        this.minTeamLevel = minTeamLevel;
    }
}