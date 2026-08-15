package com.pearl.o2o.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

public class KeywordFilterUtil {
	//inner list hold all key words
	private static List<String> keywords = new ArrayList<String>();
	//replace string, avoid to generate every time, set the max length of keywords to it
	private static int replaceStrLength = 13;
	private static String[] replaceStrs = new String[replaceStrLength];
	
	private static final Logger logger = Logger.getLogger(KeywordFilterUtil.class);
	
	static{
		try {
			loadKeyWords();
		} catch (IOException e) {
			logger.fatal("fail to load key words file!!");
			throw new RuntimeException(e);
		}
	}
	
	public static List<String> getKeywordsList(){
		return keywords;
	}
	
	private static void loadKeyWords() throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader fr = null;
		try{
			fr = new BufferedReader(new InputStreamReader(new FileInputStream(ConfigurationUtil.KEYWORD_FILE_PATH),"UTF-8"));
			int c = (char) fr.read();
			while(c != -1){
				sb.append((char)c);
				c =  fr.read();
			}
		}finally{
			fr.close();
		}
		String [] words = sb.toString().split("\\s+");
		keywords = Collections.unmodifiableList(Arrays.asList(words));
		
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
			if (input.indexOf(keyword) != -1){
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
	//REFACTOR 
	public static String filter(String input){
		for (String keyword : keywords ) {
			if (input.indexOf(keyword) != -1) {//avoid generate too many string object when invoke the replaceAll
				input = input.replaceAll(keyword, getReplaceStr(keyword.length()));
			}
		}
		return input;
	}
}
