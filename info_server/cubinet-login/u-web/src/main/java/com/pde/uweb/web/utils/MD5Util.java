package com.pde.uweb.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  MD5 生成工具类
 * 
 *  @author Sean.Weng
 */
public class MD5Util {

	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private static MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String md5(String str, String charset) {
		StringBuffer sb = new StringBuffer(32);
		try {
			byte[] bb = getMessageDigest().digest(str.getBytes(charset));
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
		return md5(str, "UTF-8").toLowerCase();
	}

//	public static void main(String[] args) {
//		
//		final String xx = "partner_id_1"+"游戏账号Account_22223"+"96e79218965eb72c92a549dd5a330112";
//		final String encodeStr = MD5Util.md5(xx);
//		
//		for(int i=0;i<1000;i++){
//			new Thread(){
//				public void run() {
//					for(int j = 0;j<10000;j++){
//						if(!md5(xx).equals(encodeStr)){
//							System.err.println("error");
//						}
//					}
//				};
//			}.start();
//		}
//	}
}
