package com.pearl.o2o.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pearl.o2o.service.GetService;
import com.pearl.o2o.utils.Converter;

public class LoginFilter implements Filter {
	GetService getService;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		if ("on".equals(getService.getSysConfig().get("sdo.switch"))) {
			res.getWriter().write(Converter.error("access denined"));
		}else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		getService = (GetService) WebApplicationContextUtils.getWebApplicationContext(config.getServletContext()).getBean("getService");
	}
}
