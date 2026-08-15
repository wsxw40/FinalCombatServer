/**
 * 
 */
package com.pde.uweb.web.controller.ma;

import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 *  控制器--用户选择游戏
 * 
 *  @author Huanggang
 */
public class UserSelectGameController extends AbstractCommonController {

	private static final Logger logger = Logger.getLogger(UserSelectGameController.class);

	/**
	 * http://cgc.pde.com.cn/loginReq.htm?
	 * appId=fc0001&partnerId=xuelei0001&loginName
	 * =hellosfdsf&ipAddress=192.168.2.40&realName=%E5%A4%A7%E5%82%BB
	 * &idcard=4202812012090335&proxyIp=10.10.14.13&proxyPort=37000 cgc
	 * 游戏登录http网关url
	 */
	private String cgcRequestUrl;

	/**
	 * 开始游戏界面
	 */
	private String startGagePage;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] select game");
		
		UserVO user = HttpSessionUtil.getLoginUser(request);
		StringBuffer sb = new StringBuffer(96).append(this.cgcRequestUrl)
				.append("?appId=").append(request.getParameter("appId"))
				.append("&partnerId=").append(request.getParameter("partnerId"))
				.append("&loginName=").append(user.getUserName())
				.append("&ipAddress=").append(request.getRemoteAddr())
				.append("&realName=").append(URLEncoder.encode(user.getIdName(), "utf-8"))
				.append("&idcard=").append(user.getIdNumber())
				.append("&proxyIp=").append(request.getParameter("proxyIp"))
				.append("&proxyPort=").append(request.getParameter("proxyPort"));

		
		HttpGet httpGet = new HttpGet(sb.toString());
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = httpClient.execute(httpGet);
		InputStream inputStream = null;

		try {
			inputStream = httpResponse.getEntity().getContent();
			byte[] bs = new byte[inputStream.available()];
			inputStream.read(bs);
//			request.setAttribute("gameStartInfo", new String(bs));
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("gameStartInfo", new String(bs, Charsets.UTF_8));
            this.handlerJSCallback(request, response, jsonObj.toString());
		} finally {
			inputStream.close();
			httpGet.releaseConnection();
		}

        return null;
		//return new ModelAndView(this.startGagePage);
	}

	/**
	 * @param cgcRequestUrl
	 *            the cgcRequestUrl to set
	 */
	public void setCgcRequestUrl(String cgcRequestUrl) {
		this.cgcRequestUrl = cgcRequestUrl;
	}

	/**
	 * @param startGagePage
	 *            the startGagePage to set
	 */
	public void setStartGagePage(String startGagePage) {
		this.startGagePage = startGagePage;
	}

}
