package com.pearl.manager.utils;

public interface InfoServerLock {

    /**
     * 尝试获取互斥锁，此方法不会抛出异常
     *
     *
     * @param key
     *
     * @param timeoutMillis 尝试获取锁的毫秒间隔，超过此时间则放弃
     * @return true 成功， false 失败
     */
    boolean tryLock(String key, long timeoutMillis);



    /**
     * 解锁
     * @param key
     */
    void unlock(String key);
}
