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

public class KeepStay implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("today = { ");
sb.append("\r\n");
sb.append("	win = ");
sb.append(context.get("win"));
sb.append(", ");
sb.append("\r\n");
sb.append("	lose = ");
sb.append(context.get("lose"));
sb.append(", ");
sb.append("\r\n");
sb.append("	kill = ");
sb.append(context.get("kill"));
sb.append(", ");
sb.append("\r\n");
sb.append("	assist = ");
sb.append(context.get("assist"));
sb.append(", ");
sb.append("\r\n");
sb.append("	dead = ");
sb.append(context.get("dead"));
sb.append(", ");
sb.append("\r\n");
sb.append("	score = ");
sb.append(context.get("score"));
sb.append(", ");
sb.append("\r\n");
sb.append("	exp = ");
sb.append(context.get("exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gp = ");
sb.append(context.get("gp"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("num = ");
sb.append(context.get("num"));
sb.append(" ");
sb.append("\r\n");
sb.append("mission =  ");
sb.append("{ ");
sb.append("\r\n");
List missions616 = new ArrayList<>();
if (null!=context.get("missions")){
if (context.get("missions") instanceof List) missions616=(List<?>)context.get("missions");
else if (context.get("missions") instanceof int[]) missions616=Stream.of((int[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof long[]) missions616=Stream.of((long[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof float[]) missions616=Stream.of((float[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof double[]) missions616=Stream.of((double[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof byte[]) missions616=Stream.of((byte[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions") instanceof String[]) missions616=Stream.of((String[])context.get("missions")).collect(Collectors.toList());
else if (context.get("missions").getClass().isArray()) missions616=Stream.of(context.get("missions")).collect(Collectors.toList());
}
missions616.forEach(amission->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(amission,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(amission,"sysMission.name"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jProperty(amission,"sysMission.title"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jProperty(amission,"description"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(amission,"target"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"number"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"status"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"award"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(amission,"sysMission.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		items =  ");
sb.append("		{ ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList"),"!=",null))){
List amissionsysMissionnormalItemList423 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")){
if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof List) amissionsysMissionnormalItemList423=(List<?>)O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList");
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof int[]) amissionsysMissionnormalItemList423=Stream.of((int[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof long[]) amissionsysMissionnormalItemList423=Stream.of((long[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof float[]) amissionsysMissionnormalItemList423=Stream.of((float[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof double[]) amissionsysMissionnormalItemList423=Stream.of((double[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof byte[]) amissionsysMissionnormalItemList423=Stream.of((byte[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof String[]) amissionsysMissionnormalItemList423=Stream.of((String[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList").getClass().isArray()) amissionsysMissionnormalItemList423=Stream.of(O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
}
amissionsysMissionnormalItemList423.forEach(normalItem->{
try{
sb.append("					{0 , \"");
sb.append(O2oUtil.getSmarty4jProperty(normalItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(normalItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(normalItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(normalItem,"type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(normalItem,"gunProperty.color"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
}
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList"),"!=",null))){
List amissionsysMissionvipItemList409 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")){
if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof List) amissionsysMissionvipItemList409=(List<?>)O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList");
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof int[]) amissionsysMissionvipItemList409=Stream.of((int[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof long[]) amissionsysMissionvipItemList409=Stream.of((long[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof float[]) amissionsysMissionvipItemList409=Stream.of((float[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof double[]) amissionsysMissionvipItemList409=Stream.of((double[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof byte[]) amissionsysMissionvipItemList409=Stream.of((byte[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof String[]) amissionsysMissionvipItemList409=Stream.of((String[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList").getClass().isArray()) amissionsysMissionvipItemList409=Stream.of(O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
}
amissionsysMissionvipItemList409.forEach(vipItem->{
try{
sb.append("					{1 , \"");
sb.append(O2oUtil.getSmarty4jProperty(vipItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(vipItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(vipItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(vipItem,"type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(vipItem,"gunProperty.color"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("  	}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}