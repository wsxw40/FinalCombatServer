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

public class DurableNotify_mail implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("<C442^1^ ");
sb.append("\r\n");
List list951 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list951=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list951=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list951=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list951=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list951=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list951=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list951=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list951=Stream.of(context.get("list")).collect(Collectors.toList());
}
list951.forEach(item->{
try{
sb.append("	<C441^2^");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("^");
sb.append(O2oUtil.getSmarty4jProperty(item,"durable"));
sb.append("> ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("> ");
sb.append("\r\n");
return sb.toString();
}

}