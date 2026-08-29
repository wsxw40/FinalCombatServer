package com.pearl.fcw.utils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.cache.CacheKey;
import com.pearl.fcw.core.cache.TransactionManager;

/**
 * 游戏内线程池
 */
public class GameThreadPool {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TransactionManager transactionManager;
    @Resource
    private CacheKey cacheKey;

	private ExecutorService threadPool = Executors.newFixedThreadPool(100);

    /**
	 * 将可执行缓存数据读写的线程投入线程池并启动
	 * @param runnable
	 */
    public void executeWithEntityManager(Runnable runnable) {
		threadPool.execute(() -> {
            try {
                String key = new Random().nextLong() + "";
                logger.debug("Client executeWithEntityManager start.");
                transactionManager.associate(cacheKey.getDistributeLockedKey(key));

                try {
                    runnable.run();
                } catch (Throwable e) {
                    if (null != key) {
                        transactionManager.rollback();
                    }
                    logger.debug("Client executeWithEntityManager rollback.");
                    throw e;
                }

                if (null != key) {
                    transactionManager.commit();
                }
                logger.debug("Client executeWithEntityManager end.");
            } catch (Exception e) {
                logger.error("", e);
            }
        });
    }

	public void shutdown() {
		threadPool.shutdown();
	}
}
