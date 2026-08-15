package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WChannelDao;
import com.pearl.fcw.gm.dao.WServerDao;
import com.pearl.fcw.gm.pojo.WChannel;
import com.pearl.fcw.gm.pojo.WServer;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.o2o.pojo.GmUser;

/**
 * 服务器
 */
@Service
public class WServerService extends DmServiceImpl<WServer, Integer> {
    @Resource
    private WServerDao wServerDao;
    @Resource
    private WChannelDao wChannelDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wServerDao;
    }

    /**
     * GM新增
     * @param wServer
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addByGm(WServer wServer, GmUser gmUser) throws Exception {
        add(wServer);
        gmThreadPool.add(wServer, gmUser);
    }

    /**
     * GM修改
     * @param wServer
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WServer wServer, GmUser gmUser) throws Exception {
        update(wServer);
        gmThreadPool.update(wServer, gmUser);
    }

    /**
     * GM物理删除
     * @param wServer
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteByGm(WServer wServer, GmUser gmUser) throws Exception {
        //先删除Channel关联数据
        WChannel wChannel = new WChannel();
        wChannel.setServerId(wServer.getId());
        for (WChannel p : wChannelDao.findList(wChannel)) {
            wChannelDao.delete(p.getId());
        }
        //删除服务器数据
        delete(wServer.getId());
        gmThreadPool.delete(wServer, gmUser);
    }

    /**
     * 分页查找频道数据
     * @param param
     * @return
     * @throws Exception
     */
    public DataTablesPage<WChannel> findChannelPage(DataTablesParameter param) throws Exception {
        return wChannelDao.findPage(param);
    }

    /**
     * GM新增频道
     * @param wChannel
     * @param gmUser
     * @throws Exception
     */
    @GmLogAdd
    public void addChannelByGm(WChannel wChannel, GmUser gmUser) throws Exception {
        wChannelDao.add(wChannel);
    }

    /**
     * GM修改频道
     * @param wChannel
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateChannelByGm(WChannel wChannel, GmUser gmUser) throws Exception {
        wChannelDao.update(wChannel);
    }

    /**
     * GM物理删除
     * @param wChannel
     * @param gmUser
     * @throws Exception
     */
    @GmLogDelete
    public void deleteChannelByGm(WChannel wChannel, GmUser gmUser) throws Exception {
        wChannelDao.delete(wChannel.getId());
    }

}
