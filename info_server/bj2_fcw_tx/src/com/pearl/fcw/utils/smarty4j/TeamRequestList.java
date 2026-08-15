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

public class TeamRequestList implements Ctx {
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
sb.append("requests = { ");
sb.append("\r\n");
List list604 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list604=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list604=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list604=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list604=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list604=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list604=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list604=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list604=Stream.of(context.get("list")).collect(Collectors.toList());
}
list604.forEach(member->{
try{
sb.append("     	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(member,"playerId"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(member,"player.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(member,"player.rank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(member,"player.exp"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(member,"nickName"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(member,"creatDay"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(member,"player.maxFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(member,"player.winRate"));
sb.append(" ");
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