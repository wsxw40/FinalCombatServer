package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetMessageItem extends BaseClientServlet {
	static Logger log=Logger.getLogger(GetMessageItem.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out=res.getWriter();
		String result;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId= StringUtil.toInt(req.getParameter("cid"));
			int messageId= StringUtil.toInt(req.getParameter("mid"));
			createService.detachMessageItem(userId, playerId, messageId);
			result=Converter.createMessage(Constants.NUM_ZERO,null);
			out.write(result);
		}
		catch (BaseException be) {
			log.error("Exception in GetMessageItem",be);
			out.write(Converter.createMessage(Constants.NUM_ONE,be.getMessage()));	
		}
		catch (Exception e) {
			log.error("Exception in GetMessageItem",e);
			out.write(Converter.createMessage(Constants.NUM_ONE,e.getMessage()));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
}
