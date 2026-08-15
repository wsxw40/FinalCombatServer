package com.pearl.fcw.core.service;

import com.pearl.o2o.utils.BinaryReader;

public interface Servletable {
    default String rpc(String... args) throws Exception {
        return "";
    }

    default byte[] server(BinaryReader reader) throws Exception {
        return new byte[0];
    }

    /**
     * 获取分布式锁依据的Key(RPC模式)
     * @param args
     * @return
     */
    default String getLockedKey(String... args) {
        return null;
    }

    /**
     * 获取分布式锁依据的Key(Server模式)
     * @param reader
     * @return
     */
    default String getLockedKey(BinaryReader reader) {
        return null;
    }
}
