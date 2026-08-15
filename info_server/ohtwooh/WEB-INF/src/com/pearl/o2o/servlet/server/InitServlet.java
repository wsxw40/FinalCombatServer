package com.pearl.o2o.servlet.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.pearl.o2o.socket.SocketClient;

public class InitServlet extends BaseServerServlet {
	private static final long serialVersionUID = -4241967861991330409L;
	static Logger log = Logger.getLogger(InitServlet.class.getName());
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
		
		String host = this.getInitParameter("proxy.host");
		int port = Integer.parseInt(this.getInitParameter("proxy.port"));		
		SocketClient client = SocketClient.getInstance();
		client.setHost(host);
		client.setPort(port);
		client.start();
		
		//init system item
		try{
//			getService.getSysItemListByType(1,Constants.GENDER_BOTH);
		}
		catch(Exception e){
			log.error("Error in InitServlet: " + e);
		}	
	}
}
