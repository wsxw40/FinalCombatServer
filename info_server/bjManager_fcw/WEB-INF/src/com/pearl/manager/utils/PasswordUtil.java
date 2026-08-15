package com.pearl.manager.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

public class PasswordUtil {
	static Logger log = LoggerFactory.getLogger(PasswordUtil.class.getName());
	
	/**
	 * 生成随即密码
	 * @param pwd_len 生成的密码的总长度
	 * @return  密码的字符串
	 */
	public static String genRandom(int pwd_len){
		 
		//35是因为数组是从0开始的，26个字母+10个数字
		final int  maxNum = 36;
		int i;  //生成的随机数
		int count = 0; //生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
		    'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		    'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		  
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < pwd_len){
		   
			//生成随机数，取绝对值，防止生成负数，
			i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1
				  
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count ++;
			}
		}
		  
		return pwd.toString();
	}
	
	
	public static String encrypt(String password){
		MessageDigest md = null;
    	String algorithm = "SHA";
    	
	    try{
	    	md = MessageDigest.getInstance(algorithm); //step 2
	    }
	    catch(NoSuchAlgorithmException e){
	    	log.error("Error while encrypting password - No "+algorithm+" found: ", e);
	    }
	    
	    try{
	    	md.update(password.getBytes("UTF-8")); //step 3
	    }
	    catch(UnsupportedEncodingException e){
	    	log.error("Error while encrypting password - Unsupported Encoding:", e);
	    }
	    
	    byte raw[] = md.digest(); //step 4
	    String hash = (new BASE64Encoder()).encode(raw); //step 5
	    return hash; //step 6
	  }	
	
	public static String getEncode(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes("utf-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args){
		System.out.println(PasswordUtil.getEncode("我的天"));
	}
}
