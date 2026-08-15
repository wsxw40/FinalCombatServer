package com.pearl.fcw.core.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.core.cache.redis.RedisLock;
import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.BaseExtEntity;
import com.pearl.fcw.core.pojo.proxy.CacheEntry;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.OperationListener;

public class Transaction {

    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

	private static final int DEFAULT_LEASE_MILLIS = 1000 * 60 * 5; // 在不手动释放锁的情况下，该锁只存在5分钟

    private long lockTimeout = 10 * 1000;

    private final CacheKey cacheKey;
    private final Ehcache localCache;
    private final RedisClient redisClient;
    private final RedisLock lock;
    private final OperationCache operationCache;
    private final int timeoutMillis;

    private List<Runnable> afterCommitCallbacks;

    private boolean active = false;
	private Map<Class<?>, Map<Serializable, EntityProxy<?>>> proxies = new HashMap<>();//主逻辑数据<BaseEntity,<Id,ProxyM>>
	private Map<Class<?>, Map<Serializable, Map<Serializable, EntityProxy<?>>>> extProxies = new HashMap<>();//从逻辑数据<BaseExtEntity,<ShareId,<Id,ProxyM>>>
	private Map<Class<?>, Map<Serializable, EntityProxy<?>>> removeProxies = new HashMap<>();//逻辑删除的数据<BaseEntity,<Id,ProxyM>>
	private static final long LOCAL_CACHE_WRITE_LOCK_MILLIS = 5;//本地缓存写入数据的加锁时间
	private Deque<Object> msgList = new LinkedList<>();//自定义消息链表，外部利用线程消息的独立性进行循环处理

    public Transaction(CacheKey cacheKey, Ehcache localCache, RedisClient redisClient, RedisLock lock, OperationCache operationCache, int timeoutMillis) {
        this.cacheKey = cacheKey;
        this.localCache = localCache;
        this.redisClient = redisClient;
        this.lock = lock;
        this.operationCache = operationCache;
        this.timeoutMillis = timeoutMillis;
    }

    public void begin() throws Exception {
        if (active) {
            return;
        }
        if (lock.tryLock(timeoutMillis, DEFAULT_LEASE_MILLIS)) {
            active = true;
        } else {
            throw new TransactionTimeoutException();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void commit() {
        if (!active) {
            return;
        }

		Date now = new Date();
		Random random = new Random();
		//逻辑删除数据
        for (Class<?> cls : removeProxies.keySet()) {
            for (EntityProxy<?> proxyM : removeProxies.get(cls).values()) {
                if (proxyM == null) {
                    continue;
                }
                BaseEntity m = proxyM.get();
                if (m == null) {
                    continue;
                }
                String key = cacheKey.getLocalDataKey(m.getClass(), m.getId());
                try {
                    delLocalData(key);
					//删除远程数据
                    String remoteDataKey = cacheKey.getRemoteDataKey(m.getClass(), m.getId());
                    if (m instanceof BaseExtEntity) {
                        remoteDataKey = cacheKey.getRemoteDataKey(m.getClass(), ((BaseExtEntity) m).getShareId() + "|" + m.getId());
                    }
                    redisClient.del(remoteDataKey);
					if (m instanceof BaseExtEntity) {//从逻辑数据需要删除远程的从逻辑版本号集合以及主逻辑版本号
                        String remoteVersionMapKey = cacheKey.getRemoteVersionMapKey(m.getClass(), ((BaseExtEntity) m).getShareId());
                        redisClient.del(remoteVersionMapKey);
                        String remoteVersionShareKey = cacheKey.getRemoteVersionShareKey(m.getClass(), ((BaseExtEntity) m).getShareId());
                        redisClient.del(remoteVersionShareKey);
					} else {//主逻辑数据需要删除远程版本号
                        String remoteVersionKey = cacheKey.getRemoteVersionKey(m.getClass(), m.getId());
                        redisClient.del(remoteVersionKey);
                    }
					//设置修改时间以及逻辑删除标记
					m.setUpdateTime(now);
                    m.setIsRemoved(true);
                    CacheDao dao = CacheDao.getModelAndDaoMap().get(m.getClass());
                    m = dao.update(m);
					//删除同一条数据在proxies或者extProxies的记录
                    if (m instanceof BaseExtEntity) {
                        if (null != extProxies.get(cls)) {
                            BaseExtEntity m1 = (BaseExtEntity) m;
                            if (null != extProxies.get(cls).get(m1.getShareId())) {
                                extProxies.get(cls).get(m1.getShareId()).remove(m1.getId());
                            }
                        }
                    } else {
                        if (null != proxies.get(cls) && null != proxies.get(cls).get(m.getId())) {
                            proxies.get(cls).remove(m.getId());
                        }
                    }
                } catch (Exception e) {
                    logger.error("logic delete data error at key " + key + " : ", e);
                }
            }
        }
		//修改主逻辑数据
        for (Class<?> cls : proxies.keySet()) {
            for (EntityProxy<?> proxyM : proxies.get(cls).values()) {
                if (proxyM == null) {
                    continue;
                }
                OperationListener l = proxyM.getListener();
				if (l.getOperations().isEmpty()) {//排除无操作记录的数据
                    continue;
                }
                BaseEntity m = proxyM.get();
                if (m == null) {
                    continue;
                }
				//随机版本号并自动设置修改时间
				m.setDbVersion(random.nextLong());
				m.setUpdateTime(now);

                String localFlushKey = cacheKey.getLocalFlushWriteKey(cls, m.getId());
                try {
                    if (localCache.tryWriteLockOnKey(localFlushKey, lockTimeout)) {
                        try {
							//记录操作
                            String localDataKey = cacheKey.getLocalDataKey(proxyM.get().getClass(), proxyM.get().getId());
                            if (writeLocalCacheProxy(localDataKey, proxyM)) {
                                operationCache.put(localDataKey, null);
                            } else {
                                operationCache.put(localDataKey, proxyM);
                            }
							//数据库数据写入远程缓存
                            String remoteDatakey = cacheKey.getRemoteDataKey(cls, m.getId());
                            redisClient.setex(remoteDatakey, EntityManager.REMOTE_KEY_SECONDS, m);
							//数据版本号写入远程缓存
							if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, m.getId()), 1, EntityManager.REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止查询和写数据库时的冲突
                                String remoteVersionKey = cacheKey.getRemoteVersionKey(cls, m.getId());
                                redisClient.setex(remoteVersionKey, EntityManager.REMOTE_KEY_SECONDS, m.getDbVersion());
                            }
                        } finally {
                            localCache.releaseWriteLockOnKey(localFlushKey);
                        }
                    }
                } catch (InterruptedException ex) {
                    logger.warn("Can not obtain local cache write lock for key {}", localFlushKey);
                } catch (Exception ex) {
                    logger.warn("Can not flush operations for key " + localFlushKey, ex);
                }
            }
        }
		//修改从逻辑数据
        for (Class<?> cls : extProxies.keySet()) {
            for (Serializable shareId : extProxies.get(cls).keySet()) {
                String localFlushKey = cacheKey.getLocalFlushWriteKey(cls, shareId);
                try {
                    if (localCache.tryWriteLockOnKey(localFlushKey, lockTimeout)) {
                        try {
                            String localVersionMapKey = cacheKey.getLocalVersionMapKey(cls, shareId);
                            Map<Serializable, Long> localVersionMap = new HashMap<>();
                            localVersionMap.putAll(readLocalCacheVersionMap(localVersionMapKey));
                            String remoteVersionMapKey = cacheKey.getRemoteVersionMapKey(cls, shareId);
                            Map<Serializable, Long> remoteVersionMap = new HashMap<>();
                            if (null != redisClient.get(remoteVersionMapKey)) {
                                remoteVersionMap.putAll(redisClient.get(remoteVersionMapKey));
                            }
                            for (EntityProxy<?> proxyM : extProxies.get(cls).get(shareId).values()) {
                                if (proxyM == null) {
                                    continue;
                                }
                                OperationListener l = proxyM.getListener();
								if (l.getOperations().isEmpty()) {//排除无操作记录的数据
                                    continue;
                                }
                                BaseExtEntity m = (BaseExtEntity) proxyM.get();
                                if (m == null) {
                                    continue;
                                }
								//随机版本号并自动设置修改时间
								m.setDbVersion(random.nextLong());
								m.setUpdateTime(now);

								//记录操作
                                String localDataKey = cacheKey.getLocalDataKey(proxyM.get().getClass(), proxyM.get().getId());
                                if (writeLocalCacheProxy(localDataKey, proxyM)) {
                                    operationCache.put(localDataKey, null);
                                } else {
                                    operationCache.put(localDataKey, proxyM);
                                }
								//从逻辑数据库数据写入远程缓存
								if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, shareId + "|" + m.getId()), 1, EntityManager.REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止查询和写数据库时的冲突
                                    String remoteDatakey = cacheKey.getRemoteDataKey(cls, m.getShareId() + "|" + m.getId());
                                    redisClient.setex(remoteDatakey, EntityManager.REMOTE_KEY_SECONDS, m);
                                }
								//从逻辑数据版本号写入远程和本地从逻辑版本号集合
                                localVersionMap.put(m.getId(), m.getDbVersion());
                                remoteVersionMap.put(m.getId(), m.getDbVersion());
                            }
							//从逻辑版本号集合以及主逻辑版本号写入远程和本地缓存
							if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, shareId), 1, EntityManager.REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止查询和写数据库时的冲突
                                writeLocalCacheVersionMap(localVersionMapKey, localVersionMap);
                                redisClient.setex(remoteVersionMapKey, EntityManager.REMOTE_KEY_SECONDS, remoteVersionMap);
								long shareVersion = random.nextLong();
                                String localVersionShareKey = cacheKey.getLocalVersionShareKey(cls, shareId);
                                String remoteVersionShareKey = cacheKey.getRemoteVersionShareKey(cls, shareId);
                                writeLocalCacheShareVersion(localVersionShareKey, shareVersion);
                                redisClient.setex(remoteVersionShareKey, EntityManager.REMOTE_KEY_SECONDS, shareVersion);
                            }
                        } finally {
                            localCache.releaseWriteLockOnKey(localFlushKey);
                        }
                    }
                } catch (InterruptedException ex) {
                    logger.warn("Can not obtain local cache write lock for key {}", localFlushKey);
                } catch (Exception ex) {
                    logger.warn("Can not flush operations for key " + localFlushKey, ex);
                }
            }
        }

        lock.unlock();
        active = false;

        if (afterCommitCallbacks != null) {
            for (Runnable r : afterCommitCallbacks) {
                try {
                    r.run();
                } catch (Throwable e) {
                    logger.warn("Error has occurred when exceute after-commit-callback", e);
                }
            }
        }
    }

    public void rollback() {
        if (!active) {
            return;
        }
        removeProxies.clear();
        proxies.clear();
        extProxies.clear();
        lock.unlock();
        active = false;
    }

    public boolean isActive() {
        return active;
    }

    /**
	 * 关联指定代理至当前事务，如果该代理已经关联，则直接返回。修改数据用
	 * @param proxy 代理
	 */
    public EntityProxy<?> enlist(EntityProxy<?> proxy) {
        if (null == proxy /*|| proxy.getListener().getOperations().isEmpty()*/) {
            return proxy;
        }
        BaseEntity m = proxy.get();
        if (null == m) {
            return proxy;
        }
        Class<?> cls = m.getClass();
        if (m instanceof BaseExtEntity) {
            BaseExtEntity m1 = (BaseExtEntity) m;
            if (!extProxies.containsKey(cls)) {
                extProxies.put(cls, new HashMap<>());
            }
            if (!extProxies.get(cls).containsKey(m1.getShareId())) {
                extProxies.get(cls).put(m1.getShareId(), new HashMap<>());
            }
			if (extProxies.get(cls).get(m1.getShareId()).containsKey(m1.getId())) {
				proxy = extProxies.get(cls).get(m1.getShareId()).get(m1.getId());//使用线程中的已有数据
			}
            extProxies.get(cls).get(m1.getShareId()).put(m1.getId(), proxy);
        } else {
            if (!proxies.containsKey(cls)) {
                proxies.put(cls, new HashMap<>());
            }
			if (proxies.get(cls).containsKey(m.getId())) {
				proxy = proxies.get(cls).get(m.getId());//使用线程中的已有数据
			}
            proxies.get(cls).put(m.getId(), proxy);
        }
        return proxy;
    }

    /**
	 * 关联指定代理至当前事务，如果该代理已经关联，则直接返回。逻辑删除数据用
	 * @param proxy 代理
	 */
	public void unlist(EntityProxy<?> proxy) {
        if (null == proxy) {
            return;
        }
        BaseEntity m = proxy.get();
        if (null == m) {
            return;
        }
        Class<?> cls = m.getClass();
        if (!removeProxies.containsKey(cls)) {
            removeProxies.put(cls, new HashMap<>());
        }
        removeProxies.get(cls).put(m.getId(), proxy);
        if (m instanceof BaseExtEntity) {
            if (extProxies.containsKey(cls)) {
				extProxies.get(cls).get(((BaseExtEntity) m).getShareId()).remove(m.getId());
            }
        } else {
            if (proxies.containsKey(cls)) {
                proxies.get(cls).remove(m.getId());
            }
        }
    }

    public void addAfterCommit(Runnable callback) {
        if (afterCommitCallbacks == null) {
            afterCommitCallbacks = new ArrayList<>();
        }
        afterCommitCallbacks.add(callback);
    }

	/**
	 * 获取从逻辑数据对应的主逻辑版本号
	 * @param shareKey
	 * @return
	 */
	public Long readLocalCacheShareVersion(String shareKey) {
		Element element = localCache.getQuiet(shareKey);
		if (null != element) {
			return (Long) element.getObjectValue();
		}
		return null;
	}

	/**
	 * 从本地线程获取主逻辑数据的一个代理实例。获取范围为未逻辑删除的主逻辑数据
	 * @param cls
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <M extends BaseEntity, P extends EntityProxy<M>> P readThreadCacheProxy(Class<M> cls, Serializable id) {
		if (proxies.containsKey(cls)) {
			if (proxies.get(cls).containsKey(id)) {
				return (P) proxies.get(cls).get(id);
			}
		}
		return null;
	}

	/**
	 * 从本地线程获取从逻辑数据的同一个shareId下属的代理实例集合。获取范围为未逻辑删除的从逻辑数据
	 * @param cls
	 * @param shareId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <M extends BaseEntity, P extends EntityProxy<M>> Map<Serializable, P> readThreadCacheProxyMap(Class<M> cls, Serializable shareId) {
		if (extProxies.containsKey(cls)) {
			if (extProxies.get(cls).containsKey(shareId)) {
				Map<Serializable, P> map = (Map<Serializable, P>) extProxies.get(cls).get(shareId);
				return map;
			}
		}
		return new HashMap<>();
	}


	/**
	 * 从本地线程获取已有的代理实例集合。获取范围包括已逻辑删除的数据，无视主从逻辑
	 * @param cls 代理实例对应的数据库类。null表示本线程内所有代理实例
	 * @param shareId
	 * @param isUpdate 获取的代理实例是否有必须有变更信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <M extends BaseEntity, P extends EntityProxy<M>> Set<P> readThreadCacheProxySet(Class<M> cls, Serializable shareId, boolean isUpdate) {
		Set<P> set = new HashSet<>();
		if (null == cls) {
			proxies.values().forEach(p1 -> {
				p1.values().forEach(p2 -> {
					if (isUpdate) {
						if (!p2.getListener().getOperations().isEmpty()) {
							set.add((P) p2);
						}
					} else {
						set.add((P) p2);
					}
				});
			});
			extProxies.values().forEach(p1 -> {
				p1.values().forEach(p2 -> {
					p2.values().forEach(p3 -> {
						if (isUpdate) {
							if (!p3.getListener().getOperations().isEmpty()) {
								set.add((P) p3);
							}
						} else {
							set.add((P) p3);
						}
					});
				});
			});
			removeProxies.values().forEach(p1 -> {
				p1.values().forEach(p2 -> {
					if (isUpdate) {
						if (!p2.getListener().getOperations().isEmpty()) {
							set.add((P) p2);
						}
					} else {
						set.add((P) p2);
					}
				});
			});
			return set;
		}
		if (proxies.containsKey(cls)) {
			return proxies.get(cls).values().stream().filter(p -> {
				if (isUpdate && p.getListener().getOperations().isEmpty()) {
					return false;
				}
				if (null != shareId) {
					BaseEntity m = p.get();
					return m.getId().equals(shareId);
				}
				return true;
			}).map(p -> (P) p).collect(Collectors.toSet());
		}
		if (extProxies.containsKey(cls)) {
			Set<EntityProxy<?>> tmp = new HashSet<>();
			if (null == shareId) {
				extProxies.get(cls).values().forEach(p -> {
					tmp.addAll(p.values());
				});
			} else {
				extProxies.get(cls).getOrDefault(shareId, new HashMap<>()).values().forEach(p -> {
					tmp.add(p);
				});
			}
			return tmp.stream().filter(p -> {
				if (isUpdate && p.getListener().getOperations().isEmpty()) {
					return false;
				}
				if (null != shareId) {
					BaseExtEntity m = (BaseExtEntity) p.get();
					return m.getShareId().equals(shareId);
				}
				return true;
			}).map(p -> (P) p).collect(Collectors.toSet());
		}
		if (removeProxies.containsKey(cls)) {
			Set<EntityProxy<?>> tmp = new HashSet<>();
			if (null == shareId) {
				removeProxies.get(cls).values().forEach(p -> {
					tmp.add(p);
				});
			} else {
				extProxies.get(cls).getOrDefault(shareId, new HashMap<>()).values().forEach(p -> {
					tmp.add(p);
				});
			}
			return tmp.stream().filter(p -> {
				if (isUpdate && p.getListener().getOperations().isEmpty()) {
					return false;
				}
				if (null != shareId) {
					if (p.get() instanceof BaseExtEntity) {
						BaseExtEntity m = (BaseExtEntity) p.get();
						return m.getShareId() == shareId;
					}
					BaseEntity m = p.get();
					return m.getId().equals(shareId);
				}
				return true;
			}).map(p -> (P) p).collect(Collectors.toSet());
		}
		return new HashSet<>();
	}

    /**
	 * 获取本地缓存的代理实例。无同步锁<br/>
	 * @param key
	 * @param constructorP
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public <M extends BaseEntity, P extends EntityProxy<M>> P readLocalCacheProxy(String key, Function<M, P> constructorP) throws Exception {
        Element element = localCache.getQuiet(key);
        if (null == element) {
            return null;
        }
        try {
            CacheEntry<M> ce = (CacheEntry<M>) element.getObjectValue();
            P proxyM = constructorP.apply(ce.getEntity());
            return proxyM;
        } catch (Exception e) {
            logger.warn(e.toString());
        }
        return null;
    }

    /**
	 * 获取本地缓存的从逻辑版本号集合。无同步锁
	 * @param key
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public Map<Serializable, Long> readLocalCacheVersionMap(String key) throws Exception {
        Map<Serializable, Long> versionMap = new HashMap<>();
        Element element = localCache.getQuiet(key);
        if (null == element) {
            return versionMap;
        }
        try {
            versionMap = (Map<Serializable, Long>) element.getObjectValue();
            return versionMap;
        } catch (Exception e) {
            logger.warn(e.toString());
        }
        return versionMap;
    }

    /**
	 * 写入本地缓存从逻辑数据对应的主逻辑版本号。有同步锁，直接覆盖
	 * @param key
	 * @param version
	 * @throws Exception
	 */
    public boolean writeLocalCacheShareVersion(String key, Long version) throws Exception {
        if (localCache.tryWriteLockOnKey(key, LOCAL_CACHE_WRITE_LOCK_MILLIS)) {
            try {
                localCache.put(new Element(key, version));
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

    /**
	 * 写入本地缓存代理实例，有同步锁<br/>
	 * 代理实例的操作记录只能追加，不可覆盖
	 * @param key
	 * @param proxyM
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public <M extends BaseEntity> boolean writeLocalCacheProxy(String key, EntityProxy<M> proxyM) throws Exception {
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

    /**
	 * 写入本地缓存从逻辑的版本号集合，有同步锁<br/>
	 * 版本号集合可以单条替换，不可整体覆盖
	 * @param key
	 * @param versionMap
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public boolean writeLocalCacheVersionMap(String key, Map<Serializable, Long> versionMap) throws Exception {
        if (localCache.tryWriteLockOnKey(key, LOCAL_CACHE_WRITE_LOCK_MILLIS)) {
            try {
                Element element = localCache.getQuiet(key);
                if (null != element) {
                    Map<Serializable, Long> ordienalVersionMap = (Map<Serializable, Long>) element.getObjectValue();
                    ordienalVersionMap.putAll(versionMap);
                    localCache.put(new Element(key, versionMap));
                } else {
                    localCache.put(new Element(key, versionMap));
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

    /**
	 * 删除本地缓存的数据。如果删除的是从逻辑数据，还要删除从逻辑版本号集合以及主逻辑版本号
	 * @param key
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public <M extends BaseEntity> void delLocalData(String key) throws Exception {
        Element element = localCache.getQuiet(key);
        if (null != element) {
            CacheEntry<M> ce = (CacheEntry<M>) element.getObjectValue();
			if (ce.getEntity() instanceof BaseExtEntity) {//从逻辑数据需要删除本地缓存的从逻辑版本号集合以及主逻辑版本号
                BaseExtEntity m = (BaseExtEntity) ce.getEntity();
                String localVersionMapKey = cacheKey.getLocalVersionMapKey(m.getClass(), m.getShareId());
                localCache.remove(localVersionMapKey);
                String shareKey = cacheKey.getLocalVersionShareKey(m.getClass(), m.getShareId());
                localCache.remove(shareKey);
            }
            localCache.remove(key);
        }
    }

    public Deque<Object> getMsgList() {
        return msgList;
    }
}
