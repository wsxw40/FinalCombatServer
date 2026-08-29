package com.pde.info.analyser.common;

public class StringHelper {
	public static final int  BIGGER_THAN=1;
	public static final int  LESS_THAN=-1;
	public static final int  EQUAL=0;
	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param separateString
	 * @return 0: equal; 1: s1>s2; -1: s1<s2;
	 */
	public static int CompareDateString(String beginDate,String endDate,String separateString) throws Exception{
		final String[] beginDates=beginDate.split(separateString);
		final String[] endDates=endDate.split(separateString);
		
		final int minlength=beginDates.length>endDates.length ? endDates.length : beginDates.length;
		
		
		for(int i=0;i<minlength;i++){
			//just compare number
			if(beginDates[i].matches("^\\d+$")&&endDates[i].matches("^\\d+$")){
				if(Integer.parseInt(beginDates[i])==Integer.parseInt(endDates[i])){
					continue;
				}else{
					return Integer.parseInt(beginDates[i])>Integer.parseInt(endDates[i]) ? BIGGER_THAN : LESS_THAN;
				}
			}else{
				throw new Exception("Just can compare date written by number!");
			}
		}
		
		return EQUAL;
	}
	
	
	public static void main(String args[]){
		try {
			System.out.println(CompareDateString("2013-06-07", "2013-06-21", "-"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
