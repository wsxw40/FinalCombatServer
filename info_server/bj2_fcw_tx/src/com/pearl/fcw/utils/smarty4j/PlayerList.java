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

public class PlayerList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("characters = { ");
sb.append("\r\n");
List players583 = new ArrayList<>();
if (null!=context.get("players")){
if (context.get("players") instanceof List) players583=(List<?>)context.get("players");
else if (context.get("players") instanceof int[]) players583=Stream.of((int[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof long[]) players583=Stream.of((long[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof float[]) players583=Stream.of((float[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof double[]) players583=Stream.of((double[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof byte[]) players583=Stream.of((byte[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof String[]) players583=Stream.of((String[])context.get("players")).collect(Collectors.toList());
else if (context.get("players").getClass().isArray()) players583=Stream.of(context.get("players")).collect(Collectors.toList());
}
players583.forEach(player->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		id = ");
sb.append(O2oUtil.getSmarty4jProperty(player,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name = \"");
sb.append(O2oUtil.getSmarty4jProperty(player,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(player,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		exp=");
sb.append(O2oUtil.getSmarty4jProperty(player,"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gender = \"");
sb.append(O2oUtil.getSmarty4jProperty(player,"gender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		last_side=");
sb.append(O2oUtil.getSmarty4jProperty(player,"lastGameSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("		last_time=");
sb.append(O2oUtil.getSmarty4jProperty(player,"lastLoginTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("		guide=\"");
sb.append(O2oUtil.getSmarty4jProperty(player,"tutorial"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		team0 = { ");
sb.append("\r\n");
List playercostumeT310 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(player,"costumeT")){
if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof List) playercostumeT310=(List<?>)O2oUtil.getSmarty4jProperty(player,"costumeT");
else if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof int[]) playercostumeT310=Stream.of((int[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof long[]) playercostumeT310=Stream.of((long[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof float[]) playercostumeT310=Stream.of((float[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof double[]) playercostumeT310=Stream.of((double[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof byte[]) playercostumeT310=Stream.of((byte[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof String[]) playercostumeT310=Stream.of((String[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeT").getClass().isArray()) playercostumeT310=Stream.of(O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
}
playercostumeT310.forEach(itemT->{
try{
if((O2oUtil.compare(itemT,"!=",""))){
sb.append("					\"");
sb.append(itemT);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		team1 = { ");
sb.append("\r\n");
List playercostumeP405 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(player,"costumeP")){
if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof List) playercostumeP405=(List<?>)O2oUtil.getSmarty4jProperty(player,"costumeP");
else if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof int[]) playercostumeP405=Stream.of((int[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof long[]) playercostumeP405=Stream.of((long[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof float[]) playercostumeP405=Stream.of((float[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof double[]) playercostumeP405=Stream.of((double[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof byte[]) playercostumeP405=Stream.of((byte[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof String[]) playercostumeP405=Stream.of((String[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"costumeP").getClass().isArray()) playercostumeP405=Stream.of(O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
}
playercostumeP405.forEach(itemP->{
try{
if((O2oUtil.compare(itemP,"!=",""))){
sb.append("					\"");
sb.append(itemP);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		weapon_info = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(player,"pi"),"!=",null)){
sb.append("			{ ");
sb.append("\r\n");
sb.append("				itemid=");
sb.append(O2oUtil.getSmarty4jProperty(player,"pi.id"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				id=");
sb.append(O2oUtil.getSmarty4jProperty(player,"pi.itemId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(player,"pi.sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List playerpiresource929 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(player,"pi.resource")){
if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof List) playerpiresource929=(List<?>)O2oUtil.getSmarty4jProperty(player,"pi.resource");
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof int[]) playerpiresource929=Stream.of((int[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof long[]) playerpiresource929=Stream.of((long[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof float[]) playerpiresource929=Stream.of((float[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof double[]) playerpiresource929=Stream.of((double[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof byte[]) playerpiresource929=Stream.of((byte[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof String[]) playerpiresource929=Stream.of((String[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource").getClass().isArray()) playerpiresource929=Stream.of(O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
}
playerpiresource929.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("						\"");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("			} ");
sb.append("\r\n");
} else {
sb.append("				nil ");
sb.append("\r\n");
}
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("cost = ");
sb.append(context.get("cost"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}