package com.pearl.o2o.dao.impl.nonjoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.CacheWrapper;
import com.pearl.o2o.pojo.DBMappingBean.Status;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DBRouteUtil;
import com.pearl.o2o.utils.DaoCacheUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class BaseMappingDao extends SqlMapClientDaoSupport {
	private static final Logger LOG = LoggerFactory.getLogger(BaseMappingDao.class);
	public static final int DEFAULT_ID = 1;
	private static final int RETRY_TIMES = 5;
	public static int num = 1;
	protected MemcachedClient mcc;

	protected LoadingCache<Class<? extends BaseMappingBean>, Map<Integer,? extends BaseMappingBean>> tableLocalcache = CacheBuilder.newBuilder()
			.build(new CacheLoader<Class<? extends BaseMappingBean>, Map<Integer,? extends BaseMappingBean>>(){
				@Override
				public Map<Integer,? extends BaseMappingBean> load(Class<? extends BaseMappingBean> clazz) throws Exception {
					return queryMappingBeanMap(clazz);
				}
			});
	protected Cache<Class<? extends BaseMappingDao>, Object> daoLocalcache = CacheBuilder.newBuilder().<Class<? extends BaseMappingDao>, Object>build();
	private static final String UPDATE_SQL = "update";
	private static final String DELETE_SQL = "delete";
	private static final String INSERT_SQL = "insert";
	private static final String SELECT_SQL = "select";

	@SuppressWarnings("unchecked")
	private static String getStandardUpdateSqlName(Class<? extends BaseMappingBean> clazz) {
		return clazz.getSimpleName() + "." + UPDATE_SQL;
	}

	@SuppressWarnings("unchecked")
	private static String getStandardSelectSqlName(Class<? extends BaseMappingBean> clazz) {
		return clazz.getSimpleName() + "." + SELECT_SQL;
	}

	@SuppressWarnings("unchecked")
	private static String getStandardDeleteSqlName(Class<? extends BaseMappingBean> clazz) {
		return clazz.getSimpleName() + "." + DELETE_SQL;
	}

	@SuppressWarnings("unchecked")
	private static String getStandardInsertSqlName(Class<? extends BaseMappingBean> clazz) {
		return clazz.getSimpleName() + "." + INSERT_SQL;
	}

	@SuppressWarnings("unchecked")
	private static String getMappingBeanKey(Class<? extends BaseMappingBean> mappingBeanClass, int id) {
		return DaoCacheUtil.oCacheKey(mappingBeanClass, id);
	}

	@SuppressWarnings("unchecked")
    protected <T extends BaseMappingBean<T>> T queryMappingBeanById(Class<T> returnClass, int id) {
		String key = getMappingBeanKey(returnClass, id);

		HashMap param = new HashMap();
		param.put("id", id);

		BaseMappingBean<T> bean = queryFromCacheOrDB( param, key, returnClass).getSingleDBMappingBean();

		return (T) bean;
	}

	protected <T extends BaseMappingBean<T>> Map<Integer, T> queryMappingBeanMap(Class<T> returnClass) {
		return queryMappingBeanMapByRelatedId(returnClass, DEFAULT_ID);
	}

	@SuppressWarnings("unchecked")
	protected <T extends BaseMappingBean<T>> Map<Integer, T> queryMappingBeanMapByRelatedId(Class<T> returnClass, int fid_playerid) {
		String key = getMappingBeanKey(returnClass, fid_playerid);

		HashMap param = new HashMap();
		param.put("fid", fid_playerid);
        param.put("tableSuffix", DBRouteUtil.getTableSuffix(returnClass, fid_playerid));// 只有外键查询，可进行分表

		Map<Integer, BaseMappingBean<T>> map = queryFromCacheOrDB( param, key, returnClass).getMappingBeans();

		if (map == null) {
			return new HashMap<Integer, T>();
		} else {
			return (Map<Integer, T>) map;
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends BaseMappingBean<T>> CacheWrapper<T> queryFromCacheOrDB(Map<?, ?> param, String key, Class<T> resultClass) {
		String sqlName = getStandardSelectSqlName(resultClass);
		CacheWrapper<T> wrapper = null;
		try {
			wrapper = getCacheWrapper(key);
		} catch (Exception e) {
			LOG.error("Error occur during queryFromCacheOrDB, exception is " , e);
		}
		if (wrapper != null) {
			return wrapper;
		} else {
			Map<Integer, BaseMappingBean<T>> mappingMap = this.getSqlMapClientTemplate().queryForMap(sqlName, param, "id");
			if (mappingMap == null) {
				return null;
			}

			wrapper = new CacheWrapper<T>(key, mappingMap);
			try {
				saveMappingBeanCache(wrapper);
			} catch (Exception e) {
				LOG.error("Error during put object into cache" , e);
			}
			return wrapper;
		}
	}

	public <T> void updateMappingBeanInCacheWithDefaultId(BaseMappingBean<T> obj) throws Exception {
		updateMappingBeanInCache(obj, DEFAULT_ID);
	}

	public <T> int updateObjInDB(BaseMappingBean<T> obj) throws Exception {
		return this.getSqlMapClientTemplate().update(getStandardUpdateSqlName(obj.getClass()), obj);
	}

	protected <T> int insertObjInDB(BaseMappingBean<T> obj) throws Exception {
		return (Integer) this.getSqlMapClientTemplate().insert(getStandardInsertSqlName(obj.getClass()), obj);
	}

	public <T> void deleteObjInDB(BaseMappingBean<T> obj) throws Exception {
		this.getSqlMapClientTemplate().update(getStandardDeleteSqlName(obj.getClass()), obj);
	}

	protected <T> int insertObjIntoDBAndCache(BaseMappingBean<T> obj) throws Exception {
		return insertObjIntoDBAndCache(obj, -1);
	}

	protected <T> int insertObjIntoDBAndCacheWithDefaultId(BaseMappingBean<T> obj) throws Exception {
		return insertObjIntoDBAndCache(obj, DEFAULT_ID);
	}

	protected <T> void deleteObjFromDBAndCacheWithDefaultId(BaseMappingBean<T> obj) throws Exception {
		deleteObjFromDBAndCache(obj, DEFAULT_ID);
	}

	        /**
     * 立即插入数据库，并更新到CACHE
	 * 
     * @param <T>
     * @param objs
     * @param fid_playerid
     *            在CACHE中关联的ID
     * @return
     * @throws Exception
     */
	protected <T> int insertObjIntoDBAndCache(BaseMappingBean<T> obj, int fid_playerid) throws Exception {

		int newId = insertObjInDB(obj);
		obj.setId(newId);

		String key = "";

		if (fid_playerid <= 0) {
			key = getMappingBeanKey(obj.getClass(), obj.getId());
		} else {
			key = getMappingBeanKey(obj.getClass(), fid_playerid);
		}

		int remainRetryTimes = RETRY_TIMES;
		boolean updateSuccess = false;

        while (!updateSuccess && remainRetryTimes > 0) {// 如果缓存CAS更新失败则重试
            CacheWrapper<T> wrapper = getCacheWrapper(key);// 如果该对象以集合形式存放，可能已经存在该KEY
			if (wrapper != null) {
				wrapper.addMappingBean(obj);

				updateSuccess = updateCacheWraper(wrapper,obj,Status.FRESH);
				remainRetryTimes--;
            } else {// 如果之前不存在于缓存中，则等下次查询会自然放入缓存,不用手动放入缓存
				updateSuccess = true;
			}
		}

		if (!updateSuccess) {
			LOG.warn("cas update fail, key: " + key);
			throw new Exception("insertMappingBeanInCache fail, cas update fail");
		}

		return newId;
	}

	/*
	 * public <T> int updateMappingBeanInCacheImmediately(BaseMappingBean<T>
	 * obj, int id) throws Exception{ String key =
	 * getMappingBeanKey(obj.getClass(),id); Set<String> mappingBeanKeys = new
	 * HashSet<String>(); mappingBeanKeys.add(key);
	 *
	 * Map<String, CacheWrapperMeta> metaInCache = mcc.get(mappingBeanKeys);
	 * synCacheToDB(cacheWrapperMetas); }
	 */

	        /**
     * 更新对象，该对象以多对一中多的关系存放在另一个对象的缓存中
	 * 
     * @param id
     *            该对象多对一中， 一的 对象的ID
     */
	public <T> void updateMappingBeanInCache(BaseMappingBean<T> obj, int id) throws Exception {
		String key = getMappingBeanKey(obj.getClass(), id);
		int remainRetryTimes = RETRY_TIMES;
		boolean updateSuccess = false;

        while (!updateSuccess && remainRetryTimes > 0) {// 如果缓存CAS更新失败则重试
			CacheWrapper<T> wrapper = getCacheWrapper(key);
			if (wrapper != null) {
				Map<Integer, BaseMappingBean<T>> map = wrapper.getMappingBeans();
				map.put(obj.getId(), obj);

				updateSuccess = updateCacheWraper(wrapper,obj,Status.CHANGED);
				remainRetryTimes--;
			} else {
				updateObjInDB(obj);
				updateSuccess = true;
			}
		}

		if (!updateSuccess) {
			LOG.warn("cas update fail, key: " + key);
			throw new Exception("updaetMappingBeanInCache fail, cas update fail");
		}
	}

	protected <T> void deleteObjFromDBAndCache(BaseMappingBean<T> obj, int id) throws Exception {
		deleteObjInDB(obj);
		String key = getMappingBeanKey(obj.getClass(), id);

		int remainRetryTimes = RETRY_TIMES;
		boolean updateSuccess = false;

        while (!updateSuccess && remainRetryTimes > 0) {// 如果缓存CAS更新失败则重试
			CacheWrapper<T> cacheWrapper = getCacheWrapper(key);
			if (cacheWrapper != null) {
				cacheWrapper.getMappingBeans().remove(obj.getId());

				updateSuccess = updateCacheWraper(cacheWrapper,obj,Status.DELETED);
				remainRetryTimes--;
			} else {
				updateSuccess = true;
			}
		}

		if (!updateSuccess) {
			LOG.warn("cas update fail, key: " + key);
			throw new Exception("deleteMappingBeanInCache fail, cas update fail");
		}
	}

	        /**
     * 返回时会附带最新cas值
	 * 
     * @param <T>
     * @param key
     * @return
     * @throws Exception
     */

	private <T> CacheWrapper<T> getCacheWrapper(String key) throws MemcachedException, InterruptedException, TimeoutException {
		GetsResponse<CacheWrapper<T>> getResponse = mcc.gets(key, Constants.CACHE_TIMEOUT);
		CacheWrapper<T> result = null;
		if (getResponse != null) {
			result = getResponse.getValue();
			result.setCas(getResponse.getCas());
		}
		return result;
	}

	private <T> boolean saveMappingBeanCache(CacheWrapper<T> wrapper) throws Exception {
		int expr = wrapper.getExprTimeInCache();
		boolean result = false;
		mcc.set(wrapper.getKey(), expr, wrapper, Constants.CACHE_TIMEOUT);
		result = true;

//		CacheWrapperMeta meta = new CacheWrapperMeta(wrapper);
//		mcc.set(meta.getKey(), expr, meta, Constants.CACHE_TIMEOUT);

		return result;
	}
    /**
     * 将CacheWrapper及它的元数据对象放入cache 元对象只保留该cache元信息，
     * 用于快速检测,并使原长期不访问的cachewrapper失效. 每次都会直接更新元数据对象
	 * 
	 * 
     * 如果CacheWrapper对象的cas值 大于0，则会以CAS的方式更新，并发情况下，可能失败，需要上层处理逻辑
     * 如果为新添加CacheWrapper，则cas字段会初始化为小于0的数，此时不进行CAS检查
	 * 
     * @param wrapper
     * @throws Exception
     * @throws Exception
     */
	private <T> boolean updateCacheWraper(final CacheWrapper<T> wrapper,final BaseMappingBean<T> obj,final Status status) throws Exception {
		int expr = wrapper.getExprTimeInCache();
		boolean result = false;
		if (wrapper.getCas() <= 0) {
			mcc.set(wrapper.getKey(), expr, wrapper, Constants.CACHE_TIMEOUT);
			result = true;
		} else {
			result = mcc.cas(wrapper.getKey(), expr, wrapper, Constants.CACHE_TIMEOUT, wrapper.getCas());
		}

		if (result&&status == Status.CHANGED) {
			logBaseMappingBeanChanageStatus(wrapper.getKey(),obj);
		}

		return result;
	}

	private <T> void logBaseMappingBeanChanageStatus(final String wrapperKey,final BaseMappingBean<T> obj){
		String[] dc_clazz_refId = wrapperKey.split(Constants.DELIMITER);
		String clazz = dc_clazz_refId[1];
		String refId = dc_clazz_refId[2];
		String id = obj.getId()+"";

		String dirtyMappingBeanKey = DaoCacheUtil.oDirtyMappingBeanKey(clazz);
		String dirtyMappingBeanValue = DaoCacheUtil.oDirtyMappingBeanValue(refId,id+"");


		boolean sAddSuccess = false;
		for(int i =0;i<2;i++){
			long time = System.currentTimeMillis();
			try {
				ServiceLocator.nosqlMetaService.getNosql().sAdd(dirtyMappingBeanKey, dirtyMappingBeanValue);
				sAddSuccess = true;
				break;
			} catch (Exception e) {
				time = System.currentTimeMillis() - time;
				ServiceLocator.syncacheLog.error(String.format("updateCacheWraper;sAdd:error;time:%sms;attempt:%s;dirtyMappingBeanKey:%s;dirtyMappingBeanValue:%s;",time,i,dirtyMappingBeanKey,dirtyMappingBeanValue),e);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					ServiceLocator.syncacheLog.error(String.format("updateCacheWraper;sleep:error;time:%sms;attempt:%s;dirtyMappingBeanKey:%s;dirtyMappingBeanValue:%s;",time,i,dirtyMappingBeanKey,dirtyMappingBeanValue),e);
				}
			}
		}
		if(!sAddSuccess){
			try{
				updateObjInDB(obj);
				ServiceLocator.syncacheLog.info(String.format("updateCacheWraper;sAdd:fail;updateObjInDB:success;dirtyMappingBeanKey:%s;dirtyMappingBeanValue:%s;",dirtyMappingBeanKey,dirtyMappingBeanValue));
			}catch (Exception e) {
				ServiceLocator.syncacheLog.error(String.format("updateCacheWraper;sAdd:fail;updateObjInDB:Exception;dirtyMappingBeanKey:%s;dirtyMappingBeanValue:%s;",dirtyMappingBeanKey,dirtyMappingBeanValue),e);
			}
		}
	}

	        /**
	 * 
     * 同步Cache到数据库 ， 用在定时任务中
	 * 
     * redis data
     * DMB_clazz -- fId_id
     * ||
     * ||
     * \/
     * DMB_clazz
	 * 
     * clazz+fId id
     * || ||
     * || ||
     * \/ \/
	 * 						
     * memcache wrapper key
     * DC_clazz_fId -- wrapper
     * id -- bean
	 * 
	 * 
     * @param cacheWrapperMeta
     *            获得的所有缓存元数据
     */
	public Map<String,Set<Integer>> getDirtyMappingBeanKeys(){
		long time = System.currentTimeMillis();
		ServiceLocator.syncacheLog.debug("getDirtyMappingBeanKeys;start;"+time);
		Map<String,Set<Integer>> dirtyCacheWrapperKeys = new HashMap<String, Set<Integer>>();
		int mappingBeanSize = 0;
		try {
			NoSql nosql = ServiceLocator.nosqlMetaService.getNosql();
			Set<String> dmbKeys = nosql.keys(DaoCacheUtil.DirtyMappingBeanKeysPattern);
			if(null != dmbKeys){
				//DMB_clazz
				for (String dmbKey : dmbKeys) {
					String[] dmb_clazz = dmbKey.split(Constants.DELIMITER);
					String clazz = dmb_clazz[1];
					String dumpKey = DaoCacheUtil.oDUMPDirtyMappingBeanKey(clazz);
					//DUMP_clazz
					nosql.rename(dmbKey, dumpKey);
                    for (int i = 0; i < 3; i++) {//失败，尝试三次
						try {
							Set<String> members = nosql.sMembers(dumpKey);
							//fId_id
							for (String tmp : members) {
								String[] fId_id = tmp.split(Constants.DELIMITER);
								String fId = fId_id[0];
								int id = Integer.parseInt(fId_id[1]);

								String dirtyCacheWrapperKey = DaoCacheUtil.oCacheKey(clazz, fId);
								Set<Integer> dirtyMappingBeanKeys = dirtyCacheWrapperKeys.get(dirtyCacheWrapperKey);
								if(null == dirtyMappingBeanKeys){
									dirtyMappingBeanKeys = new HashSet<Integer>();
									dirtyCacheWrapperKeys.put(dirtyCacheWrapperKey, dirtyMappingBeanKeys);
								}
								dirtyMappingBeanKeys.add(id);
							}
							mappingBeanSize += members.size();
							break;
						} catch (Exception e) {
							ServiceLocator.syncacheLog.warn(String.format("getDirtyMappingBeanKeys;sMembers;Exception;dumpKey:%s;attempt:%s",dumpKey,i),e);
							Thread.sleep(10);
						}
					}
					nosql.delete(dumpKey);
				}
			}
		} catch (Exception e) {
			logger.warn("getmappingBeanKeysByRedis;Exception;",e);
		}
		time = System.currentTimeMillis() - time;
		ServiceLocator.syncacheLog.info(String.format("getDirtyMappingBeanKeys;end;dirtyCacheWrapperKeySize:%s;dirtyMappingBeanSize:%s;time:%sms;",dirtyCacheWrapperKeys.size(),mappingBeanSize,time));
		return dirtyCacheWrapperKeys;
	}
	private static int SYN_CACHE_TO_DB_PAGESIZE = 15;
	public void synCacheToDB(Map<String,Set<Integer>> dirtyCacheWrapperKeys) throws Exception {
		long time = System.currentTimeMillis();

		List<String> mappingBeanKeysList = new ArrayList<String>(dirtyCacheWrapperKeys.keySet());

		final int mappingBeanKeySize = mappingBeanKeysList.size();

        int syncItems = 0;// 同步个数计算

		int startIndex = 0;
		int endIndex = 0;
		while (startIndex < mappingBeanKeySize) {
			endIndex = Math.min(startIndex + SYN_CACHE_TO_DB_PAGESIZE, mappingBeanKeySize);
			Map<String, CacheWrapper<?>> tempMap = mcc.get(mappingBeanKeysList.subList(startIndex, endIndex), Constants.CACHE_TIMEOUT);
			if (tempMap != null) {
				for(Entry<String, CacheWrapper<?>> cacheWrappers:tempMap.entrySet()){
					String dirtyCacheWrapperKey = cacheWrappers.getKey();
					CacheWrapper<?> cw = cacheWrappers.getValue();
					int tmpDirtyMappingBeanId = 0;
					try {
						if (cw == null || cw.getMappingBeans() == null) {
							ServiceLocator.syncacheLog.warn(String.format("synCacheToDB;synCacheToDB;cw == null || cw.getMappingBeans() == null;dirtyCacheWrapperKey:%s;",dirtyCacheWrapperKey));
							continue;
						}
						for (int dirtyMappingBeanId : dirtyCacheWrapperKeys.get(dirtyCacheWrapperKey)) {
							tmpDirtyMappingBeanId = dirtyMappingBeanId;
							BaseMappingBean<?> dirtyMappingBean = cw.getMappingBeans().get(dirtyMappingBeanId);
							if (null != dirtyMappingBean) {
								updateObjInDB(dirtyMappingBean);
								syncItems++;
								LOG.debug("syn key:" + dirtyMappingBeanId);
							}else{
								ServiceLocator.syncacheLog.warn(String.format("synCacheToDB;dirtyMappingBean == null;dirtyCacheWrapperKey:%s;dirtyMappingBeanId:%s;",dirtyCacheWrapperKey,tmpDirtyMappingBeanId));
							}
						}
					} catch (DataIntegrityViolationException e) {
						ServiceLocator.syncacheLog.warn(String.format("synCacheToDB;DataIntegrityViolationException;dirtyCacheWrapperKey:%s;dirtyMappingBeanId:%s;", dirtyCacheWrapperKey,tmpDirtyMappingBeanId),e);
					} catch (Exception e) {
						ServiceLocator.syncacheLog.warn(String.format("synCacheToDB;Exception;dirtyCacheWrapperKey:%s;dirtyMappingBeanId:%s;", dirtyCacheWrapperKey,tmpDirtyMappingBeanId),e);
					}
				}
			}
			startIndex += SYN_CACHE_TO_DB_PAGESIZE;
		}
		time = System.currentTimeMillis() - time;
		ServiceLocator.syncacheLog.debug(String.format("synCacheToDB;end;totalTime:%sms;totalItems:%s" ,time, syncItems));
	}

	public void deleteCacheWrapper(String key) {
		try {
			mcc.delete(key);
		} catch (Exception e) {
			LOG.warn("Error happend during deleteCacheWrapper" + e);
		}
	}

	public MemcachedClient getMcc() {
		return mcc;
	}

	public void setMcc(MemcachedClient mcc) {
		this.mcc = mcc;
	}
}
