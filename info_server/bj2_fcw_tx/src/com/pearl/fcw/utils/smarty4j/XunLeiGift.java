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

public class XunLeiGift implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{ ");
sb.append("\r\n");
sb.append("	cmd = \"xunlei_gift\", ");
sb.append("\r\n");
sb.append("	num = ");
sb.append(context.get("num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	items = { ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("list"),"!=",null))){
List list411 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list411=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list411=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list411=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list411=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list411=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list411=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list411=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list411=Stream.of(context.get("list")).collect(Collectors.toList());
}
list411.forEach(sysItem->{
try{
sb.append("				{0 , \"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}