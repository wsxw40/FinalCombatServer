package com.pearl.fcw.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.StringUtil;

@SuppressWarnings("unchecked")
public abstract class DmDaoImpl<M extends BaseEntity, ID extends Serializable> implements DmDao<M, ID> {
    protected Class<M> entityClass = null;
    protected String mapperName = null;

    @Resource
    private SqlSessionTemplate sqlSession;

    public SqlSession getSession() {
        return sqlSession;
    }

    public DmDaoImpl() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            mapperName = entityClass.getName() + "Mapper";
            //            modelAndDaoMap.put(entityClass, this);
        }
    }

    @Override
    public M add(final M t) throws Exception {
		if (null == t.getCreateTime()) {
			t.setCreateTime(new Date());
			t.setUpdateTime(t.getCreateTime());
		}
        getSession().insert(mapperName + ".insertSelective", t);
        return t;
    }

    @Override
    public M save(final M t) throws Exception {
		if (null == t.getUpdateTime()) {
			t.setUpdateTime(t.getCreateTime());
		}
        getSession().insert(mapperName + ".replaceSelective", t);
        return t;
    }

    @Override
    public M update(final M t) throws Exception {
		if (null == t.getUpdateTime()) {
			t.setUpdateTime(t.getCreateTime());
		}
        getSession().update(mapperName + ".updateByPrimaryKey", t);
        return t;
    }

    @Override
    public int delete(final ID id) throws Exception {
        return getSession().delete(mapperName + ".deleteByPrimaryKey", id);
    }

    @Override
    public M findEntity(ID id) throws Exception {
        return getSession().selectOne(mapperName + ".selectByPrimaryKey", id);
    }

    @Override
    public M findEntity(M m) throws Exception {
        return getSession().selectOne(mapperName + ".selectByPrimaryKey", m);
    }

    public M findEntityByPrimaryKey(Serializable id) throws Exception {
        return findEntity((ID) id);
    }

    @Override
    public List<M> findList(final M t) throws Exception {
        return getSession().selectList(mapperName + ".select", t);
    }

    @Override
    public Map<ID, M> findMap(Serializable t) throws Exception {
        return getSession().selectMap(mapperName + ".select", t, "id");
    }

    @Override
    public Map<ID, M> findMap(final M t) throws Exception {
        return getSession().selectMap(mapperName + ".select", t, "id");
    }

    @Override
    public Map<ID, M> findMap(final M t, final String keyColumn) throws Exception {
        return getSession().selectMap(mapperName + ".select", t, keyColumn);
    }

    @Override
    public DataTablesPage<M> findPage(DataTablesParameter param) throws Exception {
        if (null != param.getOrderColumns() && param.getOrderColumns().length > 0) {
            String str = "";
            for (int i = 0; i < param.getOrderColumns().length; i++) {
				if (!StringUtil.isEmpty(param.getOrderColumns()[i])) {//排序字段空白字符串或者null跳过
					str += param.getOrderColumns()[i].replaceAll("^(?i)name$", "`NAME`").replaceAll("^(?i)index$", "`INDEX`") + " ";//MySql关键字回避
                    str += param.getOrderDirs()[i];
                    if (i < param.getOrderColumns().length - 1) {
                        str += ",";
                    }
                }
            }
            param.setOrderColumn(str);
            param.setOrderDir(null);
        }
		if (StringUtil.isEmpty(param.getOrderColumn())) {//排序内容空白则设为null，便于后续统一处理
            param.setOrderColumn(null);
        }
        List<M> list = getSession().selectList(mapperName + ".selectPage", param);
        DataTablesPage<M> page = new DataTablesPage<M>(list);
        page.setRecordsTotal(getSession().selectOne(mapperName + ".selectCount", param));
        page.setDraw(param.getDraw());
        page.setRecordsFiltered(page.getRecordsTotal());
        return page;
    }

    @Override
    public void replace(List<M> list) throws Exception {
        getSession().insert(mapperName + ".replace", list);
    }

	@Override
	public void truncate() {
		getSession().delete(mapperName + ".truncate");
	}

}
