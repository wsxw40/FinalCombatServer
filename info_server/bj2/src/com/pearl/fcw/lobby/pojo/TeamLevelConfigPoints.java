package com.pearl.fcw.lobby.pojo;

import java.io.Serializable;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.NEST)
public class TeamLevelConfigPoints implements Serializable {

    private static final long serialVersionUID = 1732860494953754180L;
    private Integer sysItemId;
    private Float x;
    private Float y;
    private Float z;
    private Float r1;
    private Float r2;
    private Float r3;
    private Float r4;

    public TeamLevelConfigPoints() {

    }

    public Integer getSysItemId() {
        return sysItemId;
    }

    public void setSysItemId(Integer sysItemId) {
        this.sysItemId = sysItemId;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public Float getR1() {
        return r1;
    }

    public void setR1(Float r1) {
        this.r1 = r1;
    }

    public Float getR2() {
        return r2;
    }

    public void setR2(Float r2) {
        this.r2 = r2;
    }

    public Float getR3() {
        return r3;
    }

    public void setR3(Float r3) {
        this.r3 = r3;
    }

    public Float getR4() {
        return r4;
    }

    public void setR4(Float r4) {
        this.r4 = r4;
    }

}
