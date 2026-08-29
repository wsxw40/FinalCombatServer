package com.pearl.fcw.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;

public interface DmService<M extends BaseEntity, ID extends Serializable> {

    /**
     * 新增。对应xml中的statement:insertSelective
     * @param t
     * @return
     */
    public M add(final M t) throws Exception;

    /**
     * 新增或修改。对应xml中的statement:replaceSelective
     * @param t
     * @return
     */
    public M save(final M t) throws Exception;

    /**
     * 修改。对应xml中的statement:update
     * @param t
     * @return
     */
    public M update(final M t) throws Exception;

    /**
     * 根据主键删除。对应xml中的statement:delete
     * @param id
     * @return
     */
    public int delete(final ID id) throws Exception;

    /**
     * 根据主键查找。对应xml中的statement:selectByPrimaryKey
     * @param id
     * @return
     */
    public M findEntity(final ID id) throws Exception;

    /**
     * 查找一组数据。对应xml中的statement:select
     * @param t
     * @return
     */
    public List<M> findList(final M t) throws Exception;

    /**
     * 查找一组数据，主键作为Key值。对应xml中的statement:select
     * @param t
     * @return
     */
    public Map<ID, M> findMap(final Serializable t) throws Exception;

    /**
     * 查找一组数据，主键作为Key值。对应xml中的statement:select
     * @param t
     * @return
     */
    public Map<ID, M> findMap(final M t) throws Exception;

    /**
     * 查找一组数据，列名为keyColumn的列作为Key值。对应xml中的statement:select
     * @param t
     * @param keyColumn 作为返回结果的Key值的列名
     * @return
     */
    public Map<ID, M> findMap(final M t, final String keyColumn) throws Exception;

    /**
     * 使用Datatables插件的分页查询
     * @param param
     * @return
     * @throws Exception
     */
    public DataTablesPage<M> findPage(DataTablesParameter param) throws Exception;
}
