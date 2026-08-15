package com.pearl.fcw.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.gm.pojo.WGmUser;
import com.pearl.fcw.gm.service.WGmUserService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作GM用户
 */
@Controller
@RequestMapping("fcw/gm")
@UrlCode(17)
public class GmUserController extends DmController {

    @Resource
    private WGmUserService wGmUserService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("gmUsers")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wGmUserService.findPageMap(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wGmUser
     * @throws Exception
     */
    @RequestMapping("saveGmUser")
    public void save(HttpServletRequest request, HttpServletResponse response, WGmUser wGmUser, @RequestParam("groupId") Integer groupId, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wGmUserService.addByGm(wGmUser, groupId, gmUser);
        } else {//修改
            wGmUserService.updateByGm(wGmUser, groupId, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/gmUsers");
    }

    /**
     * 重置其他GM用户的密码
     * @param request
     * @param response
     * @param wGmUser
     * @return
     * @throws Exception
     */
    @RequestMapping("resetGmUserPwd")
    public @ResponseBody Object resetPwd(HttpServletRequest request, HttpServletResponse response, WGmUser wGmUser) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wGmUserService.resetPwdByGm(wGmUser, gmUser);
        JsonObject jo = new JsonObject();
        jo.addProperty("msg", StringUtil.getI18nWord("web.gm.hidden.alert.resetPwdSuccess"));
        return jo.toString();
    }

    /**
     * 逻辑删除
     * @param request
     * @param response
     * @param wGmUser
     * @throws Exception
     */
    @RequestMapping("removeGmUser")
    public void remove(HttpServletRequest request, HttpServletResponse response, WGmUser wGmUser) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wGmUserService.removeByGm(wGmUser, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/gmUsers");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wGmUser
     * @throws Exception
     */
    @RequestMapping("deleteGmUser")
    public void delete(HttpServletRequest request, HttpServletResponse response, WGmUser wGmUser) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wGmUserService.deleteByGm(wGmUser, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/gmUsers");
    }
}
