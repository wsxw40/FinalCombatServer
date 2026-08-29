package com.pearl.fcw.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;

/**
 * GM操作中一些下拉列表所用的数据，无权限拦截
 */
@Controller
@RequestMapping(value = "fcw/gm/chosen")
public class ChosenController extends DmController {
    @Resource
    private WSysItemService wSysItemService;
    @Resource
    private WPlayerService wPlayerService;

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
     * 玩家列表
     * @param request
     * @param response
     * @return
     * @throws Exception1
     */
    @RequestMapping(value = "players")
    public @ResponseBody Object getPageMap(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wPlayerService.getPageMap(param);
    }
}
