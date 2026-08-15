package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetSysItem extends BaseClientServlet{
	private static final long serialVersionUID = 6557039424602831755L;
	static Logger log = Logger.getLogger(GetSysItem.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int type = StringUtil.toInt(req.getParameter("t"));
			int subType = StringUtil.toInt(req.getParameter("st"));
			int itemId = StringUtil.toInt(req.getParameter("id"));
			SysItem	sysItem = getService.getSysItemByItemId(userId,playerId,type, subType, itemId);
			out.write(Converter.sysItem(sysItem));
		}
		catch (Exception e) {
			log.error("Exception in GetSysItemWeapon", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
		
	}
}
