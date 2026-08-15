package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class DeleteItemMod extends BaseClientServlet {
	private static final long serialVersionUID = -7126227129499698512L;
	static Logger log = Logger.getLogger(DeleteItemMod.class.getName());
	
	public DeleteItemMod() {
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		
		PrintWriter out=res.getWriter();
		String result;
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId=StringUtil.toInt(req.getParameter("cid"));
			int partId=StringUtil.toInt(req.getParameter("iid"));
			int type=StringUtil.toInt(req.getParameter("t"));
			int subType=StringUtil.toInt(req.getParameter("st"));
			
//			deleteService.deleteItemMod(userId,playerId, partId,type,subType);			
			result=Converter.createPackEquipment(null);
			out.write(result);
		}
		catch (Exception e) {
			log.error("Exception in CreateItemMod",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
	}
}
