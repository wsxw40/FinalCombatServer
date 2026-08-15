package com.pearl.fcw.info.core.persistence;

import java.io.Serializable;

public interface BaseEntity extends Serializable {

	int getId();

    void setId(int id);

//    void setCreateTime(long createTime);
//
//    void setUpdateTime(long updateTime);
//
//    void setDeleteTime(long deleteTime);
}
