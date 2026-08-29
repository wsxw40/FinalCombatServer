package com.pearl.o2o.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

public class KeywordFilterUtil {
	//inner list hold all key words
	private static List<String> keywords = new ArrayList<String>();
	private static String regex="";
	//replace string, avoid to generate every time, set the max length of keywords to it
	private static int replaceStrLength = 14;
	private static String[] replaceStrs = new String[replaceStrLength];
	
	private static final Logger logger = LoggerFactory.getLogger(KeywordFilterUtil.class);
	
	static{
		try {
			loadKeyWords();
		} catch (IOException e) {
			logger.error("fail to load key words file!!",e);
			throw new RuntimeException(e);
		} catch (Exception e) {
			logger.error("fail to load key words file!!",e);
			throw new RuntimeException(e);
		}
	}
	
	public static List<String> getKeywordsList(){
		return keywords;
	}
	static class ComparatorLength implements Comparator<String>{
		public int compare(String s1, String s2) {
			return ((Integer)s2.length()).compareTo((Integer)s1.length());
		  } 
	}
	public static void loadKeyWords() throws Exception{
		/*StringBuilder sb = new StringBuilder();
		BufferedReader fr = null;
		try{
			fr = new BufferedReader(new InputStreamReader(new FileInputStream(ConfigurationUtil.KEYWORD_FILE_PATH),"UTF-8"));
			int c = (char) fr.read();
			while(c != -1){
				sb.append((char)c);
				c =  fr.read();
			}
			
		
		String [] words = sb.toString().split("\\s+");
		Set<String> strSet=new HashSet<String>();
		Comparator<String> compare=new ComparatorLength();
		for(String word:words){
			strSet.add(word);
		}
		List<String> list=new ArrayList<String>(strSet);
		System.out.println(list.size());
		Collections.sort(list,compare);
		try {
			File file=new File("1.txt");
			FileWriter fw = new FileWriter(file);
			int i=1;
			for(String word:list){
				i++;
				if(i==11){
					i=1;
					fw.write(word+"  \r\n");
				}else{
				fw.write(word+"  ");
				}
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<String> strList=Arrays.asList(words);
		
		Collections.sort(strList,compare);
		
		keywords = Collections.unmodifiableList(strList);*/
		
		keywords = ServiceLocator.getService.getBannedWordsStrList();
		initRegEx();
		//initialize replace string
		for (int i=1; i <= replaceStrLength; i++) {
			StringBuilder sbb = new StringBuilder();
			for(int j=0; j<i; j++){
				sbb.append(ConfigurationUtil.KEYWORD_REPLACE_CHAR);
			}
			replaceStrs[i-1] =  sbb.toString();
		}
	}
	
	
	/**
	 * use to judge whether input string contains key words
	 * @param input
	 * @return
	 */
	public static boolean isLegalInput(String input){
		for (String keyword : keywords ) {
			if (input.indexOf(keyword) != -1&&!"".equalsIgnoreCase(keyword)){
				return false;
			}
		}
		return true;
	}
	
	private static String getReplaceStr(int length){
		if (length < replaceStrs.length) {
			return replaceStrs[length-1];
		}else {
			return replaceStrs[replaceStrs.length-1];
		}
	}
	@SuppressWarnings("unused")
	private static String getReplaceStr1(String s){
		if(s.equals("\n")){
			return "\\\\n";
		}else if(s.equals("\r")){
			return "\\\\r";
		}else if(s.equals("\\\\")){
			return "\\\\\\\\";
		}else if(s.equals("\\\\r")){
			return "\\\\\\\\r";
		}else {
			return "";
		}
//		
	}
	private static void initRegEx() throws Exception{
		String regEx="(";
		for (String keyword : keywords ) {
			if(!"".equals(keyword)){
				if(StringUtil.filter(keyword)){
					regEx+=StringUtil.escape(keyword, "")+"|";
				}else{
					regEx+=keyword+"|";
				}
			}
		}
		regEx=regEx.substring(0, regEx.length()-1);
		regEx+=")";
		regex=regEx;
	}
	//REFACTOR 
	public static String filter(String input){
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(input);
		StringBuffer sbr = new StringBuffer();
		while(matcher.find()){
			String key=matcher.group();
			matcher.appendReplacement(sbr, getReplaceStr(key.length())); 
		}
		matcher.appendTail(sbr); 
		return sbr.toString();
	}
}
