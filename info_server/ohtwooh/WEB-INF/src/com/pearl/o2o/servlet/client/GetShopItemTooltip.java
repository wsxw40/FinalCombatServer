package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.StringUtil;

public class GetShopItemTooltip extends BaseClientServlet {

	private static final long serialVersionUID = -7822352787246516585L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		String result;
		try{
			int type = StringUtil.toInt(req.getParameter("type"));
			int subType = StringUtil.toInt(req.getParameter("subtype"));
			int itemId = StringUtil.toInt(req.getParameter("siid"));
			result=mcc.get(CacheUtil.sSysItemTooltip(itemId));
			if(result==null){
//				result=getService.getSysItemToolTip(type,subType,itemId);
//				mcc.set(CacheUtil.sSysItemTooltip(itemId), Constants.CACHE_ITEM_TIMEOUT, result);
			}
			out.write(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		super.doGet(req, res);
	}
}
