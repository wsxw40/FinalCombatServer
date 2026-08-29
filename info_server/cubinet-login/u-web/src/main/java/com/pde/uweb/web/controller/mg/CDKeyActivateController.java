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
 * 逻辑控制--防沉迷设置
 *
 * @author Huanggang
 */
public class CDKeyActivateController extends AbstractCommonController {

//    public static final String CDKEY_ACTIVATE_SUCCESS = "激活成功";
//    public static final String CDKEY_ACTIVATE_ILLEGAL = "非法请求";
//    public static final String CDKEY_ACTIVATE_NOT_EMPTY = "激活码不能为空";
//    public static final String CDKEY_ACTIVATE_SIZE_12 = "激活码能为12位";
//    public static final String CDKEY_ACTIVATE_NOT_EXIT = "激活码不存在";
//    public static final String CDKEY_ACTIVATE_YET_USE = "激活码已被领取";
//    public static final String CDKEY_ACTIVATE_EXPIRE = "激活码已过期";
//    public static final String CDKEY_ACTIVATE_NOT_IN_GAMESERVICE = "激活码不适用于当前区服";
//    public static final String CDKEY_ACTIVATE_NOT_OPEN = "当前区服尚未开放";


    private static final Logger logger = Logger.getLogger(CDKeyActivateController.class);

    private String partnerId;
    private String cgcCdkeyActivateUrl;


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ModelAndView modelAndView = new ModelAndView(new MappingJackson2JsonView());

//        boolean success = false;
        //成功/失败/过期
//        String message = CDKEY_ACTIVATE_SUCCESS;

        logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] cdkey active ");

//        String cdkey = request.getParameter("cdkey");
//        String gameCdKeyChannelId = request.getParameter("gameCdKeyChannelId");
//        if (gameCdKeyChannelId == null || gameCdKeyChannelId.isEmpty()) {
//            message = CDKEY_ACTIVATE_ILLEGAL;
//        } else if (cdkey == null || cdkey.isEmpty()) {
//            message = CDKEY_ACTIVATE_NOT_EMPTY;
//        } else if (cdkey.length() != 12) {
//            message = CDKEY_ACTIVATE_SIZE_12;
//        } else {
//            GameCdKeyPojo gameCdKeyPojo = this.gameCdKeyDao.selectByCdkey(cdkey);
//            GameCdKeyChannelPojo gameCdKeyChannelPojo = gameCdKeyChannelDao.select(gameCdKeyPojo.getChannelId());
//
//            if (gameCdKeyPojo == null) {
//                message = CDKEY_ACTIVATE_NOT_EXIT;
//            } else if (gameCdKeyPojo.getUserId() != 0) {
//                message = CDKEY_ACTIVATE_YET_USE;
//            } else if (gameCdKeyPojo.getExpireDate().getTime() < System.currentTimeMillis()) {
//                message = CDKEY_ACTIVATE_EXPIRE;
//            } else if (gameCdKeyPojo.getChannelId() != Long.parseLong(gameCdKeyChannelId)||gameCdKeyPojo.getGameCdKeyTypeId()!=gameCdKeyChannelPojo.getGameCdKeyTypeId()) {
//                message = CDKEY_ACTIVATE_NOT_IN_GAMESERVICE;
//            } else {
//
//                GameCdKeyTypePojo gameCdKeyTypePojo = gameCdKeyTypeDao.select(gameCdKeyChannelPojo.getGameCdKeyTypeId());
//                ChannelPojo channelPojo = channelDao.select(gameCdKeyPojo.getChannelId());
//
//                if(gameCdKeyChannelPojo.getEnable() == 0||gameCdKeyTypePojo.getEnable() ==0||channelPojo.getStatus()==0){
//                    message = CDKEY_ACTIVATE_NOT_OPEN;
//                }else{
//                    UserVO vo = HttpSessionUtil.getLoginUser(request);
//                    System.out.println(vo.getUserId());
//                    gameCdKeyPojo.setUserId(vo.getUserId());
//                    boolean update = this.gameCdKeyDao.update(gameCdKeyPojo);
//                    if (update) {
//                        success = true;
//                    } else {
//                        message = CDKEY_ACTIVATE_YET_USE;
//                    }
//                }
//            }
//        }


        UserVO user = HttpSessionUtil.getLoginUser(request);

        List<NameValuePair> qparams = new ArrayList<>();
        qparams.add(new BasicNameValuePair("gameAreaAlias", request.getParameter("gameAreaAlias")));
        qparams.add(new BasicNameValuePair("cdkey", request.getParameter("cdkey")));
        qparams.add(new BasicNameValuePair("partnerId", partnerId));
        qparams.add(new BasicNameValuePair("loginName", user.getUserName() + ""));
        qparams.add(new BasicNameValuePair("ipAddress", request.getRemoteAddr()));
        qparams.add(new BasicNameValuePair("realName", user.getIdName()));
        qparams.add(new BasicNameValuePair("idcard", user.getIdNumber()));

        try {
            String result= HttpClientHelp.requestGet(this.cgcCdkeyActivateUrl, qparams);
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

    public void setCgcCdkeyActivateUrl(String cgcCdkeyActivateUrl) {
        this.cgcCdkeyActivateUrl = cgcCdkeyActivateUrl;
    }
}
