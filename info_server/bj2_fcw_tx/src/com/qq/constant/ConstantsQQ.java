package com.qq.constant;

public class ConstantsQQ {
	public static final String APPID 					= "1105455908";//应用的ID
	public static final String APPKEY 					= "NmB8VQoj3qOMwDX2";//应用的密钥
	public static final String PF 						= "3366";
	public static final String FORMAT 					= "json";
	public static final String ZONEID 					= "4";
	public static final String METHOD_GET 				= "GET";
	public static final String PROTOCOL_HTTPS 			= "https";
	
	public static final String URL_BUY_GOODS 			= "/v3/pay/buy_goods";//应用调用支付接口获取交易token以及物品URL。即上图中的第2步。
	public static final String URL_CONFIRM_DELIVERY 	= "/v3/pay/confirm_delivery";//应用调用发货通知接口。即上图中的第10步
	public static final String SERVER_NAME 				= "119.147.19.43";//地址
	
	public static String getTimestamp(){
		return System.currentTimeMillis()/1000+"";
	}

}
