package com.pearl.fcw.core.pojo;

/**
 * 从逻辑表接口
 */
public interface BaseExtEntity extends BaseEntity {
    /**
     * 获取该从逻辑数据对应主逻辑数据的ID
     * @return
     */
    Integer getShareId();

    /**
     * 获取每张从逻辑表包含的主逻辑主键的最大数量。null或者小于等于0表示无限制
     * @return
     */
    default Integer getShareAmount() {
        return 10000;
    }

    /**
     * 获取从逻辑实际分表后缀
     * @return
     */
    default String getTableSuffix() {
        if (null == getShareAmount() || getShareAmount() <= 0) {
            return "";
        }
        int result = getShareId() / getShareAmount() + (getShareId() % getShareAmount() > 0 ? 1 : 0);
        result = result > getMaxShare() ? getMaxShare() : result;
        return result + "";
    }

    /**
     * 获取最大分表数量
     * @return
     */
    default Integer getMaxShare() {
        return 400;
    }
}
