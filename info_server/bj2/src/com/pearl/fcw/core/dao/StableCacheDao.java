package com.pearl.fcw.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.gm.pojo.WSysConfig;

/**
 * 游戏系统数据和GM数据因不经常发生变更，所以在内存中常驻ConcurrentHashMap数据结构
 */
public class StableCacheDao<M extends BaseEntity, ID extends Serializable> extends CacheDao<M, ID> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final Map<Serializable, M> stableData = new ConcurrentHashMap<>();

	public Map<Serializable, M> getStableData() {
		try {
			if (stableData.isEmpty()) {
				logger.info("=====StartTime : " + System.currentTimeMillis() + "=====");
				//                stableData.putAll(super.findMap(null));
				if (entityClass.equals(WSysConfig.class)) {
					stableData.putAll(super.findList(null).stream().collect(Collectors.toMap(p -> ((WSysConfig) p).getKey(), p -> p)));
				} else {
					stableData.putAll(super.findList(null).stream().filter(p -> !p.getIsRemoved()).collect(Collectors.toMap(p -> p.getId(), p -> p)));
				}
				logger.info(getClass().getSimpleName() + " stableData size : " + stableData.size());
				logger.info("=====EndTime : " + System.currentTimeMillis() + "=====");
			}
		} catch (Exception e) {
			logger.error(getClass().getSimpleName() + " constructor error : ", e);
		}
		return stableData;
	}

	@Override
	public M add(M t) throws Exception {
		t = super.add(t);
		getStableData().put(t.getId(), t);
		return t;
	}

	@Override
	public M save(M t) throws Exception {
		t = super.save(t);
		getStableData().put(t.getId(), t);
		return t;
	}

	@Override
	public M update(M t) throws Exception {
		t = super.update(t);
		getStableData().put(t.getId(), t);
		return t;
	}

	@Override
	public int delete(ID id) throws Exception {
		getStableData().remove(id);
		return super.delete(id);
	}

	@Override
	public M findEntity(ID id) throws Exception {
		return getStableData().get(id);
	}

	@Override
	public M findEntity(M m) throws Exception {
		return getStableData().get(m.getId());
	}

	@Override
	public M findEntityByPrimaryKey(Serializable id) throws Exception {
		return getStableData().get(id);
	}

	@Override
	public void replace(List<M> list) throws Exception {
		super.replace(list);
		getStableData().clear();
		getStableData();
	}

	@Override
	public List<M> findList(M t) throws Exception {
		if (null == t) {
			return getStableData().values().stream().collect(Collectors.toList());
		}
		return super.findList(t);
	}

	/**
	 * 导出数据专用，可以包含逻辑删除的数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public List<M> findListContainsRemoved(M t) throws Exception {
		return super.findList(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<ID, M> findMap(Serializable t) throws Exception {
		if (null == t) {
			return getStableData().entrySet().stream().collect(Collectors.toMap(kv -> (ID) kv.getKey(), kv -> kv.getValue()));
		}
		return super.findMap(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<ID, M> findMap(M t) throws Exception {
		if (null == t) {
			return getStableData().entrySet().stream().collect(Collectors.toMap(kv -> (ID) kv.getKey(), kv -> kv.getValue()));
		}
		return super.findMap(t);
	}

}
