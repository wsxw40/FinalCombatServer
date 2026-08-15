package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysCharacterDao;
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.o2o.pojo.GmUser;

/**
 * 系统角色
 */
@Service
public class WSysCharacterService extends DmServiceImpl<WSysCharacter, Integer> {
    @Resource
    private WSysCharacterDao wSysCharacterDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wSysCharacterDao;
    }

    /**
     * GM新增
     * @param wSysCharacter
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addByGm(WSysCharacter wSysCharacter, GmUser gmUser) throws Exception {
        wSysCharacter.setIsDeleted("N");
        wSysCharacter = add(wSysCharacter);
        gmThreadPool.add(wSysCharacter, gmUser);
    }

    /**
     * GM修改
     * @param wSysCharacter
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WSysCharacter wSysCharacter, GmUser gmUser) throws Exception {
        wSysCharacter.setIsDeleted("N");
        wSysCharacter = update(wSysCharacter);
        gmThreadPool.update(wSysCharacter, gmUser);
    }

    /**
     * GM逻辑删除
     * @param wSysCharacter
     * @param gmUser
     * @throws Exception
     */
    public void removeByGm(WSysCharacter wSysCharacter, GmUser gmUser) throws Exception {
        wSysCharacter = findEntity(wSysCharacter.getId());
        wSysCharacter.setIsDeleted("Y");
        wSysCharacter = update(wSysCharacter);
        gmThreadPool.delete(wSysCharacter, gmUser);
    }

    /**
     * GM物理删除
     * @param wSysCharacter
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteByGm(WSysCharacter wSysCharacter, GmUser gmUser) throws Exception {
        delete(wSysCharacter.getId());
        gmThreadPool.delete(wSysCharacter, gmUser);
    }
}
