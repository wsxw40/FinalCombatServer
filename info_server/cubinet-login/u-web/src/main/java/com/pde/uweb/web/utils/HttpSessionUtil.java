package com.pde.uweb.web.utils;

import javax.servlet.http.HttpServletRequest;

import com.pde.uweb.framework.constants.HttpSessionConstant;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 * @author Huanggang
 * 
 */
public final class HttpSessionUtil {

	/**
	 * 从session获取已经登录的user对象
	 */
	public static UserVO getLoginUser(HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(HttpSessionConstant.UWEB_LOGIN_USER);
		return obj!=null ? (UserVO) obj : null;
	}

	/**
	 * 往session设置成功登录的user对象
	 */
	public static void setLoginUser(HttpServletRequest request, Object userPojo) {
		request.getSession().setAttribute(HttpSessionConstant.UWEB_LOGIN_USER, userPojo);
	}

	/**
	 * 从session中删除已登录用户
	 */
	public static void removeLoginUser(HttpServletRequest request) {
		request.getSession().removeAttribute(HttpSessionConstant.UWEB_LOGIN_USER);
	}

	/**
	 *  获得当前请求的 session id
	 */
	public static String getSessionId(HttpServletRequest request) {
		return request.getSession().getId();
	}

}
