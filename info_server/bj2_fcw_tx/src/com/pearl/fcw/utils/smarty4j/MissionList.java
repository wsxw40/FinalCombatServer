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

public class MissionList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = 1 ");
sb.append("\r\n");
sb.append("pages = 1 ");
sb.append("\r\n");
sb.append("num=");
sb.append(context.get("num"));
sb.append("  ");
sb.append("\r\n");
sb.append("awoke = ");
sb.append(context.get("awoke"));
sb.append(" ");
sb.append("\r\n");
sb.append("mission = { ");
sb.append("\r\n");
List list103 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list103=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list103=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list103=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list103=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list103=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list103=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list103=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list103=Stream.of(context.get("list")).collect(Collectors.toList());
}
list103.forEach(amission->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"id"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(amission,"sysMission.name"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(amission,"sysMission.missionTitle"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(amission,"description"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"target"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"status"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"award"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"sysMission.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		1, ");
sb.append("\r\n");
sb.append("		0, ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"cmIncome.cal"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"cmIncome.exp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"cmIncome.vipCal"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"cmIncome.vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("		vipExp=");
sb.append(O2oUtil.getSmarty4jProperty(amission,"vipUpGradeExp"));
sb.append(", ");
sb.append("\r\n");
Context includeContextVar1023=new Context();
includeContextVar1023.set("awardItemVos",O2oUtil.getSmarty4jProperty(amission,"awardItemVos"));
sb.append(new MissionItemAward().get(includeContextVar1023));
sb.append("  	}, ");
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