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
import com.pearl.fcw.gm.pojo.WSysBannedWord;
import com.pearl.fcw.gm.service.WSysBannedWordService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作禁言
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(12)
public class SysBannedWordController extends DmController {
    @Resource
    private WSysBannedWordService wSysBannedWordService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "sysBannedWords")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wSysBannedWordService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wSysBannedWord
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "saveSysBannedWord")
    public void save(HttpServletRequest request, HttpServletResponse response, WSysBannedWord wSysBannedWord, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wSysBannedWordService.addByGm(wSysBannedWord, gmUser);
        } else {//修改
            wSysBannedWordService.updateByGm(wSysBannedWord, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/payments");
    }

    /**
     * 逻辑删除
     * @param request
     * @param response
     * @param wPayment
     * @throws Exception
     */
    @RequestMapping(value = "removeSysBannedWord")
    public void remove(HttpServletRequest request, HttpServletResponse response, WSysBannedWord wSysBannedWord) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysBannedWordService.removeByGm(wSysBannedWord, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/payments");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wPayment
     * @throws Exception
     */
    @RequestMapping(value = "deleteSysBannedWord")
    public void delete(HttpServletRequest request, HttpServletResponse response, WSysBannedWord wSysBannedWord) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wSysBannedWordService.deleteByGm(wSysBannedWord, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/payments");
    }
}
