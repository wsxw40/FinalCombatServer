package com.pde.log.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -5941140648091527058L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		new LogServer(2222, LogServer.BufferSizeDefault, LogServer.DumpDelayDefault).startLogServer();
	}
}
