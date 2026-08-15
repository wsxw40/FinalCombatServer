/**
 * 
 */
package com.pde.uweb.web.controller.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.asserts.Asserts;
import com.pde.uweb.cms.service.CmsService;
import com.pde.uweb.database.cms.cmsnews.CmsNewsPojo;
import com.pde.uweb.database.cms.cmsnews.CmsNewsSearchFilter;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 * @author Huanggang
 * 
 */
public class CMSContentController extends MultiActionController {

	/**
	 * cms 服务
	 */
	private CmsService cmsService;

	/**
	 * 查询内容的大小,已json格式返回前台ajax显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView handlerQueryContentSize(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CmsNewsSearchFilter filter = new CmsNewsSearchFilter();
		filter.setAuthor(request.getParameter("author"));
		filter.setFromDate(request.getParameter("fromDate"));
		filter.setToDate(request.getParameter("toDate"));
		filter.setHeadline(request.getParameter("headline"));
		String status = request.getParameter("status");
		if (Asserts.assertStringIsNull(status))
			status = "-1";
		filter.setStatus(Integer.parseInt(status));
		
		String type = request.getParameter("type");
		if (!Asserts.assertStringIsNull(type) && Integer.parseInt(type)>0)
			filter.setType(Integer.parseInt(type));
		
		int rowCount = Integer.valueOf(5);
		String pageIndex = request.getParameter("pageIndex");
		if (Asserts.assertStringIsNull(pageIndex))
			filter.setBeginRow(0);
		else
			filter.setBeginRow(Integer.parseInt(pageIndex) * rowCount);
		filter.setRowCount(rowCount);
		
		// 用json对象处理，返回查询的条数size
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("jsonObjSize", this.cmsService.getNewsCount(filter));

		// 进行header缓存设置
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String cbk = request.getParameter("callback");
		// 判断是否为跨域处理
		if (cbk != null && cbk.length() != Integer.valueOf(0)) {
			//跨域处理
			String callback = cbk + "(" + jsonObj.toString() + ")";
			response.getWriter().print(callback);
		}else{
			response.getWriter().write(jsonObj.toString());
		}
	
		response.getWriter().flush();
		response.getWriter().close();

		return null;
	}

	/**
	 * 查询cms内容发布系统，进行分页处理后的数据显示,以json数据处理，通过ajax前后台整合
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView handlerQueryContent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CmsNewsSearchFilter filter = new CmsNewsSearchFilter();
		filter.setAuthor(request.getParameter("author"));
		filter.setFromDate(request.getParameter("fromDate"));
		filter.setToDate(request.getParameter("toDate"));
		filter.setHeadline(request.getParameter("headline"));
		String status = request.getParameter("status");
		if (Asserts.assertStringIsNull(status))
			status = "-1";
		filter.setStatus(Integer.parseInt(status));
		
		
		String type = request.getParameter("type");
		if (!Asserts.assertStringIsNull(type) && Integer.parseInt(type)>0)
			filter.setType(Integer.parseInt(type));
		
		int rowCount = Integer.valueOf(5);
		String pageIndex = request.getParameter("pageIndex");
		if (Asserts.assertStringIsNull(pageIndex))
			filter.setBeginRow(0);
		else
			filter.setBeginRow(Integer.parseInt(pageIndex) * rowCount);
		filter.setRowCount(rowCount);
		
		// 获得新闻资讯信息
		List<CmsNewsPojo> jsonObjList = cmsService.getNewsByFilter(filter);
		// 用json对象处理，返回查询的条数size
		JSONObject jsonObj = new JSONObject();

		jsonObj.put("jsonObjList", jsonObjList);
		// 进行回调跨域处理
		String cbk = request.getParameter("callback");
		
		// 进行header缓存设置
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 判断是否为跨域处理
		if (cbk != null && cbk.length() != Integer.valueOf(0)) {
			//跨域处理
			String callback = cbk + "(" + jsonObj.toString() + ")";
			response.getWriter().print(callback);
		}else{
			response.getWriter().write(jsonObj.toString());
		}
		response.getWriter().flush();
		response.getWriter().close();

		return null;

	}
	
	/**
	 * 获得user对象
	 */
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 用json对象处理，返回查询的条数size
		JSONObject jsonObj = new JSONObject();
		UserVO vo = HttpSessionUtil.getLoginUser(request);
		if (vo != null) {
			jsonObj.put("user", vo);
		}
		// 进行回调跨域处理
		String cbk = request.getParameter("callback");

		// 进行header缓存设置
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 判断是否为跨域处理
		if (cbk != null && cbk.length() != Integer.valueOf(0)) {
			// 跨域处理
			String callback = cbk + "(" + jsonObj.toString() + ")";
			response.getWriter().print(callback);
		} else {
			response.getWriter().write(jsonObj.toString());
		}
		
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	/**
	 * @param cmsService
	 *            the cmsService to set
	 */
	public void setCmsService(CmsService cmsService) {
		this.cmsService = cmsService;
	}

}
