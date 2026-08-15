package com.pearl.fcw.gm.service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.FileService;
import com.pearl.fcw.gm.dao.WGmLogDao;
import com.pearl.fcw.gm.dao.WGmTransactionDao;
import com.pearl.fcw.gm.pojo.WGmLog;
import com.pearl.fcw.gm.pojo.WGmTransaction;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.fcw.utils.JsonUtil;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM日志
 */
@Service
public class WGmLogService extends DmServiceImpl<WGmLog, Integer> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private WGmLogDao wGmLogDao;
    @Resource
    private WGmTransactionDao wGmTransactionDao;
    @Resource
    private WGmUserService wGmUserService;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wGmLogDao;
    }

    /**
     * 查找日志事务的列表
     * @param param
     * @return
     * @throws Exception
     */
    public DataTablesPage<WGmTransaction> findTransactionPage(DataTablesParameter param) throws Exception {
        return wGmTransactionDao.findPage(param);
    }

    /**
     * 根据日志回滚
     * @param id
     * @param gmUser
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void rollBackLogByGm(Integer id, GmUser gmUser) throws Exception {
        LinkedList<BaseEntity> addList = new LinkedList<>();
        LinkedList<BaseEntity> updateList = new LinkedList<>();
        LinkedList<BaseEntity> deleteList = new LinkedList<>();
        LinkedList<CacheDao> addDaoList = new LinkedList<>();
        LinkedList<CacheDao> updateDaoList = new LinkedList<>();
        LinkedList<CacheDao> deleteDaoList = new LinkedList<>();
        List<Class<?>> dmModelClassList = new ArrayList<>();
        //找到和指定日志相关的事务列表，按照ID倒序排列
        WGmTransaction tmpWGmTransaction = new WGmTransaction();
        tmpWGmTransaction.setLogId(id);
        List<WGmTransaction> trans1List = wGmTransactionDao.findList(tmpWGmTransaction);
        for (WGmTransaction trans1 : trans1List) {
            trans1.setLogId(null);
            //获取事务回滚需要的dao
            Class<? extends DmModel> clazz = (Class<? extends DmModel>) FileService.getDmmodelClassList().stream().filter(p -> p.getSimpleName().equals(trans1.getModelName())).findFirst().get();
            CacheDao dao = CacheDao.getModelAndDaoMap().get(clazz);
            //检查特定事务是否新增或者删除，如果是，只需要对此事务进行数据回滚
            Map<String, List<Object>> history = JsonUtil.readValue(trans1.getHistory(), Map.class);
            long count0 = history.values().stream().filter(p -> null == p.get(0)).count();
            long count1 = history.values().stream().filter(p -> null == p.get(1)).count();

            BaseEntity m = null;
            //特定事务是新增，回滚时做删除操作
            if (count0 > 0 && count0 == history.size()) {
                m = dao.findEntity(trans1.getModelId());
                deleteList.add(m);
                deleteDaoList.add(dao);
                if (!dmModelClassList.contains(m.getClass())) {
                    dmModelClassList.add(m.getClass());
                }
                continue;
            }
            //获取该DmModel对象的数据库字段对应的set方法
            Class<?> clazz1 = clazz;
            Map<String, Method> setMethodMap = new HashMap();
            while (null != clazz1 && !clazz1.equals(DmModel.class)) {
                for (Field f : clazz1.getDeclaredFields()) {
                    try {
                        if (Modifier.PRIVATE != f.getModifiers()) {
                            continue;
                        }
                        PropertyDescriptor pd = null;
                        if (DmModel.PATTERN_ONLY_FIRST_ONE_LOWER.matcher(f.getName()).find()) {
                            pd = new PropertyDescriptor(f.getName(), clazz1, "get" + f.getName(), "set" + f.getName());
                        } else {
                            pd = new PropertyDescriptor(f.getName(), clazz1);
                        }
                        setMethodMap.put(f.getName(), pd.getWriteMethod());
                    } catch (Exception e) {
                        continue;
                    }
                }
                clazz1 = clazz.getSuperclass();
            }
            //特定事务是删除，回滚时做新增操作
            if (count1 > 0 && count1 == history.size()) {
                m = DmModel.createInstance(clazz);
                for (String f : history.keySet()) {
                    Object value = history.get(f).get(0);
                    if (null != value && value instanceof Double) {
                        setMethodMap.get(f).invoke(m, Float.parseFloat(value.toString()));
                    } else {
                        setMethodMap.get(f).invoke(m, value);
                    }
                }
                addList.add(m);
                addDaoList.add(dao);
                if (!dmModelClassList.contains(m.getClass())) {
                    dmModelClassList.add(m.getClass());
                }
                continue;
            }
            //特定事务是更新，需要查找ID大于等于指定事务所有同一模块同一模块ID的事务组，并且按照ID倒序排列
            List<WGmTransaction> trans2List = wGmTransactionDao.findList(trans1);
            //遍历事务组，逐步做数据回滚。但是其中出现的新增事务忽略过去不回滚
            for (WGmTransaction trans2 : trans2List) {
                history = JsonUtil.readValue(trans2.getHistory(), Map.class);
                count0 = history.values().stream().filter(p -> null == p.get(0)).count();
                count1 = history.values().stream().filter(p -> null == p.get(1)).count();
                //该事务是新增操作，忽略
                if (count0 > 0 && count0 == history.size()) {
                    continue;
                }
                //获取事务组对应的数据，没有则新增一个
                if (null == m) {
                    m = dao.findEntity(trans2.getModelId());
                    if (null == m) {
                        m = DmModel.createInstance(clazz);
                        m.setId(trans2.getModelId());
                        m = dao.add(m);
                    }
                }
                //根据历史记录回滚数据
                for (String f : history.keySet()) {
                    Object value = history.get(f).get(0);
                    if (null != value && value instanceof Double) {
                        setMethodMap.get(f).invoke(m, Float.parseFloat(value.toString()));
                    } else {
                        setMethodMap.get(f).invoke(m, value);
                    }
                }
            }
            updateList.add(m);
            updateDaoList.add(dao);
            if (!dmModelClassList.contains(m.getClass())) {
                dmModelClassList.add(m.getClass());
            }
        }
        wGmUserService.batchByGmInRollBack(updateList, addList, deleteList, gmUser, updateDaoList, addDaoList, deleteDaoList);

        //清理缓存
        for (Class<?> clazz : dmModelClassList) {
            gmThreadPool.delete((DmModel) clazz.newInstance(), gmUser);
        }
    }

    /**
     * 根据事务回滚数据
     * @param id gmTransaction的Id
     * @param gmUser
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void rollBackTransactionByGm(Integer id, GmUser gmUser) throws Exception {
        //获取需要回滚的特定事务
        WGmTransaction wGmTransaction = wGmTransactionDao.findEntity(id);
        wGmTransaction.setLogId(null);
        //获取事务回滚需要的dao
        Class<? extends DmModel> clazz = (Class<? extends DmModel>) FileService.getDmmodelClassList().stream().filter(p -> p.getSimpleName().equals(wGmTransaction.getModelName())).findFirst().get();
        CacheDao dao = CacheDao.getModelAndDaoMap().get(clazz);
        //检查特定事务是否新增或者删除，如果是，只需要对此事务进行数据回滚
        Map<String, List<Object>> history = JsonUtil.readValue(wGmTransaction.getHistory(), Map.class);
        long count0 = history.values().stream().filter(p -> null == p.get(0)).count();
        long count1 = history.values().stream().filter(p -> null == p.get(1)).count();

        BaseEntity m = null;
        //特定事务是新增，回滚时做删除操作
        if (count0 > 0 && count0 == history.size()) {
            m = dao.findEntity(wGmTransaction.getModelId());
            wGmUserService.deleteByGmInRollBack(m, gmUser, dao);
            gmThreadPool.delete(m, gmUser);
            return;
        }
        //获取该DmModel对象的数据库字段对应的set方法
        Class<?> clazz1 = clazz;
        Map<String, Method> setMethodMap = new HashMap();
        while (null != clazz1 && !clazz1.equals(DmModel.class)) {
            for (Field f : clazz1.getDeclaredFields()) {
                try {
                    if (Modifier.PRIVATE != f.getModifiers()) {
                        continue;
                    }
                    PropertyDescriptor pd = null;
                    if (DmModel.PATTERN_ONLY_FIRST_ONE_LOWER.matcher(f.getName()).find()) {
                        pd = new PropertyDescriptor(f.getName(), clazz1, "get" + f.getName(), "set" + f.getName());
                    } else {
                        pd = new PropertyDescriptor(f.getName(), clazz1);
                    }
                    setMethodMap.put(f.getName(), pd.getWriteMethod());
                } catch (Exception e) {
                    continue;
                }
            }
            clazz1 = clazz.getSuperclass();
        }

        //特定事务是删除，回滚时做新增操作
        if (count1 > 0 && count1 == history.size()) {
            m = DmModel.createInstance(clazz);
            for (String f : history.keySet()) {
                Object value = history.get(f).get(0);
                if (null != value && value instanceof Double) {
                    setMethodMap.get(f).invoke(m, Float.parseFloat(value.toString()));
                } else {
                    setMethodMap.get(f).invoke(m, value);
                }
            }
            wGmUserService.addByGmInRollBack(m, gmUser, dao);
            gmThreadPool.add(m, gmUser);
            return;
        }

        //特定事务是更新，需要查找ID大于等于指定事务所有同一模块同一模块ID的事务组，并且按照ID倒序排列
        List<WGmTransaction> transList = wGmTransactionDao.findList(wGmTransaction);
        //遍历事务组，逐步做数据回滚。但是其中出现的新增事务忽略过去不回滚
        for (WGmTransaction trans : transList) {
            history = JsonUtil.readValue(trans.getHistory(), Map.class);
            count0 = history.values().stream().filter(p -> null == p.get(0)).count();
            count1 = history.values().stream().filter(p -> null == p.get(1)).count();
            //该事务是新增操作，忽略
            if (count0 > 0 && count0 == history.size()) {
                continue;
            }
            //获取事务组对应的数据，没有则新增一个
            if (null == m) {
                m = dao.findEntity(trans.getModelId());
                if (null == m) {
                    m = DmModel.createInstance(clazz);
                    m.setId(trans.getModelId());
                    m = dao.add(m);
                }
            }
            //根据历史记录回滚数据
            for (String f : history.keySet()) {
                Object value = history.get(f).get(0);
                if (null != value && value instanceof Double) {
                    setMethodMap.get(f).invoke(m, Float.parseFloat(value.toString()));
                } else {
                    setMethodMap.get(f).invoke(m, value);
                }
            }
        }
        wGmUserService.updateByGmInRollBack(m, gmUser, dao);
        gmThreadPool.update(m, gmUser);
    }

    //    @SuppressWarnings({ "unchecked", "rawtypes" })
    //    @GmLogRollBack
    //    public void deleteByGmInRollBack(DmModel m, GmUser gmUser, CacheDao dao) throws Exception {
    //        dao.delete(m.getId());
    //    }
    //
    //    @SuppressWarnings({ "unchecked", "rawtypes" })
    //    @GmLogRollBack
    //    public void addByGmInRollBack(DmModel m, GmUser gmUser, CacheDao dao) throws Exception {
    //        dao.add(m);
    //    }
    //
    //    @SuppressWarnings({ "unchecked", "rawtypes" })
    //    @GmLogRollBack
    //    public void updateByGmInRollBack(DmModel m, GmUser gmUser, CacheDao dao) throws Exception {
    //        dao.update(m);
    //    }
}
