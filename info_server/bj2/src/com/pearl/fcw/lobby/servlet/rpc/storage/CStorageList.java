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
 * 查看玩家仓库
 */
@Service("fcw_c_storage_list")
public class CStorageList extends BaseClientServlet implements Servletable {

    private static final long serialVersionUID = -6124047153822936179L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CStorageList fcw_c_storage_list;
    @Resource
    private StorageService storageService;

    @Override
    protected String[] paramNames() {
        return new String[] { "uid", "pid", "t", "cid", "p", "st" };
    }

    @Override
    protected String innerService(String... strings) {
        try {
            return fcw_c_storage_list.rpc(strings);
        } catch (Exception e) {
            logger.error("c_storage_list has error : " + getLockedKey(strings), e);
        }
        return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
    }

    @Override
    public String rpc(String... args) throws Exception {
        int playerId = Integer.parseInt(args[1]);
        int itemType = Integer.parseInt(args[2]);
        int sysCharacterId = Integer.parseInt(args[3]);
        int page = Integer.parseInt(args[4]);
        page = page < 1 ? 1 : page;
        int itemSubType = 0;
        try {
            itemSubType = Integer.parseInt(args[5]);
        } catch (Exception e) {
        }
        return storageService.getPlayerItemList(playerId, sysCharacterId, itemType, itemSubType, page);
    }

    @Override
    public String getLockedKey(String... args) {
        return args[1];
    }

}
