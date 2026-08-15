package com.pearl.fcw.core.cache;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

import javax.annotation.Resource;

import net.sf.ehcache.Ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.cache.redis.MultiKeyNonReentrantLock;
import com.pearl.fcw.core.cache.redis.NonReentrantLock;
import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.core.cache.redis.RedisLock;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;

/**
 * 事务管理器
 */
public class TransactionManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static ThreadLocal<Transaction> threadLocal = new ThreadLocal<>();
    /**
	 * 记录修改过的本地缓存数据的Key值
	 */
    //    private static ConcurrentLinkedDeque<String> updatedLocalDataKeySet = new ConcurrentLinkedDeque<>();
    //    private static ConcurrentHashMap<Transaction, Set<String>> tmpLocalDataKeyMap = new ConcurrentHashMap<>();
    //
    //    public static ConcurrentLinkedDeque<String> getUpdatedLocalDataKeySet() {
    //        return updatedLocalDataKeySet;
    //    }

    @Resource
    private CacheKey cacheKey;
    @Resource
    private Ehcache localCache;
    @Resource
    private RedisClient redisClient;
    @Resource
    private OperationCache operationCache;

	private int timeoutMillis = 5 * 1000;//事务分布式锁的超时时间，毫秒

    /**
	 * 关联一个事务至当前线程。
	 * 该操作只关联一个事务，并没有真正开始该事务。
	 * @param key 分布式锁Key
	 * @throws Exception 已经存在一个事务
	 */
    public void associate(String key) throws Exception {
        Transaction tx = getTransaction();
        if (tx == null) {
            RedisLock lock = new NonReentrantLock(redisClient, key);
            threadLocal.set(new Transaction(cacheKey, localCache, redisClient, lock, operationCache, timeoutMillis));
        } else {
            throw new TransactionDuplicateException();
        }
    }

    /**
	 * 关联一个事务至当前线程。
	 * 该操作只关联一个事务，并没有真正开始该事务。
	 * @param keys 分布式锁Key
	 * @throws Exception 已经存在一个事务
	 */
    public void associate(Collection<String> keys) throws Exception {
        Transaction tx = getTransaction();
        if (tx == null) {
            RedisLock lock = new MultiKeyNonReentrantLock(redisClient, keys);
            threadLocal.set(new Transaction(cacheKey, localCache, redisClient, lock, operationCache, timeoutMillis));
        } else {
            throw new TransactionDuplicateException();
        }
    }

    /**
	 * 关联一个事务至当前线程。
	 * 该操作只关联一个事务，并没有真正开始该事务。
	 * @param keys 分布式锁Key
	 * @throws TransactionDuplicateException 已经存在一个事务
	 */
    public void associate(String... keys) throws Exception {
        associate(Arrays.asList(keys));
    }

    /**
	 * 关联指定代理至当前事务，如果该代理已经关联，则直接返回。
	 * @param proxy 代理
	 */
    public EntityProxy<?> enlist(EntityProxy<?> proxy) {
        Transaction tx = getTransaction();
        if (null != tx) {
			//事务中记录待写入缓存的代理实例
            return tx.enlist(proxy);
        }
        return null;
    }

    /**
	 * 关联指定代理至当前事务，如果该代理已经关联，则直接返回。逻辑删除用
	 * @param proxy 代理
	 */
    public void oulist(EntityProxy<?> proxy) {
        Transaction tx = getTransaction();
        if (null != tx) {
			//事务中记录待删除缓存的代理实例
			tx.unlist(proxy);
        }
    }

    /**
	 * 提交当前线程所关联的事务，如果无关联事务，则不做任何操作。
	 */
    public void commit() {
        Transaction tx = getTransaction();
        if (tx != null) {
            try {
                //                for (String key : tmpLocalDataKeyMap.getOrDefault(tx, new HashSet<>())) {
                //                    if (!updatedLocalDataKeySet.contains(key)) {
                //                        updatedLocalDataKeySet.add(key);
                //                    }
                //                }
                tx.commit();
            } finally {
                //                tmpLocalDataKeyMap.remove(tx);
                threadLocal.remove();
            }
        }
    }

    /**
	 * 回滚当前线程所关联的事务，如果无关联事务，则不做任何操作。
	 */
    public void rollback() {
        Transaction tx = getTransaction();
        if (tx != null) {
            try {
                tx.rollback();
            } finally {
                //                tmpLocalDataKeyMap.remove(tx);
                threadLocal.remove();
            }
        }
    }

    /**
	 * 判断当前线程所关联的事务是否有效（该事务已开始则为有效）。
	 * @return true：存在关联事务且该事务已经开始
	 */
    public boolean isActive() {
        Transaction tx = getTransaction();
        return tx != null && tx.isActive();
    }

    /**
	 * @return 当前线程关联的事务
	 */
    public Transaction getTransaction() {
        return threadLocal.get();
    }

    /**
	 * 设置开始事务的超时时间（即，获取分布式锁的超时时间）。
	 * @param timeoutMillis 毫秒
	 */
    public void setTimeoutMillis(int timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public Deque<Object> getMsgList() {
        Transaction tx = getTransaction();
        if (null != tx) {
            return tx.getMsgList();
        }
        return new ConcurrentLinkedDeque<>();
    }
}
