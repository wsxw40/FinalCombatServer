package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.StringUtil;




public class DeletePlayer extends BaseClientServlet {

	private static final long serialVersionUID = 9135258354077710613L;
	static Logger log = Logger.getLogger(DeletePlayer.class.getName());
	
	public DeletePlayer(){
		super();
	}	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			Integer playerId = 0;
			String cid = req.getParameter("cid");
			
			if(cid !=null){
				playerId = Integer.parseInt(cid);
			}		
			
			if(playerId > 0){
				if(deleteService.deletePlayer(userId, playerId)){
					out.write("error = nil");
				}
				else{
					out.write("error = \"Can not create character\"");				
				}
			}
			else{
				out.write("error = \"Please input cid\"");			
			}
		}catch (Exception e) {
			log.error("exception in DeletePlayer servlet",e);
			out.write("系统出现异常错误，请联系GM");
			
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}	
}
