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
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class SendMailServlet extends BaseGMServlet {
	private static final long serialVersionUID = -169422595433967626L;
	/**
		成功返回：ok:%1
			其中，%1 表示（暂无实际意义）
		失败返回：fail:%1，
			其中，%1 表示错误代码
				0 表示时间戳过期
				1 表示验证串错误
				2 表示角色不存在
				3 表示未知错误
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
		String account =URLDecoder.decode(new String(req.getParameter("u").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		String roleId  =URLDecoder.decode(new String(req.getParameter("c").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		final String title  =URLDecoder.decode(new String(req.getParameter("o").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		final String content  =URLDecoder.decode(new String(req.getParameter("f").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		String sign = new String(req.getParameter("s").getBytes("ISO-8859-1"));
		try{
			final Player player = getService.getSimplePlayerById(getService.getPlayerIdByUserId(account));
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
			String encodeStr = MD5Util.md5(timestamp + regionId + account + roleId + key);
			if(!encodeStr.equals(sign)){//验证串错误
				out.write("fail:1");
				out.close();
				return;
			}
			if(null == player){//角色不存在
				out.write("fail:2");
				out.close();
				return;
			}
			InterfaceRecord record = new InterfaceRecord();
			record.setType(1);
			record.setAccount(account);
			record.setContent(content);
			record.setRegionId(Integer.parseInt(regionId));
			record.setRoleId(roleId);
			record.setTimestamp(time);
			record.setTitle(title);
			dao.insert(record);
			
			Runnable sendMailToPlayer=new Runnable(){
				@Override
				public void run() {
					try {
						messageService.sendSystemMail(player, KeywordFilterUtil.filter(StringUtil.escapeIndex(title))
								,KeywordFilterUtil.filter(StringUtil.escapeIndex(content)));
					} catch (Exception e) {
						
					}
				};
			};
			ServiceLocator.asynTakService.execute(sendMailToPlayer);
			
			out.write("ok:1");
			out.close();
		} catch(Exception e){
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error("error happen in  SendMailServlet ip from "+ip,e);
			out.write("fail:3");
			out.close();
			return;
		}
	}
}
