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

public class ExpiredItemNotify_mail implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("<C444^1^ ");
sb.append("\r\n");
List list824 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list824=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list824=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list824=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list824=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list824=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list824=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list824=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list824=Stream.of(context.get("list")).collect(Collectors.toList());
}
list824.forEach(item->{
try{
sb.append("	<C443^2^");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("^");
sb.append(O2oUtil.getSmarty4jProperty(item,"expireDateStr"));
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