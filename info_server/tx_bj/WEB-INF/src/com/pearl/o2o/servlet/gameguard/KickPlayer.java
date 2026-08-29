package com.pearl.o2o.servlet.gameguard;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.servlet.gm.BaseGMServlet;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 反外挂接口接入，踢人转发
 * 
 * @author OuYangGuang
 * @version
 * 
 * 
		成功返回：ok: playerId
		失败返回：fail:%1，
		其中，%1表示错误代码。
		0表示时间戳过期（一分钟过期）
		1表示验证串错误
		2表示用户不存在
		3表示未知错误
 */
public class KickPlayer extends BaseGMServlet {
	private static final long serialVersionUID = -6653797326057232889L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
		try {
			String timestamp = request.getParameter("timestamp");
			// 角色ID
			String userId =  request.getParameter("roleId");
			//接口踢人类型
			String type = request.getParameter("type");
			//sign 加密序列
			String sn = request.getParameter("sn");
			
			String snLocal = MD5Util.md5(userId + type + timestamp + key);
			 
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
			
			if(!snLocal.equals(sn)){
				out.write("fail:1");	//加密验证错误
				return;
			}
			if(type==null || type.length()<=0){
				out.write("fail:3");	//踢人类型为空
				return;
			}else{
				try {
					Integer.parseInt(type);
				} catch (NumberFormatException e) {
					out.write("fail:3");	//踢人类型错误
					return;
				}
			}
			
			Player p = getService.getPlayerDao().getPlayerById(Integer.parseInt(userId));
			
			if(p==null)
			{
				out.write("fail:2"); //用户不存在
				return;
			}
		
			if(!userId.isEmpty()){
				switch(Integer.parseInt(type)){
					case 1:
						soClient.kickPlayer(Integer.parseInt(userId),Constants.KICK_PLAYER_TYPE.GAMEGUARD_KICK.getValue());
					break;
					default:
						break;
				}
				
			}
			
			out.print("ok:"+userId);
		} catch (Exception e) {
			ServiceLocator.fileLog.error(e.getMessage());
			out.print("fail:4");
		}
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
