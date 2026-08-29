package com.pearl.o2o.utils;

import com.pearl.o2o.pojo.Player;

/**
 * 验证是否首次做某事
 * 
 * @date 2014/5/30
 * @author OuYangGuang
 */
public class FirstCheckUtils {

	public FirstCheckUtils() {
	}

	/**
	 * 如果是新手且开黄金彩盒，必得一使用次数最多的角色的精良武器 
	 * 验证是否黄金彩盒第一次
	 * 
	 * @param player
	 * @return boolean
	 */
	public static boolean getLuckyPackageFirst(Player player) {
		/* 占据isChest第长度-1位 */
		boolean flag = false;
		int isChest = player.getIsChest();
		String isChestStr = String.valueOf(isChest);
		char lPFlag = isChestStr.charAt(isChestStr.length() - 1);
		switch (lPFlag) {
		case '0':
			flag = true;
			lPFlag = '1';
			isChestStr = isChestStr.substring(0, isChestStr.length() - 1);
			isChestStr += lPFlag;
			player.setIsChest(Integer.parseInt(isChestStr));
			break;
		case '1':
			flag = false;
			break;
		}
		return flag;
	}

	/**
	 * 验证是否月卡是否第一次
	 * 
	 * @param player
	 * @return boolean Player player
	 */
	public static boolean getOnCardFirst(Player player) {
		/* 占据isChest第长度-2位 */
		boolean flag = false;
		int isChest = player.getIsChest();
		String isChestStr = String.valueOf(isChest);
		char lPFlag = 0;

		if (isChestStr.length() - 2 < 0) {	// 如继续增加下一个验证，请length - 3 ，需要注意的是，请加第三位的时候，把百位数置零
			isChest += 100;					// isChest 百位改为 千位。
			isChestStr = String.valueOf(isChest);
			lPFlag = isChestStr.charAt(isChestStr.length() - 2);
		} else {
			lPFlag = isChestStr.charAt(isChestStr.length() - 2);
		}

		switch (lPFlag) {
		case '0':
			flag = true;
			lPFlag = '1';
			String str = isChestStr.substring(isChestStr.length() - 1);
			isChestStr = isChestStr.substring(0, isChestStr.length() - 2);
			isChestStr += lPFlag + str;
			player.setIsChest(Integer.parseInt(isChestStr));
			break;
		case '1':
			flag = false;
			break;
		}

		return flag;
	}

	public static void main(String[] args) {
		Player player = new Player();
		player.setIsChest(0);
		
		System.out.println(player.getIsChest());
		System.out.println(getOnCardFirst(player));
		System.out.println(player.getIsChest());
		System.out.println(getLuckyPackageFirst(player));
		
		System.out.println(player.getIsChest());
		
		System.out.println(getLuckyPackageFirst(player));
		System.out.println(player.getIsChest());
		System.out.println(getOnCardFirst(player));
		System.out.println(player.getIsChest());
		System.out.println(getOnCardFirst(player));
		System.out.println(player.getIsChest());
		System.out.println(getLuckyPackageFirst(player));
		
		System.out.println(player.getIsChest());
	}
}
