package com.pearl.fcw.core.pojo;

import java.io.Serializable;

/**
 * 数据库逻辑数据接口
 */
public interface BaseEntity extends Serializable {
    Integer getId();

    void setId(Integer id);

    Long getDbVersion();

    void setDbVersion(Long dbVersion);
}
