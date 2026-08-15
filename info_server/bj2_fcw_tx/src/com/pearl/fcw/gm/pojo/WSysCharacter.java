package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysCharacter extends DmModel {

    private static final long serialVersionUID = 981580901977426907L;

    private Integer id;

    private Float runSpeed;

    private Float walkSpeed;

    private Float crouchSpeed;

    private Float accelSpeed;

    private Float jumpSpeed;

    private Float throwSpeed;

    private String resourceP;

    private String isDefault;

    private String isDeleted;

    private Integer cost;

    private String name;

    private Integer maxHp;

    private Integer exHp;

    private Integer level;

    private Integer isFired;

    private String resourceName;

    private Integer isEnable;

    private Float controllerHeight;

    private Float controllerRadius;

    private Float controllerCrouchHeight;

    private Integer scoreOffset;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(Float runSpeed) {
        this.runSpeed = runSpeed;
    }

    public Float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(Float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public Float getCrouchSpeed() {
        return crouchSpeed;
    }

    public void setCrouchSpeed(Float crouchSpeed) {
        this.crouchSpeed = crouchSpeed;
    }

    public Float getAccelSpeed() {
        return accelSpeed;
    }

    public void setAccelSpeed(Float accelSpeed) {
        this.accelSpeed = accelSpeed;
    }

    public Float getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(Float jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public Float getThrowSpeed() {
        return throwSpeed;
    }

    public void setThrowSpeed(Float throwSpeed) {
        this.throwSpeed = throwSpeed;
    }

    public String getResourceP() {
        return resourceP;
    }

    public void setResourceP(String resourceP) {
        this.resourceP = resourceP == null ? null : resourceP.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }

    public Integer getExHp() {
        return exHp;
    }

    public void setExHp(Integer exHp) {
        this.exHp = exHp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsFired() {
        return isFired;
    }

    public void setIsFired(Integer isFired) {
        this.isFired = isFired;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Float getControllerHeight() {
        return controllerHeight;
    }

    public void setControllerHeight(Float controllerHeight) {
        this.controllerHeight = controllerHeight;
    }

    public Float getControllerRadius() {
        return controllerRadius;
    }

    public void setControllerRadius(Float controllerRadius) {
        this.controllerRadius = controllerRadius;
    }

    public Float getControllerCrouchHeight() {
        return controllerCrouchHeight;
    }

    public void setControllerCrouchHeight(Float controllerCrouchHeight) {
        this.controllerCrouchHeight = controllerCrouchHeight;
    }

    public Integer getScoreOffset() {
        return scoreOffset;
    }

    public void setScoreOffset(Integer scoreOffset) {
        this.scoreOffset = scoreOffset;
    }
}