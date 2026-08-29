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

public class AchievementCompleted implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{cmd = \"achievement_completed\", ");
sb.append("\r\n");
sb.append("	list =  ");
sb.append("	{ ");
sb.append("\r\n");
List list743 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list743=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list743=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list743=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list743=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list743=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list743=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list743=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list743=Stream.of(context.get("list")).collect(Collectors.toList());
}
list743.forEach(achievement->{
try{
sb.append("			{ ");
sb.append("\r\n");
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"achievement.id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"achievement.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"achievement.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"achievement.type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"status"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"number"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(achievement,"achievement.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("				\"medal\", ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("time"),">=",300)){
sb.append("					0, ");
sb.append("\r\n");
} else if ( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(achievement,"achievement.color"),"<=",2))&&(O2oUtil.compare(context.get("time"),">=",180))){
sb.append("					0, ");
sb.append("\r\n");
} else if ( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(achievement,"achievement.color"),">",2)) &&(O2oUtil.compare(context.get("time"),">=",180))){
sb.append("					1, ");
sb.append("\r\n");
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(achievement,"achievement.color"),">",2)){
sb.append("					2, ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("			}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("		 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}