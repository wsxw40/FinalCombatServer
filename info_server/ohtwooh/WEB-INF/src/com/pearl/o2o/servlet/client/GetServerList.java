package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Converter;


public class GetServerList extends BaseClientServlet {
	private static final long serialVersionUID = -7772792623360674823L;
	static Logger log = Logger.getLogger(GetServerList.class.getName());	

	public GetServerList() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		
		try{		
			String result = getService.getServerList();
			out.write(result);
		}
		catch(Exception e){
			log.error("Error in GetServerList: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}		
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
