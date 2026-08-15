package com.pearl.fcw.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Resource;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.BaseExtEntity;
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

    /**
     * 查询分逻辑一条数据<br/>
     * 因从逻辑数据分表储存，所以无法直接根据主键查询数据，必须依靠主逻辑主键判断该从逻辑数据所在的分表
     * @param constructor 从逻辑类的无参构造方法
     * @param shareId 从逻辑的主逻辑主键
     * @param id 从逻辑主键
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseExtEntity> T findEntity(Supplier<T> constructor, final Serializable shareId, final ID id) throws Exception {
        T t = constructor.get();
        t.setId((Integer) id);
        M m = (M) t;
        return (T) super.findEntity(m);
    }

}
