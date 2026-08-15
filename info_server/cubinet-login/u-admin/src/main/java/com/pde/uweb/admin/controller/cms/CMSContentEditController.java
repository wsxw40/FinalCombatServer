/**
 * 
 */
package com.pde.uweb.admin.controller.cms;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.cms.dto.ModifyNewsRequestDto;
import com.pde.uweb.cms.service.CmsService;

/**
 * 控制器 -- 编辑资讯
 * 
 * @author Sean.Weng
 */
public class CMSContentEditController extends AbstractCommonController {
	
	private final static Logger log = Logger.getLogger(CMSContentEditController.class);
	private CmsService cmsService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		JSONObject jsonObj = new JSONObject();
		ModifyNewsRequestDto requestDto = new ModifyNewsRequestDto();
	    Map<String, String> map = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
//	    request.setAttribute("cmsContent", requestDto);
//	    request.setAttribute("newsColumns", cmsService.getNewsColumn());
	    // 参数校验未通过
	 	if (!map.isEmpty()) {
	 		logger.error("come from remote address [ " + request.getRemoteAddr() + " ]  edit content error");
	 		jsonObj.put("error", map);
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
	 	}
		// 发布的内容存入数据库
		try {
			boolean result = cmsService.doModifyNewsRnTx(requestDto);
			if (!result) {
				map.put("error", "发布资讯失败");
				jsonObj.put("error", map);
				this.handlerJSCallback(request, response, jsonObj.toString());
				return null;
			}
		} catch (Exception e) {
			log.error(e);
			map.put("error", e.getMessage());
			jsonObj.put("error", map);
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
//		request.setAttribute("cmsContent", requestDto);
	    this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}

	public void setCmsService(CmsService cmsService) {
		this.cmsService = cmsService;
	}
}
