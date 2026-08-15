package com.pearl.fcw.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.core.dao.DmDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;

public class DmServiceImpl<M extends BaseEntity, ID extends Serializable> implements DmService<M, ID> {

    protected DmDao<M, ID> genDao;

    @Override
    public M add(M t) throws Exception {
        return genDao.add(t);
    }

    @Override
    public M save(M t) throws Exception {
        return genDao.save(t);
    }

    @Override
    public M update(M t) throws Exception {
        return genDao.update(t);
    }

    @Override
    public int delete(ID id) throws Exception {
        return genDao.delete(id);
    }

    @Override
    public M findEntity(ID id) throws Exception {
        return genDao.findEntity(id);
    }

    @Override
    public List<M> findList(M t) throws Exception {
        return genDao.findList(t);
    }

    @Override
    public Map<ID, M> findMap(M t, String keyColumn) throws Exception {
        return genDao.findMap(t, keyColumn);
    }

    @Override
    public DataTablesPage<M> findPage(DataTablesParameter param) throws Exception {
        return genDao.findPage(param);
    }

    @Override
    public Map<ID, M> findMap(Serializable t) throws Exception {
        return genDao.findMap(t);
    }

    @Override
    public Map<ID, M> findMap(M t) throws Exception {
        return genDao.findMap(t);
    }

}
