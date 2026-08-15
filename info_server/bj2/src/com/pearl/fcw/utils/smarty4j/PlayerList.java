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
List players694 = new ArrayList<>();
if (null!=context.get("players")){
if (context.get("players") instanceof List) players694=(List<?>)context.get("players");
else if (context.get("players").getClass().isArray()) players694=Stream.of((Object[])context.get("players")).collect(Collectors.toList());
}
players694.forEach(player->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		exp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gender = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"gender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		last_side=");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"lastGameSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("		last_time=");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"lastLoginTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("		guide=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"tutorial"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		team0 = { ");
sb.append("\r\n");
List playercostumeT485 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(player,"costumeT")){
if (O2oUtil.getSmarty4jProperty(player,"costumeT") instanceof List) playercostumeT485=(List<?>)O2oUtil.getSmarty4jProperty(player,"costumeT");
else if (O2oUtil.getSmarty4jProperty(player,"costumeT").getClass().isArray()) playercostumeT485=Stream.of((Object[])O2oUtil.getSmarty4jProperty(player,"costumeT")).collect(Collectors.toList());
}
playercostumeT485.forEach(itemT->{
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
List playercostumeP897 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(player,"costumeP")){
if (O2oUtil.getSmarty4jProperty(player,"costumeP") instanceof List) playercostumeP897=(List<?>)O2oUtil.getSmarty4jProperty(player,"costumeP");
else if (O2oUtil.getSmarty4jProperty(player,"costumeP").getClass().isArray()) playercostumeP897=Stream.of((Object[])O2oUtil.getSmarty4jProperty(player,"costumeP")).collect(Collectors.toList());
}
playercostumeP897.forEach(itemP->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"pi.id"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"pi.itemId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"pi.sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List playerpiresource810 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(player,"pi.resource")){
if (O2oUtil.getSmarty4jProperty(player,"pi.resource") instanceof List) playerpiresource810=(List<?>)O2oUtil.getSmarty4jProperty(player,"pi.resource");
else if (O2oUtil.getSmarty4jProperty(player,"pi.resource").getClass().isArray()) playerpiresource810=Stream.of((Object[])O2oUtil.getSmarty4jProperty(player,"pi.resource")).collect(Collectors.toList());
}
playerpiresource810.forEach(itemS->{
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
sb.append("				{} ");
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