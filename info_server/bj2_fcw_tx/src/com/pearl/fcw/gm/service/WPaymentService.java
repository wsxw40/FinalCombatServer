package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WPaymentDao;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.o2o.pojo.GmUser;

/**
 * 交易
 */
@Service
public class WPaymentService extends DmServiceImpl<WPayment, Integer> {
    @Resource
    private WPaymentDao wPaymentDao;

    @PostConstruct
    public void init() {
        this.genDao = wPaymentDao;
    }

    /**
     * GM新增
     * @param wPayment
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addByGm(WPayment wPayment, GmUser gmUser) throws Exception {
        add(wPayment);
    }

    /**
     * GM修改
     * @param wPayment
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WPayment wPayment, GmUser gmUser) throws Exception {
        update(wPayment);
    }

    /**
     * GM物理删除
     * @param wPayment
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteByGm(WPayment wPayment, GmUser gmUser) throws Exception {
        delete(wPayment.getId());
    }
}
