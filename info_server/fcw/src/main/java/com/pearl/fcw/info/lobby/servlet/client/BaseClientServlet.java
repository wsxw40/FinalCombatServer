package com.pearl.fcw.info.lobby.servlet.client;

import java.io.Serializable;

import com.pearl.fcw.info.core.network.Executable;
import com.pearl.fcw.info.core.network.ProtostuffUtil;

import io.protostuff.Message;

public class BaseClientServlet implements Serializable, Executable {

    private static final long serialVersionUID = 2091617130107076069L;

    protected byte[] innerService(long playerId, byte[] params) throws Exception {
        return null;
    }

    protected <T extends Message<T>> T getProtoParam(byte[] data, T message) throws IllegalAccessException, InstantiationException {
        return ProtostuffUtil.mergeFrom(data, message);
    }

    protected <T extends Message<T>> byte[] outProtoByByte(T message) {
        return ProtostuffUtil.toByteArray(message);
    }

    @Override
    public byte[] execute(long playerId, byte[] params) throws Exception {
        return innerService(playerId, params);
    }

}
