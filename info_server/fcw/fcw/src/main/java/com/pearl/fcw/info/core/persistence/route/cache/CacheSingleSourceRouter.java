package com.pearl.fcw.info.core.persistence.route.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.cache.RedisClient;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;
import com.pearl.fcw.info.core.persistence.route.SingleSourceRouter;
import com.pearl.fcw.info.core.persistence.utils.EntityUtils;

public class CacheSingleSourceRouter extends CacheSourceRouter implements
		SingleSourceRouter {

	private SingleSourceRouter singleSourceRouter = null;

	public CacheSingleSourceRouter(SingleSourceRouter singleSourceRouter,
			RedisClient cacheClient, InfoServerLock infoServerLock) {
		super(cacheClient, infoServerLock);
		this.singleSourceRouter = singleSourceRouter;
	}

	public void setSingleSourceRouter(SingleSourceRouter singleSourceRouter) {
		this.singleSourceRouter = singleSourceRouter;
	}

	@Override
	public String getSchema() {
		return singleSourceRouter.getSchema();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BaseEntity> T queryById(Integer id, Class<T> clazz) {
		final String key = CacheKeyUtil.getCacheKeyById(id, clazz);
		T entity;
		try {
			entity = (T) cacheClient.get(key);
		} catch (Exception e) {
			log.warn("exception appear when try to get pojoInCache in "
					+ getSchema() + " cacheClient.get(key), key = " + key);
			return singleSourceRouter.queryById(id, clazz);
		}

		if (entity == null) {
			entity = singleSourceRouter.queryById(id, clazz);
			if (entity != null) {
				try {
					createInCache(entity);
				} catch (Exception e) {
					log.error(
							"error happened during createORMPojoInCache, exception ",
							e);
				}
			}
		}

		return entity;
	}

	@Override
	public <T extends BaseEntity> List<T> queryByIds(Collection<Integer> ids,
			Class<T> clazz) {

		final List<T> result = new ArrayList<T>(ids.size());

		final Collection<Integer> idsNeedQueryFromDB = new HashSet<Integer>();

		/* 从缓存批量获取POJO */
		final Collection<String> cacheKeys = new ArrayList<String>(ids.size());
		for (long id : ids) {
			final String key = CacheKeyUtil.getCacheKeyById(id, clazz);
			cacheKeys.add(key);
		}

		final Map<String, Object> pojosInCache;
		try {
			pojosInCache = cacheClient.get(cacheKeys);
		} catch (Exception e) {
			log.warn("exception appear when try to get pojoInCaches in "
					+ getSchema() + ", key = " + cacheKeys);
			return singleSourceRouter.queryByIds(ids, clazz);
		}

		for (Map.Entry<String, Object> entry : pojosInCache.entrySet()) {
			@SuppressWarnings("unchecked")
			T entity = (T) entry.getValue();
			if (entity != null) {
				result.add(entity);
			} else {
				Integer dbId = CacheKeyUtil.getIdByCacheKey(entry.getKey());
				idsNeedQueryFromDB.add(dbId);
			}
		}

		/* 从数据库查找在cache中不存在的对象，并更新进cache */
		List<T> list = singleSourceRouter.queryByIds(idsNeedQueryFromDB, clazz);
		Map<Integer, T> resultFromDB = EntityUtils.toPKMap(list);
		for (Map.Entry<Integer, T> entry : resultFromDB.entrySet()) {
			if (entry.getValue() != null) {
				result.add(entry.getValue()); // 会出现Null的情况，表示指定ID的数据不存在，list中不保存
				try {
					createInCache(entry.getValue());
				} catch (Exception e) {
					log.error(
							"error happened during createORMPojoInCache, exception ",
							e);
				}
			}
		}

		return result;
	}

	@Override
	public <T extends BaseEntity> Map<Integer, T> queryByIdsForMap(
			Collection<Integer> ids, Class<T> clazz) {

		final Map<Integer, T> result = new HashMap<Integer, T>(ids.size());

		final Collection<Integer> idsNeedQueryFromDB = new HashSet<Integer>();

		/* 从缓存批量获取POJO */
		final Collection<String> cacheKeys = new ArrayList<String>(ids.size());
		for (long id : ids) {
			final String key = CacheKeyUtil.getCacheKeyById(id, clazz);
			cacheKeys.add(key);
		}

		final Map<String, Object> pojosInCache;
		try {
			pojosInCache = cacheClient.get(cacheKeys);
		} catch (Exception e) {
			log.warn("exception appear when try to get pojoInCaches in "
					+ getSchema() + " cacheClient.get(cacheKeys), key = "
					+ cacheKeys);
			return singleSourceRouter.queryByIdsForMap(ids, clazz);
		}

		for (Map.Entry<String, Object> entry : pojosInCache.entrySet()) {
			@SuppressWarnings("unchecked")
			T pojo = (T) entry.getValue();
			if (pojo != null) {
				result.put(pojo.getId(), pojo);
			} else {
				Integer dbId = CacheKeyUtil.getIdByCacheKey(entry.getKey());
				idsNeedQueryFromDB.add(dbId);
			}
		}

		/* 从数据库查找在cache中不存在的对象，并更新进cache */
		Map<Integer, T> resultFromDB = singleSourceRouter.queryByIdsForMap(
				idsNeedQueryFromDB, clazz);
		for (Map.Entry<Integer, T> entry : resultFromDB.entrySet()) {
			result.put(entry.getKey(), entry.getValue());
			if (entry.getValue() != null) { // 不可能的吧……
				try {
					createInCache(entry.getValue());
				} catch (Exception e) {
					log.error(
							"error happened during createORMPojoInCache, exception ",
							e);
				}
			}
		}

		/* 处理null */
		if (result.size() != ids.size()) {
			for (Integer id : ids) {
				if (!result.containsKey(id)) {
					result.put(id, null);
				}
			}
		}

		return result;
	}

	@Override
	public <T extends BaseEntity> List<T> queryByFK(long fk, Class<T> clazz,
			String fieldName) {
		List<Integer> ids = queryIdsByFK(fk, clazz, fieldName);
		return queryByIds(ids, clazz);
	}

	@Override
	public <T extends BaseEntity> List<T> queryByFK(long fk, Class<T> clazz,
			Class<?> foreignClass) {
		ClassMetadataWrapper<T> wrapper = ClassMetadataConfig
				.getClassMetadataWrapper(clazz);
		String fieldName = wrapper
				.getSingleReferencedJavaFieldName(foreignClass);
		return queryByFK(fk, clazz, fieldName);
	}

	@Override
	public <T extends BaseEntity> List<T> queryAll(Class<T> clazz) {
		List<Integer> ids = queryAllIds(clazz);
		return queryByIds(ids, clazz);
	}

	@Override
	public <T extends BaseEntity> List<T> query(Class<T> clazz, String sql,
			Object... parameters) {
		return singleSourceRouter.query(clazz, sql, parameters);
	}

	@Override
	public <T extends BaseEntity> List<T> query(Class<T> clazz,
			String namedSql, Map<String, Object> namedParameters) {
		return singleSourceRouter.query(clazz, namedSql, namedParameters);
	}

	@Override
	public <T extends BaseEntity> List<T> query(Class<T> clazz, String sql,
			T entity) {
		return singleSourceRouter.query(clazz, sql, entity);
	}

	@Override
	public <T extends BaseEntity> int getMaxID(Class<T> clazz) {
		return singleSourceRouter.getMaxID(clazz);
	}

	@Override
	public <T extends BaseEntity> List<Integer> queryIdsByFK(long fk,
			Class<T> clazz, String fieldName) {

		List<Integer> ids = null;

		String key = CacheKeyUtil.getCacheKeyByForeignId(fk, clazz, fieldName);
		ids = cacheClient.safeGet(key);

		if (ids == null) {
			ids = singleSourceRouter.queryIdsByFK(fk, clazz, fieldName);
			cacheClient.set(key, ids);
		}

		return ids;

	}

	@Override
	public <T extends BaseEntity> List<Integer> queryAllIds(Class<T> clazz) {

		List<Integer> ids = null;

		// 从缓存中获得级联关系,返回ids
		String key = CacheKeyUtil.getCacheKeyForAll(clazz);
		ids = cacheClient.safeGet(key);

		if (ids == null || ids.size() == 0) {
			// 因为级联关系不在缓存，此处会直接获得所有关联对象， 之后代码会根据级联关系从缓存批量获取对象，
			ids = singleSourceRouter.queryAllIds(clazz);
			cacheClient.set(key, ids);
		}

		return ids;

	}

	@Override
	public <T extends BaseEntity> int insert(T entity) {
		singleSourceRouter.insert(entity);
		super.createInCache(entity);
		return 1;
	}

	@Override
	public <T extends BaseEntity> int insertNoIncrement(T entity) {
		singleSourceRouter.insertNoIncrement(entity);
		super.createInCache(entity);
		return 1;
	}

	@Override
	public <T extends BaseEntity> int update(T entity) {
		boolean success = super.updateInCache(entity);
		if (!success) {
			// 未成功放入缓存，则立即更新数据库
			return singleSourceRouter.update(entity);
		}
		return 1;
	}

	public <T extends BaseEntity> int updateImmediately(T entity) {
		int result = singleSourceRouter.update(entity);
		if (result > 0) {
			super.updateInCacheOnly(entity);
		}
		return result;
	}

	@Override
	public <T extends BaseEntity> int delete(T entity) {
		int result = singleSourceRouter.delete(entity);
		if (result > 0) {
			super.deleteInCache(entity);
		}
		return result;
	}

	@Override
	public <T extends BaseEntity> int hardDelete(T entity) {
		int result = singleSourceRouter.hardDelete(entity);
		if (result > 0) {
			super.deleteInCache(entity);
		}
		return result;
	}

	@Override
	public int queryForInt(String sql, Object... parameters) {
		return singleSourceRouter.queryForInt(sql, parameters);
	}

	@Override
	public List<Integer> queryForLongList(String sql, Object... parameters) {
		return singleSourceRouter.queryForLongList(sql, parameters);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Object... parameters) {
		return singleSourceRouter.query(sql, parameters);
	}

	@Override
	public int update(String sql, Object... parameters) {
		return singleSourceRouter.update(sql, parameters);
	}

	@Override
	public <Y> Y queryForObject(String sql, Class<Y> requiredType,
			Object... parameters) {
		return singleSourceRouter.queryForObject(sql, requiredType, parameters);
	}

	@Override
	public void batchUpdate(Collection<BaseEntity> entity) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void batchUpdateCache(Collection<?> entities) {
		singleSourceRouter.batchUpdate((Collection<BaseEntity>) entities);
	}

	@Override
	public <T extends BaseEntity> void batchInsert(List<T> entities) {
		singleSourceRouter.batchInsert(entities);
	}

	@Override
	public <T extends BaseEntity> void batchInsertNoIncrement(List<T> entities) {
		singleSourceRouter.batchInsertNoIncrement(entities);
	}

	@Override
	public List<String> getTableColumns(String tableName) {
		return singleSourceRouter.getTableColumns(tableName);
	}

	@Override
	public void execute(String sql) {
		singleSourceRouter.execute(sql);
	}

	@Override
	public Map<String, Object> queryForMap(String sql) {
		return singleSourceRouter.queryForMap(sql);
	}

	@Override
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return singleSourceRouter.namedParameterJdbcTemplate();
	}

}
