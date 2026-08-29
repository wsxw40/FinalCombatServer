package com.pearl.fcw.info.lobby.utils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;

import com.pearl.fcw.info.core.persistence.utils.CommonMsg;

public class CommonUtil {
	public static DateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
	public static DateFormat dateFormatOnlyDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat dateFormatWeek = new SimpleDateFormat("yyyy-ww");
	public static String I18NCodePref = "<C";
	public static String I18NCodeEnd = ">";
	public static String I18NCodeDeli = "^";

	public static String getLockKey(int playerId) {
		return playerId > 0 ? new StringBuilder(Constants.LOCK_TYPE)
				.append(Constants.DELIMITER).append(playerId).toString() : null;
	}

	public static String getTeamLockKey(int teamId) {
		return Constants.TEAM_LOCK_TYPE + Constants.DELIMITER + teamId;
	}

	/**
	 * 多国语言版 codestyle : |codeNO,argsNum,arg1,arg2,...
	 * 
	 * @param regx
	 * @param args
	 * @return
	 */
	public static String messageFormatI18N(String regx, Object... args) {// code
																			// style
																			// |
		regx = I18NCodePref + regx + I18NCodeDeli
				+ (args == null ? 0 : args.length);
		for (Object obj : args) {
			regx += I18NCodeDeli + String.valueOf(obj);
		}
		return regx + I18NCodeEnd;
	}

	public static String messageFormatI18N2(String regx, String pref, int num,
			Object... args) {// code style |
		regx = pref + regx + "^" + num + "^";
		for (Object obj : args) {
			regx += "\\\"" + obj + "\\\"^";
		}
		return regx + ">";
	}

	public static String getPropertyStr(int index, int value, int value2,
			int time) {
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
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 2:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 3:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 4:
			String[] allMsgDesc = CommonMsg.GUN_PROPERTY_MSG[index - 1]
					.split(",");
			if (value == 2) {
				returnValue = messageFormatI18N(allMsgDesc[2], args1);
			} else if (value == 4) {
				returnValue = messageFormatI18N(allMsgDesc[1], args1);
			} else if (value == 6) {
				returnValue = messageFormatI18N(allMsgDesc[0], args1);
			}
			// returnValue = messageFormatI18N(CommonMsg.GUN_PROPERTY_MSG[index
			// - 1], args1);
			break;
		case 5:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 6:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 7:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 8:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 9:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 10:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 11:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 12:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 13:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 14:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 30:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 31:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 33:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 34:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args1);
			break;
		case 35:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 37:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 38:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 39:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 45:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 46:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 47:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 60:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 61:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 62:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 63:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 64:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 66:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 67:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 68:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 69:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 70:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 71:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 72:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 73:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 74:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 75:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 76:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 77:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 78:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 79:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 80:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 81:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 82:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 83:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 84:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 85:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 86:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 87:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 90:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 91:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 92:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 93:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args2);
			break;
		case 94:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args4);
			break;
		case 100:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args3);
			break;
		case 101:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args3);
			break;
		case 102:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 110:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 111:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 112:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 113:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 114:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 115:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 116:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 122:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 150:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 151:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		case 152:
			returnValue = messageFormatI18N(
					CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			break;
		default:
			if (CommonMsg.GUN_PROPERTY_MSG[index - 1] != "") { // 工程兵主武器新增属性
				returnValue = messageFormatI18N(
						CommonMsg.GUN_PROPERTY_MSG[index - 1], args);
			}
			break;
		}

		return returnValue;
	}

	/**
	 * 判断id段来判定 在id段内返回true,要不返回false
	 */
	public static boolean provisional_item_scope_flag(int sysItemId) {
		if (sysItemId >= Constants.PROVISIONAL_ITEM_SCOPE_BEGIN
				&& sysItemId <= Constants.PROVISIONAL_ITEM_SCOPE_END) {
			return true;
		} else {
			return false;
		}
	}

	public static String messageFormat(String regx, Object... args) {
		return MessageFormat.format(regx, args);
	}

	public static float toFourFloat(double decimal) {
		Double d = (int) (decimal * 10000) / 10000.0;
		return Float.valueOf(d.floatValue());
	}
}