/**
 *
 */
package com.pde.uweb.web.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.cms.service.CmsService;
import com.pde.uweb.database.cms.cmsnews.CmsNewsPojo;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Huanggang
 */
public class CMSContentDetailController extends AbstractCommonController {

    private CmsService cmsService;

    @Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
    	JSONObject jsonObj = new JSONObject();
		long cmsId = Long.parseLong(request.getParameter("cmsId"));
		CmsNewsPojo cmsNewsPojo = this.cmsService.getNewsById(cmsId);
		if (cmsNewsPojo != null) {
			jsonObj.put("headLine", cmsNewsPojo.getHeadline());
			jsonObj.put("createDate", cmsNewsPojo.getCreateDate());
			
			// 分页符
			String[] contents = cmsNewsPojo.getContent().split("<div style=\"page-break-after: always; \"><span style=\"DISPLAY:none\">&nbsp;</span></div>");
			// 总页数
			int pageCount = contents.length;
			jsonObj.put("pageCount", String.valueOf(pageCount));

			JSONObject content = new JSONObject();
			for (int i = 0; i < pageCount; i++) {
				content.put(String.valueOf(i), contents[i]);
			}
			jsonObj.put("content", content);
		}
		this.handlerJSCallback(request, response, jsonObj.toJSONString());
		return null;
	}

    /**
     * @param cmsDetailPage the cmsDetailPage to set
     */


    /**
     * @param cmsService the cmsService to set
     */
    public void setCmsService(CmsService cmsService) {
        this.cmsService = cmsService;
    }

}
