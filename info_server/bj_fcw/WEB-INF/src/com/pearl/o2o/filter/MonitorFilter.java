package com.pearl.o2o.filter;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;



public class MonitorFilter implements Filter {
	static final Logger LOG = LoggerFactory.getLogger(MonitorFilter.class);	
	
	public void init(FilterConfig fc){
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException{
		HttpServletRequest request = (HttpServletRequest) req;
		//log all request parameters
		if (LOG.isDebugEnabled()){
			LOG.debug("invoke url:" + request.getRequestURL() + "  <<  " + req.getRemoteAddr() + ":" + req.getRemotePort());
			Iterator<?> i = req.getParameterMap().keySet().iterator();
			while(i.hasNext()){
				String key = (String) i.next();
				LOG.debug("[" + key + "]" + " - " + req.getParameter(key) + " ");
			}
		}
		//log cost time
		long start,end;
		start 	= System.currentTimeMillis();
		try{
			chain.doFilter(req, res);
		}finally{
			end = System.currentTimeMillis();
			long duration = end - start;
			LOG.debug("finish " + request.getRequestURL() + " : " + duration + "ms");
		}
		
	}
	
	public void destroy(){
		
	}
}
