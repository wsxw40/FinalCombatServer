package com.pearl.fcw.core.cache;

/**
 * 生成远程和本地缓存的Key值
 */
public class CacheKey {
    /**
     * 分布式锁Key
     * @param key
     * @return
     */
    public String getDistributeLockedKey(Object key) {
        return "$lock|" + key;
    }

    /**
     * 本地缓存（Ehcache）使用的Key。
     * @param cls 类
     * @param id 主键
     * @return Key
     */
    public String getLocalDataKey(Class<?> cls, Object id) {
        return "$lc|" + cls.getSimpleName() + "|" + id;
    }

    /**
     * 本地缓存（Ehcache）保存从逻辑数据版本号使用的Key。
     * @param cls 类
     * @param shareId 主逻辑主键
     * @return
     */
    public String getLocalVersionShareKey(Class<?> cls, Object shareId) {
        return "$lv|" + cls.getSimpleName() + "|" + shareId;
    }

    /**
     * 本地缓存（Ehcache）保存从逻辑数据版本号的Key。<br/>
     * 对应的数据Map结构，Key：从逻辑主键，Value：从逻辑版本号
     * @param cls 类
     * @param shareId 主逻辑主键
     * @return Key
     */
    public String getLocalVersionMapKey(Class<?> cls, Object shareId) {
        return "$lvm|" + cls.getSimpleName() + "|" + shareId;
    }

    /**
     * 远程（Redis）保存版本号的Key。
     * @param cls 类
     * @param id 主键
     * @return Key
     */
    public String getRemoteVersionKey(Class<?> cls, Object id) {
        return "$rv|" + cls.getSimpleName() + "|" + id;
    }

    /**
     * 远程（Redis）保存从逻辑数据版本号的Key。
     * @param cls 类
     * @param shareId 主逻辑主键
     * @return Key
     */
    public String getRemoteVersionShareKey(Class<?> cls, Object shareId) {
        return "$rv|" + cls.getSimpleName() + "|" + shareId;
    }

    /**
     * 远程（Redis）保存从逻辑数据版本号的Key。<br/>
     * 对应的数据Map结构，Key：从逻辑主键，Value：从逻辑版本号
     * @param cls 类
     * @param shareId 主逻辑主键
     * @return Key
     */
    public String getRemoteVersionMapKey(Class<?> cls, Object shareId) {
        return "$rvm|" + cls.getSimpleName() + "|" + shareId;
    }

    /**
     * 远程（Redis）保存修改过的数据的Key。<br/>
     * 数据仅供Info之间同步使用，不写入数据库
     * @param cls 类
     * @param id 主键
     * @return Key
     */
    public String getRemoteDataKey(Class<?> cls, Object id) {
        return "$rc|" + cls.getSimpleName() + "|" + id;
    }

    /**
     * 本地刷新缓存时的锁Key。
     * @param cls 类
     * @param id 主键
     * @return Key
     */
    public String getLocalFlushWriteKey(Class<?> cls, Object id) {
        return "$fw|" + cls.getSimpleName() + "|" + id;
    }

    /**
     * 远程刷新缓存时的锁Key。
     * @param cls 类
     * @param id 主键
     * @return Key
     */
    public String getRemoteFlushWriteKey(Class<?> cls, Object id) {
        return "$fw|" + cls.getSimpleName() + "|" + id;
    }
}
