package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class SendGiftToPlayer extends BaseGMServlet {
	
	static final long serialVersionUID = 0L;
	static Logger logger = LoggerFactory.getLogger(SendGiftToPlayer.class.getName());
	/**
	 * 成功返回：ok:%1 其中，%1 表示（暂无实际意义） 失败返回：fail:%1， 其中，%1 表示错误代码 :
	 * 	0 表示时间戳过期 1 表示验证串错误 2 表示角色不存在 3 表示未知错误 4 表示hand错误（指向操作）
	 * 
	 * @throws Exception
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		try {
			String timestamp = new String(req.getParameter("time").getBytes("ISO-8859-1"));
			String account = URLDecoder.decode(new String(req.getParameter("account").getBytes("ISO-8859-1"), "utf-8"), "utf-8");
			String hand = URLDecoder.decode(new String(req.getParameter("hand").getBytes("ISO-8859-1"), "utf-8"), "utf-8");
			String sign = new String(req.getParameter("sign").getBytes("ISO-8859-1"));
			String value = URLDecoder.decode(new String(req.getParameter("value").getBytes("ISO-8859-1"), "utf-8"), "utf-8");
			//String sId = URLDecoder.decode(new String(req.getParameter("sId").getBytes("ISO-8859-1"), "utf-8"), "utf-8"); //或赠送物品ID，hand指向该物品作用时不能为空，其余指向可为空
			
			final Player player = ServiceLocator.getService.getSimplePlayerById(ServiceLocator.getService.getPlayerIdByUserId(account));
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			
			try {
				time = simpleDateFormat.parse(timestamp);
				if ((new Date().getTime() - time.getTime()) > 60000) {// 超过一分钟
					out.write("fail:0");// 时间戳问题
					out.close();
					return;
				}
			} catch (ParseException e) {
				out.write("fail:0");// 时间戳问题
				out.close();
				return;
			}
			String encodeStr = MD5Util.md5(timestamp + account + value + key);
			if (!encodeStr.equals(sign)) {// 验证串错误
				logger.info(timestamp + account + value);
				out.write("fail:1");
				out.close();
				return;
			}
			if (null == player) {// 角色不存在
				out.write("fail:2");
				out.close();
				return;
			}
			
			int handi = hand!=null&&hand.length()>0?Integer.parseInt(hand):0;
			boolean result = false;
			switch(handi){			//指向操作
				case 1:				//赠送经验
					int aExp = value!=null&&value.length()>0?Integer.parseInt(value):0;
					ServiceLocator.createService.characterOnlineAddExp(player,aExp);
					ServiceLocator.soClient.refreashPlayerExpAndLevel(player);
				result = !result;
				break;
				default:break;
			}
			
			if(result)
			{
				String ip = "";
				if (req.getHeader("x-forwarded-for") == null) {
					ip = req.getRemoteAddr();
				}
				logger.info(DateUtil.getDf().format(new Date())+" com.pearl.o2o.servlet.gm.SendGiftToPlayer " +
						" IP: "+ ip +
								" playerId:"+player.getId() +
										" playerName:"+player.getName() +
												" hand: "+ hand +
														" value:"+value);
				final String content = "<C725^1^"+value+">";//CommonUtil.messageFormatI18N(CommonMsg.EXP_GIFT, title, value);

				Runnable sendMailToPlayer = new Runnable() {
					@Override
					public void run() {
						try {
							ServiceLocator.createService.getMessageService().sendSystemMail(player, "<C1^0>",content);
						} catch (Exception e) {

						}
					};
				};
				
				ServiceLocator.asynTakService.execute(sendMailToPlayer);

				out.write("ok:1");
				out.close();
				return;
			}
			
			out.write("fail:4");
			out.close();
		} catch (Exception e) {
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error(
					"error happen in  SendMailServlet ip from " + ip, e);
			out.write("fail:3");
			out.close();
			return;
		}
	}
}
