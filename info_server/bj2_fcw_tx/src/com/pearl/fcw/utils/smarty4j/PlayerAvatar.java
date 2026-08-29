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

public class PlayerAvatar implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("tid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"teamId"));
sb.append(" ");
sb.append("\r\n");
sb.append("avatar_info = { ");
sb.append("\r\n");
sb.append("	gender = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"gender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	last_side=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"lastGameSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("	last_time=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"lastLoginTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("	team0 = { ");
sb.append("\r\n");
List cosT407 = new ArrayList<>();
if (null!=context.get("cosT")){
if (context.get("cosT") instanceof List) cosT407=(List<?>)context.get("cosT");
else if (context.get("cosT") instanceof int[]) cosT407=Stream.of((int[])context.get("cosT")).collect(Collectors.toList());
else if (context.get("cosT") instanceof long[]) cosT407=Stream.of((long[])context.get("cosT")).collect(Collectors.toList());
else if (context.get("cosT") instanceof float[]) cosT407=Stream.of((float[])context.get("cosT")).collect(Collectors.toList());
else if (context.get("cosT") instanceof double[]) cosT407=Stream.of((double[])context.get("cosT")).collect(Collectors.toList());
else if (context.get("cosT") instanceof byte[]) cosT407=Stream.of((byte[])context.get("cosT")).collect(Collectors.toList());
else if (context.get("cosT") instanceof String[]) cosT407=Stream.of((String[])context.get("cosT")).collect(Collectors.toList());
else if (context.get("cosT").getClass().isArray()) cosT407=Stream.of(context.get("cosT")).collect(Collectors.toList());
}
cosT407.forEach(itemT->{
try{
if((O2oUtil.compare(itemT,"!=",""))){
sb.append("				\"");
sb.append(itemT);
sb.append("\",  ");
sb.append("\r\n");
} else {
sb.append("				\"\", ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	default_team0 = { ");
sb.append("\r\n");
List cos0154 = new ArrayList<>();
if (null!=context.get("cos0")){
if (context.get("cos0") instanceof List) cos0154=(List<?>)context.get("cos0");
else if (context.get("cos0") instanceof int[]) cos0154=Stream.of((int[])context.get("cos0")).collect(Collectors.toList());
else if (context.get("cos0") instanceof long[]) cos0154=Stream.of((long[])context.get("cos0")).collect(Collectors.toList());
else if (context.get("cos0") instanceof float[]) cos0154=Stream.of((float[])context.get("cos0")).collect(Collectors.toList());
else if (context.get("cos0") instanceof double[]) cos0154=Stream.of((double[])context.get("cos0")).collect(Collectors.toList());
else if (context.get("cos0") instanceof byte[]) cos0154=Stream.of((byte[])context.get("cos0")).collect(Collectors.toList());
else if (context.get("cos0") instanceof String[]) cos0154=Stream.of((String[])context.get("cos0")).collect(Collectors.toList());
else if (context.get("cos0").getClass().isArray()) cos0154=Stream.of(context.get("cos0")).collect(Collectors.toList());
}
cos0154.forEach(item0->{
try{
if((O2oUtil.compare(item0,"!=",""))){
sb.append("				\"");
sb.append(item0);
sb.append("\", ");
sb.append("\r\n");
} else {
sb.append("				\"\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	team1 = { ");
sb.append("\r\n");
List cosP603 = new ArrayList<>();
if (null!=context.get("cosP")){
if (context.get("cosP") instanceof List) cosP603=(List<?>)context.get("cosP");
else if (context.get("cosP") instanceof int[]) cosP603=Stream.of((int[])context.get("cosP")).collect(Collectors.toList());
else if (context.get("cosP") instanceof long[]) cosP603=Stream.of((long[])context.get("cosP")).collect(Collectors.toList());
else if (context.get("cosP") instanceof float[]) cosP603=Stream.of((float[])context.get("cosP")).collect(Collectors.toList());
else if (context.get("cosP") instanceof double[]) cosP603=Stream.of((double[])context.get("cosP")).collect(Collectors.toList());
else if (context.get("cosP") instanceof byte[]) cosP603=Stream.of((byte[])context.get("cosP")).collect(Collectors.toList());
else if (context.get("cosP") instanceof String[]) cosP603=Stream.of((String[])context.get("cosP")).collect(Collectors.toList());
else if (context.get("cosP").getClass().isArray()) cosP603=Stream.of(context.get("cosP")).collect(Collectors.toList());
}
cosP603.forEach(itemP->{
try{
if((O2oUtil.compare(itemP,"!=",""))){
sb.append("				\"");
sb.append(itemP);
sb.append("\",  ");
sb.append("\r\n");
} else {
sb.append("				\"\", ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	default_team1 = { ");
sb.append("\r\n");
List cos1939 = new ArrayList<>();
if (null!=context.get("cos1")){
if (context.get("cos1") instanceof List) cos1939=(List<?>)context.get("cos1");
else if (context.get("cos1") instanceof int[]) cos1939=Stream.of((int[])context.get("cos1")).collect(Collectors.toList());
else if (context.get("cos1") instanceof long[]) cos1939=Stream.of((long[])context.get("cos1")).collect(Collectors.toList());
else if (context.get("cos1") instanceof float[]) cos1939=Stream.of((float[])context.get("cos1")).collect(Collectors.toList());
else if (context.get("cos1") instanceof double[]) cos1939=Stream.of((double[])context.get("cos1")).collect(Collectors.toList());
else if (context.get("cos1") instanceof byte[]) cos1939=Stream.of((byte[])context.get("cos1")).collect(Collectors.toList());
else if (context.get("cos1") instanceof String[]) cos1939=Stream.of((String[])context.get("cos1")).collect(Collectors.toList());
else if (context.get("cos1").getClass().isArray()) cos1939=Stream.of(context.get("cos1")).collect(Collectors.toList());
}
cos1939.forEach(item1->{
try{
if((O2oUtil.compare(item1,"!=",""))){
sb.append("				\"");
sb.append(item1);
sb.append("\",  ");
sb.append("\r\n");
} else {
sb.append("				\"\",  ");
sb.append("\r\n");
}
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("weapon_info = ");
if( O2oUtil.compare(context.get("playerItem"),"!=",null)){
sb.append("	{ ");
sb.append("\r\n");
sb.append("		itemid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"id"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"itemId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List wpStable155 = new ArrayList<>();
if (null!=context.get("wpStable")){
if (context.get("wpStable") instanceof List) wpStable155=(List<?>)context.get("wpStable");
else if (context.get("wpStable") instanceof int[]) wpStable155=Stream.of((int[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof long[]) wpStable155=Stream.of((long[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof float[]) wpStable155=Stream.of((float[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof double[]) wpStable155=Stream.of((double[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof byte[]) wpStable155=Stream.of((byte[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof String[]) wpStable155=Stream.of((String[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable").getClass().isArray()) wpStable155=Stream.of(context.get("wpStable")).collect(Collectors.toList());
}
wpStable155.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.name"));
sb.append("/");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e5){
logger.error(e5.toString());
}
});
List wpChange214 = new ArrayList<>();
if (null!=context.get("wpChange")){
if (context.get("wpChange") instanceof List) wpChange214=(List<?>)context.get("wpChange");
else if (context.get("wpChange") instanceof int[]) wpChange214=Stream.of((int[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof long[]) wpChange214=Stream.of((long[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof float[]) wpChange214=Stream.of((float[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof double[]) wpChange214=Stream.of((double[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof byte[]) wpChange214=Stream.of((byte[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof String[]) wpChange214=Stream.of((String[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange").getClass().isArray()) wpChange214=Stream.of(context.get("wpChange")).collect(Collectors.toList());
}
wpChange214.forEach(itemC->{
try{
if((O2oUtil.compare(itemC,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.name"));
sb.append("/");
sb.append(itemC);
sb.append("\", ");
sb.append("\r\n");
}
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
} else {
sb.append("		nil ");
sb.append("\r\n");
}
return sb.toString();
}

}