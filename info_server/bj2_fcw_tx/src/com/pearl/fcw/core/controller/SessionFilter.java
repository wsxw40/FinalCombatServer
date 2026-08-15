package com.pearl.fcw.core.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.JsonObject;
import com.pearl.fcw.gm.service.WGmUserService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.StringUtil;

/**
 * 拦截 /fcw/**的请求处理用户权限
 */
public class SessionFilter extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private List<String> allowUrls = new ArrayList<>();
    @Resource
    private WGmUserService wGmUserService;

    public List<String> getAllowUrls() {
        return allowUrls;
    }

    public void setAllowUrls(List<String> allowUrls) {
        this.allowUrls = allowUrls;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().substring(request.getContextPath().length());
        if (!url.startsWith("/fcw")) {
            return true;
        }
        if (allowUrls.contains(url)) {
            return true;
        }
        //未登录
        if (null == request.getSession().getAttribute(Constants.GM_SESSION_KEY)) {
            if (request.getHeader("X-Requested-With") == null) {//非异步请求转到登录页面
                response.sendRedirect(request.getContextPath() + "/fcw/");
            } else {//异步请求返回未登录的错误信息
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/json;charset=UTF-8");
                JsonObject jo = new JsonObject();
                jo.addProperty("error", StringUtil.getI18nWord("web.gm.hidden.alert.loginAgain"));
                response.getWriter().append(jo.toString());
            }
            return false;
        }
        //GM用户权限拦截
        try {
            UrlCode urlCode = ((HandlerMethod) handler).getBeanType().getAnnotation(UrlCode.class);
            if (null != urlCode) {
                List<Integer> list = (List<Integer>) request.getSession().getAttribute(Constants.GM_SESSION_PRIVILEGE);
                if (!list.contains(urlCode.value())) {
                    if (request.getHeader("X-Requested-With") == null) {//非异步
                        response.sendRedirect(request.getContextPath() + "/fcw/roleError");
                    } else {//异步
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("text/json;charset=UTF-8");
                        JsonObject jo = new JsonObject();
                        jo.addProperty("error", StringUtil.getI18nWord("web.gm.hidden.alert.permissionDeny"));
                        response.getWriter().append(jo.toString());
                    }
                    return false;
                }
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //        request.getSession().setAttribute(Constants.WEB_APP_ROOT_KEY, request.getContextPath());
        super.postHandle(request, response, handler, modelAndView);
    }
}
