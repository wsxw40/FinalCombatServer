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
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.gm.service.WSysCharacterService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作系统角色
 */
@Controller
@RequestMapping("fcw/gm")
@UrlCode(2)
public class SysCharacterController extends DmController {
    @Resource
    private WSysCharacterService wSysCharacterService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("sysCharacters")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wSysCharacterService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wSysCharacter
     * @param u
     * @throws Exception
     */
    @RequestMapping("saveSysCharacter")
    public void save(HttpServletRequest request, HttpServletResponse response, WSysCharacter wSysCharacter, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wSysCharacterService.addByGm(wSysCharacter, gmUser);
        } else {//修改
            wSysCharacterService.updateByGm(wSysCharacter, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysCharacters");
    }

    /**
     * 逻辑删除
     * @param request
     * @param response
     * @param wSysCharacter
     * @throws Exception
     */
    @RequestMapping("removeSysCharacter")
    public void remove(HttpServletRequest request, HttpServletResponse response, WSysCharacter wSysCharacter) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysCharacterService.removeByGm(wSysCharacter, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysCharacters");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wSysCharacter
     * @throws Exception
     */
    @RequestMapping("deleteSysCharacter")
    public void delete(HttpServletRequest request, HttpServletResponse response, WSysCharacter wSysCharacter) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysCharacterService.deleteByGm(wSysCharacter, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/sysCharacters");
    }
}
