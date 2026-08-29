package com.pearl.fcw.core.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.BaseExtEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.lobby.pojo.WPlayerAchievement;

/**
 * 数据库数据代理类操作
 */
public class EntityManager {
    @Resource
    private RedisClient redisClient;
    @Resource
    private CacheKey cacheKey;
    @Resource
    private TransactionManager transactionManager;
    @Resource
    private OperationCache operationCache;

    private Logger logger = LoggerFactory.getLogger(getClass());

	/** 以下数据为临时使用，将来转移到配置文件中 */
	public static final int REMOTE_KEY_SECONDS = 5 * 60;//远程数据缓存时间
	public static final int REMOTE_FLUSH_KEY_MILLISECONDS = 5;//写远程数据之前加锁的时间，毫秒

	/** 以上数据为临时使用，将来转移到配置文件中 */


    /**
	 * 获取主逻辑数据的一个代理实例<br/>
	 * 获取的数据以远程数据或版本号为基准
	 * @param constructorP 代理类构造方法
	 * @param cls 数据库实体类
	 * @param id 主逻辑主键
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public <M extends BaseEntity, P extends EntityProxy<M>> P findProxy(Function<M, P> constructorP, Class<M> cls, Serializable id) throws Exception {
        Transaction tx = transactionManager.getTransaction();
        if (tx != null) {
            tx.begin();
        } else {
            throw new TransactionRequiredException();
        }

        P proxyM = null;
        CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);

		//检查本地线程是否已有数据。已有则无视本地公共缓存和远程数据
		M m = null;
		P ceProxy = (P) tx.readThreadCacheProxy(cls, id);
		if (null != ceProxy) {
			return ceProxy;
		}

		//检查远程数据和本地数据
		String remoteDateKey = cacheKey.getRemoteDataKey(cls, id);
		String localDataKey = cacheKey.getLocalDataKey(cls, id);
		m = redisClient.get(remoteDateKey);
		ceProxy = tx.readLocalCacheProxy(localDataKey, constructorP);

		//远程数据存在
        if (null != m) {
            proxyM = constructorP.apply(m);
            proxyM = enlist(proxyM);
            return proxyM;
        }

		//检查远程版本号
        String remoteVersionKey = cacheKey.getRemoteVersionKey(cls, id);
        Long remoteVersion = redisClient.get(remoteVersionKey);
		//远程版本号存在
        if (null != remoteVersion) {
			if (null != ceProxy) {//本地数据存在
				//远程和本地数据的版本号不一致，本地从数据库获取最新数据，本地版本号被远程版本号覆盖
                if (!ceProxy.get().getDbVersion().equals(remoteVersion)) {
                    m = (M) dao.findEntity(id);
                    if (null == m) {
                        return null;
                    }
                    m.setDbVersion(remoteVersion);
                    proxyM = constructorP.apply(m);
                    tx.writeLocalCacheProxy(localDataKey, proxyM);
                } else {
                    proxyM = ceProxy;
                }
			} else {//本地数据不存在，从数据库获取最新数据，本地版本号被远程版本号覆盖
                m = (M) dao.findEntity(id);
                if (null == m) {
                    return null;
                }
                m.setDbVersion(remoteVersion);
                proxyM = constructorP.apply(m);
                tx.writeLocalCacheProxy(localDataKey, proxyM);
            }
            proxyM = enlist(proxyM);
            return proxyM;
        }

		//远程无版本号和数据，有本地数据，将本地版本号作为远程版本号
        if (null != ceProxy) {
            proxyM = ceProxy;
			if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, id), 1, REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止提交事务和写数据库时的冲突
                redisClient.setex(remoteVersionKey, REMOTE_KEY_SECONDS, ceProxy.get().getDbVersion());
            }
            proxyM = enlist(proxyM);
            return proxyM;
        }

		//远程无版本号和数据，无本地数据，本地从数据库获取最新数据，并随机版本号，然后该版本号作为远程版本号
        m = (M) dao.findEntity(id);
        if (null == m) {
            return null;
        }
        m.setDbVersion(new Random().nextLong());
        proxyM = constructorP.apply(m);
        tx.writeLocalCacheProxy(localDataKey, proxyM);
		if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, id), 1, REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止提交事务和写数据库时的冲突
            redisClient.setex(remoteVersionKey, REMOTE_KEY_SECONDS, m.getDbVersion());
        }

        proxyM = enlist(proxyM);
        return proxyM;
    }

    /**
	 * 获取指定主逻辑主键所属的从逻辑数据<br/>
	 * 获取的数据以远程数据或版本号为基准
	 * @param constructorP 代理类构造方法
	 * @param constructorM 数据库实体类构造方法
	 * @param cls 数据库实体类
	 * @param shareId 主逻辑主键
	 * @return
	 * @throws Exception
	 */
    public <M extends BaseExtEntity, P extends EntityProxy<M>> Map<Serializable, P> findProxyMap(Function<M, P> constructorP, Supplier<M> constructorM, Class<M> cls, Serializable shareId)
            throws Exception {
        Transaction tx = transactionManager.getTransaction();
        if (tx != null) {
            tx.begin();
        } else {
            throw new TransactionRequiredException();
        }
        Map<Serializable, P> map = new HashMap<>();
		//检查本地线程是否已有数据。已有则无视本地公共缓存和远程数据
		if (!cls.equals(WPlayerAchievement.class) && !cls.getSuperclass().equals(WPlayerAchievement.class)) {//FIXME 具体业务的类不能写在功能代码内，PlayerAchievement表应重新设计
			map.putAll(tx.readThreadCacheProxyMap(cls, shareId));
			if (!map.isEmpty()) {
				return map;
			}
		}
		//检查远程主逻辑版本号和本地主逻辑版本号
        String remoteVersionShareKey = cacheKey.getRemoteVersionShareKey(cls, shareId);
        String localVersionShareKey = cacheKey.getLocalVersionShareKey(cls, shareId);
        Long remoteVersion = redisClient.get(remoteVersionShareKey);
        Long localVersion = tx.readLocalCacheShareVersion(localVersionShareKey);
		//远程主逻辑版本号存在
        if (null != remoteVersion) {
			//本地主逻辑版本号存在
            if (null != localVersion) {
                map = findProxyMap11(constructorP, constructorM, cls, shareId, remoteVersion, localVersion);
                map = enlist(map);
                return map;
            }
			//本地主逻辑版本号不存在
            map = findProxyMap10(constructorP, constructorM, cls, shareId, remoteVersion);
            map = enlist(map);
            return map;
        }
		//远程主逻辑版本号不存在，本地主逻辑版本号存在
        if (null != localVersion) {
            map = findProxyMap01(constructorP, constructorM, cls, shareId, localVersion);
            map = enlist(map);
            return map;
        }
		//远程和本地主逻辑版本号都不存在
        map = findProxyMap00(constructorP, constructorM, cls, shareId);
        map = enlist(map);
        return map;
    }

    /**
	 * 在远程本地主逻辑版本号存在的前提下，获取指定主逻辑主键所属的从逻辑数据
	 * @param constructorP 代理类构造方法
	 * @param constructorM 数据库实体类构造方法
	 * @param cls 数据库实体类
	 * @param shareId 主逻辑主键
	 * @param remoteVersion 远程主逻辑版本号
	 * @param localVersion 本地主逻辑版本号
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <M extends BaseExtEntity, P extends EntityProxy<M>> Map<Serializable, P> findProxyMap11(Function<M, P> constructorP, Supplier<M> constructorM, Class<M> cls, Serializable shareId,
            Long remoteVersion, Long localVersion) throws Exception {
        Transaction tx = transactionManager.getTransaction();
        CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
        Map<Serializable, P> localProxyMap = new HashMap<>();
		final Map<Serializable, Long> remoteVersionMap = new HashMap<>();//远程从逻辑版本号集合
        String remoteVersionMapKey = cacheKey.getRemoteVersionMapKey(cls, shareId);
        Map<Serializable, Long> tmpRemoteVersionMap = redisClient.get(remoteVersionMapKey);
        if (null != tmpRemoteVersionMap) {
            remoteVersionMap.putAll(tmpRemoteVersionMap);
        }
		final Map<Serializable, Long> localVersionMap = new HashMap<>();//本地从逻辑版本号集合
        String localVersionMapKey = cacheKey.getLocalVersionMapKey(cls, shareId);
        Map<Serializable, Long> tmpLocalVersionMap = tx.readLocalCacheVersionMap(localVersionMapKey);
        if (null != tmpLocalVersionMap) {
            localVersionMap.putAll(tmpLocalVersionMap);
        }
        Map<Serializable, M> databaseDataMap = new HashMap<>();
		//远程和本地的主逻辑版本号不一致
        if (!localVersion.equals(remoteVersion)) {
			//以远程为基准，获取不一致的从逻辑主键
            Set<Serializable> ids = remoteVersionMap.keySet().stream().filter(id -> {
				if (!localVersionMap.containsKey(id)) {//远程有本地没有的从逻辑主键
                        return true;
                    }
					if (!localVersionMap.getOrDefault(id, 0L).equals(remoteVersionMap.get(id))) {//远程和本地的从逻辑版本号不一致
                        return true;
                    }
                    return false;
                }).collect(Collectors.toSet());
            M m = constructorM.get();
            m.setShareId((Integer) shareId);
            databaseDataMap.putAll(dao.findMap(m));
			//遍历不一致的从逻辑主键，从远程或者数据库获取对应的数据
            for (Serializable id : ids) {
                String remoteDataKey = cacheKey.getRemoteDataKey(cls, shareId + "|" + id);
                m = redisClient.get(remoteDataKey);
                String localDataKey = cacheKey.getLocalDataKey(cls, id);
                P proxyM = null;
				//远程从逻辑数据存在
                if (null != m) {
                    proxyM = constructorP.apply(m);
				} else {//远程从逻辑数据不存在，从数据库获取数据
                    m = databaseDataMap.get(id);
                    m.setDbVersion(remoteVersionMap.get(id));
                    proxyM = constructorP.apply(m);
                }
                localProxyMap.put(id, proxyM);
                localVersionMap.put(id, remoteVersionMap.get(id));
                if (!tx.writeLocalCacheProxy(localDataKey, proxyM)) {
                    logger.warn("11:" + localDataKey);
                }
            }
			//本地从逻辑版本号集合更新到本地缓存
            tx.writeLocalCacheVersionMap(localVersionMapKey, localVersionMap);
        }
		//从本地缓存提取从逻辑数据，如果不存在该条数据，先从数据库获取最新数据，再放入本地缓存
        for (Serializable id : localVersionMap.keySet().stream().collect(Collectors.toList())) {
			if (localProxyMap.containsKey(id)) {//临时Map中已有数据，不需要再获取
                continue;
            }
            String localDatakey = cacheKey.getLocalDataKey(cls, id);
            P ceProxy = tx.readLocalCacheProxy(localDatakey, constructorP);
			if (null != ceProxy) {//本地缓存有数据
                localProxyMap.put(id, ceProxy);
			} else {//本地缓存无数据
                if (databaseDataMap.isEmpty()) {
                    M m = constructorM.get();
                    m.setShareId((Integer) shareId);
                    databaseDataMap.putAll(dao.findMap(m));
                }
                M m = databaseDataMap.get(id);
                m.setDbVersion(localVersionMap.get(id));
                P proxyM = constructorP.apply(m);
                localProxyMap.put(id, proxyM);
                tx.writeLocalCacheProxy(localDatakey, proxyM);
            }
        }
        return localProxyMap;
    }

    /**
	 * 在远程主逻辑版本号存在、本地主逻辑版本号不存在的前提下，获取指定主逻辑主键所属的从逻辑数据
	 * @param constructorP 代理类构造方法
	 * @param constructorM 数据库实体类构造方法
	 * @param cls 数据库实体类
	 * @param shareId 主逻辑主键
	 * @param remoteVersion 远程主逻辑版本号
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <M extends BaseExtEntity, P extends EntityProxy<M>> Map<Serializable, P> findProxyMap10(Function<M, P> constructorP, Supplier<M> constructorM, Class<M> cls, Serializable shareId,
            Long remoteVersion) throws Exception {
        Transaction tx = transactionManager.getTransaction();
        CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
        Map<Serializable, P> localProxyMap = new HashMap<>();
		//从数据库获取该主逻辑主键所属的全体最新数据
        M m = constructorM.get();
        m.setShareId((Integer) shareId);
        Map<Serializable, M> databaseDataMap = dao.findMap(m);
		//将本地缓存没有的代理实例写入缓存
        for (Serializable id : databaseDataMap.keySet()) {
            String localDataKey = cacheKey.getLocalDataKey(cls, id);
            P ceProxy = tx.readLocalCacheProxy(localDataKey, constructorP);
			if (null != ceProxy) {//有本地缓存数据
                localProxyMap.put(id, ceProxy);
			} else {//无本地缓存数据
                P proxyM = constructorP.apply(databaseDataMap.get(id));
                localProxyMap.put(id, proxyM);
                if (!tx.writeLocalCacheProxy(localDataKey, proxyM)) {
                    logger.warn("10:" + localDataKey);
                }
            }
        }
		//远程从逻辑版本号集合写入本地缓存
        String remoteVersionMapKey = cacheKey.getRemoteVersionMapKey(cls, shareId);
        Map<Serializable, Long> remoteVersionMap = redisClient.get(remoteVersionMapKey);
        tx.writeLocalCacheVersionMap(cacheKey.getLocalVersionMapKey(cls, shareId), remoteVersionMap);
		//远程主逻辑版本号写入本地缓存
        String localVersionShareKey = cacheKey.getLocalVersionShareKey(cls, shareId);
        tx.writeLocalCacheShareVersion(localVersionShareKey, remoteVersion);

        return localProxyMap;
    }

    /**
	 * 在远程主逻辑版本号不存在、本地主逻辑版本号存在的前提下，获取指定主逻辑主键所属的从逻辑数据
	 * @param constructorP 代理类构造方法
	 * @param constructorM 数据库实体类构造方法
	 * @param cls 数据库实体类
	 * @param shareId 主逻辑主键
	 * @param localVersion 本地主逻辑版本号
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <M extends BaseExtEntity, P extends EntityProxy<M>> Map<Serializable, P> findProxyMap01(Function<M, P> constructorP, Supplier<M> constructorM, Class<M> cls, Serializable shareId,
            Long localVersion) throws Exception {
        Transaction tx = transactionManager.getTransaction();
        CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
        Map<Serializable, P> localProxyMap = new HashMap<>();
		//获取本地从逻辑版本号集合
        String localVersionMapKey = cacheKey.getLocalVersionMapKey(cls, shareId);
        Map<Serializable, Long> localVersionMap = new HashMap<>();
        Map<Serializable, Long> tmpLocalVersionMap = tx.readLocalCacheVersionMap(localVersionMapKey);
        if (null != tmpLocalVersionMap) {
            localVersionMap.putAll(tmpLocalVersionMap);
        }

        Map<Serializable, M> databaseDataMap = new HashMap<>();
		if (!localVersionMap.isEmpty()) {//本地从逻辑版本号集合存在
			//遍历本地的从逻辑主键，获取本地缓存的从逻辑数据，如果不存在，从数据库获取最新数据，再写入本地缓存
            for (Serializable id : localVersionMap.keySet()) {
                String localDataKey = cacheKey.getLocalDataKey(cls, id);
                P ceProxy = tx.readLocalCacheProxy(localDataKey, constructorP);
				if (null != ceProxy) {//本地缓存从逻辑数据存在
                    localProxyMap.put(id, ceProxy);
				} else {//本地从逻辑数据不存在
                    if (databaseDataMap.isEmpty()) {
                        M m = constructorM.get();
                        m.setShareId((Integer) shareId);
                        databaseDataMap = dao.findMap(m);
                    }
                    M m = databaseDataMap.get(id);
                    m.setDbVersion(localVersionMap.get(id));
                    P proxyM = constructorP.apply(m);
                    localProxyMap.put(id, proxyM);
                    if (!tx.writeLocalCacheProxy(localDataKey, proxyM)) {
                        logger.warn("01:" + localDataKey);
                    }
                }
            }
		} else {//本地从逻辑版本号集合不存在
			//从数据库获取该主逻辑主键所属的全体最新数据
            M m = constructorM.get();
            m.setShareId((Integer) shareId);
            databaseDataMap = dao.findMap(m);
			//将本地缓存不存在的数据写入本地缓存
            for (Serializable id : databaseDataMap.keySet()) {
                String localDataKey = cacheKey.getLocalDataKey(cls, id);
                P ceProxy = tx.readLocalCacheProxy(localDataKey, constructorP);
                if (null == ceProxy) {
                    P proxyM = constructorP.apply(databaseDataMap.get(id));
                    proxyM.get().setDbVersion(new Random().nextLong());
                    localProxyMap.put(id, proxyM);
                    localVersionMap.put(id, proxyM.get().getDbVersion());
                    tx.writeLocalCacheProxy(localDataKey, proxyM);
                }
            }
        }
		//本地从逻辑版本号集合以及主逻辑版本号写入远程
		if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, shareId), 1, REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止提交事务和写数据库时的冲突
            redisClient.setex(cacheKey.getRemoteVersionMapKey(cls, shareId), REMOTE_KEY_SECONDS, localVersionMap);
            redisClient.setex(cacheKey.getRemoteVersionShareKey(cls, shareId), REMOTE_KEY_SECONDS, localVersion);
        }
        return localProxyMap;
    }

    /**
	 * 在远程和本地主逻辑版本号都不存在的前提下，获取指定主逻辑主键所属的从逻辑数据
	 * @param constructorP 代理类构造方法
	 * @param constructorM 数据库实体类构造方法
	 * @param cls 数据库实体类
	 * @param shareId 主逻辑主键
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private <M extends BaseExtEntity, P extends EntityProxy<M>> Map<Serializable, P> findProxyMap00(Function<M, P> constructorP, Supplier<M> constructorM, Class<M> cls, Serializable shareId)
            throws Exception {
        Transaction tx = transactionManager.getTransaction();
		while (null != cls && !DmModel.class.equals(cls) && !cls.isAnnotationPresent(GoSchema.class)) {
			cls = (Class<M>) cls.getSuperclass();
		}
		CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
        Map<Serializable, P> localProxyMap = new HashMap<>();
		//从数据库获取全体从逻辑数据
        M m = constructorM.get();
        m.setShareId((Integer) shareId);
        Map<Serializable, M> map = dao.findMap(m);
        Map<Serializable, Long> localVersionMap = new HashMap<>();
        for (Serializable id : map.keySet()) {
            String localDataKey = cacheKey.getLocalDataKey(cls, id);
            m = map.get(id);
			m.setDbVersion(new Random().nextLong());//随机版本号
            P ceProxy = tx.readLocalCacheProxy(localDataKey, constructorP);
			if (null == ceProxy) {//本地缓存中不存在该数据，则将代理数据写入缓存
                P proxyM = constructorP.apply(m);
                localProxyMap.put(id, proxyM);
                tx.writeLocalCacheProxy(localDataKey, proxyM);
            }
            localVersionMap.put(id, m.getDbVersion());
        }
		//写入本地缓存从逻辑版本集合
        tx.writeLocalCacheVersionMap(cacheKey.getLocalVersionMapKey(cls, shareId), localVersionMap);
		//写入本地缓存主逻辑版本号
		Long localVersion = new Random().nextLong();//随机本地主逻辑版本号
        tx.writeLocalCacheShareVersion(cacheKey.getLocalVersionShareKey(cls, shareId), localVersion);
		//本地从逻辑版本号集合以及主逻辑版本号写入远程
		if (redisClient.setIfAbsentMillisecond(cacheKey.getRemoteFlushWriteKey(cls, shareId), 1, REMOTE_FLUSH_KEY_MILLISECONDS)) {//远程加锁，防止提交事务和写数据库时的冲突
            redisClient.setex(cacheKey.getRemoteVersionMapKey(cls, shareId), REMOTE_KEY_SECONDS, localVersionMap);
            redisClient.setex(cacheKey.getRemoteVersionShareKey(cls, shareId), REMOTE_KEY_SECONDS, localVersion);
        }
        return localProxyMap;
    }

    /**
	 * 新增主逻辑数据
	 * @param constructorP
	 * @param m
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <M extends BaseEntity, P extends EntityProxy<M>> P add(Function<M, P> constructorP, M m) throws Exception {
        if (null == m || m instanceof BaseExtEntity) {
            throw new IllegalArgumentException("m can not be null or BaseExtEntity type.");
        }
        Transaction tx = transactionManager.getTransaction();
		//随机版本号
        Long localVersion = new Random().nextLong();
        m.setDbVersion(localVersion);
		//自动插入创建时间
		m.setCreateTime(new Date());
		m.setUpdateTime(m.getCreateTime());
		//向数据库插入数据，获取该数据的主键
		Class<M> cls = (Class<M>) m.getClass();
		while (null != cls && !DmModel.class.equals(cls) && !cls.isAnnotationPresent(GoSchema.class)) {
			cls = (Class<M>) cls.getSuperclass();
		}
		CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
        m = (M) dao.add(m);
		//创建代理实例
        P proxyM = constructorP.apply(m);
		//proxyM.getListener().onNewInstance(proxyM.getPath());//标识该新增数据
		//数据写入缓存
        String localDataKey = cacheKey.getLocalDataKey(m.getClass(), m.getId());
        tx.writeLocalCacheProxy(localDataKey, proxyM);
		//版本号写入远程
        redisClient.setex(cacheKey.getRemoteVersionKey(m.getClass(), m.getId()), REMOTE_KEY_SECONDS, localVersion);
		//更新本地线程记录
		enlist(proxyM);
        return proxyM;
    }

    /**
	 * 新增从逻辑数据
	 * @param constructorP
	 * @param constructorM
	 * @param m
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <M extends BaseExtEntity, P extends EntityProxy<M>> P add(Function<M, P> constructorP, Supplier<M> constructorM, M m) throws Exception {
        if (null == m) {
            throw new IllegalArgumentException("args can not be null.");
        }
        Transaction tx = transactionManager.getTransaction();
		//随机从逻辑版本号
        Long localExtVersion = new Random().nextLong();
        m.setDbVersion(localExtVersion);
		//自动插入创建时间
		m.setCreateTime(new Date());
		m.setUpdateTime(m.getCreateTime());
		//向数据库插入数据，获取该数据的主键
		Class<M> cls = (Class<M>) m.getClass();
		while (null != cls && !DmModel.class.equals(cls) && !cls.isAnnotationPresent(GoSchema.class)) {
			cls = (Class<M>) cls.getSuperclass();
		}
		CacheDao dao = CacheDao.getModelAndDaoMap().get(cls);
        m = (M) dao.add(m);
		//创建从逻辑代理实例
        P proxyM = constructorP.apply(m);
		//proxyM.getListener().onNewInstance(proxyM.getPath());//标识该新增数据
		//从逻辑数据写入缓存
        String localDataKey = cacheKey.getLocalDataKey(m.getClass(), m.getId());
        tx.writeLocalCacheProxy(localDataKey, proxyM);
		//从逻辑版本号写入远程
        redisClient.setex(cacheKey.getRemoteVersionKey(m.getClass(), m.getId()), REMOTE_KEY_SECONDS, localExtVersion);

		//以远程为基准更新从逻辑版本号集合
		findProxyMap(constructorP, constructorM, (Class<M>) m.getClass(), (Serializable) m.getShareId());
        String localVersionMapKey = cacheKey.getLocalVersionMapKey(m.getClass(), m.getShareId());
        Map<Serializable, Long> localVersionMap = tx.readLocalCacheVersionMap(localVersionMapKey);
		localVersionMap.put(m.getId(), m.getDbVersion());//更新刚新增的数据的版本号
		//从逻辑版本号写入本地和远程
        tx.writeLocalCacheVersionMap(localVersionMapKey, localVersionMap);
        String remoteVersionMapKey = cacheKey.getRemoteVersionMapKey(m.getClass(), m.getShareId());
        redisClient.setex(remoteVersionMapKey, REMOTE_KEY_SECONDS, localVersionMap);
		//主逻辑版本号写入本地和远程
        Long localShareVersion = new Random().nextLong();
        String localVersionKey = cacheKey.getLocalVersionShareKey(m.getClass(), m.getShareId());
        tx.writeLocalCacheShareVersion(localVersionKey, localShareVersion);
        String remoteVersionKey = cacheKey.getRemoteVersionShareKey(m.getClass(), m.getShareId());
        redisClient.setex(remoteVersionKey, REMOTE_KEY_SECONDS, localShareVersion);
		//更新本地线程记录
		enlist(proxyM);

        return proxyM;
    }

    /**
	 * 向事务提交修改数据<br/>
	 * 该方法只是通知当前线程的事务什么数据发生了修改，并未向本地缓存和远程缓存写入修改内容
	 * @param proxyM
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    private <M extends BaseEntity, P extends EntityProxy<M>> P enlist(final P proxyM) throws Exception {
        return (P) transactionManager.enlist(proxyM);
    }

    /**
	 * 向事务提交修改数据<br/>
	 * 该方法只是通知当前线程的事务什么数据发生了修改，并未向本地缓存和远程缓存写入修改内容
	 * @param proxyMs
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    private <M extends BaseEntity, P extends EntityProxy<M>> Map<Serializable, P> enlist(final Map<Serializable, P> proxyMs) throws Exception {
        Map<Serializable, P> ps = new HashMap<>();
        for (Entry<Serializable, P> kv : proxyMs.entrySet()) {
            ps.put(kv.getKey(), (P) transactionManager.enlist(kv.getValue()));
        }
        return ps;
    }

    /**
	 * 逻辑删除数据
	 * @param proxyM
	 * @throws Exception
	 */
    public <M extends BaseEntity, P extends EntityProxy<M>> void remove(final P proxyM) throws Exception {
        transactionManager.oulist(proxyM);
    }

	/**
	 * 从本地线程获取已有的代理实例集合。获取范围包括已逻辑删除的数据，无视主从逻辑
	 * @param cls 代理实例对应的数据库类
	 * @param shareId
	 * @param isUpdate 获取的代理实例是否有必须有变更信息
	 * @return
	 */
	public <M extends BaseEntity, P extends EntityProxy<M>> Collection<P> readThreadCacheProxySet(Class<M> cls, Serializable shareId, boolean isUpdate) {
		Transaction tx = transactionManager.getTransaction();
		return tx.readThreadCacheProxySet(cls, shareId, isUpdate);
	}

	/**
	 * 回滚
	 */
    public void rollback() {
        transactionManager.rollback();
    }

    public void commit() {
        transactionManager.commit();
    }

    /**
	 * 获取本线程的自定义消息队列
	 * @return
	 */
    public Deque<Object> getMsgList() {
        return transactionManager.getMsgList();
    }
}
