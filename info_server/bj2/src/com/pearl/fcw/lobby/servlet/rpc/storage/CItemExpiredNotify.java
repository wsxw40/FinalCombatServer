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
 * 失效的玩家物品列表(时限已过，耐久不足)
 */
@Service("fcw_c_item_expired_notify")
public class CItemExpiredNotify extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = 1122824498177685562L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CItemExpiredNotify fcw_c_item_expired_notify;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_item_expired_notify.rpc(strings);
        } catch (Exception e) {
            logger.error("c_item_expired_notify has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
		return storageService.getExpiredItems(playerId);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
