package com.pearl.fcw.utils.smarty4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil.Ctx;

public class ModuleStatus implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list = { ");
sb.append("\r\n");
sb.append("	Mission = {state = ");
sb.append(context.get("mission"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	DailyMission = {state = ");
sb.append(context.get("dailyMission"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	Compose = {state = ");
sb.append(context.get("compose"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	Online = {state = ");
sb.append(context.get("online"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	Present = {state = ");
sb.append(context.get("present"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	DailyCheck = {state = ");
sb.append(context.get("dailyCheck"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	FreeChange = {state = ");
sb.append(context.get("freeChange"));
sb.append("}, ");
sb.append("\r\n");
sb.append("	Shot = {state = ");
sb.append(context.get("shot"));
sb.append("} ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}