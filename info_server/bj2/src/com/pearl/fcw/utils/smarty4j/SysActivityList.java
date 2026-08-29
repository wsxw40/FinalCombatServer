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

public class SysActivityList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("activity = { ");
sb.append("\r\n");
List list257 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list257=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list257=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list257.forEach(activity->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("amission"),"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"name"));
sb.append("\",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"targetNum"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("amission"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("  	}, ");
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