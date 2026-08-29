package com.pearl.fcw.info.core.persistence.route.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.config.CacheStrategy;
import com.pearl.fcw.info.core.persistence.config.ClassMetadata;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;
import com.pearl.fcw.info.core.persistence.route.MultiSourceRouter;
import com.pearl.fcw.info.core.persistence.route.SingleSourceRouter;

public abstract class CacheSourceRouter {

    protected static final Logger log = LoggerFactory.getLogger("cache");

    private static final Map<String, CacheSourceRouter> allCacheSourceRouter = new ConcurrentHashMap<String, CacheSourceRouter>();

    private final ScheduledExecutorService scanThreadPool = Executors.newScheduledThreadPool(2);

    private volatile boolean full;                      // 脏数据是否达到阀值，下一次扫描将立刻同步进数据库
    private long lastTimeSyncMillSecs = 0L;
    private int maxDirty = 1000;                        // 最大脏数据的期望阀值，实际值为略微超过这个值，只在每次扫描时判断当前脏数据是否已超过阀值
    private int intervalSecs = 60 * 10;                 // 期望每次同步的更新间隔秒数
    private int scanIntervalSecs = intervalSecs / 2;    // 扫描频率，代表最忙情况下，进行数据库同步的频率,和maxDirty联合，最忙情况就是按该频率同步maxDirty条数据

    private final AtomicBoolean started = new AtomicBoolean(false);
    private final Set<String> dirtyKeySet = new HashSet<String>();  // 存放脏数据的KEY，定时更新,注意在所有重要访问时同步，因为flush到DB时会同步并转移数据

    protected RedisClient cacheClient = null;
    protected InfoServerLock infoServerLock = null;

    public CacheSourceRouter(RedisClient cacheClient, InfoServerLock infoServerLock) {
        this.cacheClient = cacheClient;
        this.infoServerLock = infoServerLock;
    }

    public void setMaxDirty(int maxDirty) {
        this.maxDirty = maxDirty;
    }

    public void setIntervalSecs(int intervalSecs) {
        this.intervalSecs = intervalSecs;
    }

    public void setScanIntervalSecs(int scanIntervalSecs) {
        this.scanIntervalSecs = scanIntervalSecs;
    }

    public void setCacheClient(RedisClient cacheClient) {
        this.cacheClient = cacheClient;
    }

    public void setInfoServerLock(InfoServerLock infoServerLock) {
        this.infoServerLock = infoServerLock;
    }

    public synchronized static CacheSingleSourceRouter getCacheSourceRouter(SingleSourceRouter singleSourceRouter, RedisClient cacheClient, InfoServerLock infoServerLock, int intervalSecs,
            int scanIntervalSecs, int maxDirty) {
        CacheSingleSourceRouter sourceRouter = (CacheSingleSourceRouter) allCacheSourceRouter.get(singleSourceRouter.getSchema());
        if (sourceRouter == null) {
            sourceRouter = new CacheSingleSourceRouter(singleSourceRouter, cacheClient, infoServerLock);
            sourceRouter.setIntervalSecs(intervalSecs);
            sourceRouter.setScanIntervalSecs(scanIntervalSecs);
            sourceRouter.setMaxDirty(maxDirty);
            sourceRouter.init();
        }
        return sourceRouter;
    }

    public synchronized static CacheMultiSourceRouter getCacheSourceRouter(MultiSourceRouter multiSourceRouter, RedisClient cacheClient, InfoServerLock infoServerLock, int intervalSecs,
            int scanIntervalSecs, int maxDirty) {
        CacheMultiSourceRouter sourceRouter = (CacheMultiSourceRouter) allCacheSourceRouter.get(multiSourceRouter.getSchema());
        if (sourceRouter == null) {
            sourceRouter = new CacheMultiSourceRouter(multiSourceRouter, cacheClient, infoServerLock);
            sourceRouter.setIntervalSecs(intervalSecs);
            sourceRouter.setScanIntervalSecs(scanIntervalSecs);
            sourceRouter.setMaxDirty(maxDirty);
            sourceRouter.init();
        }
        return sourceRouter;
    }

    public synchronized void init() {

        for (ClassMetadata cm : ClassMetadataConfig.getBySchema(getSchema())) {

            // 约定1： 检测数据实体的缓存过期时间是否大于脏数据同步间隔，若数据实体缓存过期时间小于实际同步间隔，则可能发生在同步时，该数据在缓存中已失效，无法获得
            if (CacheStrategy.MEMCACHED.equals(cm.getCacheStrategy()) && cm.getCacheTimeoutSecs() <= scanIntervalSecs + intervalSecs) {
                log.warn("cache live time of [" + cm.getJavaClass() + "] is too short!!!!!!!!!!!!!!!!!!!!!!!");
                throw new RuntimeException(getSchema() + " CacheSourceRouter init fail!");
            }

        }

        if (started.compareAndSet(false, true)) {
            scanThreadPool.scheduleWithFixedDelay(new Scan(), 5, scanIntervalSecs, TimeUnit.SECONDS);
            log.info(getSchema() + " CacheSourceRouter start!");
        } else {
            log.error(getSchema() + " CacheSourceRouter start fail!");
        }

        if (!allCacheSourceRouter.containsKey(getSchema())) {
            allCacheSourceRouter.put(getSchema(), this);
        } else {
            throw new RuntimeException("duplicate CacheSourceRouter, " + getSchema());
        }

    }

    public synchronized void destroy() {
        if (allCacheSourceRouter.containsKey(getSchema())) {
            log.info(getSchema() + " CacheSingleSourceRouter shutdown!");
            scanThreadPool.shutdown();
            flush();
            allCacheSourceRouter.remove(getSchema());
        }
    }

    protected String getLockKey() {
        return "cacheRouter_" + getSchema();
    }

    public void flush() {

        boolean isLocked = false;
        try {
            isLocked = infoServerLock.tryLock(getLockKey(), 30 * 1000);
        } catch (Exception e) {
        }

        if (isLocked) {
            try {

                Set<String> tempSet = null;
                synchronized (dirtyKeySet) {
                    tempSet = new HashSet<String>(dirtyKeySet);
                    dirtyKeySet.clear();
                }

                Map<String, Object> pojos = null;
                try {
                    pojos = cacheClient.get(tempSet);
                } catch (Exception e) {
                    log.warn("error happend during multi get cache obj in flush ", e);
                }

                // 在复制脏数据ID之后，处理数据之前，发生删除操作，必会出现NULL的情况。在多台INFO的情况下，该情况概率更大。
                //                // 对获取的需要更新的脏数据进行判空,某些情况下，可能会出现某个KEY对应的VALUE已为NULL，这应该为BUG，或同步设置时间不正确，记录进LOG
                //                for (Map.Entry<String, Object> entry : pojos.entrySet()) {
                //                    if (entry.getValue() == null) {
                //                        log.warn("get NULL pojo during flush! key is " + entry.getKey());
                //                    }
                //                }

                long s = System.currentTimeMillis();
                log.info(getSchema() + ", begin syn cache to DB, keys " + pojos.size());
                batchUpdateCache(pojos.values());
                log.info(getSchema() + ", syn cache to DB finished in " + (System.currentTimeMillis() - s) + "ms");

            } catch (Exception e) {
                log.warn("error happend during flush " + getSchema() + " cache obj into DB", e);
            } finally {
                lastTimeSyncMillSecs = System.currentTimeMillis();
                full = false;
                infoServerLock.unlock(getLockKey());
            }
        } else {
            log.warn(getSchema() +  "cache source router try lock fail!");
        }

    }

    public static void flushAll() {
        for (CacheSourceRouter router : allCacheSourceRouter.values()) {
            try {
                router.flush();
            } catch (Exception e) {
                log.warn("error happend during flush cache obj into DB", e);
            }
        }
    }

    public static void flushAndDestory() {
        List<CacheSourceRouter> list = new ArrayList<CacheSourceRouter>(allCacheSourceRouter.values());
        for (CacheSourceRouter cacheSourceRouter : list) {
            try {
                cacheSourceRouter.destroy();
            } catch (Exception e) {
                log.warn("error happend during flushAndDestory, schema " + cacheSourceRouter.getSchema(), e);
            }
        }
    }

    private class Scan implements Runnable {
        @Override
        public void run() {
            if (full || (System.currentTimeMillis() - lastTimeSyncMillSecs) / 1000 > intervalSecs) {
                flush();
            }
        }
    }

    protected <T extends BaseEntity> void createInCache(T entity) {

        Class<?> clazz = entity.getClass();
        ClassMetadataWrapper<T> cmw = ClassMetadataConfig.getClassMetadataWrapper(clazz);

        // all
        String cacheKeyForAll =  CacheKeyUtil.getCacheKeyForAll(clazz);
        cacheClient.delete(cacheKeyForAll);

        // fk
        Set<String> allForeignKeys = CacheKeyUtil.getAllForeignKeysByPojo(entity, cmw);
        for (String s : allForeignKeys) {
            cacheClient.delete(s);
        }

        // me
        String key = CacheKeyUtil.getCacheKeyById(entity.getId(), clazz);
        cacheClient.setex(key, cmw.getClassMetadata().getCacheTimeoutSecs(), entity);

    }

    protected <T extends BaseEntity> boolean updateInCache(T entity) {

        Class<?> clazz = entity.getClass();
        ClassMetadata cm = ClassMetadataConfig.getClassMetadata(clazz);

        String key = CacheKeyUtil.getCacheKeyById(entity.getId(), clazz);
        if (dirtyKeySet.size() >= maxDirty) {
            full = true;
        }

        //        // all
        //        String cacheKeyForAll =  CacheKeyUtil.getCacheKeyForAll(clazz);
        //        cacheClient.delete(cacheKeyForAll);

        try {
            cacheClient.setex(key, cm.getCacheTimeoutSecs(), entity);
        } catch (Exception e) {
            return false;
        }

        synchronized(dirtyKeySet){
            dirtyKeySet.add(key);
        }

        return true;

    }

    protected <T extends BaseEntity> void updateInCacheOnly(T entity) {

        Class<?> clazz = entity.getClass();
        ClassMetadata cm = ClassMetadataConfig.getClassMetadata(clazz);

        String key = CacheKeyUtil.getCacheKeyById(entity.getId(), clazz);

        //        // all
        //        String cacheKeyForAll =  CacheKeyUtil.getCacheKeyForAll(clazz);
        //        cacheClient.delete(cacheKeyForAll);

        try {
            cacheClient.setex(key, cm.getCacheTimeoutSecs(), entity);
        } catch (Exception e) {

        }

    }

    protected <T extends BaseEntity> boolean updateWithFKInCache(T entity) {

        // fk
        ClassMetadataWrapper<T> cmw = ClassMetadataConfig.getClassMetadataWrapper(entity.getClass());
        Set<String> allForeignKeys = CacheKeyUtil.getAllForeignKeysByPojo(entity, cmw);
        for (String s : allForeignKeys) {
            cacheClient.delete(s);
        }

        return updateInCache(entity);

    }

    protected <T extends BaseEntity> void deleteInCache(T entity) {

        Class<?> clazz = entity.getClass();
        ClassMetadataWrapper<T> cmw = ClassMetadataConfig.getClassMetadataWrapper(clazz);

        // all
        String cacheKeyForAll =  CacheKeyUtil.getCacheKeyForAll(clazz);
        cacheClient.delete(cacheKeyForAll);

        // fk
        Set<String> allForeignKeys = CacheKeyUtil.getAllForeignKeysByPojo(entity, cmw);
        for (String s : allForeignKeys) {
            cacheClient.delete(s);
        }

        // me
        String key = CacheKeyUtil.getCacheKeyById(entity.getId(), clazz);
        synchronized(dirtyKeySet){
            dirtyKeySet.remove(key);
        }
        cacheClient.delete(key);

    }

    public abstract String getSchema();

    protected abstract void batchUpdateCache(Collection<?> entities);

}
