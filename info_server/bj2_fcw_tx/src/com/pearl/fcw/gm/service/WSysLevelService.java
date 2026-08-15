package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysLevelDao;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.o2o.pojo.GmUser;

/**
 * 系统地图
 */
@Service
public class WSysLevelService extends DmServiceImpl<WSysLevel, Integer> {
    @Resource
    private WSysLevelDao wSysLevelDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wSysLevelDao;
    }

    /**
     * GM新增
     * @param wSysLevel
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addByGm(WSysLevel wSysLevel, GmUser gmUser) throws Exception {
        wSysLevel = add(wSysLevel);
        gmThreadPool.add(wSysLevel, gmUser);
    }

    /**
     * GM修改
     * @param wSysLevel
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WSysLevel wSysLevel, GmUser gmUser) throws Exception {
        wSysLevel = update(wSysLevel);
        gmThreadPool.update(wSysLevel, gmUser);
    }

    /**
     * GM物理删除
     * @param wSysLevel
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteByGm(WSysLevel wSysLevel, GmUser gmUser) throws Exception {
        delete(wSysLevel.getId());
        gmThreadPool.delete(wSysLevel, gmUser);
    }
}
