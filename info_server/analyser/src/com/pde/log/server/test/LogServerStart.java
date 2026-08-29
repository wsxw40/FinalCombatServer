/**
 * 
 */
package com.pde.log.server.test;

import com.pde.log.server.LogServer;


/**
 * @author lifengyang
 * 
 */
public class LogServerStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new LogServer(2222, LogServer.BufferSizeDefault, LogServer.DumpDelayDefault).startLogServer();
	}

}
