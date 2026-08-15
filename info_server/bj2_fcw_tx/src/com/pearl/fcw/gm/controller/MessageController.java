package com.pearl.fcw.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.gm.pojo.WMessage;
import com.pearl.fcw.gm.service.WMessageService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作邮件
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(6)
public class MessageController extends DmController {

    @Resource
    private WMessageService wMessageService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "messages")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wMessageService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wMessage
     * @throws Exception
     */
    @RequestMapping(value = "saveMessage")
    public void save(HttpServletRequest request, HttpServletResponse response, WMessage wMessage, @RequestParam("receiverIds") String receiverIds) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wMessageService.addAndSend(wMessage, receiverIds, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/messages");
    }

    /**
     * 逻辑删除
     * @param request
     * @param response
     * @param wMessage
     * @throws Exception
     */
    @RequestMapping(value = "removeMessage")
    public void remove(HttpServletRequest request, HttpServletResponse response, WMessage wMessage) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);

        response.sendRedirect(request.getContextPath() + "/fcw/#/messages");
    }
}
