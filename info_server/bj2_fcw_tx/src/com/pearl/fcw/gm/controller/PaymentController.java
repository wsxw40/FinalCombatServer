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
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.service.WPaymentService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作商店
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(20)
public class PaymentController extends DmController {
    @Resource
    private WPaymentService wPaymentService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "payments")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wPaymentService.findPage(param);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wPayment
     * @param u
     * @throws Exception
     */
    @RequestMapping(value = "savePayment")
    public void save(HttpServletRequest request, HttpServletResponse response, WPayment wPayment, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        if (null == u) {//新增
            wPaymentService.addByGm(wPayment, gmUser);
        } else {//修改
            wPaymentService.updateByGm(wPayment, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/payments");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wPayment
     * @throws Exception
     */
    @RequestMapping(value = "deletePayment")
    public void delete(HttpServletRequest request, HttpServletResponse response, WPayment wPayment) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wPaymentService.deleteByGm(wPayment, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/payments");
    }
}
