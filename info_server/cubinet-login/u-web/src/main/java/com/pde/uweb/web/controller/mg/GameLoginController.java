/**
 *
 */
package com.pde.uweb.web.controller.mg;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.web.utils.HttpClientHelp;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器--用户选择游戏
 *
 * @author Huanggang
 */
public class GameLoginController extends AbstractCommonController {

    private static final Logger logger = Logger.getLogger(GameLoginController.class);

    /**
     * http://cgc.pde.com.cn/loginReq.htm?
     * appId=fc0001&partnerId=xuelei0001&loginName
     * =hellosfdsf&ipAddress=192.168.2.40&realName=%E5%A4%A7%E5%82%BB
     * &idcard=4202812012090335&proxyIp=10.10.14.13&proxyPort=37000 cgc
     * 游戏登录http网关url
     */
    private String cgcRequestUrl;
    private String partnerId;

    /**
     * 开始游戏界面
     */
    private String startGagePage;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] select game");

        UserVO user = HttpSessionUtil.getLoginUser(request);

        List<NameValuePair> qparams = new ArrayList<>();
        qparams.add(new BasicNameValuePair("gameAreaAlias", request.getParameter("gameAreaAlias")));
        qparams.add(new BasicNameValuePair("partnerId", partnerId));
        qparams.add(new BasicNameValuePair("loginName", user.getUserName() + ""));
        qparams.add(new BasicNameValuePair("ipAddress", request.getRemoteAddr()));
        qparams.add(new BasicNameValuePair("realName", user.getIdName()));
        qparams.add(new BasicNameValuePair("idcard", user.getIdNumber()));

        try {
            String result= HttpClientHelp.requestGet(this.cgcRequestUrl, qparams);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result", result);
            this.handlerJSCallback(request, response, jsonObj.toString());
        } catch (Exception e) {
            logger.error("game login request error!!!",e);
        }

        return null;
        //return new ModelAndView(this.startGagePage);
    }

    /**
     * @param cgcRequestUrl the cgcRequestUrl to set
     */
    public void setCgcRequestUrl(String cgcRequestUrl) {
        this.cgcRequestUrl = cgcRequestUrl;
    }

    /**
     * @param startGagePage the startGagePage to set
     */
    public void setStartGagePage(String startGagePage) {
        this.startGagePage = startGagePage;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }
}
