package com.pearl.o2o.servlet.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.ServiceLocator;
public class Reset extends HttpServlet {
	private static final long serialVersionUID = -3393897598667208451L;
	static Logger log = LoggerFactory.getLogger(Reset.class.getName());
	public static final int StrengthAppend = 0;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		try {
			String typeStr = req.getParameter("type");
			int type = StrengthAppend;
			if(typeStr != null && typeStr.length() > 0){
				type = Integer.parseInt(typeStr);
			}
			
			switch(type){
			case StrengthAppend:
				ServiceLocator.getService.initStrengthAppend();
				break;
			}
			
			res.getWriter().write("reset success");
		} catch (Exception e) {
			ServiceLocator.nosqlLogger.warn("run syncache db task happened error",e);
		}
	}
}