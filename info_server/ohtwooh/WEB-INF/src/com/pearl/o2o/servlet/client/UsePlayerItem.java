package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class UsePlayerItem extends BaseClientServlet {

	static Logger log = Logger.getLogger(UsePlayerItem.class.getName());
	private static final long serialVersionUID = -6836692174398117141L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int type=StringUtil.toInt(req.getParameter("type"));
			int playerItemId=StringUtil.toInt(req.getParameter("pid"));
			String message = req.getParameter("msg");
			int serverId=StringUtil.toInt(req.getParameter("server_id"));
			int channelId=StringUtil.toInt(req.getParameter("channel_id"));
			
			if(type==Constants.DEFAULT_ITEM_TYPE){
				createService.useItemById(userId, playerId, type, playerItemId,message,channelId,serverId);
			}
			out.write(Converter.commonFeedback(null));
		}
		catch (NullPointerException e) {
			log.error("exception in UsePlayerItem servlet",e);
			out.write(Converter.commonFeedback("没有相关道具"));
		}
		catch (BaseException e) {
			log.error("exception in UsePlayerItem servlet",e);
			out.write(Converter.commonFeedback(e.getMessage()));
		}
		catch (Exception e) {
			log.error("exception in UsePlayerItem servlet",e);
			out.write(Converter.commonFeedback("系统出现异常错误，请联系GM"));
			
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
}
