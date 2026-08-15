package com.pearl.manager.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class CommonUtil {
	public static String toTwoDecimalString(double decimal) {
		return String.format("%.2f", decimal);
	}

	public static double toTwoDecimal(double decimal) {
		NumberFormat numberFormat = DecimalFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		return Double.valueOf(numberFormat.format(decimal));
	}

	public static float toTwoFloat(double decimal) {
		NumberFormat numberFormat = DecimalFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		return Float.valueOf((numberFormat.format(decimal)));
	}

	public static float toFourFloat(double decimal) {
		NumberFormat numberFormat = DecimalFormat.getInstance();
		numberFormat.setMaximumFractionDigits(4);
		return Float.valueOf((numberFormat.format(decimal)));
	}
}
