/**
 * 
 */
package com.pde.log.server.benchmark;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.net.SimpleSocketServer;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * @author lifengyang
 *
 */
public class SimpleSocketServerStart {

	/**
	 * @param args
	 * @throws JoranException 
	 */
	public static void main(String[] args) throws Exception {
		int port = 2223;
		String configFile = "SimpleSocketServer.xml";
	    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    
	    JoranConfigurator configurator = new JoranConfigurator();
	    lc.reset();
	    configurator.setContext(lc);
	    configurator.doConfigure(SimpleSocketServerStart.class.getClassLoader().getResourceAsStream(configFile));
	    
	    SimpleSocketServer sss = new SimpleSocketServer(lc, port);
	    sss.start();
	}

}
