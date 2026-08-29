/**
 * 
 */
package com.pde.uweb.framework.web;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author Huanggang
 * 
 */
public class HttpSessionCustomWrapper extends HttpSessionWrapper {

	/**
	 * 会话编号
	 */
	private String sessionId;

	/**
	 * 会话编号
	 */
	private Map<String, Object> map;

	public HttpSessionCustomWrapper(HttpSession session, String sessionId) {
		super(session);
		this.sessionId = sessionId;
		this.map = HttpSessionService.getInstance().getSession(sessionId);

	}

	@Override
	public Object getAttribute(String name) {
		return this.map.get(name);
	}

	@Override
	public Enumeration getAttributeNames() {
		return (new Enumerator(this.map.keySet(), true));
	}

	@Override
	public void removeAttribute(String name) {
		this.map.remove(name);
		HttpSessionService.getInstance().removeAttribute(this.sessionId, name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		this.map.put(name, value);
		HttpSessionService.getInstance().saveSession(this.sessionId, name, value);
	}

	@Override
	public void invalidate() {
		this.map.clear();
		HttpSessionService.getInstance().removeSession(this.sessionId);
	}

	/* (non-Javadoc)
	 * @see com.pde.uweb.framework.web.HttpSessionWrapper#getId()
	 */
	@Override
	public String getId() {
	 
	 return this.sessionId;
	}
	
	

}
