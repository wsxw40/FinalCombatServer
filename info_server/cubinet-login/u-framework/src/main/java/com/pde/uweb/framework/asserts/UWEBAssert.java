package com.pde.uweb.framework.asserts;

public class UWEBAssert {
	
	/** 判断对象是否非空（为空则抛错） */
	public static <T> void assertNotNull(T t, String errorMsg) {
		if (t == null) {
			throw new NullPointerException(errorMsg);
		}
	}

	/** 判断表达式是否为真（为假则抛错） */
	public static <T> void assertExpression(boolean expression, String errorMsg) {
		if (!expression) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

	/** 判断字符串是否非空（为空则抛错） */
	public static void assertStringNotNull(String str) {
		assertStringNotNull(str, "字符串不允许为空");
	}

	/** 判断字符串是否非空（为空则抛错） */
	public static void assertStringNotNull(String str, String message) {
		if (str == null || str.trim().length()==Integer.valueOf(0)) {
			throw new IllegalArgumentException(message);
		}
	}

	/** 判断字符串是否为空（不抛错） */
	public static boolean assertStringIsNull(String str) {
		return (str==null || str.trim().length()==Integer.valueOf(0));
	}
}
