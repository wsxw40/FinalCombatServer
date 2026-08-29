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

public class GrowthMissionList implements Ctx {
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
sb.append("num = ");
sb.append(context.get("num"));
sb.append(" ");
sb.append("\r\n");
sb.append("awoke = ");
sb.append(context.get("awoke"));
sb.append(" ");
sb.append("\r\n");
sb.append("mission = { ");
sb.append("\r\n");
List missions119 = new ArrayList<>();
if (null!=context.get("missions")){
if (context.get("missions") instanceof List) missions119=(List<?>)context.get("missions");
else if (context.get("missions") instanceof int[]) missions119=Stream.of((int[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof long[]) missions119=Stream.of((long[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof float[]) missions119=Stream.of((float[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof double[]) missions119=Stream.of((double[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof byte[]) missions119=Stream.of((byte[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof String[]) missions119=Stream.of((String[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions").getClass().isArray()) missions119=Stream.of(context.get("missions")).collect(Collectors.toList());
}
missions119.forEach(mi->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(mi,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(mi,"icon"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jProperty(mi,"title"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(mi,"desc"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"target"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"number"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"status"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"award"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"type"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"needaward"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(mi,"isnew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(mi,"money"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(mi,"exp"));
sb.append(",0,0,");
sb.append(O2oUtil.getSmarty4jProperty(mi,"isMain"));
sb.append(", ");
sb.append("\r\n");
Context includeContextVar1311=new Context();
includeContextVar1311.set("awardItemVos",O2oUtil.getSmarty4jProperty(mi,"awardItemVos"));
sb.append(new MissionItemAward().get(includeContextVar1311));
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}