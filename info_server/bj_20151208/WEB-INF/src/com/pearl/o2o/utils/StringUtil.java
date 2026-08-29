package com.pearl.o2o.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pearl.o2o.pojo.SysSuit;

public class StringUtil {

	public static final String encoding(String input) {
		try {
			byte[] bytes = input.getBytes("ISO-8859-1");
			return new String(bytes, "utf-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null; 
	}
	public static final String printfString(String[] paramValues) {
		StringBuilder sb=new StringBuilder();
		if(paramValues!=null){
			for(String str:paramValues){
				sb.append(str).append(",");
			}
		}
		return sb.toString(); 
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
	public static final int getIntParam(String input,int defaultValue) {
		int output = 0;
		if("".equals(input) || input==null) 
			return defaultValue;
		try{
			if (input != null) {
				output = Integer.parseInt(input.trim());
			}
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		return output;
	}
	public static final String getStringParam(String input,String defaultValue) {
		if("".equals(input)) 
			return defaultValue;
		
		return input;
	}
	public static final String[] getStringArrayParam(String input,String[] defaultValue) {
		if("".equals(input)) 
			return defaultValue;
		
		return input.split("\\|");
	}
	public static final boolean filter(String input) {
		String	regEx = "[\'\" ~!@#$%^&*()+`{}|\\\\,\\./<>?;:]";
		return filter(input, regEx);
	}
	
	public static final boolean filter(String input, String regEx) {
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		return rs;
	}
	public static final String escapeIndex(String input){
		String	regEx = "[\'\"*+`{}|\\\\/<>]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		
		if(rs){
			return mat.replaceAll("").trim();
		}else{
			return input;
		}
		
	}
	public static final String escape(String input){
		String	regEx = "[\'\"~!@#$%^&*()+`{}|\\\\,./<>?;:]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		if(rs){
			return mat.replaceAll("").trim();
		}else{
			return input;
		}
	}
	public static final String escapeEnter(String input){
		String	regEx = "[\n]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		if(rs){
			return mat.replaceAll("").trim();
		}else{
			return input;
		}
	}
	public static final String escape(String input,String replaceString) throws Exception{
		String	regEx = "[\\^\\$\\*\\+\\|\\.\\?]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		
		boolean rs = mat.find();
		if(rs){
			try {
				input=mat.replaceAll("\\"+mat.group()).trim();
				return input;
			} catch (Exception e) {
				throw e;
			}
		}else{
			return input;
		}
	}
	public static final String stringToAscii(String input) throws UnsupportedEncodingException{
		byte[] str=input.getBytes("UTF-8");
		StringBuffer sb=new StringBuffer();
		for(byte b:str){
			int c =b&0xff;
			sb.append("\\").append(c);
		}
		return sb.toString();
	}
	public static final String asciiToString(String input) throws UnsupportedEncodingException{
		return null;
	}
	
	
	public static final String updateModifiedDesc(String input,int index,String output){
		return input.substring(0,index-1)+output+input.substring(index);
	}
	@SuppressWarnings("unchecked")
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
	/**
	 * 用于替换字符串中指定位置的字符，如果指定位置大于字符串的长度，则替换最后一个字符
	 * @param str <code>目标字符串</code>
	 * @param index <code>指定位置</code>
	 * @param c <code>替换字符</code>
	 * @return {@link java.lang.String}
	 */
	public static String replaceByIndex(String str,int index,char c){
		if(str.length()<=index){
			index = str.length() -1;
		}
		char[] chars = str.toCharArray();
		chars[index] = c;
		return String.valueOf(chars);
		
	}
	/**
	 * 
	 * @param string
	 * @param type 0:正整数,1:正负整数,2:正浮点数,3:正负浮点数
	 * @return
	 */
	
	public static boolean isAllNumber(String string,int... types){
		String parten="^\\d+$";
		String parten1="^[-]?\\d+$";
		String parten2="^\\d+\\.\\d+$";
		String parten3="^[-]?\\d+\\.\\d+$";
		if(types.length>=1){
			int type=types[0];
			
			switch (type) {
			case 0: return string.matches(parten);
			case 1: return string.matches(parten1);
			case 2: return string.matches(parten2);
			case 3: return string.matches(parten3);

			default:
				return string.matches(parten);
			}
			
		}else{
			return string.matches(parten);
		}
	}
	/**
	 * if one of string in numStrings cann't convert to int, it will return
	 * a array which the length is numStrings.length+1
	 * so if the return array's length is inconsistent with numStrings's length,
	 * it means convert fail 
	 * @param numStrings
	 * @return
	 */
	public static int[] convertToInt(String[] numStrings){
		int[] intArray=new int[numStrings.length];
		for(int i=0;i<numStrings.length;i++){
			if(isAllNumber(numStrings[i])){
				intArray[i]=Integer.parseInt(numStrings[i]);
			}else{
				return new int[numStrings.length+1];
			}
		}
	
		return intArray;
	}
	
	public static HashMap<Integer,SysSuit> getDefaultSuit(String suitProString){
		
		HashMap<Integer,SysSuit> hashMap=new HashMap<Integer, SysSuit>();
		String[] allSuitProString=StringUtil.getStringArrayParam(suitProString,new String[]{""});
		for(String s: allSuitProString ){
			String[] values=s.split(";",2);
			if(StringUtil.isAllNumber(values[0].trim())){
				try {
					hashMap.put(Integer.valueOf(values[0].trim()), new SysSuit(values[0].trim(),values[1],";"));
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return hashMap;
				}
			}
		}
		return hashMap;
	}
	
	
//	public static void main(String[] args) throws UnsupportedEncodingException {
//		System.out.println(isCanEquip(null));
//		
//	}
}
