package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.pojo.InterfaceRecord;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.PasswordUtil;
import com.pearl.o2o.utils.ServiceLocator;

public class QueryAwardServlet extends BaseGMServlet {
	private static final long serialVersionUID = -169422595433967626L;

	/**
	 	成功返回：ok:%1
			其中，%1 表示成功发放的订单号。
		失败返回：fail:%1，
			其中，%1 表示错误代码。
				1 表示验证串错误
				2 表示无此订单
				3 表示奖励尚未发放
				4 表示角色不存在
				5 表示未知错误
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		try{
			
			String orderId = new String(req.getParameter("o").getBytes("ISO-8859-1"));
			String sign = new String(req.getParameter("s").getBytes("ISO-8859-1"));
			ServiceLocator.fileLog.debug(orderId);
			ServiceLocator.fileLog.debug(sign);
			InterfaceRecord record = dao.getByOrderId(orderId);
			if(null == record){//无此订单
				out.write("fail:2");
				out.close();
				return;
			}
			String encodeStr = MD5Util.md5(orderId + key);
			if(!encodeStr.equals(sign)){//验证串错误
				out.write("fail:1");
				out.close();
				return;
			}
			
			out.write("ok:" + record.getOrderId());
			out.close();
		}catch(Exception e){
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error("error happen in  QueryAwardServlet ip from "+ip,e);
			out.write("fail:5");
			out.close();
			return;
		}
	}
}
