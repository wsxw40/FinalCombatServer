package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Message;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetMessageList extends BaseClientServlet {

	private static final long serialVersionUID = 585111220320711936L;
	static Logger log = Logger.getLogger(GetMessageList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		String result;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			
			String strKey=CacheUtil.sPlayerMessage(playerId);
			List<Message> messageList=new ArrayList<Message>();
			result= mcc.get(strKey);
			if(result==null){
				messageList=getService.getMessageList(userId,playerId);
//					packList=getCostumePackList(playerId, packId, 1);//for test
				result=Converter.messageList(messageList);
				mcc.set(strKey, Constants.CACHE_ITEM_TIMEOUT, result);
			}
			
			out.write(result);
		}catch(Exception e){
			log.error("Error in GetMessageList: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
		
	}
}
