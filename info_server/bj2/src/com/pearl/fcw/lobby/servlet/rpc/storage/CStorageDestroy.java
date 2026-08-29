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
 * 玩家删除物品
 */
@Service("fcw_c_storage_destroy")
public class CStorageDestroy extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -6124047153822936179L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CStorageDestroy fcw_c_storage_destroy;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid", "t", "piid" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_storage_destroy.rpc(strings);
        } catch (Exception e) {
            logger.error("c_storage_destroy has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int playerItemId = Integer.parseInt(args[3]);
        try {
            storageService.remove(playerId, playerItemId);
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
