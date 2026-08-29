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

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;

public class QueryPlayerVipStatus extends BaseGMServlet {
	private static final long serialVersionUID = -169422595433967626L;

	/**
	 	成功返回：ok:%1
			其中，%1 该用户VIP状态，0非vip其余按该用户vip等级返回。
		失败返回：fail:%1，
			其中，%1 表示错误代码。
				1 表示参数userId错误
				2 表示无用户
				3 表示验证错误
				4 表示时间戳过期
				5 表示未知错误
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		try{
			
			String userId=new String(req.getParameter("userId").getBytes("ISO-8859-1"));
			String timestamp=new String(req.getParameter("time").getBytes("ISO-8859-1"));
			String sign=new String(req.getParameter("sign").getBytes("ISO-8859-1"));
			
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			time = simpleDateFormat.parse(timestamp);
			Date now = new Date();
			if((now.getTime() - time.getTime()) > 60000){//超过一分钟
				out.write("fail:4");//时间戳过期
				out.close();
				return;
			}
			ServiceLocator.fileLog.debug(userId);
			Player p=null;
			Integer id = 0;
			try {
				id = getService.getPlayerIdByUserId(userId);
				p = getService.getPlayerById(id);
			} catch (Exception e) { //参数错误
				out.write("fail:1");
				out.close();
				return;
			}
			
			if(p==null){//无此用户
				out.write("fail:2");
				out.close();
				return;
			}
			
			String encodeStr = MD5Util.md5(userId + timestamp + key);
			if(!encodeStr.equals(sign)){//验证串错误
				out.write("fail:3");
				out.close();
				return;
			}
			
			out.write("ok:"+p.getIsVip());
			out.close();
		}catch(Exception e){
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error("error happen in  QueryPlayerVipStatus ip from "+ip,e);
			out.write("fail:5");
			out.close();
			return;
		}
	}
}
