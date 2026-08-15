package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.OutputStream;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.User;
import com.pearl.o2o.socket.SocketClient;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.BinaryUtil;



public class Test extends BaseServerServlet {
	private static final long serialVersionUID = 5509102483642154403L;
	static Logger log = Logger.getLogger(Login.class.getName());
	
	public Test(){
//		super();
	}			
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {			
		try{
			SocketClient client = SocketClient.getInstance();
			String action = req.getParameter("action");
			String userName = req.getParameter("name");
			String msg = req.getParameter("msg");
			
			if(action.equals("chat")){
				client.message(userName, msg);
			}
			else if(action.equals("server")){
				client.serverBroadCast(msg);
			}
		}
		catch(Exception e){
			log.error("Error in Login: ", e);	
		}
	}	
}
