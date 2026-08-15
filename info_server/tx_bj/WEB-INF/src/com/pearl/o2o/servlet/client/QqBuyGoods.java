package com.pearl.o2o.servlet.client;


import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.service.BaseService;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;
import com.qq.constant.ConstantsQQ;
import com.qq.open.MyOpenApiV3;
import com.qq.pojo.Quser;
import com.qq.pojo.Token;
/**
 * Step1	应用调用支付接口获取交易token以及物品URL。即上图中的第2步
 * @author zhaolianming
 *
 */
public class QqBuyGoods extends BaseClientServlet {
	private static final long serialVersionUID = -7901231444176863249L;
	static Logger log = LoggerFactory.getLogger(QqBuyGoods.class.getName());
	private static final String[] paramNames = { "pid","cost"};

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int cost = StringUtil.toInt(args[1]);
			if (cost<=0&&cost>100) 
				Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			
			Quser quser = nosqlService.getPidToQuser(playerId);
			
			HashMap<String, String> params=new HashMap<String, String>();
			String ts = ConstantsQQ.getTimestamp();
			
			int price = 10;
			String payitem = "50005*"+10+"*" + cost;
			String amt = price*cost+"";
			
			params.put("amt", amt);
			params.put("appid",ConstantsQQ.APPID);
			params.put("appmode", "1");
			params.put("format", ConstantsQQ.FORMAT);
			params.put("goodsmeta", "FC点*游戏内的代币");					//物品信息
			params.put("goodsurl", "http://qzonestyle.gtimg.cn/qzonestyle/act/qzone_app_img/app613_613_75.png");//物品的图片url
			params.put("openid", quser.getOpenid());
			params.put("openkey",quser.getOpenkey());
			params.put("payitem", payitem);						//物品信息请使用ID*price*num
			params.put("pf", ConstantsQQ.PF);
			params.put("pfkey", quser.getPfkey());
			params.put("ts", ts);									//linux时间戳
			params.put("zoneid", ConstantsQQ.ZONEID);				//分区
			//String makeSig = MySnsSigCheck.makeSig(ConstantsQQ.METHOD_GET, ConstantsQQ.URL_BUY_GOODS, params, ConstantsQQ.APPKEY+"&");//生成签名
			
			MyOpenApiV3 openApiV3 = new MyOpenApiV3(ConstantsQQ.APPID,ConstantsQQ.APPKEY);
			openApiV3.setServerName(ConstantsQQ.SERVER_NAME);
			String resp = openApiV3.api(ConstantsQQ.URL_BUY_GOODS, params,ConstantsQQ.PROTOCOL_HTTPS, ConstantsQQ.METHOD_GET);
			System.out.println(resp);
			JSONObject jo = JSONObject.parseObject(resp);
			Integer ret = jo.getInteger("ret");
			if (ret==0) {
				String is_lost = jo.getString("is_lost");
				String url_params = jo.getString("url_params");//给客服端
				url_params = URLEncoder.encode(url_params, "utf-8");
				String token_id = jo.getString("token");
				//保持成token
				nosqlService.updateToken(playerId, new Token(token_id,ts,payitem));
				
				if (ConfigurationUtil.SWITCH_XUNLEI_LOG.getIsOn()) {
					nosqlService.addXunleiLog("21.1"
						+ Constants.XUNLEI_LOG_DELIMITER + playerId
						+ Constants.XUNLEI_LOG_DELIMITER + quser.getOpenid()
						+ Constants.XUNLEI_LOG_DELIMITER + token_id
						+ Constants.XUNLEI_LOG_DELIMITER + ts
						+ Constants.XUNLEI_LOG_DELIMITER + payitem
						+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())
						);
					}
				return Converter.getQqBuyGoods(0,url_params);
			}
				return Converter.getQqBuyGoods(-1,jo.getString("msg"));
			} catch (Exception e) {
				log.warn("Exception in QQ_buy_goods", e);
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		@Override
		protected String[] paramNames() {
			return paramNames;
		}
	
		@Override
		protected String getLockKey(String[] paramNames) {
			return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
		}
}
