package com.pearl.o2o.servlet.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.StringUtil;

public class Configuration extends BaseServerServlet {
	private static final long serialVersionUID = 5509102483642154403L;
	static Logger log = Logger.getLogger(Configuration.class.getName());
	
	public Configuration(){
		super();
	}			
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		super.service(req, res);

		try{
			String key = req.getParameter("key");
			String value = req.getParameter("value");
			
			if (StringUtil.isEmptyString(key)) {
				throw new IllegalArgumentException("key is emptyo");
			}
			
			this.updateService.updateSysValue(key, value);
			res.getWriter().write("operation success");
		}catch(Exception e){
			res.getWriter().write("error happend" + e);
		}
	}
}
