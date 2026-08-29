package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.exception.BaseException;



public class CreatePlayer extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = Logger.getLogger(CreatePlayer.class.getName());

	public CreatePlayer(){
		super();
	}	
	 
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();		
		Player player = null;

		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			String name = req.getParameter("name");
			int id = StringUtil.toInt(req.getParameter("id"));
			int side = StringUtil.toInt(req.getParameter("side"));
			player = createService.createPlayer(userId, name.trim(), id,side);
			if(player!=null){
				out.write(Converter.createPlayer(player.getId(),null));			
			}
		}
		catch(BaseException be){
			out.write(Converter.createPlayer(0,be.getMessage()));
			return;
		}		
		catch(Exception e){
			log.error("Error in CreatePlayer: ", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
			return;
		}
	}
}
