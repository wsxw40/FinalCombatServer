package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.StringUtil;

public final class SysSuit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6051291494644191486L;
	private final int suitId;
	private final HashSet<Property> weapon4Pros ;
	private final HashSet<Property> clothes4Pros;
	private final HashSet<Property> weapon6Pros;
	private final HashSet<Property> clothes6Pros;
	/**
	 * for show
	 */
	private final ArrayList<Property> allSpec4Pros;
	private final ArrayList<Property> allSpec6Pros;

	public SysSuit(String id, String content, String divChar) throws Exception {
		this.suitId = Integer.valueOf(id);
		
		weapon4Pros = new HashSet<SysSuit.Property>();
		clothes4Pros = new HashSet<SysSuit.Property>();
		weapon6Pros = new HashSet<SysSuit.Property>();
		clothes6Pros = new HashSet<SysSuit.Property>();
		allSpec4Pros=new ArrayList<SysSuit.Property>();
		allSpec6Pros=new ArrayList<SysSuit.Property>();
		
		String[] allPros = content.split(divChar);
		for (String pro : allPros) {
			String[] allproVal = pro.split(":", 3);
			Property property = toProperty(allproVal[2], ":");
			if (property != null) {
				if (allproVal[0] .equals("1")) {
					if (allproVal[1] .equals( "4")) {
						weapon4Pros.add(property);
						weapon6Pros.add(property);
						allSpec4Pros.add(property);
					} else if (allproVal[1] .equals( "6")) {
						weapon6Pros.add(property);
						allSpec6Pros.add(property);
					} else {
						throw new Exception("Must be 4 or 6 suit !");
					}

				} else if (allproVal[0] .equals( "2")) {
					if (allproVal[1].equals( "4")) {
						clothes4Pros.add(property);
						clothes6Pros.add(property);
						allSpec4Pros.add(property);
					} else if (allproVal[1] .equals( "6")) {
						clothes6Pros.add(property);
						allSpec6Pros.add(property);
					} else {
						throw new Exception("Must be 4 or 6 suit !");
					}
				} else {
					throw new Exception("invalid suit type String!");
				}
			}else{
				throw new Exception("invalid property!");
			}
		}
	}

	public int getSuitId() {
		return suitId;
	}

	public HashSet<Property> getWeapon4Pros() {
		return weapon4Pros;
	}

	public HashSet<Property> getClothes4Pros() {
		return clothes4Pros;
	}

	public HashSet<Property> getWeapon6Pros() {
		return weapon6Pros;
	}

	public HashSet<Property> getClothes6Pros() {
		return clothes6Pros;
	}
 
	public ArrayList<Property> getAllSpec4Pros() {
		return allSpec4Pros;
	}

	public ArrayList<Property> getAllSpec6Pros() {
		return allSpec6Pros;
	}

	public Property toProperty(String proString, String divChar) {
		String[] proValues = proString.split(divChar);
		int proValuesLength = proValues.length;
		if (proValuesLength < 2) {
			return null;
		} else {
			int[] proIntValues = StringUtil.convertToInt(proValues);
			if (proIntValues.length == proValuesLength) {
				switch (proValuesLength) {
				case 2:
					return new Property(proIntValues[0], proIntValues[1], 0, 0);
				case 3:
					return new Property(proIntValues[0], proIntValues[1],
							proIntValues[2], 0);
				case 4:
					return new Property(proIntValues[0], proIntValues[1],
							proIntValues[2], proIntValues[3]);
				default:
					return new Property(proIntValues[0], proIntValues[1],
							proIntValues[2], proIntValues[3]);
				}
			} else {
				return null;
			}

		}
	}

	public HashSet<Property> getSpecSet(int type){
		switch (type) {
		case 1:
			return weapon4Pros;
		case 2:
			return clothes4Pros;
		case 3:
			return weapon6Pros;
		case 4:
			return clothes6Pros;
		default:
			return null;
		
		}
	}
	
	
	public static class Property implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5349269400904206680L;
		private int proId;
		private int value1;
		private int value2;
		private int time;
		private String desc="";

		public Property(int proId, int value1, int value2, int time) {
			this.proId = proId;
			this.value1 = value1;
			this.value2 = value2;
			this.time = time;
			
		}

		public int getProId() {
			return proId;
		}

		public void setProId(int proId) {
			this.proId = proId;
		}

		public int getValue1() {
			return value1;
		}

		public void setValue1(int value1) {
			this.value1 = value1;
		}

		public int getValue2() {
			return value2;
		}

		public void setValue2(int value2) {
			this.value2 = value2;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public String getDesc() {
			this.desc= CommonUtil.getPropertyStr(proId, value1, value2, time);
			return desc;
		}

		public String toString() {
			return proId + "," + value1 + "," + value2 + "," + time;
		}

	}
	
//	public static void main(String[] args) {
//		String suitProString="101;1:4:45:6:0:0;2:4:73:6:0:0;2:6:61:6:0:0;2:6:63:0:0:0| 102;1:4:45:6:0:0;2:4:73:6:0:0;2:6:61:6:0:0;2:6:60:0:0:0";
//		HashMap<Integer, SysSuit> map=getDefaultSuit(suitProString);
//		Iterator<Integer> keyIterator=map.keySet().iterator();
//		System.err.println(map.size());
//		while(keyIterator.hasNext()){
//			int key=keyIterator.next();
//			SysSuit ss=map.get(key);
//			System.err.println("key"+key);
//			for(int i=1;i<5;i++){
//				HashSet<Property> properties=ss.getSpecSet(i);
//				Iterator<Property> iterator=properties.iterator();
//				while(iterator.hasNext()){
//					System.out.println(iterator.next().toString());
//				}
//			}
//		}
//	}
}
