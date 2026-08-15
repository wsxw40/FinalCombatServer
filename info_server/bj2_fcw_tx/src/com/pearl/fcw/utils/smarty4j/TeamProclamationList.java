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

public class TeamProclamationList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
List list514 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list514=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list514=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list514=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list514=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list514=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list514=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list514=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list514=Stream.of(context.get("list")).collect(Collectors.toList());
}
list514.forEach(p->{
try{
sb.append("		{");
sb.append(O2oUtil.getSmarty4jProperty(p,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"icon"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"nikeName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"content"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"createTimeStr"));
sb.append("\"}, ");
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