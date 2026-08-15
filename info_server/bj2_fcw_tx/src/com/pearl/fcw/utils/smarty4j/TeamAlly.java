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

public class TeamAlly implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("team = { ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jProperty(context.get("team"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	ally = { ");
sb.append("\r\n");
List teamteamList67 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")){
if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof List) teamteamList67=(List<?>)O2oUtil.getSmarty4jProperty(context.get("team"),"teamList");
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof int[]) teamteamList67=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof long[]) teamteamList67=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof float[]) teamteamList67=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof double[]) teamteamList67=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof byte[]) teamteamList67=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList") instanceof String[]) teamteamList67=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"teamList").getClass().isArray()) teamteamList67=Stream.of(O2oUtil.getSmarty4jProperty(context.get("team"),"teamList")).collect(Collectors.toList());
}
teamteamList67.forEach(ally->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jProperty(ally,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"name"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"logo"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"kill"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"headShot"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"gameWin"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"gameRatio"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(ally,"leaderName"));
sb.append("\"}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}	 ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}