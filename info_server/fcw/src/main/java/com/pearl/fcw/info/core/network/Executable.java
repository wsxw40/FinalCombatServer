package com.pearl.fcw.info.core.network;

@FunctionalInterface
public interface Executable {

    byte[] execute(long playerId, byte[] params) throws Exception;

}
