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
List map969 = new ArrayList<>();
if (null!=context.get("map")){
if (context.get("map") instanceof List) map969=(List<?>)context.get("map");
else if (context.get("map").getClass().isArray()) map969=Stream.of((Object[])context.get("map")).collect(Collectors.toList());
}
map969.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			display =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			description = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isLight=0, ");
sb.append("\r\n");
sb.append("			place = ");
sb.append(context.get("key"));
sb.append(", ");
sb.append("\r\n");
List list36 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list36=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list36=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list36.forEach(item->{
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
List map69 = new ArrayList<>();
if (null!=context.get("map")){
if (context.get("map") instanceof List) map69=(List<?>)context.get("map");
else if (context.get("map").getClass().isArray()) map69=Stream.of((Object[])context.get("map")).collect(Collectors.toList());
}
map69.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			display =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			description = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isLight=0, ");
sb.append("\r\n");
sb.append("			place = ");
sb.append(context.get("key"));
sb.append(", ");
sb.append("\r\n");
List list264 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list264=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list264=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list264.forEach(teamItem->{
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