package com.pearl.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.utils.ServiceLocator;


public class WriteXunleiLog extends HttpServlet{
	private static final long serialVersionUID = 6293251331743925814L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		try {
			ServiceLocator.nosqlService.xunleiLogWrite();
		} catch (Exception e) {
			ServiceLocator.exception.error(e);
		}
	}

}
