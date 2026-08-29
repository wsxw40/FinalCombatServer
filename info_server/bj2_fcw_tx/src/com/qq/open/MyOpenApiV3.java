package com.qq.open;

import java.util.*;
import java.util.Map.Entry;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.*;

import com.qq.open.SnsNetwork;
import com.qq.open.MySnsSigCheck;
import com.qq.open.SnsStat;

import org.json.JSONObject;
import org.json.JSONException;
import org.apache.commons.httpclient.methods.multipart.FilePart;
 /**
 * 提供访问腾讯开放平台 OpenApiV3 的接口
 *
 * @version 3.0.2
 * @since jdk1.5
 * @author open.qq.com
 * @copyright © 2012, Tencent Corporation. All rights reserved.
 * @History:
 *				 3.0.3 | coolinchen| 2012-11-07 11:20:12 | support POST request in  "multipart/form-data" format
 *               3.0.2 | coolinchen| 2012-10-08 11:20:12 | support printing request string and result
 *				 3.0.1 | nemozhang | 2012-08-28 16:40:20 | support cpay callback sig verifictaion
 *               3.0.0 | nemozhang | 2012-03-21 12:01:05 | initialization
 *
 */
 
public class MyOpenApiV3
{
	private String appid;;
	private String appkey;
	private String serverName;
    /**
     * 构造函数
     * @param appid 应用的ID
     * @param appkey 应用的密钥
     */
	public MyOpenApiV3(String appid, String appkey) {
		this.appid = appid;
		this.appkey = appkey;
	}
    /**
     * 设置OpenApi服务器的地址
     * @param serverName OpenApi服务器的地址
     */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

    /**
     * 执行API调用
     * @param scriptName OpenApi CGI名字 ,如/v3/user/get_info
     * @param params OpenApi的参数列表
     * @param protocol HTTP请求协议 "http" / "https"
     * @return 返回服务器响应内容
     */
    public String api(String scriptName, HashMap<String, String> params, String protocol,String method) throws OpensnsException
    {
        System.out.println("5.11====================================");
        // 检查openid openkey等参数
        if (params.get("openid") == null)
            throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openid is empty");
        System.out.println("5.12===================================="+params.get("openid"));
        if (!isOpenid(params.get("openid")))
            throw new OpensnsException(ErrorCode.PARAMETER_INVALID, "openid is invalid");
        // 无需传sig,会自动生成
        System.out.println("5.13====================================");
        params.remove("sig");
        // 添加固定参数
        System.out.println("5.14====================================");
        params.put("appid", this.appid);
        // 签名密钥
        System.out.println("5.15===================================="+this.appid);
        String secret = this.appkey + "&";
        // 计算签名
        System.out.println("5.16===================================="+secret);
        String sig = MySnsSigCheck.makeSig(method, scriptName, params, secret);
        System.out.println("5.17===================================="+sig);
        sig= MySnsSigCheck.encodeValue(sig);
        StringBuilder sb = new StringBuilder(64);
        System.out.println("5.18===================================="+sig);
        sb.append(protocol).append("://").append(this.serverName).append(scriptName);
        System.out.println("5.19====================================");
        String url = sb.toString().trim();//https://119.147.19.43/v3/pay/buy_goods
        System.out.println("5.20===================================="+url);
        long startTime = System.currentTimeMillis();
        System.out.println("5.21===================================="+startTime);
		//通过调用以下方法，可以打印出最终发送到openapi服务器的请求参数以及url，默认注释
        String myRequest = myRequest(url,params,sig);
        System.out.println("5.22===================================="+myRequest);
        // 发送请求
        String resp = MySnsNetwork.getRequest(myRequest, protocol);
        System.out.println("5.23===================================="+resp);
        // 解码JSON
		JSONObject jo = null;
		System.out.println("5.24====================================");
		try {
		    System.out.println("5.25====================================");
			jo = new JSONObject(resp);
			System.out.println("5.26===================================="+jo);
		} catch (JSONException e) {
		    System.out.println("5.27====================================");
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, e);
		}
        // 检测ret值
        //int rc = jo.optInt("ret", 0);
        // 统计上报
        // SnsStat.statReport(startTime, serverName, params, method, protocol, rc,scriptName);
		//通过调用以下方法，可以打印出调用openapi请求的返回码以及错误信息，默认注释
		//printRespond(resp);
		 System.out.println("5.7===================================="+resp);
        return resp;
    }
    /**
     * 辅助函数，打印出完整的请求串内容
     * 
     * @param url 请求cgi的url
     * @param method 请求的方式 get/post
     * @param params OpenApi的参数列表
     */
	private String myRequest(String url,HashMap<String, String> params,String sig) throws OpensnsException	{
		StringBuilder buffer = new StringBuilder(128);
		ArrayList<Entry<String, String>> mappingList = new ArrayList<Map.Entry<String,String>>(params.entrySet()); 
		Collections.sort(mappingList, new Comparator<Map.Entry<String,String>>(){
			   public int compare(Map.Entry<String,String> mapping1,Map.Entry<String,String> mapping2){
			    return mapping1.getKey().compareTo(mapping2.getKey());
			   }
			  }); 
		for (Entry<String, String> entry : mappingList) {
			try {
				buffer.append(URLEncoder.encode((String)entry.getKey(), "UTF-8").replace("+", "%20").replace("*", "%2A"))
				.append("=")
				.append(URLEncoder.encode((String)entry.getValue(), "UTF-8").replace("+", "%20").replace("*", "%2A"))
				.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		buffer.append("sig="+sig);
		String tmp = url+"?"+buffer.toString();
		return tmp;
	}
	/**
     * 辅助函数，打印出完整的请求串内容
     * 
     * @param url 请求cgi的url
     * @param method 请求的方式 get/post
     * @param params OpenApi的参数列表
     */
	private void printRequest(String url,String method,HashMap<String, String> params) throws OpensnsException
	{
		System.out.println("==========Request Info==========\n");
		System.out.println("method:  " + method);
		System.out.println("url:  " + url);
		System.out.println("params:");
		System.out.println(params);
		System.out.println("querystring:");
		StringBuilder buffer = new StringBuilder(128);
		Iterator iter = params.entrySet().iterator();
		while (iter.hasNext())
		{
			Map.Entry entry = (Map.Entry) iter.next(); 
			try			{	
				buffer.append(URLEncoder.encode((String)entry.getKey(), "UTF-8").replace("+", "%20").replace("*", "%2A")).append("=").append(URLEncoder.encode((String)entry.getValue(), "UTF-8").replace("+", "%20").replace("*", "%2A")).append("&");
			}catch(UnsupportedEncodingException e){
				throw new OpensnsException(ErrorCode.MAKE_SIGNATURE_ERROR, e);
			}
		}
		String tmp = buffer.toString();
		tmp = tmp.substring(0,tmp.length()-1);
		System.out.println(tmp);
		System.out.println();
	}
	/**
     * 辅助函数，打印出完整的执行的返回信息
     * 
     * @return 返回服务器响应内容
     */
	private void printRespond(String resp) {
		System.out.println("===========Respond Info============");
		System.out.println(resp);
	}
    /**
     * 验证openid是否合法
     */
	private boolean isOpenid(String openid) {
		return (openid.length() == 32) && openid.matches("^[0-9A-Fa-f]+$");
	}
}
