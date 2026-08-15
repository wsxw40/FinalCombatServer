package com.pearl.fcw.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.lobby.service.TestService;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.utils.ConfigurationUtil;


/**
 * 测试用
 */
@Controller
@RequestMapping("")
public class TestController extends DmController {
    @Resource
    private EntityManager entityManager;
    @Resource
    private WPlayerService wPlayerService;


    @Resource
    private TestService testService;

    @RequestMapping("test")
    public @ResponseBody Object test(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) throws Exception {

        testService.innerService(id.toString());

        //        String str = wPlayerService.getPlayerDailyDiscount(id);
        //        System.out.println(str);

        return "success";
    }

    @RequestMapping("test1")
	public @ResponseBody Object test1(HttpServletRequest request, HttpServletResponse response, @RequestParam("uri") String uri) throws Exception {
        BaseClientServlet servlet = null;
		servlet = (BaseClientServlet) ConfigurationUtil.beanFactory.getBean(ConfigurationUtil.INFO_INTERFACE_PREFIX + uri);
        String str = servlet.getResponseStr(request, response);
        str = str.replaceAll("\r\n", "&#13;").replaceAll("\t", "  ").replaceAll("\\\"", "''");
        return str;
    }
}
