package com.pearl.fcw.info.gm.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartupListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);

    public static final String ASSETS_VERSION = "assetsVersion";

    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.debug("Initializing context...");

        ServletContext context = event.getServletContext();

        String appVersion = null;
        try {
            InputStream is = context.getResourceAsStream("/META-INF/MANIFEST.MF");
            if (is == null) {
                logger.debug("META-INF/MANIFEST.MF not found.");
            } else {
                Manifest mf = new Manifest();
                mf.read(is);
                Attributes atts = mf.getMainAttributes();
                appVersion = atts.getValue("Implementation-Version");
            }
        } catch (IOException e) {
            logger.error("I/O Exception reading manifest: " + e.getMessage());
        }

        if (appVersion == null || appVersion.contains("SNAPSHOT")) {
            appVersion = "" + new Random().nextInt(100000);
        }

        logger.info("Application version set to: " + appVersion);
        context.setAttribute(ASSETS_VERSION, appVersion);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
