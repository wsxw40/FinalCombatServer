package com.pearl.fcw.info.lobby.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.config.annotation.Index;
import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.lobby.pojo.enums.PlayerState;

@Entity
@Schema(GoSchema.MAIN)
public class Player extends BasePojo {

    private static final long serialVersionUID = -3609150921466192707L;

    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private String userName;
    @Index(name = "PLAYER_NAME", length = 8)
    private String name = "";
    private long sysCharacterId;
    private String password;
    private String mac;
    private Date activateTime = null;   // 激活日期
    private Date lastLoginTime;
    private Date lastLogoutTime;
    private String lastLoginIp;
    private String lastLoginDevice;
    @Index(name = "PLAYER_EXP")
    private int exp = 0;
    private int gold = 0; // 金币
    private int diamond; // 钻石
    private int power; // 能源
    private int arena; // 竞技场货币
    private String avatar; // 头像
    private int skillPoint;
    private int life;
    private float damage; // 角色攻击力
    private int vipLevel;// VIP等级

    private Date banStartTime = null; // 封停开始时间
    private Date banEndTime = null; // 封停结束时间
    private String banReason = ""; // 封停原因
    private int banCount = 0;    // 已经被封停的次数

    @ManyToOne
    private transient User user;

    @Transient
    private int onlineState = PlayerState.OFFLINE.getValue();
    @Transient
    private String appStore; // 渠道号
    @Transient
    private String clientVersion; // 版本号

    @Override
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSysCharacterId() {
        return sysCharacterId;
    }

    public void setSysCharacterId(long sysCharacterId) {
        this.sysCharacterId = sysCharacterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime(Date lastLogoutTime) {
        this.lastLogoutTime = lastLogoutTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginDevice() {
        return lastLoginDevice;
    }

    public void setLastLoginDevice(String lastLoginDevice) {
        this.lastLoginDevice = lastLoginDevice;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getArena() {
        return arena;
    }

    public void setArena(int arena) {
        this.arena = arena;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Date getBanStartTime() {
        return banStartTime;
    }

    public void setBanStartTime(Date banStartTime) {
        this.banStartTime = banStartTime;
    }

    public Date getBanEndTime() {
        return banEndTime;
    }

    public void setBanEndTime(Date banEndTime) {
        this.banEndTime = banEndTime;
    }

    public String getBanReason() {
        return banReason;
    }

    public void setBanReason(String banReason) {
        this.banReason = banReason;
    }

    public int getBanCount() {
        return banCount;
    }

    public void setBanCount(int banCount) {
        this.banCount = banCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(int onlineState) {
        this.onlineState = onlineState;
    }

    public String getAppStore() {
        return appStore;
    }

    public void setAppStore(String appStore) {
        this.appStore = appStore;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

}
