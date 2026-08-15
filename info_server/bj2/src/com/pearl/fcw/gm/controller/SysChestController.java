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
import com.pearl.fcw.gm.pojo.WSysChest;
import com.pearl.fcw.gm.service.WSysChestService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作系统宝箱
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(26)
public class SysChestController extends DmController {
    @Resource
    private WSysChestService wSysChestService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysChests")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wSysChestService.getPageMap(param);
        //        return wSysChestService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wSysChest
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "saveSysChest")
    public void save(HttpServletRequest request, HttpServletResponse response, WSysChest wSysChest, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wSysChestService.addByGm(wSysChest, gmUser);
        } else {//修改
            wSysChestService.updateByGm(wSysChest, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysChests");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wSysChest
     * @throws Exception
     */
    @RequestMapping(value = "deleteSysChest")
    public void delete(HttpServletRequest request, HttpServletResponse response, WSysChest wSysChest) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysChestService.deleteByGm(wSysChest, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysChests");
    }
}
