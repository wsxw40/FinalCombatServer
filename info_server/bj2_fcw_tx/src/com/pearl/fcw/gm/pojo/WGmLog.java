package com.pearl.fcw.gm.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.GM)
public class WGmLog extends DmModel {

    private static final long serialVersionUID = 5942561223580616755L;

    private Integer id;

    private Integer gmUserId;

    private String gmUserName;

    private String type;

    private String methodName;

    private Date recordTime;

    private String args;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGmUserId() {
        return gmUserId;
    }

    public void setGmUserId(Integer gmUserId) {
        this.gmUserId = gmUserId;
    }

    public String getGmUserName() {
        return gmUserName;
    }

    public void setGmUserName(String gmUserName) {
        this.gmUserName = gmUserName == null ? null : gmUserName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }
}