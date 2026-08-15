package com.pearl.o2o.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.pearl.o2o.pojo.FightRecord;
import com.pearl.o2o.pojo.Team;

public class GsonUtil {

	public static List<FightRecord> getFightRecoreds(String str) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<FightRecord>>() {
		}.getType();
		List<FightRecord> fights = gson.fromJson(str, type);
		if (StringUtils.isEmpty(str)) {
			return new ArrayList<FightRecord>();
		}
		return fights;
	}

	public static String FightRecoredsToString(List<FightRecord> frs) {
		Gson gson = new Gson();
		if (frs.isEmpty()) {
			return null;
		}
		return gson.toJson(frs);
	}

	public static void main(String[] args) {
		Team t = new Team();
		Integer i = t.getSize();
		System.out.println(i + 1);
	}
}
