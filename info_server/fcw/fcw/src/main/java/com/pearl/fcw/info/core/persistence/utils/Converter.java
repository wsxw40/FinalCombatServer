package com.pearl.fcw.info.core.persistence.utils;

import java.util.List;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.lobby.pojo.events.BasePlayerEvent;
import com.pearl.fcw.info.lobby.utils.ServiceLocator;
import com.pearl.fcw.info.lobby.utils.SmartyManager;

public class Converter {
	private static final Logger log = LoggerFactory.getLogger(Converter.class);
	private static final SmartyManager sm = ServiceLocator.sm;

	public static String dailyCheck(String date, int day,
			List<String> checkList, int result) {
		String value = "";
		try {
			Context ctx = new Context();
			ctx.set("result", result);
			ctx.set("date", date);
			ctx.set("day", day);
			ctx.set("checkList", checkList);
			return sm.getEncodedBody("DailyCheck.st", ctx);
		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}

	public static String friendNewsEntry(BasePlayerEvent event) {
		String value = "";

		try {
			Context ctx = new Context();

			ctx.set("event", event);
			return sm.getEncodedBody("FriendNewsEntry.st", ctx);
		} catch (Exception e) {
			log.warn("Error in Convertor: ", e);
		}
		return value;
	}

}
