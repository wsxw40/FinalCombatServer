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

public class TreeItemList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
if( O2oUtil.compare(context.get("treeType"),"==",3) ){
sb.append("	tank={ ");
sb.append("\r\n");
List map236 = new ArrayList<>();
if (null!=context.get("map")){
if (context.get("map") instanceof List) map236=(List<?>)context.get("map");
else if (context.get("map") instanceof int[]) map236=Stream.of((int[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof long[]) map236=Stream.of((long[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof float[]) map236=Stream.of((float[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof double[]) map236=Stream.of((double[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof byte[]) map236=Stream.of((byte[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof String[]) map236=Stream.of((String[])context.get("map")).collect(Collectors.toList());
else if (context.get("map").getClass().isArray()) map236=Stream.of(context.get("map")).collect(Collectors.toList());
}
map236.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			display =\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name =\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			description = \"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isLight=0, ");
sb.append("\r\n");
sb.append("			place = ");
sb.append(context.get("key"));
sb.append(", ");
sb.append("\r\n");
List list915 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list915=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list915=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list915=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list915=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list915=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list915=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list915=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list915=Stream.of(context.get("list")).collect(Collectors.toList());
}
list915.forEach(item->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(item,"level"),">=",O2oUtil.getSmarty4jProperty(sysItem,"level"))){
sb.append("					isLight=1, ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
}
if( O2oUtil.compare(context.get("treeType"),"!=",3) ){
if( O2oUtil.compare(context.get("treeType"),"==",2) ){
sb.append("		attack= ");
}
if( O2oUtil.compare(context.get("treeType"),"==",1) ){
sb.append("		wall= ");
}
if( O2oUtil.compare(context.get("treeType"),"==",5) ){
sb.append("		machine= ");
}
sb.append("	{ ");
sb.append("\r\n");
List map151 = new ArrayList<>();
if (null!=context.get("map")){
if (context.get("map") instanceof List) map151=(List<?>)context.get("map");
else if (context.get("map") instanceof int[]) map151=Stream.of((int[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof long[]) map151=Stream.of((long[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof float[]) map151=Stream.of((float[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof double[]) map151=Stream.of((double[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof byte[]) map151=Stream.of((byte[])context.get("map")).collect(Collectors.toList());
else if (context.get("map") instanceof String[]) map151=Stream.of((String[])context.get("map")).collect(Collectors.toList());
else if (context.get("map").getClass().isArray()) map151=Stream.of(context.get("map")).collect(Collectors.toList());
}
map151.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			display =\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name =\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			description = \"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isLight=0, ");
sb.append("\r\n");
sb.append("			place = ");
sb.append(context.get("key"));
sb.append(", ");
sb.append("\r\n");
List list624 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list624=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list624=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list624=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list624=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list624=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list624=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list624=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list624=Stream.of(context.get("list")).collect(Collectors.toList());
}
list624.forEach(teamItem->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamItem,"itemId"),"==",O2oUtil.getSmarty4jProperty(sysItem,"id"))){
sb.append("					isLight=1, ");
sb.append("\r\n");
}
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
}
return sb.toString();
}

}