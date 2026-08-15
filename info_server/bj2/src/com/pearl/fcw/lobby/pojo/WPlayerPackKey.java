package com.pearl.fcw.lobby.pojo;

import com.pearl.fcw.core.pojo.DmExtModel;

public class WPlayerPackKey extends DmExtModel{

    private static final long serialVersionUID = -6273847002725287046L;

    private Integer id;

    private Integer sysCharacterId;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysCharacterId() {
        return sysCharacterId;
    }

    public void setSysCharacterId(Integer sysCharacterId) {
        this.sysCharacterId = sysCharacterId;
    }

    @Override
    public Integer getShareId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setShareId(Integer shareId) {
        // TODO Auto-generated method stub

    }
}