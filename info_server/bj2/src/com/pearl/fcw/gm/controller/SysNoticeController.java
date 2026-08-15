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
import com.pearl.fcw.gm.pojo.WSysNotice;
import com.pearl.fcw.gm.service.WSysNoticeService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作公告
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(9)
public class SysNoticeController extends DmController {
    @Resource
	private WSysNoticeService wSysNoticeService;

    /**
	 * 列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sysNotices")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
		return wSysNoticeService.findPage(param);
    }

    /**
	 * 保存
	 * @param request
	 * @param response
	 * @param wPayment
	 * @param u
	 * @throws Exception
	 */
	@RequestMapping(value = "saveSysNotice")
	public void save(HttpServletRequest request, HttpServletResponse response, WSysNotice m, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
		if (null == u) {//新增
			wSysNoticeService.addByGm(m, gmUser);
		} else {//修改
			wSysNoticeService.updateByGm(m, gmUser);
        }
		response.sendRedirect(request.getContextPath() + "/fcw/#/sysNotices");
    }

    /**
	 * 逻辑删除
	 * @param request
	 * @param response
	 * @param wPayment
	 * @throws Exception
	 */
	@RequestMapping(value = "removeSysNotice")
	public void delete(HttpServletRequest request, HttpServletResponse response, WSysNotice m) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
		wSysNoticeService.removeByGm(m, gmUser);
		response.sendRedirect(request.getContextPath() + "/fcw/#/sysNotices");
    }
}
