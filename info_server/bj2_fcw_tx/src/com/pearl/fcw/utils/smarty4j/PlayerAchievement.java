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

public class PlayerAchievement implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("achievement={ ");
sb.append("\r\n");
sb.append("	pages = ");
sb.append(context.get("pages"));
sb.append(", ");
sb.append("\r\n");
sb.append("	size = ");
sb.append(context.get("size"));
sb.append(", ");
sb.append("\r\n");
sb.append("	complete = ");
sb.append(context.get("complete"));
sb.append(", ");
sb.append("\r\n");
sb.append("	list={ ");
sb.append("\r\n");
List list460 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list460=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list460=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list460=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list460=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list460=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list460=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list460=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list460=Stream.of(context.get("list")).collect(Collectors.toList());
}
list460.forEach(a->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(a,"status"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(a,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}