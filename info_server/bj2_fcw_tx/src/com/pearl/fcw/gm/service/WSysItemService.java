package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WPaymentDao;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 系统物品
 */
@Service
public class WSysItemService extends DmServiceImpl<WSysItem, Integer> {
    @Resource
    private WSysItemDao wSysItemDao;
    @Resource
    private WPaymentDao wPaymentDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wSysItemDao;
    }

    /**
     * GM新增
     * @param sysItem
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addByGm(WSysItem wSysItem, final GmUser gmUser) throws Exception {
        if (wSysItem.getType().equals(Constants.DEFAULT_PACKAGE_TYPE)) {//大礼包
            if (StringUtil.isEmpty(wSysItem.getItems())) {
                throw new Exception(ExceptionMessage.NOT_PACKAGE_ITEM);
            }
        } else {
            wSysItem.setItems("");
        }
        wSysItem.setIsDeleted("N");
        wSysItem = add(wSysItem);
        gmThreadPool.add(wSysItem, gmUser);
    }

    /**
     * GM修改
     * @param wSysItem
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WSysItem wSysItem, final GmUser gmUser) throws Exception {
        wSysItem.setIsDeleted("N");
        wSysItem = update(wSysItem);
        gmThreadPool.update(wSysItem, gmUser);
    }

    /**
     * GM逻辑删除
     * @param wSysItem
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void removeByGm(WSysItem wSysItem, final GmUser gmUser) throws Exception {
        wSysItem = findEntity(wSysItem.getId());
        wSysItem.setIsDeleted("Y");
        wSysItem = update(wSysItem);
        gmThreadPool.delete(wSysItem, gmUser);
    }

    /**
     * GM物理删除
     * @param wSysItem
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteByGm(WSysItem wSysItem, final GmUser gmUser) throws Exception {
        //先删除WPayment的关联数据
        WPayment wPayment = new WPayment();
        wPayment.setItemId(wSysItem.getId());
        for (WPayment p : wPaymentDao.findList(wPayment)) {
            wPaymentDao.delete(p.getId());
        }
        //删除WSysItem数据
        delete(wSysItem.getId());
        gmThreadPool.delete(wSysItem, gmUser);
    }
}
