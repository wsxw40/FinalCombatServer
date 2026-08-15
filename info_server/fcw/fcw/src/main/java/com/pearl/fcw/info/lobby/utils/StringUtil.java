package com.pearl.fcw.info.lobby.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pearl.fcw.info.lobby.pojo.SysSuit;

public class StringUtil {

	public static String getIpByUrl(String url) {
		url = url.toLowerCase();
		url = url.replace("http://", "");
		url = url.substring(0, url.indexOf("/") - 1);
		return url;
	}

	public static boolean isEmptyString(String input) {
		return input == null || input.length() == 0;
	}

	public static final long toLong(String input) {
		if (!isEmptyString(input)) {
			return Long.parseLong(input.trim());
		}
		throw new IllegalArgumentException();
	}

	public static final int toInt(String input) {
		if (!isEmptyString(input)) {
			return Integer.parseInt(input.trim());
		}
		throw new IllegalArgumentException();
	}

	public static String trim(String input) {
		return isEmptyString(input) ? "" : input.trim();
	}

	public static final boolean filter(String input) {
		String regEx = "[\'\" ~!@#$%^&*()+`{}|\\\\,\\./<>?;:]";
		return filter(input, regEx);
	}

	public static final boolean filter(String input, String regEx) {
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		return rs;
	}

	public static final String stringToAscii(String input)
			throws UnsupportedEncodingException {
		byte[] str = input.getBytes(Constants.ENCODING);
		StringBuffer sb = new StringBuffer();
		for (byte b : str) {
			int c = b & 0xff;
			sb.append("\\").append(c);
		}
		return sb.toString();
	}

	public static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	/**
	 * @param string
	 *            "1,2,3,4"或者"1,2,3,4,"
	 */
	public static List<Long> strToLonList(String string) {
		List<Long> list = new ArrayList<Long>();
		if (!isEmptyString(string)) {
			for (String s : string.split(",")) {
				list.add(toLong(s));
			}
		}
		return list;
	}

	/**
	 * @param string
	 *            "1,2,3,4"或者"1,2,3,4,"
	 */
	public static List<Integer> strToIntList(String string) {
		List<Integer> list = new ArrayList<Integer>();
		if (!isEmptyString(string)) {
			for (String s : string.split(",")) {
				list.add(toInt(s));
			}
		}
		return list;
	}

	public static final int lengthByte(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		try {
			return s.getBytes(Constants.ENCODING).length;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private static final int CHAR_BUF_SIZE = 256;

	private static final char[] cbuf = new char[CHAR_BUF_SIZE];

	public static long getUTFLength(String s) {
		int len = s.length();
		long utflen = 0;
		for (int off = 0; off < len;) {
			int csize = Math.min(len - off, CHAR_BUF_SIZE);
			s.getChars(off, off + csize, cbuf, 0);
			for (int cpos = 0; cpos < csize; cpos++) {
				char c = cbuf[cpos];
				if (c >= 0x0001 && c <= 0x007F) {
					utflen++;
				} else {
					utflen += 2;
				}
			}
			off += csize;
		}
		return utflen;
	}

	private static final Pattern SQL_VALUE_REPLACE_PATTERN = Pattern
			.compile("([_%'\"\\\\])");

	public static String escapeSQLValue(String value) {
		if (value == null) {
			return null;
		}
		Matcher m = SQL_VALUE_REPLACE_PATTERN.matcher(value);
		return m.replaceAll("\\\\$1");
	}

	public static final int getIntParam(String input, int defaultValue) {
		int output = 0;
		if ("".equals(input) || input == null)
			return defaultValue;
		try {
			if (input != null) {
				output = Integer.parseInt(input.trim());
			}
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		return output;
	}

	/**
	 * 
	 * @param string
	 * @param type
	 *            0:正整数,1:正负整数,2:正浮点数,3:正负浮点数
	 * @return
	 */

	public static boolean isAllNumber(String string, int... types) {
		String parten = "^\\d+$";
		String parten1 = "^[-]?\\d+$";
		String parten2 = "^\\d+\\.\\d+$";
		String parten3 = "^[-]?\\d+\\.\\d+$";
		if (types.length >= 1) {
			int type = types[0];

			switch (type) {
			case 0:
				return string.matches(parten);
			case 1:
				return string.matches(parten1);
			case 2:
				return string.matches(parten2);
			case 3:
				return string.matches(parten3);

			default:
				return string.matches(parten);
			}

		} else {
			return string.matches(parten);
		}
	}

	/**
	 * if one of string in numStrings cann't convert to int, it will return a
	 * array which the length is numStrings.length+1 so if the return array's
	 * length is inconsistent with numStrings's length, it means convert fail
	 * 
	 * @param numStrings
	 * @return
	 */
	public static int[] convertToInt(String[] numStrings) {
		int[] intArray = new int[numStrings.length];
		for (int i = 0; i < numStrings.length; i++) {
			if (isAllNumber(numStrings[i])) {
				intArray[i] = Integer.parseInt(numStrings[i]);
			} else {
				return new int[numStrings.length + 1];
			}
		}

		return intArray;
	}

	public static final String[] getStringArrayParam(String input,
			String[] defaultValue) {
		if ("".equals(input))
			return defaultValue;

		return input.split("\\|");
	}

	public static HashMap<Integer, SysSuit> getDefaultSuit(String suitProString) {

		HashMap<Integer, SysSuit> hashMap = new HashMap<Integer, SysSuit>();
		String[] allSuitProString = StringUtil.getStringArrayParam(
				suitProString, new String[] { "" });
		for (String s : allSuitProString) {
			String[] values = s.split(";", 2);
			if (StringUtil.isAllNumber(values[0].trim())) {
				try {
					hashMap.put(Integer.valueOf(values[0].trim()), new SysSuit(
							values[0].trim(), values[1], ";"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return hashMap;
				}
			}
		}
		return hashMap;
	}

	public static final String escape(String input) {
		String regEx = "[\'\"~!@#$%^&*()+`{}|\\\\,./<>?;:]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);
		boolean rs = mat.find();
		if (rs) {
			return mat.replaceAll("").trim();
		} else {
			return input;
		}
	}

	public static final String escape(String input, String replaceString)
			throws Exception {
		String regEx = "[\\^\\$\\*\\+\\|\\.\\?]";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(input);

		boolean rs = mat.find();
		if (rs) {
			try {
				input = mat.replaceAll("\\" + mat.group()).trim();
				return input;
			} catch (Exception e) {
				throw e;
			}
		} else {
			return input;
		}
	}

	/**
	 * 用于替换字符串中指定位置的字符，如果指定位置大于字符串的长度，则替换最后一个字符
	 * 
	 * @param str
	 *            <code>目标字符串</code>
	 * @param index
	 *            <code>指定位置</code>
	 * @param c
	 *            <code>替换字符</code>
	 * @return {@link java.lang.String}
	 */
	public static String replaceByIndex(String str, int index, char c) {
		if (str.length() <= index) {
			index = str.length() - 1;
		}
		char[] chars = str.toCharArray();
		chars[index] = c;
		return String.valueOf(chars);

	}
}
