package com.pde.uweb.bbs.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pde.infor.common.utils.Base64;

/**
 * bbs 内置函数调用
 * 
 * @author Huanggang
 * 
 */
public abstract class BBSDiscuzFunction {

	private static final Logger logger = Logger.getLogger(BBSDiscuzFunction.class);

	protected String urlEncoder(String value) {

		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
		}
		return null;
	}

	protected String md5(String input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		return byte2hex(md.digest(input.getBytes()));
	}

	protected String md5(long input) {
		return md5(String.valueOf(input));
	}

	protected String base64Decode(String input) {
		try {
			return new String(Base64.decode(input.toCharArray()), "iso-8859-1");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	protected String base64Encode(String input) {
		try {
			return new String(Base64.encode(input.getBytes("iso-8859-1")));
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	protected String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString();
	}

	protected String substr(String input, int begin, int length) {
		return input.substring(begin, begin + length);
	}

	protected String substr(String input, int begin) {
		if (begin > 0) {
			return input.substring(begin);
		} else {
			return input.substring(input.length() + begin);
		}
	}

	protected long microtime() {
		return System.currentTimeMillis();
	}

	protected long time() {
		return System.currentTimeMillis() / 1000;
	}

	protected String sprintf(String format, long input) {
		String temp = "0000000000" + input;
		return temp.substring(temp.length() - 10);
	}

	/**
	 * @param function
	 * @param model
	 * @param action
	 * @param args
	 * @return
	 */
	protected String callUserFunction(String function, String model, String action, Map<String, Object> args) {
		if ("uc_api_mysql".equals(function)) {
			String temp = this.apiMysql(model, action, args);

			return temp;
		}
		if ("uc_api_post".equals(function)) {
			String temp = this.apiPost(model, action, args);

			return temp;
		}
		return "";
	}

	/**
	 * 直接发送post http请求
	 * 
	 * @param module
	 *            请求板块
	 * @param action
	 *            处理的action
	 * @param arg
	 * @return
	 */
	public abstract String apiPost(String module, String action, Map<String, Object> arg);

	/**
	 * 直接调用对方的mysql数据库
	 * 
	 * @param model
	 *            请求板块
	 * @param action
	 *            action 处理的action
	 * @param args
	 * @return
	 */
	public abstract String apiMysql(String model, String action, Map<String, Object> args);

}
