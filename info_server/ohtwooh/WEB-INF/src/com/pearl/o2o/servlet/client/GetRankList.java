package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;

public class GetRankList extends BaseClientServlet {
	private static final long serialVersionUID = -1034688895474825054L;
	static Logger log = Logger.getLogger(GetRankList.class.getName());

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doPost(req, res);
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try {
			String strkey=CacheUtil.sRankList();
			String result=mcc.get(strkey);
			if(result==null){
				result = getService.getRankList();
				mcc.set(strkey, Constants.CACHE_ITEM_TIMEOUT, result);
			}
			out.write(result);
		} catch (Exception e) {
			log.error("Error in GetRankList: " + e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
}
