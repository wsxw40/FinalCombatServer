package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetMessage extends BaseClientServlet {
	private static final long serialVersionUID = 585111220320711936L;
	static Logger log = Logger.getLogger(GetMessage.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		String result;
		try {
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int messageId = StringUtil.toInt(req.getParameter("mid"));
			result = Converter.message(getService.getMessage(userId, playerId, messageId));
			out.write(result);
		} catch (Exception e) {
			log.error("Error in GetMessage: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
		
	}
}
