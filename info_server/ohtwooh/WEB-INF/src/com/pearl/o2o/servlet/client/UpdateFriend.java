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
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;


public class UpdateFriend extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	static Logger log = Logger.getLogger(UpdateFriend.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();		
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			Integer playerId = StringUtil.toInt(req.getParameter("cid"));
			Integer friendId = StringUtil.toInt(req.getParameter("id"));		
			String  action 	 = req.getParameter("action");			
			
			if(Constants.ACTION_FRIEND_ADD.equals(action)){
				if(playerId.equals(friendId)){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_SELF);
				}
				updateService.updateFriend(userId, playerId, friendId, Constants.BOOLEAN_NO);
			}
			else if(Constants.ACTION_FRIEND_BLACK_ADD.equals(action)){
				if(playerId.equals(friendId)){
					throw new BaseException(ExceptionMessage.CANNOT_ADD_SELF_BLACK);
				}
				updateService.updateFriend(userId, playerId, friendId, Constants.BOOLEAN_YES);
			}
			else if(Constants.ACTION_DELETE.equals(action)){
				deleteService.deleteFriend(userId, playerId, friendId);
			}
		}
		catch (BaseException e) {
			log.warn("Error in UpdateFriend: " + e);
			out.write(Converter.error(e.getMessage()));
		}
		catch(Exception e){
			log.error("Error in UpdateFriend: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}	
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);		
	}
}
