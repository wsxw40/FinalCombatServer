/**
 * 
 */
package com.pde.uweb.framework.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * cookies处理
 * 
 * @author Huanggang
 * 
 */
public class HttpCookiesUtil {

	/**
	 * 根据cookies 的name获取对应的value
	 * 
	 * @param request
	 * @param cookiesName
	 *            cookies名字
	 * @return 如果不存在，返回null。
	 */
	public static String getCookies(HttpServletRequest request, String cookiesName) {

		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		// 判断是否存在cookies，也可以判断是否为该浏览器第一次请求
		if (cookies != null && cookies.length > 0) {

			for (int i = 0; i < cookies.length; i++) {

				sCookie = cookies[i];
				if (sCookie.getName().equals(cookiesName)) {
					return sCookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 设置cookies,
	 * 
	 * @param response
	 * @param name
	 *            名称
	 * @param value
	 * @param path
	 */
	public static String initCookies(HttpSession session,HttpServletResponse response, String name, String path) {

		String sid =session.getId();
		Cookie cookies = new Cookie(name, sid);
		cookies.setPath(path);
		response.addCookie(cookies);
		return sid;

	}

}
