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
import com.pearl.fcw.gm.service.WGmLogService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM日志查看和数据回滚
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(5)
public class GmLogController extends DmController {
    @Resource
    private WGmLogService wGmLogService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("gmLogs")
    public @ResponseBody Object findPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wGmLogService.findPage(param);
    }

    /**
     * 日志事务列表
     * @param request
     * @param response
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping("gmTransactions")
    public @ResponseBody Object findTransactionPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wGmLogService.findTransactionPage(param);
    }

    /**
     * 根据一个事务记录回滚
     * @param request
     * @param response
     * @param id gmTransaction的id
     * @throws Exception
     */
    @RequestMapping("backTransaction")
    public @ResponseBody Object backTransaction(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wGmLogService.rollBackTransactionByGm(id, gmUser);
        JsonObject jo = new JsonObject();
        jo.addProperty("msg", StringUtil.getI18nWord("web.gm.hidden.alert.rollBackSuccess"));
        return jo.toString();
    }

    /**
     * 根据一个日志记录回滚
     * @param request
     * @param response
     * @param id gmLog的id
     * @return
     * @throws Exception
     */
    @RequestMapping("backLog")
    public @ResponseBody Object backLog(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wGmLogService.rollBackLogByGm(id, gmUser);
        JsonObject jo = new JsonObject();
        jo.addProperty("msg", StringUtil.getI18nWord("web.gm.hidden.alert.rollBackSuccess"));
        return jo.toString();
    }
}
