package com.pearl.o2o.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.o2o.service.GetService;

public class IpFilter implements Filter {
	private String[] allow;
	private String[] deny;
	private FilterConfig filterConfig = null;
	GetService getService;
	private String getIpAddr(HttpServletRequest request) { 
		 if (request.getHeader("x-forwarded-for") == null) {  
	         return request.getRemoteAddr();  
	        }  
	        return request.getHeader("x-forwarded-for");  

	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		String clientAddr = getIpAddr((HttpServletRequest)request);

		if (hasMatch(clientAddr, deny)) {
			handleInvalidAccess(request, response, clientAddr);
			return;
		}

		if ((allow.length > 0) && !hasMatch(clientAddr, allow)) {
			handleInvalidAccess(request, response, clientAddr);
			return;
		}

		chain.doFilter(request, response);
	}

	private void handleInvalidAccess(ServletRequest request,ServletResponse response, String clientAddr) throws IOException {
		((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	private boolean hasMatch(String clientAddr, String[] regExps) {
		for (int i = 0; i < regExps.length; i++) {
			if (clientAddr.matches(regExps[i]))
				return true;
		}

		return false;
	}
	public void destroy() {
		this.filterConfig = null;
		this.allow = null;
		this.deny = null;
	}

	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		this.allow = extractRegExps(filterConfig.getInitParameter("allow"));
		this.deny = extractRegExps(filterConfig.getInitParameter("deny"));
		getService = (GetService) WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext()).getBean("getService");
	}

	private String[] extractRegExps(String initParam) {
		if (initParam == null) {
			return new String[0];
		} else {
			return initParam.split(",");
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	public String toString() {
		if (filterConfig == null)
			return ("ClientAddrFilter()");
		StringBuffer sb = new StringBuffer("ClientAddrFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

}
