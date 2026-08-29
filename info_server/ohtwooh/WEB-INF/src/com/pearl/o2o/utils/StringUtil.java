package com.pearl.o2o.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static final String encoding(String input) {
		try {
			byte[] bytes = input.getBytes("ISO-8859-1");
			return new String(bytes, "GBK");
		} catch (Exception ex) {
		}

		return null;
	}
	
	
	public static boolean isEmptyString(String input){
		return input == null || "".equals(input.trim());
	}

	public static final int toInt(String input) {
		int output = 0;
		try{
			if (input != null) {
				output = Integer.parseInt(input.trim());
			}
		} catch (NumberFormatException e) {
			throw e;
		}
		return output;
	}

	public static final boolean filter(String input) {
		String	regEx = "[\'\" ~!@#$%^&*()+`{}|\\,./<>?;:]";
		return filter(input, regEx);
	}
	
	public static final boolean filter(String input, String regEx) {
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		return rs;
	}
	
	public static final String escape(String input){
		String	regEx = "[\'\" ~!@#$%^&*()+`{}|\\,./<>?;:]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		if(rs){
			return mat.replaceAll("").trim();
		}else{
			return input;
		}
	}
	
	
	public static final String updateModifiedDesc(String input,int index,String output){
		return input.substring(0,index-1)+output+input.substring(index);
	}
	public static final List toList(String input){
		List list=new ArrayList();
		String tempArray[]=input.split(Constants.DELIMITER_RESOURCE_STABLE);
		for(int i=0;i<tempArray.length;i++)
		{
			list.add(tempArray[i]);
		}
		return list;
	}
	public static final boolean isCanEquip(String input,int index){
		String str=input.substring(index-1,index);
		if("0".equals(str)){
			return false;
		}else{
			return true;
		}
	}
	public static final boolean isCanEquip(String input){
		if(input!=null){
			if(input.indexOf("1")!=-1){
				return true;
			}else if(input.indexOf("2")!=-1){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	public static final String getUnitString(int input){
		return input==1?"":String.valueOf(input);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(isCanEquip(null));
		
	}
}
