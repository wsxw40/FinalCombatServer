package com.pearl.o2o.utils;

import java.text.DecimalFormat;

public class NumberUtil {
	public static float numberFormat(float num){
		//FCW 三位小数变四位小数
		DecimalFormat df = new DecimalFormat("####.####");
		return Float.parseFloat(df.format(num));
	}

	//FCW 三位小数变四位小数
	public static DecimalFormat df = new DecimalFormat("0.0000");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double cost=Math.sqrt(9000)*0.1*(1.0/4);
		System.out.println((int)cost);
		System.out.println(Math.sqrt(9000)*0.1*0.25);
	}

}
