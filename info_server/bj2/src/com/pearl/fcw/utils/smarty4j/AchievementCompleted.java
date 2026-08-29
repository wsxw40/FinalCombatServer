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
List list630 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list630=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list630=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list630.forEach(achievement->{
try{
sb.append("			{ ");
sb.append("\r\n");
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"achievement.id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"achievement.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"achievement.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"achievement.type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"status"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"number"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(achievement,"achievement.number"));
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