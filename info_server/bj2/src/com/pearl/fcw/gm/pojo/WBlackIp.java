package com.pearl.fcw.gm.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.GM)
public class WBlackIp extends DmModel {

    private static final long serialVersionUID = -1306398620948381885L;

    private Integer id;

    private Integer ip;

    private String isBanned;

    private Integer bannedTime;

    private String description;

    private Date createTime;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }

    public String getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(String isBanned) {
        this.isBanned = isBanned == null ? null : isBanned.trim();
    }

    public Integer getBannedTime() {
        return bannedTime;
    }

    public void setBannedTime(Integer bannedTime) {
        this.bannedTime = bannedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}