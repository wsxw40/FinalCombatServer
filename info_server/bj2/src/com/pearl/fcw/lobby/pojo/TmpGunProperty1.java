package com.pearl.fcw.lobby.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.NEST)
public class TmpGunProperty1 implements Serializable {

    private static final long serialVersionUID = -7904454822574873503L;
    private List<TmpGunProperty2> list = new ArrayList<>();

    public TmpGunProperty1() {
    }

    public TmpGunProperty1(List<TmpGunProperty2> list) {
        this.list = list;
    }

    public List<TmpGunProperty2> getList() {
        return list;
    }

    public void setList(List<TmpGunProperty2> list) {
        this.list = list;
    }
}
