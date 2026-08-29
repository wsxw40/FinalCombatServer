package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;

public class QueryXunleBalanceServlet extends BaseGMServlet {
	private static final long serialVersionUID = -1318831526458379815L;
	static Logger logger = LoggerFactory.getLogger(QueryXunleBalanceServlet.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		PrintWriter out = res.getWriter();
		String userId=req.getParameter("userId");
		String timestamp=req.getParameter("time");
		String sign = req.getParameter("sign");
		try{
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			try{
				time = simpleDateFormat.parse(timestamp);
				Date now = new Date();
				if((now.getTime() - time.getTime()) > 60000){//超过一分钟
					out.write("fail:0");//时间戳过期
					out.close();
					return;
				}
			}catch(ParseException e){
				logger.error("ParseException happened in QueryXunleBalanceServlet",e);
				out.write("fail:4");
				out.close();
				return;
			}
			String encodeStr = MD5Util.md5(timestamp + userId  + key);
			if(!encodeStr.equals(sign)){//验证串错误
				out.write("fail:1");
				out.close();
				return;
			}
			Integer playerId = getService.getPlayerIdByUserId(userId);
			if(playerId==null){
				out.write("fail:2");//用户不存在
				out.close();
				return;
			}
//			Player player=getService.getSimplePlayerById(playerId);
			PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
			out.write("ok:"+playerInfo.getXunleiPoint());
			out.close();
			return;
			
		}catch (Exception e) {
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error("error happen in  QueryXunleBalanceServlet ip from "+ip,e);
			out.write("fail:3");
			out.close();
			return;
		}
	}
}
