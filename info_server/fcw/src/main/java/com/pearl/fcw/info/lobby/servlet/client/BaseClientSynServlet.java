package com.pearl.fcw.info.lobby.servlet.client;

import javax.annotation.Resource;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.network.MessageException;
import com.pearl.fcw.info.core.network.ResponseCode;
import com.pearl.fcw.info.lobby.utils.CacheUtil;
import com.pearl.fcw.info.lobby.utils.Constants;

public class BaseClientSynServlet extends BaseClientServlet {

    private static final long serialVersionUID = 6583355216015615317L;

    @Resource
    protected InfoServerLock lock;

    @Override
    protected byte[] innerService(long playerId, byte[] params) throws Exception {
        return null;
    }

    @Override
    public byte[] execute(long playerId, byte[] params) throws Exception {

        String key = CacheUtil.getLockCacheKeyForPlayer(playerId);
        if (lock.tryLock(key, Constants.SYNCHRONIZE_TIMEOUT)) {
            try {
                return innerService(playerId, params);
            } finally {
                lock.unlock(key);
            }
        } else {
            throw new MessageException(ResponseCode.SERVER_BUSY);
        }
    }
}
