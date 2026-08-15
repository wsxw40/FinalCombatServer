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
List list352 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list352=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list352=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list352.forEach(a->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"status"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.number"));
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