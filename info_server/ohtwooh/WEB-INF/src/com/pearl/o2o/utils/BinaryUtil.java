package com.pearl.o2o.utils;

import java.io.UnsupportedEncodingException;


public class BinaryUtil {
	public static byte[] toByta(byte data) {
	    return new byte[]{data};
	}		
	
	public static byte[] toByta(char data) {
	    return new byte[] {
		    (byte)((data >> 0) & 0xff),	    		
	        (byte)((data >> 8) & 0xff),
	    };
	}	
	
	public static char toChar(byte[] data){
		if (data.length < 2){
			throw new IllegalArgumentException("the byte's length is error when try to convert to char");
		}
		int result = data[1] & 0xff;
		result = (result << 8) |( data[0] & 0xff); 
		
		return (char) result;
	}
	
	public static byte[] toByta(short data) {
	    return new byte[] {
		    (byte)((data >> 0) & 0xff),	    		
	        (byte)((data >> 8) & 0xff),
	    };
	}
	
	public static short toShort(byte[] data){
		if (data.length < 2){
			throw new IllegalArgumentException("the byte's length is error when try to convert to short");
		}
		int result = data[1] & 0xff;
		result = (result << 8) |( data[0] & 0xff); 
		
		return (short) result;
	}
	
	
	public static byte[] toByta(int data) {
	    return new byte[] {
		    (byte)((data >> 0) & 0xff),
	        (byte)((data >> 8) & 0xff),
	        (byte)((data >> 16) & 0xff),	        
	        (byte)((data >> 24) & 0xff),
	    };
	}		
	
	public static int toInt(byte[] data){
		if (data.length < 4){
			throw new IllegalArgumentException("the byte's length is error when try to convert to int");
		}
		int result =  data[3] & 0xff;
		result = ((result << 8) | (data[2] & 0xff));
		result = ((result << 8) | (data[1] & 0xff));
		result = ((result << 8) | (data[0] & 0xff));
		
		return result;
	}
	
	
	public static byte[] toByta(int[] data) {
	    if (data == null) return null;
	    // ----------
	    byte[] byts = new byte[data.length * 4];
	    for (int i = 0; i < data.length; i++)
	        System.arraycopy(toByta(data[i]), 0, byts, i * 4, 4);
	    return byts;
	}	
	
	public static byte[] toByta(float data) {
	    return toByta(Float.floatToRawIntBits(data));
	}	
	
	public static float toFloat(byte[] data){
		int temp = toInt(data);
		return Float.intBitsToFloat(temp);
	}
	
	public static byte[] toByta(String data) {
		if(data==null){
			return null;
		}
		else{
			int length;
			try {
				length = data.getBytes("GBK").length;
				byte[] byts = new byte[length+4];
				System.arraycopy(toByta(data.getBytes("GBK").length), 0, byts, 0, 4);
				System.arraycopy(data.getBytes("GBK"), 0, byts, 4, length);
				return byts;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}		
	}
	
	public static String toString(byte[] data){
		if (data.length < 4){
			throw new IllegalArgumentException("the byte's length is error when try to convert to String");
		}
		int strLength =  data[3] & 0xff;
		strLength = ((strLength << 8) | (data[2] & 0xff));
		strLength = ((strLength << 8) | (data[1] & 0xff));
		strLength = ((strLength << 8) | (data[0] & 0xff));
		
		if (data.length -4 != strLength) {
			throw new IllegalArgumentException("the byte's length is error when try to convert to String");
		}
		try {
			return new String(data,4,data.length - 4,"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}