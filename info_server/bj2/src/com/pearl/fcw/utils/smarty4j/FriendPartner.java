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

public class FriendPartner implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list = { ");
sb.append("\r\n");
List list519 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list519=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list519=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list519.forEach(p->{
try{
if( O2oUtil.compare(p,"!=",null)){
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"team"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"minutesSinceLastLogin"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}