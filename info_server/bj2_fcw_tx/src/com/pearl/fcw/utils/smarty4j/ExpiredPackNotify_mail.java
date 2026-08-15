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

public class ExpiredPackNotify_mail implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("<C447^1^ ");
sb.append("\r\n");
List list651 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list651=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list651=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list651=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list651=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list651=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list651=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list651=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list651=Stream.of(context.get("list")).collect(Collectors.toList());
}
list651.forEach(item->{
try{
if((O2oUtil.compare(O2oUtil.getSmarty4jProperty(item,"durable"),"<=",0))){
sb.append("		<C445^1^");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("> ");
sb.append("\r\n");
} else {
sb.append("		<C446^1^");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("> ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("> ");
sb.append("\r\n");
return sb.toString();
}

}