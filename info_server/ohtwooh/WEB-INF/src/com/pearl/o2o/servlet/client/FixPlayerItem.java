package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class FixPlayerItem extends BaseClientServlet {
	private static final long serialVersionUID = 348899082580431429L;
	static Logger log = Logger.getLogger(FixPlayerItem.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int type=StringUtil.toInt(req.getParameter("t"));
			int playerItemId=StringUtil.toInt(req.getParameter("pid"));
			updateService.fixPlayerItem(userId, playerId, playerItemId, type);
			out.write(Converter.commonFeedback(null));
		}
		catch (BaseException be) {
			log.error("Exception in CreateMessage",be);
			out.write(Converter.commonFeedback(be.getMessage()));	
		}
		catch(Exception e){
			log.error("Error in DeleteMessage: " + e);
			out.write(Converter.commonFeedback(e.getMessage()));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
}
