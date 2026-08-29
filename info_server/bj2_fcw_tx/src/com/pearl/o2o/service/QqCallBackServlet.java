package com.pearl.o2o.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.DateUtil;
import com.qq.constant.ConstantsQQ;
import com.qq.open.MyOpenApiV3;
import com.qq.open.MySnsSigCheck;
import com.qq.open.OpenApiV3;
import com.qq.open.SnsSigCheck;
import com.qq.pojo.Quser;

/**
 * Step3	腾讯回调应用的发货URL。即上图中的第7步
 * Step4	应用调用发货通知接口。即上图中的第10步
 * @param
 * @author OuYangGuang
 * */
public class QqCallBackServlet extends BaseServerServlet {
	static final long serialVersionUID = 0L;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			String amt 					= myGetParameter("amt", request);
			String appid 				= myGetParameter("appid", request);
			String billno				= myGetParameter("billno", request);
			String openid 				= myGetParameter("openid", request);
			String payamt_coins 		= myGetParameter("payamt_coins", request);
			String payitem 				= myGetParameter("payitem", request);
			String providetype 			= myGetParameter("providetype", request);
			String pubacct_payamt_coins = myGetParameter("pubacct_payamt_coins", request);
			String token 				= myGetParameter("token", request);
			String ts 					= myGetParameter("ts", request);
			String version 				= myGetParameter("version", request);
			String zoneid 				= myGetParameter("zoneid", request);
			String sig 					= myGetParameter("sig", request);
			
			Map<String, String[]> map = request.getParameterMap();
			HashMap<String, String> params=new HashMap<String, String>();
            for (Map.Entry<String,String[]> entry : map.entrySet()) {
            	String key = entry.getKey();
            	String value = request.getParameter(key);
            	value = value == null ? "" : value;
            	params.put(key, value);
            }
            
			String url_path="/bj/cindex/qqCallBackServlet";
			boolean verifySig = SnsSigCheck.verifySig("GET", url_path, params, ConstantsQQ.APPKEY+"&", sig);
			Quser quser = nosqlService.getOidToQuser(openid);
			Integer pid = quser.getId();
			if (verifySig) {//对上回调成功
				//===========发送start===========
					String[] split = payitem.split("\\*");
					int wXlpt=0;
					if (split.length==3) 
						wXlpt = Integer.parseInt(split[2]);
					PlayerInfo pInfo = getService.getPlayerInfoById(pid);	
					if(null != pInfo )					{
						Integer cXlpt = pInfo.getXunleiPoint();
						//update
						pInfo.setXunleiPoint(cXlpt+wXlpt);
						Player player = getService.getPlayerById(pid);
						updateService.getPlayerInfoDao().update(pInfo);
						mcc.delete(CacheUtil.oPlayerInfo(pid));
						mcc.delete(CacheUtil.oPlayer(pid));
						mcc.delete(CacheUtil.sPlayer(pid));
						soClient.messageUpdatePlayerMoney(player);
					}
					if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
						nosqlService.addXunleiLog("21.1"
							+ Constants.XUNLEI_LOG_DELIMITER + pid
							+ Constants.XUNLEI_LOG_DELIMITER + quser.getOpenid()
							+ Constants.XUNLEI_LOG_DELIMITER + token
							+ Constants.XUNLEI_LOG_DELIMITER + ts
							+ Constants.XUNLEI_LOG_DELIMITER + payitem
							+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
							);
						}
				//===========发送end=============
				HashMap<String, String> params1=new HashMap<String, String>();
				params1.put("amt",amt);
				params1.put("appid",appid);
				params1.put("billno",billno);
				params1.put("openid",openid);
				params1.put("openkey",quser.getOpenkey());
				params1.put("payamt_coins",payamt_coins);
				params1.put("payitem",payitem);
				params1.put("pf",ConstantsQQ.PF);
				params1.put("provide_errno","0");
				params1.put("providetype",providetype);
				params1.put("pubacct_payamt_coins",pubacct_payamt_coins);
				params1.put("token_id",token);
				params1.put("ts",ConstantsQQ.getTimestamp());
				params1.put("version",version);
				params1.put("zoneid",zoneid);
				
				OpenApiV3 openApiV3 = new OpenApiV3(ConstantsQQ.APPID, ConstantsQQ.APPKEY);
	            openApiV3.setServerName(ConstantsQQ.SERVER_NAME);
	            String resp=openApiV3.api(ConstantsQQ.URL_CONFIRM_DELIVERY, params,ConstantsQQ.PROTOCOL_HTTPS);

	            //删除对应的token
				nosqlService.delToken(pid, token);
				//整理对应的token
				nosqlService.neatenToken(pid);
				response.getWriter().write("{\"ret\":0,\"msg\":\"OK\"}");
				//发送东西了
			}else {
				//{"ret":4,"msg":"请求参数错误：（sig）"}
				response.getWriter().write("{\"ret\":4,\"msg\":\"请求参数错误：（sig）\"}");
			}
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String myGetParameter(String key,ServletRequest request) {
		String value = request.getParameter(key);
		if (value==null) 
			return "";
		return value;
	}

}
