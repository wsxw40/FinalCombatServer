package com.pearl.fcw.info.core.persistence.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.network.PacketType;
import com.pearl.fcw.info.lobby.utils.ConfigurationUtil;


public class ThreadPoolSelector {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolSelector.class);

    private ExecutorService loginThreadPool = Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_LOGIN_POOLSIZE);       // thread pool to serve login request only
    private ExecutorService serverThreadPool = Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_SERVER_POOLSIZE);      // thread pool to serve server requests
    private ExecutorService clientThreadPool = Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_CLIENT_POOLSIZE);      // thread pool to serve client requests

    public ThreadPoolSelector() {
        logger.info("ServerHandler executor login thread pool size is " + ConfigurationUtil.SOCKET_LOGIN_POOLSIZE);
        logger.info("ServerHandler executor server thread pool size is " + ConfigurationUtil.SOCKET_SERVER_POOLSIZE);
        logger.info("ServerHandler executor client thread pool size is " + ConfigurationUtil.SOCKET_CLIENT_POOLSIZE);
    }

    public ExecutorService chooseThreadPool(int packageType) {
        if (PacketType.LOGIN.getNumber() == packageType) {
            return loginThreadPool;

        } else if (PacketType.SERVER.getNumber() == packageType) {
            return serverThreadPool;

        } else {
            return clientThreadPool;
        }
    }

    public void stop() {
        loginThreadPool.shutdown();
        serverThreadPool.shutdown();
        clientThreadPool.shutdown();
    }
}
