package com.pearl.fcw.info.core.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMessage {
    private static Logger logger = LoggerFactory.getLogger("message");

    public static void log(String headerString) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(headerString);
    }
}
