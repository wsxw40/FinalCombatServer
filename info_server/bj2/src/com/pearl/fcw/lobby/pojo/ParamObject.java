package com.pearl.fcw.lobby.pojo;

import java.io.Serializable;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.NEST)
public class ParamObject<T> implements Serializable {

    private static final long serialVersionUID = -4534458772471700186L;
    private T value;

    public ParamObject() {

    }

    public ParamObject(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
