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
List missions98 = new ArrayList<>();
if (null!=context.get("missions")){
if (context.get("missions") instanceof List) missions98=(List<?>)context.get("missions");
else if (context.get("missions").getClass().isArray()) missions98=Stream.of((Object[])context.get("missions")).collect(Collectors.toList());
}
missions98.forEach(amission->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"sysMission.name"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"sysMission.title"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"description"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"target"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"number"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"status"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"award"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(amission,"sysMission.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		items =  ");
sb.append("		{ ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList"),"!=",null))){
List amissionsysMissionnormalItemList921 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")){
if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList") instanceof List) amissionsysMissionnormalItemList921=(List<?>)O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList");
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList").getClass().isArray()) amissionsysMissionnormalItemList921=Stream.of((Object[])O2oUtil.getSmarty4jProperty(amission,"sysMission.normalItemList")).collect(Collectors.toList());
}
amissionsysMissionnormalItemList921.forEach(normalItem->{
try{
sb.append("					{0 , \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(normalItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(normalItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(normalItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(normalItem,"type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(normalItem,"gunProperty.color"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
}
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList"),"!=",null))){
List amissionsysMissionvipItemList903 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")){
if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList") instanceof List) amissionsysMissionvipItemList903=(List<?>)O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList");
else if (O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList").getClass().isArray()) amissionsysMissionvipItemList903=Stream.of((Object[])O2oUtil.getSmarty4jProperty(amission,"sysMission.vipItemList")).collect(Collectors.toList());
}
amissionsysMissionvipItemList903.forEach(vipItem->{
try{
sb.append("					{1 , \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(vipItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(vipItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(vipItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(vipItem,"type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(vipItem,"gunProperty.color"));
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