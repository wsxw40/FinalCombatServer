package com.pearl.fcw.lobby.servlet.rpc.storage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.StorageService;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 装备道具
 */
@Service("fcw_c_storage_req_equipt")
public class CStorageReqEquipt extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -6124047153822936179L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CStorageReqEquipt fcw_c_storage_req_equipt;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid", "cid", "playeritemid", "t" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_storage_req_equipt.rpc(strings);
        } catch (Exception e) {
            logger.error("c_storage_req_equipt has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int sysCharacterId = Integer.parseInt(args[2]);
        int playerItemId = Integer.parseInt(args[3]);
        storageService.equip(playerId, sysCharacterId, playerItemId);
        return Smarty4jConverter.error(null);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
