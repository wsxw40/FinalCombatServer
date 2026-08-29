package com.pearl.fcw.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.GmThreadPool;

/**
 * 增加分表处理
 */
public class CacheDao<M extends BaseEntity, ID extends Serializable> extends DmDaoImpl<M, ID> {
    @Resource
    private GmThreadPool gmThreadPool;

    private static Map<Class<? extends BaseEntity>, CacheDao<? extends BaseEntity, ? extends Serializable>> modelAndDaoMap = new HashMap<>();

    public static Map<Class<? extends BaseEntity>, CacheDao<? extends BaseEntity, ? extends Serializable>> getModelAndDaoMap() {
        return modelAndDaoMap;
    }

    public CacheDao() {
        if (null != entityClass) {
            modelAndDaoMap.put(entityClass, this);
        }
    }

    /**
	 * 为减少SQL语句长度，提高效率，把List做分片处理后再批量插入或者更新
	 * @param list
	 * @throws Exception
	 */
    @SuppressWarnings("unchecked")
    public void replaceBatch(List<BaseEntity> list) throws Exception {
		if (null == list || list.isEmpty()) {
			return;
		}
		int separator = 1000;//分片长度
        int separatorIndex = 0;
        List<M> mList = new ArrayList<>();
        for (BaseEntity m : list) {
            if (null != m && m.getClass().equals(entityClass)) {
                mList.add((M) m);
                if (separatorIndex < separator) {
                    separatorIndex++;
                } else {
                    replace(mList);
                    mList.clear();
                    separatorIndex = 0;
                }
            }
        }
        if (!mList.isEmpty()) {
            replace(mList);
        }
		//清理对应的缓存
		gmThreadPool.delete(list.get(0), null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public M add(M t) throws Exception {
        ColumnDescriptor<M> des = ColumnDescriptor.apply(t.getClass());
        if (null != des) {
			des.set(t);
        }
        return super.add(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public M save(M t) throws Exception {
        ColumnDescriptor<M> des = ColumnDescriptor.apply(t.getClass());
        if (null != des) {
            des.set(t);
        }
        return super.save(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public M update(M t) throws Exception {
        ColumnDescriptor<M> des = ColumnDescriptor.apply(t.getClass());
        if (null != des) {
            des.set(t);
        }
		M m = super.update(t);
		if (null != des) {
			m = des.get(m);
		}
		return m;
    }

    @SuppressWarnings("unchecked")
    @Override
    public M findEntity(ID id) throws Exception {
        M m = super.findEntity(id);
        if (null != m) {
            ColumnDescriptor<M> des = ColumnDescriptor.apply(m.getClass());
            if (null != des) {
                return des.get(m);
            }
        }
        return m;
    }

    @SuppressWarnings("unchecked")
    @Override
    public M findEntity(M m) throws Exception {
        m = super.findEntity(m);
        if (null != m) {
            ColumnDescriptor<M> des = ColumnDescriptor.apply(m.getClass());
            if (null != des) {
                return des.get(m);
            }
        }
        return m;
    }

    @SuppressWarnings("unchecked")
    @Override
    public M findEntityByPrimaryKey(Serializable id) throws Exception {
        M m = super.findEntityByPrimaryKey(id);
        if (null != m) {
            ColumnDescriptor<M> des = ColumnDescriptor.apply(m.getClass());
            if (null != des) {
                return des.get(m);
            }
        }
        return m;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<M> findList(M t) throws Exception {
        List<M> mList = super.findList(t);
        if (!mList.isEmpty()) {
            ColumnDescriptor<M> des = ColumnDescriptor.apply(mList.get(0).getClass());
            if (null != des) {
                for (M m : mList) {
                    m = des.get(m);
                }
            }
        }
        return mList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<ID, M> findMap(Serializable t) throws Exception {
        Map<ID, M> map = super.findMap(t);
        if (!map.isEmpty()) {
            ColumnDescriptor<M> des = ColumnDescriptor.apply((Class<M>) Stream.of(map.values()).collect(Collectors.toList()).get(0).getClass());
            if (null != des) {
                for (M m : map.values()) {
                    m = des.get(m);
                }
            }

        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<ID, M> findMap(M t) throws Exception {
        Map<ID, M> map = super.findMap(t);
        if (!map.isEmpty()) {
            Class<M> cls = (Class<M>) map.values().toArray()[0].getClass();
            ColumnDescriptor<M> des = ColumnDescriptor.apply(cls);
            if (null != des) {
                for (M m : map.values()) {
                    m = des.get(m);
                }
            }

        }
        return map;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<ID, M> findMap(M t, String keyColumn) throws Exception {
        Map<ID, M> map = super.findMap(t, keyColumn);
        if (!map.isEmpty()) {
            ColumnDescriptor<M> des = ColumnDescriptor.apply((Class<M>) Stream.of(map.values()).collect(Collectors.toList()).get(0).getClass());
            if (null != des) {
                for (M m : map.values()) {
                    m = des.get(m);
                }
            }

        }
        return map;
    }

    @Override
    public DataTablesPage<M> findPage(DataTablesParameter param) throws Exception {
        // TODO Auto-generated method stub
        return super.findPage(param);
    }

    @Override
    public void replace(List<M> list) throws Exception {
        // TODO Auto-generated method stub
        super.replace(list);
    }

    //    /**
	//     * 查询分逻辑一条数据<br/>
	//     * 因从逻辑数据分表储存，所以无法直接根据主键查询数据，必须依靠主逻辑主键判断该从逻辑数据所在的分表
	//     * @param constructor 从逻辑类的无参构造方法
	//     * @param shareId 从逻辑的主逻辑主键
	//     * @param id 从逻辑主键
    //     * @return
    //     * @throws Exception
    //     */
    //    @SuppressWarnings("unchecked")
    //    public <T extends BaseExtEntity> T findEntity(Supplier<T> constructor, final Serializable shareId, final ID id) throws Exception {
    //        T t = constructor.get();
    //        t.setId((Integer) id);
    //        M m = (M) t;
    //        return (T) super.findEntity(m);
    //    }

}
