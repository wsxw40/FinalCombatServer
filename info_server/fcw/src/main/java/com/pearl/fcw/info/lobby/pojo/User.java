package com.pearl.fcw.info.lobby.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pearl.fcw.info.core.persistence.config.annotation.Index;
import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.MAIN)
public class User extends BasePojo {

    private static final long serialVersionUID = 1568100242090914395L;

    @Id
    @GeneratedValue
    private long id;
    @Index(name = "USER_NAME", length = 8)
    private String userName = null;
    private String password = null;
    private String lastLoginIp = "";
    private Date lastLoginTime = null;
    private Date activateTime = null;   // 激活日期

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public boolean getIsActivate() {
        return this.activateTime != null;
    }
}
