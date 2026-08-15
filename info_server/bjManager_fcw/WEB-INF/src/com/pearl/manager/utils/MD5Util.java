package com.pearl.manager.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static MessageDigest md5;

	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String md5(String str, String charset) {
		StringBuilder sb = new StringBuilder();
		try {
			byte[] bb = md5.digest(str.getBytes(charset));
			for (byte b : bb) {
				sb.append(hexDigits[b >>> 4 & 0xf]);
				sb.append(hexDigits[b & 0xf]);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static String md5(String str) {
		return md5(str, "UTF-8").toUpperCase();
	}
	
	public static void main(String[] args){
		String encodeStr = MD5Util.md5("20120510103800" + "222" + "SB_00002" + "1111" +"xldcf000001"+ "xlyouxi@pde");
		System.out.println(encodeStr);
	}
}
