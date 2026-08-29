package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Weapon extends BasePojo {

    private static final long serialVersionUID = 6789992907007133182L;

    protected float xDamageRate; // 伤害系数
    protected float xFireSpeed = 0F;// 开火速度
    protected float xBaseDamage = 0F; // 基础伤害
    protected float xLife = 0F; // 生命
    protected float xControlTurnSpeed; // 摇杆锁敌转身速度
    protected float xAutoTurnSpeed; // 自动锁定敌转身速度

    public float getxBaseDamage() {
        return xBaseDamage;
    }

    public void setxBaseDamage(float xBaseDamage) {
        this.xBaseDamage = xBaseDamage;
    }

    public float getxFireSpeed() {
        return xFireSpeed;
    }

    public void setxFireSpeed(float xFireSpeed) {
        this.xFireSpeed = xFireSpeed;
    }

    public float getXFireSpeed() {
        return xFireSpeed;
    }

    public void setXFireSpeed(float xFireSpeed) {
        this.xFireSpeed = xFireSpeed;
    }

    public float getXBaseDamage() {
        return xBaseDamage;
    }

    public void setXBaseDamage(float xBaseDamage) {
        this.xBaseDamage = xBaseDamage;
    }

    public float getxLife() {
        return xLife;
    }

    public void setxLife(float xLife) {
        this.xLife = xLife;
    }

    public float getXLife() {
        return xLife;
    }

    public void setXLife(float xLife) {
        this.xLife = xLife;
    }

    public float getXDamageRate() {
        return xDamageRate;
    }

    public void setXDamageRate(float xDamageRate) {
        this.xDamageRate = xDamageRate;
    }

    public float getxDamageRate() {
        return xDamageRate;
    }

    public void setxDamageRate(float xDamageRate) {
        this.xDamageRate = xDamageRate;
    }

    public float getxControlTurnSpeed() {
        return xControlTurnSpeed;
    }

    public void setxControlTurnSpeed(float xControlTurnSpeed) {
        this.xControlTurnSpeed = xControlTurnSpeed;
    }

    public float getxAutoTurnSpeed() {
        return xAutoTurnSpeed;
    }

    public void setxAutoTurnSpeed(float xAutoTurnSpeed) {
        this.xAutoTurnSpeed = xAutoTurnSpeed;
    }

    public float getXControlTurnSpeed() {
        return xControlTurnSpeed;
    }

    public void setXControlTurnSpeed(float xControlTurnSpeed) {
        this.xControlTurnSpeed = xControlTurnSpeed;
    }

    public float getXAutoTurnSpeed() {
        return xAutoTurnSpeed;
    }

    public void setXAutoTurnSpeed(float xAutoTurnSpeed) {
        this.xAutoTurnSpeed = xAutoTurnSpeed;
    }

}
