package com.pearl.o2o.utils;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.beanutils.PropertyUtils;

import com.google.common.collect.Multiset;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.GeneralStageClearInfo.StageClearBox;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.service.onbuy.PlayerBuyWay;

public class CommonUtil {
	public static DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
	public static DateFormat dateFormatOnlyDate = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dateFormatWeek = new SimpleDateFormat("yyyy-ww");
	public static String I18NCodePref = "<C";
	public static String I18NCodeEnd = ">";
	public static String I18NCodeDeli = "^";
	public static String getLockKey(int playerId) {
		return new StringBuilder(Constants.LOCK_TYPE).append(Constants.DELIMITER).append(playerId).toString();
	}
	public static String getTeamLockKey(int teamId) {
		return  Constants.TEAM_LOCK_TYPE+Constants.DELIMITER+teamId;
	}
	//我 爱 大 冲 峰
	public static int getRandowMapByLevel(int level) {
		final  Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		switch (level) {
		case 1:
			map.put(0,196);
			map.put(1,1000-196);
			break;
		case 2:
			map.put(0,367);
			map.put(1,1000-367);
			break;
		case 3:
			map.put(0,528);
			map.put(1,1000-367);
			break;
		default:
			break;
		}
		int index = CommonUtil.getRandomValueByWeights(map);
		return index;
	}
	
	public static List<StageClearBox> getStageClearBoxList(int index, final Player player, boolean isclear) throws Exception {
		List<StageClearBox> stageClearBoxList = new ArrayList<StageClearBox>();
		// final List<SysItem> boxItemlist=new ArrayList<SysItem>();
		// GeneralStageClearInfo clearInfo=new GeneralStageClearInfo();
		// for(int i=0;i<(index>3?3:index);i++){//only 3 boxs for non-vip
		// StageClearBox scb=clearInfo.new StageClearBox();
		// scb.setType(i+1);
		// scb.setNum(0);
		// SysItem sysItem=getRandomGift(i+1);
		// scb.setSysItem(sysItem);
		// boxItemlist.add(sysItem);
		// stageClearBoxList.add(scb);
		// }
		// // Collections.shuffle(stageClearBoxList);
		// if(player.getIsVip()==1&&isclear){
		// StageClearBox scb=clearInfo.new StageClearBox();
		// scb.setType(4);
		// scb.setNum(0);
		// SysItem sysItem=getRandomGift(4);
		// scb.setSysItem(sysItem);
		// boxItemlist.add(sysItem);
		// stageClearBoxList.add(scb);
		// }
		// Runnable task = new Runnable() {
		// @Override
		// public void run() {
		// for (SysItem si : boxItemlist) {
		// try {
		// ServiceLocator.createService.awardToPlayer(player, si, null);
		// } catch (Exception e) {
		// ServiceLocator.fileLog.warn("Exception in getStageClearBoxList", e);
		// }
		// }
		//
		// }
		// };
		// ServiceLocator.asynTakService.submit(task);
		return stageClearBoxList;
	}

	public static SysItem getRandomGift(int type) throws Exception {

		int ids[] = Constants.giftPool[0];
		int index = (int) Math.round((Math.random() * (ids.length - 1)));
		switch (type) {
		case 1:

			break;
		case 2:
			int rate = (int) (Math.random() * 100);
			if (rate > 93 && rate <= 98) {
				ids = Constants.giftPool[1];
				index = (int) Math.round((Math.random() * (ids.length - 1)));
			} else if (rate > 98) {
				ids = Constants.giftPool[2];
				index = (int) Math.round((Math.random() * (ids.length - 1)));
			}
			break;
		case 3:
			rate = (int) (Math.random() * 100);
			if (rate > 85 && rate <= 95) {
				ids = Constants.giftPool[1];
				index = (int) Math.round((Math.random() * (ids.length - 1)));
			} else if (rate > 95) {
				ids = Constants.giftPool[2];
				index = (int) Math.round((Math.random() * (ids.length - 1)));
			}
			break;
		case 4:
			rate = (int) (Math.random() * 100);
			if (rate > 85 && rate <= 95) {
				ids = Constants.giftPool[1];
				index = (int) Math.round((Math.random() * (ids.length - 1)));
			} else if (rate > 95) {
				ids = Constants.giftPool[2];
				index = (int) Math.round((Math.random() * (ids.length - 1)));
			}
			break;
		default:
			break;
		}

		SysItem si = ServiceLocator.getService.getSysItemByItemId(ids[index]);
		return si;

	}

	public static boolean isToday(Date date) {
		boolean is = true;
		if (date != null) {
			Date now = new Date();
			if (now.getYear() != date.getYear()) {
				is = false;
			}
			if (now.getMonth() != date.getMonth()) {
				is = false;
			}
			if (now.getDate() != date.getDate()) {
				is = false;
			}
		} else {
			is = false;
		}
		return is;
	}
	

	public static int getPostiveNumber(int number) {
		return number > 0 ? number : 0;
	}

	public static int getWeaponSeq(int wId) {
		Integer seq;
		switch (wId) {
		case Constants.WEAPON_PISTOL_ID:
			seq = 2;
			break;
		case Constants.WEAPON_SUBMACHINE_ID:
			seq = 1;
			break;
		case Constants.WEAPON_SHOT_GUN_ID:
			seq = 2;
			break;
		case Constants.WEAPON_MACHINE_GUN_ID:
			seq = 1;
			break;
		case Constants.WEAPON_CURE_GUN_ID:
			seq = 2;
			break;
		case Constants.WEAPON_SIGNAL_GUN_ID:
			seq = 2;
			break;
		case Constants.WEAPON_JQT_GUN_ID:                   //哨兵机枪，主武器
			seq = 1;
			break;
//		case Constants.WEAPON_MED_GUN_ID:				   //治疗机枪塔，特殊武器	
//			seq=4;
//			break;
		case Constants.WEAPON_RIFFLE_ID:
			seq = 1;
			break;
		case Constants.WEAPON_SNIPER_GUN_ID:
			seq = 1;
			break;
		case Constants.WEAPON_MINI_GUN_ID:
			seq = 1;
			break;
		case Constants.WEAPON_MEDITNEEDLE_GUN_ID:
			seq = 1;
			break;
		case Constants.WEAPON_FLAME_GUN_ID:
			seq = 1;
			break;
		case Constants.WEAPON_ROCKET_LUNCHER_ID:
			seq = 1;
			break;
		case Constants.WEAPON_BOW_ID:
			seq = 1;
			break;
		case Constants.WEAPON_KNIFE_ID:
			seq = 3;
			break;
		case Constants.WEAPON_THROW_ID:
			seq = 4;
			break;
		case Constants.WEAPON_FLASH_ID:
			seq = 4;
			break;
		case Constants.WEAPON_SMOKE_ID:
			seq = 4;
			break;
		case Constants.WEAPON_AMMO_GRENADE_ID:
			seq = 1;
			break;
		case Constants.WEAPON_WAR_DRUM:
			seq = 4;
			break;
		case Constants.WEAPON_MILK:
			seq = 4;
			break;
		case Constants.WEAPON_GLASS:
			seq = 4;
			break;
		case Constants.WEAPON_ZYZDZ_EDIT_DEFAULT:  
			seq=1;
			break;
		default:
			seq = 0;
			break;
		}
		return seq;
	}

	public static int getCotumeSeq(int cId) {
		Integer seq;
		switch (cId) {
		case 1:
			seq = 1;
			break;
		case 2:
			seq = 2;
			break;
		case 3:
			seq = 3;
			break;
		default:
			seq = 0;
			break;
		}
		return seq;
	}

	@SuppressWarnings("unchecked")
	public static int getListPages(List list, int size) {
		int pages = 0;
		if (list == null) {
			return pages;
		}
		if (list.size() % size == 0) {
			pages = list.size() / size;
		} else {
			pages = list.size() / size + 1;
		}
		return pages;
	}

	public static int getSubListPages(List list, int type) {
		int size = Constants.PAGE_SIZE[type - 1];
		int pages = 0;
		if (list == null) {
			list = new ArrayList();
		}
		if (list.size() % size == 0) {
			pages = list.size() / size;
		} else {
			pages = list.size() / size + 1;
		}
		return pages;
	}

	public static int getMessageListtPages(List list, int type) {
		int size = Constants.PAGE_SIZE_MESSAGE_ITEM[type - 1];
		int pages = 0;
		if (list == null) {
			list = new ArrayList();
		}
		if (list.size() % size == 0) {
			pages = list.size() / size;
		} else {
			pages = list.size() / size + 1;
		}
		return pages;
	}

	public static int getStrengthSubListPages(List list, int type) {
		int size = Constants.STRENGTH_PAGE_SIZE[type - 1];
		int pages = 0;
		if (list == null) {
			list = new ArrayList();
		}
		if (list.size() % size == 0) {
			pages = list.size() / size;
		} else {
			pages = list.size() / size + 1;
		}
		return pages;
	}

	/**
	 * method to judge if the list contain the PlayerItem by compare the obj.id
	 * 
	 * @param list
	 * @param obj
	 * @return
	 */
	public static boolean isContain(List<PlayerItem> list, PlayerItem obj) {
		boolean is = false;
		for (PlayerItem object : list) {
			if ((int) object.getId() == obj.getId()) {
				is = true;
				break;
			}
		}
		return is;

	}

	public static String arrayToString(String[] array) {
		String str = "";
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				str += array[i] + ",";
			}
		}
		return str.substring(0, str.length() - 1);
	}

	/**
	 * method to cut the string last word
	 * 
	 * @param input
	 * @return
	 */
	public static String cutLastWord(String input) {
		String returnValue = "";
		if (!"".equals(input)) {
			returnValue = input.substring(0, input.length() - 1);
		}
		return returnValue;
	}

	public static String messageFormat(String regx, Object... args) {
		return MessageFormat.format(regx, args);
	}
	
	public static String messageFormat(String regx, Object args) {
		return MessageFormat.format(regx, args);
	}
	/**
	 * 多国语言版			codestyle : |codeNO,argsNum,arg1,arg2,...
	 * @param regx
	 * @param args
	 * @return
	 */
	public static String messageFormatI18N(String regx,Object... args){//code style |
		regx = I18NCodePref +regx + I18NCodeDeli + (args==null?0:args.length);
		for(Object obj :args){
			regx+=I18NCodeDeli+String.valueOf(obj);
		}
		return regx+I18NCodeEnd;
	}
	public static  String messageFormatI18N2(String regx,String pref,int num,Object... args){//code style |
		regx = pref +regx + "^" + num+"^";
		for(Object obj :args){
			regx+="\\\""+obj+"\\\"^";
		}
		return regx+">";
	}
	public static String getPropertyStr(int index, int value, int value2, int time) {
		String valueStr = "";
		String value2Str = "";
		if (value > 0) {
			valueStr += "+" + value;
		} else {
			valueStr += value;
		}
		if (value2 > 0) {
			value2Str += "+" + value2;
		} else {
			value2Str += value2;
		}
		Object args[] = { value, value2, time };
		Object args1[] = { time, value, value2, };
		Object args2[] = { valueStr, value2Str, time };
		Object args3[] = { time, valueStr, value2Str, };
		Object args4[] = { value, value2Str, time };
		String returnValue = "";

		switch (index) {
		case 1:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 2:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 3:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 4:
			String[] allMsgDesc=CommonMsg.GUN_PROPERTY_MSG[index - 1].split(",");
			if(value==2){
				returnValue=messageFormatI18N(allMsgDesc[2], args1);
			}else if(value==4){
				returnValue=messageFormatI18N(allMsgDesc[1], args1);
			}else if(value==6){
				returnValue =messageFormatI18N(allMsgDesc[0], args1);
			}
	//		returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 5:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 6:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 7:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 8:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 9:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 10:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 11:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 12:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 13:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 14:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 30:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 31:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 33:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 34:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 35:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 37:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 38:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 39:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 45:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 46:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 47:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 60:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 61:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 62:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 63:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 64:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 66:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 67:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 68:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 69:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 70:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 71:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 72:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 73:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 74:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 75:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 76:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 77:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 78:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 79:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 80:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 81:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 82:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 83:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 84:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 85:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 86:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 87:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 90:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 91:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 92:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 93:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 94:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args4);
			break;
		case 100:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args3);
			break;
		case 101:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args3);
			break;
		case 102:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;	
		case 110:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 111:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 112:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 113:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 114:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 115:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 116:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 122:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 150:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 151:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 152:
			returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		default: 
			if(CommonMsg.GUN_PROPERTY_MSG[index - 1]!=""){ //工程兵主武器新增属性
				returnValue=messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			}
			break;
		}
		
	
		return returnValue;
	}

	// add unique basePojo objec to list
	@SuppressWarnings("unchecked")
	public static List<BaseMappingBean> addUniqueObject(List list, BaseMappingBean obj) {
		boolean isAdd = true;
		if (list != null && list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				BaseMappingBean pojo = (BaseMappingBean) list.get(i);
				if (pojo.getId() == (obj.getId())) {
					isAdd = false;
					break;
				}
			}
		}
		if (isAdd) {
			list.add(obj);
		}
		return list;
	}

	public static String toTwoDecimalString(double decimal) {
		return String.format("%.2f", decimal);
	}

	public static double toTwoDecimal(double decimal) {
		
//		NumberFormat numberFormat = DecimalFormat.getInstance();
//		numberFormat.setMaximumFractionDigits(2);
		return Double.valueOf((int)(decimal*100)/100.0);
	}

	public static float toTwoFloat(double decimal) {
//		NumberFormat numberFormat = DecimalFormat.getInstance();
//		numberFormat.setMaximumFractionDigits(2);
//		System.out.println(decimal);
		Double d=(int)(decimal*100)/100.0;
		return Float.valueOf(d.floatValue());
	}

	public static float toFourFloat(double decimal) {
//		NumberFormat numberFormat = DecimalFormat.getInstance();
//		numberFormat.setMaximumFractionDigits(4);
		Double d=(int)(decimal*10000)/10000.0;
		return Float.valueOf(d.floatValue());
	}

	public static int minsBetweenTimes() {

		Calendar c = Calendar.getInstance();

		long frontLong = c.getTimeInMillis();

		c.setFirstDayOfWeek(Calendar.MONDAY);

		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		long backLong = c.getTimeInMillis();

		return (int) ((backLong - frontLong) / 60000L);
	}

	public static long millisecondToEndOfToday() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		long end = c.getTimeInMillis();
		return (end - now);
	}

	public static long millisecondToNextday() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.add(Calendar.DAY_OF_MONTH, Constants.NUM_ONE);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long end = c.getTimeInMillis();
		return (end - now);
	}
	
	/**
	 * 到明天两点的时间
	 * @return
	 */
	public static long millisecondToNextday2() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.add(Calendar.DAY_OF_MONTH, Constants.NUM_ONE);
		c.set(Calendar.HOUR_OF_DAY, 2);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		long end = c.getTimeInMillis();
		return (end - now);
	}

	public static int minutesToNextMonday() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 2);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.DAY_OF_WEEK, 2);
		c.set(Calendar.WEEK_OF_MONTH, c.get(Calendar.WEEK_OF_MONTH) + 1);
		long end = c.getTimeInMillis();
		return ((int) (end - now) / (1000 * 60));
	}
	
	/**
	 * 到下周二两点的时间
	 * @return
	 */
	public static int minutesToNextTuesday() {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 2);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.DAY_OF_WEEK, 3);
		c.set(Calendar.WEEK_OF_MONTH, c.get(Calendar.WEEK_OF_MONTH) + 1);
		long end = c.getTimeInMillis();
		return ((int) (end - now) / (1000 * 60));
	}	

	public static int minutesToDate(Date expireDate) {
		Calendar c = Calendar.getInstance();
		long now = c.getTimeInMillis();
		long end = expireDate.getTime();
		return (int) ((end - now) / (1000 * 60));
	}
	/**
	 * 获取稀有度
	 * 	1-25 普通
		26-50 精良
		51-75 卓越
		76-100 稀有
	 * 
	 * @param map
	 * @return
	 */
	public static int getRareLevel(int rareLevel) {
		if(rareLevel<0){
			return 0;
		}else if(rareLevel>0&&rareLevel<=25){
			return 1;
		}else if(rareLevel>25&&rareLevel<=50){
			return 2;
		}else if(rareLevel>50&&rareLevel<=75){
			return 3;
		}else if(rareLevel>75&&rareLevel<=100){
			return 4;
		}
		return 0;
	}
	public static int getLoseLevel(int fromRare,int toRare) {
		int loseLevel = Math.abs(fromRare-toRare);
		if(loseLevel == 0){
			loseLevel = 1;
		}
		return loseLevel;
	}
	/**
	 * 根据权重随机数值的接口
	 * 
	 * @param map
	 * @return
	 */
	public static int getRandomValueByWeights(Map<Integer, Integer> map) {
		Random random = new Random();
		int totalWeight = 0;
		for (int i : map.values()) {
			totalWeight += i;
		}
//		int[] randomArray = new int[totalWeight];
		List<Integer> list=new ArrayList<Integer>();
//		int arrayIndex = 0;
		for (Entry<Integer, Integer> each : map.entrySet()) {
			for (int i = 0; i < each.getValue(); i++) {
				list.add(each.getKey());
//				randomArray[arrayIndex++] = each.getKey();
			}
		}
		Collections.shuffle(list);
		return list.get(random.nextInt(totalWeight));
//		randomArray[random.nextInt(totalWeight)];
	}
	
	public static int[] getRandomValuesByWeights(int num,Map<Integer, Integer> map) {
		int[] indexs = new int[num];
		for(int i=0;i<num;){
			int rdmIdx = getRandomValueByWeights(map);
			map.put(rdmIdx, 0);
			indexs[i++]= rdmIdx;
		}
		return indexs;
	}

	public static<T> List<T> getRandomValueByWeightsWithoutIntArrayWithNum(Multiset<T> map,int num) {
		List<T> list = new ArrayList<T>(num);
		
		int totalWeight = 0;
		for (Multiset.Entry<T> entry : map.entrySet()) {
			totalWeight += entry.getCount();
		}
		
		for (int i = 0; i < num; i++) {
			list.add(getRandomValueByWeightsWithoutIntArrayByTotalWeight(map,totalWeight));
		}
		return list;
	}

	public static<T> T getRandomValueByWeightsWithoutIntArrayByTotalWeight(Multiset<T> map,int totalWeight) {
		int randomIndex = new Random().nextInt(totalWeight);
		for (Multiset.Entry<T> entry : map.entrySet()) {
			if ((randomIndex -= entry.getCount()) < 0) {
				return entry.getElement();
			}
		}
		return map.elementSet().iterator().next();
	}

	public static<T> T getRandomValueByWeightsWithoutIntArray(Multiset<T> map) {
		int totalWeight = 0;
		for (Multiset.Entry<T> entry : map.entrySet()) {
			totalWeight += entry.getCount();
		}
		int randomIndex = new Random().nextInt(totalWeight);
		for (Multiset.Entry<T> entry : map.entrySet()) {
			if ((randomIndex -= entry.getCount()) < 0) {
				return entry.getElement();
			}
		}
		return map.elementSet().iterator().next();
	}
	
	/**
	 * @param playerId
	 * @param type
	 *            5
	 * @param mType
	 *            SysItem.mType
	 * @param mValue
	 *            SysItem.mValue
	 * @return
	 * @throws Exception
	 */
	public static int sortMaterial(int playerId, int type, int itemId) throws Exception {
		List<PlayerItem> materialList = ServiceLocator.getService.getPlayerItemList1(playerId, type, 0, 0);
		return sortMaterial(playerId, itemId, materialList);
	}

	public static int sortMaterial(int playerId, int itemId, List<PlayerItem> playerItemList) throws Exception {
		int maxNumItemId = 0;

		List<PlayerItem> dealList = new ArrayList<PlayerItem>();
		int totalNum = 0;
		for (int i = 0; i < playerItemList.size(); i++) {
			PlayerItem pi = playerItemList.get(i);
			SysItem si = pi.getSysItem();
			if (si!=null&&si.getId() == itemId) {
				if(pi.getQuantity() < Constants.MAX_STACK_SIZE){
					dealList.add(pi);
					totalNum += pi.getQuantity();
				}else if(0 == maxNumItemId){
					maxNumItemId = pi.getId();
				}
			}
		}

		if(dealList.size()>1){
			int size = totalNum / Constants.MAX_STACK_SIZE;

			for (int i = 0; i < dealList.size(); i++) {
				PlayerItem pi = dealList.get(i);
				if (i < size) {
					pi.setQuantity(Constants.MAX_STACK_SIZE);
					if (maxNumItemId == Constants.NUM_ZERO) {
						maxNumItemId = pi.getId();
					}
					ServiceLocator.updateService.updatePlayerItem(pi);
				} else if (i == size && totalNum%Constants.MAX_STACK_SIZE>0) {
					if (maxNumItemId == Constants.NUM_ZERO) {
						maxNumItemId = pi.getId();
					}
					pi.setQuantity(totalNum%Constants.MAX_STACK_SIZE);
					ServiceLocator.updateService.updatePlayerItem(pi);
				} else {
					ServiceLocator.deleteService.deletePlayerItem(pi.getPlayerId(), pi.getSysItem().getType(), pi);
				}
			}
		}
		if(maxNumItemId == 0 && !dealList.isEmpty()){
			maxNumItemId = dealList.get(0).getId();
		}
		return maxNumItemId;
	}
	
	/**
	 * 将物品 按 沓数 进行整理
	 * 
	 * @param playerItems 被整理物品集合
	 */
	private static void sortMaterialDirect(List<PlayerItem> playerItems)throws Exception {
		Map<Integer,List<PlayerItem>> materialMap = new HashMap<Integer, List<PlayerItem>>();
		for (PlayerItem pi : playerItems) {
			if(pi.getQuantity() < Constants.MAX_STACK_SIZE){
				List<PlayerItem> list = materialMap.get(pi.getItemId());
				if(null == list){
					list = new ArrayList<PlayerItem>(10);
					materialMap.put(pi.getItemId(), list);
				}
				list.add(pi);
			}
		}
		for (Entry<Integer, List<PlayerItem>> material : materialMap.entrySet()) {
			if(material.getValue().size()>1){
				int totalQuantity = 0;
				for(PlayerItem pi:material.getValue()){
					totalQuantity += pi.getQuantity();
					pi.setQuantity(0);
				}
				int size = totalQuantity / Constants.MAX_STACK_SIZE;
				for(int i =0;i<material.getValue().size();i++){
					PlayerItem pi = material.getValue().get(i);
					if( i<size ){
						pi.setQuantity(Constants.MAX_STACK_SIZE);
						ServiceLocator.updateService.updatePlayerItem(pi);
					}else if( i==size && totalQuantity%Constants.MAX_STACK_SIZE>0 ){
						pi.setQuantity(totalQuantity%Constants.MAX_STACK_SIZE);
						ServiceLocator.updateService.updatePlayerItem(pi);
					}else{
						ServiceLocator.deleteService.deletePlayerItem(pi.getPlayerId(), pi.getSysItem().getType(), pi);
					}
					
				}
			}
		}
	}
	
	public static void sortAllMaterialDirect(int playerId, int type) throws Exception {
		List<PlayerItem> playerItems = ServiceLocator.getService.getPlayerItemList1(playerId, type, 0, 0);
		sortMaterialDirect(playerItems);
	}
	public static void sortMaterialsBySubTypeDirect(int playerId ,int type ,int subType) throws Exception{
		List<PlayerItem> playerItems = ServiceLocator.getService.getPlayerItemList1(playerId, type, 0, subType);
		sortMaterialDirect(playerItems);
	}
	public static void sortAllMaterial(int playerId, int type) throws Exception {
		List<PlayerItem> playerItems = ServiceLocator.getService.getPlayerItemList1(playerId, type, 0, 0);
		sortMaterialDirect(playerItems);
	}
	public static void sortMaterialsBySubType(int playerId ,int type ,int subType) throws Exception{
		List<PlayerItem> playerItems = ServiceLocator.getService.getPlayerItemList1(playerId, type, 0, subType);
		sortMaterialDirect(playerItems);
	}
	public static void checkNull(Object obj, String msg) throws BaseException {
		if (null == obj) {
			if(msg!=null)
				throw new BaseException(msg);
			else
				throw new NullPointerException();
		}
	}

	/**
	 * 从整数min和整数max中去随机数，包含min和max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandomBetween2Numbers(int min, int max) {
		
		if (max >= min) {
			
			min = (min >= 0 ? min : 0);
			max = (max >= 0 ? max : 0);
			
			Random random = new Random();
			int n = max - min;
			int rd1=random.nextInt(n/2+1);
			int rd2=random.nextInt(n/2+1);
			return (min+max)/2+rd1-rd2;
		}
		return 0;
	}
	
	public static String generateDartleTopRetStr(String key,Player player) throws Exception{
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		String pId = String.valueOf(player.getId()); 
		String pref = new StringBuilder().append("{").append("\"").append(player.getName()).append("\",")
		.append(player.getRank()).append(",")
		.toString();
		double value = Math.abs(nosql.zScore(key, pId));
		String valueStr = String.valueOf((int)value);
		String result = new StringBuilder().append(pref).append(valueStr).append("},").toString();
		return result;	
	}
	
	public static boolean numberIsIn(int n,int...numbers){
		for(int i : numbers){
			if(n == i){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object[]> getDifferences(Object o1,Object o2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Map<String,Object> propMap1 = PropertyUtils.describe(o1);
		Map<String,Object> propMap2 = PropertyUtils.describe(o2);
		Map<String,Object[]> returnMap = new HashMap<String,Object[]>();
		Iterator<String> keyItt = propMap1.keySet().iterator();
		for(;keyItt.hasNext();){
			String key =  keyItt.next();
			Object val1 =  propMap1.get(key);
			if(val1==null){
				val1="null";
			}
			if(propMap2.containsKey(key)){
				Object val2 =  propMap2.get(key);
				//过滤数组和集合
				if(val2!=null&&!val1.equals(val2)&&!(val1 instanceof Object[])&&!(val1 instanceof Collection)){
					Object[] vals = {val1,val2}; 
					returnMap.put(key, vals);
				}
			}
		}
		return returnMap;
	}
	public static int buyPlayerItem(PlayerBuyWay buyWay) throws Exception {
		return buyWay.buy();
	}
	
	/**
	 * 对于累加计费的 物品 获得当前购买价格，用于资源争夺战兑换宝箱
	 * @param record
	 * @param baseCost
	 * @return
	 */
	public static int getCostForBuyRecord(int record,int baseCost,int iid){
		if(iid==48){//资源争夺战魔盒
		//	return (int)((Math.pow(1.5d,0.01*record)*baseCost));
			return (int)(baseCost*Math.log(0.9*(record+1)+2.5)/Math.log(3.4));
		}else{
			return (int)((Math.log(0.075*(record+1)+2.5)/Math.log(3.5))*baseCost);	
		}
		
	}	
	
	/**
	 * 对于累加计费的 物品 获得当前购买价格，用于资源争夺战buff
	 * @param record
	 * @param baseCost
	 * @return
	 */
	public static int getCostForZYZDZBuff(int record,int baseCost){
		if(record==0){//虽然下面公式也是返回0，但最常用的是0，所以这里定死一个数值，提高效率
			return 0;
		}else{
			double d=(0.0006*Math.pow((record),2) + 0.08*(record))*baseCost;
			return (int)d;
		}
	}	
			
	
	/**
	 * 根据当日购买次数，设置资源争夺战购买的payment
	 * @param payment  
	 * @param count 已经购买的次数
	 * @param needClone  是否需要进行payment克隆
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public static Payment initZYZDZBuffPayment(Payment payment,int count,boolean needClone) throws CloneNotSupportedException{
		Payment otherPayment=null;
		if(needClone){
			otherPayment=payment.clone();
		}else{
			otherPayment=payment;
		}
		int baseCost=otherPayment.getCost();
		int calCost=CommonUtil.getCostForZYZDZBuff(count, baseCost);
		otherPayment.setCost(calCost);
		return otherPayment;
	}

	public static boolean isResMagicBox(SysItem sysItem) {
		if (sysItem.getType() == Constants.DEFAULT_OPEN_TYPE
				&& "48".equals(sysItem.getIId())) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isZYZDZBuff(SysItem sysItem) {
		if (sysItem.getType()!=null && sysItem.getType() == Constants.DEFAULT_ZYZDZ_TYPE
				&&sysItem.getSubType()!=null && sysItem.getSubType()==6) {
			return true;
		} else {
			return false;
		}
	}	
	
	/**
	 * 根据今天累计获得的资源跟战队等级，来返回收益系数
	 * @param attTeam_today_Res 今天累计的资源
	 * @param level 战队等级
	 * @return 收益系数 分别为1  0.5 0
	 */
	public static double Team_Res_ratio(int attTeam_today_Res, int level) {
		double result = attTeam_today_Res>(Constants.ZYZDZ_TEAM_RES_RATIO[level-1][0]+Constants.ZYZDZ_TEAM_RES_RATIO[level-1][1])?0:attTeam_today_Res > Constants.ZYZDZ_TEAM_RES_RATIO[level-1][0]?0.5:1;
		return result;
	}

	/**
	 * 根据今天累计获得的资源跟战队等级，来返回收益系数
	 * @param attTeam_today_Res 今天累计的资源
	 * @param level 战队等级
	 * @return 收益系数 分别为1  0.5 0
	 */
	public static double player_Res_ratio(int player_today_Res, int level) {
		double result = player_today_Res>(Constants.ZYZDZ_PLAYER_RES_RATIO[level-1][0]+Constants.ZYZDZ_PLAYER_RES_RATIO[level-1][1])?0:player_today_Res > Constants.ZYZDZ_PLAYER_RES_RATIO[level-1][0]?0.5:1;
		return result;
	}
	//zlm2015-5-7-限时装备-开始-------------------------------------
	/**
	 * 判断id段来判定 在id段内返回true,要不返回false
	 */
	public static boolean provisional_item_scope_flag(int sysItemId) {
		if(sysItemId>=Constants.PROVISIONAL_ITEM_SCOPE_BEGIN&&sysItemId<=Constants.PROVISIONAL_ITEM_SCOPE_END){
			return true;
		}else {
			return false;
		}
	}
	//zlm2015-5-7-限时装备-结束-------------------------------------
}
