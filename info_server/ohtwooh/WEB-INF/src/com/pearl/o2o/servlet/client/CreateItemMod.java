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

public class CreateItemMod extends BaseClientServlet {
	private static final long serialVersionUID = -6515353905004343636L;
	static Logger log = Logger.getLogger(CreateItemMod.class.getName());
	
	public CreateItemMod() {
		super();
	}
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
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int weaponId = StringUtil.toInt(req.getParameter("pid"));
			String array = req.getParameter("array");
			createService.createItemMod(userId,playerId,weaponId,array);
			result=Converter.createPackEquipment(null);
			out.write(result);
		}
		catch (BaseException be) {
			log.error("Exception in CreateItemMod",be);
			out.write(Converter.createPackEquipment(be.getMessage()));	
		}
		catch (Exception e) {
			log.error("Exception in CreateItemMod",e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));	
		}
	}
}
