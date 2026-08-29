package com.pearl.fcw.lobby.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.NEST)
public class TmpGunProperty implements Serializable {

    private static final long serialVersionUID = -5272093976402716822L;
    private Map<String, TmpGunProperty1> map = new HashMap<>();

    public Map<String, TmpGunProperty1> getMap() {
        return map;
    }

    public void setMap(Map<String, TmpGunProperty1> map) {
        this.map = map;
    }
}
