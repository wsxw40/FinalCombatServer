/**
 * 
 */
package com.pde.uweb.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Huanggang
 * 
 */
public class HttpServletReqWrapper extends  javax.servlet.http.HttpServletRequestWrapper {

	private String sessionId;

	public HttpServletReqWrapper(HttpServletRequest request, String sessionId) {
		super(request);
		this.sessionId = sessionId;

	}

	@Override
	public HttpSession getSession() {
		return new HttpSessionCustomWrapper(super.getSession(), this.sessionId);
	}

	@Override
	public HttpSession getSession(boolean create) {

		return new HttpSessionCustomWrapper(super.getSession(create), this.sessionId);

	}

}
