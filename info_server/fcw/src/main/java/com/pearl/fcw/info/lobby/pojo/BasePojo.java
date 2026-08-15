package com.pearl.fcw.info.lobby.pojo;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.pearl.fcw.info.core.persistence.BaseEntity;

@MappedSuperclass
public abstract class BasePojo implements BaseEntity, Cloneable {

    private static final long serialVersionUID = 5979630953714473567L;

    protected Date createTime;
    protected Date updateTime;
    protected Date deleteTime;
    protected boolean isDeleted = false;

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    @Override
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
