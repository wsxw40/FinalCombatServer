package com.pearl.fcw.core.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作日志<br/>
 * AOP切片
 */
@Aspect
@Component
@Service
public class GmLogService {
    private static final String EDP = "@annotation(com.pearl.fcw.core.service.GmLogService.";
    @Resource
    private GmThreadPool gmThreadPool;

    @Around(EDP + "GmLogUpdate)")
    public Object update(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        GmUser gmUser = null;
        try {
            LinkedList<BaseEntity> oldList = new LinkedList<>();
            LinkedList<BaseEntity> newList = new LinkedList<>();
            for (Object arg : args) {
                if (null != arg && arg instanceof DmModel) {
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(arg.getClass());
                    BaseEntity m = dao.findEntityByKey((DmModel) arg);
                    oldList.add(m);
                } else if (null != arg && arg instanceof GmUser) {
                    gmUser = (GmUser) arg;
                }
            }

            obj = joinPoint.proceed(args);

            for (Object arg : args) {
                if (null != arg && arg instanceof DmModel) {
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(arg.getClass());
                    BaseEntity m = dao.findEntityByKey((BaseEntity) arg);
                    newList.add(m);
                }
            }
            gmThreadPool.gmLog(oldList, newList, gmUser, joinPoint.getStaticPart().getSignature().toShortString(), "update");
        } catch (Throwable e) {
            throw e;
        }
        return obj;
    }

    /**
     * 记录GM操作日志：Update
     */
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GmLogUpdate {

    }

    @Around(EDP + "GmLogAdd)")
    public Object add(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        GmUser gmUser = null;
        try {
            LinkedList<BaseEntity> oldList = new LinkedList<>();
            LinkedList<BaseEntity> newList = new LinkedList<>();

            obj = joinPoint.proceed(args);

            for (Object arg : args) {
                if (null != arg && arg instanceof DmModel) {
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(arg.getClass());
                    BaseEntity m = dao.findEntityByKey((DmModel) arg);
                    oldList.add(null);
                    newList.add(m);
                } else if (null != arg && arg instanceof GmUser) {
                    gmUser = (GmUser) arg;
                }
            }
            gmThreadPool.gmLog(oldList, newList, gmUser, joinPoint.getStaticPart().getSignature().toShortString(), "add");
        } catch (Throwable e) {
            throw e;
        }
        return obj;
    }

    /**
     * 记录GM操作日志：Add
     */
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GmLogAdd {

    }

    @Around(EDP + "GmLogDelete)")
    public Object delete(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        GmUser gmUser = null;
        try {
            LinkedList<BaseEntity> oldList = new LinkedList<>();
            LinkedList<BaseEntity> newList = new LinkedList<>();
            for (Object arg : args) {
                if (null != arg && arg instanceof DmModel) {
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(arg.getClass());
                    BaseEntity m = dao.findEntityByKey(((BaseEntity) arg).getId());
                    oldList.add(m);
                    newList.add(null);
                } else if (null != arg && arg instanceof GmUser) {
                    gmUser = (GmUser) arg;
                }
            }

            obj = joinPoint.proceed(args);

            gmThreadPool.gmLog(oldList, newList, gmUser, joinPoint.getStaticPart().getSignature().toShortString(), "delete");
        } catch (Throwable e) {
            throw e;
        }
        return obj;
    }

    /**
     * 记录GM操作日志：Delete
     */
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GmLogDelete {

    }

    @SuppressWarnings("unchecked")
    @Around(EDP + "GmLogReplaceBatch)")
    public Object replaceBatch(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        GmUser gmUser = null;
        try {
            LinkedList<BaseEntity> oldList = new LinkedList<>();
            LinkedList<BaseEntity> newList = new LinkedList<>();
            for (Object arg : args) {
                if (null != arg && arg instanceof List) {
                    newList.addAll((List<? extends DmModel>) arg);
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(((List<? extends DmModel>) arg).get(0).getClass());
                    List<? extends BaseEntity> oldTmpList = dao.findList(null);
                    for (BaseEntity newM : newList) {
                        BaseEntity oldM = oldTmpList.stream().filter(p -> p.getId().equals(newM.getId())).findFirst().orElse(null);
                        oldList.add(oldM);
                    }
                } else if (null != arg && arg instanceof GmUser) {
                    gmUser = (GmUser) arg;
                }
            }

            obj = joinPoint.proceed(args);

            gmThreadPool.gmLog(oldList, newList, gmUser, joinPoint.getStaticPart().getSignature().toShortString(), "batch");
        } catch (Throwable e) {
            throw e;
        }
        return obj;
    }

    /**
     * 记录GM操作日志：ReplaceBatch
     */
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GmLogReplaceBatch {

    }

    @SuppressWarnings("rawtypes")
    @Around(EDP + "GmLogRollBack)")
    public Object rollBack(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object obj = null;
        GmUser gmUser = null;
        try {
            LinkedList<BaseEntity> oldList = new LinkedList<>();
            LinkedList<BaseEntity> newList = new LinkedList<>();
            for (Object arg : args) {
                if (null != arg && arg instanceof DmModel) {
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(arg.getClass());
                    BaseEntity m = dao.findEntityByKey(((BaseEntity) arg).getId());
                    oldList.add(m);
                } else if (null != arg && arg instanceof List) {
                    List list = (List) arg;
                    for (Object o : list) {
                        if (null != o && o instanceof DmModel) {
                            CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(o.getClass());
                            BaseEntity m = dao.findEntityByKey(((BaseEntity) o).getId());
                            oldList.add(m);
                        }
                    }
                } else if (null != arg && arg instanceof GmUser) {
                    gmUser = (GmUser) arg;
                }
            }

            obj = joinPoint.proceed(args);

            for (Object arg : args) {
                if (null != arg && arg instanceof DmModel) {
                    CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(arg.getClass());
                    BaseEntity m = dao.findEntityByKey(((BaseEntity) arg).getId());
                    newList.add(m);
                } else if (null != arg && arg instanceof List) {
                    List list = (List) arg;
                    for (Object o : list) {
                        if (null != o && o instanceof DmModel) {
                            CacheDao<?, ?> dao = CacheDao.getModelAndDaoMap().get(o.getClass());
                            BaseEntity m = dao.findEntityByKey(((BaseEntity) o).getId());
                            newList.add(m);
                        }
                    }
                }
            }

            gmThreadPool.gmLog(oldList, newList, gmUser, joinPoint.getStaticPart().getSignature().toShortString(), "back");
        } catch (Exception e) {
            throw e;
        }

        return obj;
    }

    /**
     * 记录GM操作日志：RollBack
     */
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface GmLogRollBack {

    }
}
