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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pde.uweb.framework.web.HttpCookiesUtil;
import com.pde.uweb.framework.web.HttpServletReqWrapper;

/**
 * // 生成会话session Id，保证整个网站的会话层ID是唯一的，随后通过ajax回馈到客户端。 //
 * 由于实行的是动静分离，客户端html运行在apache中，html通过ajax与服务器交互。
 * 
 * @author Huanggang
 * 
 */
public class SessionFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5653756712403538040L;

	private String sessionId;

	private String cookieDomain;

	private String cookiePath;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
			ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		HttpSession httpSession=request.getSession(true);

		String localSessionId = HttpCookiesUtil.getCookies(request, this.sessionId);

		if (localSessionId == null || localSessionId.length() == Integer.valueOf(0)) {

			localSessionId = HttpCookiesUtil.initCookies(httpSession,response, this.sessionId, cookiePath);

		}

		filterChain.doFilter(new HttpServletReqWrapper(request, localSessionId), servletResponse);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		this.sessionId = filterConfig.getInitParameter("sessionId");

		if (this.sessionId == null || this.sessionId.length() == 0) {
			this.sessionId = "sid";
		}
		this.cookieDomain = filterConfig.getInitParameter("cookieDomain");
		if (this.cookieDomain == null) {
			this.cookieDomain = "";
		}

		this.cookiePath = filterConfig.getInitParameter("cookiePath");
		if (this.cookiePath == null || this.cookiePath.length() == 0) {
			this.cookiePath = "/";
		}

	}

}
