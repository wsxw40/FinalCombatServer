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
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作系统物品
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(1)
public class SysItemController extends DmController {
    @Resource
    private WSysItemService wSysItemService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysItems")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        DataTablesPage<WSysItem> page = wSysItemService.findPage(param);
        return page;
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param player
     * @param playerInfo
     * @throws Exception
     */
    @RequestMapping(value = "saveSysItem")
    public void save(HttpServletRequest request, HttpServletResponse response, WSysItem wSysItem, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wSysItemService.addByGm(wSysItem, gmUser);
        } else {//修改
            wSysItemService.updateByGm(wSysItem, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysItems");
    }

    /**
     * 逻辑删除
     * @param request
     * @param response
     * @param wSysItem
     * @throws Exception
     */
    @RequestMapping(value = "removeSysItem")
    public void remove(HttpServletRequest request, HttpServletResponse response, WSysItem wSysItem) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysItemService.removeByGm(wSysItem, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysItems");
    }

    /**
     * 逻辑恢复
     * @param request
     * @param response
     * @param wSysItem
     * @throws Exception
     */
    @RequestMapping(value = "restoreSysItem")
    public void restore(HttpServletRequest request, HttpServletResponse response, WSysItem wSysItem) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysItem = wSysItemService.findEntity(wSysItem.getId());
        wSysItem.setIsRemoved(false);
        wSysItemService.updateByGm(wSysItem, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysItems");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wSysItem
     * @throws Exception
     */
    @RequestMapping(value = "deleteSysItem")
    public void delete(HttpServletRequest request, HttpServletResponse response, WSysItem wSysItem) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysItemService.deleteByGm(wSysItem, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysItems");
    }
}
