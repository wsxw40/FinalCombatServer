package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysStrengthenAppendDao;
import com.pearl.fcw.gm.pojo.WSysStrengthenAppend;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.o2o.pojo.GmUser;

/**
 * 系统强化合成
 */
@Service
public class WSysStrengthenAppendService extends DmServiceImpl<WSysStrengthenAppend, Integer> {
    @Resource
    private WSysStrengthenAppendDao wSysStrengthenAppendDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wSysStrengthenAppendDao;
    }

    /**
	 * GM新增
	 * @param wSysStrengthenAppend
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogAdd
    public void addByGm(WSysStrengthenAppend wSysStrengthenAppend, GmUser gmUser) throws Exception {
        wSysStrengthenAppend = add(wSysStrengthenAppend);
        gmThreadPool.add(wSysStrengthenAppend, gmUser);
    }

    /**
	 * GM修改
	 * @param wSysStrengthenAppend
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogUpdate
    public void updateByGm(WSysStrengthenAppend wSysStrengthenAppend, GmUser gmUser) throws Exception {
        wSysStrengthenAppend.setIsRemoved(false);
        wSysStrengthenAppend = update(wSysStrengthenAppend);
        gmThreadPool.update(wSysStrengthenAppend, gmUser);
    }

    /**
	 * GM物理删除
	 * @param wSysStrengthenAppend
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogDelete
    public void deleteByGm(WSysStrengthenAppend wSysStrengthenAppend, GmUser gmUser) throws Exception {
        delete(wSysStrengthenAppend.getId());
    }
}
