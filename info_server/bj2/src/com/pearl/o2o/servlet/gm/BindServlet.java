package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.pojo.InterfaceRecord;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerVO;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class BindServlet extends BaseGMServlet {
	private static final long serialVersionUID = -5424835643098512993L;

	/**
		成功返回：ok:%1
			其中，%1表示（暂无实际意义）
			失败返回：fail:%1，
			其中，%1表示错误代码
			0表示时间戳过期
			1表示验证串错误
			2表示老账号不存在
			3 表示老账号已经包含“xldcf”字样
			4 表示老账号于PLAYER ID不匹配
			5新账号不正确
			6 未知异常

	 * @throws Exception 
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		
		String timestamp = new String(req.getParameter("t").getBytes("ISO-8859-1"));
		String regionId = new String(req.getParameter("r").getBytes("ISO-8859-1"));
		String num = new String(req.getParameter("n").getBytes("ISO-8859-1"));
		String playerID = new String(req.getParameter("p").getBytes("ISO-8859-1"));
		String xunleiId = new String(req.getParameter("u").getBytes("ISO-8859-1"));
		String sign = new String(req.getParameter("s").getBytes("ISO-8859-1"));
		try{
			
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			try{
				time = simpleDateFormat.parse(timestamp);
				if((new Date().getTime() - time.getTime()) > 60000){//超过一分钟
					out.write("fail:0");//时间戳问题
					out.close();
					return;
				}
			}catch(ParseException e){
				out.write("fail:0");//时间戳问题
				out.close();
				return;
			}
			if(num.indexOf("xldcf")!=-1){
				out.write("fail:3");//老账号已经包含“xldcf”字样
				out.close();
				return;
			}
			if(xunleiId.indexOf("xldcf")==-1){
				out.write("fail:5");//新账号不正确
				out.close();
				return;
			}
			Integer playerId=getService.getPlayerIdByUserId(num);
			if(playerId==null){
				out.write("fail:2");//老账号不存在
				out.close();
				return;
			}
			Player player = getService.getSimplePlayerById(playerId);
			if(player==null){
				out.write("fail:2");//老账号不存在
				out.close();
				return;
			}else if(player.getId()!=StringUtil.toInt(playerID)){
				out.write("fail:4");//老账号于PLAYER ID不匹配
				out.close();
				return;
			}
			String encodeStr = MD5Util.md5(timestamp + regionId + num + playerID +xunleiId+ key);
			if(!encodeStr.equals(sign)){//验证串错误
				out.write("fail:1");
				out.close();
				return;
			}
			PlayerVO p=new PlayerVO();
			p.setId(player.getId());
			p.setUserName(xunleiId);
			updateService.updateXunleiId(p);
			ServiceLocator.fileLog.info("bind xunlei ID:"+num+" tansfer to "+xunleiId );
			out.write("ok:1");
			out.close();
			
		} catch(Exception e){
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error("error happen in  SendMailServlet ip from "+ip,e);
			out.write("fail:6");
			out.close();
			return;
		}
	}
}
