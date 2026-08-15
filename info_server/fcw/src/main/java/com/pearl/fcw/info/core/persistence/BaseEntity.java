package com.pearl.fcw.info.core.persistence;

import java.io.Serializable;
import java.util.Date;

public interface BaseEntity extends Serializable {

    long getId();

    void setId(long id);

    void setCreateTime(Date createTime);

    void setUpdateTime(Date updateTime);

    void setDeleteTime(Date deleteTime);

}
