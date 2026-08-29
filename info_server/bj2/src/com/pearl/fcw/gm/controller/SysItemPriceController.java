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
import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.gm.service.WSysItemPriceService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作多对多交易
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(20)
//FIXME 暂时使用payment的权限
public class SysItemPriceController extends DmController {
    @Resource
	private WSysItemPriceService wSysItemPriceService;

    /**
	 * 列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "sysItemPrices")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
		return wSysItemPriceService.getPageMap(param);
    }

    /**
	 * 保存
	 * @param request
	 * @param response
	 * @param wPayment
	 * @param u
	 * @throws Exception
	 */
	@RequestMapping(value = "saveSysItemPrice")
	public void save(HttpServletRequest request, HttpServletResponse response, WSysItemPrice m, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
		if (null == u) {//新增
			wSysItemPriceService.addByGm(m, gmUser);
		} else {//修改
			wSysItemPriceService.updateByGm(m, gmUser);
        }
		response.sendRedirect(request.getContextPath() + "/fcw/#/sysItemPrices");
    }

    /**
	 * 逻辑删除
	 * @param request
	 * @param response
	 * @param wPayment
	 * @throws Exception
	 */
	@RequestMapping(value = "removeSysItemPrice")
	public void delete(HttpServletRequest request, HttpServletResponse response, WSysItemPrice m) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
		wSysItemPriceService.removeByGm(m, gmUser);
		response.sendRedirect(request.getContextPath() + "/fcw/#/sysItemPrices");
    }
}
