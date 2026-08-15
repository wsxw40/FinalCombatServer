package com.pearl.fcw.core.cache;

import java.beans.PropertyDescriptor;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.proxy.CacheEntry;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.Operation;
import com.pearl.fcw.core.pojo.proxy.Operation.Type;

/**
 * 代理实例操作缓冲区。
 * 负责记录客户端的修改数据操作信息，并定时向数据库写入操作信息
 */
public class OperationCache {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private CacheKey cacheKey;
    private Ehcache localCache;
    private RedisClient redisClient;

    private final ConcurrentLinkedDeque<String> keys = new ConcurrentLinkedDeque<>();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	private final ConcurrentLinkedDeque<EntityProxy<? extends BaseEntity>> failCommitData = new ConcurrentLinkedDeque<>();//事务提交修改数据失败后临时储存失败数据的容器
	private int databaseFlushSeconds = 15;//缓存写入数据库的时间，秒
	private int databaseFlushRetryMillis = 500;//缓存写入数据库时争取分布式锁失败后重试的时间间隔，毫秒
	private int databaseFlushIntervalSeconds = 15 * 60;//缓存写入数据库的时间间隔，秒
	private static final long LOCAL_CACHE_WRITE_LOCK_MILLIS = 5;//本地缓存写入数据的加锁时间

    /***
	 * @param cacheKey
	 * @param localCache
	 * @param redisClient
	 * @param databaseFlushSeconds 每次写入数据库的时长，秒。最好小于databaseFlushIntervalSeconds的一半
	 * @param databaseFlushIntervalSeconds 每次向数据库写入数据的时间间隔，秒
	 */
    public OperationCache(CacheKey cacheKey, Ehcache localCache, RedisClient redisClient, int databaseFlushSeconds, int databaseFlushIntervalSeconds) {
        this.cacheKey = cacheKey;
        this.localCache = localCache;
        this.redisClient = redisClient;
        this.databaseFlushSeconds = databaseFlushSeconds;
        this.databaseFlushIntervalSeconds = databaseFlushIntervalSeconds;
        executor.scheduleWithFixedDelay(this::flush, this.databaseFlushIntervalSeconds, this.databaseFlushIntervalSeconds, TimeUnit.SECONDS);
    }

    /**
	 * 记录写入Ehcache的数据
	 * @param key 保存在Ehcache中的Key
	 * @param proxyM 代理实例。不为null时表示该Key对应的数据写入Ehcache失败
	 * @throws Exception
	 */
    public void put(String key, EntityProxy<? extends BaseEntity> proxyM) throws Exception {
        keys.addLast(key);
        if (null != proxyM) {
            failCommitData.addLast(proxyM);
            logger.info("commit fail : " + key);
        }
        supply();
    }

    /**
	 * 将先前事务提交时写入缓存失败的数据重新写入缓存
	 * @throws Exception
	 */
    public void supply() throws Exception {
        if (!failCommitData.isEmpty()) {
            logger.info("commit fail count : " + failCommitData.size());
            EntityProxy<? extends BaseEntity> proxyM = failCommitData.pollFirst();
            if (null == proxyM) {
                return;
            }
            String key = cacheKey.getLocalDataKey(proxyM.get().getClass(), proxyM.get().getId());
            if (!writeLocalCacheProxy(key, proxyM)) {
                failCommitData.addLast(proxyM);
                logger.info("another commit fail : " + key);
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void flush() {
        for (int i = 0; i < failCommitData.size(); i++) {
            try {
                supply();
            } catch (Exception e) {
                logger.error(e.toString());
            }
        }
		long expireTime = System.currentTimeMillis() + databaseFlushSeconds * 1000;//可以写入数据库的最后时间

        logger.info("Begin flush database");
        String lockedKey = cacheKey.getDistributeLockedKey("flush");
        logger.info("flush count : " + keys.size());
        while (!keys.isEmpty()) {
            if (System.currentTimeMillis() > expireTime) {
                logger.info("End flush database because of expireTime.");
                break;
            }
			//获取分布式锁失败后线程休眠
            if (!redisClient.setIfAbsentMillisecond(lockedKey, 1, databaseFlushSeconds * 1000)) {
                try {
                    Thread.sleep(databaseFlushRetryMillis);
                    continue;
                } catch (InterruptedException e) {
                    logger.error("Error has occurred when flush database.", e);
                }
            }
			//遍历缓存中修改过的DataKey，将数据的操作记录写入数据库
            String localDataKey = keys.pollFirst();
            try {
                CacheEntry<? extends BaseEntity> ce = readLocalCacheEntry(localDataKey);
				if (null == ce) {//获取本地缓存时争锁失败（可能是其他线程正在争锁），把该key值转移到链表尾端，待以后再次获取
                    keys.addLast(localDataKey);
                    continue;
                }
                BaseEntity m = ce.getEntity();
				if (null == m) {//本地缓存中无对应的数据库数据，该条数据忽略
                    continue;
                }
                Class<?> cls = m.getClass();
                while (!cls.isAnnotationPresent(GoSchema.class) && cls != DmModel.class) {
                    cls = cls.getSuperclass();
                }
                CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
				//获取数据库的当前数据
                m = dao.findEntity(m);
				m.setUpdateTime(ce.getEntity().getUpdateTime());//注意修改时间
				//将缓存中的操作记录写到数据库数据上
                //                logger.info("Flush key : " + localDataKey);
                for (Operation op : ce.getListener().getOperations()) {
                    //                    logger.info(op.getPath() + " : " + op.getType() + " - " + op.getValue());
                    onFlush(op, m);
                }
				//写入数据库
                m = dao.update(m);
            } catch (Exception e) {
                logger.error("Error has occurred when flush database for key " + localDataKey, e);
                continue;
            } finally {
                redisClient.del(lockedKey);
            }
        }

        logger.info("End flush database");
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void close() {
        try {
            while (!failCommitData.isEmpty()) {
                supply();
            }
            while (!keys.isEmpty()) {
				//遍历缓存中修改过的DataKey，将数据的操作记录写入数据库
                String localDataKey = keys.pollFirst();
                try {
                    CacheEntry<? extends BaseEntity> ce = readLocalCacheEntry(localDataKey);
					if (null == ce) {//获取本地缓存时争锁失败（可能是其他线程正在争锁），把该key值转移到链表尾端，待以后再次获取
                        keys.addLast(localDataKey);
                        continue;
                    }
                    BaseEntity m = ce.getEntity();
					if (null == m) {//本地缓存中无对应的数据库数据，该条数据忽略
                        continue;
                    }
                    CacheDao dao = CacheDao.getModelAndDaoMap().get(m.getClass());
					//获取数据库的当前数据
                    m = dao.findEntity(m);
					//将缓存中的操作记录写到数据库数据上
                    //                logger.info("Flush key : " + localDataKey);
                    for (Operation op : ce.getListener().getOperations()) {
                        //                    logger.info(op.getPath() + " : " + op.getType() + " - " + op.getValue());
                        onFlush(op, m);
                    }
					//写入数据库
                    m = dao.update(m);
                } catch (Exception e) {
                    logger.error("Error has occurred when flush database for key " + localDataKey, e);
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("OperationCache shutdown error : ", e);
        }
        logger.info("OperationCache shutdown successfully");
    }

    /**
	 * 读取本地缓存代理实例的操作记录，有同步锁
	 * 读取操作记录后，本地缓存该代理实例的操作记录被清空
	 * @param key
	 * @return 返回代理实例对应的数据库数据以及操作记录。返回null表示争锁失败，无法获取该Key值对应的数据库数据以及操作记录
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    private <M extends BaseEntity> CacheEntry<M> readLocalCacheEntry(String key) throws Exception {
        if (localCache.tryWriteLockOnKey(key, LOCAL_CACHE_WRITE_LOCK_MILLIS)) {
            try {
                Element element = localCache.getQuiet(key);
                if (null != element) {
                    CacheEntry<M> ce = (CacheEntry<M>) element.getObjectValue();
                    localCache.put(new Element(key, new CacheEntry<M>(ce.getEntity())));
                    return ce;
                } else {
                    return new CacheEntry<M>();
                }
            } catch (Exception e) {
                throw e;
            } finally {
                localCache.releaseWriteLockOnKey(key);
                //                localCache.flush();
            }
        }
        return null;
    }

    /**
	 * 写入本地缓存代理实例，有同步锁<br/>
	 * 代理实例的操作记录只能追加，不可覆盖
	 * @param key
	 * @param proxyM
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    private <M extends BaseEntity> boolean writeLocalCacheProxy(String key, EntityProxy<M> proxyM) throws Exception {
        if (localCache.tryWriteLockOnKey(key, LOCAL_CACHE_WRITE_LOCK_MILLIS)) {
            try {
                Element element = localCache.getQuiet(key);
                if (null != element) {
                    CacheEntry<M> ce = (CacheEntry<M>) element.getObjectValue();
                    ce.setEntity(proxyM.get());
                    ce.getListener().getOperations().addAll(proxyM.getListener().getOperations());
                    localCache.put(new Element(key, ce));
                } else {
                    localCache.put(new Element(key, new CacheEntry<>(proxyM.get(), proxyM.getListener())));
                }
            } catch (Exception e) {
                throw e;
            } finally {
                localCache.releaseWriteLockOnKey(key);
                //                localCache.flush();
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void onFlush(Operation op, BaseEntity m) throws Exception {
        PropertyDescriptor pd = null;
        List list = null;
        Map map = null;
        String[] pathArr = op.getPath().split("\\.");
        Object m0 = m;
        for (int i = 0; i < pathArr.length; i++) {
            String path = pathArr[i];
            if (m0 instanceof List) {
                list = (List) m0;
                if (i < pathArr.length - 1 || (i == pathArr.length - 1 && op.getType() == Type.ADD)) {
                    int index = Integer.parseInt(path);
                    m0 = list.get(index);
                }
            } else if (m0 instanceof Map) {
                map = (Map) m0;
                if (i < pathArr.length - 1 || (i == pathArr.length - 1 && op.getType() == Type.ADD)) {
                    m0 = map.get(path);
                }
            } else {
				if (DmModel.PATTERN_ONLY_FIRST_ONE_LOWER.matcher(path).find()) {//get，set的特殊规则：字段名只有首字母一个小写
                    pd = new PropertyDescriptor(path, m0.getClass(), "get" + path, "set" + path);
                } else {
                    pd = new PropertyDescriptor(path, m0.getClass());
                }
                if (i < pathArr.length - 1 || (i == pathArr.length - 1 && op.getType() == Type.ADD)) {
                    m0 = pd.getReadMethod().invoke(m0);
                }
            }
        }
        switch (op.getType()) {
        case INCREASE:
            if (null == op.getValue()) {
                return;
            }
            if (isSubClass(pd.getPropertyType(), Number.class)) {
                double value = 0;
				try {//null转0
                    value = Double.parseDouble(pd.getReadMethod().invoke(m0).toString());
                    value += Double.parseDouble(op.getValue().toString());
                } catch (Exception e) {
                }
                if (pd.getPropertyType().equals(Byte.class)) {
                    pd.getWriteMethod().invoke(m0, (byte) value);
                } else if (pd.getPropertyType().equals(Short.class)) {
                    pd.getWriteMethod().invoke(m0, (short) value);
                } else if (pd.getPropertyType().equals(Integer.class)) {
                    pd.getWriteMethod().invoke(m0, (int) value);
                } else if (pd.getPropertyType().equals(Long.class)) {
                    pd.getWriteMethod().invoke(m0, (long) value);
                } else if (pd.getPropertyType().equals(Float.class)) {
                    pd.getWriteMethod().invoke(m0, (float) value);
                } else if (pd.getPropertyType().equals(Double.class)) {
                    pd.getWriteMethod().invoke(m0, value);
                } else {
                    throw new Exception(m0.getClass() + " can not use INCREASE operation");
                }
            } else if (isSubClass(pd.getPropertyType(), Date.class)) {
                Date date = (Date) pd.getReadMethod().invoke(m0);
                Instant instant = date.toInstant().plusMillis(Long.parseLong(op.getValue().toString()));
                date = Date.from(instant);
                pd.getWriteMethod().invoke(m0, date);
            } else {
                throw new Exception(m0.getClass() + " can not use INCREASE operation");
            }
            break;
        case UPDATE:
        case SET:
            pd.getWriteMethod().invoke(m0, op.getValue());
            break;
        case ADD:
            list = (List) m0;
            if (null != op.getValue() && op.getValue().getClass().isArray()) {
                Object[] arr = (Object[]) op.getValue();
                for (Object o : arr) {
                    list.add(o);
                }
            } else {
                list.add(op.getValue());
            }
            break;
        case PUT:
            map = (Map) m0;
            map.put(pathArr[pathArr.length - 1], op.getValue());
            break;
        case REMOVE:
            if (isSubClass(pd.getPropertyType(), List.class)) {
                list = (List) m0;
                int index = Integer.parseInt(pathArr[pathArr.length - 1]);
                list.remove(index);
            } else if (isSubClass(pd.getPropertyType(), Map.class)) {
                map = (Map) m0;
                map.remove(pathArr[pathArr.length - 1]);
            } else {
                throw new Exception(pd.getPropertyType() + " can not use REMOVE operation");
            }
            break;
        default:
            break;
        }
    }

    /**
	 * 递归判断child类是否parent类的子类
	 * @param child
	 * @param parent
	 * @return
	 */
    private boolean isSubClass(Class<?> child, Class<?> parent) {
        boolean b = false;
        Class<?> cls = child;
        while (!cls.equals(Object.class)) {
            if (cls.equals(parent)) {
                return true;
            }
            cls = child.getSuperclass();
        }
        return b;
    }
}
