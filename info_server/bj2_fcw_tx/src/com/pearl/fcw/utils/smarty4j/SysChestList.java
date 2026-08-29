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

public class SysChestList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("num=");
sb.append(context.get("num"));
sb.append(" , ");
sb.append("\r\n");
sb.append("chest = { ");
sb.append("\r\n");
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
list411.forEach(chest->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(chest,"id"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"type"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"level"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"sysItemId"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"weight"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"number"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"useType"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"isNotice"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"price"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(chest,"chipPrice"));
sb.append(", ");
sb.append("\r\n");
sb.append("		item = 		{ ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(chest,"sysItem"),"!=",null))){
sb.append("				{0 , \"");
sb.append(O2oUtil.getSmarty4jProperty(chest,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(chest,"sysItem.displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(chest,"sysItem.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(chest,"sysItem.type"));
sb.append("}, ");
sb.append("\r\n");
}
sb.append("		}, ");
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