package com.pde.uweb.web.controller.mg;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.web.utils.HttpClientHelp;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-10-10
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public class MyGameListController extends AbstractCommonController {

    private static final Logger logger = Logger.getLogger(MyGameListController.class);

    private String partnerId;
    private String cgcMyGameListUrl;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] CDKeyData ");

        UserVO user = HttpSessionUtil.getLoginUser(request);

        List<NameValuePair> qparams = new ArrayList<>();
        qparams.add(new BasicNameValuePair("partnerId", partnerId));
        qparams.add(new BasicNameValuePair("loginName", user.getUserId() + ""));

        try {
            String result= HttpClientHelp.requestGet(this.cgcMyGameListUrl, qparams);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("result", result);
            this.handlerJSCallback(request, response, jsonObj.toString());
        } catch (Exception e) {
            logger.error("game login request error!!!",e);
        }

        return null;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public void setCgcMyGameListUrl(String cgcMyGameListUrl) {
        this.cgcMyGameListUrl = cgcMyGameListUrl;
    }
}
