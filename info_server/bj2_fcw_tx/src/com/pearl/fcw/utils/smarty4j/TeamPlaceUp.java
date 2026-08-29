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

public class TeamPlaceUp implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("placeLevelUp= { ");
sb.append("\r\n");
sb.append("	level = ");
sb.append(context.get("level"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isCanLevelUp =");
sb.append(context.get("isCanLevelUp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	levelUpTime = ");
sb.append(context.get("levelUpTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("	money = ");
sb.append(context.get("money"));
sb.append(", ");
sb.append("\r\n");
sb.append(" } ");
sb.append("\r\n");
return sb.toString();
}

}