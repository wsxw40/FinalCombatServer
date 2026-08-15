package com.pearl.fcw.core.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring页面错误统一处理
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (request.getRequestURI().endsWith("/fcw/login")) {
            Map<String, Object> map = new HashMap<>();
            map.put("error", 1);
            return new ModelAndView("login", map);
        }
        //        logger.error(handler.toString());
        //        logger.error(ex.toString());
        //        for (StackTraceElement p : ex.getStackTrace()) {
        //            logger.error(p.toString());
        //        }
        logger.error("SpringMVC error : ", ex);
        return new ModelAndView("500");
    }

}
