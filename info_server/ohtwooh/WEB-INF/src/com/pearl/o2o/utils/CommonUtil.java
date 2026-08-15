package com.pearl.o2o.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pearl.o2o.pojo.PlayerItem;

public class CommonUtil {
	public static boolean isToday(Date date){
		boolean is=true;
		if(date!=null){
			Date now=new Date();
			if(now.getYear()!=date.getYear()){
				is=false;
			}
			if(now.getMonth()!=date.getMonth()){
				is=false;
			}
			if(now.getDate()!=date.getDate()){
				is=false;
			}
		}else{
			is=false;
		}	
		return is;
	}
	public static int getWeaponSeq(int wId){
		Integer seq;
		switch (wId) {
			case 1:
				seq=2;break;
			case 2:
				seq=1;break;
			case 3:
				seq=1;break;
			case 4:
				seq=1;break;
			case 5:
				seq=1;break;
			case 6:
				seq=1;break;
			case 7:
				seq=1;break;
			case 10:
				seq=3;break;
			case 20:
				seq=4;break;
			case 21:
				seq=5;break;
			case 22:
				seq=6;break;
			default:
				seq=0;break;
		}
		return seq;
	}
	public static int getCotumeSeq(int subType){
		Integer seq;
		switch (subType) {
			case 1:
				seq=1;break;
			case 2:
				seq=2;break;
			case 3:
				seq=3;break;
			case 4:
				seq=4;break;
			case 5:
				seq=5;break;	
			case 6:
				seq=6;break;
			case 8:
				seq=7;break;
			default:
				seq=0;break;
		}
		return seq;
	}
	public static int getListPages(List list,int size){
		int pages=0;
		if(list==null){
			list=new ArrayList();
		}
		if(list.size()%size==0){
			pages=list.size()/size;
		}else{
			pages=list.size()/size+1;
		}
		return pages;
	}
	public static boolean isContain(List<PlayerItem> list,PlayerItem obj){
		boolean is=false;
		for(PlayerItem object:list){
			if((int)object.getId()==obj.getId()){
				is=true;
				break;
			}
		}
		return is;
		
	}
	public static String arrayToString(String[] array){
		String str="";
		if(array!=null){
			for(int i=0;i<array.length;i++){
				str+=array[i]+",";
			}
		}
		return str.substring(0,str.length()-1);
	}
	public static String cutLastWord(String input){
		String returnValue="";
		if(!"".equals(input)){
			returnValue=input.substring(0, input.length()-1);
		}
		return returnValue;
	}
	public static String messageFormat(String regx,Object args[]){
		return MessageFormat.format(regx,args);
	}
	public static String messageFormat(String regx,Object args){
		return MessageFormat.format(regx,args);
	}
	
}
