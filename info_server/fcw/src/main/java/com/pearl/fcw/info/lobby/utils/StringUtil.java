package com.pearl.fcw.info.lobby.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return filter(input, Constants.ILLEGAL_CHARACTER);
    }

    public static final boolean filterNoSpace(String input) {
        return filter(input, Constants.ILLEGAL_CHARACTER_NO_SPACE);
    }

    public static final boolean filter(String input, String regEx) {
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(input);
        boolean rs = mat.find();
        return rs;
    }

    public static final String stringToAscii(String input) throws UnsupportedEncodingException {
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
     * @param string "1,2,3,4"或者"1,2,3,4,"
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
     * @param string "1,2,3,4"或者"1,2,3,4,"
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

    private static final Pattern SQL_VALUE_REPLACE_PATTERN = Pattern.compile("([_%'\"\\\\])");

    public static String escapeSQLValue(String value) {
        if (value == null) {
            return null;
        }
        Matcher m = SQL_VALUE_REPLACE_PATTERN.matcher(value);
        return m.replaceAll("\\\\$1");
    }

}
