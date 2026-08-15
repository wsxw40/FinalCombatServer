package com.pde.log.server.benchmark;

import com.pde.log.server.LogServer;

import ch.qos.logback.classic.net.SimpleSocketServer;

/**
 * Hello world!
 * 
 */
public class AppStart {
	public static void main(String[] argv) {

		int port = -1;
		if (argv.length == 2) {
			port = parsePortNumber(argv[0]);
		} else {
			usage("Wrong number of arguments.");
		}

		LogServer logServer = new LogServer(port);
		if(!logServer.startLogServer()){
			usage("LogServer start fail.");
		}
	}

	static void usage(String msg) {
		System.err.println(msg);
		System.err.println("Usage: java " + SimpleSocketServer.class.getName() + " port configFile");
		System.exit(1);
	}

	static int parsePortNumber(String portStr) {
		try {
			return Integer.parseInt(portStr);
		} catch (java.lang.NumberFormatException e) {
			e.printStackTrace();
			usage("Could not interpret port number [" + portStr + "].");
			// we won't get here
			return -1;
		}
	}
}
