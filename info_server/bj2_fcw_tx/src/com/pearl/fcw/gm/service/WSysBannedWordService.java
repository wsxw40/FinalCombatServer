package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysBannedWordDao;
import com.pearl.fcw.gm.pojo.WSysBannedWord;
import com.pearl.o2o.pojo.GmUser;

/**
 * 禁言
 */
@Service
public class WSysBannedWordService extends DmServiceImpl<WSysBannedWord, Integer> {
    @Resource
    private WSysBannedWordDao wSysBannedWordDao;

    @PostConstruct
    public void init() {
        this.genDao = wSysBannedWordDao;
    }

    /**
     * GM新增
     * @param wSysBannedWord
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addByGm(WSysBannedWord wSysBannedWord, GmUser gmUser) throws Exception {
        wSysBannedWord.setDeleted("N");
        add(wSysBannedWord);
    }

    /**
     * GM修改
     * @param wSysBannedWord
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WSysBannedWord wSysBannedWord, GmUser gmUser) throws Exception {
        wSysBannedWord.setDeleted("N");
        update(wSysBannedWord);
    }

    /**
     * GM逻辑删除
     * @param wSysBannedWord
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void removeByGm(WSysBannedWord wSysBannedWord, GmUser gmUser) throws Exception {
        wSysBannedWord.setDeleted("Y");
        update(wSysBannedWord);
    }

    /**
     * GM物理删除
     * @param wSysBannedWord
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteByGm(WSysBannedWord wSysBannedWord, GmUser gmUser) throws Exception {
        delete(wSysBannedWord.getId());
    }
}
