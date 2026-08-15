package com.pearl.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pearl.o2o.pojo.Switch;


public class MonitorServlet extends HttpServlet {
	
	private static final long serialVersionUID = -7056585142522228309L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		List<Switch> list=Switch.getSwitchList();
		for(Switch sw:list){
			res.getWriter().write(sw.getName()+"   "+sw.getIsOn());
		}
	}
	
	
}
