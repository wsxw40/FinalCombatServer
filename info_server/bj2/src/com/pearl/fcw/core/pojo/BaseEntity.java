package com.pearl.fcw.core.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库逻辑数据接口
 */
public interface BaseEntity extends Serializable {
    Integer getId();

    void setId(Integer id);

    Long getDbVersion();

    void setDbVersion(Long dbVersion);

    /**
	 * 是否已逻辑删除
	 * @return
	 */
    default boolean getIsRemoved() {
        return false;
    }

    /**
	 * 设置是否已逻辑删除
	 * @param isRemoved
	 */
    default void setIsRemoved(boolean isRemoved) {
    }

	/**
	 * 获取创建时间
	 * @return
	 */
	default Date getCreateTime() {
		return null;
	}

	/**
	 * 设置创建时间
	 * @param date
	 */
	default void setCreateTime(Date date) {

	}

	/**
	 * 获取修改时间
	 * @return
	 */
	default Date getUpdateTime() {
		return null;
	}

	/**
	 * 设置修改时间
	 * @param date
	 */
	default void setUpdateTime(Date date) {

	}
}
