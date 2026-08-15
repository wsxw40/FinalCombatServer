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
import com.pearl.fcw.gm.pojo.WChannel;
import com.pearl.fcw.gm.pojo.WServer;
import com.pearl.fcw.gm.service.WServerService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作区服
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(7)
public class ServerController extends DmController {
    @Resource
    private WServerService wServerService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "servers")
    public @ResponseBody Object findPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wServerService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wServer
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "saveServer")
    public void save(HttpServletRequest request, HttpServletResponse response, WServer wServer, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wServerService.addByGm(wServer, gmUser);
        } else {//修改
            wServerService.updateByGm(wServer, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/servers");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wServer
     * @throws Exception
     */
    @RequestMapping(value = "deleteServer")
    public void delete(HttpServletRequest request, HttpServletResponse response, WServer wServer) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wServerService.deleteByGm(wServer, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/servers");
    }

    /**
     * 渠道列表
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "channels")
    public @ResponseBody Object findChannelPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wServerService.findChannelPage(param);
    }

    /**
     * 保存渠道
     * @param request
     * @param response
     * @param wChannel
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "saveChannel")
    public void saveChannel(HttpServletRequest request, HttpServletResponse response, WChannel wChannel, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wServerService.addChannelByGm(wChannel, gmUser);
        } else {//修改
            wServerService.updateChannelByGm(wChannel, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/channels");
    }

    /**
     * 物理删除渠道
     * @param request
     * @param response
     * @param wChannel
     * @throws Exception
     */
    @RequestMapping(value = "deleteChannel")
    public void deleteChannel(HttpServletRequest request, HttpServletResponse response, WChannel wChannel) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wServerService.deleteChannelByGm(wChannel, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/channels");
    }
}
