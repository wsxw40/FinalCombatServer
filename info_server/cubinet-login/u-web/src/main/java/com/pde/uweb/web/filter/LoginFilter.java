/**
 * 
 */
package com.pde.uweb.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 * @author Huanggang
 * 
 */
public class LoginFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		UserVO userVo = HttpSessionUtil.getLoginUser(request);
		if (userVo == null) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("loginResult", false);
			String cbk = request.getParameter("callback");
			// 进行header缓存设置
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			// 判断是否为 js跨域回调处理
			if (cbk != null && cbk.length() != Integer.valueOf(0)) {
				String callback = cbk + "(" + jsonObj.toString() + ")";
				response.getWriter().print(callback);
			} else {
				response.getWriter().write(jsonObj.toString());
			}
			response.getWriter().flush();
			response.getWriter().close();
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {}

}
