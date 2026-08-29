package com.pearl.o2o.utils;

import java.util.Date;

import com.pearl.o2o.pojo.Player;

public class XunleiLogUtils {

	/**
	 *  强化部件产出和消耗
	 * @param player		//玩家
	 * @param handleType	//操作类型（0表示消耗；1表示产出）
	 * @param oldItemSize	//原先强化部件数量
	 * @param useItemSize	//本次改变的强化部件数量
	 * @param nowItemSize	//剩余强化部件数量
	 * @param type			//渠道类型
	 */
	public static void xlLog_22_1(Player player,int handleType, int oldItemSize, int useItemSize,int nowItemSize,int type) {
		ServiceLocator.nosqlService.addXunleiLog("22.1"
		+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()								//玩家帐号ID
		+ Constants.XUNLEI_LOG_DELIMITER + player.getName()									//玩家角色名
		+ Constants.XUNLEI_LOG_DELIMITER + handleType										//操作类型（0表示消耗;1表示产出）
		+ Constants.XUNLEI_LOG_DELIMITER + oldItemSize										//原先强化部件数量
		+ Constants.XUNLEI_LOG_DELIMITER + useItemSize										//强化部件数量
		+ Constants.XUNLEI_LOG_DELIMITER + nowItemSize										//剩余强化部件数量
		+ Constants.XUNLEI_LOG_DELIMITER + type												//渠道类型
		+ Constants.XUNLEI_LOG_DELIMITER + CommonUtil.simpleDateFormat.format(new Date())	//操作时间
		);
	}
	/**
	 * VIP经验相关日志
	 * @param player
	 * @param oldSize	//原先VIP经验
	 * @param useSize	//获得VIP经验
	 * @param nowSize	//最终VIP经验
	 * @param type		//渠道类型
	 */
	public static void xlLog_23_1(Player player, int oldSize, int useSize,int nowSize,int type) {
		ServiceLocator.nosqlService.addXunleiLog("23.1"
				+ Constants.XUNLEI_LOG_DELIMITER + player.getUserName()
				+ Constants.XUNLEI_LOG_DELIMITER + player.getName()
				+ Constants.XUNLEI_LOG_DELIMITER + oldSize
				+ Constants.XUNLEI_LOG_DELIMITER + useSize
				+ Constants.XUNLEI_LOG_DELIMITER + nowSize
				+ Constants.XUNLEI_LOG_DELIMITER + type
				+ Constants.XUNLEI_LOG_DELIMITER+ CommonUtil.simpleDateFormat.format(new Date())
				);
	}
}
