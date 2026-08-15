package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class CreateMessageItem extends BaseClientServlet {


	private static final long serialVersionUID = -1077440198506335030L;
	static Logger log=Logger.getLogger(CreateMessageItem.class.getName());
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out=res.getWriter();
		String result;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId= StringUtil.toInt(req.getParameter("cid"));
			int receiverId= StringUtil.toInt(req.getParameter("rid"));
			int itemId= StringUtil.toInt(req.getParameter("iid"));
			int costType=StringUtil.toInt(req.getParameter("costid"));
			Map<String,String> resultMap = createService.createMessageWithItem(userId, playerId, receiverId, itemId,costType);		
			result=Converter.createMessage(Constants.NUM_ZERO, null, StringUtil.toInt(resultMap.get("gp")), StringUtil.toInt(resultMap.get("cr")));
			out.write(result);
		}
		catch (BaseException be) {
			log.error("Exception in CreateMessageMode",be);
			out.write(Converter.createMessage(Constants.NUM_ONE,be.getMessage(),0,0));	
		}
		catch (Exception e) {
			log.error("Exception in CreateMessageMode",e);
			out.write(Converter.createMessage(Constants.NUM_ONE,e.getMessage(),0,0));		
		}
	}
}
