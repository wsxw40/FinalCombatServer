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

public class DeleteMessage extends BaseClientServlet {
	private static final long serialVersionUID = 7986649272863124341L;
	static Logger log = Logger.getLogger(GetMessageList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			String messages=req.getParameter("mid");
			String message[]=messages.split(",");
			for(int i=0;i<message.length;i++){
				if(message[i]!=null&&!"".equals(message[i])){
					updateService.updateMessage(playerId,StringUtil.toInt(message[i]));
				}
			}
			out.write(Converter.createMessage(Constants.NUM_ZERO, null));
		}
		catch (BaseException be) {
			log.error("Exception in CreateMessage",be);
			out.write(Converter.createMessage(Constants.NUM_ONE,be.getMessage()));	
		}
		catch(Exception e){
			log.error("Error in DeleteMessage: " + e);
			out.write(Converter.createMessage(Constants.NUM_ONE,e.getMessage()));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
	}
}
