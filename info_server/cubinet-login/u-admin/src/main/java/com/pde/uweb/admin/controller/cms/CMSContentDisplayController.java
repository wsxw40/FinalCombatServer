package com.pde.uweb.admin.controller.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.asserts.Asserts;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.cms.service.CmsService;

/**
 * 控制器--内容显示
 * 
 * @author Sean.Weng
 */
public class CMSContentDisplayController extends AbstractCommonController {

	private static final Logger logger = Logger.getLogger(CMSContentDisplayController.class);
	
	private CmsService cmsService;
	
	/** 跳转到信息更新页面 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("come from address [ " + request.getRemoteAddr() + " ] show news content ");

		JSONObject jsonObj = new JSONObject();
		String newsId = request.getParameter("newsId");
		if (!Asserts.assertStringIsNull(newsId)) {
			jsonObj.put("cms", cmsService.getNewsById(Long.parseLong(newsId)));
		}
		
		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}
	

	public void setCmsService(CmsService cmsService) {
		this.cmsService = cmsService;
	}
}