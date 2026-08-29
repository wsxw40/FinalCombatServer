package com.pearl.fcw.lobby.servlet.rpc.storage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.StorageService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 玩家使用物品
 */
@Service("fcw_c_storage_item_use")
public class CStorageItemUse extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -9008315314560301000L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CStorageItemUse fcw_c_storage_item_use;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid", "type", "playeritemid", "msg", "server_id", "channel_id" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_storage_item_use.rpc(strings);
        } catch (Exception e) {
            logger.error("c_storage_item_use has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int playerItemId = Integer.parseInt(args[3]);
        String msg = null;
        try {
            msg = args[4];
        } catch (Exception e) {
        }
        int serverId = 0;
        try {
            serverId = Integer.parseInt(args[5]);
        } catch (Exception e) {
        }
        int channelId = 0;
        try {
            channelId = Integer.parseInt(args[6]);
        } catch (Exception e) {
        }
        try {
            storageService.useItem(playerId, playerItemId, serverId, channelId, msg);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
            throw e;
        }
        return Smarty4jConverter.error(null);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
