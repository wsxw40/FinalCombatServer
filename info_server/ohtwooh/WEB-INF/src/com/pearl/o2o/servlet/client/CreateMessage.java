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
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;

public class CreateMessage extends BaseClientServlet {

	private static final long serialVersionUID = -4665043181234763917L;
	static Logger log = Logger.getLogger(CreateMessage.class.getName());
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
			//String receiverName= StringUtil.encoding(req.getParameter("receiver"));
			String receiverName= req.getParameter("receiver");
			String subject= req.getParameter("subject");
			String content= req.getParameter("content");
			subject=KeywordFilterUtil.filter(subject);
			content=KeywordFilterUtil.filter(content);
			createService.createMessage(userId,playerId, receiverName, subject, content)	;		
			result=Converter.createMessage(Constants.NUM_ZERO,null);
			out.write(result);
		}
		catch (BaseException be) {
			log.error("Exception in CreateMessage",be);
			out.write(Converter.createMessage(Constants.NUM_ONE,be.getMessage()));	
		}
		catch (Exception e) {
			log.error("Exception in CreateMessage",e);
				
		}
	}
}
