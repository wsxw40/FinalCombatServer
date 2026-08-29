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
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.gm.service.WSysLevelService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作系统地图
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(3)
public class SysLevelController extends DmController {
    @Resource
    private WSysLevelService wSysLevelService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysLevels")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wSysLevelService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wSysLevel
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "saveSysLevel")
    public void save(HttpServletRequest request, HttpServletResponse response, WSysLevel wSysLevel, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wSysLevelService.addByGm(wSysLevel, gmUser);
        } else {//修改
            wSysLevelService.updateByGm(wSysLevel, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysLevels");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wSysLevel
     * @throws Exception
     */
    @RequestMapping(value = "deleteSysLevel")
    public void delete(HttpServletRequest request, HttpServletResponse response, WSysLevel wSysLevel) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysLevelService.deleteByGm(wSysLevel, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysLevels");
    }
}
