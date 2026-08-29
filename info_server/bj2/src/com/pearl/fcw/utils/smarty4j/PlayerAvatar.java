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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"teamId"));
sb.append(" ");
sb.append("\r\n");
sb.append("avatar_info = { ");
sb.append("\r\n");
sb.append("	gender = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"gender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	last_side=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"lastGameSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("	last_time=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"lastLoginTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("	team0 = { ");
sb.append("\r\n");
List cosT364 = new ArrayList<>();
if (null!=context.get("cosT")){
if (context.get("cosT") instanceof List) cosT364=(List<?>)context.get("cosT");
else if (context.get("cosT").getClass().isArray()) cosT364=Stream.of((Object[])context.get("cosT")).collect(Collectors.toList());
}
cosT364.forEach(itemT->{
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
List cos0398 = new ArrayList<>();
if (null!=context.get("cos0")){
if (context.get("cos0") instanceof List) cos0398=(List<?>)context.get("cos0");
else if (context.get("cos0").getClass().isArray()) cos0398=Stream.of((Object[])context.get("cos0")).collect(Collectors.toList());
}
cos0398.forEach(item0->{
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
List cosP315 = new ArrayList<>();
if (null!=context.get("cosP")){
if (context.get("cosP") instanceof List) cosP315=(List<?>)context.get("cosP");
else if (context.get("cosP").getClass().isArray()) cosP315=Stream.of((Object[])context.get("cosP")).collect(Collectors.toList());
}
cosP315.forEach(itemP->{
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
List cos1839 = new ArrayList<>();
if (null!=context.get("cos1")){
if (context.get("cos1") instanceof List) cos1839=(List<?>)context.get("cos1");
else if (context.get("cos1").getClass().isArray()) cos1839=Stream.of((Object[])context.get("cos1")).collect(Collectors.toList());
}
cos1839.forEach(item1->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"id"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"itemId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List wpStable126 = new ArrayList<>();
if (null!=context.get("wpStable")){
if (context.get("wpStable") instanceof List) wpStable126=(List<?>)context.get("wpStable");
else if (context.get("wpStable").getClass().isArray()) wpStable126=Stream.of((Object[])context.get("wpStable")).collect(Collectors.toList());
}
wpStable126.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.name"));
sb.append("/");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e5){
logger.error(e5.toString());
}
});
List wpChange730 = new ArrayList<>();
if (null!=context.get("wpChange")){
if (context.get("wpChange") instanceof List) wpChange730=(List<?>)context.get("wpChange");
else if (context.get("wpChange").getClass().isArray()) wpChange730=Stream.of((Object[])context.get("wpChange")).collect(Collectors.toList());
}
wpChange730.forEach(itemC->{
try{
if((O2oUtil.compare(itemC,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.name"));
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
sb.append("		{} ");
sb.append("\r\n");
}
return sb.toString();
}

}