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
import com.pearl.fcw.gm.pojo.WSysStrengthenAppend;
import com.pearl.fcw.gm.service.WSysStrengthenAppendService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作系统强化合成
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(22)
public class SysStrengthenAppendController extends DmController {
    @Resource
    private WSysStrengthenAppendService wSysStrengthenAppendService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysStrengthenAppends")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wSysStrengthenAppendService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wSysStrengthenAppend
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "saveSysStrengthenAppend")
    public void save(HttpServletRequest request, HttpServletResponse response, WSysStrengthenAppend wSysStrengthenAppend, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wSysStrengthenAppendService.addByGm(wSysStrengthenAppend, gmUser);
        } else {//修改
            wSysStrengthenAppendService.updateByGm(wSysStrengthenAppend, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysStrengthenAppends");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wSysStrengthenAppend
     * @throws Exception
     */
    @RequestMapping(value = "deleteSysStrengthenAppend")
    public void delete(HttpServletRequest request, HttpServletResponse response, WSysStrengthenAppend wSysStrengthenAppend) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysStrengthenAppendService.deleteByGm(wSysStrengthenAppend, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysStrengthenAppends");
    }
}
