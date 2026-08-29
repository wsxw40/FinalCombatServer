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

public class SimplePlayerAvatar implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("avatar_info ={ ");
sb.append("\r\n");
sb.append("	\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"name"));
sb.append("\",	 ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"lastGameSide"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"gender"));
sb.append("\",	 ");
sb.append("\r\n");
sb.append("	{ ");
sb.append("\r\n");
List playercostumeT479 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")){
if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof List) playercostumeT479=(List<?>)O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT");
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT").getClass().isArray()) playercostumeT479=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
}
playercostumeT479.forEach(itemT->{
try{
if((O2oUtil.compare(itemT,"!=",""))){
sb.append("				\"");
sb.append(itemT);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	{ ");
sb.append("\r\n");
List playercostumeP638 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")){
if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof List) playercostumeP638=(List<?>)O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP");
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP").getClass().isArray()) playercostumeP638=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
}
playercostumeP638.forEach(itemP->{
try{
if((O2oUtil.compare(itemP,"!=",""))){
sb.append("				\"");
sb.append(itemP);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	{ ");
sb.append("\r\n");
sb.append("		type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"pi.wId"));
sb.append(",  ");
sb.append("\r\n");
List playerpiresource242 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof List) playerpiresource242=(List<?>)O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource").getClass().isArray()) playerpiresource242=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
}
playerpiresource242.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("				\"");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	},	 ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}