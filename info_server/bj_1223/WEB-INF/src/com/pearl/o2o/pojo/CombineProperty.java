package com.pearl.o2o.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 镶嵌的属性类
 * @author wuxiaofei
 */
public class CombineProperty {
	public static final String OPENED 	= "0;";
	public static final String SLOTTED 	= "1;";
	public static final String UNOPEN	= "";
	
	public static Map<String, double[]> strengthenAppendMap = new HashMap<String, double[]>();

	public final static List<Property> firstWeaponLevel1Pool = new ArrayList<Property>();
	public final static List<Property> firstWeaponLevel2Pool = new ArrayList<Property>();
	public final static List<Property> firstWeaponLevel3Pool = new ArrayList<Property>();
	
	public final static List<Property> secondWeaponLevel1Pool = new ArrayList<Property>();
	public final static List<Property> secondWeaponLevel2Pool = new ArrayList<Property>();
	public final static List<Property> secondWeaponLevel3Pool = new ArrayList<Property>();
	
	public final static List<Property> thridWeaponLevel1Pool = new ArrayList<Property>();
	public final static List<Property> thridWeaponLevel2Pool = new ArrayList<Property>();
	public final static List<Property> thridWeaponLevel3Pool = new ArrayList<Property>();
	
	public final static List<Property> clothLevel1Pool = new ArrayList<Property>();
	public final static List<Property> clothLevel2Pool = new ArrayList<Property>();
	public final static List<Property> clothLevel3Pool = new ArrayList<Property>();
	
	public final static List<Property> engineerCorpsLevel1Pool = new ArrayList<Property>();
	public final static List<Property> engineerCorpsLevel2Pool = new ArrayList<Property>();
	public final static List<Property> engineerCorpsLevel3Pool = new ArrayList<Property>();
	
	public final static Map<String, Map<Integer, Integer>> KeyValueMapMap = new HashMap<String, Map<Integer, Integer>>();
	public final static Map<String, List<Property>> PropertyListMap = new HashMap<String, List<Property>>();
	/**
	 *
	 */
	public final static int[][][] COMBINE_PROPERTYS = {
		{//主武器属性池
			//属性1 属性2 time
			{ 1, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
//			{ 3, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 4, 1, 2, 2, 1, 1, 12, 12, 1, 4, 4, 1, 1, 6, 6, 1, 6, 6, 1, 1, 4, 4},
			{ 5, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 6, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 7, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 8, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 9, 1, 5, 15, 0, 0, 20, 20, 1, 10, 30, 0, 0, 20, 20, 1, 15, 45, 0, 0, 20, 20},
			{ 10, 1, 5, 15, 0, 0, 20, 20, 1, 10, 30, 0, 0, 20, 20, 1, 15, 45, 0, 0, 20, 20},
//			{ 11, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 31, 1, 15, 21, 1, 1, 0, 0, 1, 7, 11, 1, 1, 0, 0, 1, 5, 7, 1, 1, 0, 0},
			{ 34, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 37, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
//			{ 38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{ 45, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			
//			{ 74, 1, 4, 10, 0, 0, 0, 0, 1, 8, 20, 0, 0, 0, 0, 1, 19, 37, 0, 0, 0, 0},
//			{ 75, 1, 4, 10, 0, 0, 0, 0, 1, 8, 20, 0, 0, 0, 0, 1, 19, 37, 0, 0, 0, 0},
//			{ 77, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 14, 26, 0, 0, 0, 0},
//			{ 78, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 14, 26, 0, 0, 0, 0},
//			{ 81, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 5, 11, 0, 0, 0, 0},
//			{ 82, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 5, 11, 0, 0, 0, 0},
		},
		{//副武器属性池
			{ 1, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 4, 1, 2, 2, 1, 1, 12, 12, 1, 4, 4, 1, 1, 6, 6, 1, 6, 6, 1, 1, 4, 4},
			{ 5, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 6, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 7, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 8, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 9, 1, 5, 15, 0, 0, 20, 20, 1, 10, 30, 0, 0, 20, 20, 1, 15, 45, 0, 0, 20, 20},
			{ 10, 1, 5, 15, 0, 0, 20, 20, 1, 10, 30, 0, 0, 20, 20, 1, 15, 45, 0, 0, 20, 20},
			{ 31, 1, 15, 21, 1, 1, 0, 0, 1, 7, 11, 1, 1, 0, 0, 1, 5, 7, 1, 1, 0, 0},
			{ 34, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 37, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 45, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
		},
		{//近身武器属性池
			{ 1, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
//			{ 3, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 4, 1, 2, 2, 1, 1, 12, 12, 1, 4, 4, 1, 1, 6, 6, 1, 6, 6, 1, 1, 4, 4},
			{ 5, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 6, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 7, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 8, 1, 1, 3, 0, 0, 5, 5, 1, 2, 6, 0, 0, 5, 5, 1, 3, 9, 0, 0, 5, 5},
			{ 9, 1, 5, 15, 0, 0, 20, 20, 1, 10, 30, 0, 0, 20, 20, 1, 15, 45, 0, 0, 20, 20},
			{ 10, 1, 5, 15, 0, 0, 20, 20, 1, 10, 30, 0, 0, 20, 20, 1, 15, 45, 0, 0, 20, 20},
//			{ 11, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 31, 1, 15, 21, 1, 1, 0, 0, 1, 7, 11, 1, 1, 0, 0, 1, 5, 7, 1, 1, 0, 0},
			{ 34, 1, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 2, 6, 1, 0, 0, 0, 0, 3, 9},
			{ 37, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
//			{ 38, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{ 45, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
		},
		{//服装帽子配饰属性池
			{ 60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{ 61, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{ 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
			{ 66, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 9, 21, 0, 0, 0, 0},
//			{ 67, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 9, 21, 0, 0, 0, 0},
//			{ 68, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 9, 21, 0, 0, 0, 0},
//			{ 69, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 9, 21, 0, 0, 0, 0},
//			{ 70, 1, 3, 7, 0, 0, 0, 0, 1, 6, 14, 0, 0, 0, 0, 1, 9, 21, 0, 0, 0, 0},
			{ 71, 1, 1, 3, 0, 0, 0, 0, 1, 1, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
//			{ 72, 1, 6, 14, 0, 0, 0, 0, 1, 12, 18, 0, 0, 0, 0, 1, 18, 42, 0, 0, 0, 0},
			{ 73, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 76, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 79, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 83, 1, 4, 10, 0, 0, 0, 0, 1, 8, 20, 0, 0, 0, 0, 1, 12, 30, 0, 0, 0, 0},
//			{ 91, 1, 1, 1, 0, 0, 0, 0, 1, 1, 3, 0, 0, 0, 0, 1, 1, 5, 0, 0, 0, 0},
			{ 94, 1, 15, 21, 1, 1, 0, 0, 1, 7, 11, 1, 1, 0, 0, 1, 5, 7, 1, 1, 0, 0},
			
			{ 110, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			{ 111, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			{ 112, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			{ 113, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			{ 114, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			{ 115, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			
			{ 122, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 5, 11, 0, 0, 0, 0},
			{ 123, 1, 2, 4, 0, 0, 0, 0, 1, 4, 8, 0, 0, 0, 0, 1, 6, 12, 0, 0, 0, 0},
			
		},
		{//工程兵主武器属性池
			{ 116, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 117, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 118, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 119, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 120, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 121, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			
			{ 200, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 201, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 202, 1, 10, 10, 0, 0, 0, 0, 1, 20, 20, 0, 0, 0, 0, 1, 30, 30, 0, 0, 0, 0},
			{ 203, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 204, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},
			{ 205, 1, 1, 3, 0, 0, 0, 0, 1, 2, 6, 0, 0, 0, 0, 1, 3, 9, 0, 0, 0, 0},

		}
		
	};
	//{ 39, 1, 4, 10, 0, 0, 0, 0, 1, 8, 20, 0, 0, 0, 0, 1, 19, 37, 0, 0, 0, 0},

	
	public static Map<Integer, Integer>  propertyConvertMap=new HashMap<Integer, Integer>();       //一般主武器属性与工程兵主武器属性转换对应关系
	static {
		for (int i = 0; i < COMBINE_PROPERTYS.length; i++) {
			for (int j = 0; j < COMBINE_PROPERTYS[i].length; j++) {
				int[] eachArray = COMBINE_PROPERTYS[i][j];
				int propertyId = eachArray[0];
				if (eachArray[1] == 1) {
					Property property = new Property(propertyId, eachArray[2], eachArray[3], eachArray[4], eachArray[5], eachArray[6], eachArray[7]);
					if (i == 0) {
						firstWeaponLevel1Pool.add(property);
					} else if(i == 1) {
						secondWeaponLevel1Pool.add(property);
					} else if (i == 2) {
						thridWeaponLevel1Pool.add(property);
					} else if (i == 3) {
						clothLevel1Pool.add(property);
					} else if (i==4){
						engineerCorpsLevel1Pool.add(property);
					}
				}
				if (eachArray[8] == 1) {
					Property property = new Property(propertyId, eachArray[9], eachArray[10], eachArray[11], eachArray[12], eachArray[13], eachArray[14]);
					if (i == 0) {
						firstWeaponLevel2Pool.add(property);
					} else if(i == 1) {
						secondWeaponLevel2Pool.add(property);
					} else if (i == 2) {
						thridWeaponLevel2Pool.add(property);
					} else if (i == 3) {
						clothLevel2Pool.add(property);
					} else if (i == 4) {
						engineerCorpsLevel2Pool.add(property);
					}
					
				}
				if (eachArray[15] == 1) {
					Property property = new Property(propertyId, eachArray[16], eachArray[17], eachArray[18], eachArray[19], eachArray[20], eachArray[21]);
					if (i == 0) {
						firstWeaponLevel3Pool.add(property);
					} else if(i == 1) {
						secondWeaponLevel3Pool.add(property);
					} else if (i == 2) {
						thridWeaponLevel3Pool.add(property);
					} else if (i == 3) {
						clothLevel3Pool.add(property);
					} else if (i==4) {
						engineerCorpsLevel3Pool.add(property);
					}
				}
			}
		}
		
		Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		for(Property pro : firstWeaponLevel1Pool){
			map1.put(firstWeaponLevel1Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_1_1", map1);
		PropertyListMap.put("1_1_1", firstWeaponLevel1Pool);
		
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		for(Property pro : firstWeaponLevel2Pool){
			map2.put(firstWeaponLevel2Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_1_2", map2);
		PropertyListMap.put("1_1_2", firstWeaponLevel2Pool);
		
		Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();
		for(Property pro : firstWeaponLevel3Pool){
			map3.put(firstWeaponLevel3Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_1_3", map3);
		PropertyListMap.put("1_1_3", firstWeaponLevel3Pool);

		Map<Integer, Integer> map16 = new HashMap<Integer, Integer>();
		for(Property pro : secondWeaponLevel1Pool){
			map16.put(secondWeaponLevel1Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_2_1", map16);
		PropertyListMap.put("1_2_1", secondWeaponLevel1Pool);
		
		Map<Integer, Integer> map17 = new HashMap<Integer, Integer>();
		for(Property pro : secondWeaponLevel2Pool){
			map17.put(secondWeaponLevel2Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_2_2", map17);
		PropertyListMap.put("1_2_2", secondWeaponLevel2Pool);
		
		Map<Integer, Integer> map18 = new HashMap<Integer, Integer>();
		for(Property pro : secondWeaponLevel3Pool){
			map18.put(secondWeaponLevel3Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_2_3", map18);
		PropertyListMap.put("1_2_3", secondWeaponLevel3Pool);
		
		Map<Integer, Integer> map4 = new HashMap<Integer, Integer>();
		for(Property pro : thridWeaponLevel1Pool){
			map4.put(thridWeaponLevel1Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_3_1", map4);
		PropertyListMap.put("1_3_1", thridWeaponLevel1Pool);
		
		Map<Integer, Integer> map5 = new HashMap<Integer, Integer>();
		for(Property pro : thridWeaponLevel2Pool){
			map5.put(thridWeaponLevel2Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_3_2", map5);
		PropertyListMap.put("1_3_2", thridWeaponLevel2Pool);
		
		Map<Integer, Integer> map6 = new HashMap<Integer, Integer>();
		for(Property pro : thridWeaponLevel3Pool){
			map6.put(thridWeaponLevel3Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("1_3_3", map6);
		PropertyListMap.put("1_3_3", thridWeaponLevel3Pool);


		Map<Integer, Integer> map7 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel1Pool){
			map7.put(clothLevel1Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("2_1_1", map7);
		PropertyListMap.put("2_1_1", clothLevel1Pool);
		
		Map<Integer, Integer> map8 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel2Pool){
			map8.put(clothLevel2Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("2_1_2", map8);
		PropertyListMap.put("2_1_2", clothLevel2Pool);
		
		Map<Integer, Integer> map9 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel3Pool){
			map9.put(clothLevel3Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("2_1_3", map9);
		PropertyListMap.put("2_1_3", clothLevel3Pool);

		Map<Integer, Integer> map10 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel1Pool){
			map10.put(clothLevel1Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("3_1_1", map10);
		PropertyListMap.put("3_1_1", clothLevel1Pool);
		
		Map<Integer, Integer> map11 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel2Pool){
			map11.put(clothLevel2Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("3_1_2", map11);
		PropertyListMap.put("3_1_2", clothLevel2Pool);
		
		Map<Integer, Integer> map12 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel3Pool){
			map12.put(clothLevel3Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("3_1_3", map12);
		PropertyListMap.put("3_1_3", clothLevel3Pool);

		Map<Integer, Integer> map13 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel1Pool){
			map13.put(clothLevel1Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("3_2_1", map13);
		PropertyListMap.put("3_2_1", clothLevel1Pool);
		
		Map<Integer, Integer> map14 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel2Pool){
			map14.put(clothLevel2Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("3_2_2", map14);
		PropertyListMap.put("3_2_2", clothLevel2Pool);
		
		Map<Integer, Integer> map15 = new HashMap<Integer, Integer>();
		for(Property pro : clothLevel3Pool){
			map15.put(clothLevel3Pool.indexOf(pro), 1);
		}
		KeyValueMapMap.put("3_2_3", map15);
		PropertyListMap.put("3_2_3", clothLevel3Pool);
		
		//key 为一般主武器属性， value 为对应的工程兵特殊属性
		propertyConvertMap.put(10,200);
		propertyConvertMap.put(1,201);
		propertyConvertMap.put(4,202);
		propertyConvertMap.put(31,203);
		propertyConvertMap.put(34,204);
		propertyConvertMap.put(45,205);
		propertyConvertMap.put(6,116);
		propertyConvertMap.put(37,117);
		propertyConvertMap.put(7,118);
		propertyConvertMap.put(5,119);
		propertyConvertMap.put(8,120);
		propertyConvertMap.put(9,121);

		
	}
	
	static class Property {
		public Property(int id, int param1Min, int param1Max, int param2Min, int param2Max, int param3Min, int param3Max){
			this.id = id;
			this.param1Min = param1Min;
			this.param1Max = param1Max;
			this.param2Min = param2Min;
			this.param2Max = param2Max;
			this.param3Min = param3Min;
			this.param3Max = param3Max;
		}
		private final int id;
		private final int param1Min;
		private final int param1Max;
		private final int param2Min;
		private final int param2Max;
		private final int param3Min;
		private final int param3Max;
		
		public int getId() {
			return id;
		}
		public int getParam1Min() {
			return param1Min;
		}
		public int getParam1Max() {
			return param1Max;
		}
		public int getParam2Min() {
			return param2Min;
		}
		public int getParam2Max() {
			return param2Max;
		}
		public int getParam3Min() {
			return param3Min;
		}
		public int getParam3Max() {
			return param3Max;
		}
		/**
		 * Property to ?,?,?,?
		 * @param pro
		 * @return
		 */
		public String property2String(){
			StringBuilder sb = new StringBuilder();
			int value = CommonUtil.getRandomBetween2Numbers(param1Min, param1Max);
			int value1 = CommonUtil.getRandomBetween2Numbers(param2Min, param2Max);
			int time = CommonUtil.getRandomBetween2Numbers(param3Min, param3Max);
			return sb.append(id).append(",").append(value).append(",").append(value1).append(",").append(time).toString();
		}
	}
	
	/**
	 * input item's type and subType and diamand's level
	 * return ?,?,?,?
	 * @param type
	 * @param subType
	 * @param level
	 * @return
	 */
	public static String getProperty(int type, int subType, int level) throws BaseException{
		StringBuilder sb = new StringBuilder();
		sb.append(type).append("_").append(subType).append("_").append(level);
		String key = sb.toString();
		Map<Integer, Integer> map = KeyValueMapMap.get(key);
		if(map == null){
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_RETRY);
		}
		int index = CommonUtil.getRandomValueByWeights(map);
		Property pro = PropertyListMap.get(key).get(index);
		if(pro == null){
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_RETRY);
		}
		return pro.property2String();
	}
	//                                                     0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
	private static int[] ConvertPlayerItemLoseLevelRate = {0,0,0,0,0,0,0,0,0,0,10,15,20,25,30,40};
	
	/**
	 * @return true 掉一级，false 不掉级
	 */
	public static boolean getConvertPlayerItemLoseLevelResult(int level,int currRareLevel,int anotherRareLevel){
		int rate = calcConvertPlayerItemLoseRate(level,currRareLevel,anotherRareLevel);
		if(rate == 0){
			return false;
		}else if(rate == 100){
			return true;
		}
		return new Random().nextInt(100) < rate;
	}
	
	public static int calcConvertPlayerItemLoseRate(int level,int currRareLevel,int anotherRareLevel){
		int result = 0;
		if(level>0){
			int rate1 = ConvertPlayerItemLoseLevelRate[level];
			int rate2 = anotherRareLevel - currRareLevel;
			result = rate1 + rate2;
			result = Math.min(100, result);
			result = Math.max(0, result);
		} 
		return result;
	}
	
	public static int getConvertPlayerItemLoseRate(int fromLevel,int toLevelint ,int fromRareLevel,int toRareLevel){
		int rateFrom = CombineProperty.calcConvertPlayerItemLoseRate(fromLevel,fromRareLevel,toRareLevel);
		int rateTo = CombineProperty.calcConvertPlayerItemLoseRate(toLevelint,toRareLevel,fromRareLevel);
		int rate = (rateFrom*100 + rateTo*100 - rateFrom*rateTo)/100;
		return rate;
	}
	/**
	 * 获取特定级别的特定属性的值
	 * @param type 主武器属性--0，副武器属性--1，近身武器属性--2，服饰属性--3，工程兵属性--4
	 * @param index 某种属性ID
	 * @param level 1/2/3级
	 * @return 
	 */
	public static int[] getSpecificProperty(int type,int index, int level){
		int[] properties=new int[6];
		String twoLevelIndex=useIndexNumGetIndexInCombineArray(index);
	
		if(twoLevelIndex.contains(",")){
			int indexInArray=Integer.parseInt(twoLevelIndex.split(",")[1]);
	
			int[] allProperties=COMBINE_PROPERTYS[type][indexInArray];
			
			for(int i=(level-1)*7+2;i<level*7+1;i++){
				properties[(i-2)%7]=allProperties[i];
			
			}
		}
		
		return properties;
	}
	/**
	 * 根据属性ID值，获取第一条（由于ID值在数组中不唯一）该属性在combine_propertys数组的下标
	 * @param indexNum 属性ID值
	 * @return
	 */
	
	private static String useIndexNumGetIndexInCombineArray(int indexNum){
		StringBuffer sBuffer=new StringBuffer();
		for(int i=0;i<COMBINE_PROPERTYS.length;i++){
			
			for(int j=0;j<COMBINE_PROPERTYS[i].length;j++){
				
				if(indexNum==COMBINE_PROPERTYS[i][j][0]){
					sBuffer.append(i).append(",").append(j);
					return sBuffer.toString();  //由于有些相同属性，故找到就退出
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args){
		for(Entry<String, List<Property>> entry : PropertyListMap.entrySet()){
			String key = entry.getKey();
			System.out.println("******************************** key = " + key + " ***********************************");
			if(entry.getValue().size() == 0){
				System.out.println(entry.getValue());
			}
			for(Property pro : entry.getValue()){
				int id = pro.getId();
				int valueMin = pro.getParam1Min();
				int valueMax = pro.getParam1Max();
				int value1Min = pro.getParam2Min();
				int value1Max = pro.getParam2Max();
				int timeMin = pro.getParam3Min();
				int timeMax = pro.getParam3Max();
				System.out.println("id=" + id + " " + CommonUtil.getPropertyStr(id, CommonUtil.getRandomBetween2Numbers(valueMin, valueMax), CommonUtil.getRandomBetween2Numbers(value1Min, value1Max), CommonUtil.getRandomBetween2Numbers(timeMin, timeMax)));
			}
		}
//		for(Entry<String, List<Property>> entry : PropertyListMap.entrySet()){
//			Map<Integer, Integer> keyValueMap = KeyValueMapMap.get(entry.getKey());
//			String key = entry.getKey();
//			System.out.println("******************************** key = " + key + " ***********************************");
//			for(int i=0; i<10000; i++){
//				int index = CommonUtil.getRandomValueByWeights(keyValueMap);
//				System.out.println(entry.getValue().get(index).property2String());
//			}
//		}
	}
	

}
