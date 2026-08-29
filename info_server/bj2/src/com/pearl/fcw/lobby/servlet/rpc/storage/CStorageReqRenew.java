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
 * 玩家道具续费
 */
@Service("fcw_c_storage_req_renew")
public class CStorageReqRenew extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -6124047153822936179L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CStorageReqRenew fcw_c_storage_req_renew;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid", "piid", "t", "costid" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_storage_req_renew.rpc(strings);
        } catch (BaseException e) {
            return Smarty4jConverter.error(e.getMessage());
        } catch (Exception e) {
            logger.error("c_storage_req_renew has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int playerItemId = Integer.parseInt(args[2]);
        int paymentId = Integer.parseInt(args[4]);
        storageService.renew(playerId, playerItemId, paymentId);
        return Smarty4jConverter.error(null);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
