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

public class TeamList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(context.get("pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("team = { ");
sb.append("\r\n");
List teamList273 = new ArrayList<>();
if (null!=context.get("teamList")){
if (context.get("teamList") instanceof List) teamList273=(List<?>)context.get("teamList");
else if (context.get("teamList") instanceof int[]) teamList273=Stream.of((int[])context.get("teamList")).collect(Collectors.toList());
else if (context.get("teamList") instanceof long[]) teamList273=Stream.of((long[])context.get("teamList")).collect(Collectors.toList());
else if (context.get("teamList") instanceof float[]) teamList273=Stream.of((float[])context.get("teamList")).collect(Collectors.toList());
else if (context.get("teamList") instanceof double[]) teamList273=Stream.of((double[])context.get("teamList")).collect(Collectors.toList());
else if (context.get("teamList") instanceof byte[]) teamList273=Stream.of((byte[])context.get("teamList")).collect(Collectors.toList());
else if (context.get("teamList") instanceof String[]) teamList273=Stream.of((String[])context.get("teamList")).collect(Collectors.toList());
else if (context.get("teamList").getClass().isArray()) teamList273=Stream.of(context.get("teamList")).collect(Collectors.toList());
}
teamList273.forEach(team->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(team,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"logo"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(team,"kill"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(team,"headShot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(team,"gameWin"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(team,"gameTotal"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(team,"memberCount"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(team,"size"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"challengeRatio"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"leaderName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"city"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(team,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(team,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(team,"fight"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"createTimeStr"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}