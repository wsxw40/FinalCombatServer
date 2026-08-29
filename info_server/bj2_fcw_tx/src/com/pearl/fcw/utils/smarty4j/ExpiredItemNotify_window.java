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

public class ExpiredItemNotify_window implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
List list903 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list903=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list903=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list903=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list903=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list903=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list903=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list903=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list903=Stream.of(context.get("list")).collect(Collectors.toList());
}
list903.forEach(item->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(item,"expireSecondsLeft"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("list1={ ");
sb.append("\r\n");
List durableList715 = new ArrayList<>();
if (null!=context.get("durableList")){
if (context.get("durableList") instanceof List) durableList715=(List<?>)context.get("durableList");
else if (context.get("durableList") instanceof int[]) durableList715=Stream.of((int[])context.get("durableList")).collect(Collectors.toList());
else if (context.get("durableList") instanceof long[]) durableList715=Stream.of((long[])context.get("durableList")).collect(Collectors.toList());
else if (context.get("durableList") instanceof float[]) durableList715=Stream.of((float[])context.get("durableList")).collect(Collectors.toList());
else if (context.get("durableList") instanceof double[]) durableList715=Stream.of((double[])context.get("durableList")).collect(Collectors.toList());
else if (context.get("durableList") instanceof byte[]) durableList715=Stream.of((byte[])context.get("durableList")).collect(Collectors.toList());
else if (context.get("durableList") instanceof String[]) durableList715=Stream.of((String[])context.get("durableList")).collect(Collectors.toList());
else if (context.get("durableList").getClass().isArray()) durableList715=Stream.of(context.get("durableList")).collect(Collectors.toList());
}
durableList715.forEach(item->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(item,"durable"));
sb.append(",}, ");
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